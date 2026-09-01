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

public class ConfirmMobileNo  extends MasterStepDefs {

    WebDriver driver;
    WebDriverUtil webUtil;
    TermsOfService TermsService = new TermsOfService(driver);


    public ConfirmMobileNo(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private final By ConfirmMobileNum = By.xpath("//div[@class='medium-8 columns']/h2");
    private final By ConfirmMobileNumDesc = By.xpath("//div[@class='columns medium-10']/p");
    private final By sendCodeBtn = By.id("btnNext");

    public void ConfirmMobileNumDetailsPage() throws Exception {
        try {

            if ((webUtil.isElementDisplayed(ConfirmMobileNum)) &&
                    (webUtil.getText(ConfirmMobileNum).equals("Confirm your mobile number"))) {
                log.info("Successfully landed in Confirm your mobile number Page");
            } else {
                log.info("Not landed in Confirm your mobile number Page");
            }

            String expectedConfirmMobileNum = TermsService.getExpectedText("ContactDetailsDesc");
            webUtil.gettextlog(ConfirmMobileNumDesc, String::equals, expectedConfirmMobileNum);

            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Confirm your mobile number Page");

        } catch (TimeoutException e) {
            log.error("An unexpected error occurred in Contact Details Page verification:", e);
            throw new ExceptionUtils("Failed to verify Contact Details Page: " + e.getMessage());

        }
    }


    public void clickSendCodeBtn() throws Exception {
        try {
            webUtil.click(sendCodeBtn);
            log.info("Send Code button Clicked on confirm Mobile number");
            Thread.sleep(3000);
        } catch (NoSuchElementException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }



}
