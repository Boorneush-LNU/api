package com.cucumbercraft.POMPages.DigitalRegistration;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class ScanQRCode extends MasterStepDefs {


    WebDriver driver;
    WebDriverUtil webUtil;
    TermsOfService TermsService = new TermsOfService(driver);


    public ScanQRCode(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }


    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    private final By ContInPhone = By.xpath("//div[@class='medium-8 columns']/h2");
    private final By MobileSteps = By.xpath("//div[@class='medium-8 columns']/p");
    private final By StepsOne = By.xpath("//ol[@class='steps-list']//li[1]//div[1]//p[1]");
    private final By StepsTwo = By.xpath("//ol[@class='steps-list']//li[2]//div[1]//p[1]");
    private final By ScanQR = By.id("btnNext");
//    private final By ScanQRMobile = By.xpath("//div[@id='container']/h1");
//    private final By ScanQRMobileDesc = By.xpath("//div[@id='container']/h2");




    public void ContinueMobilePage() throws Exception {
        try {

            if ((webUtil.isElementDisplayed(ContInPhone)) &&
                    (webUtil.getText(ContInPhone).equals("Continue on your smartphone"))) {
                log.info("Successfully landed in Continue on your smartphone Page");
            } else {
                log.info("Not landed in Continue on your smartphone Page");
            }

            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Continue on your smartphone Page");

            String expectedMobileSteps = TermsService.getExpectedText("MobileSteps");
            webUtil.gettextlog(MobileSteps, String::equals, expectedMobileSteps);

        } catch (TimeoutException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }


    public void ContinueMobileSteps() throws Exception {
        try {

            String expectedMobileScanQR = TermsService.getExpectedText("ScanQRStep");
            webUtil.gettextlog(StepsOne, String::equals, expectedMobileScanQR);

            String expectedCompleteVerification = TermsService.getExpectedText("VerificationStep");
            webUtil.gettextlog(StepsTwo, String::equals, expectedCompleteVerification);

            webUtil.click(ScanQR);
            log.info("Scan QR button Clicked on Continue on your smartphone Page");
            Thread.sleep(5000);

            log.info("QR code is displayed");

        } catch (NoSuchElementException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }






    }