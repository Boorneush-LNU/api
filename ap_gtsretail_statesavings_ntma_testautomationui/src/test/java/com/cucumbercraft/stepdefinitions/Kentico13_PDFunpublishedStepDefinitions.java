package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Kentico13_PDFunpublishedStepDefinitions extends MasterStepDefs{
    WebDriver driver= DriverManager.getWebDriver();
    String testCaseNumber=null;
    String linkName=null;
    WebDriverUtil webUtil = new WebDriverUtil(driver);

    @Given("Open the PDF Link for {string} as {string}")
    public void openThePDFLinkForAs(String testCaseNumber, String linkName) throws InterruptedException {
        this.testCaseNumber=testCaseNumber;
        this.linkName=linkName;
        String finalLinkName=FrameworkConstants.envLinkFetch()+linkName;
        driver.get(finalLinkName);
        driver.manage().window().maximize();
        webUtil.waitUntilElementVisible(By.id("accept-recommended-btn-handler"),4);
        driver.findElement(By.id("accept-recommended-btn-handler")).click();
        ExtentCucumberAdapter.addTestStepLog(testCaseNumber + " : " + finalLinkName+" opened on the webpage");
        String dataBannerText=null;
            webUtil.waitUntilElementVisible(By.id("ltrTitle"),4);
            dataBannerText = driver.findElement(By.id("ltrTitle")).getText();
            if (dataBannerText.equalsIgnoreCase("Help and Support")) {
                ExtentCucumberAdapter.getCurrentStep().pass("Help and Support page is displayed successfully");
            } else {
                ExtentCucumberAdapter.getCurrentStep().fail("Help and Support Page is not displayed");
            }
        }

    }



