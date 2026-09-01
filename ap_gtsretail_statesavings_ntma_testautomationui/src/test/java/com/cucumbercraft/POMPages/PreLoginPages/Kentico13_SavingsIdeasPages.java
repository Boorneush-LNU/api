package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
@Log4j2
public class Kentico13_SavingsIdeasPages extends Kentico13_MasterPages{

    private WebDriver driver;
    private Kentico13_ComponentSavingIdeas comp_SavingIdeas;
    public Kentico13_SavingsIdeasPages(WebDriver driver){
        this.driver=driver;
        comp_SavingIdeas=new Kentico13_ComponentSavingIdeas(driver);
    }

    private final By generalContentPara=By.xpath("//*[@class='m11-body_copy--content general-content']/p");
    private final By generalContentParaLink=By.xpath("//*[@class='m11-body_copy--content general-content']/p/a");
    private final By generalContentParaLinkChildcarePlusOurProd=By.xpath("//*[@class='m11-body_copy--content general-content']/p[2]/a");

    public List<String> getGeneralContentParaList (){
        webUtil.waitUntilElementVisible(generalContentPara,4);
        List <String> generalParaList=new ArrayList<>();
        for (WebElement elemVar:driver.findElements(generalContentPara)){
            generalParaList.add(elemVar.getText());
        }
        return generalParaList;
    }

    public String bannerTitleSavingsIdeasPg(){
        return getTextByLocator(bannerTitle);
    }

    public String bannerTextSavingsIdeasPg(){
        return getTextByLocator(bannerText);
    }

    public String carouselHdrSavingsIdeasPg(){
        return getTextByLocator(carouselHdr);
    }

    public List <String> viewAllOurProductsBtnSavingsIdeasPg(){
        return getBtnClickAndNewTabLocator(carouselSectionViewAllProdBtn);
    }

    public List <String> getGeneralContentParaLink1Details(){
        return getBtnClickAndNewTabLocator(generalContentParaLink);
    }

    public List <String> getGeneralContentParaLink2DetailsOurProductsChildcarePlus(){
        return getBtnClickAndNewTabLocator(generalContentParaLinkChildcarePlusOurProd);
    }
    public boolean clickNavigationConfirmationSavingIdeas(String pageName){
        return comp_SavingIdeas.titlePageSavingsIdeas(pageName).equalsIgnoreCase(clickNavigationTabNameSavingIdeasPg(pageName));
    }

    private String clickNavigationTabNameSavingIdeasPg(String pageName){
        webUtil.clickLogW(navigationItem(pageName),pageName);
        return getTextByLocator(bannerTitle);
    }

}

