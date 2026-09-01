package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_IndividualProductPages;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PrizeBondsPage;
import io.cucumber.java.en.And;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class Kentico13_IndividualProductPagesStepDefinitions extends MasterStepDefs{
    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_IndividualProductPages individualProdPages =new Kentico13_IndividualProductPages(driver);
    Kentico13_Homepage homePage=new Kentico13_Homepage(driver);
    Kentico13_PrizeBondsPage pbPage=new Kentico13_PrizeBondsPage(driver);


    @And("Validate content for Banner section on {string} Page")
    public void validateContentForBannerSectionOnPage(String pageName) {
        try {
            extractExcelValue= FrameworkConstants.getExpMasterMapIndProdPgs().get(pageName);
            this.pageName=pageName;
            logReportStepValidationStart("Banner section validation Initiated");
            if(!(pageName.equalsIgnoreCase("Deposit Account"))) {
                strCompareString(individualProdPages.getBannerYearIssueNumber(), extractExcelValue.get("expBannerYearIssueNumber"), "Banner text-Year and Issue Number");
            }
            strCompareString(individualProdPages.getBannerTitle(pageName),  extractExcelValue.get("expBannerTitle"),  "Banner title");
            strCompareString(individualProdPages.getBannerText(),  extractExcelValue.get("expBannerText"), "Banner Text content");
            if(pageName.equalsIgnoreCase("Instalment Savings") || pageName.equalsIgnoreCase("Childcare Plus") || pageName.equalsIgnoreCase("Deposit Account")){
                getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBannerBtnLbl")),individualProdPages.getExpBannerBtnClickList(),extractExcelValue.get("expBannerBtnLbl").split(";")[0],pageName);
            }
            else{
                strCompareString(individualProdPages.getBannerBtnLbl(), extractExcelValue.get("expBannerBtnLbl"), "Banner Button Label");
                individualProdPages.clickBuyNowBannerBtn("Buy Now");
                strCompareString(individualProdPages.getBuyNowModalTitle(),extractExcelValue.get("expBuyNowModalHeader"), "Buy Now modal header");
                individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
            }
            getListItemsComparison(expListContent(extractExcelValue.get("expBannerStatsListItem")), individualProdPages.actStatsListContent(),"Banner Stats ");
            logReportStepValidationEnd("Banner section validation Completed");
        }
        catch(Exception e){
            ExtentCucumberAdapter.getCurrentStep().fail("Banner Section Validation Failed");
            log.error("Banner Section Validation Failed");
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }

    @And("Validate the content for Highlights section on {string} Page")
    public void validateTheContentForHighlightsSectionOnPage(String pageName) {
        logReportStepValidationStart("Highlights Bullet Points section validation Initiated");
        if(pageName.equalsIgnoreCase("Prize Bonds")){
            strCompareString(pbPage.getHighlightsSectionTitle(),extractExcelValue.get("expHighlightsSectionTitle"),"Highlights Section Title ");
        }else{
            strCompareString(individualProdPages.getHighlightsSectionTitle(),extractExcelValue.get("expHighlightsSectionTitle"),"Highlights Section Title ");
        }
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsLeftBulletPtArr")), individualProdPages.actHighlightsLeftListContent(),"Highlights Bullet Section Left Side ");
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsRightBulletPtArr")), individualProdPages.actHighlightsRightListContent(),"Highlights Bullet Section Right Side ");
        ExtentCucumberAdapter.addTestStepLog("Highlights Bullet Points section validation Completed");
        log.info("Highlights Bullet Points section validation Completed");

        log.info("Highlights Bullet Points section Links and button validation initiated");
        ExtentCucumberAdapter.addTestStepLog("Highlights Bullet Points section Links and button validation initiated");
        if(!(pageName.equalsIgnoreCase("Prize Bonds"))){
            getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expFindOutMoreOrCustApplFormBtn")), individualProdPages.getFindOutMoreBtnDetails(),extractExcelValue.get("expFindOutMoreOrCustApplFormBtn").split(";")[0],pageName);
        }

        if(!(pageName.equalsIgnoreCase("Deposit Account"))){
            getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsCheckClickNavigationArr")), individualProdPages.getHighlightsLinksClickCheckNavigation(),"Highlights Bullet Section Links Navigation ");
            getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsClickNewTabArr")), individualProdPages.getHighlightsLinksClickNewTabValidation(),"Highlights Bullet Section Click Links New Tab ");
        }

        ExtentCucumberAdapter.addTestStepLog("Highlights Bullet Points section Links and button validation completed");
        log.info("Highlights Bullet Points section Links and button validation completed");
        logReportStepValidationEnd("Highlights Bullet Points section validation Completed");
    }

    @And("Validate the content for Secondary Banner section")
    public void validateTheContentForSecondaryBannerSection() {
        logReportStepValidationStart("Secondary Banner section validation Initiated");
        strCompareString(individualProdPages.getSecondaryBannerTitle(),extractExcelValue.get("expBannerTitle2"),"Secondary Banner Section Title ");
        if(pageName.equalsIgnoreCase("Instalment Savings") || pageName.equalsIgnoreCase("Childcare Plus") || pageName.equalsIgnoreCase("Deposit Account")){
            getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBannerBtnLbl2")),individualProdPages.getExpBanner2BtnClickList(),extractExcelValue.get("expBannerBtnLbl2").split(";")[0],pageName);
        }
        else{
            strCompareString(individualProdPages.getSecondaryBannerBtnLbl(),extractExcelValue.get( "expBannerBtnLbl2"), "Secondary Banner Button Label ");
            ExtentCucumberAdapter.addTestStepLog("Secondary Banner section button validation initiated");
            log.info("Secondary Banner section button validation initiated");
            individualProdPages.clickSecondaryBannerBuyNowBtn("Secondary Banner Button -Buy Now");
            strCompareString(individualProdPages.getBuyNowModalTitle(),extractExcelValue.get("expBuyNowModalHeader"), "Buy Now modal header");
            individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
            ExtentCucumberAdapter.addTestStepLog("Secondary Banner section button validation completed");
            log.info("Secondary Banner section button validation completed");
        }

        ExtentCucumberAdapter.addTestStepLog("Secondary Banner section validation Completed");
        log.info("Secondary Banner section validation Completed");
        logReportStepValidationEnd("Secondary Banner section validation Completed");
    }


    @And("Validate the content for Frequently asked questions")
    public void validateTheContentForFrequentlyAskedQuestions() {
        logReportStepValidationStart("Frequently Asked Questions section validation Initiated");
        strCompareString(individualProdPages.getFAQHdr(),extractExcelValue.get("expFaqHdr"),"FAQ Section Title ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFaqListItem")), individualProdPages.actFAQListContent(),"FAQ Section List Links content validation ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFAQsCheckClickNavigationArr")), individualProdPages.getFAQLinksClickCheckNavigation(),"FAQ Section Links Navigation ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFAQClickNewTabArr")), individualProdPages.getFAQLinksClickNewTabValidation(),"FAQ Section Click Links New Tab ");
        logReportStepValidationEnd("Frequently Asked Questions section validation Completed");
    }

    @And("Validate the content for Forms and Downloads section on {string} Page")
    public void validateTheContentForFormsAndDownloadsSectionOnPage(String pageName) {
        logReportStepValidationStart("Forms and Downloads section validation Initiated");
        strCompareString(individualProdPages.getFormsAndDownloadsHdr(),extractExcelValue.get("expFormsAndDownloadsHdr"),"Forms and Downloads Section Title ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFormsAndDownloadsListItem")), individualProdPages.actFormsAndDownloadsListContent(),"Forms and Downloads Section List Links content validation ");
        getListItemsComparison(expListContent(extractExcelValue.get("getExpFormsAndDownloadsCheckClickNavigationArr")), individualProdPages.getFormsAndDownloadsLinksClickCheckNavigation(),"Forms and Downloads Section Links Navigation ");
        getListItemsComparison(expListContent(extractExcelValue.get("expFormsAndDownloadsClickNewTabArr")), individualProdPages.getFormsAndDownloadsLinksClickNewTabValidation(),"Forms and Downloads Click Links New Tab ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expFAQSectionBtn")), individualProdPages.getFAQSectionBtnDetails(),extractExcelValue.get("expFaqSectionBtnLbl"),pageName);
        logReportStepValidationEnd("Forms and Downloads section validation Completed");
    }

    @And("Validate the Carousel section on {string} Page")
    public void validateTheCarouselSectionOnPage(String pageName) {
        logReportStepValidationStart("Carousel section validation Initiated");
        extractExcelValue=FrameworkConstants.getExpMasterMapIndProdPgs().get(pageName);
        strCompareString(individualProdPages.getCarouselHdr(),extractExcelValue.get("expCarouselHdr"),"Carousel Section Title ");
        List<String> getCarouselOrderProduct=homePage.getCarouselOrderPg();
        HashMap<String, HashMap<String,String>> getCarouselTileDetailsMap=homePage.getCarouselTileDetails(getCarouselOrderProduct);
        HashMap<String, HashMap<String,String>> expCarouselTileDetailsMap=Kentico13_Homepage.getExpCarouselTileDetails();
        carouselSectionContentValidation(expListContent(homePage.getExpCarouselHeaderTileName(pageName)),getCarouselOrderProduct,getCarouselTileDetailsMap,expCarouselTileDetailsMap,pageName);
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBtnClickAndNewTabViewAllProdArr")), individualProdPages.getCarouselViewProdBtnDetails(),extractExcelValue.get("expBtnClickAndNewTabViewAllProdArr").split(";")[0],pageName);
        logReportStepValidationEnd("Carousel section validation Completed");
    }

    @And("Validate the Savings Calculator Icon presence on {string} page")
    public void validateTheSavingsCalculatorIconPresenceOnPage(String pageName) {
        logReportStepValidationStart("Savings Calculator section validation Initiated");
        strCompareString(individualProdPages.getSavingsCalculatorIconLblPage(),"Savings Calculator","Savings Calculator Icon ");
        strCompareString(individualProdPages.clickSavingsCalculatorIcon(),"Savings Calculator","Savings Calculator Page Header ");
        strCompareString(individualProdPages.clickSavingsCalculatorCloseBtn(),pageName,"Close Button on Savings Calculator Clicked");
        logReportStepValidationEnd("Savings Calculator section validation Completed");
    }

    @And("Validate the content for Interest Rates and Returns section on {string} Page")
    public void validateTheContentForInterestRatesAndReturnsSectionOnPage(String pageName) {
        logReportStepValidationStart("Interest Rates and Returns section validation Initiated");
        strCompareString(individualProdPages.getTitleInterestRatesAndReturnsSection(),extractExcelValue.get("expIntRatesReturnsTitle"),"Section title");
        strCompareString(individualProdPages.getTSummaryTextInterestRatesAndReturnsSection(),extractExcelValue.get("expIntRatesReturnsText"),"Section Summary text");
        individualProdPages.clickChevronShowYearlyReturns();
        HashMap<String, String> interestRatesRetMap=FrameworkConstants.getExpMasterMapIntRatesReturns().get(pageName);
        getListItemsComparison(interestRatesTableHdr(),individualProdPages.getInterestRatesTableHeaderList(),"Table header");
        getListItemsComparison(getProductInterestRatesAndReturnsValue(interestRatesRetMap),individualProdPages.getInterestRateTableValuesList(),"Table values");
        individualProdPages.clickChevronShowYearlyReturns();
        logReportStepValidationEnd("Interest Rates and Returns section validation completed");
    }

    public static List<String> interestRatesTableHdr(){
        List  <String> tempLoad=new ArrayList<>();
        tempLoad.add("Term");
        tempLoad.add("AER");
        tempLoad.add("Interest");
        tempLoad.add("Your Return");
        return tempLoad;
    }

    public static List<String> getProductInterestRatesAndReturnsValue(HashMap<String,String> masterMapIntRatesRet){
        List <String> productIntRatesReturns = new ArrayList<>();
        for (int i=1;i<=masterMapIntRatesRet.size();i++){
            productIntRatesReturns.add(masterMapIntRatesRet.get("LineItem"+i));
        }
        return productIntRatesReturns;
    }

    @And("Validate the Savings Calculator page content")
    public void validateTheSavingsCalculatorIconPresenceOnPage() {
        extractExcelValue= FrameworkConstants.getExpMapNewCommsPg();
        this.pageName="NewComms";
        logReportStepValidationStart("Check Savings calculator page content validation Initiated");
        strCompareString(individualProdPages.getSavingsCalculatorIconLblPage(),extractExcelValue.get("expSCIconLbl"),"Savings Calculator");
        strCompareString(individualProdPages.clickSavingsCalculatorIcon(),extractExcelValue.get("expSCHdr"),"Savings Calculator");
        strCompareString(individualProdPages.getScSubHdr(),extractExcelValue.get("expSCSubHdr"),"Savings Calculator Fixed Header");
        strCompareString(individualProdPages.getSCAmountToInvestLbl(),extractExcelValue.get("expSCInvestAmountLbl"),"Savings Calculator Amount");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expSCFindOutMore")),individualProdPages.getSCFindOutMore(),"SSCN FAQ", "Savings Calculator");
        strCompareString(individualProdPages.getSCPBHdr(),extractExcelValue.get("expPBHdr"),"Savings Calculator PB Header");
        strCompareString(individualProdPages.getPBDesc(),extractExcelValue.get("expPBDesc"),"Savings Calculator PB Description");
        strCompareString(individualProdPages.getSCDesc(),extractExcelValue.get("expSCDesc"),"Savings Calculator Description");
        strCompareString(individualProdPages.getSCCalculatorBtn(),extractExcelValue.get("expSCCalculateBtn"),"Savings Calculator Calculate button");
        getListItemsComparison(expListContent(extractExcelValue.get("expSectionHeadersListArr")),individualProdPages.getSectionHeadersList(),"Section Headers FT:");
        getListItemsComparison(expYearsAndIssues,individualProdPages.getYearsAndIssues(),"Years and Issues");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expLearnMoreLink")),individualProdPages.getLearnMoreLink(),"Learn MOre", "Savings Calculator");
        getListItemsComparison(expResultValues,individualProdPages.getPercentageBenefit(),"Years and Issues");
        getListItemsComparison(expBenefitCalculation,individualProdPages.getResultValues(),"Years and Issues");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBuyNow")),individualProdPages.clickBuyNowBtnInSavingsCalculator("Buy now"),"Buynow", "Savings Calculator");
//        strCompareString(individualProdPages.clickSavingsCalculatorCloseBtn(),pageName,"Close Button on Savings Calculator Clicked");
        logReportStepValidationEnd("Check Savings calculator page content validation Completed");

    }
    List<String> expYearsAndIssues= Arrays.asList(new String[]{"10 year", "Issue 9", "5 year", "Issue 25", "3 year", "Issue 18"});
    List<String> expResultValues=Arrays.asList(new String[]{"22% total return", "AER 2.01%", "Tax Free", "9% total return", "AER 1.74%", "Tax Free", "4% total return", "AER 1.32%", "Tax Free"});
    List<String> expBenefitCalculation=Arrays.asList(new String[]{"€61.00", "€54.50", "€52.00"});


}
