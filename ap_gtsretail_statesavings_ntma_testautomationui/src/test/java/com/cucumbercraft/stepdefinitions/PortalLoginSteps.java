package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.*;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PortalLoginSteps extends MasterStepDefs {
    public WebDriver driver = DriverManager.getWebDriver();
    Excelutils excelutils = new Excelutils();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    SignInPg signinpage = new SignInPg(driver);
    Security_Page securitycodepg = new Security_Page(driver);
    HomePage homepage = new HomePage(driver);
    Security_Page securityPage = new Security_Page(driver);
    SliderContent sliderContent = new SliderContent(driver);
    public final String url = properties.getProperty("OTPEndPoint");
    String OTPresponse;


    @Then("Click sign in button on home page")
    public void clickSignInButtonOnHomePage() {
        try {
            homepage.clickSignIn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("login to Ireland State Savings with {string} as data reference")
    public void loginToIrelandStateSavingsUsing(String testcase) {
        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = config.getSheetName();
         data = excelutils.getData(path, sheetName, testcase, TestData.class,"getTestcaseName");
        Assertions.assertThat(data).isNotNull();
        if(testcase.equals("ChangePassword")) {       // This ensures that during regression testing, when the password is changed, the previous password remains available.
            data.setPassword(CredentialManager.getOldPassword());
        } else if (testcase.equals("ChangeEmail")) {
            data.setUsername(CredentialManager.getOldEmail());
        }
        try {
            signinpage.typeInUsername(data.getUsername())
                    .typeInPassword(data.getPassword())
                    .clickBtnSignIn()
                    .typeInOTP(data.getMobileNumber())
                    .clickBtnVerifyOTP(data.getMobileNumber());
            ExtentCucumberAdapter.addTestStepLog("Login Successful");
            Thread.sleep(5000);

        } catch (Exception e) {
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }
    }

    @Then("login to Ireland State Savings with {string} as data")
    public void loginToIrelandStateSavings(String testcase) {
        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = config.getSheetName();
        data = excelutils.getData(path, sheetName, testcase, TestData.class,"getTestcaseName");
        Assertions.assertThat(data).isNotNull();
        if(testcase.equals("ChangePassword")) {
            data.setPassword(CredentialManager.getOldPassword());

        } else if (testcase.equals("ChangeEmail")) {
            data.setUsername(CredentialManager.getOldEmail());
        }
        try {
            signinpage.typeInUsername(data.getUsername())
                    .typeInPassword(data.getPassword())
                    .clickBtnSignIn();
            ExtentCucumberAdapter.addTestStepLog("Login Successful");


        } catch (Exception e) {
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }
    }


    @Then("login to Ireland State Savings with {string} as data reference OTP")
    public void loginToIrelandStateSavingsUsingthe(String testcase) {
        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = config.getSheetName();
        data = excelutils.getData(path, sheetName, testcase, TestData.class,"getTestcaseName");
        Assertions.assertThat(data).isNotNull();
        if(testcase.equals("ChangePasswordNoOTP")) {       // This ensures that during regression testing, when the password is changed, the previous password remains available.
            data.setPassword(CredentialManager.getOldPasswordOTP());
        }
        else if (testcase.equals("TC71_AlertDisabled")) {
            data.setPassword(CredentialManager.getOldPasswordNoti());
        }
        try {
            signinpage.typeInUsername(data.getUsername())
                    .typeInPassword(data.getPassword())
                    .clickBtnSignIn()
                    .typeInOTP(data.getMobileNumber())
                    .clickBtnVerifyOTP(data.getMobileNumber());
            ExtentCucumberAdapter.addTestStepLog("Login Successful");
            Thread.sleep(3000);

        } catch (Exception e) {
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }
    }



    @Then("Validate the proper rendering and placement of the email and password input fields")
    public void validateTheProperRenderingAndPlacementOfTheEmailAndPasswordInputFields() {
        signinpage.validate_Sign_in_Block();
    }



    @And("enter new password, click next to confirm input")
    public void userEntersNewPasswordClicksNext() {
        try {
            signinpage.newPwd();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("validate next button when password requirement is not met")
    public void validateNextButtonOnResetPasswordPage() {
        try {
            signinpage.validatePswdRequirementaremetAndButtonDisabled();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @SneakyThrows
    @Then("Refresh the page")
    public void pageRefresh() {
        Thread.sleep(2000);
        driver.navigate().refresh();

    }

    @And("enter new password")
    public void userEntersNewPassword() {
        try {
            signinpage.enternewpassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Click next button")
    public void ClickNext() {
        try {
            signinpage.clickNextButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("enter old password:{string}, click next to confirm input")
    public void userEntersOldPasswordClicksNext(String password) {
        try {
            signinpage.oldPwd(password);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("click {string} button in mail message body")
    public void click_on_confirm_change_of_email_address_button_mail_message_body(String button) {
        try {
            signinpage.getLinkMailinator1();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Then("User login using these credentials: username {string} password {string} mobile number {string}")
    public void userLoginUsingTheseCredentialsUsernamePasswordMobileNumber(String username, String pwd, String mobile) {
        try {
            homepage.clickSignIn();
            signinpage.typeInUsername(username)
                    .typeInPassword(pwd)
                    .clickBtnSignIn()
                    .typeInOTP(mobile)
                    .clickBtnVerifyOTP(mobile)
                    .validateWelcomeMsg();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("click the Forgot Password button to initiate the password reset process")
    public void clickTheForgotPasswordButtonToInitiateThePasswordResetProcess() {
        signinpage.clickForgotPassword();
    }
    @Then("email entered is {string} ensure error message displayed")
    public void userClickForgotPasswordButtonEntersOnSliderClickOnNextButton(String mail) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: "+sheetName);
            TestData getData = excelutils.getData(path, sheetName, mail, TestData.class,"getTestcaseName");
            Assertions.assertThat(getData).isNotNull();
            signinpage.forgotPwdErrorValidation(mail,getData);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @Then("open mailinator portal with new email")
    public void userLaunchMailnatorPortalWithThis() {
        try {
            signinpage.openMailinator(data.getUsername());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @SneakyThrows
    @And("validate email address verified message is displayed MetaData")
    public void validateEmailAdressVerifiedMessageIsDisplayedMeta() {
        webUtil.skip_switchToNewWindow(2);
        Thread.sleep(2000);
        var expected = "Thank you for verifying your email address. This is the email address you will use to sign in to Ireland State Savings Online.";
        webUtil.gettextlog(signinpage.emailVerify, String::equals, "Email address verified!");

        webUtil.gettextlog(signinpage.paraText, String::equals, expected);

    }


    @Then("click the change password button present in the email body")
    public void userClickOnChangeYourPasswordButtonWhichIsDisplayedInMailMessageBody() {
        try {
            signinpage.getLinkMailinator();

//            signinpage.captureLinksForPerformance();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("click the change password button present in the email body Perf")
    public void userClickOnChangeYourPasswordButtonWhichIsDisplayedInMailMessageBodyPerf() {

        List<String> emailsToProcess = Arrays.asList(
                "perf-test-user-01@mailinator.com",
                "perf-test-user-02@mailinator.com",
                "perf-test-user-03@mailinator.com"
                // Neter mails
        );

        try {

            Map<String, String> extractedLinks = signinpage.captureLinksForPerformance(emailsToProcess);

            System.out.println("Successfully extracted links for " + extractedLinks.size() + " emails.");

        } catch (Exception e) {
            Assert.fail("Failed to capture links for performance test: " + e.getMessage());
        }
    }





    @Then("Validate error message on sign in page for {string}")
    public void validateErrorMessageForFeilds(String str) {
        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = config.getErrorSheetName();
        System.out.println("Sheet Name: "+sheetName);
        TestData getData = excelutils.getData(path, sheetName, str, TestData.class,"getTestcaseName");
        Assertions.assertThat(getData).isNotNull();
        signinpage.feildErrorMessage(str,getData);

    }





    @And("validate email address verified message is displayed")
    public void validateEmailAdressVerifiedMessageIsDisplayed() {
        webUtil.skip_switchToNewWindow(2);
        var expected = "Thank you for verifying your email address. This is the email address you will use to sign in to Ireland State Savings Online.";
        webUtil.gettextlog(signinpage.emailVerify, String::equals, "Email address verified!");

        webUtil.gettextlog(signinpage.paraText, String::equals, expected);
        webUtil.click(By.linkText("Sign in now"));
    }


    @Then("enter valid email address and click Next")
    public void enterValidEmailAddressAndClickNext() {
        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = config.getSheetName();
//        data = excelutils.getData(path, sheetName, "ForgotPassword", TestData.class,"getTestcaseName"); //Before MS. Changed from ForgotPassword to TC47
        data = excelutils.getData(path, sheetName, "TC28_ForgotPassword", TestData.class,"getTestcaseName");
        Assertions.assertThat(data).isNotNull();
        signinpage.enterResetPasswordEmail(data.getUsername());
        sliderContent.clickPrimayBtn(webUtil.waitUntilElementVisible(signinpage.resetPasswordSlider,10));
    }

    @Then("a password reset successfully message is displayed")
    public void aPasswordResetSuccessfullyMessageIsDisplayed() {
        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = config.getSheetName();
        data = excelutils.getData(path, sheetName, "TC28_ForgotPassword", TestData.class,"getTestcaseName");
//        data = excelutils.getData(path, sheetName, "ForgotPassword", TestData.class,"getTestcaseName");
//        data = excelutils.getData(FrameworkConstants.getExcelLocationAutomationRegression(), "Regression", "ForgotPassword", TestData.class,"getTestcaseName"); // Try Shan
        webUtil.gettextlog(signinpage.notificationBox, String::equals, "Congratulations! You have successfully reset your password. Sign in below");
    }



    @SneakyThrows
    @Then("Validate Verification code Slider content")
    public void validatePara() {
        WebElement element = webUtil.waitUntilElementVisible(securityPage.verificationCodeSlider, 20);
        webUtil.gettextlog(signinpage.Verificationcode, String::equals, signinpage.ExpPara, "Paragraph");
    }

    @Then("validate {string} error message content on login")
    public void validateErrorMessageContent(String Scenario) {

        try {


            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            data = excelutils.getData(path, sheetName, Scenario, TestData.class,"getTestcaseName");
            Assertions.assertThat(data).isNotNull();
            String username= data.getUsername();
            String password=data.getPassword();
            List<String> errorMessage=data.getErrormessages();
            signinpage.enterUsernameAndIncorrectPassword(username, password);
            homepage.validateErrorMessageForLockedUser(errorMessage);


            ExtentCucumberAdapter.addTestStepLog("Validating Locked user content");


        }
        catch(Exception e){
            Assert.fail("Failed to validate " + Scenario + " content : " + e.getMessage());
        }
    }

    @Then("User click on Didn't receive the code on OTP page")
    public void clickOtpPgeDidNotReceiveLink() {

        signinpage.clickotpPgeCodeNotReceivedLink();
    }

    @Then("User click on Didn't receive the code for forgot password")
    public void clickTheForgotPassworDidNotReceiveLink() {
        signinpage.clickForgotPasswordDidNotReceiveLink();
    }


    @Then("validate {string} slider content on login")
    public void validateSliderContent(String sliderName) {
        try {
            switch (sliderName) {
                case "Forgot Password did not get Email":
                    ExtentCucumberAdapter.addTestStepLog("Validating Forgot Password did not get Email slider content");
                    sliderContent.validateContentOnDidNotGetCheckEmail(sliderName);
                    break;
                case "Forgot password did not get Verification code":
                    ExtentCucumberAdapter.addTestStepLog("Validating Forgot Password did not get Verification code slider content");
                    sliderContent.validateDidNotGetCodeSliderOnForgotPassword(sliderName);
                    break;
                case "Login OTP page did not get Verification code":
                    ExtentCucumberAdapter.addTestStepLog("Validating Login OTP page did not get Verification code slider content");
                    sliderContent.validateContentOnOtpPgeDidNotGetCode(sliderName);
                    break;
            }
        } catch (Exception e) {
            Assert.fail("Failed to validate " + sliderName + " content : " + e.getMessage());
        }
    }

    @Then("User click on Didn't receive the code on check email page")
    public void clickCheckEmailDidNotReceiveLink () {
        signinpage.clickCheckEmailCodeNotReceivedLink();
    }


    @Then("validate error message {string} is displayed")
    public void validateErrorMessageIsDisplayed(String errorMessage) {
        webUtil.gettextlog(signinpage.notificationBox,String::equals,errorMessage);
    }
}
