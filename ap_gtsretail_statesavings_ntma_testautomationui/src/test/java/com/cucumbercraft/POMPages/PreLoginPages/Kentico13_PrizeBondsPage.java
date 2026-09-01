package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
@Log4j2
public class Kentico13_PrizeBondsPage extends Kentico13_MasterPages{
    WebDriver driver;

    Kentico13_PrizeBondsResultsPage pbResultsPg;

    Kentico13_SignInPage signInPg;
    public Kentico13_PrizeBondsPage (WebDriver driver){
        this.driver=driver;
        pbResultsPg=new Kentico13_PrizeBondsResultsPage(driver);
        signInPg=new Kentico13_SignInPage(driver);
    }
//    String pageName=null;

    private final By bannerBtn2= By.xpath("//*[@class='m01-banner-buttons']/a[2]");
    private final By secondaryBannerText=By.xpath("//*[@class='m03-title_text_button--content']/p");
    private final By secondaryBannerBtn=By.xpath("//*[@class='m03-title_text_button--content']/following-sibling::a");
    private final By bannerDrawAmount=By.xpath("//*[@class='m16-draw_details--prize text-2']");
    private final By bannerDrawDateLeft=By.xpath("//*[@class='m16-draw_details--draw-date date']");
    private final By bannerDrawDateTextRight=By.xpath("//*[@class='text-2']");
    private final By bannerDrawDateRight=By.xpath("//*[@class='m16-draw_details--next-draw date']");
    private final By highlightsHdr=By.xpath("//h2[@class='m03-title_text_button--title']");
    private final By pbDrawHdr=By.xpath("//*[text()='Prize Bond Draw']");
    private final By pbDrawHdrLinkRecentDrawResults=By.xpath("//*[text()='Recent Draw Results']");
    private final By pbDrawHdrLinkManageYourPB=By.xpath("//*[text()='Manage your Prize Bonds']");
    private final By pbDrawHdrLbl=By.xpath("//*[@class='m06-2col_cards--block']//h2");
    private final By seeDrawResultsBtn=By.xpath("//*[text()='See draw results']");
    private final By pbDrawSummaryTxt=By.xpath("//p[@class='intro']/../p[2]");
    private final By pbDrawRegisterBtn=By.xpath("//*[@class='m06-2col_cards--block']//*[text()='Register']");
    private final By pbDrawSignInBtn=By.xpath("//*[@class='m06-2col_cards--block']//*[text()='Register']//following-sibling::a");
    private final By checkMyNumbersHdr=By.xpath("//*[text()='Check My Numbers']");
    private final By checkMyNumSummaryText=By.xpath("//*[@class='tab-content']/p");
    private final By checkMyNumFirstPBNumHdrLbl=By.xpath("//label[@for='firstBondNumber']");
    private final By checkMyNumLastPBNumHdrLbl=By.xpath("//label[@for='lastBondNumber']");
    private final By checkMyNumFirstPBTxtInput=By.xpath("//label[@for='firstBondNumber']/following-sibling::input");
    private final By checkMyNumLastPBTxtInput=By.xpath("//label[@for='lastBondNumber']/following-sibling::input");
    private final By checkMyNumBtn=By.xpath("//*[@class='tab-content']//button");
    private final By chevronHowDoIFindMyPBNum=By.xpath("//div[@class='st-container']/a");
    private final By chevronExpandHdr=By.xpath("//div[@class='m17-draw_search--info']//h5");
    private final By chevronExpandSummaryText=By.xpath("//div[@class='m17-draw_search--info']//p");
    private final By buyPBHdr=By.xpath("//*[text()='Buy Prize Bonds']");
    private final By pbTileSummaryText=By.xpath("//*[@class='m06-2col_cards--block-item'][1]//h3//following-sibling::p");
    private final By pbGiftTileSummaryText=By.xpath("//*[@class='m06-2col_cards--block-item'][2]//h3//following-sibling::p");
    private final By BannerSec=By.className("m16-draw_details");
    private final By BannerValue=By.cssSelector(".m16-draw_details span");


    public String getBannerBtn2Lbl() {
        return getTextByLocator(bannerBtn2);
    }

    public String getBannerBtn1Lbl(){
        return getTextByLocator(bannerBtn);
    }

    public List <String> getBannerBtn2ClickList(){
        return getBtnClickAndNewTabLocator(bannerBtn2);
    }

    public void clickBuyNowBannerBtn2(String messageLog) {
        try {
            webUtil.clickLogW(bannerBtn2, messageLog);
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
    }

    public String getSecondaryBannerText(){
        return getTextByLocator(secondaryBannerText);
    }

    public boolean clickSecondaryBannerBtn(String btnName,String nextPgHdr){
        return clickBtnLinkCompareNxtPgBannerTitle(secondaryBannerBtn,hdrTitleHelpArticles,btnName,nextPgHdr);
    }

    public List<String> getSecondaryBannerBtnList(){
        return getBtnClickAndNewTabLocator(secondaryBannerBtn);
    }

    public String navigateBackPg()  {
        webUtil.navigateBackFunc();
        return getTextByLocator(bannerTitle);
    }

    public String getBannerDrawPrizeAmount(){
        return getTextByLocator(bannerDrawAmount);
    }

    public String getBannerDrawPrizeDateTextLeft(){
        return getTextByLocator(bannerDrawDateLeft);
    }

    public String getBannerDrawTextRight(){
        return getTextByLocator(bannerDrawDateTextRight);
    }

    public String getBannerDrawPrizeDateTextRight(){
        return getTextByLocator(bannerDrawDateRight);
    }

    public String getHighlightsSectionTitle(){
        return getTextByLocator(highlightsHdr);
    }

    public String getPBDrawSectionTitle(){
        return getTextByLocator(pbDrawHdr);
    }

    public String getPBDrawTilesHdrLblsArr(){
        return getPBDrawTilesValues(pbDrawHdrLbl);
    }

    public String getPBDrawTilesSummaryTextArr(){
        return getPBDrawTilesValues(pbDrawSummaryTxt);
    }
    private String getPBDrawTilesValues(By elemVarList){
        webUtil.waitUntilElementVisible(elemVarList,4);
        String strTemp=null;
        List<WebElement> elemList=driver.findElements(elemVarList);
        strTemp=String.format("%s;%s",elemList.get(0).getText(),elemList.get(1).getText());
        return strTemp;
    }

    public List<String> getPBDrawHdrLinkRecentDrawResultsListLink(){
        webUtil.waitUntilElementVisible(pbDrawHdrLinkRecentDrawResults,4);
        return getBtnClickAndNewTabLocator(pbDrawHdrLinkRecentDrawResults);
    }

    public List<String> pbDrawHdrLinkMngYourPBListLink(){
        webUtil.waitUntilElementVisible(pbDrawHdrLinkManageYourPB,4);
        return getBtnClickAndNewTabLocator(pbDrawHdrLinkManageYourPB);
    }

    public String getPBDrawSeeDrawResultsBtnLbl(){
        return getTextByLocator(seeDrawResultsBtn);
    }

    public String getPBDrawRegisterBtnLbl(){
        return getTextByLocator(pbDrawRegisterBtn);
    }

    public String getPBDrawSignInBtnLbl(){
        return getTextByLocator(pbDrawSignInBtn);
    }

    public boolean clickPbDrawTileRecentDrawResultsLink(String btnName,String nextPgHdr){
        return clickBtnLinkCompareNxtPgBannerTitle(pbDrawHdrLinkRecentDrawResults,pbResultsPg.resultsPgHdr,btnName,nextPgHdr);
    }

    public boolean clickPbDrawTileMngYourPBLink(String btnName,String nextPgHdr){
//        return clickBtnLinkCompareNxtPgBannerTitle(pbDrawHdrLinkManageYourPB,signInPg.bannerTitleSignIn,btnName,nextPgHdr);
                return clickBtnLinkCompareNxtPgBannerTitle(pbDrawHdrLinkManageYourPB,bannerTitleSignIn,btnName,nextPgHdr);
    }

    public boolean clickPbDrawTileSeeDrawResultsBtn(String btnName,String nextPgHdr){
        return clickBtnLinkCompareNxtPgBannerTitle(seeDrawResultsBtn,pbResultsPg.resultsPgHdr,btnName,nextPgHdr);
    }

    public boolean clickPbDrawTileRegisterBtn(String btnName,String nextPgHdr){
        return clickBtnLinkCompareNxtPgBannerTitle(pbDrawRegisterBtn,bannerTitle,btnName,nextPgHdr);
    }

    public boolean clickPbDrawTileSignInBtn(String btnName,String nextPgHdr){
        return clickBtnLinkCompareNxtPgBannerTitle(pbDrawSignInBtn,bannerTitleSignIn,btnName,nextPgHdr);
    }

//Check My Numbers Section

    public String getCheckMyNumbersHdr(){
        return getTextByLocator(checkMyNumbersHdr);
    }
    public String getCheckMyNumSummaryText(){
        return getTextByLocator(checkMyNumSummaryText);
    }

    public String getCheckMyNumFirstPBNumHdrLbl(){
        return getTextByLocator(checkMyNumFirstPBNumHdrLbl);
    }

    public String getCheckMyNumLastPBNumHdrLbl(){
        return getTextByLocator(checkMyNumLastPBNumHdrLbl);
    }

    private void enterFirstBondNumber(String firstBondNumberValue){
        webUtil.waitUntilElementVisible(checkMyNumFirstPBTxtInput,4);
        driver.findElement(checkMyNumFirstPBTxtInput).sendKeys(firstBondNumberValue);
    }

    private void enterLastBondNumber(String lastBondNumberValue){
        webUtil.waitUntilElementVisible(checkMyNumLastPBTxtInput,4);
        driver.findElement(checkMyNumLastPBTxtInput).sendKeys(lastBondNumberValue);
    }

    public String searchFirstBondNumber(String firstBondNumberValue){
        enterFirstBondNumber(firstBondNumberValue);
        webUtil.clickLogW(checkMyNumBtn,"Check my numbers");
        return getTextByLocator(bannerTitle);
    }

    public String searchLastBondNumber(String lastBondNumberValue){
        enterFirstBondNumber(lastBondNumberValue);
        webUtil.clickLogW(checkMyNumBtn,"Check my numbers");
        return getTextByLocator(bannerTitle);
    }

    public String getCheckMyNumBtnLbl(){
        return getTextByLocator(checkMyNumBtn);
    }

    public String getChevronHowDoIFindMyPBNumText(){
        return getTextByLocator(chevronHowDoIFindMyPBNum);
    }

    public void clickChevronHowDoIFindMyPBNum(String chevronName){
        webUtil.clickLogW(chevronHowDoIFindMyPBNum,chevronName);
    }

    public String getChevronExpandHdr(){
        return getTextByLocator(chevronExpandHdr);
    }

    public String getChevronExpandSummaryText(){
        return getTextByLocator(chevronExpandSummaryText);
    }

    //Buy Prize Bonds Section

    public String getBuyPBHdr(){
        return getTextByLocator(buyPBHdr);
    }

    public String getPBTileSummaryText(){
        return getTextByLocator(pbTileSummaryText);
    }

    public String getPBGiftTileSummaryText(){
        return getTextByLocator(pbGiftTileSummaryText);
    }

    public void PBResultValues() {
        List<WebElement> banner = driver.findElements(BannerSec);
        if (banner.isEmpty()) {
            Assert.fail("Purple banner section is not there in the UI");
        }

        List<WebElement> cells = driver.findElements(BannerValue);
        for (WebElement cell : cells) {
            String value = cell.getText().trim();
            log.info("Values: " + value);
            ExtentCucumberAdapter.addTestStepLog("Values of the Results: " + value);

            if (value.isEmpty()) {
                ExtentCucumberAdapter.addTestStepLog("Content is missing in banner");
                Assert.fail("Empty value is in results banner.");
            }
        }

    }

}
