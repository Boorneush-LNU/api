package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_AboutUsPage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_IndividualProductPages;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Kentico13_AboutUsStepDefinitions extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_IndividualProductPages individualProdPages = new Kentico13_IndividualProductPages(driver);
    Kentico13_Homepage homePage = new Kentico13_Homepage(driver);
    Kentico13_AboutUsPage aboutUsPage=new Kentico13_AboutUsPage(driver);
    static HashMap<String,String> extractExcelValueAboutUsPg= FrameworkConstants.getExpMapAboutUsPg();

    static final Logger log = LogManager.getLogger(Kentico13_AboutUsStepDefinitions.class);

    @And("Validate content in Simple ways to save Carousel section for {string} Page")
    public void validateContentInSimpleWaysToSaveCarouselSectionForPage(String pageName) {
        logReportStepValidationStart("Carousel section validation Initiated");
//        strCompareString(individualProdPages.getCarouselHdr(), extractExcelValue.get("expCarouselHdr"), "Carousel Section Title ");
        strCompareString(individualProdPages.getCarouselHdr(), extractExcelValueAboutUsPg.get("expCarouselHdr"), "Carousel Section Title ");
        ExtentCucumberAdapter.addTestStepLog("Carousel Tile Order validation initiated");
        log.info("Carousel Tile Order validation initiated");
        List<String> expListHeaderTileCarousel = expListContent(homePage.getExpCarouselHeaderTileName("About Us"));
        List<String> carouselOrder = homePage.getCarouselOrderPg();
        getListItemsComparison(expListHeaderTileCarousel, carouselOrder, "Carousel Tile ");
        ExtentCucumberAdapter.addTestStepLog("Carousel Tile Order validation completed");
        log.info("Carousel Tile Order validation completed");
        HashMap<String, HashMap<String, String>> getCarouselTileDetailsMap = homePage.getCarouselTileDetails(carouselOrder);
        for (String tileHeaderName : expListHeaderTileCarousel) {
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap, Kentico13_Homepage.getExpCarouselTileDetails(), tileHeaderName, "Tile Year Issue", "");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap, Kentico13_Homepage.getExpCarouselTileDetails(), tileHeaderName, "Tile Header", "");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap, Kentico13_Homepage.getExpCarouselTileDetails(), tileHeaderName, "Tile Bullet points", "");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap, Kentico13_Homepage.getExpCarouselTileDetails(), tileHeaderName, "Buy now button", "");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap, Kentico13_Homepage.getExpCarouselTileDetails(), tileHeaderName, "Learn More button", "");
        }
//        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBtnClickAndNewTabViewAllProdArr")), individualProdPages.getCarouselViewProdBtnDetails(),extractExcelValue.get("expBtnClickAndNewTabViewAllProdArr").split(";")[0], pageName);
        getListItemsComparisonBtnLink(expListContent(extractExcelValueAboutUsPg.get("expBtnClickAndNewTabViewAllProdArr")), individualProdPages.getCarouselViewProdBtnDetails(),extractExcelValueAboutUsPg.get("expBtnClickAndNewTabViewAllProdArr").split(";")[0], pageName);
        logReportStepValidationEnd("Carousel section validation Completed");
    }


    @And("Validate content in Highlights section for About Us Page")
    public void validateContentInHighlightsSectionForAboutUsPage() {
        logReportStepValidationStart("Highlights section validation Initiated");
//        ArrayList <String> expListHighlights=loadHeaderParaLoadFromSheet(extractExcelValue);
        ArrayList <String> expListHighlights=loadHeaderParaLoadFromSheet(extractExcelValueAboutUsPg);
        getListItemsComparison(expListHighlights,aboutUsPage.getAboutSUsHighlightsText(),"Highlights text");
//        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expLinkDetailsStateSavingsRegHighlights")),aboutUsPage.getLinkDetailsStateSavingsRegHighlights(),"Highlights link details", "About Us");
        getListItemsComparisonBtnLink(expListContent(extractExcelValueAboutUsPg.get("expLinkDetailsStateSavingsRegHighlights")),aboutUsPage.getLinkDetailsStateSavingsRegHighlights(),"Highlights link details", "About Us");
        logReportStepValidationEnd("Highlights section validation Completed");
    }

    @And("Validate content for Banner Section for About Us Page")
    public void validateContentForBannerSectionForAboutUsPage()  {
//        extractExcelValue= FrameworkConstants.getExpMapAboutUsPg();
                extractExcelValueAboutUsPg= FrameworkConstants.getExpMapAboutUsPg();
        logReportStepValidationStart("Banner section validation Initiated");
//        strCompareString(aboutUsPage.getBannerTitleAboutUsPg(),extractExcelValue.get("expBannerTitle"),"Banner Title ");
//        strCompareString(aboutUsPage.getBannerTextAboutUsPg(),extractExcelValue.get("expBannerText"),"Banner summary text ");
        strCompareString(aboutUsPage.getBannerTitleAboutUsPg(),extractExcelValueAboutUsPg.get("expBannerTitle"),"Banner Title ");
        strCompareString(aboutUsPage.getBannerTextAboutUsPg(),extractExcelValueAboutUsPg.get("expBannerText"),"Banner summary text ");
        logReportStepValidationEnd("Banner section validation Completed");
    }


    @And("Validate content in Reasons to save section for the {string} Page")
    public void validateContentInReasonsToSaveSectionForPage(String pageName) {
        logReportStepValidationStart("Reasons to Save section validation Initiated");
//        strCompareString(aboutUsPage.getHdrReasonsToSave(),extractExcelValue.get("expHdrReasonsToSave"),"Reasons to Save section Title ");
//        strCompareString(aboutUsPage.getSummaryTextReasonsToSave(),extractExcelValue.get("getSummaryTextReasonsToSave"),"Reason to Save section summary text ");

        strCompareString(aboutUsPage.getHdrReasonsToSave(),extractExcelValueAboutUsPg.get("expHdrReasonsToSave"),"Reasons to Save section Title ");
        strCompareString(aboutUsPage.getSummaryTextReasonsToSave(),extractExcelValueAboutUsPg.get("getSummaryTextReasonsToSave"),"Reason to Save section summary text ");
        getListItemsComparisonMultipleBtnLink(homePage.getExpSavingIdeasSectionDetails(),homePage.getSavingIdeasSectionListDetails() ,"Reasons to Save section",pageName);
        logReportStepValidationEnd("Reasons to Save section validation Completed");
    }

    private ArrayList<String> loadHeaderParaLoadFromSheet(HashMap<String, String> extractExcelValueTemp)
    {
        ArrayList <String>expectedParagraph = new ArrayList<String>();
        expectedParagraph.add(extractExcelValueTemp.get("expHighlightsTextWeSupport"));
        expectedParagraph.add(extractExcelValueTemp.get("expHighlightsTextWeProtect"));
        expectedParagraph.add(extractExcelValueTemp.get("expHighlightsTextWeCharge"));
        expectedParagraph.add(extractExcelValueTemp.get("expHighlightsTextIrelandSSO"));
        return expectedParagraph;
    }


}