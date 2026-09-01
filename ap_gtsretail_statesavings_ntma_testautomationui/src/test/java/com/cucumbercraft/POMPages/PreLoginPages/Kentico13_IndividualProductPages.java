package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Kentico13_IndividualProductPages extends Kentico13_MasterPages {
    private WebDriver driver;
    Kentico13_ComponentHeader comp_Header;
    public Kentico13_IndividualProductPages (WebDriver driver){
        this.driver=driver;
        comp_Header= new Kentico13_ComponentHeader(driver);
    }

    private final By intRatesReturnsTitle = By.xpath("//h2[text()='Interest Rates & Returns']");
    private final By intRatesReturnsText = By.xpath("//h2[text()='Interest Rates & Returns']/ancestor::section/div[2]");
    private final By chevronShowYearlyReturns = By.xpath("//a[normalize-space()='Show yearly returns']");
    private final By tableHdrLblsList = By.xpath("//table[@class='tablesaw tablesaw-stack']//th");
    private final By tableHdrLblsListPBWinner = By.xpath("//div[contains(@class, 'th')]");
    private final By masterTableRowElements=By.xpath("//*[@class='tablesaw tablesaw-stack']/tbody/tr");
    private final By scHeader = By.id("tabLinkFixedTerm");
    private final By scAmountLbl = By.xpath("//div[@class='m22-input-group']/label");
    private final By scCalculatorBtn = By.xpath("//button[@class='button button--primary js-calculateFixedRateReturn']");
    private final By scFindOutMore = By.xpath("//div[@class='m22-xsell-button']/a");
    private final By pbDesc = By.xpath("//div[@class='m22-xsell-text']/p");
    private final By pbHdr = By.xpath("//div[@class='m22-xsell-title']/h3");
    private final By fixedTermHeadersListArr = By.xpath("//h2[@class='m08-product_listing_cards--title']");
    private final By yearsAndIssues = By.xpath("//h2[@class='m08-product_listing_cards--title']//ancestor::a//preceding-sibling::div//span");
    private final By enterAmount = By.id("m22-amount-to-invest");
    private final By learnMoreLink = By.xpath("//a[@class='button button--secondary gtm-cta' and @gtm-card-title='Product name']");
    private final By benefitPercentage = By.xpath("//h2[@class='m08-product_listing_cards--title']//ancestor::a/parent::div/following-sibling::div//li");
    private final By resultValues = By.xpath("//p[@class='m22-result-text']/span");
    private final By buyNow = By.xpath("//button[contains(@data-url-sign-in, '/your-savings/login?id=')][1]");


    private By masterTableRowElement (int interatorRow, int iteratorRowValue){
        String elemLocator=String.format("//*[@class='tablesaw tablesaw-stack']/tbody/tr[%s]/td[%s]",interatorRow,iteratorRowValue);
        return By.xpath(elemLocator);
    }

    public String getBannerTitle(String pageName){
        this.pageName=pageName;
        return getTextByLocator(bannerTitle);
    }

    public String getBannerText(){
        return getTextByLocator(bannerText);
    }

    public String getBannerBtnLbl(){
        return getTextByLocator(bannerBtn);
    }

    public List<String> actStatsListContent(){
        webUtil.waitUntilElementVisible(bannerStatsListItemArr,4);
        return getStrListItemsByLocator(bannerStatsListItemArr);
    }

    public String getBannerYearIssueNumber(){
        return getTextByLocator(bannerYearIssueNumber);
    }

    public String getHighlightsSectionTitle(){
        return getTextByLocator(highlightsSectionTitle);
    }

    public void clickBuyNowBannerBtn(String messageLog) {
        try {
            webUtil.clickLogW(bannerBtn, messageLog);
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }
    public void clickBuyNowModalCloseBtn(String messageLog){
        try {
            webUtil.clickLogW(btnBuyNowCloseModal, messageLog);
        }catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }

    public String getBuyNowModalTitle(){
        webUtil.waitUntilElementVisible(buyNowModal,4);
        String modalTitle=null;
        try {
            modalTitle=driver.findElement(buyNowModal).getText();;
        }
        catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
        return modalTitle;
    }

    public List<String> actHighlightsRightListContent(){
        webUtil.waitUntilElementVisible(highlightsRightBulletPtArr,4);
        return getStrListItemsByLocator(highlightsRightBulletPtArr);
    }

    public List<String> actHighlightsLeftListContent(){
        webUtil.waitUntilElementVisible(highlightsLeftBulletPtArr,4);
        return getStrListItemsByLocator(highlightsLeftBulletPtArr);
    }

    public List <String> getHighlightsLinksClickCheckNavigation(){
        webUtil.waitUntilElementVisible(highlightsHyperlinksArr,4);
        return getStrListItemsAttributeByLocator(highlightsHyperlinksArr,"href");
    }

    public List <String> getHighlightsLinksClickNewTabValidation(){
        webUtil.waitUntilElementVisible(highlightsHyperlinksArr,4);
        return getStrListItemsAttributeByLocator(highlightsHyperlinksArr,"target");
    }
    public String getSecondaryBannerTitle(){
        return getTextByLocator(banner2Title);
    }
    public String getSecondaryBannerBtnLbl(){
        return getTextByLocator(banner2Btn);
    }

    public void clickSecondaryBannerBuyNowBtn(String messageLog) {
        try {
            webUtil.clickLogW(banner2Btn, messageLog);
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }

    public String getFAQHdr(){
        return getTextByLocator(faqHdr);
    }

    public List <String> getFAQLinksClickCheckNavigation(){
        return getStrListItemsAttributeByLocator(faqListArr,"href");
    }

    public List <String> getFAQLinksClickNewTabValidation(){
        return getStrListItemsAttributeByLocator(faqListArr,"target");
    }
    public List<String> actFAQListContent(){
        return getStrListItemsByLocator(faqListArr);
    }

    public List<String> actFormsAndDownloadsListContent(){return getStrListItemsByLocator(formsAndDownloadsListArr);
    }

    public String getFormsAndDownloadsHdr(){
        return getTextByLocator(formsAndDownloadsHdr);
    }

    public List <String> getFormsAndDownloadsLinksClickCheckNavigation(){
        return getStrListItemsAttributeByLocator(formsAndDownloadsListArr,"href");
    }
    public List <String> getFormsAndDownloadsLinksClickNewTabValidation(){
        return getStrListItemsAttributeByLocator(formsAndDownloadsListArr,"target");
    }

    public String getCarouselHdr(){
        return getTextByLocator(carouselHdr);
    }

    public List <String> getCarouselViewProdBtnDetails(){
        return getBtnClickAndNewTabLocator(carouselSectionViewAllProdBtn);
    }

    public List <String> getFindOutMoreBtnDetails(){
        return getBtnClickAndNewTabLocator(highlightsBtn);
    }

    public List <String> getFAQSectionBtnDetails(){
        return getBtnClickAndNewTabLocator(faqSectionBtn);
    }

    public String getSavingsCalculatorIconLblPage(){
        return getSavingsCalculatorIconLbl();
    }

    public String clickSavingsCalculatorIcon(){
        return clickSavingsCalculatorIcon(pageName);
    }

    public String clickSavingsCalculatorCloseBtn(){
        return clickSavingsCalculatorCloseBtn(pageName);
    }


    public  String getSavingsCalculatorIconLbl(){
        return getTextByLocator(calculatorPgIconLbl);
    }

    public String clickSavingsCalculatorIcon(String pageName){
        webUtil.clickLogW(calculatorPgIconLbl,"Savings Calculator on Page"+pageName);
        return getTextByLocator(calculatorPgHdr);
    }
    public String clickSavingsCalculatorCloseBtn(String pageName){
        webUtil.clickLogW(calculatorPgCloseBtn,"Savings Calculator on Page"+pageName);
        return getTextByLocator(bannerTitle);
    }

//Interest rates and returns section

    public String getTitleInterestRatesAndReturnsSection(){
        return getTextByLocator(intRatesReturnsTitle);
    }

    public String getTSummaryTextInterestRatesAndReturnsSection(){
        return getTextByLocator(intRatesReturnsText);
    }

    public void clickChevronShowYearlyReturns(){
        webUtil.clickLogW(chevronShowYearlyReturns,"Show Yearly Returns Chevron");
    }

    public List <String> getInterestRateTableValuesList () {
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

    public List <String> getInterestRatesTableHeaderList(){
        List <String> tableHdrTemp=new ArrayList<>();
        webUtil.waitUntilElementVisible(tableHdrLblsList,4);
        List <WebElement> tableHdrTempElem=driver.findElements(tableHdrLblsList);
        for(WebElement elemVar:tableHdrTempElem){
            tableHdrTemp.add(elemVar.getText());
        }
        return tableHdrTemp;
    }

    public List <String> getInterestRatesTableHeaderListPbWinner(){
        List <String> tableHdrTemp=new ArrayList<>();
        webUtil.waitUntilElementVisible(tableHdrLblsListPBWinner,4);
        List <WebElement> tableHdrTempElem=driver.findElements(tableHdrLblsListPBWinner);
        for(WebElement elemVar:tableHdrTempElem){
            tableHdrTemp.add(elemVar.getText());
        }
        return tableHdrTemp;
    }



    public List <String> getExpBannerBtnClickList(){
        return getBtnClickAndNewTabLocator(bannerBtn);
    }

    public List <String> getExpBanner2BtnClickList(){
        return getBtnClickAndNewTabLocator(banner2Btn);
    }

    public String getScSubHdr(){
        return getTextByLocator(scHeader);
    }
    public String getSCAmountToInvestLbl(){
        return getTextByLocator(scAmountLbl);
    }
    public String getSCCalculatorBtn() {
        WebElement amount= driver.findElement(enterAmount);
        amount.sendKeys("50");
        WebElement clickCalculateBtn= driver.findElement(scCalculatorBtn);
        clickCalculateBtn.click();
        return getTextByLocator(scCalculatorBtn);
    }
    public List<String> getLearnMoreLink(){
        return  getBtnClickAndNewTabLocator(learnMoreLink);
    }

    public List<String> getSCFindOutMore(){
        return  getBtnClickAndNewTabLocator(scFindOutMore);
    }
    public String getPBDesc(){
        return  getTextByLocator(pbDesc);
    }
    public String getSCPBHdr(){
        return  getTextByLocator(pbHdr);
    }
    public List<String> expectedYearsAndIssues(){


        List<String> expected = Arrays.asList("Issue 1", "2021 - Issue 2", "2022 - Issue 3");
        List<String> actual = getYearsAndIssues();

        if (expected.equals(actual)) {
            System.out.println("Expected and actual values are the same.");
        } else {
            System.out.println("Expected and actual values differ.");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
        return  getTextValuesByLocator(yearsAndIssues);
    }

    public List<String> getSectionHeadersList(){
        return getTextValuesByLocator(fixedTermHeadersListArr);
    }
    public String getSCDesc(){

        List<WebElement> allContents = driver.findElements(By.xpath("//div[@class='m22-calculator-terms']//p"));
        String scDesc = allContents.stream()
                .map(e -> e.getText().replaceAll("\\s+", " ").trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
        return scDesc;

    }

    public List<String> getYearsAndIssues(){
        return  getTextValuesByLocator(yearsAndIssues);
    }

    public List<String> getPercentageBenefit(){
        return  getTextValuesByLocator(benefitPercentage);
    }

    public List<String> getResultValues(){
        return  getTextValuesByLocator(resultValues);
    }

    public List<String> clickBuyNowBtnInSavingsCalculator(String messageLog) {
        List<String> modalTitleList = new ArrayList<>();
        try {
            List<WebElement> buyNowButtons = driver.findElements(buyNow);

            for (WebElement button : buyNowButtons) {
                String buttonLabel = button.getText().trim();
                modalTitleList.add(buttonLabel);


                // Click Buy Now button
                webUtil.waitUntilElementVisible(buyNow, 4);
                webUtil.clickLogW(buyNow, messageLog);

                // Wait for modal
                getBuyNowModalTitle();

                // Close modal
                webUtil.waitUntilElementVisible(btnBuyNowCloseModal, 4);
                webUtil.clickLogW(btnBuyNowCloseModal, "Closing modal");


                clickSavingsCalculatorIcon();
                Thread.sleep(3000);


            }
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
        return modalTitleList;
    }



}
