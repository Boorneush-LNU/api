package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.BuyNow.YourOrder;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.Context;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    public By savePref = By.xpath("//*[contains(@class,'save')]/button");
    public By BackToSS = By.id("backLinkWithinLoginBlock");
    public By Back = By.xpath("//a[@class='button button--tertiary button--small button--alt gtm-cta']");
    public By signIn = By.xpath("//*[contains(@id,'signInBlock1')]/a");
    public By forgotPwddidnotreceivelink = By.xpath("//a[@href='javascript:showResendCode()']");
    public By otpPgedidnotreceivelink = By.xpath("//button[contains(@class, 'js-modalTrigger') and contains(@class, 'm34-member-login-field-link')]");
    public By stateSavingtabbtn = By.xpath(".//*[@title='Need a secure home for your savings?']");
    public By hdr = By.xpath("//*[@class='m01-banner--title']");
    public By forgotPwd = By.xpath("//button[contains(@id,'Forgot') or contains(text(),'Forgot')]");
    public By lockedUserError = By.xpath("//section[contains(@class,'locked-account')]");
    private static final By HDR1 = By.tagName("h1");
    private static final By PARAGRAPH = By.tagName("p");
    private static final By STRONG = By.tagName("strong");
    private static final By SECONDARY_BUTTON = By.xpath("//div[contains(@class,'cta-container')]//a[1]");
    public static final By PRIMARY_BUTTON = By.xpath("//div[contains(@class,'cta-container')]//a[2]");
    //Home page);
    private final By btnContinueToReviewSignIn = By.id("btnSubmitCart");
public static final By Lang = By.xpath("//ul[@class='language-toggle--list']/li/a[@title='Gaeilge']");
    public By registration = By.xpath("//*[@id='registerBlock1']/a");
    private final By btnBuyNow = By.xpath("//li[contains(@id,'buyMoreBlock')]/a");
    private final By btnBuyNowSS = By.id("buyNowCTA");
    public By englishLanguage = By.xpath("//button[@class='language-toggle--selected']");
    public By gaeilgeLanguage = By.xpath("(//a[@class='gtm-link-click header__nav-link'])[2]");
    public By home = By.xpath("//a[contains(text(),'Home')]");
    public By ourProducts = By.xpath("//a[@title='Our Products']");
    public By prizeBonds = By.xpath("//a[@title='Prize Bonds']");
    public By aboutStateSaving = By.xpath("//a[@title='About State Savings']");
    public By helpAndSupport = By.xpath("//a[@title='Help and Support']");
    public By allProducts = By.xpath("//a[@title='View all products']");
    public By checkemaildidnotreceivelink = By.xpath("//a[@href='javascript:showResendEmailPassword()']");
    //    footer of homepage
    public By aboutUs = By.xpath("//a[@title='About Us']");
    public By contactUs = By.xpath("//a[@title='Contact Us']");
    public By siteUseTermAndConditions = By.xpath("//a[contains(text(),'Site Use Terms and Conditions')]");
    public By termAndConditions = By.xpath("//a[contains(text(),'Terms & Conditions')]");
    public By accessibility = By.xpath("//a[@title='Accessibility']");
    public By dataProtection = By.xpath("//a[@title='Data Protection']");
    public By cookiesPolicy = By.xpath("//a[@title='Cookie Policy']");
    public By cookiesWeUse = By.xpath("//a[@title='Cookies We Use']");
    public By NTMA = By.xpath("//a[contains(text(),'NTMA')]");
    public By anPost = By.xpath("//a[contains(text(),'An Post')]");
    public By prizeBondCompany = By.xpath("//a[contains(text(),'Prize Bond Company')]");
    public By footerLogo = By.xpath("//a[@title='Go back to homepage']");
    WebDriver driver;
    public By lblpreference = By.xpath("//*[contains(text(),'Privacy Preference Centre')]");
    public By btnAllowall = By.xpath("//*[contains(text(),'Allow All')]");

    private final WebDriverUtil webUtil;
    Context testContext;
    private By tempAcceptCookies;
    private By acceptCookies;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }


    public void userpermission() {
        try {

            if (webUtil.isElementVisible(lblpreference, 5)) {
                ExtentCucumberAdapter.addTestStepLog("Privacy Preferences displayed");
                tempAcceptCookies = By.xpath("(//button[contains(text(),'Allow All')])[2]");

                acceptCookies = By.id("accept-recommended-btn-handler");
//                webUtil.click(acceptCookies);
                if (webUtil.isElementVisible(tempAcceptCookies, 5))
                    driver.findElement(tempAcceptCookies).click();
                else {
                    driver.findElement(acceptCookies).click();
                }




            }
        } catch (Exception ignored) {
            ExtentCucumberAdapter.addTestStepLog("Preferences permission enabled already");

        }
    }

    public void validateErrorMessageForLockedUser(List<String> errorMessage) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(lockedUserError, 20);


        // Validate Header text

        String txtHeader = sliderRootElement.findElement(HDR1).getText();
        webUtil.CompareString(txtHeader, String::equals, errorMessage.get(0), "Header");


        String txtBody = sliderRootElement.findElement(PARAGRAPH).getText();
        String expectedDescription=String.join(",",errorMessage.subList(1,4)).trim();
        webUtil.CompareString(txtBody, String::equals, expectedDescription, "Description");

        String txtSubBody = sliderRootElement.findElement(STRONG).getText();
        webUtil.CompareString(txtSubBody, String::equals, errorMessage.get(4).trim(), "Sub Description");

        // Validate Resend code button text
        String txtBackBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtBackBtn, String::equals, errorMessage.get(5).trim(), "Back to Sign in");
        // Validate cancel button text

        String txtHepSupportBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtHepSupportBtn, String::equals, errorMessage.get(6).trim(), "Back to Sign in");


    }


    public void GaeilgeLang()  {

        WebElement dropdownToggle = driver.findElement(By.xpath("//button[@class='language-toggle--selected']"));
        dropdownToggle.click();

        //ul[@class='language-toggle--list']//li[2]  -- This also clicks on Gaeilge.

        WebElement option = driver.findElement(By.xpath("//ul[@class='language-toggle--list']//a[text()='Gaeilge']"));
        option.click();
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Language changed from English to Gealige");
    }

    @SneakyThrows
    public void BacktoSS(){
        try {
            Thread.sleep(3000);
            webUtil.clickLog(Back, "Click Back button in Details Page");
//            webUtil.click(Back);
            Thread.sleep(5000);
            webUtil.clickLog(BackToSS, "Click Back to SS in Dashboard  Page");
//            webUtil.click(BackToSS);
            Thread.sleep(5000);
        } catch (NoSuchElementException e){
            System.err.println("Element not found: " + e.getMessage());
            Assert.fail("Missing element in BacktoSS");
        }
    }



    public void clickSignIn() {

        webUtil.clickLog(signIn, "Sign in button");
    }

    public YourOrder clickBuyNowOnHomePage() {
        webUtil.clickLog(btnBuyNow, "Buy Now Button on Homepage");
        return new YourOrder(driver);
    }

    @SneakyThrows
    public void clickBuyNowOnHomePageSS() {
        Thread.sleep(2000);
        webUtil.clickLog(btnBuyNowSS, "Buy Now Button on Homepage");
    }

    @SneakyThrows
    public void clickContinueSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmitCart")));
        webUtil.scrollToView(btnContinueToReviewSignIn);
        element.click();
        Thread.sleep(7000);
//        webUtil.scrollToView(btnContinueToReviewSignIn);
//        webUtil.clickLog(btnContinueToReviewSignIn, "Continue to review button on your order page");
    }





    public void clickFooterLink(String linkName)
    {
        By elefooterLink=By.xpath("//footer//ul//li");
        webUtil.getElements(elefooterLink)
                .stream()
                .filter(s->s.getText().equals(linkName))
                .findFirst()
                .ifPresent(WebElement::click);
    }
}


