package com.cucumbercraft.stepdefinitions;

import com.assertthat.selenium_shutterbug.utils.file.FileUtil;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.beust.ah.A;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.*;
import com.cucumbercraft.framework.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Log4j2
public class ProfileSettingsSteps extends MasterStepDefs {

    private final String newEmail = CredentialManager.generateNewEmail();
    WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    ProfileAndSettingsPg pfPg = new ProfileAndSettingsPg(driver);
    StateSavingsDashboardPage dashboard = new StateSavingsDashboardPage(driver);
    SliderContent sliderContent = new SliderContent(driver);
    private String newPassword;
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    SignInPg signinpage = new SignInPg(driver);

    @Then("click profile & settings on dashboard page")
    public void clickProfileSettingsOnDashboardPage() {
        Consumer<StateSavingsDashboardPage.NavigationButton> clicker = dashboard.clickNavigationButton();
        clicker.accept(StateSavingsDashboardPage.NavigationButton.PROFILE_AND_SETTINGS);
    }

    @Then("click Your Saving Module on dashboard page")  //Shan
    public void clickYourSavingPage() {
        Consumer<StateSavingsDashboardPage.NavigationButton> clicker = dashboard.clickNavigationButton();
        clicker.accept(StateSavingsDashboardPage.NavigationButton.YOUR_SAVINGS);
    }


    @SneakyThrows
    @Then("Validate Content of P&S Page") // Shan Code
    public void ValdiateContent() {
        Map<By, String> Exp = new HashMap<>();
        Exp.put(pfPg.YourPersonalDetailsHeader, "Your Personal Details");
        Exp.put(pfPg.YourFullName, "Your Full Name");
        Exp.put(pfPg.EmailAddress, "Your Email Address");
        Exp.put(pfPg.YourPassword, "Your Password");
        Exp.put(pfPg.mobnumlabel, "Your Mobile Number");
        Exp.put(pfPg.Address, "Your Address");
        Exp.put(pfPg.Secondheader, "Your Account Details");
        Exp.put(pfPg.SSCN, "State Savings Customer Number (SSCN)");
        Exp.put(pfPg.BankDetails, "Your Bank Details");

        for(Map.Entry<By, String> entry : Exp.entrySet()){
            webUtil.gettextlog(entry.getKey(), String::equalsIgnoreCase,entry.getValue());
        }
        log.info("All Headers");
        Map<String, String> Expected = new HashMap<>();
        APIReusuableLibrary api = new APIReusuableLibrary();
        Expected = api.getUserDetails(data.getUsername());

        String Name = Expected.get("name") ;
        String FullName = Name.replaceFirst("^\\S+\\s", "");// Remove the 1st name from API

        // Comparing Actual in a loc & Exp with String.(Declared in Instance)
        webUtil.gettextlog(pfPg.ActualName, String::equals,FullName,"First Name");
        webUtil.gettextlog(pfPg.ActualEmailAddress, String::equals, Expected.get("emailAddress"), "Email");
        webUtil.gettextlog(pfPg.ActualYourPassword, String::equals, pfPg.ExpPassword, "Password");
        webUtil.gettextlog(pfPg.ActualMobileNumber, String::equals, pfPg.ExpMobileNumber, "Mobile Number");
        webUtil.gettextlog(pfPg.ActualAddress, String::equals, pfPg.ExpeAddress, "Address");
        webUtil.gettextlog(pfPg.ActualSSCN, String::equals, Expected.get("sscn"), "SSCN Number");
        webUtil.gettextlog(pfPg.ActualBankDetails, String::equals, pfPg.ExpBankDetails, "Bank Details");

    }

    @And("click on change button in the {string} section")
    public void clickChangeButton(String sectionName) {
        try {
            switch (sectionName) {
                case "Your Password":
                    sliderContent = pfPg.clickChangePassword();
                    break;
                case "Your Email Address":
                    sliderContent = pfPg.clickChangeEmail();
                    break;

            }

        } catch (Exception e) {
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "failed to click on change button for " + sectionName);
            Assert.fail("failed to click on change password button");
        }
    }

    @Then("click on confirm button")
    public void clickConfirmBtnOnChangeEmailAddress() {
        pfPg.clickConfirmChangeEmail();
    }

    @Then("User click on close icon on email security code screen")
    public void ClickCloseIconOnSecurityCode() {
        try {
            pfPg.ClickEmailSecurityCodeCloseButton();


        } catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }
    }


    @Then("User click on Didn't receive the code for change email")
    public void ClickOnDidNotReceiveCodeChangeEmail() {
        try {
            pfPg.ClickDidNotReceiveLink();

        } catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }
    }

    @Then("User click on close icon at top")
    public void ClickCloseIcon () {
        try {
            pfPg.ClickEmailCloseButton();
        } catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }
    }


    @Then("click {string} button in the Your Bank Details section")
    public void clickAddNowButtonInTheSection(String arg0) {
        pfPg.clickAddNowButton();
    }

    @And("click on request an update button in the {string} section")
    public void clickRequestUpdateeButton(String sectionName) {
        try {
            sliderContent = pfPg.clickRequestUpdate(sectionName);

        } catch (Exception e) {
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "failed to click on request update button for " + sectionName + "section");
            Assert.fail("failed to click on request update button for " + sectionName + "section");
        }
    }

    @Then("User clicks on PB Buynow from Dashboard Modal")
    public void BuynowFromDashboardModal() {
        webUtil.click(pfPg.BuynowDashboard);
    }

    @Then("User clicks on PB Buynow from Dashboard")
    public void BuynowFromDashboadr() {
        webUtil.click(pfPg.BuynowDashboard);
        webUtil.click(pfPg.Buynow);
        ExtentCucumberAdapter.addTestStepLog("User clicks on Buynow from DashBoard & Buynow from the Modal");
    }


    @Then("validate {string} slider content")
    public void validateSliderContent(String sliderName) {
        try {
            switch (sliderName) {
                case "Your Bank Details-Add IBAN":
                    ExtentCucumberAdapter.addTestStepLog("Validating add IBAN slider content");
                    sliderContent.validateAddIBANSliderContent(sliderName);
                    break;

                case "Didn't get your Verification Code?":
                    ExtentCucumberAdapter.addTestStepLog("Validating Didn't Receive Verification Code");
                    sliderContent.validateVerificationCodeSliderReceive(sliderName);
                    break;

                case "Your Bank Details-Change IBAN":
                    String currentIBAN=apiDriver.getUserDetails(data.getUsername()).get("bankAccountIban").replaceAll("[^0-9]", "");
                    ExtentCucumberAdapter.addTestStepLog("Validating change IBAN slider content");
                    sliderContent.validateChangeIBANSliderContent(sliderName,currentIBAN);
                    break;
                case "Your Password-change your password":
                    ExtentCucumberAdapter.addTestStepLog("Validating change password slider content");
                    sliderContent.validatePasswordSliderContentAgainstExpected(sliderName);
                    break;
                case "Your Email Address-Change Email address":
                    ExtentCucumberAdapter.addTestStepLog("Validating change email slider content");
                    sliderContent.validateChangeEmailSlider(sliderName);
                    break;
                case "Your Full Name":
                    ExtentCucumberAdapter.addTestStepLog("Validating change name slider content");
                    sliderContent.validateChangeNameSlider(sliderName);
                    break;
                case "Your Mobile Number":
                    ExtentCucumberAdapter.addTestStepLog("Validating change mobile number slider content");
                    sliderContent.validateChangeMobileNumberSlider(sliderName);
                    break;
                case "Your Address":
                    ExtentCucumberAdapter.addTestStepLog("Validating change address slider content");
                    sliderContent.validateChangeAddressSlider(sliderName);
                    break;
                case "State Savings Customer Number (SSCN):":
                    String sscnCode = apiDriver.getUserDetails(data.getUsername()).get("sscn");
                    ExtentCucumberAdapter.addTestStepLog("Validating show SSCN code slider content");
                    sliderContent.validateShowSSCNCodeSlider(sliderName, sscnCode);
                    break;
                case "Add holding:request to add holdings":
                    ExtentCucumberAdapter.addTestStepLog("Validating add holding slider content");
                    sliderContent.validateAddHoldingSlider(sliderName);
                    break;
                case "Add holding:Thank you your holding is downloaded":
                    ExtentCucumberAdapter.addTestStepLog("Validating thank you add holding slider content");
                    sliderContent.validateThankYouAddHolding(sliderName);
                    break;
                case "Forgot Password:Reset Password":
                    ExtentCucumberAdapter.addTestStepLog("Validating reset password slider content");
                    sliderContent.validateResetPasswordSlider(sliderName);
                    break;
                case "Forgot Password:Check Email":
                    ExtentCucumberAdapter.addTestStepLog("Validating check email slider content");
                    sliderContent.validateCheckEmailSlider(sliderName);
                    break;
                case "Your Password-Verification code":
                case "Your Email Address-Verification code":
                case "Forgot Password:Verification code":
                case "Your Bank Details-Verification code":
                    ExtentCucumberAdapter.addTestStepLog("Validating verification code slider content");
                    sliderContent.validateVerificationCodeSlider(sliderName, data.getMobileNumber());
                    break;
                case "Your Email Address-Check Inbox":
                    ExtentCucumberAdapter.addTestStepLog("Validating check inbox slider content");
                    sliderContent.validateCheckInboxSlider(sliderName);
                    break;
                case "Your Password did not get Verification code":
                    ExtentCucumberAdapter.addTestStepLog("Validating Didn't get your Verification Code? slider content");
                    sliderContent.validateContentDidNotGetCodeChgPsw(sliderName);
                    break;
            }
        } catch (Exception e) {
            Assert.fail("Failed to validate " + sliderName + " content : " + e.getMessage());
        }
    }

    @Then("User click on cancel button on email security code screen")
    public void ClickCancelBtnOnSecurityCode() {
        try {
            pfPg.ClickEmailSecurityCodeCancelButton();
        } catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }
    }

    @Then("User click on close button on did not receive code screen")

    public void ClickCancelBtnonDidNotReceiveCode() {

        try {

            pfPg.ClickDidNotReceiveCloseButton();


        } catch(NoSuchElementException e){

            log.error("Element not found: " + e.getMessage());

        }

    }

    @Then("User click on cancel button on did not receive code screen")
    public void ClickCancelBtnOnDidNotReceive() {
        try {
            pfPg.ClickDidNotReceiveCancelButton();


        } catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }
    }




    @Then("enter old and new password")
    public void enterOldAndNewPassword() {
        String oldPassword = CredentialManager.getOldPassword();
        newPassword = CredentialManager.generateNewPassword();
        webUtil.sendKeys(pfPg.pwdOldPwdTxt,oldPassword);
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered old password: " + oldPassword);
        webUtil.sendKeys(pfPg.pwdChangePwdTxt, newPassword);
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered new password: " + newPassword);
    }

    @Then("enter old and new password Notification")
    public void enterOldAndNewPasswordNoti() {
        String oldPassword = CredentialManager.getOldPasswordNoti();
        newPassword = CredentialManager.generateNewPassword();
        webUtil.sendKeys(pfPg.pwdOldPwdTxt,oldPassword);
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered old password: " + oldPassword);
        webUtil.sendKeys(pfPg.pwdChangePwdTxt, newPassword);
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered new password: " + newPassword);
    }



    @Then("enter old and new password OTP")
    public void enterOldAndNewPasswordOTP() {
        String oldPassword = CredentialManager.getOldPasswordOTP();
        newPassword = CredentialManager.generateNewPassword();
        webUtil.sendKeys(pfPg.pwdOldPwdTxt, oldPassword);
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered old password: " + oldPassword);
        webUtil.sendKeys(pfPg.pwdChangePwdTxt, newPassword);
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered new password: " + newPassword);
    }




    @Then("validate all password requirements are fulfilled")
    public void validateAllPasswordRequirementsAreFulfilled() {
        List<WebElement> requirements = webUtil.getElements(By.xpath("//section[@id='sectionChangePassword']//li"));

        boolean isRequirementFulfilled = requirements.stream()
                .map(s -> s.findElement(By.tagName("span")).getAttribute("class"))
                .anyMatch(className -> className.equalsIgnoreCase("val invalid"));
        if (isRequirementFulfilled) {
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Password requirements not fulfilled");
            Assert.fail("Password requirement not fulfilled");
        } else
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "All password requirements are fulfilled");


    }

    @Then("User click on Didn't receive the code for change password")
    public void ClickOnDidNotReceiveCodeChangePassword() {
        try {
            pfPg.ClickDidNotReceivePassword();

        } catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }
    }



    @And("confirm button is enabled")
    public void emailChangeConfirmButtonIsEnabled() {

        webUtil.waitUntilElementVisible(pfPg.btnConfirmChangePassword, 20);
        webUtil.gettextlog(pfPg.btnConfirmChangePassword, String::equalsIgnoreCase, "Confirm");
        if (webUtil.objIsEnabled(pfPg.btnConfirmChangePassword))
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Confirm button is enabled");
        else
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Confirm button is not enabled");

    }

    @SneakyThrows
    @Then("click on confirm button on {string} slider")
    public void clickOnemailChangeConfirmButtonChangePassword(@NotNull String sliderName) {

        switch (sliderName) {
            case "Your Password":
                pfPg.clickConfirmChangePassword();
                if (webUtil.isElementVisible(pfPg.passwordSecurityCode, 10)) {
                    CredentialManager.saveNewPassword(newPassword);
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Verification Code Slider is displayed");
                } else {
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Error in change password");
                }
                break;
            case "Your Password Notification":
                pfPg.clickConfirmChangePassword();
                if (webUtil.isElementVisible(pfPg.passwordSecurityCode, 10)) {
                    CredentialManager.saveNewPasswordNoti(newPassword);
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Verification Code Slider is displayed");
                } else {
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Error in change password");

                }
                break;
            case "Your Password OTP":
                pfPg.clickConfirmChangePassword();
                if (webUtil.isElementVisible(pfPg.passwordSecurityCode, 10)) {
//                    CredentialManager.saveNewPasswordOTP(newPassword);
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Verification Code Slider is displayed");
                } else {
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Error in change password");

                }
                break;


            case "Verification Code":
                webUtil.click(pfPg.passwordSecurityCode);
                break;

            case "Your Email Address":
                Thread.sleep(3000);
                pfPg.clickConfirmChangeEmail();


                if (webUtil.isElementVisible(pfPg.emailSecurityCode, 10)) {
                    CredentialManager.saveNewEmail(newEmail);
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Verification Code Slider is displayed");
                    data.setUsername(newEmail);
                } else {
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Error in change password");

                }
                break;


        }

    }
//-----------------------------Negative Scenarios---------------------------------------------------

    @And("User navigates to Email address Change section on the Profile and Settings")
    public void user_navigates_to_email_address_change_section_on_the_profile_and_settings() {
        try {

            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.click(pfPg.changeEmailbutton);
        } catch (Exception e) {
            System.out.println("failed to change password ");
            Assert.fail(e.getStackTrace()[0].getMethodName());

        }
    }


    @And("new email address is {string} ensure error message displayed")
    public void user_validates_the_invalid_email_address(String newEmailType) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: " + sheetName);
            TestData getData = excelutils.getData(path, sheetName, newEmailType, TestData.class, "getTestcaseName");
            Assertions.assertThat(getData).isNotNull();
            String expectedErrorMessage = getData.getErrormessages().get(0);
            switch (newEmailType) {
                case "CE-Blank":
                    webUtil.sendKeys(pfPg.newEmailtxt, getData.getUsername());
                    webUtil.click(pfPg.emailChangeConfirmButton);
                    webUtil.gettextlog(pfPg.emptyEmail, String::equalsIgnoreCase, expectedErrorMessage);
                    break;
                case "CE-SameEmail":
                    webUtil.sendKeys(pfPg.newEmailtxt, CredentialManager.getOldEmail());
                    webUtil.click(pfPg.emailChangeConfirmButton);
                    webUtil.gettextlog(pfPg.errorEmail, String::equalsIgnoreCase, expectedErrorMessage);
                    break;
                case "CE-Invalid":
                case "CE-AlreadyRegistered":
                    webUtil.sendKeys(pfPg.newEmailtxt, getData.getUsername());
                    webUtil.click(pfPg.emailChangeConfirmButton);
                    webUtil.gettextlog(pfPg.errorEmail, String::equalsIgnoreCase, expectedErrorMessage);
                    break;

            }


        } catch (Exception e) {
            System.out.println("failed to change password ");
            Assert.fail(e.getStackTrace()[0].getMethodName());

        }

    }


    @Then("current password and new password type is {string} ensure error message displayed")
    public void user_is_validation_on_same_password(String passwordType) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: " + sheetName);
            TestData getData = excelutils.getData(path, sheetName, passwordType, TestData.class, "getTestcaseName");
            Assertions.assertThat(getData).isNotNull();
            String expectedErrorMessage = getData.getErrormessages().get(0);
            switch (passwordType) {
                case "CP-SamePassword":
                    webUtil.sendKeys(pfPg.pwdOldPwdTxt, CredentialManager.getOldPassword());
                    webUtil.sendKeys(pfPg.pwdChangePwdTxt, CredentialManager.getOldPassword());
                    webUtil.click(pfPg.btnConfirmChangePassword);
                    webUtil.waitUntilElementVisible(pfPg.errorNewPwd, 10);
                    webUtil.gettextlog(pfPg.errorNewPwd, String::equalsIgnoreCase, expectedErrorMessage);
                    break;
                case "CP-InvalidOldPassword":
                    webUtil.sendKeys(pfPg.pwdOldPwdTxt, "Dummy!2019");
                    webUtil.sendKeys(pfPg.pwdChangePwdTxt, CredentialManager.generateNewPassword());
                    webUtil.click(pfPg.btnConfirmChangePassword);
                    webUtil.waitUntilElementVisible(pfPg.errorNewPwd, 10);

                    webUtil.gettextlog(pfPg.errorNewPwd, String::equalsIgnoreCase, expectedErrorMessage);
                    break;
                case "CP-BlankOldPassword":
                    webUtil.sendKeys(pfPg.pwdOldPwdTxt, "");
                    webUtil.sendKeys(pfPg.pwdChangePwdTxt, CredentialManager.generateNewPassword());
                    webUtil.click(pfPg.btnConfirmChangePassword);
                    webUtil.waitUntilElementVisible(pfPg.errorOldPwd, 10);
                    webUtil.gettextlog(pfPg.errorOldPwd, String::equalsIgnoreCase, expectedErrorMessage);
                    break;
            }

        } catch (Exception e) {
            System.out.println("failed to change password ");
            Assert.fail(e.getStackTrace()[0].getMethodName());

        }
    }


    @And("User navigates to Password Change section on the Profile and Settings")
    public void userNavigatesToPasswordChangeSectionOnTheProfileAndSettingsTabFor() {
        try {
            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.click(pfPg.changePasswordbutton);
        } catch (Exception e) {
            System.out.println("failed to change password ");
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }
    }


    @Then("validate the success banner message is displayed for: {string}")
    public void validateTheSuccessBannerMessageIsDisplayedFor(String journey) {
        String expectedMessage = "Thank you, your %s has been updated";
        switch (journey) {
            case "Change Password":
                webUtil.gettextlog(pfPg.bannerMessage, String::equalsIgnoreCase, String.format(expectedMessage, "password"));
                log.info(webUtil.getText(pfPg.bannerMessage));
                ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.bannerMessage));
                break;
            case "Add IBAN":
                webUtil.gettextlog(pfPg.bannerMessage, String::equalsIgnoreCase, "Thank you, your Bank Account details have been added to your profile");
                log.info(webUtil.getText(pfPg.bannerMessage));
                ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.bannerMessage));
                break;

        }
    }

    @SneakyThrows
    @Then("click on notifications from submenu")
    public void clickOnNotificationsFromSubmenu() {
        Consumer<StateSavingsDashboardPage.NavigationButton> clicker = dashboard.clickNavigationButton();
        clicker.accept(StateSavingsDashboardPage.NavigationButton.NOTIFICATION);
        Thread.sleep(6000);
    }

    @SneakyThrows
    @Then("enter new email address in the field")
    public void enterNewEmailAddress() {
        driver.findElement(pfPg.newEmailtxt).clear();
        webUtil.sendKeys(pfPg.newEmailtxt, newEmail);
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered new email address: " + data.getUsername());

    }


    @SneakyThrows
    @Then("Enter Mail & Click on Close button and verify it navigates to profile & setting page New Tc")
    public void enterNewEmailAndCancel() {
        try {
            webUtil.sendKeys(pfPg.newEmailtxt, newEmail);
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Entered new email address: " + data.getUsername());
            webUtil.click(pfPg.CancelButton);  // Newly added
            Thread.sleep(2000);
            log.info("Click on Cancel button");
        } catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }
    }

        @Then("Validate Email")
        public void CancelAndValidate() {
        try {
            webUtil.gettextlog(pfPg.EmailCheck, String::equals, CredentialManager.getOldEmail(), "Check Existing Mail Id");
        } catch(NoSuchElementException e){
            log.error("Element not found: "+ e.getMessage());
        }
        }

    @And("click on show your SSCN code button in the {string} section")
    public void clickOnShowYourSSCNCodeButtonInTheSection(String arg0) {
        sliderContent = pfPg.clickShowSSCNCode();
    }
}