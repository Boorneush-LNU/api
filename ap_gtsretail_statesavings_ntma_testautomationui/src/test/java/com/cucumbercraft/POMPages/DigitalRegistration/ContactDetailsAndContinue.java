package com.cucumbercraft.POMPages.DigitalRegistration;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.WebReg.CheckDetailsAndContin;
import com.cucumbercraft.POMPages.WebReg.WeMatchDetails;
import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;

public class ContactDetailsAndContinue extends MasterStepDefs  {


    WebDriver driver;
    WebDriverUtil webUtil;
    WeMatchDetails matchDetials = new WeMatchDetails(driver);
    CheckDetailsAndContin checkDetails = new CheckDetailsAndContin(driver);
    TermsOfService TermsService = new TermsOfService(driver);


    public ContactDetailsAndContinue(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private final By emailAddress = By.id("txtEmailAddress");
    private final By mobileNumberEnter = By.id("txtPhone");
    private final By ContactDetailsHeader = By.xpath("//div[@id='pnl_contactDetail']/h4");
private final By ContactDetails = By.xpath("//div[@id='pnl_contactDetail']/p");
    private final By MobileReg = By.xpath("//strong[@id='notificationAlertMessage']");
    private final By nextBtn = By.id("btnNext");
    private final By lblEmail = By.id("lblEmail");
    private final By lblPrefix = By.id("lblPrefix");
    private final By lblMobile = By.id("lblPhone");
    private final By checkConsent = By.xpath("//label[@for='terms']");



    public void enterEmailAddressDig() throws InterruptedException {
        try {
            String email = REGISTRATION_DATA.getEmailAddress();
            if (email.contains("null")) {
                return;
            } else {
                webUtil.sendKeys(emailAddress, email);
            }
            Thread.sleep(2000);
        } catch (NullPointerException e) {
            throw new ExceptionUtils("email is not entered." + e.getMessage());
        }
    }

    public void enterNumberDig() {
        try {
            String phone = REGISTRATION_DATA.getMobileNumber();
            if (phone.contains("null")) return;
            else webUtil.sendKeys(mobileNumberEnter, phone);
        } catch (NullPointerException e) {
            throw new ExceptionUtils("email is not entered." + e.getMessage());
        }
    }

    public void enterAllDetailsForDigitalUser() {
        try {
            enterEmailAddressDig();
            enterNumberDig();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    public void ContactDetailsPage() throws Exception {
        try {

            if ((webUtil.isElementDisplayed(ContactDetailsHeader)) && (webUtil.isElementDisplayed(ContactDetails)) &&
                    (webUtil.getText(ContactDetailsHeader).equals("Contact Details"))) {
                log.info("Successfully landed in Contact Details Page");
            } else {
                log.info("Not landed in Contact Details Page");
            }
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Contact Details Page");

            String expectedContactDetails = TermsService.getExpectedText("ContactDetails");
            String expectedEmailLabel = TermsService.getExpectedText("EmailLabel");
            String expectedPrefixLabel = TermsService.getExpectedText("PrefixLabel");
            String expectedMobileLabel = TermsService.getExpectedText("MobileNumberLabel");
            webUtil.gettextlog(ContactDetails, String::equals, expectedContactDetails);
            webUtil.gettextlog(lblEmail, String::equals, expectedEmailLabel);
            webUtil.gettextlog(lblPrefix, String::equals, expectedPrefixLabel);
            webUtil.gettextlog(lblMobile, String::equals, expectedMobileLabel);

        } catch (TimeoutException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());

        }
    }

    public void MobileReg() throws Exception {
        try {
            String expectedMobileReg = TermsService.getExpectedText("NeedMobilePhone");
            webUtil.gettextlog(MobileReg, String::equals, expectedMobileReg);
        } catch (NoSuchElementException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }

    public void checkConsent() throws InterruptedException {

        if (webUtil.isElementDisplayed(checkConsent)) {
            try {
                WebElement element = webUtil.waitUntilElementLocated(checkConsent, 5);
                String expectedConsent = TermsService.getExpectedText("Consent");
                webUtil.gettextlog(checkConsent, String::equals, expectedConsent);
                webUtil.click(checkConsent);
            } catch (NoSuchElementException e) {
                throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
            }
        } else {
            throw new ExceptionUtils("Consent checkbox xpath may have changed");
        }
    }

    public void clickNextBtn() throws Exception {
        try {
            webUtil.gettextlog(nextBtn, String::equalsIgnoreCase, "Next");
            webUtil.click(nextBtn);
            Thread.sleep(2000);
        } catch ( NoSuchElementException e) {
                throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
            }

    }

}
