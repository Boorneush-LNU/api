package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.entity.OTPValidationType;
import com.cucumbercraft.framework.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.cucumbercraft.entity.OTPValidationType.*;
import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;

@Log4j2
public class Security_Page {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    public final By forgotPasswordDidNotReceiveSlider = By.id("sectionResendCode");
    private  String msgVerify = "Enter the verification code we’ve sent to the registered mobile phone number ending with ~0033";
    private final String otpErrorMsg = "Please enter your verification code.";
    private final String otpErrorMsg1 = "Verification code is invalid or has expired. Please try again.";
    private final By secHeader = By.xpath("//h2[contains(text(),'Veri')]");
    private final By verifyNumber = By.xpath("//div[@class='product-security-form']/p");
    private final By enterOTPLabel = By.xpath("//label[@class='form-label']");
    private final By enterOtp = By.id("txtPin");
    private final By didnotLink = By.xpath("//button[@class='link-blue field-link js-modalTrigger gtm-linkclick' and contains(@regstrnData-modal,'resend-security-code') ]");
    private final By cnfmButton = By.xpath("//*[text()='Confirm']");
    private final By cancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnSecuritySubmit']//preceding-sibling::button");
    private final By error = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_RFVtxtPIN']");
    private final By lessThansixDigit = By.xpath("//span[@id='CFVtxtPin']");
    private final By wrongError = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_lblResponse");
    private final By numberXpath = By.xpath("//strong[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_lblPhone']");
    //Login page
    private final By signInOTPFeild = By.id("securityCodeForm");
    private final By otpLengthError = By.id("REVSecurityCode");
    private final By otpBlankError = By.id("RFVSecurityCode");
    private final By otpExpired = By.id("verifyError"); //Shan comment
    private final By otpExpiredError = By.id("REVSecurityCode");  //Shan Added new Locator

    public By otp = signInOTPFeild;
    public By confirmbtn = By.xpath("//input[@aria-label='Confirm']");
    public By val2 = By.xpath("//form[@id='verify-form']/../h4");

    //Add IBAN
    private final By addIbanOtpFeild = By.id("securityCodeText");
    private final By addIbanOtpConfirmBtn = By.id("security-code-error");
    private final By addIbanOtpLengthError = By.id("security-code-regex-error");
    private final By addIbanOtpBlankError = By.id("security-code-required-error");
    private final By addIbanOtpExpiredError = By.id("verify-code-server-error");
    public By VerificationSlider = By.id("sectionResend");

    //Verification Code Slider
    public By verificationCodeSlider = By.cssSelector("section.active[data-name*='one-time']");
    Properties prop = Settings.getInstance();


    public Security_Page(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }

    /**
     * Verify the security page header
     */

    public void vrfySecurityPage() throws Exception {
        if (!webUtil.getText(secHeader).contentEquals("Verification Code"))
            throw new ExceptionUtils("Security page header content is changed");
        String str = webUtil.getText(verifyNumber);
        String str1 = "Please enter the verification code we've sent to the registered mobile phone number ending ~" + data.getMobileNumber().substring(data.getMobileNumber().length() - 4);
        webUtil.CompareString(str, "equalsIgnoreCase", str1);

        if (!webUtil.getText(enterOTPLabel).equals("Enter verification code"))
            throw new ExceptionUtils("Enter OTP Label not verified");
        log.info("Security page content matched");
    }


    /**
     * Enter the Otp and verify the error text
     *
     * @throws Exception
     */
    public void enterOtp() throws Exception {
        String otp = api.getOTP(prop.getProperty("OTPEndPoint"), 200, data.getMobileNumber());
        try {
            webUtil.sendKeys(enterOtp, otp);
//            ExtentCucumberAdapter.addTestStepLog("OTP is entered");
        } catch (Exception e) {
            throw new ExceptionUtils("Unable to enter OTP ".concat(e.getMessage()));
        }
    }

    /**
     * Enter the Otp and verify the error text
     *
     * @throws Exception
     */
     public void validateAddIBANOtpError(OTPValidationType validationType) throws Exception {
         var element = webUtil.waitUntilElementVisible(verificationCodeSlider,10);

         String otp = api.getOTP(prop.getProperty("OTPEndPoint"), 200, data.getMobileNumber());
        try {
            switch (validationType) {
                case BLANK:
                    element.findElement(SliderContent.TEXT_FIELD).sendKeys("");
                   element.findElement(SliderContent.PRIMARY_BUTTON).click();
                    webUtil.gettextlog(addIbanOtpBlankError, String::equals, BLANK.getErrorMessage());
                    break;
                case LESS:
                    element.findElement(SliderContent.TEXT_FIELD).sendKeys(otp.substring(2));
                    element.findElement(SliderContent.PRIMARY_BUTTON).click();
                    webUtil.gettextlog(addIbanOtpLengthError, String::equals, LESS.getErrorMessage());
                    break;
                case INVALID:
                    element.findElement(SliderContent.TEXT_FIELD).sendKeys("123456");
                    element.findElement(SliderContent.PRIMARY_BUTTON).click();
                    webUtil.gettextlog(addIbanOtpExpiredError,String::equals, INVALID.getErrorMessage());
                    break;
                case EXPIRED:
                    element.findElement(SliderContent.TEXT_FIELD).sendKeys("123456");
                    element.findElement(SliderContent.PRIMARY_BUTTON).click();
                    webUtil.gettextlog(addIbanOtpExpiredError, String::equals, EXPIRED.getErrorMessage());
                    break;
                default:
                    throw new ExceptionUtils("Invalid OTP validation type!");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * didn't link helper function
     *
     * @return
     * @throws InterruptedException
     */

    public boolean didnotLinkHelper() throws InterruptedException {
        return webUtil.isElementclickable(didnotLink, 20);
    }

    /**
     * Click on didn't link if the otp is not recived
     *
     * @throws InterruptedException
     */
    public void didnotLink() throws Exception {
        if (didnotLinkHelper()) {
            webUtil.click(didnotLink);
            webUtil.waitForPageLoaded();
            enterOtp();
        } else {
            log.info("didn't link is not clicked");
        }
    }

    /**
     * Click on confirm button of security page
     */

    public void clkCnfrmBtn() throws Exception {

        if (!webUtil.getText(cnfmButton).contains("Confirm")) {
            throw new ExceptionUtils("Confirm button on OTP page is not validated");
        }
        webUtil.click(cnfmButton);


    }

    public void clickCancelBtn() throws Exception {
        if (webUtil.isElementclickable(cancelBtn, 20)) {
            if (webUtil.getText(cancelBtn).contains("Cancel")) {
                try {
                    webUtil.click(cancelBtn);
                } catch (Exception e) {
                    throw new ExceptionUtils("OTP Page cancel button click is interrupted");
                }
            } else {
                throw new ExceptionUtils("Cancel button content is changed on OTP page");
            }
        } else {
            throw new ExceptionUtils("OTP Page cancel button x-ptah is changed");
        }
    }

    @SneakyThrows
    public void errorCheck(String scenario, TestData Data) {
        webUtil.waitFor(2000);
        boolean isDisplayed = webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("login/verify"));
        String expectedMessage = Data.getErrormessages().get(0);
        if (isDisplayed) {
            switch (scenario) {
                case "Blank Verification Code":
                    driver.findElement(signInOTPFeild).sendKeys("", Keys.ENTER);
                    webUtil.gettextlog(otpBlankError, String::equals, expectedMessage);
                    break;
                case "Expired Verification Code":  // Shan Added
                    driver.findElement(signInOTPFeild).sendKeys(Data.getOTP(), Keys.ENTER);
                    webUtil.gettextlog(otpExpired, String::equals, expectedMessage);
                    break;
                case "Wrong Verification Code":
                    driver.findElement(signInOTPFeild).sendKeys(Data.getOTP(), Keys.ENTER);
                    webUtil.gettextlog(otpExpiredError, String::equals, expectedMessage);
                    break;
                case "Less than six digits":
                    driver.findElement(signInOTPFeild).sendKeys(Data.getOTP(), Keys.ENTER);
                    webUtil.gettextlog(otpLengthError, String::equals, expectedMessage);
                    break;
                default:
                    System.out.println("No error validation on verification code page");
            }
        } else {
            ExtentCucumberAdapter.addTestStepLog("Authentication error on login page");
            log.info("Authentication error on login page");
        }
    }


}
