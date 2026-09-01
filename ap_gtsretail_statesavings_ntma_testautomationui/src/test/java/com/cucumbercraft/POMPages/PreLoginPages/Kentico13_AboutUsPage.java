package com.cucumbercraft.POMPages.PreLoginPages;

import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.framework.TestHarness;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Kentico13_AboutUsPage extends Kentico13_MasterPages {
    private WebDriver driver;
    public Kentico13_AboutUsPage(WebDriver driver){
        this.driver=driver;
    }
    private final By cntntbanner=By.className("m01-banner-text");
    private final By aboutStateSavingsDetailsH4Ref=By.xpath("//div[@class='m04-2col_text--block-item']/h4");
    private final By aboutStateSavingsDetailsPRef=By.xpath("//div[@class='m04-2col_text--block-item']/p");
    private final By highlightsLinkStateSavingsReg=By.xpath("//div[@class='m04-2col_text--block-item']/p/span/a");
    private final By hdrReasonsToSave = By.xpath("//*[text()='Reasons to save']");
    private final By summaryTxtReasonsToSave = By.xpath("//div[@class='m03-title_text_button--content']/p");

    public String getBannerTitleAboutUsPg()  {
        return getTextByLocator(bannerTitle);
    }

    public String getBannerTextAboutUsPg() {
        return getTextByLocator(cntntbanner);
    }

    public ArrayList<String> getAboutSUsHighlightsText()  {

        ArrayList <String> cumulativeList=new ArrayList<String>();
        String h4Txt=null;
        String pTxt=null;
        webUtil.waitUntilElementVisible(aboutStateSavingsDetailsH4Ref,4);
        webUtil.waitUntilElementVisible(aboutStateSavingsDetailsPRef,4);
        List<WebElement> elemList=driver.findElements(aboutStateSavingsDetailsH4Ref);
       for (int iterator=0;iterator<elemList.size();iterator++){
           h4Txt=elemList.get(iterator).getText();
           pTxt=driver.findElements(aboutStateSavingsDetailsPRef).get(iterator).getText();
           cumulativeList.add(h4Txt.concat(";").concat(pTxt));
           h4Txt=null;
           pTxt=null;
       }
        return cumulativeList;
    }

    public List<String> getLinkDetailsStateSavingsRegHighlights(){
        return getBtnClickAndNewTabLocator(highlightsLinkStateSavingsReg);
    }

    public String getHdrReasonsToSave(){
        return getTextByLocator(hdrReasonsToSave);
    }

    public String getSummaryTextReasonsToSave(){
        return getTextByLocator(summaryTxtReasonsToSave);
    }


}
