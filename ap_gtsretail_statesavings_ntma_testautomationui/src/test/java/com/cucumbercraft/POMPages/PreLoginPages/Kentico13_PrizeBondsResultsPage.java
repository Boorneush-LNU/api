package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2
public class Kentico13_PrizeBondsResultsPage extends Kentico13_MasterPages{

    WebDriver driver;
    public Kentico13_PrizeBondsResultsPage(WebDriver driver){
        this.driver=driver;
    }

    public final By resultsPgHdr= By.xpath("//*[@class='m03-title_text_button--title']");


}
