package com.cucumbercraft.Models;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class expectedExtract {

    private static HashMap<String, HashMap<String,String>> returnMasterMap(String sheetName)  {
        File file;
        FileInputStream fis2=null;
        XSSFWorkbook workbook2=null;
        String resolvedPath = null;
        try{
            resolvedPath = FrameworkConstants.getExcelLocationPreLoginContent();
            if (resolvedPath == null || resolvedPath.contains("null")) {
                throw new IllegalStateException(
                    "Environment is not set — resolved Excel path is invalid: [" + resolvedPath + "]. " +
                    "Ensure 'Environment' parameter is passed from the TestNG XML and @BeforeTest runs before step definitions load FrameworkConstants.");
            }
            file=new File(resolvedPath);
            fis2=new FileInputStream(file);
            workbook2=new XSSFWorkbook(fis2);
        }
        catch(Exception e) {
            System.out.println("Error in Excel Method load for path [" + resolvedPath + "]: " + e);
            throw new RuntimeException("Failed to load Excel workbook from [" + resolvedPath + "]. Root cause: " + e.getMessage(), e);
        }
        XSSFSheet sheet=workbook2.getSheet(sheetName);
        HashMap<String, HashMap<String,String>> returnMasterMapTemp=new HashMap<>();
        int columnTotal=sheet.getRow(0).getLastCellNum();
        HashMap<String,String> value;
        for (int i=1;i<columnTotal;i++){
            String Key=sheet.getRow(0).getCell(i).getStringCellValue();
            value=readSheetTemp(i,sheet);
            returnMasterMapTemp.put(Key,value);
        }
        try {
            fis2.close();
            workbook2.close();
        }catch(Exception e) {
            System.out.println("Error is Excel Method load "+ e);
        }
        return returnMasterMapTemp;
    }

    public static HashMap<String, HashMap<String,String>>returnMasterMapValues(String sheetName) {
        return returnMasterMap(sheetName);
    }

    private static HashMap<String,String> returnMasterNonMultipleMap(String sheetName)  {
        File file;
        FileInputStream fis2 = null;
        XSSFWorkbook workbook2=null;
        String resolvedPath = null;
        try {
            resolvedPath = FrameworkConstants.getExcelLocationPreLoginContent();
            if (resolvedPath == null || resolvedPath.contains("null")) {
                throw new IllegalStateException(
                    "Environment is not set — resolved Excel path is invalid: [" + resolvedPath + "]. " +
                    "Ensure 'Environment' parameter is passed from the TestNG XML and @BeforeTest runs before step definitions load FrameworkConstants.");
            }
            file = new File(resolvedPath);
            fis2 = new FileInputStream(file);
            workbook2 = new XSSFWorkbook(fis2);
        }
        catch(Exception e) {
            System.out.println("Error in Excel Method load for path [" + resolvedPath + "]: " + e);
            throw new RuntimeException("Failed to load Excel workbook from [" + resolvedPath + "]. Root cause: " + e.getMessage(), e);
        }
        XSSFSheet sheet=workbook2.getSheet(sheetName);
        HashMap<String,String> returnMasterNonMultipleMapTemp=new HashMap<>();
        int rowTotal=sheet.getLastRowNum();
        for (int i=1;i<=rowTotal;i++){
            String key=sheet.getRow(i).getCell(0).getStringCellValue();
            String value=sheet.getRow(i).getCell(1).getStringCellValue();
            returnMasterNonMultipleMapTemp.put(key,value);
            key=null;
            value=null;
        }
        try {
            fis2.close();
            workbook2.close();
        }
        catch(Exception e){
            System.out.println("Error is Excel Method load "+ e);
        }
        return returnMasterNonMultipleMapTemp;
    }

    private static HashMap<String, String>readSheetTemp(int cellValue, XSSFSheet sheet)  {
        HashMap<String, String> excelMap = new HashMap<String, String>();
        int totalRow = sheet.getLastRowNum();
        String cellTempValue;
        String cellTempVariable;
        for (int j=1;j<=totalRow;j++){
            cellTempValue=sheet.getRow(j).getCell(cellValue).getStringCellValue();
            cellTempVariable=sheet.getRow(j).getCell(0).getStringCellValue();
            if(!(cellTempValue.equalsIgnoreCase("NA"))){
                excelMap.put(cellTempVariable,cellTempValue);
            }
            cellTempValue=null;
            cellTempVariable=null;
        }
        return excelMap;
    }

    public static HashMap<String,String> extractExpected(String sheetName, String pageName)  {
        return returnMasterMap(sheetName).get(pageName);
    }


    public static HashMap<String,String> extractExpectedNonMultipleMap(String sheetName)  {
        return returnMasterNonMultipleMap(sheetName);
    }

    private static List<String> elemListFromArr(String value){
        return Arrays.asList(value.split("<>"));
    }

    private static HashMap<String, String> elemHMapFromArr(String value){
        HashMap<String, String> loadMap=new HashMap<>();
        List <String>arrListValue=  Arrays.asList(value.split(";"));
        String tempKey=null;
        String tempValue=null;
        String[] valueTempArr =null;
        for(String valueTemp:arrListValue){
            valueTempArr=valueTemp.split("<>");
            tempKey= valueTempArr[0];
            tempValue=valueTempArr[1];
            loadMap.put(tempKey,tempValue);
            valueTempArr=null;
            tempValue=null;
            tempKey=null;
        }
        return loadMap;
    }

    public static HashMap<String, String> compHdrFtrSvIdLoadMap(HashMap<String, String> nonMasterMap,String expHeaderListVar){
        return elemHMapFromArr(nonMasterMap.get(expHeaderListVar));
    }

    public static List<String> compHdrFtrSvIdLoadList(HashMap<String, String> nonMasterMap, String expHeaderListVar){
        return elemListFromArr(nonMasterMap.get(expHeaderListVar));
    }


//    public static HashMap<String, HashMap<String,String>> returnMasterBuyNowTestData(String sheetName){
//        File file;
//        FileInputStream fis2=null;
//        XSSFWorkbook workbook2=null;
//        try{
//            file=new File(FrameworkConstants.getExcelLocation());
//            fis2=new FileInputStream(file);
//            workbook2=new XSSFWorkbook(fis2);
//        }
//        catch(Exception e) {
//            System.out.println("Error is Excel Method load "+ e);
//        }
//        XSSFSheet sheet=workbook2.getSheet(sheetName);
//        HashMap<String, HashMap<String,String>> returnMasterMapTemp=new HashMap<>();
//        int columnTotal=sheet.getRow(0).getLastCellNum();
//        HashMap<String,String> value;
//        for (int i=1;i<columnTotal;i++){
//            String Key=sheet.getRow(0).getCell(i).getStringCellValue();
//            value=readSheetTemp(i,sheet);
//            returnMasterMapTemp.put(Key,value);
//        }
//        try {
//            fis2.close();
//            workbook2.close();
//        }catch(Exception e) {
//            System.out.println("Error is Excel Method load "+ e);
//        }
//        return returnMasterMapTemp;
//    }



    ////////////////



    public static HashMap<String,String> extractExpected1(String sheetName, String pageName, String workbook)  {
        return returnMasterMap1(sheetName,workbook).get(pageName);
    }

    private static HashMap<String, HashMap<String,String>> returnMasterMap1(String sheetName, String workbook)  {
        File file;
        FileInputStream fis2=null;
        XSSFWorkbook workbook2=null;
        try{
            file=new File(workbook);
            fis2=new FileInputStream(file);
            workbook2=new XSSFWorkbook(fis2);
        }
        catch(Exception e) {
            System.out.println("Error is Excel Method load "+ e);
        }
        XSSFSheet sheet=workbook2.getSheet(sheetName);
        HashMap<String, HashMap<String,String>> returnMasterMapTemp=new HashMap<>();
        int columnTotal=sheet.getRow(0).getLastCellNum();
        HashMap<String,String> value;
        for (int i=1;i<columnTotal;i++){
            String Key=sheet.getRow(0).getCell(i).getStringCellValue();
            value=readSheetTemp(i,sheet);
            returnMasterMapTemp.put(Key,value);
        }
        try {
            fis2.close();
            workbook2.close();
        }catch(Exception e) {
            System.out.println("Error is Excel Method load "+ e);
        }
        return returnMasterMapTemp;
    }

}
