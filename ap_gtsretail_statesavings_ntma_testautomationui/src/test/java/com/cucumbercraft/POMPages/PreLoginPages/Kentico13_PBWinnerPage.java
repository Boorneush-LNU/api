package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.framework.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class Kentico13_PBWinnerPage extends Kentico13_MasterPages{
    WebDriver driver;

    Kentico13_PrizeBondsResultsPage pbResultsPg;
    Kentico13_SignInPage signInPg;
    public Kentico13_PBWinnerPage (WebDriver driver){
        this.driver=driver;
        pbResultsPg=new Kentico13_PrizeBondsResultsPage(driver);
        signInPg=new Kentico13_SignInPage(driver);
    }

    private final By EnterBond = By.id("firstBondNumber");
    private final By checkMyNumBtn=By.xpath("//button[@class='button button--secondary button--alt']");
    private final By Search=By.xpath("//a[@class='button button--secondary button--alt gtn-cta']");
    private final By SectionTitle=By.xpath("//div[@class='large-11 medium-12 columns end']");
private final By SecondSectionHeader=By.xpath("//div[@class='m03-title_text_button--content']//h2");
    private final By Date=By.xpath("//div[@class='td td--date']");
    private final By Value=By.xpath("//div[@class='td td--value']");
    private final By Number=By.xpath("//div[@class='td td--number']");
    private final By Status=By.xpath("//div[@class='td td--status']");
//a[@class='button button--primary gtm-cta']

    public void enterBond(String Value) throws Exception {
        if (webUtil.isElementDisplayed(EnterBond, 10)) {
            try {
                webUtil.sendKeys(EnterBond, Value);
            } catch (Exception e) {
                throw new ExceptionUtils("Bond is not entered");
            }
        } else {
            throw new ExceptionUtils("Enter Bond X-Path may have been changed");
        }
    }

    public void ClickCheckMyNumber() throws Exception {
        if (webUtil.isElementDisplayed(checkMyNumBtn, 10)) {
            try {
                webUtil.click(checkMyNumBtn);
                webUtil.waitForPageLoaded();
                webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("search-result-is-winner"));

            } catch (Exception e) {
                throw new ExceptionUtils("Check my number is clicked");
            }
        } else {
            throw new ExceptionUtils("Check my number X-Path may have been changed");
        }
    }


    public void ClickSearch() throws Exception {
        if (webUtil.isElementDisplayed(Search, 10)) {
            try {
                webUtil.click(Search);
                webUtil.waitForPageLoaded();
                webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("prize-bonds#drawsearchform"));

            } catch (Exception e) {
                throw new ExceptionUtils("Search button is clicked");
            }
        } else {
            throw new ExceptionUtils("Search button X-Path may have been changed");
        }
    }

    public String getBannerTitle(String pageName){
        this.pageName=pageName;
        return getTextByLocator(bannerTitle);
    }

    public String getSectionTitle(){
        return getTextByLocator(SectionTitle);
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

    public String getBuyPBHdr(){
        return getTextByLocator(SecondSectionHeader);
    }

    public String getDataValue(){
        return getTextByLocator(Date);
    }

    public String getAmountValue(){
        return getTextByLocator(Value);
    }


    public String getBondValue(){
        return getTextByLocator(Number);
    }


    public String getStatusValue(){
        return getTextByLocator(Status);
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

    public List <String> getFAQSectionBtnDetails(){
        return getBtnClickAndNewTabLocator(faqSectionBtn);
    }








}
