package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.text.SimpleDateFormat;
import java.util.*;

public class mockDataSteps extends MasterStepDefs{

    Excelutils excelRow = new Excelutils();
    PurchaseModel data;


    @Given("Mock User Data for Set-{string}")
    public void mockUserDataForSet(String testCaseNameSet) {
        String setDetails = "TC2;TC3;TC13;TC1;TC4";
//        Approved
//        String purTransListref="PB2025426722;PB2025426720;PB2025426719";
//        Parked
//        String purTransListref="PB2025426704;PB2025426703;PB2025426702";
//        For Approval

        String purTransListref="SS2025426628;SS2025426629;SS2025426631;SS2025426632;SS2025426642";
        List<String>purList=Arrays.asList(purTransListref.split(";"));
        List<String> testCaseNameList1 = Arrays.asList(setDetails.split(";"));
        expPurTransSummaryMap=new HashMap<>();
        expPurTransDetailsMasterMap=new HashMap<>();
        payRefList=new ArrayList<>();
        for (int i=0;i<purList.size();i++) {
            data = excelRow.getTestData(FrameworkConstants.getExcelLocationAutomationRegression(), config.getBuyNowSheetName(),testCaseNameList1.get(i) );
            expPurTransSummaryMap.put(purList.get(i), getExpList1(data, purList.get(i)));
            expPurTransDetailsMasterMap.put(purList.get(i), expOrderMapTemp1(data, purList.get(i)));
            payRefList.add(purList.get(i));
        }
//        FrameworkLogger.log(LogType.EXTENTANDINFOPASS,getMethodExec2()+"PURLIST >>>>>>>"+purList);
        System.out.println("PURLIST >>>>>>>"+purList);
        System.out.println("PURLIST PAYREF >>>>>>>"+payRefList);
        System.out.println("MAPSUMMARYEXP >>>>>>>"+expPurTransSummaryMap);
        System.out.println("MAPMASTEREXP >>>>>>>"+expPurTransDetailsMasterMap);
    }

    public List<String> getExpList1(PurchaseModel data, String payRefNo) {
        List<String> expTempList=new ArrayList<>();
        Date dateCurrent=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String formatDate=sdf.format(dateCurrent);
        expTempList.add(formatDate);
        expTempList.add(data.getExpCardType());
        expTempList.add(data.getExpCardNo());
        expTempList.add(data.getExpName());
        expTempList.add(data.getExpAuthCode());
        expTempList.add(payRefNo);
        expTempList.add("€"+data.getExpValue());
//            add the total amount method in the data ref sheet and also in the model
        expTempList.add("");
        expTempList.add("");
        return expTempList;
    }


    private HashMap<String,String> expOrderMapTemp1 (PurchaseModel data, String paymentRefNo) {
        Date dateCurrent=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String formatDate=sdf.format(dateCurrent);
        HashMap <String,String> tempMap1=new HashMap<>();
        tempMap1.put("Card Holder Name :" ,data.getExpName());
        tempMap1.put("Name :" ,data.getExpPurDet_Name());
        tempMap1.put("Order Date :" ,formatDate);
        tempMap1.put("Last Modified :" ,formatDate);
        tempMap1.put("County :" ,data.getExpPurDet_County());
        tempMap1.put("Card Number :" ,data.getExpCardNo());
        tempMap1.put("AddressLine3 :",data.getExpPurDet_AddressLine3());
        tempMap1.put("Amount :" ,data.getExpValue());
        tempMap1.put("Card type :" ,data.getExpCardType());
        tempMap1.put("AddressLine2 :",data.getExpPurDet_AddressLine2());
        tempMap1.put("AddressLine1 :",data.getExpPurDet_AddressLine1());
        tempMap1.put("Payment Ref :" ,paymentRefNo);
        tempMap1.put("Edit :" ,data.getExpPayDet_Edit());
        tempMap1.put("Contact Number :" ,data.getExpPurDet_ContactNumber());
        tempMap1.put("Email Address :" ,data.getExpPurDet_EmailAddress());
        tempMap1.put("Auth Code :" ,data.getExpAuthCode());
        tempMap1.put("Eircode :" ,data.getExpPurDet_Eircode());
        tempMap1.put("Change Status :" ,data.getExpAdmin_ChangeStatus());
        tempMap1.put("Account No :" ,data.getExpPurDet_AccountNo());

        for(int i=1;i<=data.getExpOrderDetails().size();i++){
            tempMap1.put("Order Details_Item"+i,data.getExpOrderDetails().get(i-1));
        }

        return tempMap1;
    }

    public String methodNameExec(){
        return new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
    }

    public StackTraceElement[] stackTraceMethod(){
        return new Throwable().getStackTrace();
    }


    public String getMethodExec2(){
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        for(StackTraceElement stack:stackTrace){
            System.out.println(stack);
        }

        System.out.println(stackTrace);
        return stackTrace[1] .getMethodName();
    }


}
