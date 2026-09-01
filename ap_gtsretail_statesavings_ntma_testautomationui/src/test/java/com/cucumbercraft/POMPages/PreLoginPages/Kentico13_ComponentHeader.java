package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.Models.FrameworkConstants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Log4j2
public class Kentico13_ComponentHeader extends Kentico13_MasterPages {
    private final WebDriver driver;
    public Kentico13_ComponentHeader(WebDriver driver){
        this.driver=driver;
    }
    private final By elemHeaderNavigationList=By.xpath("//*[@id='navigation_main']/li/a");
    private final By secNavHeaderValueDropDown=By.xpath("//*[contains(@class,'secondary-nav__link')]");
    private final By secNavHeaderList=By.xpath("//*[contains(@class,'secondary-nav__link')]/a");
    private final By btnLangSel=By.xpath("//button[@class='language-toggle--selected']");
    private final By langToggleList=By.xpath("//*[@class='language-toggle--list']/li");
    public List <String> getPageHeaderComponentListDetails() {
        return getHeaderLinkPrimaryDetails();
    }

    public List <String> getPageHeaderSecondaryComponentListDetails()  {
        return getHeaderLinkSecondaryDetails();
    }

    public void clickLangToggleBtn(String buttonName){
        webUtil.clickLogW(btnLangSel,buttonName);
    }

    private List <String> getHeaderLinkPrimaryDetails(){
        List <String> getTempHeaderValue=new ArrayList<>();
        List <WebElement> getTempWebElemHeaderValue=new ArrayList<>();
        webUtil.waitUntilElementVisible(elemHeaderNavigationList,4);
        String loadTempAttrTarget=null;
        getTempWebElemHeaderValue=driver.findElements(elemHeaderNavigationList);

        String tempDataLoad=null;

        for (int i=0;i<5;i++){
            loadTempAttrTarget=getTempWebElemHeaderValue.get(i).getAttribute("target");
            if(loadTempAttrTarget.isEmpty()){
                loadTempAttrTarget="Not Present";
            }
            tempDataLoad=String.format("%s;%s;%s",getTempWebElemHeaderValue.get(i).getText(),getTempWebElemHeaderValue.get(i).getAttribute("href"),loadTempAttrTarget);
            log.info(tempDataLoad);
            getTempHeaderValue.add(tempDataLoad);
            tempDataLoad=null;
            loadTempAttrTarget=null;
        }
        return getTempHeaderValue;
    }

    private String compTitlePage(String pageName){
        HashMap<String, String> mapTitle = FrameworkConstants.mapTitleExtract();
        return mapTitle.get(pageName);
    }

    public String titlePage(String pageName){
        return compTitlePage(pageName);
    }

    private List <String> getHeaderLinkSecondaryDetails()  {
        List <String> getTempHeaderValue=new ArrayList<>();
        List <WebElement> getTempWebElemHeaderValue=new ArrayList<>();
        webUtil.waitUntilElementVisible(secNavHeaderList,4);
        String loadTempAttrTarget=null;
        getTempWebElemHeaderValue=driver.findElements(secNavHeaderList);
        String tempDataLoad=null;
        webUtil.waitUntilElementVisible(secNavHeaderValueDropDown,4);
        String loadValue1=driver.findElement(secNavHeaderValueDropDown).getText();
        getTempHeaderValue.add(String.format("%s;%s;%s",loadValue1,"","Not Present"));
        for (int i=0;i<getTempWebElemHeaderValue.size();i++){
            loadTempAttrTarget=getTempWebElemHeaderValue.get(i).getAttribute("target");
            if(loadTempAttrTarget.isEmpty()){
                loadTempAttrTarget="Not Present";
            }
            tempDataLoad=String.format("%s;%s;%s",getTempWebElemHeaderValue.get(i).getText(),getTempWebElemHeaderValue.get(i).getAttribute("href"),loadTempAttrTarget);
           log.info(tempDataLoad);
            getTempHeaderValue.add(tempDataLoad);
            tempDataLoad=null;
            loadTempAttrTarget=null;
        }
        return getTempHeaderValue;
    }



}
