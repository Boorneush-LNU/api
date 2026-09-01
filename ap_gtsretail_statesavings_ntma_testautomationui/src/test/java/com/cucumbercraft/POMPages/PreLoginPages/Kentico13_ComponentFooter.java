package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.Models.FrameworkConstants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
@Log4j2
public class Kentico13_ComponentFooter extends Kentico13_MasterPages{
    private final WebDriver driver;
    public Kentico13_ComponentFooter(WebDriver driver){
        this.driver=driver;
    }
    private final By elemFooterNavigationList=By.xpath("//footer//li/a");
    private final By hdrWhatIsPBC=By.xpath("//*[@class='m14-help_content--title']");
    private final By hdrCookiesWeUse=By.xpath("//h3[@id='cookie-policy-title']");
    private final By hdrTitleFooterPages=By.xpath("//*[@class='m03-title_text_button--title']");

    private By elemFooterText (String footerLinkName) {
        String xpathBuilder=String.format("//footer[@class='footer']//li/a[@title='%s']",footerLinkName);
        return By.xpath(xpathBuilder);
    }

    private List<String> actFooterOrderList(){
        String tempData=null;
        List<String> tempFooterLoad=new ArrayList<>();
        webUtil.waitUntilElementVisible(elemFooterNavigationList,4);
        List <WebElement> elemList=driver.findElements(elemFooterNavigationList);
        for(WebElement elemVar: elemList){
            tempFooterLoad.add(elemVar.getText());
        }
        return tempFooterLoad;
    }

    public List <String> getFooterOrderList(){
        return actFooterOrderList();
    }

    private List <String> footerLinkDetails(){
        List <String> getTempFooterValue=new ArrayList<>();
        List <WebElement> getTempWebElemFooterValue=new ArrayList<>();
        webUtil.waitUntilElementVisible(elemFooterNavigationList,4);
        getTempWebElemFooterValue=driver.findElements(elemFooterNavigationList);
        String tempDataLoad=null;
        String loadTempAttrTarget=null;
        for (int i=0;i<getTempWebElemFooterValue.size();i++){
            loadTempAttrTarget=getTempWebElemFooterValue.get(i).getAttribute("target");
            if(loadTempAttrTarget.isEmpty()){
                loadTempAttrTarget="Not Present";
            }
            tempDataLoad=String.format("%s;%s;%s",getTempWebElemFooterValue.get(i).getText(),getTempWebElemFooterValue.get(i).getAttribute("href"),loadTempAttrTarget);
            getTempFooterValue.add(tempDataLoad);
            tempDataLoad=null;
            loadTempAttrTarget=null;
        }
        return getTempFooterValue;
    }

    public List <String> getFooterLinkDetailsList(){
        return footerLinkDetails();
    }

    private String compFooterPage(String pageName){
        return FrameworkConstants.mapFooterExtract().get(pageName);
    }

    public String getFooterTitlePage(String pageName){
        return compFooterPage(pageName);
    }

    public String clickFooterLinkInternal(String footerLinkName){
        By locatorValueHdr=null;
        String tempExpNextPageHdr=null;
        if(footerLinkName.equalsIgnoreCase("Cookies We Use")){
            locatorValueHdr=hdrCookiesWeUse;
        } else if (footerLinkName.equalsIgnoreCase("Prize Bond Company") ) {
            locatorValueHdr=hdrWhatIsPBC;
        }else{
            locatorValueHdr=hdrTitleFooterPages;
        }
        webUtil.clickLogW(elemFooterText(footerLinkName),footerLinkName);
        return driver.findElement(locatorValueHdr).getText();
    }

    public String getURLClickFooterLinkExternal(String footerLinkName){
        webUtil.clickLogW(elemFooterText(footerLinkName),footerLinkName);
        driver.switchTo().window(driver.getWindowHandles().stream().skip(1).findFirst().get());
        webUtil.waitFor(2000);
        String currentURL= driver.getCurrentUrl();
        driver.switchTo().window(driver.getWindowHandles().stream().skip(1).findFirst().get()).close();
       log.info(currentURL);
        driver.switchTo().window(driver.getWindowHandles().stream().findFirst().get());
        webUtil.waitUntilElementVisible(bannerTitle,4);
        return currentURL;
    }


    }


