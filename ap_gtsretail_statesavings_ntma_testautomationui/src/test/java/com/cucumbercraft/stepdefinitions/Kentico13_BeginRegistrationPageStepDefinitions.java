package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_BeginRegistrationPage;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Kentico13_BeginRegistrationPageStepDefinitions extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_BeginRegistrationPage beginRegPg=new Kentico13_BeginRegistrationPage(driver);

    @Then("Validate content for Banner Section for Begin Registration Page")
    public void validateContentForBannerSectionForBeginRegistrationPage()  {
        extractExcelValue= FrameworkConstants.getExpMapBeginRegPg();
        logReportStepValidationStart("Banner section validation Initiated");
        strCompareString(beginRegPg.getBannerTitle(),extractExcelValue.get("expBannerTitle"),"Banner Title ");
        strCompareString(beginRegPg.getBannerText(),extractExcelValue.get("expBannerText"),"Banner Text ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expBannerBtnArr")),beginRegPg.getBannerBtnListDetails(),"Begin Registration","Begin Registration");
        logNavigation(beginRegPg.clickBannerBtn("Begin Registration","Let’s get started"),"Begin Registration","Personal Details page ");
        logNavigation(beginRegPg.navigateBackPg(extractExcelValue.get("expBannerTitle")),"","User navigation to Begin Registration Page");
        logReportStepValidationEnd("Banner section validation Completed");
    }

    @And("Validate content in Benefits of Registering section for Begin Registration page")
    public void validateContentInBenefitsOfRegisteringSectionForBeginRegistrationPage() {
        logReportStepValidationStart("Benefits of Registering section validation Initiated");
        strCompareString(beginRegPg.getBenefitsOfRegHdr(),extractExcelValue.get("expBenefitsOfRegHdr"),"Benefits of registering ");
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsLeftBulletPtArr")),beginRegPg.getHighlightsLeftBulletPtArr(),"Highlights Left bullet point ");
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsRightBulletPtArr")),beginRegPg.getHighlightsRightBulletPtArr(),"Highlights Right bullet point ");
        strCompareString(beginRegPg.getSSOViewMngProdHdr(),extractExcelValue.get("expSSOViewMngProdHdr"),"SSO View and Manage Products Table Header ");
        getListItemsComparison(expListContent(extractExcelValue.get("expMngProdTableListArr")),beginRegPg.getMngProdTableList(),"SSO View and Manage Products Table ");
        strCompareString(beginRegPg.getSSOViewMngProdText(),extractExcelValue.get("getSSOViewMngProdText"),"SSO View and Manage Products section below table text ");
        strCompareString(beginRegPg.getSSOViewEndText(),extractExcelValue.get("expSSOViewEndText"),"SSO View and Manage Section End Text");

        logReportStepValidationEnd("Benefits of Registering section validation Completed");
    }

    @And("Validate content in Who can register for Ireland State Savings Online? section for {string} Page.")
    public void validateContentInWhoCanRegisterForIrelandStateSavingsOnlineSectionForPage(String pageName)  {
        logReportStepValidationStart("Who can register for Ireland State Savings Online? section validation Initiated");
        getListItemsComparison(expListContent(extractExcelValue.get("expWhoCanRegisterSSOHdrsListArr")),beginRegPg.getWhoCanRegisterSSOHdrsList(),"Headers List ");
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsLeftBulletPtArrBOR")),beginRegPg.getHighlightsLeftBulletPtArrBOR(),"Highlights Left bullet point ");
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsRightBulletPtArrBOR")),beginRegPg.getHighlightsRightBulletPtArrBOR(),"Highlights Right bullet point ");
        strCompareString(beginRegPg.getSSOViewMngProdHdr(),extractExcelValue.get("expSSOViewMngProdHdr"),"SSO View and Manage Products Table Header ");

        getListItemsComparison(expListContent(extractExcelValue.get("expMngProdTableListArr")),beginRegPg.getMngProdTableList(),"SSO View and Manage Products Table ");

        logNavigation(beginRegPg.clickVideoSectionBeginRegBtn("Begin Registation","Let’s get started"),"Begin Registration","Personal Details page ");
        logNavigation(beginRegPg.navigateBackPg(extractExcelValue.get("expBannerTitle")),"","User navigation to Begin Registration Page");

        logReportStepValidationEnd("Who can register for Ireland State Savings Online? section validation Completed");
    }




}