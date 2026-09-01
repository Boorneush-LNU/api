package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_AboutUsPage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_OurProducts;
import io.cucumber.java.en.And;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
@Log4j2
public class Kentico13_OurProductsStepDefs extends MasterStepDefs{

    WebDriver driver= DriverManager.getWebDriver();
    HashMap<String,HashMap<String,String>> extractExcelValueTileDetails;
    Kentico13_OurProducts ourProducts=new Kentico13_OurProducts(driver);
    Kentico13_Homepage homePage=new Kentico13_Homepage(driver);
    Kentico13_AboutUsPage aboutUsPage=new Kentico13_AboutUsPage(driver);
    static HashMap<String,String> extractExcelValueOurprodPg= FrameworkConstants.getExpMapOurProdPg();


    @And("User clicks {string} Tile link on Our Products Page")
    public void userClicksTileLinkOnOurProductsPage(String pageName)  {
        logNavigation(homePage.clickNavigationConfirmationHeader(pageName),"page",pageName);
    }

    @And("Validate content for Banner section on the {string} Page")
    public void validateContentForBannerSectionOnThePage(String pageName)  {
        this.pageName=pageName;
        extractExcelValue= FrameworkConstants.getExpMapOurProdPg();
        logReportStepValidationStart("Banner section validation Initiated");
        strCompareString(ourProducts.getBannerTitle(pageName),extractExcelValue.get("expBannerTitle"),"Banner Title");
        strCompareString(ourProducts.getBannerText(),extractExcelValue.get("expBannerText"),"Banner Text");
        logReportStepValidationEnd("Banner section validation Completed");
    }


    @And("Validate the content for {string} section")
    public void validateTheContentForSection(String sectionName)   {
        logReportStepValidationStart(sectionName+ " section validation Initiated");
        extractExcelValueTileDetails=FrameworkConstants.getExpMasterMapOurProdTileDetails();
        List<String> expSectionproductListArr=expListContent(extractExcelValue.get(expMapVarLoad(sectionName)));
        tileSectionContentValidation(expSectionproductListArr,ourProducts.getOuterMapLoadFinal().get(sectionName),extractExcelValueTileDetails);
        logReportStepValidationEnd(sectionName+ " section validation Completed");
    }


    @And("Validate the Section and Tile Header Order is correct on the Page")
    public void validateTheSectionAndTileHeaderOrderIsCorrectOnThePage() {
        logReportStepValidationStart("Section and Tile Header Order validation Initiated");
        getListItemsComparison(expListContent(extractExcelValue.get("expSectionHeadersListArr")),ourProducts.getSectionHeadersList(),"Section Headers ");
        getListItemsComparison(expListContent(extractExcelValue.get("expTileHeaderOrderListArr")),ourProducts.getTileHeaderOrder(),"Tile Header Order ");
        logReportStepValidationEnd("Section and Tile Header Order validation Completed");
    }

    @And("Validate the content for Need a little more help deciding? section")
    public void validateTheContentForNeedALittleMoreHelpDecidingSection()  {
        logReportStepValidationStart("Need a little more help deciding? section validation Initiated");
        strCompareString(ourProducts.getNeedALittleMoreHelpDecidingHdr(),extractExcelValue.get("expNeedALittleMoreHelpDecidingHdr"),"Header ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expVisitHelpAndSupportBtnListDetailsArr")),ourProducts.getVisitHelpAndSupportBtnListDetails(),"Visit Help & Support", pageName);
        logNavigation(ourProducts.clickVisitHelpAndSupportBtn("Visit Help & Support","Help and Support"),"page","Help and Support ");
        logNavigation(ourProducts.navigateBackPg(extractExcelValue.get("expBannerTitle")),"","User navigation to Our Products ");
        logReportStepValidationEnd("Need a little more help deciding? section validation Completed");
    }


    public void tileSectionContentValidation(List<String> expListTileHeaderSection, HashMap<String, HashMap<String,String>> getTileDetailsMap, HashMap<String, HashMap<String,String>> expTileDetailsMap){
        for(String tileHeaderName:expListTileHeaderSection){
            strStringHashMapTileCompare(getTileDetailsMap, expTileDetailsMap,tileHeaderName,"Tile Year and Issue Number","");
            strStringHashMapTileCompare(getTileDetailsMap,expTileDetailsMap,tileHeaderName,"Tile Stats","");
            strStringHashMapTileCompare(getTileDetailsMap,expTileDetailsMap,tileHeaderName,"Tile Header","");
            strStringHashMapTileCompare(getTileDetailsMap,expTileDetailsMap,tileHeaderName,"Tile Primary button","");
            strStringHashMapTileCompare(getTileDetailsMap,expTileDetailsMap,tileHeaderName,"Tile Secondary button","");
        }
    }

    public void strStringHashMapTileCompare(HashMap<String,HashMap<String,String>> actHashOfHashMap,HashMap<String,HashMap<String,String>> expHashOfHashMap, String tileName, String searchInternalMap, String messageLog){
        strCompareString(actHashOfHashMap.get(tileName).get(searchInternalMap),actHashOfHashMap.get(tileName).get(searchInternalMap),tileName+" "+searchInternalMap+" "+messageLog+" ");
    }

    public String expMapVarLoad(String sectionName){
        HashMap<String,String> loadExpVarMap=new HashMap<>();
        String dataVar=null;
        loadExpVarMap.put("Fixed Term Savings Products","expFixedTermSavingsProductsListArr");
        loadExpVarMap.put("Prize Bonds","expPrizeBondsListArr");
        loadExpVarMap.put("Regular Saving Products","expRegularSavingProductsListArr");
        loadExpVarMap.put("Deposit Accounts","expDepositAccountsListArr");
        return loadExpVarMap.get(sectionName);
    }

    @And("Validate content in Reasons to save section for {string} Page")
    public void validateContentInReasonsToSaveSectionForPage(String pageName) {
        logReportStepValidationStart("Reasons to Save section validation Initiated");
//        strCompareString(aboutUsPage.getHdrReasonsToSave(),extractExcelValue.get("expHdrReasonsToSave"),"Reasons to Save section Title ");
//        strCompareString(aboutUsPage.getSummaryTextReasonsToSave(),extractExcelValue.get("getSummaryTextReasonsToSave"),"Reason to Save section summary text ");

        strCompareString(aboutUsPage.getHdrReasonsToSave(),extractExcelValueOurprodPg.get("expHdrReasonsToSave"),"Reasons to Save section Title ");
        strCompareString(aboutUsPage.getSummaryTextReasonsToSave(),extractExcelValueOurprodPg.get("getSummaryTextReasonsToSave"),"Reason to Save section summary text ");
        getListItemsComparisonMultipleBtnLink(homePage.getExpSavingIdeasSectionDetails(),homePage.getSavingIdeasSectionListDetails() ,"Reasons to Save section",pageName);
        logReportStepValidationEnd("Reasons to Save section validation Completed");
    }



}
