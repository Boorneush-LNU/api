package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

@Log4j2
public class Kentico13_Homepage extends Kentico13_MasterPages {

    private final WebDriver driver;
    Kentico13_ComponentHeader comp_Header;
    Kentico13_ComponentFooter comp_Footer;
    Kentico13_ComponentSavingIdeas comp_SavingIdeas;
    Kentico13_ComponentCarousel comp_Carousel;

    public Kentico13_Homepage(WebDriver driver){
        this.driver=driver;
        comp_Header=new Kentico13_ComponentHeader(driver);
        comp_SavingIdeas=new Kentico13_ComponentSavingIdeas(driver);
        comp_Carousel=new Kentico13_ComponentCarousel(driver);
        comp_Footer=new Kentico13_ComponentFooter(driver);
    }

    private final By tempAcceptCookies = By.xpath("(//button[contains(text(),'Allow All')])[2]");
    private final By privacyPrefLbl=By.xpath("//*[contains(text(),'Privacy Preference Centre')]");
    private final By acceptCookies = By.id("accept-recommended-btn-handler");
    private final By privacyPrefBtnAllowAll=By.xpath("//*[contains(text(),'Allow All')]");
    private final By secondaryBannerText=By.xpath("//*[@class='m02-text-button--text']");
    private final By highlightsHomepageTitle=By.xpath("//section[contains(@class,'m03-title_text_button')]");
    private final By highlightsFindOutMoreBtn=By.xpath("//*[@class='m04-2col_text--block']/../a");
    private final By carouselSectionMaster=By.xpath("//*[@class='m05-product_gallery--slider slick-initialized slick-slider']/div/div/div");
    private final By carouselSectionHeader=By.xpath("//h2[normalize-space()='We have the Savings Product for you']");
    private final By carouselSectionSummaryText=By.xpath("//h2[normalize-space()='We have the Savings Product for you']/../../following-sibling::div//div/p");
    private final By prizeBondsSectionHeader=By.xpath("//h2[normalize-space()='Prize Bonds']");
    private final By prizeBondsSectionSummaryText=By.xpath("//h2[normalize-space()='Prize Bonds']/../../following-sibling::div//div/p");
    private final By savingIdeasSectionHeader=By.xpath("//h2[normalize-space()='Savings Ideas']");
    private final By savingIdeasSectionSummaryText=By.xpath("//h2[normalize-space()='Savings Ideas']/../../following-sibling::div//div/p");
    private final By prizeBondsSectionPBTileLearnMore=By.xpath("//*[@class='m01-banner-buttons'][1]//a[@title='Learn More']");
    private final By prizeBondsSectionPBTileBuyNow=By.xpath("//*[@class='m06-2col_cards--block-item'][1]//a[@title='Buy now']");
    private final By prizeBondsSectionPBTileHeader=By.xpath("//*[@class='m06-2col_cards--block-item'][1]//h3");
    private final By prizeBondsSectionPBTileSummaryText=By.xpath("//*[@class='m06-2col_cards--block-item'][1]//p");
    private final By prizeBondsSectionPBGiftTileLearnMore=By.xpath("//*[@class='m01-banner-buttons'][1]//a[@title='Learn More']");
    private final By prizeBondsSectionPBGiftTileBuyNow=By.xpath("//*[@class='m06-2col_cards--block-item'][2]//a[@title='Gift now']");
    private final By prizeBondsSectionPBGiftTileHeader=By.xpath("//*[@class='m06-2col_cards--block-item'][2]//h3");
    private final By prizeBondsSectionPBGiftTileSummaryText=By.xpath("//*[@class='m06-2col_cards--block-item'][2]//p");
    private final By bannerTitleBuyNow=By.xpath("//div[@class='wrapper__ecommerce']//h3");
    private final By bannerTitleSignIn=By.xpath("//h1[@class='m34-member-login--title']");
    private final By bannerTitleRegister=By.xpath("//h1[@class='m01-banner--title']");
    private final By prizeBondsSectionPBTileLearnMoreHomePage=By.xpath("(//div[@class='m06-2col_cards--block-content--buttons js-equal-height']//a[text()='Learn more'])[1]");
    private final By prizeBondsSectionPBGiftTileLearnMoreHomePage=By.xpath("(//div[@class='m06-2col_cards--block-content--buttons js-equal-height']//a[text()='Learn more'])[2]");

//    Privacy Preference :
private final By privPrefHeader = By.id("pc-title");
    private final By privPrefDesc = By.id("pc-policy-text");
    private final By ppAllowAll=By.id("accept-recommended-btn-handler");
    private final By ppRejectAll=By.xpath("//button[@id='accept-recommended-btn-handler']//following-sibling::button");
    private final By cookiesweUse=By.xpath("//a[@class='privacy-notice-link']");
    private final By consentHdr=By.id("manage-cookies-text");
    private final By sNHdr=By.id("ot-header-id-C0001");
    private final By sNAlwaysActive =By.id("ot-status-id-C0001");
    private final By sNDESC=By.id("ot-desc-id-C0001");
    private final By performanceHdr=By.id("ot-header-id-C0002");
    private final By performanceDesc=By.id("ot-desc-id-C0002");
    private final By functionalHdr=By.id("ot-header-id-C0003");
    private final By functionalDesc=By.id("ot-desc-id-C0003");
    private final By confirmMyChoices=By.xpath("//button[@class='save-preference-btn-handler onetrust-close-btn-handler button-theme'][1]");
    private final By poweredByOne=By.xpath("//a[@class='powered-by-logo']//img[@title='Powered by OneTrust Opens in a new Tab'][1]");


    public void launchHomepageUrl(){
        try {
            driver.get(FrameworkConstants.envLinkFetch());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        }

    public String fetchUrlBrowser(){
        return driver.getCurrentUrl();
    }

    public String getPageBrowserTitle() {
     return driver.getTitle();
    }
    public String getHomepageBannerTitle(){
        return getTextByLocator(bannerTitle);
    }
    public String getHomepageBannerText(){
        return getTextByLocator(bannerText);
    }
    public String getHomepageBannerBtnLbl(){
        return getTextByLocator(bannerBtn);
    }

    public List <String> getBannerSectionBtnDetails(){
        webUtil.waitUntilElementVisible(bannerBtn,4);
        return getBtnClickAndNewTabLocator(bannerBtn);
    }
    public String getSecondaryBannerText(){
        return getTextByLocator(secondaryBannerText);
    }
    public String highlightsTitleHomepage(){
        return getTextByLocator(highlightsHomepageTitle);
    }

    public List<String> actHighlightsRightListContent(){
        webUtil.waitUntilElementVisible(highlightsRightBulletPtArr,4);
        return getStrListItemsByLocator(highlightsRightBulletPtArr);
    }

    public List<String> actHighlightsLeftListContent(){
        webUtil.waitUntilElementVisible(highlightsLeftBulletPtArr,4);
        return getStrListItemsByLocator(highlightsLeftBulletPtArr);
    }

    public List<String> getIrelandStateSavingsOnlineBtnDetails(){
        return getBtnClickAndNewTabLocator(highlightsHyperlinksArr);
    }

    public List <String> getHomepageFindOutMoreBtnDetails(){
        return getBtnClickAndNewTabLocator(highlightsFindOutMoreBtn);
    }
    public List <String> getPageHeaderListDetails() {
        return comp_Header.getPageHeaderComponentListDetails();
    }
    public List <String> getExpPageHeaderDetails(){
        return FrameworkConstants.expHeaderListCreator();
    }

    public List <String> getExpPageSecNavHeaderDetails(){
        return FrameworkConstants.expHeaderListSecNavCreator();
    }

    public List <String> getPageHdrSecondaryNavComponentListDetails(){
        return comp_Header.getPageHeaderSecondaryComponentListDetails();
    }

     private String clickNavigationTabName(String tabName){
        if (tabName.equalsIgnoreCase("Begin Registration")){
            webUtil.clickLogW(navigationItem("Register"),"Register");
         }
        else{
        webUtil.clickLogW(navigationItem(tabName),tabName);
        }
        if(tabName.equalsIgnoreCase("Help and Support")){
            return getTextByLocator(hdrBannerTitleHelpSupport);
        } else if (tabName.equalsIgnoreCase("Buy now")) {
            return getTextByLocator(bannerTitleBuyNow);
        } else if (tabName.equalsIgnoreCase("Sign in")) {
            return getTextByLocator(bannerTitleSignIn);
        } else if (tabName.equalsIgnoreCase("Begin Registration")) {
            return getTextByLocator(bannerTitleRegister);
        } else{
            return getTextByLocator(bannerTitle);
        }
    }

    public boolean clickNavigationConfirmationHeader(String pageName){
        if (pageName.equalsIgnoreCase("Begin Registration")){
            pageName="Register";
        }
        if((pageName.equalsIgnoreCase("English"))) {
            return true;
        }
        else{
        return clickNavigationTabName(pageName).equalsIgnoreCase(comp_Header.titlePage(pageName));
        }
        }

    public void navigateBackPg()  {
        webUtil.navigateBackFunc();
        webUtil.waitUntilElementVisible(bannerTitle,4);
    }

    public void pageRefresh() {
        driver.navigate().refresh();
    }



    //Carousel Section
    public String getHomepageCarouselSectionTitle(){
        return getTextByLocator(carouselSectionHeader);
    }
    public String getHomepageCarouselSectionSummaryText(){
        return getTextByLocator(carouselSectionSummaryText);
    }
    public List<String> getCarouselOrderPg(){
        return comp_Carousel.getCarouselHeaderOrder();
    }

    public HashMap<String, HashMap<String,String>> getCarouselTileDetails(List<String> carouselOrder){
        return comp_Carousel.getAllCarouselTileDetails(carouselOrder);
    }


    public void clickCarouselNextBtn(String tileBtnName){
        int iterIndTemp=0;
        if(tileBtnName.equalsIgnoreCase("Instalment Savings")){
            iterIndTemp=1;
        } else if (tileBtnName.contains("Prize Bonds")) {
            iterIndTemp=3;
        }
        else {
            iterIndTemp=0;
            }

        for (int i=0;i<iterIndTemp;i++){
            webUtil.clickLogW(comp_Carousel.tileCarouselNextButton,"Carousel Next button");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    public static HashMap<String, HashMap<String,String>> getExpCarouselTileDetails()  {
//        return Kentico13_ComponentCarousel.expCarouselContentLoad();
        return FrameworkConstants.expMasterMapCompCarousel();
    }

    ////////////////////////////////////////////////////////////////////////////////

    public String getExpCarouselHeaderTileName(String pageName){
        return comp_Carousel.expCarouselHeaderCreaterByPage(pageName);
    }

    public List <String> getCarouselViewProdBtnDetails(){
        return getBtnClickAndNewTabLocator(carouselSectionViewAllProdBtn);
    }

    public boolean getClickNavigationConfirmationCarouselHeader(String tileBtnName){
        clickCarouselNextBtn(tileBtnName);
        return comp_Carousel.clickNavigationConfirmationCarousel(tileBtnName, "Header");
    }

    public boolean getClickNavigationConfirmationCarouselLearnMore(String tileBtnName){
        clickCarouselNextBtn(tileBtnName);
        return comp_Carousel.clickNavigationConfirmationCarousel(tileBtnName, "Learn More");
    }

    public String getHeaderClickBuyNowBtnCarouselTile(String tileName){
        clickCarouselNextBtn(tileName);
        return comp_Carousel.clickBuyNowBtnCarouselTile(tileName);
    }

    public void getClickBuyNowModalCloseBtn(){
        comp_Carousel.clickBuyNowModalCloseBtnCarousel();
    }


    //PB Section
    public String getHomepagePBSectionTitle(){
        return getTextByLocator(prizeBondsSectionHeader);
    }
    public String getPBGiftTileHdr(){
        return getTextByLocator(prizeBondsSectionPBGiftTileHeader);
    }

    public String getPBTileHdr(){
        return getTextByLocator(prizeBondsSectionPBTileHeader);
    }

    public String getHomepagePBSectionSummaryText(){
        return getTextByLocator(prizeBondsSectionSummaryText);
    }

    public String getPrizeBondsSectionPBTileHeaderAndSummaryText(){
     String tempData=null;
     tempData=getTextByLocator(prizeBondsSectionPBTileHeader);
     webUtil.waitUntilElementVisible(prizeBondsSectionPBTileSummaryText,4);
     tempData=String.format("%s;%s",tempData,driver.findElement(prizeBondsSectionPBTileSummaryText).getText());
     return tempData;
    }

    public String getPrizeBondsSectionPBGiftTileHeaderAndSummaryText(){
        String tempData=null;
        tempData=getTextByLocator(prizeBondsSectionPBGiftTileHeader);
        webUtil.waitUntilElementVisible(prizeBondsSectionPBGiftTileSummaryText,4);
        tempData=String.format("%s;%s",tempData,driver.findElement(prizeBondsSectionPBGiftTileSummaryText).getText());
        return tempData;
    }


    public List<String> getPrizeBondsSectionPBTileLearnMoreBtn(){
        return getBtnClickAndNewTabLocator(prizeBondsSectionPBTileLearnMore);
    }

    public List<String> getPrizeBondsSectionPBGiftTileLearnMoreBtn(){
        return getBtnClickAndNewTabLocator(prizeBondsSectionPBGiftTileLearnMore);
    }

    private boolean clickNavigationConfirmationButton(By locatorValueBtn,By locatorValueNextModalPageHeader,String buttonName,String expNavigatedPageNameOrTitle){
        webUtil.clickLogW(locatorValueBtn,buttonName);
        webUtil.waitUntilElementVisible(locatorValueNextModalPageHeader,4);
        return driver.findElement(locatorValueNextModalPageHeader).getText().equalsIgnoreCase(expNavigatedPageNameOrTitle);
    }

    public String getPrizeBondsPBTileBuyNowBtnLbl(){
        return getTextByLocator(prizeBondsSectionPBTileBuyNow);
    }

    public String getPrizeBondsPBGiftTileBuyNowBtnLbl(){
        return getTextByLocator(prizeBondsSectionPBGiftTileBuyNow);
    }

    public String clickPrizeBondsTileBuyGiftNowBtn(String buttonName) {
        By locatorValue=null;
        if(buttonName.equalsIgnoreCase("Buy now")){
            locatorValue=prizeBondsSectionPBTileBuyNow;
        }
        else if (buttonName.equalsIgnoreCase("Gift now")){
            locatorValue=prizeBondsSectionPBGiftTileBuyNow;
        }
        webUtil.clickLogW(locatorValue,buttonName);
        return getTextByLocator(buyNowModal);
    }

    public boolean clickValidatorPrizeBondsTileBuyNowBtn(String buttonName) {
        boolean validator=false;
        By locatorValue=null;
        if(buttonName.equalsIgnoreCase("Buy now")){
            locatorValue=prizeBondsSectionPBTileBuyNow;
        }
        else if (buttonName.equalsIgnoreCase("Gift now")){
            locatorValue=prizeBondsSectionPBGiftTileBuyNow;
        }
        try {
            validator=clickNavigationConfirmationButton(locatorValue,buyNowModal,buttonName,"Registered for Ireland State Savings Online?");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
        webUtil.clickLogW(btnBuyNowCloseModal,"Buy now modal Close button");
        return validator;
    }

    public boolean clickPrizeBondsPBTileLearnMoreBtn() {
        boolean validator=false;
        try {
          validator=  clickNavigationConfirmationButton(prizeBondsSectionPBTileLearnMoreHomePage,bannerTitle,"Buy now",comp_Header.titlePage("Prize Bonds"));
        } catch (Exception e) {
            validator=false;
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
        return validator;
    }

    public boolean clickPrizeBondsPBGiftTileLearnMoreBtn() {
        boolean validator=false;
        try {
validator=            clickNavigationConfirmationButton(prizeBondsSectionPBGiftTileLearnMoreHomePage,bannerTitle,"Buy now",comp_Header.titlePage("Prize Bonds"));
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
        return validator;
    }

    //Saving Ideas Section
    public String getHomepageSavingIdeasSectionTitle(){
        return getTextByLocator(savingIdeasSectionHeader);
    }
    public String getHomepageSavingIdeasSectionSummaryText(){
        return getTextByLocator(savingIdeasSectionSummaryText);
    }

    public List <String> getSavingIdeasSectionListDetails() {
        return comp_SavingIdeas.getSavingIdeasComponentListDetails();
    }
    public List <String> getExpSavingIdeasSectionDetails(){
        return FrameworkConstants.expSavingIdeasSectionListCreator();
    }

    public boolean clickNavigationConfirmationSavingIdeas(String pageName){
        return comp_SavingIdeas.titlePageSavingsIdeas(pageName).equalsIgnoreCase(clickNavigationTabName(pageName));
    }

//Footer Section Homepage
public List <String> getPageFooterListDetails() {
    return comp_Footer.getFooterLinkDetailsList();
}
    public List <String> getExpPageFooterDetails(){
        return FrameworkConstants.expFooterListCreator();
    }


    public boolean clickNavigationInternalConfirmationFooterHeader(String footerLinkName){
        return comp_Footer.getFooterTitlePage(footerLinkName).equalsIgnoreCase(comp_Footer.clickFooterLinkInternal(footerLinkName));
    }

    public boolean clickNavigationExternalConfirmationFooterHeader(String footerLinkName){
        return comp_Footer.getFooterTitlePage(footerLinkName).equalsIgnoreCase(comp_Footer.getURLClickFooterLinkExternal(footerLinkName));
    }


    // Privacy Preference Section
    public int privacyPrefSectionLbl(){
        webUtil.waitUntilElementVisible(privacyPrefLbl,4);
        return driver.findElements(privacyPrefLbl).size();
    }
    public void clickPrivacyPrefAllowAllBtn(){
        try {
            if (webUtil.isElementVisible(privacyPrefLbl, 4)) {
                ExtentCucumberAdapter.addTestStepLog("Privacy Preferences displayed");
                if (webUtil.isElementVisible(tempAcceptCookies, 4))
                    driver.findElement(tempAcceptCookies).click();
                else {
                    driver.findElement(acceptCookies).click();
                }
            }
        } catch (Exception ignored) {
            ExtentCucumberAdapter.addTestStepLog("Preferences permission enabled already");

        }
//        webUtil.clickLog(privacyPrefBtnAllowAll,buttonName);
    }

    // Others
    public String getHomepageBannerTitle(String pageName){
        this.pageName=pageName;
        return driver.findElement(bannerTitle).getText();
    }

    public void clickFooterLink(String linkName)
    {
        By elefooterLink=By.xpath("//footer//ul//li");
        webUtil.getElements(elefooterLink)
                .stream()
                .filter(s->s.getText().equals(linkName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public String getPrivacyPrefHdr(){
        return getTextByLocator(privPrefHeader);
    }

    public String getPrivacyPrefDesc(){
        String desc=getTextByLocator(privPrefDesc);
        desc=desc.replace("Cookies We Use","").trim();
        System.out.println(desc);
        return  desc;
    }


    public List <String> getPPCookiesWeUse(){
        return  getBtnClickAndNewTabLocator(cookiesweUse);
    }
    public String getPrivacyPrefBtnRejectAll(){

        return getTextByLocator(ppRejectAll);
    }
    public String getPrivacyPrefBtnAllowAll(){

        return getTextByLocator(ppAllowAll);
    }
    public String getConsentHdr(){

        return getTextByLocator(consentHdr);
    }
    public String getSNHdr(){

        return getTextByLocator(sNHdr);
    }

    public String getSNAlwaysActive(){
        return getTextByLocator(sNAlwaysActive);
    }
    public String getSNDesc(){
        return getTextByLocator(sNDESC);
    }
    public String getPerformanceHdr(){
        return getTextByLocator(performanceHdr);
    }
    public String getPerformanceDesc(){
        return getTextByLocator(performanceDesc);
    }
    public String getFunctionalHdr(){
        return getTextByLocator(functionalHdr);
    }
    public String getFunctionalDesc(){
        return getTextByLocator(functionalDesc);
    }
    public String getBtnConfirmMyChoices(){
        return getTextByLocator(confirmMyChoices);
    }

    public void launchSSCNpageUrl()  {
        driver.get(FrameworkConstants.envLinkFetchSSCN());
    }







}
