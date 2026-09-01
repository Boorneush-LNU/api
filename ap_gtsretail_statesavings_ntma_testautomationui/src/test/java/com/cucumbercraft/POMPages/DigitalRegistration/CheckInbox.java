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

public class CheckInbox extends MasterStepDefs {

    WebDriver driver;
    WebDriverUtil webUtil;
    TermsOfService TermsService = new TermsOfService(driver);


    public CheckInbox(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }


    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private final By header = By.id("title");
    private final By LinkSend = By.xpath("//div[@class='medium-8 columns']/p[1]");
    private final By LinkSendDesc = By.xpath("//div[@class='medium-8 columns']/p[2]");
    private final By eMail = By.id("spanEmailAddress");

    public void InboxPage() throws Exception {
        try {

            if ((webUtil.isElementDisplayed(header)) &&
                    (webUtil.getText(header).equals("Check your inbox"))) {
                log.info("Successfully landed in Check your inbox Page");
            } else {
                log.info("Not landed in Check your inbox Page");
            }

            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Check your inbox Page");

        } catch (TimeoutException e) {
            log.error("An unexpected error occurred in Contact Details Page verification:", e);
            throw new ExceptionUtils("Failed to verify Contact Details Page: " + e.getMessage());
        }
        }


    public void UserInbox() throws Exception {
    try {
    String expectedCheckLinkSend = TermsService.getExpectedText("LinkSend");
    webUtil.gettextlog(LinkSend, String::equals, expectedCheckLinkSend);

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

    String expectedCheckLinkSendDesc = TermsService.getExpectedText("LinkSendDesc");
    webUtil.gettextlog(LinkSendDesc, String::equals, expectedCheckLinkSendDesc);

    }
        catch (NoSuchElementException | TimeoutException e) {
        throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
    }
}
}



