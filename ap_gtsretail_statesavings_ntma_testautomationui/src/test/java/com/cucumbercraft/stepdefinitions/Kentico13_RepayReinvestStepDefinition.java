package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_RepayReinvest;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class Kentico13_RepayReinvestStepDefinition extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_RepayReinvest RepayReinvest = new Kentico13_RepayReinvest(driver);
    Kentico13_Homepage homePage = new Kentico13_Homepage(driver);


    @Given("^Launch the Ireland State Savings Online Repay Reinvest Page")
    public void LaunchtheIrelandStateSavingsOnlineHomepage() {
        ExtentCucumberAdapter.addTestStepLog("Execution on <b>" + FrameworkConstants.getEnvNameRR() + "</b> Environment");
        log.info("Execution on " + FrameworkConstants.getEnvNameRR() + " Environment");
        RepayReinvest.launchRepayReinvestpageUrl();
        ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + RepayReinvest.fetchUrlBrowser() + "\n" + RepayReinvest.getPageBrowserTitle() + "</b>");
        log.info("Application Title:->" + RepayReinvest.fetchUrlBrowser() + "\n" + RepayReinvest.getPageBrowserTitle());
        currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
        privacyPrefSelectAllowAllBtn();
    }

    public void privacyPrefSelectAllowAllBtn(){
        try {
            if (homePage.privacyPrefSectionLbl() != 0) {
                ExtentCucumberAdapter.addTestStepLog("Privacy Preferences displayed");
                log.info("Privacy Preferences displayed");
                homePage.clickPrivacyPrefAllowAllBtn();
                ExtentCucumberAdapter.addTestStepLog("Preferences permission has been enabled by clicking on Allow All");
                log.info("Preferences permission has been enabled by clicking on Allow All");
            } else {
                ExtentCucumberAdapter.addTestStepLog("<b>Privacy Preferences not displayed on time after URL launched</b>");
                log.info("Privacy Preferences not displayed on time after URL launched");
            }
        } catch (Exception ignored) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            ExtentCucumberAdapter.addTestStepLog("Preferences permission enabled already");
            log.info("Preferences permission enabled already");
        }
    }


    @Then("Validate contents in Repay Reinvest Page")
    public void validateContentInSignInPanelSectionForSignInPage() {
        extractExcelValue= FrameworkConstants.getExpMapNewCommsPg();
//        this.pageName="Newcomms-RepayReinvest";
        this.pageName="NewComms";
        logReportStepValidationStart("Repay reinvest Panel section validation Initiated");
        logReportStepValidationStart("Criteria/Highlights section validation Initiated");
        strCompareString(RepayReinvest.getRepayReinvestSectionTitle(),extractExcelValue.get("ExpRepayReinvestTitle"),"Repay Reinvest Title ");
        strCompareString(RepayReinvest.getRepayReinvestSectionText(),extractExcelValue.get("ExpRepayReinvestText"),"Highlights/Criteria Section Text ");
        strCompareString(RepayReinvest.getRepayReinvestSecHeaderTwo(),extractExcelValue.get("ExpRepayReinvestSecHeaderTwo"),"Video Section Header ");
        strCompareString(RepayReinvest.getRepayReinvestDesLinks(),extractExcelValue.get("ExpHighlightsTextRepayReinvest"),"Highlights/Criteria Section ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("ExpLinkInRepayReinvest")),RepayReinvest.getLinkDetailsRepayReinvestHighlights(),"Highlights link details", "Newcomms-RepayReinvest");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("ExpLinkInRepayReinvest_1")),RepayReinvest.getLinkDetailsRepayReinvestHighlights1(),"Highlights link details", "Newcomms-RepayReinvest");
        logReportStepValidationEnd("Repay Reinvest Panel section validation Completed");
    }



}