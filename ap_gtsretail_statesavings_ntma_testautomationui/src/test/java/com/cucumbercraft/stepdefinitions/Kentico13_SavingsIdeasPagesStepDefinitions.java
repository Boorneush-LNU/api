package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_SavingsIdeasPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
@Log4j2
//import static com.cucumbercraft.pages.expectedExtract.extractExpected;

public class Kentico13_SavingsIdeasPagesStepDefinitions extends MasterStepDefs {

    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_Homepage homePage=new Kentico13_Homepage(driver);
    Kentico13_SavingsIdeasPages savingsIdeasPage = new Kentico13_SavingsIdeasPages(driver);
    static HashMap<String,String> extractExcelValueSavingsIdeasPg;

    @When("User Navigates to {string} Page from the Savings Ideas Section")
    public void userNavigatesToPageFromTheSavingsIdeasSection(String pageName) {
        logNavigation(savingsIdeasPage.clickNavigationConfirmationSavingIdeas(pageName), "page", pageName);
    }

    @Then("Validate content for Banner Section for Savings Ideas Page - {string}")
    public void validateContentForBannerSectionForSavingsIdeasPage(String pageName) {
//        extractExcelValue= FrameworkConstants.getExpMasterMapSavingsIdeasPgs().get(pageName);
        extractExcelValueSavingsIdeasPg= FrameworkConstants.getExpMasterMapSavingsIdeasPgs().get(pageName);
        this.pageName=pageName;
        logReportStepValidationStart("Banner section validation Initiated");
//        strCompareString(savingsIdeasPage.bannerTitleSavingsIdeasPg(),extractExcelValue.get("expBannerTitle"),"Banner Title ");
//        strCompareString(savingsIdeasPage.bannerTextSavingsIdeasPg(),extractExcelValue.get("expBannerText"),"Banner Text ");

        strCompareString(savingsIdeasPage.bannerTitleSavingsIdeasPg(),extractExcelValueSavingsIdeasPg.get("expBannerTitle"),"Banner Title ");
        strCompareString(savingsIdeasPage.bannerTextSavingsIdeasPg(),extractExcelValueSavingsIdeasPg.get("expBannerText"),"Banner Text ");


        logReportStepValidationEnd("Banner section validation Completed");
    }

    @And("Validate content in General Context Paragraph section for {string} page")
    public void validateContentInGeneralContextParagraphSectionForPage(String pageName) {
        logReportStepValidationStart("General context paragraph section validation Initiated");
//        getListItemsComparison(expListContent(extractExcelValue.get("expGeneralContentParagraphArr")),savingsIdeasPage.getGeneralContentParaList(),"General Context Paragraph ");
//        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expGeneralContentParagraphArrBtnLink1")),savingsIdeasPage.getGeneralContentParaLink1Details(),extractExcelValue.get("expGeneralContentParagraphArrBtnLink1").split(";")[0],"Link Details in General Context Paragraph Section ");
        getListItemsComparison(expListContent(extractExcelValueSavingsIdeasPg.get("expGeneralContentParagraphArr")),savingsIdeasPage.getGeneralContentParaList(),"General Context Paragraph ");
        getListItemsComparisonBtnLink(expListContent(extractExcelValueSavingsIdeasPg.get("expGeneralContentParagraphArrBtnLink1")),savingsIdeasPage.getGeneralContentParaLink1Details(),extractExcelValueSavingsIdeasPg.get("expGeneralContentParagraphArrBtnLink1").split(";")[0],"Link Details in General Context Paragraph Section ");


        if(pageName.equalsIgnoreCase("Childcare Plus")){
//            getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expGeneralContentParagraphArrBtnLink2")),savingsIdeasPage.getGeneralContentParaLink2DetailsOurProductsChildcarePlus(),extractExcelValue.get("expGeneralContentParagraphArrBtnLink2").split(";")[0],"Link Details in General Context Paragraph Section ");
            getListItemsComparisonBtnLink(expListContent(extractExcelValueSavingsIdeasPg.get("expGeneralContentParagraphArrBtnLink2")),savingsIdeasPage.getGeneralContentParaLink2DetailsOurProductsChildcarePlus(),extractExcelValueSavingsIdeasPg.get("expGeneralContentParagraphArrBtnLink2").split(";")[0],"Link Details in General Context Paragraph Section ");
        }
        logReportStepValidationEnd("General context paragraph section validation Completed");
    }

    @And("Validate content in Carousel section for Savings Ideas Page - {string}")
    public void validateContentInCarouselSectionForSavingsIdeasPage(String pageName) {
        logReportStepValidationStart("Carousel section validation Initiated");
//        strCompareString(savingsIdeasPage.carouselHdrSavingsIdeasPg(),extractExcelValue.get("expCarouselHeader"),"Carousel Section Title ");
        strCompareString(savingsIdeasPage.carouselHdrSavingsIdeasPg(),extractExcelValueSavingsIdeasPg.get("expCarouselHeader"),"Carousel Section Title ");
        List<String> getCarouselOrderProduct=homePage.getCarouselOrderPg();
        HashMap<String, HashMap<String,String>> getCarouselTileDetailsMap=homePage.getCarouselTileDetails(getCarouselOrderProduct);
        HashMap<String, HashMap<String,String>> expCarouselTileDetailsMap=Kentico13_Homepage.getExpCarouselTileDetails();

        carouselSectionContentValidation(expListContent(homePage.getExpCarouselHeaderTileName(pageName)),getCarouselOrderProduct,getCarouselTileDetailsMap,expCarouselTileDetailsMap,pageName);
//        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expCarouselSectionViewAllProdBtnArr")), savingsIdeasPage.viewAllOurProductsBtnSavingsIdeasPg(),extractExcelValue.get("expCarouselSectionViewAllProdBtnArr").split(";")[0],pageName);
        getListItemsComparisonBtnLink(expListContent(extractExcelValueSavingsIdeasPg.get("expCarouselSectionViewAllProdBtnArr")), savingsIdeasPage.viewAllOurProductsBtnSavingsIdeasPg(),extractExcelValueSavingsIdeasPg.get("expCarouselSectionViewAllProdBtnArr").split(";")[0],pageName);
        logReportStepValidationEnd("Carousel section validation Completed");

    }


}
