package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
@Log4j2
public class Kentico13_SavingsCalculatorPage extends Kentico13_MasterPages{
    WebDriver driver;
    public Kentico13_SavingsCalculatorPage(WebDriver driver){
        this.driver=driver;
    }
    private final By savingsCalhdr= By.id("lblTitleText");
    private final By closeModalBtn=By.xpath("//*[@class='m22-calculator-wrapper']//a[@aria-label='Close Modal']");
    private final By tabLinkFixedTerm=By.id("tabLinkFixedTerm");
    private final By amountToInvestLabel=By.xpath("//*[@for='m22-amount-to-invest']");
    private final By calculateBtn=By.xpath("//*[@class='m22-input-group']/following-sibling::button");
    private final By pBPanelHdr=By.xpath("//*[@class='m22-xsell-title']/h3");
    private final By pBPanelSummaryTxt=By.xpath("//*[@class='m22-xsell-text']/p");
    private final By pBPanelFindOutMoreBtn=By.xpath("//*[@class='m22-xsell-button']/a");
    private final By calculatorTermsTextArr=By.xpath("//*[@class='m22-calculator-terms']/p");
    private final By tilesStatsArr=By.xpath("//*[@class='m22-result-text']/../div/following-sibling::div/ul");
    private final By tilesProdHdrArr=By.xpath("//*[@class='m22-result-text']/..//h2");
    private final By tilesProdHdrLinkArr=By.xpath("//*[@class='m22-result-text']/..//h2/parent::a");
    private final By tilesProdLearnMoreBtnArr=By.xpath("//*[@class='m22-result-text']/following-sibling::div/a");
    private final By tilesProdYearIssueLblArr=By.xpath("//*[@class='m22-result-text']/..//span/parent::div");
    private final By tilesProdBuyNowArr=By.xpath("//*[@class='m22-result-text']/following-sibling::div/button");
    private final By tilesProdResultsReturnsValueArr=By.xpath("//*[@class='m22-result-text']/..//p");

    public String getSavingsCalhdr() {
        return getTextByLocator(savingsCalhdr);
    }

    public String getCloseModalBtnLbl() { return getTextByLocator(closeModalBtn);
    }
    public void clickCloseModalBtn(String buttonName){webUtil.clickLogW(closeModalBtn,buttonName);}

    public String getTabLinkFixedTerm() {
        return getTextByLocator(tabLinkFixedTerm);
    }

    public String getCalculateBtnLbl() {
        return getTextByLocator(calculateBtn);
    }

    public void clickCalculateBtn(String buttonName){
        webUtil.clickLogW(calculateBtn,buttonName);
    }
    public String getAmountToInvestLabel() {
        return getTextByLocator(amountToInvestLabel);
    }

    public String getPBPanelHdr() {
        return getTextByLocator(pBPanelHdr);
    }

    public String getPBPanelSummaryTxt() {
        return getTextByLocator(pBPanelSummaryTxt);
    }

    public List<String> getPBPanelFindOutMoreBtn() {
        return getBtnClickAndNewTabLocator(pBPanelFindOutMoreBtn);
    }

    public void clickPBPanelFindOutMoreBtn (String buttonName, String nextPgHdr){
        clickBtnLinkCompareNxtPgBannerTitle(pBPanelFindOutMoreBtn,bannerTitle,buttonName,nextPgHdr);
    }

    public List <String> getCalculatorTermsTextListArr(){
        return getStrListItemsByLocator(calculatorTermsTextArr);
    }

//    public List <String> returnsFieldCalc(int amtValue){
//        webUtil.waitUntilElementVisible(tilesProdResultsReturnsValueArr,4);
//        String textLabel="Returns: €";
//        List <String> loadList=new ArrayList<>();
//        for (WebElement elemVar: driver.findElements(tilesProdResultsReturnsValueArr)){
//            elemVar.getText();
//        }
//
//    }


}
