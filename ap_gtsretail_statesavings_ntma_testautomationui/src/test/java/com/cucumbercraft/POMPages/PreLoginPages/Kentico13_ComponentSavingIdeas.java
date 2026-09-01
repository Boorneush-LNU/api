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
public class Kentico13_ComponentSavingIdeas extends Kentico13_MasterPages{
    private final WebDriver driver;
    public Kentico13_ComponentSavingIdeas(WebDriver driver){
        this.driver=driver;
    }
    private final By savingIdeasSectionArr= By.xpath("//div[contains(@class,'m07-carousel_cards--slide gtm-carousel')]/a");

    public List<String> getSavingIdeasComponentListDetails() {
        return getSavingsIdeasSectionLinkDetails();
    }
    private List <String> getSavingsIdeasSectionLinkDetails(){
        List <String> getTempHeaderValue=new ArrayList<>();
        List <WebElement> getTempWebElemHeaderValue=new ArrayList<>();
        webUtil.waitUntilElementVisible(savingIdeasSectionArr,4);
        getTempWebElemHeaderValue=driver.findElements(savingIdeasSectionArr);
        String tempDataLoad=null;
        String targetText=null;
        String tempTargetText=null;
        for (int i=0;i<3;i++){
            targetText=getTempWebElemHeaderValue.get(i).getAttribute("target");
            if(targetText.isEmpty()){
                tempTargetText="Not Present";
            }
            else{
                tempTargetText=targetText;
            }
            tempDataLoad=String.format("%s;%s;%s",getTempWebElemHeaderValue.get(i).getText(),getTempWebElemHeaderValue.get(i).getAttribute("href"),tempTargetText);
            getTempHeaderValue.add(tempDataLoad);
            tempDataLoad=null;
            tempTargetText=null;
            targetText=null;
        }
        return getTempHeaderValue;
    }

    public String titlePageSavingsIdeas(String pageName){
        return compSavingIdeasTitlePage(pageName);
    }

    private String compSavingIdeasTitlePage(String pageName){
        HashMap<String, String> mapTitle = FrameworkConstants.mapSavingIdeasSectionTitleExtract();
        return mapTitle.get(pageName);
    }


}
