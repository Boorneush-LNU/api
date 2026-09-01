package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_IndividualProductPages;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PrizeBondsPage;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class Kentico13_PrizeBondsPageStepDefinitions extends MasterStepDefs{

    WebDriver driver= DriverManager.getWebDriver();
    Kentico13_IndividualProductPages individualProdPages =new Kentico13_IndividualProductPages(driver);
    Kentico13_PrizeBondsPage pbPage=new Kentico13_PrizeBondsPage(driver);
    Kentico13_Homepage homePage=new Kentico13_Homepage(driver);

    @And("Validate content for Banner section on Prize Bonds Page")
    public void validateContentForBannerSectionOnPrizeBondsPage() {
        try {
            extractExcelValue= FrameworkConstants.getExpMasterMapIndProdPgs().get("Prize Bonds");
            logReportStepValidationStart("Banner section validation Initiated");
            strCompareString(individualProdPages.getBannerTitle(pageName),  extractExcelValue.get("expBannerTitle"),  "Banner title");
            strCompareString(individualProdPages.getBannerText(),  extractExcelValue.get("expBannerText"), "Banner Text content");
            strCompareString(pbPage.getBannerBtn1Lbl(),extractExcelValue.get("expBannerBtnLbl"),"Banner Button1 - Buy now label ");
            strCompareString(pbPage.getBannerBtn2Lbl(),extractExcelValue.get("expBannerBtnLbl2"),"Banner Button2 - Gift now label ");
            individualProdPages.clickBuyNowBannerBtn("Buy now");
            strCompareString(individualProdPages.getBuyNowModalTitle(),extractExcelValue.get("expBuyNowModalHeader"), "Buy now modal header");
            individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
            pbPage.clickBuyNowBannerBtn2("Gift now");
            strCompareString(individualProdPages.getBuyNowModalTitle(),extractExcelValue.get("expBuyNowModalHeader"), "Gift now modal header");
            individualProdPages.clickBuyNowModalCloseBtn("Gift now modal Close button");
            logReportStepValidationEnd("Banner section validation Completed");
        }
        catch(Exception e){
            ExtentCucumberAdapter.getCurrentStep().fail("Banner Section Validation Failed");
            log.error("Banner Section Validation Failed : " + e.getMessage());
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }

    }


    @And("Validate content for Secondary Banner section on Prize Bonds Page")
    public void validateContentForSecondaryBannerSectionOnPrizeBondsPage()  {
        logReportStepValidationStart("Secondary Banner section validation Initiated");
        strCompareString(pbPage.getSecondaryBannerText(),extractExcelValue.get("expSecondaryBannerText"),"Secondary Banner text ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expSecondaryBannerBtnListArr")),pbPage.getSecondaryBannerBtnList(),"Find out more","Prize Bonds page ");
        logNavigation(pbPage.clickSecondaryBannerBtn("Find out more", "How to Purchase?"),"page","Help article navigation ");
        strCompareString(pbPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"),"Prize Bonds Banner title");
        logReportStepValidationEnd("Secondary Banner section validation Completed");
    }


    @And("Validate content for Prize Bonds Draw dates section")
    public void validateContentForPrizeBondsDrawDatesSection() {
        logReportStepValidationStart("Prize Bonds Draw dates section validation Initiated");
//        strCompareString(pbPage.getBannerDrawPrizeAmount(),extractExcelValue.get("expBannerDrawPrizeAmount"),"PB Draw Prize Amount ");
//        strCompareString(pbPage.getBannerDrawPrizeDateTextLeft(),extractExcelValue.get("expBannerDrawPrizeDateTextLeft"),"PB Draw Date Text Left Panel ");
//        strCompareString(pbPage.getBannerDrawTextRight(),extractExcelValue.get("getBannerDrawTextRight"),"PB Draw Text Right Panel ");
//        strCompareString(pbPage.getBannerDrawPrizeDateTextRight(),extractExcelValue.get("expBannerDrawPrizeDateTextRight"),"PB Draw Date Text Right Panel ");

        pbPage.PBResultValues();

        logReportStepValidationEnd("Prize Bonds Draw dates section validation Completed");
    }


    @And("Validate the content for Prize Bond Draw section")
    public void validateTheContentForPrizeBondDrawSection()  {
        logReportStepValidationStart("Prize Bonds Draw section validation Initiated");
        strCompareString(pbPage.getPBDrawSectionTitle(),extractExcelValue.get("expPBDrawSectionTitle"),"Section Title ");
        strCompareString(pbPage.getPBDrawTilesHdrLblsArr(),extractExcelValue.get("expPBDrawTilesHdrLblsArr"),"Tiles Header ");
        strCompareString(pbPage.getPBDrawTilesSummaryTextArr(),extractExcelValue.get("expPBDrawTilesSummaryTextArr"),"Tiles Summary Text ");
        strCompareString(pbPage.getPBDrawSeeDrawResultsBtnLbl(),extractExcelValue.get("getPBDrawSeeDrawResultsBtnLbl"),"See draw results button label ");
        strCompareString(pbPage.getPBDrawRegisterBtnLbl(),extractExcelValue.get("expPBDrawRegisterBtnLbl"),"Register button label ");
        strCompareString(pbPage.getPBDrawSignInBtnLbl(),extractExcelValue.get("expPBDrawSignInBtnLbl"),"Sign in button label Text ");

        logNavigation(pbPage.clickPbDrawTileRecentDrawResultsLink("Recent Draw Results Link","Prizes in this draw"),"page","PB Results");
        strCompareString(pbPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"), "Prize Bonds Page ");
        logNavigation(pbPage.clickPbDrawTileSeeDrawResultsBtn("See draw results button","Prizes in this draw"),"page","Pb Results");
        strCompareString(pbPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"), "Prize Bonds Page ");
        logNavigation(pbPage.clickPbDrawTileMngYourPBLink("Manage your Prize Bonds Link","Ireland State Savings Online"),"page","Sign in");
        strCompareString(pbPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"), "Prize Bonds Page ");
        logNavigation(pbPage.clickPbDrawTileSignInBtn("Sign in button","Ireland State Savings Online"),"page","Sign in");
        strCompareString(pbPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"), "Prize Bonds Page ");
        logNavigation(pbPage.clickPbDrawTileRegisterBtn("Register button","Register for Ireland State Savings Online"),"page","Begin Registration");
        strCompareString(pbPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"), "Prize Bonds Page ");
        logReportStepValidationEnd("Prize Bonds Draw section validation Completed");
    }


    @And("Validate the content for Buy Prize Bonds Section on {string} Page")
    public void validateTheContentForBuyPrizeBondsSectionOnPage(String pageName) {
        logReportStepValidationStart("Buy Prize Bonds section validation Initiated");
        strCompareString(pbPage.getBuyPBHdr(),extractExcelValue.get("expBuyPBHdr"),"Buy Prize Bonds section Title ");
        strCompareString(homePage.getPBTileHdr(),extractExcelValue.get("expPBWinnerTileHdr"),"Buy Prize Bonds section Title ");
        strCompareString(pbPage.getPBTileSummaryText(),extractExcelValue.get("expPBTileSummaryText"),"Prize Bonds Tile summary text ");
        strCompareString(homePage.getPBGiftTileHdr(),extractExcelValue.get("expPBGiftTileHdr"),"Prize Bonds Gift Tile Title ");
        strCompareString(pbPage.getPBGiftTileSummaryText(),extractExcelValue.get("expPBGiftTileSummaryText"),"Prize Bonds Gift Tile summary text ");

        strCompareString(homePage.getPrizeBondsPBTileBuyNowBtnLbl(),extractExcelValue.get("expBuyPBTileBtnName"),"Buy now on PB Tile");
        strCompareString(homePage.getPrizeBondsPBGiftTileBuyNowBtnLbl(),extractExcelValue.get("expBuyPBGiftTileBtnName"),"Gift now on PB as a Gift Tile");

        homePage.clickPrizeBondsTileBuyGiftNowBtn("Buy now");
        strCompareString(individualProdPages.getBuyNowModalTitle(),extractExcelValue.get("expBuyNowModalHeader"), "Buy now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
        strCompareString(individualProdPages.getBannerTitle(pageName),  extractExcelValue.get("expBannerTitle"),  "Banner title");
        homePage.clickPrizeBondsTileBuyGiftNowBtn("Gift now");
        strCompareString(individualProdPages.getBuyNowModalTitle(),extractExcelValue.get("expBuyNowModalHeader"), "Gift now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Gift now modal Close button");
        strCompareString(individualProdPages.getBannerTitle(pageName),  extractExcelValue.get("expBannerTitle"),  "Banner title");
        logReportStepValidationEnd("Buy Prize Bonds section validation Completed");
    }


    @And("Validate the content for Check My Numbers section")
    public void validateTheContentForCheckMyNumbersSection() {
        logReportStepValidationStart("Check My Numbers section validation Initiated");
        strCompareString(pbPage.getCheckMyNumbersHdr(),extractExcelValue.get("expCheckMyNumbersHdr"),"Section title ");
        strCompareString(pbPage.getCheckMyNumSummaryText(),extractExcelValue.get("expCheckMyNumSummaryText"),"Summary text ");
        strCompareString(pbPage.getCheckMyNumFirstPBNumHdrLbl(),extractExcelValue.get("expCheckMyNumFirstPBNumHdrLbl"),"First Prize Bond Number header field label ");
        strCompareString(pbPage.getCheckMyNumLastPBNumHdrLbl(),extractExcelValue.get("expCheckMyNumLastPBNumHdrLbl"),"Last Prize Bond Number header field label ");
        strCompareString(pbPage.getCheckMyNumBtnLbl(),extractExcelValue.get("expCheckMyNumBtnLbl"),"Check my numbers button label ");
        pbPage.clickChevronHowDoIFindMyPBNum(extractExcelValue.get("expChevronHowDoIFindMyPBNumText"));
        strCompareString(pbPage.getChevronHowDoIFindMyPBNumText(),extractExcelValue.get("expChevronHowDoIFindMyPBNumText"),"Chevron text ");
        strCompareString(pbPage.getChevronExpandHdr(),extractExcelValue.get("expChevronExpandHdr"),"Chevron expanded section header text ");
        strCompareString(pbPage.getChevronExpandSummaryText(),extractExcelValue.get("expChevronExpandSummaryText"),"Chevron expanded section summary text ");
        logReportStepValidationEnd("Check My Numbers section validation Completed");
    }

}
