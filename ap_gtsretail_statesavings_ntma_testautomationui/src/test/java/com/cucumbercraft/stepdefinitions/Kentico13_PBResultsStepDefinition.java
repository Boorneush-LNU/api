package com.cucumbercraft.stepdefinitions;


import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_IndividualProductPages;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PbResults;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PrizeBondsPage;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kentico13_PBResultsStepDefinition extends MasterStepDefs {

    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_IndividualProductPages individualProdPages = new Kentico13_IndividualProductPages(driver);
    Kentico13_PrizeBondsPage pbPage = new Kentico13_PrizeBondsPage(driver);
    Kentico13_Homepage homePage = new Kentico13_Homepage(driver);
    Kentico13_PbResults PbResults = new Kentico13_PbResults(driver);
    public final By NextButton = By.xpath("//a[@class='gtm-link-click m-pagination--next']");
    public final By PreviousButton = By.xpath("//a[@class='gtm-link-click m-pagination--prev']");
public final By PageNumberLocator = By.xpath("//*[@id=\"winners-table\"]/div[3]/div[1]/a[3]");
//    public final By TableHeader = By.xpath("//*[@id=\"group-table\"]/div[1]/div");


    @And("Validate the content for Prize Bond Draw section Results")
    public void validateTheContentForPrizeBondDrawSection() {
        logReportStepValidationStart("Prize Bonds Draw section validation Initiated");
        strCompareString(pbPage.getPBDrawSectionTitle(), extractExcelValue.get("expPBDrawSectionTitle"), "Section Title ");
        strCompareString(pbPage.getPBDrawTilesHdrLblsArr(), extractExcelValue.get("expPBDrawTilesHdrLblsArr"), "Tiles Header ");
        strCompareString(pbPage.getPBDrawTilesSummaryTextArr(), extractExcelValue.get("expPBDrawTilesSummaryTextArr"), "Tiles Summary Text ");
        strCompareString(pbPage.getPBDrawSeeDrawResultsBtnLbl(), extractExcelValue.get("getPBDrawSeeDrawResultsBtnLbl"), "See draw results button label ");
        logNavigation(pbPage.clickPbDrawTileRecentDrawResultsLink("Recent Draw Results Link","Prizes in this draw"),"page","PB Results");
    }


    @And("Validate Dropdown contents in Pb results page")
    public void validateTheContentForPrizeBondDropdownSection() {
        extractExcelValue = FrameworkConstants.getExpMapPbResultPg();
        this.pageName = "PBResults";
        logReportStepValidationStart("Pb Results section validation Initiated");
        logReportStepValidationStart("Criteria/Highlights section validation Initiated");
        strCompareString(PbResults.getDropdwonTitle(pageName),extractExcelValue.get("expPbResultsDropdownHeader"),"PB Results Dropdown Title ");
        strCompareString(PbResults.getListTitle(pageName),extractExcelValue.get("expPbResultsListHeader"),"PB Results List Title ");
        Contents();
        strCompareString(PbResults.getMapTitle(pageName),extractExcelValue.get("expPbResultsMapHeader"),"PB Results Map Title ");

    }

public void Contents(){
        PbResults.DropdownValues();
    WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='draw-date-select']"));
    Select dropdown = new Select(dropdownElement);
    List<WebElement> options = dropdown.getOptions();
    List<String> actualValues = new ArrayList<>();
    for (WebElement option : options) {
        actualValues.add(option.getText().trim());
    }
    logReportStepValidationStart("Pb Results Dropdown Values " + actualValues);
    Map();
}



public void PrizevalueDD(){
    PbResults.PrizeValueDropdownValues();
    WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='winners-prize-value']"));
    Select dropdown = new Select(dropdownElement);
    List<WebElement> options = dropdown.getOptions();
    List<String> actualValues = new ArrayList<>();
    for (WebElement option : options) {
        actualValues.add(option.getText().trim());
    }
    logReportStepValidationStart("Pb Results Prize value Dropdown Values " + actualValues);
}

    public void LocationDD(){
        PbResults.LocationDD();
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='winners-location']"));
        Select dropdown = new Select(dropdownElement);
        List<WebElement> options = dropdown.getOptions();
        List<String> actualValues = new ArrayList<>();
        for (WebElement option : options) {
            actualValues.add(option.getText().trim());
        }
        logReportStepValidationStart("Pb Results location Dropdown Values " + actualValues);
    }

    public void SortByDD(){
        PbResults.sortByDD();
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='winners-sort-by']"));
        Select dropdown = new Select(dropdownElement);
        List<WebElement> options = dropdown.getOptions();
        List<String> actualValues = new ArrayList<>();
        for (WebElement option : options) {
            actualValues.add(option.getText().trim());
        }
        logReportStepValidationStart("Pb Results SortBy Dropdown Values " + actualValues);
    }





    public void Map(){
        try {
            PbResults.Map();
        } catch (Exception e) {
            System.out.println("Not found");
        }
}

    @And("Click on List Button")
    public void List(){
        try {
            PbResults.List();
        } catch (Exception e) {
            System.out.println("Not found");
        }
    }


    @And("Validate the Winners in the draw Header")
    public void validateTheContentForPrizeBondSecondSection() {
        extractExcelValue = FrameworkConstants.getExpMapPbResultPg();
        this.pageName = "PBResults";
        logReportStepValidationStart("Pb Results section validation Initiated");
        logReportStepValidationStart("Criteria/Highlights section validation Initiated");
        strCompareString(PbResults.getFilterTitle(pageName),extractExcelValue.get("expPbResultsFilter"),"PB Results Filter Title ");
        strCompareString(PbResults.getPriceValueTitle(pageName),extractExcelValue.get("expPbResultsPrizeValue"),"PB Results Price Value Title ");
        strCompareString(PbResults.getLocationHeader(pageName),extractExcelValue.get("expPbResultsLocation"),"PB Results Location Title ");
        strCompareString(PbResults.getSortByHeader(pageName),extractExcelValue.get("expPbResultsSortBy"),"PB Results SortBy Title ");
        strCompareString(PbResults.getSearchHeader(pageName),extractExcelValue.get("expPbResultsSearchPB"),"PB Results Search Title ");
        PrizevalueDD();
        LocationDD();
        SortByDD();

    }

    @And("Navigate through all the buttons in Winner in draw")
    public void IterateContentForPrizeBondSecondSection() {
        PbResults.iteratePagesWithManualCounter(driver,NextButton,PreviousButton,20);
    }



    // Not able to use ; to separate the value in Excel . So took single xpath for all the values
    @Then("Validate the Table Header and contents in Pb Results")
    public void validateContentInSignInPanelSectionForSignInPage() {
        extractExcelValue = FrameworkConstants.getExpMapPbResultPg();
        this.pageName = "PBResults";
        HashMap<String, String> interestRatesRetMap=FrameworkConstants.getExpMasterMapIntRatesReturns().get(pageName);
        getListItemsComparison(PbResults(),PbResults.getPrizeTableHeaderListPbWinner(),"Table header");
        logReportStepValidationStart("Prize bond draw validation Initiated");

        PbResults.PBResultValues();

        logReportStepValidationEnd("Prize bond draw validation Completed");
    }

    public static List<String> PbResults(){
        List  <String> tempLoad=new ArrayList<>();
        tempLoad.add("Prize Value");
        tempLoad.add("Number of Prizes");
        tempLoad.add("Value of Prizes");

        return tempLoad;
    }

//    public List <String> getPrizeTableHeaderListPbWinner(){
//        List <String> tableHdrTemp=new ArrayList<>();
//        webUtil.waitUntilElementVisible(TableHeader,4);
//        List <WebElement> tableHdrTempElem=driver.findElements(TableHeader);
//        for(WebElement elemVar:tableHdrTempElem){
//            tableHdrTemp.add(elemVar.getText());
//        }
//        return tableHdrTemp;
//    }




//    @And("Validate the content for Prizes in this draw section on {string} Page")
//    public void validateTheContentForPrizesInThisDrawSection(String pageName) {
//        HashMap<String, String> interestRatesRetMap = FrameworkConstants.getExpMasterMapPrizesInDraw().get(pageName);
//        getListItemsComparison(prizesInTheDrawTableHdr(), individualProdPages.getInterestRatesTableHeaderList(), "Table header");
//        getListItemsComparison(getPrizesInTheDrawValue(interestRatesRetMap), PbResults.getPrizesInThisDrawValuesList(), "Table values");
////        individualProdPages.clickChevronShowYearlyReturns();
//        logReportStepValidationEnd("Interest Rates and Returns section validation completed");
//    }



    public static List<String> prizesInTheDrawTableHdr(){
        List  <String> tempLoad=new ArrayList<>();
        tempLoad.add("Prize Value");
        tempLoad.add("Number of Prizes");
        tempLoad.add("Value of Prizes");
        return tempLoad;
    }

    public static List<String> getPrizesInTheDrawValue(HashMap<String,String> masterMapPrizeInDraw){
        List <String> valuesPrizesInThisDraw = new ArrayList<>();
        for (int i=1;i<=masterMapPrizeInDraw.size();i++){
            valuesPrizesInThisDraw.add(masterMapPrizeInDraw.get("LineItem"+i));
        }
        return valuesPrizesInThisDraw;
    }







    @And("Validate the content for Buy Prize Bonds Section on {string} Page in PbWinner Map View")
    public void validateTheContentForBuyPrizeBondsSectionOnPagePbWinnerMapView(String pageName) {

        extractExcelValue = FrameworkConstants.getExpMasterMapIndProdPgs().get("Prize Bonds");
        logReportStepValidationStart("Buy Prize Bonds section validation Initiated");
        strCompareString(PbResults.getBuyPBHdr(), extractExcelValue.get("expBuyPBHdr"), "Buy Prize Bonds Second section Title ");
        strCompareString(homePage.getPBTileHdr(), extractExcelValue.get("expPBWinnerTileHdr"), "Buy Prize Bonds section Title ");
        strCompareString(pbPage.getPBTileSummaryText(), extractExcelValue.get("expPBTileSummaryText"), "Prize Bonds Tile summary text ");
        strCompareString(homePage.getPBGiftTileHdr(), extractExcelValue.get("expPBGiftTileHdr"), "Prize Bonds Gift Tile Title ");
        strCompareString(pbPage.getPBGiftTileSummaryText(), extractExcelValue.get("expPBGiftTileSummaryText"), "Prize Bonds Gift Tile summary text ");

        strCompareString(homePage.getPrizeBondsPBTileBuyNowBtnLbl(), extractExcelValue.get("expBuyPBTileBtnName"), "Buy now on PB Tile");
        strCompareString(homePage.getPrizeBondsPBGiftTileBuyNowBtnLbl(), extractExcelValue.get("expBuyPBGiftTileBtnName"), "Gift now on PB as a Gift Tile");

        homePage.clickPrizeBondsTileBuyGiftNowBtn("Buy now");
        strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Buy now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Buy Now Close button");
        homePage.clickPrizeBondsTileBuyGiftNowBtn("Gift now");
        strCompareString(individualProdPages.getBuyNowModalTitle(), extractExcelValue.get("expBuyNowModalHeader"), "Gift now modal header");
        individualProdPages.clickBuyNowModalCloseBtn("Gift now modal Close button");

        logReportStepValidationEnd("Buy Prize Bonds section validation Completed");
    }


}

