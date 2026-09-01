package com.cucumbercraft.stepdefinitions;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PBAdmin_Home;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.Models.PropertyConfig;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.function.BiPredicate;
@Log4j2
public class AdminPortal {
    WebDriver driver = DriverManager.getWebDriver();
    PropertyConfig config= ConfigFactory.create(PropertyConfig.class);
    PBAdmin_Home adminPortal =new PBAdmin_Home(driver);
    @Given("Launch the PB Admin portal and login with valid credentials as {string}")
    public void launchThePBAdminPortalAndLoginWithValidCredentialsAs(String pbAdminUser) throws IOException {
// this.adminUserType=pbAdminUser;
        log.info("PB Admin URL: "+ FrameworkConstants.envPBAdminLinkFetch());
// driver.get(config.getPbWebAdminUrl());
        driver.get(FrameworkConstants.envPBAdminLinkFetch());
        String userEmail=null;
        if (pbAdminUser.equalsIgnoreCase("SS Admin User")) {
            adminPortal.loginSSPBAdminUser(config.getSSAdminUser_Email(),config.getSSAdminUser_Pwd());
            userEmail=config.getSSAdminUser_Email();
        } else if (pbAdminUser.equalsIgnoreCase("PB Admin User")) {
            adminPortal.loginSSPBAdminUser(config.getPBAdminUser_Email(),config.getPBAdminUser_Pwd());
            userEmail=config.getPBAdminUser_Email();
        } else {
            log.error("Please check the SS/PB user indicator or check username and password");
        }
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(adminPortal.getHdrAdminHomePg())));

        strCompareString(adminPortal.getHdrAdminHomePg(),"State Savings Administration Home","header");
    }
    ////////////////////////to be moved to MasterStepDefinitions once the code is merged////////////////////////
    public void strCompareString(String actualResult, String expectedResult, String sectionReportLog) {
        try {
            BiPredicate<String, String> comparisonMethod=String::equalsIgnoreCase;
            boolean condition = comparisonMethod.test(actualResult, expectedResult);
            String color = condition ? "yellow" : "red";
            String resultText = condition ? "PASS" : "FAIL";
            String message = condition ?String.format("For <font color='%s'><span>%s</span></font> ,The Text: <font color='%s'><span>%s</span></font> : contains the expected text : <font color='%s'><span>%s</span></font>",color, sectionReportLog,color, actualResult, color, resultText):
                    String.format("The Text: <font color='%s'><span>%s</span></font> : does not match the expected text : <font color='%s'><span>%s</span></font> : <font color='%s'><span>%s</span></font>", color, actualResult,color,expectedResult, color, resultText);
            if (condition) {
                ExtentCucumberAdapter.getCurrentStep().pass(message);
                log.info(">>>"+sectionReportLog+">> Actual-" + actualResult + " >> Expected -" + expectedResult);
            } else {
                ExtentCucumberAdapter.getCurrentStep().fail(message);
                log.error(">>>"+sectionReportLog+">> Actual-" + actualResult + " >> Expected -" + expectedResult);
            }
        } catch (Exception ex) {
            ExtentCucumberAdapter.addTestStepLog("Exception Found </b>:Fail " + ex);
            org.testng.Assert.fail("Failed " + ex.getMessage());
        }
    }
}