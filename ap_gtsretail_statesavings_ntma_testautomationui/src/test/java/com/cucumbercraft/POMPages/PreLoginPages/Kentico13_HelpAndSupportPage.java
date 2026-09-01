package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Log4j2
public class Kentico13_HelpAndSupportPage extends Kentico13_MasterPages {
    private WebDriver driver;
    public Kentico13_HelpAndSupportPage(WebDriver driver){
        this.driver=driver;
    }
    private final By searchField=By.id("txtSearchKeyword");
    private final By btnSearch=By.id("btnSearch");
    private final By tipHeader = By.xpath("//div[@class='m11-body_copy--content general-content']/h6");
    private final By saveHeader = By.xpath("//div[@class='m11-body_copy--content general-content']/h1");
    private final By howToUseHdr=By.xpath("//div[@class='m11-body_copy--content general-content']/h3");
    private final By sscnFAQ=By.xpath("//a[@href='/help-support/help-articles/what-is-state-savings-customer-number-(sscn)']");
    private final By sscniFrame=By.xpath("//a[starts-with(@class, 'ytSpecButtonShapeNextHost')]");
    By sscnImage = By.xpath("//img[contains(@src,'SSCN-QR-rebrand-CVE-01.png')]");

    public String hdrBanner(){
       return getTextByLocator(hdrBannerTitleHelpSupport);
    }

    public String getPlaceHolderTxt(){
        webUtil.waitUntilElementVisible(searchField,4);
        return driver.findElement(searchField).getAttribute("placeholder");
    }

    public List<String> getSSCNFAQ(){
        return  getBtnClickAndNewTabLocator(sscnFAQ);
    }

    public List<String> getiFrameSSCN(){
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@src,'youtube')]"));
        driver.switchTo().frame(iframe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sscniFrame));

        List<String> result = getBtnClickAndNewTabLocator(sscniFrame);

        driver.switchTo().defaultContent();
        return result;

    }

    public String getTipHdr(){
        return getTextByLocator(tipHeader);
    }

    public String getSaveHdr(){
        return getTextByLocator(saveHeader);
    }

    public String getSSCNDesc() {
        List<WebElement> allContents = driver.findElements(By.xpath("//div[@class='m11-body_copy--content general-content']//p"));
        String sscnDesc = allContents.stream()
                .map(e -> e.getText().replaceAll("\\s+", " ").trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
        System.out.println("sscn: " + sscnDesc);
        return sscnDesc;
    }

    public String getHowToUseHdr(){
        return getTextByLocator(howToUseHdr);
    }

    public void validateImageLoaded() {

        try {
            WebElement imageElement = driver.findElement(sscnImage);

            if (imageElement.isDisplayed()) {
                System.out.println("SSCN image is visible on the page.");
            } else {
                Assert.fail("SSCN image is present but not visible.");
            }

        } catch (
                NoSuchElementException e) {
            Assert.fail("SSCN image not found on the page.");
        }
    }



    public void validateiFrame() {

        boolean isIframePresent=driver.findElements(By.xpath("//iframe[@class='optanon-category-C0005']")).size()>0;
        if(isIframePresent){
            System.out.println("iFrame is present");
        }
        else {
            System.out.println("iFrame is not present");

        }
    }




}
