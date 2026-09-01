package com.cucumbercraft.POMPages.PreLoginPages;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Kentico13_PbResults  extends Kentico13_MasterPages {
    WebDriver driver;

    Kentico13_PrizeBondsResultsPage pbResultsPg;
    Kentico13_SignInPage signInPg;
    static Logger log = LogManager.getLogger(MasterStepDefs.class);

    public Kentico13_PbResults(WebDriver driver) {
        this.driver = driver;
        pbResultsPg = new Kentico13_PrizeBondsResultsPage(driver);
        signInPg = new Kentico13_SignInPage(driver);

    }

    private final By prizesInTheDrawTitle = By.xpath("//h1[@class='m03-title_text_button--title'][1]");

    private final By prizesInTheDrawTableHdr = By.xpath("//div[@id='group-table']//div[@class='th']");

    private final By masterTableRowElements=By.xpath("//div[@id='group-table-body']//div[contains(@class,'td')]");

    private final By DropdownHeader=By.xpath("//div[@class='large-4 large-pull-8 medium-6 medium-pull-6 columns']/h4");
private final By Options = By.xpath("//select[@id='draw-date-select']");
    private final By PVOptions = By.xpath("//select[@id='winners-prize-value']");
    private final By LocationOptions = By.xpath("//select[@id='winners-location']");
    private final By SortByOptions = By.xpath("//select[@id='winners-sort-by']");
    private final By MapPage = By.xpath("//li[@class='tab-trigger map']");

    private final By FirstPrizeValue = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[1]/div[1]");
    private final By FirstNoOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[1]/div[2]");
    private final By FirstValueOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[1]/div[3]");
    private final By SecPrizeValue = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[2]/div[1]");
    private final By SecNoOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[2]/div[2]");
    private final By SecValueOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[2]/div[3]");
    private final By ThirdPrizeValue = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[3]/div[1]");
    private final By ThirdNoOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[3]/div[2]");
    private final By ThirdValueOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[3]/div[3]");
    private final By FourPrizeValue = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[4]/div[1]");
    private final By FourNoOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[4]/div[2]");
    private final By FourValueOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[1]/div[4]/div[3]");
    private final By TotalPrizeValue = By.xpath("//*[@id=\"group-table-body\"]/div[2]/div[1]");
    private final By TotalNoOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[2]/div[2]");
    private final By TotalValueOfPrize = By.xpath("//*[@id=\"group-table-body\"]/div[2]/div[3]");


    private final By ValueName = By.className("mobile-th");
    private final By ListPage = By.xpath("//li[@class='tab-trigger list']");
    private final By getMapTitle = By.xpath("//section[2]//h1");
    private final By getListTitle = By.xpath("//section[1]/section[1]/div/div/div/h1");
    private final By SecondSectionHeader=By.xpath("//div[@class='m03-title_text_button--content']//h2");
public final By NextButton = By.xpath("//a[@class='gtm-link-click m-pagination--next']");
    public final By PreviousButton = By.xpath("//a[@class='gtm-link-click m-pagination--prev']");
private final By rowLocator = By.id("[id='group-table-body']");
    private final By FilterBy=By.xpath("//div[@class='medium-12 columns']//h5");
private final By PrizeValueHeader = By.xpath("//label[@for='winners-prize-value']");
    private final By LocationHeader = By.xpath("//label[@for='winners-location']");
    private final By SortByHeader = By.xpath("//label[@for='winners-sort-by']");
    private final By SearchHeader=By.xpath("//label[@for='m21-filter-value']");
    public final By PageNumberLocator = By.xpath("//*[@id=\"winners-table\"]/div[3]/div[1]/a[3]");
    public final By TableHeader = By.xpath("//*[@id=\"group-table\"]/div[1]/div");
    public final By TableValues = By.xpath("//div[@id='group-table']//div[@class='td']");
    public final By TableValuesNone = By.xpath("//div[@class='responsive-table']//p");

    private By masterTableRowElement (int interatorRow, int iteratorRowValue){
        String elemLocator=String.format("//*[@class='tablesaw tablesaw-stack']/tbody/tr[%s]/td[%s]",interatorRow,iteratorRowValue);
        return By.xpath(elemLocator);
    }




    public String getFilterTitle(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(FilterBy);
    }

    public String getDropdwonTitle(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(DropdownHeader);
    }

    public String getBuyPBHdr(){
        return getTextByLocator(SecondSectionHeader);
    }


    public String getMapTitle(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(getMapTitle);
    }

    public String getListTitle(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(getListTitle);
    }

    public String getPriceValueTitle(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(PrizeValueHeader);
    }

    public String getLocationHeader(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(LocationHeader);
    }

    public String getSortByHeader(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(SortByHeader);
    }


    public String getSearchHeader(String pageName) {
        this.pageName = pageName;
        return getTextByLocator(SearchHeader);
    }


    @SneakyThrows
    public void DropdownValues(){
        webUtil.click(Options);
        Thread.sleep(2000);
    }

    public void PrizeValueDropdownValues(){
        webUtil.click(PVOptions);
    }

    public void LocationDD(){
        webUtil.click(LocationOptions);
    }


    public void sortByDD(){
        webUtil.click(SortByOptions);
    }

    public String getFirstPrizeValue(){
        return getTextByLocator(FirstPrizeValue);
    }

    public String getFirstNoOfPrize(){
        return getTextByLocator(FirstNoOfPrize);
    }

    public String getValuePrize(){
        return getTextByLocator(FirstValueOfPrize);
    }


    public String getSecondPrizeValue(){
        return getTextByLocator(SecPrizeValue);
    }

    public String getSecondNoOfPrize(){
        return getTextByLocator(SecNoOfPrize);
    }

    public String getSecondValuePrize(){
        return getTextByLocator(SecValueOfPrize);
    }


    public String getThirdPrizeValue(){
        return getTextByLocator(ThirdPrizeValue);
    }

    public String getThirdNoOfPrize(){
        return getTextByLocator(ThirdNoOfPrize);
    }

    public String getThirdValuePrize(){
        return getTextByLocator(ThirdValueOfPrize);
    }


    public String getFourthPrizeValue(){
        return getTextByLocator(FourPrizeValue);
    }

    public String getFourthNoOfPrize(){
        return getTextByLocator(FourNoOfPrize);
    }

    public String getFourthValuePrize(){
        return getTextByLocator(FourValueOfPrize);
    }


    public String geTotalPrizeValue(){
        return getTextByLocator(TotalPrizeValue);
    }

    public String getTotalNoOfPrize(){
        return getTextByLocator(TotalNoOfPrize);
    }

    public String getTotalValuePrize(){
        return getTextByLocator(TotalValueOfPrize);
    }





















    public void Map(){
        webUtil.click(MapPage);
    }

    @SneakyThrows
    public void List(){
        Thread.sleep(2000);
        webUtil.click(ListPage);
    }

//    @SneakyThrows
////    public void IterateValues(){
//        while (true) {
//
//            try {
//
//                WebElement nextButton = webUtil.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(NextButton));
//                WebElement nextButtonPageNo = webUtil.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(NextButton));
//                System.out.println("Pass1");
//                if (nextButton.isDisplayed() && nextButton.isEnabled()) {
//                    nextButtonPageNo.click();
//                    Thread.sleep(1000);
//                    System.out.println("Pass2");
//                } else {
//
//                    System.out.println("Next button is disabled. Reached the last page.");
//                    break;
//                }
//            } catch (NoSuchElementException | TimeoutException e) {
//                System.out.println("Pass3: The 'Next' button is no longer present. Reached the end of the pages.");
//                break;
//            }
//
//        }
//        while (true) {
//            try {
//                System.out.println("Pass P");
//                WebElement prevButton = webUtil.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(PreviousButton));
////                WebElement prevButton = driver.findElement(PreviousButton);
//                WebElement prevButtonPage = webUtil.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(PreviousButton));
//                if (prevButton.isDisplayed() && prevButton.isEnabled()) {
//                    prevButtonPage.click();
//                } else {
//                    System.out.println("Previous button is disabled or not displayed. Reached the first page.");
//                    break;
//                }
//            } catch (Exception e) {
//                System.out.println("Previous button is not present. Reached the first page.");
//                break;
//            }
//        }
//    }


    public void iteratePagesWithManualCounter(WebDriver driver, By nextButtonLocator, By prevButtonLocator, int maxForwardClicks) {
        int clickCount = 1;

        while (clickCount < maxForwardClicks) {
            try {
                WebElement nextButton = webUtil.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(NextButton));
                WebElement nextButtonPageNo = webUtil.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(NextButton));

                if (nextButtonPageNo.isDisplayed() && nextButtonPageNo.isEnabled()) {
                    nextButton.click();
                    clickCount++;
                    System.out.println("Click Next" + (clickCount + 1));
                    Thread.sleep(1000);

                } else {
                    System.out.println("Next button disabled");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error clicking Next: " + e.getMessage());
                break;
            }
        }
            try {
                for (int i = 0; i < 18; i++) {
                    WebElement prevButton = driver.findElement(prevButtonLocator);

                    if (prevButton.isDisplayed() && prevButton.isEnabled()) {
                        prevButton.click();
                        System.out.println("Clicked Previous : " + (i + 1));
                        Thread.sleep(1000);
                    } else {
                        System.out.println("Previous button disabled : " + (i + 1));
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Click Previous: " + e.getMessage());
            }
        }


    public List <String> getPrizesInThisDrawValuesList () {
        List<String> tempTableValues = new ArrayList<>();
        webUtil.waitUntilElementVisible(masterTableRowElements, 4);
        List<WebElement> elemList = driver.findElements(masterTableRowElements);
        String actValueElem = null;
        String finalLoadValueRow = null;
        for (int iteratorRow = 1; iteratorRow <= elemList.size(); iteratorRow++) {
            actValueElem = null;
            finalLoadValueRow = null;
            for (int iteratorRowValue = 1; iteratorRowValue <= 4; iteratorRowValue++) {
                webUtil.waitUntilElementVisible(masterTableRowElement(iteratorRow, iteratorRowValue), 4);
                actValueElem = driver.findElement(masterTableRowElement(iteratorRow, iteratorRowValue)).getText();
                if (finalLoadValueRow == null) {
                    finalLoadValueRow = actValueElem;
                } else {
                    finalLoadValueRow = finalLoadValueRow + ";" + actValueElem;
                }
            }
            tempTableValues.add(finalLoadValueRow);
        }
        return tempTableValues;
    }



    public List <String> getPrizeTableHeaderListPbWinner(){
        List <String> tableHdrTemp=new ArrayList<>();
        webUtil.waitUntilElementVisible(TableHeader,4);
        List <WebElement> tableHdrTempElem=driver.findElements(TableHeader);
        for(WebElement elemVar:tableHdrTempElem){
            tableHdrTemp.add(elemVar.getText());
        }
        return tableHdrTemp;
    }




    @SneakyThrows
    public void PBResultValues() {
        List<WebElement> cells = driver.findElements(TableValues);
        for (WebElement cell : cells) {
            String label = cell.findElement(ValueName).getText();
            String value = cell.getText().replace(label, "").trim();
            log.info("Values " + value);
            ExtentCucumberAdapter.addTestStepLog("Values of the Pb Results " + value);
            if (value.isEmpty() && driver.findElement(TableValuesNone).isDisplayed()) {
                ExtentCucumberAdapter.addTestStepLog("0 prizes shown");
                Assert.fail("Validation : No prize value ");

            }
        }
    }





}


