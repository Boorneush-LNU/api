package com.cucumbercraft.POMPages.DigitalRegistration;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class CheckDetails extends MasterStepDefs {

    WebDriver driver;
    WebDriverUtil webUtil;
    TermsOfService TermsService = new TermsOfService(driver);


    public CheckDetails(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }


    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private final By eMail = By.id("spanEmailAddress");
    private final By phoneNumber = By.id("spanMobileNumber");
    private final By nextBtn = By.id("btnNext");
    private final By CheckDetails = By.xpath("//h2[@id='title']");
    private final By SecondFormDetails = By.xpath("//h4[@id='secondFormTitle']");


    public void ContactDetailsPage() throws Exception {
        try {

            String expectedCheckDetails = TermsService.getExpectedText("ContactDetailsCheck");
            webUtil.gettextlog(CheckDetails, String::equals, expectedCheckDetails);

            if ((webUtil.isElementDisplayed(SecondFormDetails)) &&
                    (webUtil.getText(SecondFormDetails).equals("Contact Details"))) {
                log.info("Successfully landed in Contact Details Page");
            } else {
                log.info("Not landed in Contact Details Page");
            }
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Contact Details Page");

        } catch (TimeoutException e) {
            log.error("An unexpected error occurred in Contact Details Page verification:", e);
            throw new ExceptionUtils("Failed to verify Contact Details Page: " + e.getMessage());

        }
    }



    public void verifyEmail() throws Exception {
        try {
            String screenshotPath = "";
            if (webUtil.isElementDisplayed(eMail)) {

                String displayedEmail = webUtil.getText(eMail);
                String expectedEmail = REGISTRATION_DATA.getEmailAddress();

                if (!displayedEmail.equals(expectedEmail)) {

                    screenshotPath = Util.takeScreenshotasFile(driver);
                    ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshotPath, "Email Mismatch");
                    ExtentCucumberAdapter.addTestStepLog("Expected Email: " + expectedEmail +
                            ", Found Email: " + displayedEmail);

                } else {
                    screenshotPath = Util.takeScreenshotasFile(driver);
                    ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshotPath, "Email Verified Successfully");
                    ExtentCucumberAdapter.addTestStepLog("Successfully verified E-Mail address: " + displayedEmail);
                }
            }
        } catch (NullPointerException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }


    public void verifyNumber() throws Exception {
        try {
        String screenshotPath = "";

        String expectedNumber = REGISTRATION_DATA.getPrefix().concat(REGISTRATION_DATA.getMobileNumber());

        if (webUtil.isElementDisplayed(phoneNumber)) {
            String displayedNumber = webUtil.getText(phoneNumber).replaceAll(" ", "");

            System.out.println("Displayed Number (Cleaned): " + displayedNumber);
            System.out.println("Expected Number: " + expectedNumber);

            if (!displayedNumber.contentEquals(expectedNumber)) {
                screenshotPath = Util.takeScreenshotasFile(driver);
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshotPath, "Phone Number Mismatch");
                ExtentCucumberAdapter.addTestStepLog("Expected Number: " + expectedNumber +
                        ", Found Number: " + displayedNumber);

                throw new ExceptionUtils("Provided Phone-Number not matched");

            } else {
                screenshotPath = Util.takeScreenshotasFile(driver);
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshotPath, "Phone Number Verified Successfully");
                ExtentCucumberAdapter.addTestStepLog("Successfully verified Phone Number: " + displayedNumber);
            }
        }
        } catch (NoSuchElementException | NullPointerException e) {
                throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }


    public void clickNextBtn() throws Exception {
        try {
            webUtil.gettextlog(nextBtn, String::equalsIgnoreCase, "Next");
            webUtil.click(nextBtn);
            Thread.sleep(3000);
        } catch ( NoSuchElementException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }




}
