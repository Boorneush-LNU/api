package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.Models.FrameworkConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Kentico13_PrizeBondWinningPaymentOption extends Kentico13_MasterPages {
    private WebDriver driver;
    Kentico13_ComponentHeader comp_Header;

    public Kentico13_PrizeBondWinningPaymentOption(WebDriver driver) {
        this.driver = driver;
        comp_Header = new Kentico13_ComponentHeader(driver);
    }

    private final By highlightsLinkStateSavingsReg = By.xpath("(//div[@class='large-11 medium-12 columns end'])//p[2]");
    private final By PBLink = By.linkText("What are my Prize Bond winnings payment options?");


    public String fetchUrlBrowser(){
        return driver.getCurrentUrl();
    }

    public String getPageBrowserTitle() {
        return driver.getTitle();
    }

    public void launchPBpageUrl()  {
        driver.get(FrameworkConstants.envLinkFetchPB());
    }

    public String getPBPrizeWinningOptionSectionText(){
        return getTextByLocator(NewCommSectionText);
    }

    public String getPBPrizeWinningOptionSectionTitle(){
        try {
            return getTextByLocator(NewCommSectionTitle);
        } catch (Exception e){
            System.out.println("Unexpected error " + e.getMessage());
            return "";
        } }

//    public String getPBPrizeWiiningOptionSecHeaderTwo() {
//        return getTextByLocator(SectionTwoTitle);
//    }

    public String getPBPrizeWinningOptionDesLinks() {
        return getTextByLocator(highlightsLinkStateSavingsReg).trim();
    }


    public List<String> getLinkDetailsPBHighlights(){
        return getBtnClickAndNewTabLocator(PBLink);
    }



}
