package com.cucumbercraft.Models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;

import java.util.List;

@Data
public class TestData {
    @ExcelRow
    private int rownum;

    @ExcelCellName("Scenario")
    private String testcaseName;

    @ExcelCellName("Username")
    private String username ="";


    @ExcelCellName("Password")
    private String password="";

    @ExcelCellName("Number")
    private String mobileNumber;

    @ExcelCellName("Holding ID")
    private String holdingID;

    @ExcelCellName("IBAN_Number")
    private String IBAN;

    @ExcelCellName("Journey")
    private String journey;

    @ExcelCellName("DOB")
    private List<String> dob;

    @ExcelCellName("Product")
    private String product;

    @ExcelCellName("Products")
    private List<String> reinvestProducts;

    @ExcelCellName("Amount")
    private List<String> reinvestAmounts;

    @ExcelCellName("Allocate Full Amount Index")
    private String allocateAllAmountIndex;

    @ExcelCellName("Prize Bond Range")
    private String bondRange;

    @ExcelCellName("Prize Amount")
    private String chooseAmount;

    @ExcelCellName("MethodType")
    private String optionType;

    @ExcelCellName("POSB Account Number")
    private String posbAccountNumber;

    @ExcelCellName("IBAN_Added_Status")
    private String IBANAddedStatus;

    @ExcelCellName("Error Message")
    private List<String> errormessages;

    @ExcelCellName("OTP")
    private String OTP;

    @ExcelCellName("Name")
    private String Name;

    //******************** H&S *******************//

    @ExcelCellName("Link")
    private String hdrLinkText="";

    @ExcelCellName("Paragraph")
    private String para="";

    @ExcelCellName("Bullet List")
    private String bulletPoint="";

    @ExcelCellName("Bullet List2")
    private String bulletPointSecond="";

    @ExcelCellName("Sub Header")
    private String subHdr="";

    @ExcelCellName("Sub Header2")
    private String subHdr2="";

    @ExcelCellName("Table")
    private String table="";

    @ExcelCellName("Anchor")
    private String anchor="";

    @ExcelCellName("Article")
    private String article="";

    @ExcelCellName("H1")
    private String H1;

    @ExcelCellName("H4")
    private String H4;

    @ExcelCellName("P")
    private String P;
    @ExcelCellName("A")
    private String A;

    @ExcelCellName("H2")
    private String H2;

    @ExcelCellName("H3")
    private String H3;

    @ExcelCellName("LI")
    private String LI;

    @ExcelCellName("LI2")
    private String LI2;

    //******************** Slider & Modal Content *******************//

    @ExcelCellName("Slider name")
    private String sliderName;

    @ExcelCellName("Slider header")
    private String sliderHdr;

    @ExcelCellName("Slider Content")
    private  String sliderContent;

    @ExcelCellName("Slider Primary Button")
    private String sliderPrimaryButton;

    @ExcelCellName("Slider Secondary Button")
    private String sliderSecondaryButton;

    //******************** Hardcode *******************//


//    @ExcelCellName("Expected Result")
//    private String ExpectedResult;

    @ExcelCellName("Key")
    private String ExpectedResult;


    @ExcelCellName("Key")
    private String ExpectedKey;


    @ExcelCellName("Key")
    private String key;


    @ExcelCellName("Value")
    private String expectedValue;

    public String getKey() {
        return key;
    }


    public String getExpectedValue() {
        return expectedValue;
    }



}
