package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_BeginRegistrationPage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Kentico13_SignInPageStepDefinitions extends MasterStepDefs{
    WebDriver driver = DriverManager.getWebDriver();
    Kentico13_SignInPage signInPage=new Kentico13_SignInPage(driver);


    @And("Validate content for Banner Section for Sign In page")
    public void validateContentForBannerSectionForSignInPage()  {
        extractExcelValue= FrameworkConstants.getExpMapSignInPg();
        this.pageName="Sign in";
        logReportStepValidationStart("Banner section validation Initiated");
        strCompareString(signInPage.getBannerTitleSignInPg(),extractExcelValue.get("expBannerTitle"), "Banner Title ");
        strCompareString(signInPage.getBannerTextSignInPg(),extractExcelValue.get("expBannerText"), "Banner Text ");
        logReportStepValidationEnd("Banner section validation Completed");
    }


    @And("Validate content in Sign In Panel section for Sign In page")
    public void validateContentInSignInPanelSectionForSignInPage() {
        logReportStepValidationStart("Sign In Panel section validation Initiated");
        strCompareString(signInPage.getSignInPanelHdr(),extractExcelValue.get("expSignInPanelHdr"),"Header ");
        strCompareString(signInPage.getSignInPanelEmailAddLbl(),extractExcelValue.get("expSignInPanelEmailAddLbl"),"Email Address label ");
        strCompareString(signInPage.getSignInPanelEmailAddPlaceholderTxt(),extractExcelValue.get("expSignInPanelEmailAddPlaceholderTxt"),"Email Address Field placeholder text ");
        strCompareString(signInPage.getSignInPanelPwdLbl(),extractExcelValue.get("expSignInPanelPwdLbl"),"Password label ");
        strCompareString(signInPage.getSignInPanelBtnLbl(),extractExcelValue.get("expSignInPanelBtnLbl"),"Button label ");
        strCompareString(signInPage.getSignInPanelFrgtPwdLink(),extractExcelValue.get("expSignInPanelFrgtPwdLink"), "Forgot your Password link ");
        logNavigation(signInPage.clickSignInPanelFrgtPwdLink("Forgot your Password?","Reset your Password"),"","Reset your Password slider");
        logNavigation(signInPage.clickCancelBtnResetYourPwdSlider(extractExcelValue.get("expBannerTitle")),"","Reset your password slider is cancelled and Sign In page");
        strCompareString(signInPage.getSignInPanelRegisterText(),extractExcelValue.get("expSignInPanelRegisterText"),"Register/Sign Up text");
        logNavigation(signInPage.clickSignUpNowLink(extractExcelValue.get("expSignInPanelSignUpLink")),"","Registration - Personal Details page ");
        strCompareString(signInPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"),"User is navigated back to Sign In page");
        logReportStepValidationEnd("Sign In Panel section validation Completed");
    }

    @And("Validate content in Highlights or Criteria section for {string} Page")
    public void validateContentInHighlightsOrCriteriaSectionForPage(String pageName)  {
        logReportStepValidationStart("Criteria/Highlights section validation Initiated");
        strCompareString(signInPage.getCriteriaSectionTitle(),extractExcelValue.get("expCriteriaSectionTitle"),"Highlights/Criteria Section Title ");
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsLeftBulletPtArr")),signInPage.getHighlightsLeftBulletPtArr(),"Highlights Left bullet point ");
        getListItemsComparison(expListContent(extractExcelValue.get("expHighlightsRightBulletPtArr")),signInPage.getHighlightsRightBulletPtArr(),"Highlights Right bullet point ");
        logNavigation(signInPage.getRegisterBtnValidation("Begin registration"),"","Registration Page");
        strCompareString(signInPage.navigateBackPg(),extractExcelValue.get("expBannerTitle"),"User Navigation back on Sign In page");
        logReportStepValidationEnd("Criteria/Highlights validation Completed");
    }

    @Given("Launch the URL")
    public void launchTheURL() throws InterruptedException {

        driver.get("https://anpostcom-dev.dev-anpost.com/Shop/Mobile/Sim-Only-Plans");
        Thread.sleep(4000);

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        By dPath=By.xpath("//*[@class='s01__card__btn--wrapper']/button");
        Thread.sleep(4000);

        driver.findElements(dPath).get(1).click();
        Thread.sleep(4000);


//        driver.get("https://statesavings-preprod.dev-anpost.com/our-products");
//        Thread.sleep(2000);
//        driver.findElement(By.id("accept-recommended-btn-handler")).click();
//        By locPr=By.className("m01-banner--title");
//        By locNr=By.linkText("About Us");
//        System.out.println(getTextLocWait(locPr));
//        System.out.println(getClickLocWait(locNr));
//        Thread.sleep(2000);



//        driver.get("https://app-anpost-sswbemyaccountadmin-d-ne01.azurewebsites.net/");
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Saahil.SunilGulve@anpost.ie");
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//input[@type='submit']")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("GoldenGood2025");
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//input[@type='submit']")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//input[@type='submit']")).click();
//        Thread.sleep(5000);
//        String data1=driver.findElement(By.xpath("//div/h3")).getText();
//        System.out.println(data1);
    }

    WebDriverUtil util1=new WebDriverUtil(driver);
    public String getTextLocWait(By loc1){
       return util1.waitUntilElementVisible(loc1,5).getText();
    }

    public String getClickLocWait(By loc2){
        util1.waitUntilElementLocated(loc2,5).click();
        return driver.getTitle();
    }
}
