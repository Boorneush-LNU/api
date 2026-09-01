package com.cucumbercraft.POMPages.BuyNow;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import com.cucumbercraft.framework.WebDriverUtil;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Map;


public class YourDetail {

    private final By hdrPersonalDetails = By.xpath("//h4[text()='Personal Details']");
    private final By lblApplicationType = By.cssSelector("#lblUser_ApplicationType");
    private final By lblSole = By.cssSelector("label[for='chkUser_SingleAppType']");
    private final By InvalidDOBPBGiftTwo = By.xpath("//span[@id='dob-custom-error_2']");
    private final By lblSurname = By.xpath("//input[@id='txtUser_Surname']/preceding-sibling::label");
    private final By lblDOB = By.xpath("//input[@id='DayOfBirth']/preceding-sibling::label");
    private final By btnDOBtooltip = By.cssSelector("button[aria-describedby='lblUser_DoBTooltip']");
    private final By txtDOBtooltip = By.cssSelector("#lblUser_DoBTooltip");
    private final By lblPPSN = By.xpath("//input[@id='txtUser_PPSNumber']/preceding-sibling::label[1]");
    private final By btnPPSNtooltip = By.cssSelector("button[aria-describedby='lblUser_PPSNumberTooltip']");
    private final By txtPPSNtooltip = By.cssSelector("#lblUser_PPSNumberTooltip");
    private final By lblEmail = By.xpath("//input[@id='txtUser_Email']/preceding-sibling::label[1]");
    private final By jointRadio = By.cssSelector("#chkUser_JointAppType");
    private final By lblJoint = By.cssSelector("label[for='chkUser_JointAppType']");
    private final By lblJointSurname = By.xpath("//input[@id='txtUser_Surname2']/preceding-sibling::label");
    private final By lblJointDOB = By.xpath("//input[@id='DayOfBirth2']/preceding-sibling::label");
    private final By btnJointDOB = By.cssSelector("button[aria-describedby='lblUser_DoBTooltip2']");
    private final By txtJointDOBTooltip = By.cssSelector("#lblUser_DoBTooltip2");
    private final By lblJointPPSN = By.xpath("(//input[@id='txtUser_PPSNumber2']/preceding-sibling::label)[2]");
    private final By btnJointPPSNtooltip = By.cssSelector("button[aria-describedby='lblUser_PPSNumberTooltip2']");
    private final By txtJointPPSN = By.id("lblUser_PPSNumberTooltip2");
    private final By lblJointEmail = By.xpath("(//input[@id='txtUser_Email2']/preceding-sibling::label)[3]");
    private final By fldFirstName = By.id("txtGuest_FirstName");
    private final By fldJointFirstName = By.id("txtGuest2_FirstName");
    private final By fldPrimarySurname = By.id("txtGuest_Surname");
    private final By fldSecondarySurname = By.id("txtGuest2_Surname");
    private final By fldSurname = By.id("txtUser_Surname");
    private final By fldJointSurname = By.id("txtUser_Surname2");
    private final By fldDate = By.id("DayOfBirth");
    private final By fldMonth = By.id("MonthOfBirth");
    private final By fldYear = By.id("YearOfBirth");
    private final By fldJointDate = By.id("DayOfBirth2");
    private final By fldJointMonth = By.id("MonthOfBirth2");
    private final By fldJointYear = By.id("YearOfBirth2");
    private final By fldPPSN = By.id("txtUser_PPSNumber");
    private final By fldJointPPSN = By.id("txtUser_PPSNumber2");
    private final By fldEmail = By.id("txtUser_Email");
    private final By fldJointEmail = By.id("txtUser_Email2");
    private final By fldPBEmail = By.id("txtGuest_Email");
    private final By chkAgreeTerms = By.id("chkUser_Terms");  //span[@id='CVchkUser_Terms']
//    private final By chkAgreeTerms = By.xpath("//span[@id='CVchkUser_Terms']");
    private final By chkAgreeTermsGuest = By.id("chkGuest_Terms");
    private final By btnContOrder = By.xpath("//button[normalize-space()='Continue to your order']");
    private final By btnContOrderIrish = By.xpath("//button[normalize-space()=concat('Lean ar aghaidh chuig d', \"'\", 'ordú')]");
    private final String contentDOBTooltip = "A Minor (under 18) may only purchase Ireland State Savings products subject to written consent of parent/guardian. Where the holder is a Minor, download and complete the standard application form evidencing parental/guardian consent.";
    private final String contentPPSNTooltip = "Your Personal Public Service Number (PPSN) can be found on your Public Services Card or on any documentation from Revenue. Your State Savings Customer Number (SSCN) can be found on correspondence you receive from us.";
    private final By fldGuestAddress1 = By.id("txtGuest_Address1");
    private final By county = By.id("ddlGuest_AddressCounty");
    private final By Date = By.id("DayOfBirth");
    private final By Month = By.id("MonthOfBirth");
    private final By Year = By.id("YearOfBirth");
    private final By DateJoint = By.id("DayOfBirth_gift2");
    private final By MonthJoint = By.id("MonthOfBirth_gift2");
    private final By YearJoint = By.id("YearOfBirth_gift2");
    private final By area = By.xpath("// label[text()='Area']/ parent::div/ select");
    private final By District = By.id("ddlGuest_AddressDublinArea");
    private final By chkTermsPB = By.xpath("//div[@class='checkbox']");
    private final By btnSubmitPBDetails = By.id("btnSubmitUser");
    private final By btnAddOrder = By.xpath("//button[@id='btnSubmitModalAdd']");
    private final By btnHolderJoint = By.xpath("//label[@for='chkJointHolderType']");
    private final By btncancel = By.id("btnClearCart");
    private final By lblcancel = By.xpath("//h1[text()='Cancel transaction']");
    private final By txtcancel = By.xpath("//div[@class='medium-12 columns']/p[contains(text(),'Are you sure you want to cancel this transaction?')]");
    private final By btnNo = By.xpath("//button[@class='gtm-cta button button--secondary js-closeModal' and text()='No']");
    private final By btnyescancel = By.xpath("//button[@class='gtm-cta button button--primary' and text()='Yes, cancel']\n");
    WebDriver driver;
    WebDriverUtil webUtil;
    String journey;
    APIReusuableLibrary apiReusuableLibrary = new APIReusuableLibrary();
    private By btnJoint;
    private final By fldGuestAddress2 = By.id("txtGuest_Address2");
    private final By ddlGuestTitle = By.id("ddlGuest_Title");


    public YourDetail(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }


    public YourDetail validateContentSoleYourDetails() {
        webUtil.gettextlog(lblApplicationType, String::equals, "Application Type", "Label");
        webUtil.gettextlog(lblSole, String::equals, "Single", "Label");
        webUtil.gettextlog(lblSurname, String::equals, "Surname", "Label");
        webUtil.gettextlog(lblDOB, String::equals, "Date of Birth", "Label");
        webUtil.clickLog(btnDOBtooltip, "Date of Birth Tooltip");
        webUtil.scrollToView(txtDOBtooltip);
        webUtil.gettextlog(txtDOBtooltip, String::equals, contentDOBTooltip, "Date Of Birth Tooltip Content");
        webUtil.scrollToView(btnPPSNtooltip);
        webUtil.gettextlog(lblPPSN, String::equals, "PPSN or SSCN", "Label");
        webUtil.clickLog(btnPPSNtooltip, "PPSNTooltip");
        webUtil.scrollToView(txtPPSNtooltip);
        webUtil.gettextlog(txtPPSNtooltip, String::equals, contentPPSNTooltip, "PPSN Tooltip Content");
        webUtil.gettextlog(lblEmail, String::equals, "Email", "Label");
        return this;
    }

    public YourDetail validateContentJointYourDetails() {
        webUtil.gettextlog(lblApplicationType, String::equals, "Application Type", "Label");
        webUtil.gettextlog(lblJoint, String::equals, "Joint", "Label");
        webUtil.gettextlog(lblJointSurname, String::equals, "Surname", "Label");
        if (!journey.contains("SignIn"))
            webUtil.gettextlog(lblDOB, String::equals, "Date of Birth", "Label");
        else
            webUtil.gettextlog(lblJointDOB, String::equals, "Date of Birth", "Label");
        webUtil.clickLog(btnJointDOB, "Date of Birth Tooltip");
        webUtil.scrollToView(txtJointDOBTooltip);
        webUtil.gettextlog(txtJointDOBTooltip, String::equals, contentDOBTooltip, "Date Of Birth Tooltip Content");
        webUtil.gettextlog(lblJointPPSN, String::equals, "PPSN or SSCN", "Label");
        webUtil.clickLog(btnJointPPSNtooltip, "PPSNTooltip");
        webUtil.scrollToView(txtJointPPSN);
        webUtil.gettextlog(txtJointPPSN, String::equals, contentPPSNTooltip, "PPSN Tooltip Content");
        webUtil.gettextlog(lblJointEmail, String::equals, "Email", "Label");
        return this;
    }

    public YourDetail selectJoint() {
        btnJoint = By.xpath("//label[@for='chkUser_JointAppType']");
        webUtil.click(btnJoint);
        return this;
    }

    public void clickCancelBtnYourDetails() {
        webUtil.click(btncancel);
        webUtil.gettextlog(lblcancel, String::equals,"Cancel transaction");
        webUtil.gettextlog(txtcancel, String::equals, "Are you sure you want to cancel this transaction?");
        webUtil.gettextlog(btnNo, String::equals, "No");
        webUtil.gettextlog(btnyescancel, String::equals, "Yes, cancel");
    }



    public YourDetail withPrimaryFirstname(String surname) {
        webUtil.sendKeys(fldFirstName, surname);
        return this;
    }

    public YourDetail withSecondaryFirstname(String surname) {
        webUtil.scrollToView(fldJointFirstName);
        webUtil.sendKeys(fldJointFirstName, surname);
        return this;
    }

    public YourDetail withPrimarySurname(String surname) {
        webUtil.sendKeys(fldPrimarySurname, surname);
        return this;
    }

    public YourDetail withSecondarySurname(String surname) {
        webUtil.sendKeys(fldSecondarySurname, surname);
        return this;
    }

    public YourDetail withPrimaryApplicantSurname(String surname) {
        webUtil.sendKeys(fldSurname, surname);
        return this;
    }

    public YourDetail withPrimaryApplicantBirthdate(String[] birthdate) {
        if (birthdate != null && birthdate.length == 3) {
            webUtil.sendKeys(fldDate, birthdate[0]);
            webUtil.sendKeys(fldMonth, birthdate[1]);
            webUtil.sendKeys(fldYear, birthdate[2]);
        } else {
            throw new IllegalArgumentException("Birthdate must contain day, month, and year");
        }
        return this;
    }

    public YourDetail withPrimaryApplicantSSCN(String sscn) {
        webUtil.scrollToView(fldPPSN);
        webUtil.sendKeys(fldPPSN, sscn);
        return this;
    }

    public YourDetail withPrimaryApplicantEmail(String email) {
        webUtil.sendKeys(fldEmail, email);
        return this;
    }

    public YourDetail withPrimaryEmail(String email) {
        webUtil.sendKeys(fldPBEmail, email);
        return this;
    }

    public YourDetail withSecondaryApplicantSurname(String surname) {
        webUtil.sendKeys(fldJointSurname, surname);
        return this;
    }

    public YourDetail withSecondaryApplicantBirthdate(String[] birthdate) {
        if (birthdate != null && birthdate.length == 3) {
            webUtil.sendKeys(fldJointDate, birthdate[0]);
            webUtil.sendKeys(fldJointMonth, birthdate[1]);
            webUtil.sendKeys(fldJointYear, birthdate[2]);
        } else {
            throw new IllegalArgumentException("Birthdate must contain day, month, and year");
        }
        return this;
    }

    public YourDetail withSecondaryApplicantSSCN(String sscn) {
        webUtil.sendKeys(fldJointPPSN, sscn);
        return this;
    }

    public YourDetail withSecondaryApplicantEmail(String mail) {
        webUtil.sendKeys(fldJointEmail, mail);
        return this;
    }
@SneakyThrows
    public YourDetail validatePrimaryApplicantDetails(String email)  {
        By txtSurname = By.id("lblUser_Surname");
        By txtBirthdate = By.id("lblUser_DateOfBirth");
        By txtSSCN = By.id("lblUser_PpsNumber");
        By txtEmail = By.id("lblUser_Email");
        Map<String, String> userMap = apiReusuableLibrary.getFormattedUserDetailsByDate(email);
        webUtil.gettextlog(txtSurname, String::equals, userMap.get("lastName"));
        webUtil.gettextlog(txtBirthdate, String::equals, userMap.get("dateOfBirth"));
        webUtil.gettextlog(txtSSCN, String::equals, userMap.get("ppsNumber"));
        webUtil.gettextlog(txtEmail, String::equals, userMap.get("emailAddress"));

        return this;
    }

    public YourDetail validatePBApplicantDetails(String email) throws FileNotFoundException {
        By txtFirstName = By.id("lblFirstnameGuest");
        By txtSurname = By.id("lblSurnameGuest");
        By txtBirthdate = By.id("lblDOBGuest");
        By txtSSCN = By.id("lblPPSNGuest");
        By txtEmail = By.id("lblEmailGuest");
        By txtAddress1 = By.id("lblAddress1Guest");
        By txtCounty = By.id("lblCountyGuest");
        Map<String, String> userMap = apiReusuableLibrary.getFormattedUserDetailsByDate(email);
        webUtil.gettextlog(txtFirstName, String::equals, userMap.get("firstName"));
        webUtil.gettextlog(txtSurname, String::equals, userMap.get("lastName"));
        webUtil.gettextlog(txtBirthdate, String::equals, userMap.get("dateOfBirth"));
        webUtil.gettextlog(txtSSCN, String::equals, userMap.get("ppsNumber"));
        webUtil.gettextlog(txtEmail, String::equals, userMap.get("emailAddress"));
        webUtil.gettextlog(txtAddress1, String::equals, userMap.get("addressLine1"));
        webUtil.gettextlog(txtCounty, String::equals, userMap.get("county"));

        return this;
    }


    public YourDetail agreeTerms() {

        webUtil.javascriptClick(chkAgreeTerms);
        return this;
    }

//    public YourDetail agreeTermsDB() {
//     System.out.println("P");
//        webUtil.waitUntilElementVisible(chkAgreeTerms, 20);
//    webUtil.scrollToView(chkAgreeTerms);
////        webUtil.click(chkAgreeTerms);
//        webUtil.javascriptClick(chkAgreeTerms);
//        return this;
//    }

//    public void AgreeTC()
//    {
//        webUtil.waitUntilElementVisible(By.xpath("(//input[@id='chkUser_Terms'])[1]"), 20);
//        webUtil.scrollToView(By.xpath("(//input[@id='chkUser_Terms'])[1]"));
//        webUtil.click(By.xpath("(//input[@id='chkUser_Terms'])[1]"));
////        webUtil.click();
//    }


    public YourDetail withAddress(String address) {
        String[] arr = address.split(",");
        webUtil.sendKeys(fldGuestAddress1, arr[0]);
        webUtil.sendKeys(fldGuestAddress2, arr[1]);
        webUtil.selectDropDown(county, select -> select.selectByVisibleText("Dublin"));
        if (webUtil.isElementVisible(area, 10))
            webUtil.selectDropDown(area, select -> select.selectByVisibleText("Dublin 6"));
        return this;
    }


    public YourDetail withAddressIrish(String address) {
        String[] arr = address.split(",");
        webUtil.sendKeys(fldGuestAddress1, arr[0]);
        webUtil.sendKeys(fldGuestAddress2, arr[1]);
        webUtil.selectDropDown(county, select -> select.selectByVisibleText("Dublin"));
        if (webUtil.isElementVisible(District, 10))
            webUtil.selectDropDown(District, select -> select.selectByVisibleText("Dublin 6"));
        return this;
    }


    //Shan added for PB as Gift
    public YourDetail withDOB(String DOB) {
        String Date1 = "20-00-1999";
        String[] arr1 = Date1.split("-");
//        String[] arr = DOB.split("-");
        webUtil.sendKeys(Date, arr1[0]);
        webUtil.sendKeys(Month, arr1[1]);
        webUtil.sendKeys(Year, arr1[2]);
//        webUtil.selectDropDown(county, select -> select.selectByVisibleText("Dublin"));
//        if (webUtil.isElementVisible(area, 10))
//            webUtil.selectDropDown(area, select -> select.selectByVisibleText("Dublin 6"));
        return this;
    }

    //Shan added for PB as Gift joint holder
    @SneakyThrows
    public YourDetail withDOBJoint(String DOB) {
        String[] arr = DOB.split("-");
        webUtil.sendKeys(DateJoint, arr[0]);
        webUtil.sendKeys(MonthJoint, arr[1]);
        webUtil.sendKeys(YearJoint, arr[2]);
        driver.findElement(MonthJoint).sendKeys("25", Keys.TAB);
        driver.findElement(YearJoint).sendKeys("2019", Keys.TAB);
        driver.findElement(MonthJoint).click();

        driver.findElement(YearJoint).click();
        String expectedErrorMessage = "Please enter a valid date of birth";
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement24 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidDOBPBGiftTwo));
        String actualErrorMessage = errorElement24.getText();
        Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Invalid 2 Holders PB as Gift");
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Invalid 2 Holders PB as Gift");


        return this;
    }




    public YourDetail agreeTermsPB() {

        webUtil.scrollToView(chkTermsPB);
        webUtil.javascriptClick(By.xpath("//input[@id='chkGuest_Terms']"));
        return this;
    }

    public YourOrder submitDetails() {
        webUtil.clickLog(btnContOrder, "Continue to order button");
        return new YourOrder(driver);
    }

   // Added concat xpath because it contains ' in the Sentence, which throws error
    public YourOrder submitDetailsIrish() {
        webUtil.clickLog(btnContOrderIrish, "Lean ar aghaidh chuig d'ordú");
        return new YourOrder(driver);
    }





    public YourOrder submitDetailsForPB() {
        webUtil.clickLog(btnSubmitPBDetails, "Continue to Prize Bond details");
        return new YourOrder(driver);
    }

    public YourOrder submitHolderDetails() {
        webUtil.click(btnAddOrder);
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal"));
        } catch (Exception ignored) {
        }
        return new YourOrder(driver);
    }


    public YourDetail enterPrimaryApplicantDetails(String surname, String[] birthdate, String SSCN, String email) {
        withPrimaryApplicantSurname(surname)
                .withPrimaryApplicantBirthdate(birthdate)
                .withPrimaryApplicantSSCN(SSCN)
                .withPrimaryApplicantEmail(email);
        return this;
    }

    public YourDetail enterSecondaryApplicantDetails(String surname, String[] birthdate, String SSCN, String email) {
        withSecondaryApplicantSurname(surname)
                .withSecondaryApplicantBirthdate(birthdate)
                .withSecondaryApplicantSSCN(SSCN)
                .withSecondaryApplicantEmail(email);
        return this;
    }

    public YourDetail enterPBApplicantDetails(String firstname, String surname, String email, String address) {

        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withPrimaryEmail(email)
                .withAddress(address)
                .agreeTermsPB()
                .submitDetailsForPB();
        return this;
    }

    public YourDetail enterPBApplicantDetailsIrish(String firstname, String surname, String email, String address) {

        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withPrimaryEmail(email)
                .withAddressIrish(address)
                .agreeTermsPB()
                .submitDetailsForPB();
        return this;
    }



    public YourDetail enterPrimaryHolderDetails(String firstname, String surname, String address) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }

        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withAddress(address);

        return this;

    }

    public YourDetail enterPrimaryHolderDetailsIrish(String firstname, String surname, String address) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }

        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withAddressIrish(address);

        return this;

    }









//Shan added. Delete it if not used
    public YourDetail enterPrimaryHolderDetailsDOB(String firstname, String surname, String DOB) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }
System.out.println("j");
        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));

        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withDOB(DOB);
        System.out.println("jf");
        return this;

    }

// For Joint
    public YourDetail enterPrimaryHolderDetailsDOBJoint(String firstname, String surname, String DOB) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }

        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withDOB(DOB);

        return this;

    }




    public YourDetail enterSecondaryHolderDetails(String firstname, String surname) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }
        By title = By.id("ddlGuest2_Title");
        webUtil.selectDropDown(title, select -> select.selectByVisibleText("Mr"));
        withSecondaryFirstname(firstname)
                .withSecondarySurname(surname);

//        try {
//            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal"));
//        }catch (Exception ignored){}
        return this;

    }

//Shan. Delete if not used
    public YourDetail enterSecondaryHolderDetails1(String firstname, String surname, String DOB) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }
        By title = By.id("ddlGuest2_Title");
        webUtil.selectDropDown(title, select -> select.selectByVisibleText("Mr"));
        withSecondaryFirstname(firstname)
                .withSecondarySurname(surname).withDOBJoint(DOB);

//        try {
//            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal"));
//        }catch (Exception ignored){}
        return this;

    }



    public YourDetail selectJointHolder(String joint) {

        if (joint.equals("Yes")) {
            webUtil.scrollToView(btnHolderJoint);
            webUtil.click(btnHolderJoint);
        }
        return this;
    }


    public Runnable enterPBHolderDetails(String holderType, PurchaseModel data) {
        Runnable pbHolderJoint = data.getProduct().indexOf("Prize Bonds as a Gift") != 0 ?
                () -> {
                    selectJointHolder(data.getPbHolderFlag());
                    enterPrimaryHolderDetails(data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getPbAddress());
                    enterSecondaryHolderDetails(data.getPbFirstName2(),
                            data.getPbSurName2());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    selectJointHolder(data.getPbHolderFlag()).
                            enterPrimaryHolderDetails(data.getPbFirstName(),
                                    data.getPbSurName(),
                                    data.getPbAddress());
                    enterSecondaryHolderDetails(data.getPbFirstName2(),
                            data.getPbSurName2());
                    submitHolderDetails();
                };


        Runnable pbHolderSole = data.getProduct().indexOf("Prize Bonds as a Gift") != 0 ?
                () -> {
                    enterPrimaryHolderDetails(data.getPbFirstName(), data.getPbSurName(), data.getPbAddress());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    enterPrimaryHolderDetails(
                            data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getPbAddress());
                    submitHolderDetails();
                };

        return holderType.equals("Yes") ? pbHolderJoint : pbHolderSole;

    }


    public Runnable enterPBHolderDetailsIrish(String holderType, PurchaseModel data) {
        Runnable pbHolderJoint = data.getProduct().indexOf("Duaisbhannaí mar Bhronntanas") != 0 ?
                () -> {
                    selectJointHolder(data.getPbHolderFlag());
                    enterPrimaryHolderDetailsIrish(data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getPbAddress());
                    enterSecondaryHolderDetails(data.getPbFirstName2(),
                            data.getPbSurName2());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    selectJointHolder(data.getPbHolderFlag()).
                            enterPrimaryHolderDetailsIrish(data.getPbFirstName(),
                                    data.getPbSurName(),
                                    data.getPbAddress());
                    enterSecondaryHolderDetails(data.getPbFirstName2(),
                            data.getPbSurName2());
                    submitHolderDetails();
                };


        Runnable pbHolderSole = data.getProduct().indexOf("Duaisbhannaí mar Bhronntanas") != 0 ?
                () -> {
                    enterPrimaryHolderDetailsIrish(data.getPbFirstName(), data.getPbSurName(), data.getPbAddress());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    enterPrimaryHolderDetailsIrish(
                            data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getPbAddress());
                    submitHolderDetails();
                };

        return holderType.equals("Yes") ? pbHolderJoint : pbHolderSole;

    }



    // Shan. Delete it later if not used
    public Runnable enterPBHolderDetailsJoint(String holderType, PurchaseModel data) {
        Runnable pbHolderJoint = data.getProduct().indexOf("Prize Bonds as a Gift") != 0 ?
                () -> {
                    selectJointHolder(data.getPbHolderFlag());
                    enterPrimaryHolderDetailsDOB(data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getFirstUserDOB());
                    enterSecondaryHolderDetails1(data.getPbFirstName2(),
                            data.getPbSurName2(),data.getFirstUserDOB());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    selectJointHolder(data.getPbHolderFlag()).
                            enterPrimaryHolderDetailsDOB(data.getPbFirstName(),
                                    data.getPbSurName(),
                                    data.getFirstUserDOB());  // Changing PBadress to DOB
                    enterSecondaryHolderDetails1(data.getPbFirstName2(),
                            data.getPbSurName2(),data.getSecondUserDOB());// Added SecondUserDOB
                    submitHolderDetails();
                };


        Runnable pbHolderSole = data.getProduct().indexOf("Prize Bonds as a Gift") != 0 ?
                () -> {
                    enterPrimaryHolderDetailsDOB(data.getPbFirstName(), data.getPbSurName(), data.getSecondUserDOB());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    enterPrimaryHolderDetailsDOB(
                            data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getSecondUserDOB());
                    submitHolderDetails();
                };

        return holderType.equals("Yes") ? pbHolderJoint : pbHolderSole;

    }












    public void yourDetailsErrorValidation(PurchaseModel purchaseModel)  {

//        webUtil.clickLog(terms_cond, "Terms & Condition on user details");
//        if (purchaseModel.getJourney().contains("Joint") && !purchaseModel.getJourney().contains("Guest"))
//            webUtil.click(By.xpath("//label[@for='chkUser_JointAppType']"));
//
//        webUtil.clickLog(submitOrder, "Continue to order button");

        if (purchaseModel.getJourney().contains("Guest")) {
            validateGuestYourDetailError();
        } else if (purchaseModel.getJourney().equalsIgnoreCase("SignIn")) {
            webUtil.gettextlog(By.id("CVchkUser_Terms"), String::equals, "You must accept the Terms & Conditions to proceed");
            if (!journey.contains("Joint")) {
                webUtil.javascriptClick(chkAgreeTerms);
                webUtil.clickLog(btnContOrder, "Continue to order button");
            }
        }
        if (purchaseModel.getJourney().contains("Joint")) {
//            webUtil.click(By.xpath("//label[@for='chkUser_JointAppType']"));
            validateJointYourDetailError();
        }
    }

    public void validateJointYourDetailError() {
        webUtil.gettextlog(By.id("RFVtxtUser_Surname2"), String::equals, "Please enter your surname");
        webUtil.gettextlog(By.id("dob-custom-error2"), String::equals, "Please enter a valid date of birth");
        webUtil.gettextlog(By.id("RFVtxtUser_PPSNumber2"), String::equals, "Please enter your PPSN or SSCN");
        webUtil.gettextlog(By.id("RFVtxtUser_Email2"), String::equals, "Please enter your email");
        webUtil.gettextlog(By.id("CVchkUser_Terms"), String::equals, "You must accept the Terms & Conditions to proceed");
    }

    public YourDetail validateGuestYourDetailError() {
        webUtil.gettextlog(By.id("RFVtxtUser_Surname"), String::equals, "Please enter your surname");
        webUtil.gettextlog(By.id("dob-custom-error"), String::equals, "Please enter a valid date of birth");
        webUtil.gettextlog(By.id("RFVtxtUser_PPSNumber"), String::equals, "Please enter your PPSN or SSCN");
        webUtil.gettextlog(By.id("RFVtxtUser_Email"), String::equals, "Please enter your email");
        webUtil.gettextlog(By.id("CVchkUser_Terms"), String::equals, "You must accept the Terms & Conditions to proceed");
        return this;
    }



    public YourDetail validateSignInYourDetailError() {  //Shan Added
//        webUtil.gettextlog(chkAgreeTerms, String::equals, "You must accept the Terms & Conditions to proceed");
        webUtil.gettextlog(By.id("CVchkUser_Terms"), String::equals, "You must accept the Terms & Conditions to proceed");
        return this;
    }
}
