package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.RepayReinvest.Holdings;
import com.cucumbercraft.POMPages.StateSavingsDashboardPage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.function.Consumer;


public class DashboardSteps extends MasterStepDefs {


    WebDriver driver = DriverManager.getWebDriver();
    StateSavingsDashboardPage dashBoard = new StateSavingsDashboardPage(driver);
    Holdings holdings = new Holdings(driver);
    WebDriverUtil webUtil = new WebDriverUtil(driver);


    public DashboardSteps() {


    }

    @And("^User select State Savings Product and clicks on manage button$")
    public void userClicksOnManageButton() {
        try {
//            dashBoard.clickProduct(context.regstrnData.getProduct());
            dashBoard.clickProduct(data.getProduct());
        } catch (Exception e) {
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }
    }

    @When("Verify {string} prompt message on dashboard")
    public void VerifyThePromptMessageIsOnDashboard(String prompt) {
        try {
            dashBoard.verifyAddIBANPromptMessage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("click on add your bank details now link")
    public void userClickOnAddYourBankDetailsNowLink() {

        try {
            dashBoard.addBankDetailLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("validate product displayed on dashboard")
    public void validateProductDisplayedOnDashboard() {
        try {
            dashBoard.validateProductPortfolio(data.getUsername());
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("validate product summary page")
    public void validateProductSummaryPage() {
        dashBoard.validateSummaryPage(data.getUsername(), "Savings Certificates");
    }

    @SneakyThrows
    @Then("click on {string} link")
    public void clickOnLinkOnDashboardPage(String buttonName) {

        switch (buttonName) {
            case "Download PDF Summary(dashboard)":
                dashBoard.clickDownloadPDFSummaryLink();
                Thread.sleep(2000);
                break;
            case "Download PDF Summary(summary)":
                holdings.clickDownloadPDFSummaryLink();
                Thread.sleep(2000);
                break;
            case "Request to add holdings":
                dashBoard.clickRequestToAddHoldingsLink();
                Thread.sleep(3000);
                break;
            case "Are all of your holdings included in Ireland State Savings Online?":
                dashBoard.clickRequestToAddHoldingsLink();
                Thread.sleep(3000);
                break;
            default:
                Assert.fail("Invalid button name: " + buttonName);
        }

    }


    @Then("click your savings from submenu")
    public void clickYourSavingsFromSubmenu() {
        Consumer<StateSavingsDashboardPage.NavigationButton> navigationButtonConsumer=dashBoard.clickNavigationButton();
        navigationButtonConsumer.accept(StateSavingsDashboardPage.NavigationButton.YOUR_SAVINGS);
    }

    @Then("Click on Close X button on the slider")
    public void closeSlider() {
       try {
            WebElement element = webUtil.waitUntilElementVisible(dashBoard.addHoldingSlider, 10);
            element.findElement(dashBoard.CloseX).click();
            }
         catch(NoSuchElementException e){
            log.error("Element not found: " + e.getMessage());
        }

    }
}
