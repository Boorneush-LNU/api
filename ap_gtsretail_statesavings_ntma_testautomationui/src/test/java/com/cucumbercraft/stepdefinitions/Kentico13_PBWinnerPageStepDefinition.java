package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_IndividualProductPages;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PBWinnerPage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PrizeBondsPage;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kentico13_PBWinnerPageStepDefinition extends MasterStepDefs {

    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_IndividualProductPages individualProdPages = new Kentico13_IndividualProductPages(driver);
    Kentico13_PrizeBondsPage pbPage = new Kentico13_PrizeBondsPage(driver);
    Kentico13_Homepage homePage = new Kentico13_Homepage(driver);
    Kentico13_PBWinnerPage PBWinner = new Kentico13_PBWinnerPage(driver);


    @And("Validate content for Banner section on Prize Bonds Page PBWinner")
    public void validateContentForBannerSectionOnPrizeBondsPagePBWinner() {
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

    @And("Validate the content for Check My Numbers section for PB As Winner")
    public void validateTheContentForCheckMyNumbersSection() {
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

    @And("^Enter your Winning Prize Bond \"([^\"]*)\"$")
    public void EnterTheBondValue(String Value) {
        try {
            PBWinner.enterBond(Value);
            PBWinner.ClickCheckMyNumber();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Click on Search Again Button$")
    public void ClickSearch() {
        try {
            PBWinner.ClickSearch();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("Validate content for Banner section on Winner Page")
    public void validateContentForBannerSectionOnPrizeBondsPagePBWinnerPage() {
        try {

            extractExcelValue = FrameworkConstants.getExpMapPbResultPg();
            this.pageName = "PBResults";
            logReportStepValidationStart("Banner section validation Initiated");
            strCompareString(PBWinner.getBannerTitle(pageName), extractExcelValue.get("expBannerTitlePbWinner"), "Banner title");
            strCompareString(PBWinner.getSectionTitle(), extractExcelValue.get("expHighlightsSectionTitlePbWinner"), "Banner Text content");

        } catch (Exception e) {
            ExtentCucumberAdapter.getCurrentStep().fail("Banner Section Validation Failed");
            log.error("Banner Section Validation Failed : " + e.getMessage());
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }


    @And("Validate the content for Frequently asked questions in PBWinner Page")
    public void validateTheContentForFrequentlyAskedQuestionsInPbWinner() {

        extractExcelValue = FrameworkConstants.getExpMasterMapIndProdPgs().get("Prize Bonds");
        logReportStepValidationStart("Frequently Asked Questions section validation Initiated");
        strCompareString(PBWinner.getFAQHdr(), extractExcelValue.get("expFaqHdr"), "FAQ Section Title ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFaqListItem")), PBWinner.actFAQListContent(), "FAQ Section List Links content validation ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFAQsCheckClickNavigationArr")), PBWinner.getFAQLinksClickCheckNavigation(), "FAQ Section Links Navigation ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFAQClickNewTabArr")), PBWinner.getFAQLinksClickNewTabValidation(), "FAQ Section Click Links New Tab ");
        logReportStepValidationEnd("Frequently Asked Questions section validation Completed");
    }

    @And("Validate the content for Forms and Downloads section on {string} PBWinner Page")
    public void validateTheContentForFormsAndDownloadsSectionOnPagePbWinner(String pageName) {
        logReportStepValidationStart("Forms and Downloads section validation Initiated");
        strCompareString(individualProdPages.getFormsAndDownloadsHdr(), extractExcelValue.get("expFormsAndDownloadsHdr"), "Forms and Downloads Section Title ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFormsAndDownloadsListItem")), PBWinner.actFormsAndDownloadsListContent(), "Forms and Downloads Section List Links content validation ");
        getListItemsComparison(expListContent(extractExcelValue.get("getExpFormsAndDownloadsCheckClickNavigationArr")), PBWinner.getFormsAndDownloadsLinksClickCheckNavigation(), "Forms and Downloads Section Links Navigation ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFormsAndDownloadsClickNewTabArr")), PBWinner.getFormsAndDownloadsLinksClickNewTabValidation(), "Forms and Downloads Click Links New Tab ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expFAQSectionBtn")), PBWinner.getFAQSectionBtnDetails(), extractExcelValue.get("expFaqSectionBtnLbl"), pageName);
        logReportStepValidationEnd("Forms and Downloads section validation Completed");
    }


    @And("Validate the content for Buy Prize Bonds Section on {string} Page in PbWinner")
    public void validateTheContentForBuyPrizeBondsSectionOnPagePbWinner(String pageName) {

        extractExcelValue = FrameworkConstants.getExpMasterMapIndProdPgs().get("Prize Bonds");
        logReportStepValidationStart("Buy Prize Bonds section validation Initiated");
        strCompareString(PBWinner.getBuyPBHdr(), extractExcelValue.get("expBuyPBWinnerHdr"), "Buy Prize Bonds Second section Title ");
        strCompareString(homePage.getPBTileHdr(), extractExcelValue.get("expPBWinnerTileHdr"), "Buy Prize Bonds section Title ");
        strCompareString(pbPage.getPBTileSummaryText(), extractExcelValue.get("expPBTileSummaryText"), "Prize Bonds Tile summary text ");
        strCompareString(homePage.getPBGiftTileHdr(), extractExcelValue.get("expPBGiftTileHdr"), "Prize Bonds Gift Tile Title ");
        strCompareString(pbPage.getPBGiftTileSummaryText(), extractExcelValue.get("expPBGiftTileSummaryText"), "Prize Bonds Gift Tile summary text ");

        strCompareString(homePage.getPrizeBondsPBTileBuyNowBtnLbl(), extractExcelValue.get("expBuyPBTileBtnName"), "Buy now on PB Tile");
        strCompareString(homePage.getPrizeBondsPBGiftTileBuyNowBtnLbl(), extractExcelValue.get("expBuyPBGiftTileBtnName"), "Gift now on PB as a Gift Tile");

        homePage.clickPrizeBondsTileBuyGiftNowBtn("Buy now");
        strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Buy now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
        strCompareString(individualProdPages.getBannerTitle(pageName), extractExcelValue.get("expBuyPBWinnerTitle"), "Banner title");
        homePage.clickPrizeBondsTileBuyGiftNowBtn("Gift now");
        strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Gift now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Gift now modal Close button");
        strCompareString(individualProdPages.getBannerTitle(pageName), extractExcelValue.get("expBuyPBWinnerTitle"), "Banner title");
        logReportStepValidationEnd("Buy Prize Bonds section validation Completed");
    }


//    @And("Validate the content for Winning bond section on {string} Page")
//    public void validateTheContentForInterestRatesAndReturnsSectionOnPagePbWinner(String pageName) {
//        HashMap<String, String> interestRatesRetMap=FrameworkConstants.getExpMasterMapIntRatesReturns().get(pageName);
//        getListItemsComparison(WinningBondNumber(),individualProdPages.getInterestRatesTableHeaderListPbWinner(),"Table header");
//        strCompareString(PBWinner.getBuyPBHdr(), extractExcelValue.get("expBuyPBWinnerHdr"), "Buy Prize Bonds Second section Title ");
//        strCompareString(PBWinner.getDataValue(), "31 Jul 2025", "Pb Winner Date : ");
//        strCompareString(PBWinner.getAmountValue(), "€500.00", "Pb Winner Amount : ");
//        strCompareString(PBWinner.getBondValue(), "OS176151", "Pb Winner Bond : ");
//        strCompareString(PBWinner.getStatusValue(), "Awarded", "Pb Winner Status : ");
//
//        logReportStepValidationEnd("Interest Rates and Returns section validation completed");
//    }


    @Then("Validate Table contents in PrizeBond Winner Page")
    public void validateContentInSignInPanelSectionForSignInPage() {
        extractExcelValue = FrameworkConstants.getExpMapPbResultPg();
        this.pageName = "PBResults";
        HashMap<String, String> interestRatesRetMap=FrameworkConstants.getExpMasterMapIntRatesReturns().get(pageName);
        getListItemsComparison(WinningBondNumber(),individualProdPages.getInterestRatesTableHeaderListPbWinner(),"Table header");
        logReportStepValidationStart("Winning Bond numbers validation Initiated");
        strCompareString(PBWinner.getDataValue(),extractExcelValue.get("expPbWinnerDate"),"Pb Winner Date : ");
        strCompareString(PBWinner.getAmountValue(),extractExcelValue.get("expPbWinnerAmount"),"Pb Winner Amount : ");
        strCompareString(PBWinner.getBondValue(),extractExcelValue.get("expPbWinnerBond"),"Pb Winner Bond : ");
        strCompareString(PBWinner.getStatusValue(),extractExcelValue.get("expPbWinnerStatus"),"Pb Winner Status : ");
        logReportStepValidationEnd("Winning Bond numbers validation Completed");
    }




    public static List<String> WinningBondNumber(){
        List  <String> tempLoad=new ArrayList<>();
        tempLoad.add("Draw Date");
        tempLoad.add("Prize Value");
        tempLoad.add("Bond Number");
        tempLoad.add("Status");
        return tempLoad;
    }

    public static List<String> getProductInterestRatesAndReturnsValue(HashMap<String,String> masterMapIntRatesRet){
        List <String> productIntRatesReturns = new ArrayList<>();
        for (int i=1;i<=masterMapIntRatesRet.size();i++){
            productIntRatesReturns.add(masterMapIntRatesRet.get("LineItem"+i));
        }
        return productIntRatesReturns;
    }





}
