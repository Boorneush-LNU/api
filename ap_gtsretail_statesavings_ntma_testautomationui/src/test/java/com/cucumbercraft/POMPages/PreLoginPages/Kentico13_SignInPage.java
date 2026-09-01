package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
@Log4j2
public class Kentico13_SignInPage extends Kentico13_MasterPages{
    private WebDriver driver;
    Kentico13_ComponentHeader comp_Header;
    public Kentico13_SignInPage (WebDriver driver){
        this.driver=driver;
        comp_Header=new Kentico13_ComponentHeader(driver);
    }
    private final By bannerTextSignIn=By.xpath("//*[@class='m34-member-login--text ']");
    private final By criteraSectionTitle=By.xpath("//*[@class='m03-title_text_button--title']");
    private final By criteraSectionText=By.xpath("//*[@class='m03-title_text_button--content']");
    private final By videosSectionTitle=By.xpath("//*[@class='m03-title_text_button--content']/h4");
    private final By registerBtnVideoSection=By.xpath("//*[@class='m04-2col_text--button button button--primary gtm-cta']/../a[@title='Begin registration']");
    private final By signInNowBtnVideoSection=By.xpath("//*[@class='m03-title_text_button--content']/../a[@title='Sign in now']");

    private final By signInPanelHdr=By.xpath("//div[@class='m34-member-login-box-wrapper']/h4");
    private final By signInPanelEmailAddLbl=By.xpath("//label[@class='form-label' and @for='emailLoginAccount']");
    private final By signInPanelEmailAddPlaceholderTxt=By.xpath("//input[@name='emailLoginAccount']");
    private final By signInPanelPwdLbl=By.xpath("//label[@class='form-label' and @for='passwordLoginAccount']");
    private final By signInPanelFrgtPwdLink=By.xpath("//*[@class='form-element']/button");
    private final By signInPanelRegisterText=By.xpath("//*[@id='login-form']//p");
    private final By signInPanelSignUpLink=By.xpath("//*[@id='login-form']//p/a");
    private final By signInPanelBtn=By.xpath("//input[@id='login-form-submit']");
    private final By resetYourPwdSliderHdr=By.xpath("//section[@id='sectionResetPassword']/h4");
    private final By resetYourPwdSliderCancelBtn=By.xpath("//*[@aria-label='Cancel']");
    private final By sscnLinkCriteriasSection=By.xpath("//*[@class='m03-title_text_button--content']//a");
    public String getBannerTitleSignInPg(){
        return getTextByLocator(bannerTitleSignIn);
    }
    public String getBannerTextSignInPg(){
        return getTextByLocator(bannerTextSignIn);
    }
    public List <String> getRegisterBtnVideoSection(){
        return getBtnClickAndNewTabLocator(registerBtnVideoSection);
    }

    public List <String> getSignInNowBtnVideoSection(){
        return getBtnClickAndNewTabLocator(signInNowBtnVideoSection);
    }

    public boolean getSignInNowBtnValidation(String btnName){
        webUtil.clickLogW(signInNowBtnVideoSection,btnName);
        return getTextByLocator(bannerTitleSignIn).equalsIgnoreCase("Ireland State Savings Online");
    }

    public boolean getRegisterBtnValidation(String btnName){
        webUtil.clickLogW(registerBtnVideoSection,btnName);
        return driver.findElement(bannerTitle).getText().equalsIgnoreCase("Register for Ireland State Savings Online");
    }

    private final By benefitsOfRegHighlightsListItem(int iterator){
        String tempPathFormer=String.format("%s%d%s","//div[@class='m04-2col_text--block-item'][",iterator,"]/ul/li");
        return By.xpath(tempPathFormer);
    }

    public String navigateBackPg()  {
        webUtil.navigateBackFunc();
        return getTextByLocator(bannerTitleSignIn);
    }

    public String getCriteriaSectionTitle(){
        return getTextByLocator(criteraSectionTitle);
    }
    public String getCriteriaSectionText(){
        return getTextByLocator(criteraSectionText);
    }


    public String getVideoSectionTitle(){
        return getTextByLocator(videosSectionTitle);
    }

    public String getSignInPanelHdr(){
        return getTextByLocator(signInPanelHdr);
    }

    public String getSignInPanelEmailAddLbl(){
        return getTextByLocator(signInPanelEmailAddLbl);
    }

    public String getSignInPanelEmailAddPlaceholderTxt(){
        webUtil.waitUntilElementVisible(signInPanelEmailAddPlaceholderTxt,4);
        return driver.findElement(signInPanelEmailAddPlaceholderTxt).getAttribute("placeholder");
    }

    public String getSignInPanelPwdLbl(){
        return getTextByLocator(signInPanelPwdLbl);
    }

    public String getSignInPanelFrgtPwdLink(){
        return getTextByLocator(signInPanelFrgtPwdLink);
    }

    public boolean clickSignInPanelFrgtPwdLink(String linkName, String sliderHeaderText){
        webUtil.clickLogW(signInPanelFrgtPwdLink,linkName);
        return (getTextByLocator(resetYourPwdSliderHdr)).equalsIgnoreCase(sliderHeaderText);
    }

    public boolean clickCancelBtnResetYourPwdSlider(String expNextPageHeader){
        webUtil.clickLogW(resetYourPwdSliderCancelBtn, "Cancel Button");
        return (getTextByLocator(bannerTitleSignIn)).equalsIgnoreCase(expNextPageHeader);
    }
    public String getSignInPanelRegisterText(){
        return getTextByLocator(signInPanelRegisterText);
    }
    public String getSignInPanelBtnLbl(){
        webUtil.waitUntilElementVisible(signInPanelBtn,4);
        return driver.findElement(signInPanelBtn).getAttribute("value");
    }

    public boolean clickSignUpNowLink(String btnName){
        webUtil.clickLogW(signInPanelSignUpLink,btnName);
        webUtil.waitUntilElementVisible(personalDetailRegPgTitle,4);
        return driver.findElement(personalDetailRegPgTitle).getText().equalsIgnoreCase("Let’s get started");
    }

    public List <String> getSSCNLinkDetails(){
        return getBtnClickAndNewTabLocator(sscnLinkCriteriasSection);
    }

    public List <String> getHighlightsLeftBulletPtArr(){
        return getHighlightsArr(benefitsOfRegHighlightsListItem(1));
    }

    public List <String> getHighlightsRightBulletPtArr(){
        return getHighlightsArr(benefitsOfRegHighlightsListItem(2));
    }

    private List <String> getHighlightsArr(By locatorValue){
        webUtil.waitUntilElementVisible(locatorValue,4);
        List<String> highlightLoadList=new ArrayList<>();
        for (WebElement elemVar: driver.findElements(locatorValue)){
            highlightLoadList.add(elemVar.getText());
        }
        return highlightLoadList;
    }


}
