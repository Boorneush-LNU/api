package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class Kentico13_ComponentCarousel extends Kentico13_MasterPages {

    private final WebDriver driver;

    Kentico13_ComponentHeader comp_Header;
    public Kentico13_ComponentCarousel(WebDriver driver){
        this.driver=driver;
        comp_Header=new Kentico13_ComponentHeader(driver);
    }

    private final By tileHeaderMaster=By.xpath("//h3[@class='m05-product_gallery--title']");
    protected final By tileCarouselNextButton=By.xpath("//button[@aria-label='Next item']");
    private final By tileCarouselPrevButton=By.xpath("//button[@aria-label='Previous item']");
    private final By modalTitleBuyNowBtn=By.xpath("//h1[@class='underlined-heading']");
    private final By modalBuyNowCloseBtn=By.xpath("//h1[@class='underlined-heading']/../button");
    private By tileYearIssue (String tileName) {
        String xpathBuilder=String.format("//*[@title='%s']/../div",tileName);
        return By.xpath(xpathBuilder);
    }

    private By tileHeader (String tileName) {
        String xpathBuilder=String.format("//*[@title='%s']",tileName);
        return By.xpath(xpathBuilder);
    }

    private By tileBulletListItems (String tileName) {
        String xpathBuilder=String.format("//*[@title='%s']/../ul/li",tileName);
        return By.xpath(xpathBuilder);
    }

    private By tileLearnMoreButton(String tileName){
        String xpathBuilder=String.format("//*[@title='%s']/../following-sibling::div/a[1]",tileName);
        return By.xpath(xpathBuilder);
    }

    private By tileBuyNowButton(String tileName){
        String xpathBuilder=String.format("//*[@title='%s']/../following-sibling::div/a[2]",tileName);
        return By.xpath(xpathBuilder);
    }

    public String clickBuyNowBtnCarouselTile(String tileName){
        webUtil.clickLogW(tileBuyNowButton(tileName),tileName);
        return getTextByLocator(modalTitleBuyNowBtn);
    }

    public void clickBuyNowModalCloseBtnCarousel(){
        webUtil.waitUntilElementVisible(modalBuyNowCloseBtn,4);
        webUtil.clickLogW(modalBuyNowCloseBtn,"Close button on Buy Now modal");
    }

    public WebElement tileHeaderWebElem(String tileName){
        webUtil.waitUntilElementVisible(tileHeader(tileName),4);
        return driver.findElement(tileHeader(tileName));
    }

    public boolean clickNavigationConfirmationCarousel(String tileBtnName,String headerOrLearnMoreInd){
        By locatorTempValue=null;
        if(headerOrLearnMoreInd.equalsIgnoreCase("Learn more")){
            locatorTempValue=tileLearnMoreButton(tileBtnName);
        }
        else if(headerOrLearnMoreInd.equalsIgnoreCase("Header")){
            locatorTempValue=tileHeader(tileBtnName);
        }
        else{
            ExtentCucumberAdapter.addTestStepLog("Incorrect headerOrLearnMoreInd passed");
            log.error("Incorrect headerOrLearnMoreInd passed");
        }
        webUtil.waitUntilElementVisible(locatorTempValue,4);
        webUtil.clickLogW(locatorTempValue,tileBtnName);

        String tempVar=getTextByLocator(bannerTitle);
        boolean validator=false;
        if(tempVar.equalsIgnoreCase(comp_Header.titlePage(tileBtnName))){
            validator=true;
        }
        else{
            validator=false;
        }
        return validator;
    }

    private String getTileYearIssue(String tileName){
        return getTextByLocator(tileYearIssue(tileName));
    }

    private String getTileBulletListItems(String tileName) {
        webUtil.waitUntilElementVisible(tileBulletListItems(tileName), 4);
        List<WebElement> elemList = driver.findElements(tileBulletListItems(tileName));
        String elemTemp = null;
        String elemStrList=null;
        for (WebElement elemVar:elemList){
            elemTemp=elemVar.getText();
            if(elemStrList==null){
                elemStrList=elemTemp;
            }
            else{
                elemStrList=String.format("%s;%s",elemStrList,elemTemp);
            }
            elemTemp=null;
        }
        return elemStrList;
    }
    private String getTileHeader(String tileName){
        webUtil.waitUntilElementVisible(tileHeader(tileName),4);
        WebElement webElemTemp=driver.findElement(tileHeader(tileName));
        String targetText=webElemTemp.getAttribute("target");
        String tempTargetText=null;
        if(targetText.isEmpty()){
            tempTargetText="Not Present";
        }
        else{
            tempTargetText=targetText;
        }
        return String.format("%s;%s;%s",webElemTemp.getText(),webElemTemp.getAttribute("href"),tempTargetText);
    }
    private String getTileLearnMoreButton(String tileName){
        webUtil.waitUntilElementVisible(tileLearnMoreButton(tileName),4);
        WebElement webElemTemp=driver.findElement(tileLearnMoreButton(tileName));
        String targetText=webElemTemp.getAttribute("target");
        String tempTargetText=null;
        if(targetText.isEmpty()){
            tempTargetText="Not Present";
        }
        else{
            tempTargetText=targetText;
        }
        return String.format("%s;%s;%s",webElemTemp.getText(),webElemTemp.getAttribute("href"),tempTargetText);
    }
    private String getTileBuyNowButton(String tileName){
        return getTextByLocator(tileBuyNowButton(tileName));
    }

    private HashMap <String, String> getCarouselTileDetails(String tileName){
        HashMap <String, String> elemCarouselStrList=new HashMap<>();
        elemCarouselStrList.put("Tile Header",getTileHeader(tileName));
        if((getTileYearIssue(tileName)).isEmpty()){
            elemCarouselStrList.put("Tile Year Issue","Not Present");
        }
        else{
            elemCarouselStrList.put("Tile Year Issue",getTileYearIssue(tileName));
        }
        elemCarouselStrList.put("Tile Bullet points",getTileBulletListItems(tileName));
        elemCarouselStrList.put("Learn More button",getTileLearnMoreButton(tileName));
        if(tileName.equalsIgnoreCase("Instalment Savings")){
            elemCarouselStrList.put("Buy now button","Not Present");
        }
        else{
            elemCarouselStrList.put("Buy now button",getTileBuyNowButton(tileName));
        }
        return elemCarouselStrList;
    }

    public HashMap<String,HashMap<String,String>> getAllCarouselTileDetails(List<String> carouselOrderFunc){
        HashMap<String,HashMap<String,String>> getAllCarouselTileDetailsTemp=new HashMap<>();
        int i=0;
        for(String tileName:carouselOrderFunc){
            if(i>2){
                webUtil.clickLogW(tileCarouselNextButton,"Carousel Next button");
                getAllCarouselTileDetailsTemp.put(tileName,getCarouselTileDetails(tileName));
            }
            getAllCarouselTileDetailsTemp.put(tileName,getCarouselTileDetails(tileName));
            i++;
        }
        return getAllCarouselTileDetailsTemp;
    }


    public List<String> getCarouselHeaderOrder (){
        List<String> listItems=new ArrayList<>();
        String elemTemp=null;
        int i=0;
        webUtil.waitUntilElementVisible(tileHeaderMaster,4);
        List<WebElement> elemList=driver.findElements(tileHeaderMaster);
        for(WebElement elemVar: elemList){
            if(i>2){
                webUtil.clickLogW(tileCarouselNextButton,"Carousel Next button");
                elemTemp=elemVar.getText();
                listItems.add(elemTemp);
            }
            else{
                elemTemp=elemVar.getText();
                listItems.add(elemTemp);
            }
            i++;
            elemTemp=null;
        }
        driver.navigate().refresh();
        return listItems;
    }

    public String expCarouselHeaderCreaterByPage(String pageName)  {
        String expCarouselTileOrder =null;
        List<String> expCompleteCarouselTileDisplay =null;
        try {
            expCarouselTileOrder = FrameworkConstants.getExpCarouselOrderBaseRef();
            expCompleteCarouselTileDisplay = expListContentValue(FrameworkConstants.getExpCompleteCarouselTileDisplay());
        }
        catch(Exception e){
            log.error("Error is fetching the Carousel Order");
        }
            if (expCompleteCarouselTileDisplay.contains(pageName)) {
                return expCarouselTileOrder;
            } else if (pageName.equalsIgnoreCase("Prize Bonds")) {
                return expCarouselTileOrder.replace(";Prize Bonds;Prize Bonds as a Gift", "");
            } else {
                return expCarouselTileOrder.replace(pageName + ";", "");
            }
    }

    public List <String>expListContentValue(String expList){
        return Arrays.asList(expList.split(";"));
    }

}
