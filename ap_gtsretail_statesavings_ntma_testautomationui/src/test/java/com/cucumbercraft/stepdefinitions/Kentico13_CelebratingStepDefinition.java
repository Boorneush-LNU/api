package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Celebrating100Y;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_RepayReinvest;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class Kentico13_CelebratingStepDefinition extends  MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_Celebrating100Y Celebrate = new Kentico13_Celebrating100Y(driver);
    Kentico13_Homepage homePage = new Kentico13_Homepage(driver);


    @Given("^Launch the Ireland State Savings Online Celebrating 100 Years Page")
    public void LaunchtheIrelandStateSavingsOnlineHomepage() {
        ExtentCucumberAdapter.addTestStepLog("Execution on <b>" + FrameworkConstants.getEnvNameCelebrate() + "</b> Environment");
        log.info("Execution on " + FrameworkConstants.getEnvNameCelebrate() + " Environment");
        Celebrate.launchCelebating100YpageUrl();
        ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + Celebrate.fetchUrlBrowser() + "\n" + Celebrate.getPageBrowserTitle() + "</b>");
        log.info("Application Title:->" + Celebrate.fetchUrlBrowser() + "\n" + Celebrate.getPageBrowserTitle());
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

    @Then("Validate contents in Celebrating 100 Years Page")
    public void validateContentInSignInPanelSectionForSignInPage() {
        extractExcelValue= FrameworkConstants.getExpMapNewCommsPg();
        this.pageName="NewComms";
        logReportStepValidationStart("Celebrate 100Y ears Panel section validation Initiated");
        logReportStepValidationStart("Criteria/Highlights section validation Initiated");
        strCompareString(Celebrate.getCelebrateSectionTitle(),extractExcelValue.get("ExpCelebrate100YTitle"),"Celebrate100Years Title ");
        strCompareString(Celebrate.getCelebrateSectionText(),extractExcelValue.get("ExpCelebrate100YText"),"Highlights/Criteria Section Text ");
        strCompareString(Celebrate.getCelebrateSecHeaderTwo(),extractExcelValue.get("ExpCelebrate100YSecHeaderTwo"),"Video Section Header ");
        strCompareString(Celebrate.getCelebrateDesLinks(),extractExcelValue.get("ExpLinkDetailsStateSavingsRegHighlights"),"Highlights/Criteria Section ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("ExpLinkInCelebrate")),Celebrate.getLinkDetailsStateSavingsCelebrateHighlights(),"Highlights link details", "Newcomms-RepayReinvest");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("ExpLinkInCelebrate_1")),Celebrate.getLinkDetailsStateSavingsCelebrateHighlights1(),"Highlights link details", "Newcomms-RepayReinvest");
        logReportStepValidationEnd("Celebrating 100 years Panel section validation Completed");
    }




}
