package com.cucumbercraft.Models;


import com.cucumbercraft.framework.ExceptionUtils;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Excelutils {

   public static Excelutils getInstance() {
        return new Excelutils();
    }

    public PurchaseModel getTestData(String filepath,String sheetName,String testcase) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName(sheetName).build();
        List<PurchaseModel> allRows = Poiji.fromExcel(new File(filepath), PurchaseModel.class, options);
        return allRows
                .stream()
                .filter(testData -> testData.getTestcase().equals(testcase))
                .findFirst()
                .get();

    }



    public <T> T getData(String filepath, String sheetName, String testcase, Class<T> type, String methodName) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().preferNullOverDefault(true).sheetName(sheetName).build();
        List<T> allRows = Poiji.fromExcel(new File(filepath), type, options);
        return allRows
                .stream()
                .filter(testData -> {
                    try {
                        return type.getMethod(methodName).invoke(testData).equals(testcase);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst()
                .orElseThrow(() -> new ExceptionUtils("Testcase not found"));

    }

    public <T> List<T>  getTestData(String filepath, String sheetName, Class<T> type) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName(sheetName).build();
        return Poiji.fromExcel(new File(filepath), type, options);

    }






    public <T> int getRowIndex(String linkName, List<T> allData) {
        if (linkName == null || allData == null || allData.isEmpty()) {
            throw new IllegalArgumentException("Link name and regstrnData list cannot be null or empty");
        }

        return allData.stream()
                .filter(data -> {
                    try {
                        String headerText = (String) data.getClass()
                                .getMethod("getHdrLinkText")
                                .invoke(data);
                        return linkName.equals(headerText);
                    } catch (Exception e) {
                        throw new ExceptionUtils("Failed to get header text: " + e.getMessage());
                    }
                })
                .map(data -> {
                    try {
                        return (Integer) data.getClass()
                                .getMethod("getRownum")
                                .invoke(data);
                    } catch (Exception e) {
                        throw new ExceptionUtils("Failed to get row number: " + e.getMessage());
                    }
                })
                .findFirst()
                .orElseThrow(() -> new ExceptionUtils("Link not found: " + linkName));
    }

    public <T> void appendNewRow(String filePath, String sheetName, T newData, Class<T> type) {
            try {
                // Read existing data
                PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                        .sheetName(sheetName)
                        .build();
                List<T> data = Poiji.fromExcel(new File(filePath), type, options);

                // Set row number for new data if it has getRownum and setRownum methods
                try {
                    int lastRowNum = data.isEmpty() ? 0 :
                        (int) data.get(data.size() - 1).getClass().getMethod("getRownum").invoke(data.get(data.size() - 1));
                    newData.getClass().getMethod("setRownum", int.class).invoke(newData, lastRowNum + 1);
                } catch (NoSuchMethodException e) {
                    throw new ExceptionUtils("setRownum method not found in new data class: " + e.getMessage());

                }

                // Add new row
                data.add(newData);

                // Write back to Excel
                Poiji.toExcel(new File(filePath),type, data,  options);
            } catch (Exception e) {
                throw new ExceptionUtils("Failed to append row to Excel: " + e.getMessage());
            }
        }


}
