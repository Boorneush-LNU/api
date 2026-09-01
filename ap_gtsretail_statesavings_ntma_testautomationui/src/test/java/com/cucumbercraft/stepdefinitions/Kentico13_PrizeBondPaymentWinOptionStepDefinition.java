package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PrizeBondWinningPaymentOption;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class Kentico13_PrizeBondPaymentWinOptionStepDefinition  extends  MasterStepDefs{

    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_PrizeBondWinningPaymentOption PBWinningPayment = new Kentico13_PrizeBondWinningPaymentOption(driver);
    Kentico13_Homepage homePage = new Kentico13_Homepage(driver);


    @Given("^Launch the Ireland State Savings Online PB Winning Payment Option Page")
    public void LaunchtheIrelandStateSavingsOnlineHomepage() {
        ExtentCucumberAdapter.addTestStepLog("Execution on <b>" + FrameworkConstants.getEnvNamePB() + "</b> Environment");
        log.info("Execution on " + FrameworkConstants.getEnvNamePB() + " Environment");
        PBWinningPayment.launchPBpageUrl();
        ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + PBWinningPayment.fetchUrlBrowser() + "\n" + PBWinningPayment.getPageBrowserTitle() + "</b>");
        log.info("Application Title:->" + PBWinningPayment.fetchUrlBrowser() + "\n" + PBWinningPayment.getPageBrowserTitle());
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

    @Then("Validate contents in PrizeBond payment winning Option Page")
    public void validateContentInSignInPanelSectionForSignInPage() {
        extractExcelValue= FrameworkConstants.getExpMapNewCommsPg();
        this.pageName="NewComms";
        logReportStepValidationStart("PB payment winning option Panel section validation Initiated");
        logReportStepValidationStart("Criteria/Highlights section validation Initiated");
        strCompareString(PBWinningPayment.getPBPrizeWinningOptionSectionTitle(),extractExcelValue.get("ExpPBPrizeWinningOptionTitle"),"PrizeBond payment winning Option Title ");
        strCompareString(PBWinningPayment.getPBPrizeWinningOptionSectionText(),extractExcelValue.get("ExpPBPrizeWinningOptionText"),"Highlights/Criteria Section Text ");
        strCompareString(PBWinningPayment.getPBPrizeWinningOptionDesLinks(),extractExcelValue.get("ExpLinkDetailsStateSavingsPBHighlights"),"Highlights/Criteria Section ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("ExpLinkInPB")),PBWinningPayment.getLinkDetailsPBHighlights(),"Highlights link details", "Newcomms-PB payment winning option");
        logReportStepValidationEnd("PB payment winning option Panel section validation Completed");
    }



}
