package com.cucumbercraft.POMPages.DigitalRegistration;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.WebReg.CheckDetailsAndContin;
import com.cucumbercraft.POMPages.WebReg.ConfirmMobileNumber;
import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.Context;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CheckList extends MasterStepDefs {

    WebDriver driver;
    WebDriverUtil webUtil;


    public CheckList(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }

    TermsOfService TermsService = new TermsOfService(driver);

    private static final Logger log = LogManager.getLogger(DriverFactory.class);


    private final By CheckList = By.xpath("//h2[@class='m39-registration-form__heading underlined-heading']");
    private final By RequiredList = By.xpath("//p[@class='m39-registration-form__text']");
    private final By CheckList1 = By.xpath("//ul[@class='check-list']/li[1]");
    private final By CheckList2 = By.xpath("//ul[@class='check-list']/li[2]");
    private final By CheckList3 = By.xpath("//ul[@class='check-list']/li[3]");
    private final By CheckList4 = By.xpath("//div[@class='medium-10 columns']//p[1]");
    private final By PleaseNote = By.xpath("//div[@class='medium-10 columns']//p[2]");
    private final By nextBtn = By.id("btnNext");

    public void CheckList() throws Exception {
        try {

            if ((webUtil.isElementDisplayed(CheckList)) && (webUtil.isElementDisplayed(RequiredList)) &&
                    (webUtil.getText(CheckList).equals("Before you continue"))) {
                log.info("Successfully landed in Checklist before you continue Page");
            } else {
                log.info("Not landed in Checklist before you continue Page");
            }
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Checklist before you continue Page");

            String expectedChecklist1 = TermsService.getExpectedText("Checklist_1");
            String expectedChecklist2 = TermsService.getExpectedText("Checklist_2");
            String expectedChecklist3 = TermsService.getExpectedText("Checklist_3");
            String expectedChecklist4 = TermsService.getExpectedText("YourSelfie");
            String expectedChecklist5 = TermsService.getExpectedText("PleaseNote");


            webUtil.gettextlog(CheckList1, String::equals, expectedChecklist1);
            webUtil.gettextlog(CheckList2, String::equals, expectedChecklist2);
            webUtil.gettextlog(CheckList3, String::equals, expectedChecklist3);
            webUtil.gettextlog(CheckList4, String::equals, expectedChecklist4);
            webUtil.gettextlog(PleaseNote, String::equals, expectedChecklist5);

        } catch (TimeoutException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());


        }
    }


    public void clickNextBtn() throws Exception {
        try {
        webUtil.gettextlog(nextBtn, String::equalsIgnoreCase, "Next");
        webUtil.click(nextBtn);
    } catch ( NoSuchElementException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }


}
