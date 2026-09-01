package com.cucumbercraft.POMPages.ExpeditedReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Expedited_ErrorMessage {
    WebDriver driver;
    WebDriverUtil webUtil;

    public Expedited_ErrorMessage(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }

    //Lets get Started page
    private final By fName = By.id("first-name-error");
    private final By SName = By.id("surname-error");
    private final By DOB = By.id("dob-error");
    private final By blankSSCN = By.id("required-ppsn-sscn-error");
    private final By invalidSSCN = By.id("ppsn-sscn-error");

    public void errorLetGetStarted(String Indicator)  {
        switch (Indicator) {
            case "Blank":
                webUtil.gettextlog(fName,String::equals,"Please enter your first name","First name error message");
                webUtil.gettextlog(SName,String::equals,"Please enter your surname","Sur-name error message");
                webUtil.gettextlog(DOB,String::equals,"Please enter a valid date of birth","DOB error message");
                webUtil.gettextlog(blankSSCN,String::equals,"Please enter your PPSN or SSCN","SSCN error message");

                break;
            case "Incorrect":
                webUtil.gettextlog(invalidSSCN,String::equals,"Please enter a valid PPSN or SSCN number");

                break;
            default:
                throw new ExceptionUtils("Please check the Indicator Name");
        }
    }

    //Enter your Email Page
    private final By blankEmail = By.id("RFVEmailAddress");
    private final By invalidEmail = By.id("REVEmailAddress");
    private final By blankReEmail = By.id("RFVCompareEmail");
    private final By invalidReEmail = By.id("REVCompareEmailRegex");
    private final By compareEmail = By.id("CVCompareText");
    private final By checkBox = By.id("CVCheck");

    public void errorEmailPage(String Indicator) throws Exception {
        switch (Indicator) {
            case "Blank":
                if (webUtil.isElementDisplayed(blankEmail, 10)) {
                    if (!webUtil.getText(blankEmail).equals("Please enter your email address.")) {
                        throw new ExceptionUtils("Email error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Email error message xpath may have changed");
                }
                if (webUtil.isElementDisplayed(blankReEmail, 10)) {
                    if (!webUtil.getText(blankReEmail).equals("Please re-enter your email address.")) {
                        throw new ExceptionUtils("Re-enter email error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Re-enter email error message xpath may have changed");
                }
                if (webUtil.isElementDisplayed(checkBox, 10)) {
                    if (!webUtil.getText(checkBox).equals("You must accept the Terms & Conditions to proceed.")) {
                        throw new ExceptionUtils("consent error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("consent error message xpath may have changed");
                }
                break;
            case "Invalid":
                if (webUtil.isElementDisplayed(invalidEmail, 10)) {
                    if (!webUtil.getText(invalidEmail).equals("Please enter a valid email address.")) {
                        throw new ExceptionUtils("Invalid email error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Invalid email error message xpath may have changed");
                }
                if (webUtil.isElementDisplayed(invalidReEmail, 10)) {
                    if (!webUtil.getText(invalidReEmail).equals("Please enter a valid email address.")) {
                        throw new ExceptionUtils("Invalid Re-enter email error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Invalid Re-enter email error message xpath may have changed");
                }
                break;
            case "EmailMismatch":
                if (webUtil.isElementDisplayed(compareEmail, 10)) {
                    if (!webUtil.getText(compareEmail).equals("The email addresses do not match.")) {
                        throw new ExceptionUtils("compare email error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("comapare email error message xpath may have changed");
                }
                break;
            default:
                throw new ExceptionUtils("Please check the Indicator Name");
        }
    }

    //Enter your PIN page
    private final By blankPIN = By.id("RFVRegCode");
    private final By incorrectPIN = By.id("apiError");
    private final By lessThan6 = By.id("CFVRegCode");

    public void errorPINPage(String PinIndicator) throws Exception {
        switch (PinIndicator) {
            case "Blank":
                if (webUtil.isElementDisplayed(blankPIN, 10)) {
                    if (!webUtil.getText(blankPIN).equals("Please enter your PIN.")) {
                        throw new ExceptionUtils("Blank PIN Error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Blank PIN Error message xpath may have changed");
                }
                break;
            case "Incorrect":
                if (webUtil.isElementDisplayed(incorrectPIN, 10)) {
                    if (!webUtil.getText(incorrectPIN).equals("Incorrect email address and/or PIN. Please try again.")) {
                        throw new ExceptionUtils("Expired or incorrect or less than 6 digit pin error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("incorrect pin error message xpath may have changed");
                }
                break;
            case "LessThan6":
                if (webUtil.isElementDisplayed(lessThan6, 10)) {
                    if (!webUtil.getText(lessThan6).equals("Incorrect email address and/or PIN. Please try again.")) {
                        throw new ExceptionUtils("Expired or incorrect or less than 6 digit pin error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("incorrect pin error message xpath may have changed");
                }
                break;
            default:
                throw new ExceptionUtils("Please check the Indicator Name");
        }
    }

    //Enter Your Mobile number page
    private final By blankMobile = By.id("mobile-error");
    private final By blankReMobile = By.id("mobile-errorB");
    private final By compareMobile = By.id("compare-mobile");
    private final By distanceAlgoMobile = By.id("error");
    private final By comparePrefix = By.id("compare-prefix");

    public void errorMobilePage(String MobileIndicator) throws Exception {
        switch (MobileIndicator) {
            case "Blank":
                if (webUtil.isElementDisplayed(blankMobile, 10)) {
                    if (!webUtil.getText(blankMobile).equals("Please enter your mobile phone number.")) {
                        throw new ExceptionUtils("Blank Mobile number error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Mobile number Blank error message xpath may have changed");
                }
                if (webUtil.isElementDisplayed(blankReMobile, 10)) {
                    if (!webUtil.getText(blankReMobile).equals("Please re-enter your mobile phone number.")) {
                        throw new ExceptionUtils("Blank Re-enter Mobile number error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Re-Mobile number blank error message xpath may have changed");
                }
                break;
            case "Mobile_MisMatch":
                Thread.sleep(2000);
                if (webUtil.isElementDisplayed(compareMobile, 15)) {
                    if (!webUtil.getText(compareMobile).equals("The mobile phone numbers do not match.")) {
                        throw new ExceptionUtils("compare Mobile number error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Mobile number MisMatch error message xpath may have changed");
                }
                break;
            case "Incorrect":
                if (webUtil.isElementDisplayed(distanceAlgoMobile, 10)) {
                    if (!webUtil.getText(distanceAlgoMobile).equals("Invalid or partial mobile phone number entered. Please try again.")) {
                        throw new ExceptionUtils("Distance Algorithm not followed error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Invalid mobile number error message xpath may have changed");
                }
                break;
            case "Prefix_MisMatch":
                if (webUtil.isElementDisplayed(comparePrefix, 10)) {
                    if (!webUtil.getText(comparePrefix).equals("Please select your prefix.")) {
                        throw new ExceptionUtils("compare Prefix error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Prefix not matched error message xpath may have changed");
                }
                break;
            default:
                throw new ExceptionUtils("Please check the Indicator Name");
        }
    }

    ///Security code page
    private final By blankSecurity = By.id("RFVPINCode");
    private final By lessThanSixCode = By.id("REVCode");
    private final By invalidExpSecurity = By.id("ApiError");

    public void errorSecurityPage(String Indicator) throws Exception {
        switch (Indicator) {
            case "Blank":
                if (webUtil.isElementDisplayed(blankSecurity, 10)) {
                    if (!webUtil.getText(blankSecurity).equals("Please enter your verification code.")) {
                        throw new ExceptionUtils("Blank Security code error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Blank Security code error message xpath not matched");
                }
                break;
            case "LessThan6":
                if (webUtil.isElementDisplayed(lessThanSixCode, 10)) {
                    if (!webUtil.getText(lessThanSixCode).equals("Please enter the six-digit verification code sent to your registered mobile number.")) {
                        throw new ExceptionUtils("Less Than Six Digit Security code error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Less than six digit Security code error message xpath may have changed");
                }
                break;
            case "Incorrect":
                if (webUtil.isElementDisplayed(invalidExpSecurity, 10)) {
                    if (!webUtil.getText(invalidExpSecurity).equals("Verification code is invalid or has expired. Please try again.")) {
                        throw new ExceptionUtils("Invalid or Expired Security code error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Invalid or Expired Security code error message xpath may have changed");
                }
                break;
            case "Expired":
                if (webUtil.isElementDisplayed(invalidExpSecurity, 10)) {
                    if (!webUtil.getText(invalidExpSecurity).equals("Verification code is invalid or has expired. Please try again.")) {
                        throw new ExceptionUtils("Invalid or Expired Security code error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Expired Security code error message xpath may have changed");
                }
                break;
            default:
                throw new ExceptionUtils("Please check the Indicator Name");
        }
    }

    //Password page
    private final By blankPassword = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedCreatePassword_RFVtxtPWD");
    private final By reqNotMatch = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedCreatePassword_ctl01");

    public void errorPasswordPage(String Indicator) throws Exception {
        switch (Indicator) {
            case "Blank":
                if (webUtil.isElementDisplayed(blankPassword, 10)) {
                    if (!webUtil.getText(blankPassword).equals("Please enter your new password.")) {
                        throw new ExceptionUtils("Blank Password error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Blank Password error message xpath may have changed");
                }
                break;
            case "Req_Not_Matched":
                if (webUtil.isElementDisplayed(reqNotMatch, 10)) {
                    if (!webUtil.getText(reqNotMatch).equals("Please enter a valid password.")) {
                        throw new ExceptionUtils("Password Requirement error message is not similar");
                    }
                } else {
                    throw new ExceptionUtils("Password Requiement error message xpath may have changed");
                }
                break;
            default:
                throw new ExceptionUtils("Please check the Indicator Name");
        }
    }

    //Already Register
    private final By Already_header = By.id("headerCaseOne");
    private final By Already_paragraph = By.id("firstDescriptionCaseOne");
    private final String Already_paragrapCont = "If you believe this to be incorrect, or you did not register your details, please contact Ireland State Savings on 0818 20 50 60 or 01 705 7200 and we will assist you.";

    public void alreadyRegisterPage()  {
        webUtil.gettextlog(Already_header,String::equals,"These details are already registered");
        webUtil.gettextlog(Already_paragraph,String::equals,Already_paragrapCont);

    }
}