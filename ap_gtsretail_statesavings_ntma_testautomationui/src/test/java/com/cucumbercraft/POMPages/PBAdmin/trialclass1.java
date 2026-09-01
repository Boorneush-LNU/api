package com.cucumbercraft.POMPages.PBAdmin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class trialclass1 {
    public static void main(String[] args) throws IOException {
     trialclass1 t1=new trialclass1();
     ArrayList <ExpPayRefData> arrPayRefData=new ArrayList<>();
     List<String> arr=List.of("D1","D2","D3");
HashMap<String,List<String>> amp1=new HashMap<>();
amp1.put("D1",List.of("D1V1","D1V2","D1V3","D1V4","D1V5","D1V6"));
        amp1.put("D2",List.of("D2V1","D2V2","D2V3","D2V4","D2V5","D2V6"));
        amp1.put("D3",List.of("D3V1","D3V2","D3V3","D3V4","D3V5","D3V6"));

     for(String arrV:arr){
         arrPayRefData.add(t1.loadJsonMethod(amp1.get(arrV)));
     }



t1.printJson(arrPayRefData);

t1.extractDataJson();

    }

    public ExpPayRefData loadJsonMethod(List<String> valueList) throws IOException {
        return new ExpPayRefData
                (       valueList.get(0),
                        valueList.get(1),
                        valueList.get(2),
                        valueList.get(3),
                        valueList.get(4),
                        valueList.get(5),
                        valueList.get(6),
                        valueList.get(7),
                        valueList.get(8),
                        valueList.get(9),
                        valueList.get(10),
                        valueList.get(11),
                        valueList.get(12)
                );
    }

    public void printJson(ArrayList <ExpPayRefData> arrPayRefData) throws IOException {
        String filePath=System.getProperty("user.dir") + File.separator + "src\\test\\resources" + File.separator+"Test_Data"+ File.separator+"tempJSONFILE.json";
        File file=new File(filePath);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWr=new FileWriter(file);
        gson.toJson(arrPayRefData, fileWr);
        fileWr.close();
    }

    public void extractDataJson() throws IOException {
        String filePath=System.getProperty("user.dir") + File.separator + "src\\test\\resources" + File.separator+"Test_Data"+ File.separator+"tempJSONFILE.json";
        File file=new File(filePath);
        FileReader filer=new FileReader(file);
        Gson gson=new Gson();
        ExpPayRefData[] expd1=gson.fromJson(filer, ExpPayRefData[].class);
        for (ExpPayRefData exp: expd1){
            System.out.println(exp.getExpListValues());
        }
        filer.close();
    }

}
