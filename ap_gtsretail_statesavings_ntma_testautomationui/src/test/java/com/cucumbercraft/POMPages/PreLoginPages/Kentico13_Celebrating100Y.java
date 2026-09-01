package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.Models.FrameworkConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Kentico13_Celebrating100Y extends Kentico13_MasterPages {

    private WebDriver driver;
    Kentico13_ComponentHeader comp_Header;

    public Kentico13_Celebrating100Y(WebDriver driver) {
        this.driver = driver;
        comp_Header = new Kentico13_ComponentHeader(driver);
    }

    private final By NewCommSectionTitle = By.xpath("(//div[@class='large-11 medium-12 columns end'])//h2");
    private final By NewCommSectionText =By.xpath("//div[@class='m03-title_text_button--content']/p[1]");
    private final By SectionTwoTitle = By.xpath("(//div[@class='large-11 medium-12 columns end'])//p[2]");
    private final By highlightsLinkStateSavingsReg = By.xpath("(//div[@class='large-11 medium-12 columns end'])//p[3]");
    private final By SSOLink = By.linkText("Getting Started with Ireland State Savings Online");
    private final By RegisterSSO = By.linkText("Register for Ireland State Savings Online today");


    public void launchCelebating100YpageUrl(){
        driver.get(FrameworkConstants.envLinkFetchCelebrate());
    }

    public String fetchUrlBrowser(){
        return driver.getCurrentUrl();
    }

    public String getPageBrowserTitle() {
        return driver.getTitle();
    }

    public String getCelebrateSectionText(){
        return getTextByLocator(NewCommSectionText);
    }

    public String getCelebrateSectionTitle(){
        return getTextByLocator(NewCommSectionTitle);
    }

    public String getCelebrateSecHeaderTwo() {
        return getTextByLocator(SectionTwoTitle);
    }

    public String getCelebrateDesLinks() {
        return getTextByLocator(highlightsLinkStateSavingsReg);
    }


    public List<String> getLinkDetailsStateSavingsCelebrateHighlights(){
        return getBtnClickAndNewTabLocator(SSOLink);
    }


    public List<String> getLinkDetailsStateSavingsCelebrateHighlights1(){
        return getBtnClickAndNewTabLocator(RegisterSSO);
    }


}
