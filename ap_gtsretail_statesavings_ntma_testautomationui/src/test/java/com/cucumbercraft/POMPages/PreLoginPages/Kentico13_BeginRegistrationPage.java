package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Kentico13_BeginRegistrationPage extends Kentico13_MasterPages{
    private WebDriver driver;

    public Kentico13_BeginRegistrationPage (WebDriver driver){
        this.driver=driver;
    }

    private final By benefitsOfRegHdr=By.xpath("//h2[@class='m03-title_text_button--title']");
    private final By benefitsOfRegHighlightsListItem(int iterator){
        String tempPathFormer=String.format("%s%d%s","//div[@class='m04-2col_text--block-item'][",iterator,"]/ul/li");
        return By.xpath(tempPathFormer);
    }
    private final By ssoViewMngProdHdr=By.xpath("//section[@class='m39-supported-products']//h4");
    private final By rowManageProduct=By.xpath("//div[@class='m39-supported-products__table-wrapper']//tr");
    private String rowMngPrdFunc(int i, int j){
        return "//div[@class='m39-supported-products__table-wrapper']//tr["+i+"]/td["+j+"]";
    }
    private final By ssoViewMngProdText=By.xpath("//section[@class='m39-supported-products']//p");
    private final By ssoViewMngProdTableHdrCol1=By.xpath("//div[@class='m39-supported-products__table-wrapper']//tr/th[1]");
    private final By ssoViewMngProdTableHdrCol2=By.xpath("//div[@class='m39-supported-products__table-wrapper']//tr/th[2]");
    private final By whoCanRegisterSSOHdrs=By.xpath("//div[@class='m37-list-button__content']/h4");
    private final By whoCanRegisterSSOBulletListItems=By.xpath("//div[@class='m37-list-button__content']/ul/li");
    private final By whoCanRegisterSSOPara=By.xpath("//ul[@class='m37-list-button__list']/following-sibling::p");
    private final By whoCanRegSSOLinks=By.xpath("//div[@class='m37-list-button__content']/ul/li//a");
    private final By videoHowToRegSSOHdr=By.xpath("//*[@class='m37-list-button__content']//h5");
    private final By videoSectionBeginRegBtn=By.xpath("//*[@class='m37-list-button__content']//a[contains(@class,'button')]");
    private final By helpSupportHdr=By.xpath("//h1[@class='m14-help_content--title']");
    private final By termsAndConditionsHdr=By.xpath("//h1[@class='m03-title_text_button--title']");
    private final By ssoEndtext=By.xpath("//p[2]");

    public List<String> getMngProdTableList(){
        String rowCol1=null,rowCol2=null,conLoad=null;
        List <String> listProdMngTemp=new ArrayList<>();
        listProdMngTemp.add((driver.findElement(ssoViewMngProdTableHdrCol1).getText().concat(":").concat(driver.findElement(ssoViewMngProdTableHdrCol2).getText())));
        webUtil.waitUntilElementVisible(rowManageProduct,4);
        for (int i=1;i<driver.findElements(rowManageProduct).size();i++){
            rowCol1=driver.findElement(By.xpath(rowMngPrdFunc((i),1))).getText();
            rowCol2=driver.findElement(By.xpath(rowMngPrdFunc((i),2))).getText();
            conLoad=rowCol1+":"+rowCol2;
            listProdMngTemp.add(conLoad);
            conLoad=null;
            rowCol1=null;
            rowCol2=null;
        }
        return listProdMngTemp;
    }

    public String getBannerTitle(){
        return getTextByLocator(bannerTitle);
    }

    public String getBannerText(){
        return getTextByLocator(bannerText);
    }

    public boolean clickBannerBtn(String buttonName, String nextPgHeader){
        boolean valid=false;
        webUtil.waitUntilElementToBeClickable(bannerBtn,4);
        webUtil.clickLogW(bannerBtn,buttonName);
        webUtil.waitUntilElementVisible(personalDetailRegPgTitle,4);
        String dataLoadTemo=driver.findElement(personalDetailRegPgTitle).getText();
        if(dataLoadTemo.equalsIgnoreCase(nextPgHeader)){
            log.info("Banner button Validation True");
            valid=true;
        }
        else{
            log.error("Banner button Validation False");
            valid=false;
        }
        return valid;
    }

    public List<String> getBannerBtnListDetails(){
        return getBtnClickAndNewTabLocator(bannerBtn);
    }

    public String getBenefitsOfRegHdr(){
        return getTextByLocator(benefitsOfRegHdr);
    }

    public boolean navigateBackPg(String titleBeginReg)  {
        webUtil.navigateBackFunc();
        webUtil.waitUntilElementVisible(bannerTitle,4);
        return (driver.findElement(bannerTitle).getText()).equalsIgnoreCase(titleBeginReg);
    }

    private List <String> getHighlightsArr(By locatorValue){
        webUtil.waitUntilElementVisible(locatorValue,4);
        List<String> highlightLoadList=new ArrayList<>();
        for (WebElement elemVar: driver.findElements(locatorValue)){
            highlightLoadList.add(elemVar.getText());
        }
        return highlightLoadList;
    }

    public List <String> getHighlightsLeftBulletPtArr(){
        return getHighlightsArr(benefitsOfRegHighlightsListItem(1));
    }

    public List <String> getHighlightsRightBulletPtArr(){
        return getHighlightsArr(benefitsOfRegHighlightsListItem(2));
    }

    public String getSSOViewMngProdHdr(){
        return getTextByLocator(ssoViewMngProdHdr);
    }

    public String getSSOViewMngProdText(){
        return getTextByLocator(ssoViewMngProdText);
    }

    public List <String> getWhoCanRegisterSSOHdrsList(){
        return getHighlightsArr(whoCanRegisterSSOHdrs);
    }

    public List <String> getHighlightsLeftBulletPtArrBOR(){
        return getHighlightsArr(benefitsOfRegHighlightsListItemLeftBOR(1));
    }

    public List <String> getHighlightsRightBulletPtArrBOR(){
        return getHighlightsArr(benefitsOfRegHighlightsListItemRightBOR(1));
    }

    private final By benefitsOfRegHighlightsListItemRightBOR(int iterator){
        String tempPathFormer=String.format("%s%d%s","(//div[@class='m04-2col_text--block-item'])[4][",iterator,"]/ul/li");
        return By.xpath(tempPathFormer);
    }

    private final By benefitsOfRegHighlightsListItemLeftBOR(int iterator){
        String tempPathFormer=String.format("%s%d%s","(//div[@class='m04-2col_text--block-item'])[3][",iterator,"]/ul/li");
        return By.xpath(tempPathFormer);
    }

    public String getSSOViewEndText(){
        return getTextByLocator(ssoEndtext);
    }

    public List <String> getWhoCanRegisterSSOBulletListItems(){
        return getHighlightsArr(whoCanRegisterSSOBulletListItems);
    }

    public List<String> getWhoCanRegisterSSOPara(){
        webUtil.waitUntilElementVisible(whoCanRegisterSSOPara,4);
        List<String> listLoadSSOPara=new ArrayList<>();
        String tempItem=null;
        for(int i=0;i<2;i++){
            tempItem=driver.findElements(whoCanRegisterSSOPara).get(i).getText();
            listLoadSSOPara.add(tempItem);
        }
        return listLoadSSOPara;
    }

    public List <String> getStateSavingsCustomerNumberLinkDetails(){
        return getBtnClickAndNewTabLocator(whoCanRegSSOLinks);
    }

    public List <String> getLinkNameListDetails(String linkName){
        return getBtnClickAndNewTabLocator(navigationItem(linkName));
    }

    public String clickStateSavingsCustomerNumberLink(String linkName){
        webUtil.clickLogW(whoCanRegSSOLinks,linkName);
        return getTextByLocator(helpSupportHdr);
    }

    public String clickTermsAndConditionsLink(String linkName){
        webUtil.clickLogW(navigationItem(linkName),linkName);
        return getTextByLocator(termsAndConditionsHdr);
    }

    public String clickSeeProdManagedOnlineLink(String linkName){
        webUtil.clickLogW(navigationItem(linkName),linkName);
        return getTextByLocator(helpSupportHdr);
    }

    public String getVideoHowToRegSSOHdr(){
        return getTextByLocator(videoHowToRegSSOHdr);
    }

    public List <String> getvideoSectionBeginRegBtnListDetails(){
        return getBtnClickAndNewTabLocator(videoSectionBeginRegBtn);
    }

    public boolean clickVideoSectionBeginRegBtn(String buttonName, String nextPgHeader){
        webUtil.waitUntilElementToBeClickable(videoSectionBeginRegBtn,4);
        webUtil.clickLogW(videoSectionBeginRegBtn,buttonName);
        webUtil.waitUntilElementVisible(personalDetailRegPgTitle,4);
        return (driver.findElement(personalDetailRegPgTitle).getText()).equalsIgnoreCase(nextPgHeader);
    }



}
