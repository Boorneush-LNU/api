package com.cucumbercraft.POMPages.DigitalRegistration;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.*;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class MobileNumOTP extends MasterStepDefs {

    WebDriver driver;
    WebDriverUtil webUtil;
    TermsOfService TermsService = new TermsOfService(driver);
    APIReusuableLibrary apiUtil = new APIReusuableLibrary();
    String otpUrl = properties.getProperty("OTPEndPoint");


    public MobileNumOTP(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }


    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private final By ConfirmMobileNoTitle = By.xpath("//div[@class='medium-8 columns']/h2");
    private final By ConfirmMobileNoDesc = By.xpath("//div[@class='columns medium-10']/p");
    private final By lblCode = By.id("securityCodeTitle");
    private final By enterCode = By.id("securityCode");
    private final By confirmBtn = By.id("btnNext");


    public void ConfirmMobileOTPPage() throws Exception {
        try {
            if ((webUtil.isElementDisplayed(ConfirmMobileNoTitle)) &&
                    (webUtil.getText(ConfirmMobileNoTitle).equals("Confirm your mobile number"))) {
                log.info("Successfully landed in Confirm your mobile number Page");
            } else {
                log.info("Not landed in Confirm your mobile number Page");
            }

            String expectedConfirmMobileNoDesc = TermsService.getExpectedText("ConfirmMobileNoDesc");
            webUtil.gettextlog(ConfirmMobileNoDesc, String::equals, expectedConfirmMobileNoDesc);

            String expectedOTPLabel = TermsService.getExpectedText("VerificationCode");
            webUtil.gettextlog(lblCode, String::equals, expectedOTPLabel);

            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Confirm your mobile number Page");

        } catch ( TimeoutException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }

        }


    public void enterSecuritycode() throws Exception {
        String otp = apiUtil.getOTP(otpUrl, 200, "0".concat(REGISTRATION_DATA.getOtpNumber()));
        try {
            webUtil.sendKeys(enterCode, otp);
        } catch (NoSuchElementException e) {
            throw new ExceptionUtils("Security code not entered " +  e.getMessage());
        }
    }

    public void clickConfirmBtn()  {
        try {
            webUtil.click(confirmBtn);
            webUtil.waitForPageLoaded();
        } catch (NoSuchElementException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }







}
