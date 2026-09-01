package com.cucumbercraft.Models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Registration {

    @ExcelRow
    private int rownum;

    @ExcelCellName("Scenario")
    private String scenario;



        @ExcelCellName("FirstName")
        private String firstName;
        @ExcelCellName("SecondName")
        private String secondName;
        @ExcelCellName("DOB")
        private String dob;



        @ExcelCellName("EmailAddress")
        private String emailAddress;
        @ExcelCellName("Re-EnterEmailAddress")
        private String reEnterEmailAddress;
        @ExcelCellName("MobileNumber")
        private String mobileNumber;
        @ExcelCellName("Re-EnterMobileNumber")
        private String reEnterMobileNumber;
        @ExcelCellName("prefix")
        private String prefix;
        @ExcelCellName("Prefix re-enter")
        private String prefixReEnter;



        @ExcelCellName("SSCN")
        private String sscn;
        @ExcelCellName("PIN")
        private String pin;
        @ExcelCellName("Password")
        private String password;
        @ExcelCellName("addressLine1")
        private String addressLine1;
        @ExcelCellName("addressLine2")
        private String addressLine2;
        @ExcelCellName("town")
        private String town;
        @ExcelCellName("eircode")
        private String eircode;
        @ExcelCellName("countyCode")
        private String countyCode;
        @ExcelCellName("countryCode")
        private String countryCode;
        @ExcelCellName("Country_State_Region")
        private String countryStateRegion;
        @ExcelCellName("country")
        private String country;
        @ExcelCellName("registrationType")
        private String registrationType;
        @ExcelCellName("otpIndicator")
        private String otpIndicator;
        @ExcelCellName("otpNumber")
        private String otpNumber;
        @ExcelCellName("Token")
        private String token;
        @ExcelCellName("PIN_Web")
        private String pinWeb;
        @ExcelCellName("Results")
        private String results;



}

