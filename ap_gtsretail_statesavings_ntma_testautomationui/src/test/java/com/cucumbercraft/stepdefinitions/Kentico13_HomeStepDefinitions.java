package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

@Log4j2
public class Kentico13_HomeStepDefinitions extends MasterStepDefs{

    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_Homepage homePage=new Kentico13_Homepage(driver);

    @When("User Navigates to {string} Page")
    public void userNavigatesToPage(String pageName) {
        logNavigation(homePage.clickNavigationConfirmationHeader(pageName) , "page",pageName);
    }

    @Then("Validate the Header Section on {string} Page")
    public void validateTheHeaderSectionOnPage(String pageName) {
        this.pageName=pageName;
        logReportStepValidationStart("Header section validation Initiated");
        if(!(pageName.equalsIgnoreCase("Sign In"))) {
            getListItemsComparisonMultipleBtnLink(homePage.getExpPageHeaderDetails(), homePage.getPageHeaderListDetails(), "Header Section", pageName);
        }
        getListItemsComparisonMultipleBtnLink(homePage.getExpPageSecNavHeaderDetails(), homePage.getPageHdrSecondaryNavComponentListDetails(), "Secondary Header Section", pageName);
        logReportStepValidationEnd("Header section validation Completed");
    }

    @Then("Validate the Header Section on HomePage")
    public void validateTheHeaderSectionOnHomePage()  {
        logReportStepValidationStart("Header section validation Initiated");
        extractExcelValue=FrameworkConstants.getExpMapHomePg();
        getListItemsComparisonMultipleBtnLink(homePage.getExpPageHeaderDetails(), homePage.getPageHeaderListDetails(), "Header Section", "Homepage");
        getListItemsComparisonMultipleBtnLink(homePage.getExpPageSecNavHeaderDetails(), homePage.getPageHdrSecondaryNavComponentListDetails(), "Secondary Header Section", "Homepage");
        for (String pageName:expListContent(extractExcelValue.get("hdrNavigationExpCntntList"))){
            logNavigation(homePage.clickNavigationConfirmationHeader(pageName), "page", pageName);
            if(!(pageName.equalsIgnoreCase("Home"))){
                homePage.navigateBackPg();
            }
        }

        for (String pageNameTemp:expListContent(extractExcelValue.get("expHdrSecNavigationExpCntntList"))){
            logNavigation(homePage.clickNavigationConfirmationHeader(pageNameTemp), "page", pageNameTemp);
            if(!((pageNameTemp.equalsIgnoreCase("English")) || (pageNameTemp.equalsIgnoreCase("Back to statesavings.ie")))){
                homePage.navigateBackPg();
            }
        }
        logReportStepValidationEnd("Header section validation Completed");
    }

    @And("Validate content for Main Banner section on Homepage")
    public void validateContentForMainBannerSectionOnHomepage() {
        logReportStepValidationStart("Main Banner section validation Initiated");
        strCompareString(homePage.getHomepageBannerTitle(),extractExcelValue.get("expHomepageBannerTitle"),"Banner title");
        strCompareString(homePage.getHomepageBannerText(),extractExcelValue.get("expHomepageBannerText"),"Banner Text content");
//        strCompareString(homePage.getHomepageBannerBtnLbl(),"Learn More","Banner button label");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBannerSectionBtn")), homePage.getBannerSectionBtnDetails(),"Learn more","Homepage");

        logReportStepValidationEnd("Main Banner section validation Completed");
    }

    @And("Validate content for Secondary Banner section on Homepage")
    public void validateContentForSecondaryBannerSectionOnHomepage() {
        logReportStepValidationStart("Secondary Banner section validation Initiated");
        strCompareString(homePage.getSecondaryBannerText(),extractExcelValue.get("expHomepageSecondaryBannerText"),"Secondary Banner section ");
        logReportStepValidationEnd("Secondary Banner section validation Completed");
    }

    @And("Validate the content for Highlights section on Homepage")
    public void validateTheContentForHighlightsSectionOnHomepage() {
        logReportStepValidationStart("Highlights Bullet Points section validation Initiated");
        strCompareString(homePage.highlightsTitleHomepage(),extractExcelValue.get("expHighlightsTitleHomepage"),"Highlights Section Title ");
        getListItemsComparison(expListContent(extractExcelValue.get("exphighlightsLeftBulletPtArr")), homePage.actHighlightsLeftListContent(),"Highlights Bullet Section Left Side ");
        getListItemsComparison(expListContent(extractExcelValue.get("exphighlightsRightBulletPtArr")), homePage.actHighlightsRightListContent(),"Highlights Bullet Section Right Side ");
        ExtentCucumberAdapter.addTestStepLog("Highlights Bullet Points section validation Completed");

        ExtentCucumberAdapter.addTestStepLog("Highlights Bullet Points section Links and button validation initiated");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expHomePageFindOutMoreBtn")), homePage.getHomepageFindOutMoreBtnDetails(),"Find out more","Homepage");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("highlightsIrelandStateSavingsOnlineLnk")), homePage.getIrelandStateSavingsOnlineBtnDetails(),"Ireland State Savings Online Link","Homepage");
        ExtentCucumberAdapter.addTestStepLog("Highlights Bullet Points section Links and button validation completed");
        logReportStepValidationEnd("Highlights Bullet Points section validation Completed");
    }

    @And("Validate the content for Carousel section on Homepage")
    public void validateTheContentForCarouselSectionOnHomepage()  {
        logReportStepValidationStart("Carousel section validation Initiated");
        log.info("Carousel section validation Initiated");
        strCompareString(homePage.getHomepageCarouselSectionTitle(),extractExcelValue.get("expCarouselSectionHeaderHomepage"),"Carousel Section Header ");
        strCompareString(homePage.getHomepageCarouselSectionSummaryText(),extractExcelValue.get("expCarouselSectionSummaryContentHomepage"),"Carousel Section Summary Text ");
        ExtentCucumberAdapter.addTestStepLog("Carousel Tile Order validation initiated");
        log.info("Carousel Tile Order validation initiated");
        List <String> expListHeaderTileCarousel=expListContent(homePage.getExpCarouselHeaderTileName("Homepage"));
        List <String> carouselOrder=homePage.getCarouselOrderPg();
        HashMap<String,HashMap<String,String>> carouselTileDetailsMap=homePage.getCarouselTileDetails(carouselOrder);
        HashMap<String,HashMap<String,String>> expCarouselTileDetailsMap=homePage.getExpCarouselTileDetails();
        getListItemsComparison(expListHeaderTileCarousel,carouselOrder,"Carousel Tile ");
        log.info(expListHeaderTileCarousel.toString() + carouselOrder + "Carousel Tile ");
        ExtentCucumberAdapter.addTestStepLog("Carousel Tile Order validation completed");
        log.info("Carousel Tile Order validation completed");
        for(String tileHeaderName:expListHeaderTileCarousel){
            strStringHashMapCarouselCompare(carouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Tile Year Issue","");
            strStringHashMapCarouselCompare(carouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Tile Header","");
            strStringHashMapCarouselCompare(carouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Tile Bullet points","");
            strStringHashMapCarouselCompare(carouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Buy now button","");
            strStringHashMapCarouselCompare(carouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Learn More button","");
        }
        homePage.pageRefresh();
        log.info(expListHeaderTileCarousel);
        for(String tileHeadervalue: expListHeaderTileCarousel) {
            log.info(tileHeadervalue);
            clickNavigationCarouselElements(tileHeadervalue);
        }
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBtnClickAndNewTabViewAllProdArr")), homePage.getCarouselViewProdBtnDetails(),"View all our savings products","Homepage");
        logReportStepValidationEnd("Carousel section validation Completed");
    }

    @And("Validate the content for Prize Bonds Section on Homepage")
    public void validateTheContentForPrizeBondsSectionOnHomepage()  {
        logReportStepValidationStart("Prize Bonds section validation Initiated");
        strCompareString(homePage.getHomepagePBSectionTitle(),extractExcelValue.get("expPrizeBondsSectionHeader"),"Prize Bonds section Title");
        strCompareString(homePage.getHomepagePBSectionSummaryText(),extractExcelValue.get("expPrizeBondsSectionSummaryText"),"Prize Bonds Section summary text");
        strCompareString(homePage.getPrizeBondsSectionPBTileHeaderAndSummaryText(),extractExcelValue.get("expPrizeBondsPBTileHeaderSummaryText"),"Prize Bonds Section PB Tile header and Summary ");
        strCompareString(homePage.getPrizeBondsSectionPBGiftTileHeaderAndSummaryText(),extractExcelValue.get("expPrizeBondsPBGiftTileHeaderSummaryText"),"Prize Bonds Section PB Gift Tile header and Summary ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expPBTileLearnMoreBtn")), homePage.getPrizeBondsSectionPBTileLearnMoreBtn(),"Prize Bonds Tile Learn more","Homepage");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expPBGiftTileLearnMoreBtn")), homePage.getPrizeBondsSectionPBGiftTileLearnMoreBtn(),"Prize Bonds Gift Tile Learn more","Homepage");

        strCompareString(homePage.getPrizeBondsPBTileBuyNowBtnLbl(),"Buy now","Buy now on PB Tile");
        strCompareString(homePage.getPrizeBondsPBGiftTileBuyNowBtnLbl(),"Gift now","Gift now on PB as a Gift Tile");
        logNavigation(homePage.clickValidatorPrizeBondsTileBuyNowBtn("Buy now"),"modal","Buy now button on PB Tile");
        logNavigation(homePage.clickValidatorPrizeBondsTileBuyNowBtn("Gift now"),"modal","Gift now button on PB as a Gift Tile");
        logNavigation(homePage.clickPrizeBondsPBTileLearnMoreBtn(),"Page","Learn more Button on PB Tile - Prize Bonds");
        homePage.navigateBackPg();
        logNavigation(homePage.clickPrizeBondsPBGiftTileLearnMoreBtn(),"Page","Learn more Button on PB as a Gift Tile - Prize Bonds");
        homePage.navigateBackPg();
        logReportStepValidationEnd("Prize Bonds section validation Completed");
    }

    @And("Validate the content for Saving Ideas section on Homepage")
    public void validateTheContentForSavingIdeasSectionOnHomepage()  {
        logReportStepValidationStart("Saving Ideas section validation Initiated");
        strCompareString(homePage.getHomepageSavingIdeasSectionTitle(),extractExcelValue.get("expSavingsIdeasTitle"),"Savings Ideas section Title ");
        strCompareString(homePage.getHomepageSavingIdeasSectionSummaryText(),extractExcelValue.get("expSavingsIdeasSummaryText"),"Savings Ideas section summary text ");
        getListItemsComparisonMultipleBtnLink(homePage.getExpSavingIdeasSectionDetails(),homePage.getSavingIdeasSectionListDetails() ,"Savings Ideas section","Homepage");
        List <String> expSavingsIdeasListDetails=expListContent(extractExcelValue.get("expSavingIdeasCntntList"));
        for (String pageName:expSavingsIdeasListDetails){
            logNavigation(homePage.clickNavigationConfirmationSavingIdeas(pageName),"page",pageName);
            homePage.navigateBackPg();
        }
        logReportStepValidationEnd("Saving Ideas section validation Completed");
    }

    @And("Validate the Footer section on the Homepage")
    public void validateTheFooterSectionOnTheHomepage()  {
        extractExcelValue=FrameworkConstants.getExpMapHomePg();
        logReportStepValidationStart("Footer section validation Initiated");
        getListItemsComparisonMultipleBtnLink(homePage.getExpPageFooterDetails(),homePage.getPageFooterListDetails() ,"Footer Section","Homepage");
        for (String pageName:expListContent(extractExcelValue.get("ftrInternalNavigationExpCntntList"))){
            logNavigation(homePage.clickNavigationInternalConfirmationFooterHeader(pageName), "page", pageName);
            homePage.navigateBackPg();
        }
        System.out.println(extractExcelValue.get("ftrExternalNavigationExpCntntList"));
        for (String pageName:expListContent(extractExcelValue.get("ftrExternalNavigationExpCntntList"))){
            logNavigation(homePage.clickNavigationExternalConfirmationFooterHeader(pageName), "page", pageName);
        }
        logReportStepValidationEnd("Footer section validation Completed");
    }

    @And("Validate the Footer section for the {string} Page")
    public void validateTheFooterSectionForThePage(String pageName) {
        logReportStepValidationStart("Footer section validation Initiated");
        getListItemsComparisonMultipleBtnLink(homePage.getExpPageFooterDetails(),homePage.getPageFooterListDetails() ,"Footer Section",pageName);
        logReportStepValidationEnd("Footer section validation Completed");
    }

    public void clickNavigationCarouselElements(String tileHeaderName)  {
        logNavigation(homePage.getClickNavigationConfirmationCarouselHeader(tileHeaderName), "page",tileHeaderName);
        homePage.navigateBackPg();
        logNavigation(homePage.getClickNavigationConfirmationCarouselLearnMore(tileHeaderName), "page",tileHeaderName);
        homePage.navigateBackPg();
        if(!(tileHeaderName.equalsIgnoreCase("Instalment Savings"))){
            strCompareString(homePage.getHeaderClickBuyNowBtnCarouselTile(tileHeaderName),"Registered for Ireland State Savings Online?", "Buy Now modal header");
            homePage.getClickBuyNowModalCloseBtn();
        }
        homePage.pageRefresh();
    }

    @Given("^Launch the Ireland State Savings Online Homepage$")
    public void LaunchtheIrelandStateSavingsOnlineHomepage(){
        ExtentCucumberAdapter.addTestStepLog("Execution on <b>"+FrameworkConstants.getEnvName()+"</b> Environment");
        log.info("Execution on "+FrameworkConstants.getEnvName()+" Environment");
        homePage.launchHomepageUrl();
        ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>"+homePage.fetchUrlBrowser()+"\n"+homePage.getPageBrowserTitle()+"</b>");
        log.info("Application Title:->"+homePage.fetchUrlBrowser()+"\n"+homePage.getPageBrowserTitle());
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

    @Given("^Launch the Ireland State Savings Online Homepage and click on Privacy preference section$")
    public void privacyPrefSection() {
            ExtentCucumberAdapter.addTestStepLog("Execution on <b>"+FrameworkConstants.getEnvName()+"</b> Environment");
            log.info("Execution on "+FrameworkConstants.getEnvName()+" Environment");
            homePage.launchHomepageUrl();
            ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>"+homePage.fetchUrlBrowser()+"\n"+homePage.getPageBrowserTitle()+"</b>");
            log.info("Application Title:->"+homePage.fetchUrlBrowser()+"\n"+homePage.getPageBrowserTitle());
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            if (homePage.privacyPrefSectionLbl() != 0) {
                ExtentCucumberAdapter.addTestStepLog("Privacy Preferences displayed");
                log.info("Privacy Preferences displayed");

            }
    }

    @And("Validate the content for privacy preference section on home page")
    public void validateTheContentForCheckMyNumbersSection() {
        extractExcelValue= FrameworkConstants.getExpMapPrivacyPrefPg();
        this.pageName="Privacy Preference Section";
        logReportStepValidationStart("Check privacy preference section validation Initiated");
        strCompareString(homePage.getPrivacyPrefHdr(),extractExcelValue.get("expPrivacyPrefHdr"),"Privacy Pref title ");
        strCompareString(homePage.getPrivacyPrefDesc(),extractExcelValue.get("expPrivacyPrefDesc"),"Privacy Pref text ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expPrivacyPrefLinkCookiesWeUse")),homePage.getPPCookiesWeUse(),"CookiesWeUse", "PrivacyPreference");
        strCompareString(homePage.getPrivacyPrefBtnRejectAll(),extractExcelValue.get("expPrivacyPrefBtnRejectAll"),"Reject ALL button");
        strCompareString(homePage.getPrivacyPrefBtnAllowAll(),extractExcelValue.get("expPrivacyPrefBtnAllowAll"),"Allow All button");
        strCompareString(homePage.getConsentHdr(),extractExcelValue.get("expConsentPreferencesHdr"),"Consent Header");
        strCompareString(homePage.getSNHdr(),extractExcelValue.get("expStrictlyNecessaryHdr"),"Strictly necessary header");
        strCompareString(homePage.getSNAlwaysActive(),extractExcelValue.get("expStrictlyNecessaryAlwaysActive"),"Always active");
        strCompareString(homePage.getSNDesc(),extractExcelValue.get("expStrictlyNecessaryDesc"),"Strictly necessary Desc");
        strCompareString(homePage.getPerformanceHdr(),extractExcelValue.get("expPerformanceHdr"),"Performance Header");
        strCompareString(homePage.getPerformanceDesc(),extractExcelValue.get("expPerformanceDesc"),"Performance Description");
        strCompareString(homePage.getFunctionalHdr(),extractExcelValue.get("expFunctionalHdr"),"Functional header");
        strCompareString(homePage.getFunctionalDesc(),extractExcelValue.get("expFunctionalDesc"),"Functional Description");
        strCompareString(homePage.getBtnConfirmMyChoices(),extractExcelValue.get("expPrivacyPrefBtnConfirmMyChoices"),"Confirm my choices button");
        logReportStepValidationEnd("Check privacy preference section Completed");
    }



}
