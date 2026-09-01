package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.RepayReinvest.Cashin_Reinvest;
import com.cucumbercraft.POMPages.Workflow;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class WorkFlowSteps extends MasterStepDefs {
    private final WebDriver driver = DriverManager.getWebDriver();
    private final Workflow wkflow = new Workflow(driver);
    private WebDriverUtil webUtil;
    private final By Mail = By.xpath("//input[@class='form-control ltr_override input ext-input text-box ext-text-box']");
    private final By SignIn = By.id("idSIButton9");


    @Given("^Launch Workflow$")
    public void launchWorkflow() {
        wkflow.launchWrkflow();
    }

    @Then("^Validate purchase page$")
    public void validatePurchasePage() {
        try {
            wkflow.validatePurchase();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Given("User launch google")
    public void userLaunchGoogle() {
        driver.get("https://www.google.com");
        ExtentCucumberAdapter.addTestStepLog("Launched Google");


    }

    @Then("Enter some text")
    public void enterSomeText() {
        System.out.println("INFO");
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Launched Google");
    }

    @Then("enter open first link")
    public void enterOpenFirstLink() {
        System.out.println("FAIL");
        ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, "Launched Google");
    }

    @Then("Validate all links")
    public void validateAllLinks() {
        System.out.println("WARNING");
        ExtentCucumberAdapter.getCurrentStep().log(Status.WARNING, "Launched Google");
    }

    @Then("Validate footer")
    public void validateFooter() {
        System.out.println("SKIP");
        ExtentCucumberAdapter.getCurrentStep().log(Status.SKIP, "Launched Google");
    }

    @Then("Validate headers")
    public void validateHeaders() {
        driver.findElement(By.name("j"));

    }

    @And("Click administration forms select process: {string} and action: {string}")
    public void clickAdminstrationFormsSelectProcessAndActionOnPage(String process, String action) {

        wkflow.getOption(process, action);

    }

    @Then("Validate case details page is displayed for this holding id: {string}")
    public void validateCaseDetailsPageIsDisplayed(String testcase) {
        try {
//            wkflow.validate_Repayment_Search("4018039816");
            wkflow.validate_Repayment_Search(testcase);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Then("Validate the case details with the current date {string}")
    public void userOpensMaturedHoldingAndSelectsMethodType(String testcase) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getSheetName();
            data = excelutils.getData(path, sheetName, testcase, TestData.class,"getTestcaseName");
            String holdingId = data.getHoldingID();
            wkflow.validate_Repayment_Search_CurrentDate(holdingId);
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }



    @Then("VValidate case details page is displayed for data reference: {string}")
    public void validateCaseDetailsPageIsDisplayed1(String dataRef) {
        try {
            // Fetch the Holding ID from your data source using the reference key
            String holdingId = data.getHoldingID();

            // Pass the dynamically fetched ID to the workflow method
            wkflow.validate_Repayment_Search(holdingId);
        } catch (Exception e) {
            Assert.fail("Failed to validate case details for " + dataRef + ": " + e.getMessage());
        }
    }




    @Given("Launch admin portal")
    public void launchAdminPortal() {

        driver.get("https://Gulves:HotelHand2024@app-anpost-sswbepbadminportal-q-ne01.azurewebsites.net/Orders/Orders.aspx");
        try {
            Assertions.assertThat(driver.findElement(By.xpath("//h1")).getText().trim())
                    .as("Admin Portal heading")
                    .isEqualTo("State Savings Administration Home");
            ExtentCucumberAdapter.getCurrentStep().pass("Admin portal is up and running");
        } catch (AssertionError e) {
            ExtentCucumberAdapter.getCurrentStep().fail("Admin portal heading not validated");
            throw new ExceptionUtils(e.getMessage());
        }

    }
}
