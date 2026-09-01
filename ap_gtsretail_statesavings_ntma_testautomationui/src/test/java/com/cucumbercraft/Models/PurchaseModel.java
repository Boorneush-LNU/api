package com.cucumbercraft.Models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelConstructor;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class PurchaseModel {

    @ExcelCellName("TestCaseName")
    private String testcase;

    //To be refactored later as a Master Test Case column in the excel - this might be utilized only for the PB Admin scenarios (exhaustive testing) unless there is a better approach to deal with the scenarios for that system.
//  @ExcelCellName("TestCaseName")
//    private String masterTestCaseName;

    @ExcelCellName("Entrypoint")
    private String entry;

    @ExcelCellName("Product")
    private List<String> product;

    @ExcelCellName("Journey")
    private String journey;

    @ExcelCellName("Aml_Indicator")
    private String amlFlag;

    @ExcelCellName("Source of Funds")
    private String sourceOfFunds;

    @ExcelCellName("Purpose of Account")
    private String purposeOfAccount;

    @ExcelCellName("Amount")
    private List<String> amount;

    @ExcelCellName("Firstname")
    private String firstName;


    @ExcelCellName("PB_Holder_Joint")
    private String pbHolderFlag;

    @ExcelCellName("DOB")
    private String firstUserDOB;

    @ExcelCellName("DOB2")
    private String secondUserDOB;

    @ExcelCellName("SSCN")
    private String firstUserSSCN;

    @ExcelCellName("SSCN2")
    private String secondUserSSCN;

    @ExcelCellName("Surname")
    private String firstUserSurname;

    @ExcelCellName("Surname2")
    private String secondUserSurname;

    @ExcelCellName("PB_Holder_Fname")
    private String pbFirstName;

    @ExcelCellName("PB_Holder_Surname")
    private String pbSurName;

    @ExcelCellName("PB_Holder_Address")
    private String pbAddress;

    @ExcelCellName("PB_Holder_Fname2")
    private String pbFirstName2;

    @ExcelCellName("PB_Holder_Surname2")
    private String pbSurName2;

    @ExcelCellName("Address")
    private String address;

    @ExcelCellName("Email")
    private String firstUserEmail;

    @ExcelCellName("Email2")
    private String secondUserEmail;

    @ExcelCellName("Password")
    private String password;

    @ExcelCellName("Mobilenumber")
    private String mobileNum;

    @ExcelCellName("CDE")
    private List<String> CDE;

    @ExcelCellName("Paymnt_Details")
    private String paymentDetails;

    @ExcelCellName("expDate")
    private String expDate;

    @ExcelCellName("expCardType")
    private String expCardType;

    @ExcelCellName("expCardNo")
    private String expCardNo;

    @ExcelCellName("expName")
    private String expName;

    @ExcelCellName("expAuthCode")
    private String expAuthCode;

    @ExcelCellName("expPayRef")
    private String expPayRef;

    @ExcelCellName("expValue")
    private String expValue;

    @ExcelCellName("expPurDet_Name")
    private String expPurDet_Name;

    @ExcelCellName("expPurDet_AddressLine1")
    private String expPurDet_AddressLine1;

    @ExcelCellName("expPurDet_AddressLine2")
    private String expPurDet_AddressLine2;

    @ExcelCellName("expPurDet_AddressLine3")
    private String expPurDet_AddressLine3;

    @ExcelCellName("expPurDet_County")
    private String expPurDet_County;

    @ExcelCellName("expPurDet_Eircode")
    private String expPurDet_Eircode;

    @ExcelCellName("expPurDet_ContactNumber")
    private String expPurDet_ContactNumber;

    @ExcelCellName("expPurDet_EmailAddress")
    private String expPurDet_EmailAddress;

    @ExcelCellName("expPurDet_AccountNo")
    private String expPurDet_AccountNo;

    @ExcelCellName("expPayDet_Edit")
    private String expPayDet_Edit;

    @ExcelCellName("expOrderDetails")
    private List<String> expOrderDetails;

    @ExcelCellName("expAdmin_ChangeStatus")
    private String expAdmin_ChangeStatus;

    @ExcelCellName("expAdmin_Source")
    private String expAdmin_Source;

    @ExcelCellName("expOrderDetails")
    private String pbAdmin_ExpOrderDetails;

    @ExcelCellName("expPbSSUserInd")
    private String pbSSUserInd;

}
