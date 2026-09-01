package com.cucumbercraft.POMPages;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Kentico13_MasterPages {

    protected final  WebDriver driver= DriverManager.getWebDriver();
    protected final WebDriverUtil webUtil=new WebDriverUtil(driver);

    protected String pageName=null;
//    static Logger log = LogManager.getLogger(Kentico13_MasterPages.class);
//    protected final Kentico_HomePageProduct product=new Kentico_HomePageProduct(driver);
//    protected final Kentico13_ComponentHeader comp_header=new Kentico13_ComponentHeader(driver);
//    protected final Kentico13_HelpAndSupportPage K13_HelpAndSupport=new Kentico13_HelpAndSupportPage(driver);

    protected By navigationItem (String locatorTemp){
        return By.linkText(locatorTemp);
    }
    protected By bannerYearIssueNumber = By.className("m01-banner--issue-no");
    protected By bannerTitle = By.className("m01-banner--title");
    protected By hdrBannerTitleHelpSupport=By.xpath("//h1[@id='ltrTitle']");
    protected By bannerTitleSignIn=By.xpath("//h1[@class='m34-member-login--title']");
    protected By bannerText = By.xpath("//*[@class='m01-banner--content']/p");
    protected By bannerBtn = By.xpath("//*[@class='m01-banner-buttons']/a");
    protected  By NewCommSectionTitle =By.xpath("(//div[@class='large-11 medium-12 columns end'])//h2");
    protected By NewCommSectionText =By.xpath("//div[@class='m03-title_text_button--content']/p[1]");
    protected By hdrTitleHelpArticles=By.xpath("//h1[@class='m14-help_content--title']");
    protected By personalDetailRegPgTitle=By.xpath("//h2[@id='title']");
    protected By bannerStatsListItemArr=By.xpath("//li[@class='rates-callout']");
    protected By highlightsSectionTitle = By.xpath("//section[@class='m03-title_text_button  brand-white ']/div/div/h2");
    protected By highlightsLeftBulletPtArr = By.xpath("//*[@class='m04-2col_text--block-item'][1]/ul[@class='list-tick']/li");
    protected By highlightsRightBulletPtArr = By.xpath("//*[@class='m04-2col_text--block-item'][2]/ul[@class='list-tick']/li");
    protected By highlightsHyperlinksArr=By.xpath("//*[@class='list-tick']/li/a");
    protected By highlightsBtn= By.xpath("//*[@class='m04-2col_text  brand-white'][1]/div/div/a");
    protected By banner2Title = By.xpath("//h1[@class='m9-CTA_banner--title']");
    protected By banner2Btn = By.xpath("//*[@class='m9-CTA_banner--title']/../a");
    protected By faqHdr = By.xpath("//*[@class='m04-2col_text--block-item--faq']/../h4");
    protected By faqListArr = By.xpath("//*[@class='m04-2col_text--block-item--faq']/li/a");
    protected By formsAndDownloadsHdr=By.xpath("//*[@class='m04-2col_text--block-item--downloads']/../h4");
    protected By formsAndDownloadsListArr = By.xpath("//*[@class='m04-2col_text--block-item--downloads']/following-sibling::ul/li/a");
    protected By faqSectionBtn= By.xpath("//*[@class='m04-2col_text--block-item--faq']/../parent::div/following-sibling::a");
    protected By carouselHdr= By.xpath("//div[@class='m03-title_text_button--content']/h2");
    protected By btnNextscroll = By.xpath("//button[@class='slider-next slick-arrow']");
    protected By btnPrevscroll = By.xpath("//button[@class='slider-prev slick-arrow']");
    protected By carouselSectionViewAllProdBtn=By.xpath("//*[@class='m05-product_gallery--cta']/a");
    protected By btnBuyNowCloseModal=By.xpath("//section[@id='sign-in-modal']//button[@aria-label='Close modal']");
    protected By buyNowModal=By.xpath("//h1[@class='underlined-heading']");
    protected By calculatorPgIconLbl=By.xpath("//*[@id='lblButonText']");
    protected By calculatorPgHdr=By.xpath("//*[@id='lblTitleText']");
    protected By calculatorPgCloseBtn=By.xpath("//*[@aria-label='Close Modal']");

    protected String getTitle(){
        return driver.getTitle();
    }

    protected boolean getListArrComparisonResult(List <String>expListArr, List <String> actListArr){
        boolean compareRes=false;
        if(!(actListArr==null)){
            for (int iteratorList=0;iteratorList<actListArr.size();iteratorList++) {
                compareRes = webUtil.CompareStringStr(actListArr.get(iteratorList), "equalsIgnoreCase", expListArr.get(iteratorList));
            }
        }
        else{
            compareRes=false;
            ExtentCucumberAdapter.getCurrentStep().fail("Actual List is null");
        }
        return compareRes;
    }

    protected List <String> getStrListItemsByLocator(By locatorValue){
        List <WebElement> tempListElem=new ArrayList<>();
        webUtil.waitUntilElementVisible(locatorValue,4);
        tempListElem=driver.findElements(locatorValue);
        List <String> tempListStr=new ArrayList<>();
        for (WebElement tempValueElem: tempListElem){
            tempListStr.add(tempValueElem.getText());
        }
        return tempListStr;
    }

    protected List <String> getStrListItemsAttributeByLocator(By locatorValue, String attibuteType){
        List <WebElement> tempListElem=new ArrayList<>();
        webUtil.waitUntilElementVisible(locatorValue,4);
        tempListElem=driver.findElements(locatorValue);
        String tempValue=null;
        List <String> tempListStr=new ArrayList<>();
        for (WebElement tempValueElem: tempListElem){
            webUtil.waitFor(2000);
            tempValue=tempValueElem.getAttribute(attibuteType);
            if(attibuteType.equalsIgnoreCase("target")){
                if(tempValue.isEmpty()){
                    tempValue="Not Present";
                }
            }
            tempListStr.add(tempValueElem.getAttribute(attibuteType));
        }
        return tempListStr;
    }

    public String getBuyNowModalTitle(){
        String modalTitle=null;
        webUtil.waitUntilElementVisible(buyNowModal,4);
        try {
            modalTitle=getTextByLocator(buyNowModal);
        }
        catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
        return modalTitle;
    }

    protected List <String> getBtnClickAndNewTabLocator(By locatorValue){
        List <String> btnClickParametersList=new ArrayList<>();
        webUtil.waitUntilElementVisible(locatorValue,4);
        WebElement webElemTemp=driver.findElement(locatorValue);
        btnClickParametersList.add(webElemTemp.getText());
        btnClickParametersList.add(webElemTemp.getAttribute("href"));
        btnClickParametersList.add(webElemTemp.getAttribute("target"));
        return btnClickParametersList;
    }

    protected List <String> getBtnClickAndNewTabWebElem(WebElement webElem){
        List <String> btnClickParametersList=new ArrayList<>();
        btnClickParametersList.add(webElem.getText());
        btnClickParametersList.add(webElem.getAttribute("href"));
        btnClickParametersList.add(webElem.getAttribute("target"));
        return btnClickParametersList;
    }

    protected void getLinksOrBtnClick(By locatorvalue,String expValueHref, String messageLogHref, String expValueTarget, String messageLogTarget){
        webUtil.waitUntilElementVisible(locatorvalue,4);
        webUtil.gettextByAttributeValidator(locatorvalue,"equalsIgnoreCase",expValueHref,"href",messageLogHref);
        webUtil.gettextByAttributeValidator(locatorvalue,"equalsIgnoreCase",expValueTarget,"target",messageLogTarget);
    }

    protected boolean clickBtnLinkCompareNxtPgBannerTitle(By locatorValueSource,By locatorValueNxtPgHdr,String btnName,String nextPgHdr){
        webUtil.clickLogW(locatorValueSource,btnName);
        webUtil.waitUntilElementVisible(locatorValueNxtPgHdr,10);
        return (driver.findElement(locatorValueNxtPgHdr).getText()).equalsIgnoreCase(nextPgHdr);
    }

    protected String getTextByLocator(By locatorValue){
        try {
            webUtil.waitUntilElementVisible(locatorValue, 4);
            return driver.findElement(locatorValue).getText();
        }
    catch(Exception e){
            return "";
        }
    }

    // Parses webelement to retrieve the xpath used for identification
    public String getXpathFromElement(WebElement me) {
        return (me.toString().split("-> xpath: ")[1]).substring(0, (me.toString().split("-> xpath: ")[1]).length() - 1);
    }

    public List<String> getDropDownValues(By elemLocator){
        webUtil.waitUntilElementVisible(elemLocator,4);
        Select statusDD=new Select(driver.findElement(elemLocator));
        return statusDD.getOptions().stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    protected List <String> getTextValuesByLocator(By locatorValue){
        List <String> listText=new ArrayList<>();
        for (WebElement elemVar : driver.findElements(locatorValue)) {
            String text = elemVar.getText();
            listText.add(text);
            System.out.println("Found text: " + text);
        }
        return listText;
    }

}
