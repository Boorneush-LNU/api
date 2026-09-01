package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.*;
import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;


public class Kentico13_PBNotAWinnerStepDefinition  extends MasterStepDefs {

    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_IndividualProductPages individualProdPages = new Kentico13_IndividualProductPages(driver);
    Kentico13_PrizeBondsPage pbPage = new Kentico13_PrizeBondsPage(driver);
    Kentico13_Homepage homePage = new Kentico13_Homepage(driver);
//    Kentico13_PBWinnerPage PBWinner = new Kentico13_PBWinnerPage(driver);
    Kentico13_PBNotAWinner PBNotAWinner = new Kentico13_PBNotAWinner(driver);

    @And("Validate content for Banner section on Prize Bonds Page PB Not a Winner")
    public void validateContentForBannerSectionOnPrizeBondsPagePBNotWinner() {
        try {
            extractExcelValue = FrameworkConstants.getExpMasterMapIndProdPgs().get("Prize Bonds");
            logReportStepValidationStart("Banner section validation Initiated");
            strCompareString(individualProdPages.getBannerTitle(pageName), extractExcelValue.get("expBannerTitle"), "Banner title");
            strCompareString(individualProdPages.getBannerText(), extractExcelValue.get("expBannerText"), "Banner Text content");
            strCompareString(pbPage.getBannerBtn1Lbl(), extractExcelValue.get("expBannerBtnLbl"), "Banner Button1 - Buy now label ");
            strCompareString(pbPage.getBannerBtn2Lbl(), extractExcelValue.get("expBannerBtnLbl2"), "Banner Button2 - Gift now label ");
            individualProdPages.clickBuyNowBannerBtn("Buy now");
            strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Buy now modal header");
            individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
            pbPage.clickBuyNowBannerBtn2("Gift now");
            strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Gift now modal header");
            individualProdPages.clickBuyNowModalCloseBtn("Gift now modal Close button");
            logReportStepValidationEnd("Banner section validation Completed");
        } catch (Exception e) {
            ExtentCucumberAdapter.getCurrentStep().fail("Banner Section Validation Failed");
            log.error("Banner Section Validation Failed : " + e.getMessage());
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }

    @And("Validate the content for Check My Numbers section for PB Not a Winner")
    public void validateTheContentForCheckMyNumbersSectionPBNotAWinner() {
        logReportStepValidationStart("Check My Numbers section validation Initiated");
        strCompareString(pbPage.getCheckMyNumbersHdr(), extractExcelValue.get("expCheckMyNumbersHdr"), "Section title ");
        strCompareString(pbPage.getCheckMyNumSummaryText(), extractExcelValue.get("expCheckMyNumSummaryText"), "Summary text ");
        strCompareString(pbPage.getCheckMyNumFirstPBNumHdrLbl(), extractExcelValue.get("expCheckMyNumFirstPBNumHdrLbl"), "First Prize Bond Number header field label ");
        strCompareString(pbPage.getCheckMyNumLastPBNumHdrLbl(), extractExcelValue.get("expCheckMyNumLastPBNumHdrLbl"), "Last Prize Bond Number header field label ");
        strCompareString(pbPage.getCheckMyNumBtnLbl(), extractExcelValue.get("expCheckMyNumBtnLbl"), "Check my numbers button label ");
        pbPage.clickChevronHowDoIFindMyPBNum(extractExcelValue.get("expChevronHowDoIFindMyPBNumText"));
        strCompareString(pbPage.getChevronHowDoIFindMyPBNumText(), extractExcelValue.get("expChevronHowDoIFindMyPBNumText"), "Chevron text ");
        strCompareString(pbPage.getChevronExpandHdr(), extractExcelValue.get("expChevronExpandHdr"), "Chevron expanded section header text ");
        strCompareString(pbPage.getChevronExpandSummaryText(), extractExcelValue.get("expChevronExpandSummaryText"), "Chevron expanded section summary text ");
        logReportStepValidationEnd("Check My Numbers section validation Completed");
    }


    @And("^Enter your Not A Winning Prize Bond \"([^\"]*)\"$")
    public void EnterTheBondValue(String Value) {
        try {
            PBNotAWinner.enterNotABond(Value);
            PBNotAWinner.ClickCheckMyNumber();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Search Again Button Not a Winner$")
    public void ClickSearch() {
        try {
            PBNotAWinner.ClickSearch();
            ExtentCucumberAdapter.addTestStepLog("Search Again Button validated");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }




    @And("Validate content for Banner section for Not a Winner Page")
    public void validateContentForBannerSectionOnPrizeBondsPagePBNotAWinnerPage() {
        try {

            extractExcelValue = FrameworkConstants.getExpMapPbResultPg();
            this.pageName = "PBResults";
            logReportStepValidationStart("Banner section validation Initiated");
            strCompareString(PBNotAWinner.getBannerTitle(pageName), extractExcelValue.get("expBannerTitlePbNotAWinner"), "Banner title");
//            strCompareString(PBNotAWinner.getBannerSection(), extractExcelValue.get("expHighlightsSectionTitlePbNotAWinner"), "Banner Text content");
            strCompareString(PBNotAWinner.getBannerText(), extractExcelValue.get("expBannerTextPbNotAWinner"), "Banner Text content");
        } catch (Exception e) {
            ExtentCucumberAdapter.getCurrentStep().fail("Banner Section Validation Failed");
            log.error("Banner Section Validation Failed : " + e.getMessage());
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }


    @And("Validate the content for Buy Prize Bonds Section on {string} Page in PbNotAWinner")
    public void validateTheContentForBuyPrizeBondsSectionOnPagePbNotAWinner(String pageName) {

        extractExcelValue = FrameworkConstants.getExpMasterMapIndProdPgs().get("Prize Bonds");
        logReportStepValidationStart("Buy Prize Bonds section validation Initiated");
        strCompareString(PBNotAWinner.getBuyPBHdr(), extractExcelValue.get("expBuyPBWinnerHdr"), "Buy Prize Bonds Second section Title ");
        strCompareString(homePage.getPBTileHdr(), extractExcelValue.get("expPBWinnerTileHdr"), "Buy Prize Bonds section Title ");
        strCompareString(pbPage.getPBTileSummaryText(), extractExcelValue.get("expPBTileSummaryText"), "Prize Bonds Tile summary text ");
        strCompareString(homePage.getPBGiftTileHdr(), extractExcelValue.get("expPBGiftTileHdr"), "Prize Bonds Gift Tile Title ");
        strCompareString(pbPage.getPBGiftTileSummaryText(), extractExcelValue.get("expPBGiftTileSummaryText"), "Prize Bonds Gift Tile summary text ");

        strCompareString(homePage.getPrizeBondsPBTileBuyNowBtnLbl(), extractExcelValue.get("expBuyPBTileBtnName"), "Buy now on PB Tile");
        strCompareString(homePage.getPrizeBondsPBGiftTileBuyNowBtnLbl(), extractExcelValue.get("expBuyPBGiftTileBtnName"), "Gift now on PB as a Gift Tile");

        homePage.clickPrizeBondsTileBuyGiftNowBtn("Buy now");
        strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Buy now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
        strCompareString(individualProdPages.getBannerTitle(pageName), extractExcelValue.get("expBuyPBNotAWinnerTitle"), "Banner title");
        homePage.clickPrizeBondsTileBuyGiftNowBtn("Gift now");
        strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Gift now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Gift now modal Close button");
        strCompareString(individualProdPages.getBannerTitle(pageName), extractExcelValue.get("expBuyPBNotAWinnerTitle"), "Banner title");
        logReportStepValidationEnd("Buy Prize Bonds section validation Completed");
    }





}
