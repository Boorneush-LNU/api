package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Celebrating100Y;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_Homepage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_PrizeBondWinningPaymentOption;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_RepayReinvest;
import com.cucumbercraft.POMPages.StateSavingsDashboardPage;
import com.cucumbercraft.POMPages.ExpeditedReg.Check_Your_Inbox;
import com.cucumbercraft.POMPages.ExpeditedReg.ExpeditedLetsGetStarted;
import com.cucumbercraft.POMPages.ExpeditedReg.Expedited_EnterEmail;
import com.cucumbercraft.POMPages.ExpeditedReg.Expedited_EnterMobileNumber;
import com.cucumbercraft.POMPages.IBAN.Add_IBAN;
import com.cucumbercraft.POMPages.ProfileAndSettingsPg;
import com.cucumbercraft.POMPages.RepayReinvest.Holdings;
import com.cucumbercraft.POMPages.RepayReinvest.SelectProduct;
import com.cucumbercraft.POMPages.Security_Page;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.POMPages.WebReg.Web_CreatePassword;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.Models.ExcelReader;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;


public class TitleSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    public static Map<String, String> regstrnData = new HashMap<>();
    String workBook = FrameworkConstants.getExcelLocationAutomationRegression();

    private final Holdings holdings = new Holdings(driver);
    private final SignInPg login = new SignInPg(driver);
    private final StateSavingsDashboardPage dashboard = new StateSavingsDashboardPage(driver);
    private final SelectProduct select = new SelectProduct(driver);
    private final Security_Page secure = new Security_Page(driver);
    Expedited_EnterMobileNumber mobile = new Expedited_EnterMobileNumber(driver);
    Web_CreatePassword createPassword = new Web_CreatePassword(driver);
    Expedited_EnterEmail email = new Expedited_EnterEmail(driver);
    ExpeditedLetsGetStarted letGS = new ExpeditedLetsGetStarted(driver);
    ProfileAndSettingsPg pfPg = new ProfileAndSettingsPg(driver);
    Check_Your_Inbox inbox = new Check_Your_Inbox(driver);
    Kentico13_RepayReinvest RepayReinvest = new Kentico13_RepayReinvest(driver);
    Kentico13_PrizeBondWinningPaymentOption PBWinningPayment = new Kentico13_PrizeBondWinningPaymentOption(driver);
    Kentico13_Celebrating100Y Celebrate = new Kentico13_Celebrating100Y(driver);
    Kentico13_Homepage homePage=new Kentico13_Homepage(driver);
    Add_IBAN add = new Add_IBAN(driver);
    String url = properties.getProperty("OTPEndPoint");
    String OTPresponse;

    WebDriverUtil webUtil = new WebDriverUtil(driver);
    static Logger log = LogManager.getLogger(MasterStepDefs.class);


    public  Excelutils excelUtils = new Excelutils();


    @Given("^User login with valid credential \"([^\"]*)\"$")
    public void userLoginwithValidCredential(String Tcno) throws Throwable {

        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = "Title_Validation";
        data = excelutils.getData(path, sheetName, Tcno, TestData.class,"getTestcaseName");
        Assertions.assertThat(data).isNotNull();
        try {
            login.LightLogin(data.getUsername(), data.getPassword(), data.getMobileNumber());
            ExtentCucumberAdapter.addTestStepLog("Login Successful");


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Given("^User enters invalid credentials \"([^\"]*)\"$")
    public void userEntersInvalidCredentials(String Tcno) throws Throwable {


        try {

    login.EnterCredentials("UAT-WF-User22@mailinator.com", "Dummy!2020");



        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Enter and Re enter email for Title$")
    public void enterAndReEnterEmailforTitle() {
        try {
            email.enterEmailAddressforTitle();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Given("^User enters valid credentials \"([^\"]*)\"$")
    public void userEntersvalidCredentials(String Tcno) throws Throwable {


        try {

            login.EnterCredentials("AMLOnlineUser14-CDEN@mailinator.com", "Dummy!2025");


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user enter the details for Title \"([^\"]*)\"$")
    public void userEnterTheDetailsTitle(String scenario) {
        try {

            ExcelReader excelReader = new ExcelReader();
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = "Title_Validation";
            regstrnData = excelReader.reqExceldata(path, sheetName, scenario);
            updateTestDataConverter(regstrnData);


            letGS.enterdetails();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Then("^user enter the details for ECR \"([^\"]*)\"$")
    public void userEnterTheDetailsECR(String scenario) {
        try {
            ExcelReader excelReader = new ExcelReader();
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = "Title_Validation";
            regstrnData = excelReader.reqExceldata(path, sheetName, scenario);
            updateTestDataConverter(regstrnData);


            letGS.enterdetailsecr();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^open Email for web registration")
    public void openEmailforWebRegistartion() {
        try {

            String token = inbox.emailVerificationToken(regstrnData.get("EmailAddress1"));
            inbox.launchEmailToken(token);
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^open Email for ECR")
    public void openEmailforECR() {
        try {

            String token = inbox.emailVerificationToken(regstrnData.get("EmailAddress"));
            inbox.launchEmailToken(token);
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^Enter all the details for mobile number$")
    public void enteralltheDetailsForMobileNumber() {
        try {

            mobile.enterMobileNumberdetailsforTitle();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^Enter password web Registration Title$")
    public void enterPasswordForWebRegistration() {
        try {
            createPassword.enterPasswordWebTitle();
            createPassword.clickShowPassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user enter invalid details for Title$")
    public void userEnterinvalidDetailsTitle() {
        try {


            letGS.enterinvaliddetails();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("User navigates to Change email: number {string} email {string}")
    public void userNavigatesToChangeemail(String number, String email) throws Throwable {
        try {
            OTPresponse = apiDriver.getOTP(url, 200, number);
            webUtil.sendKeys(pfPg.newEmailtxt, email);
            ExtentCucumberAdapter.addTestStepLog("Email entered successfully");
            webUtil.click(pfPg.emailChangeConfirmButton);
            webUtil.sendKeys(pfPg.EmailEnterSecCodeText, OTPresponse);
            ExtentCucumberAdapter.addTestStepLog("OTP entered successfully");
            webUtil.click(pfPg.EmailVerifyButton);

        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

    public Map<String, String> updateTestDataConverter(Map<String, String> updatedTestData) {

        for (String name : regstrnData.keySet()) {

            String url = regstrnData.get(name);
            if (url.equalsIgnoreCase("<Blank>")) {
                updatedTestData.replace(name, url, "");
            }
        }
        return updatedTestData;
    }

    @And("^User logout$")
    public void userLogout() {
        try {
            login.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("User logged out successfully");
    }

    @When("user clicks on View and Manage button to access summary")
    public void userSelectsTheProductAndClicksOnViewManageButton() {
        try {
            dashboard.clickProduct(data.getProduct());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @SneakyThrows
    @And("User choose {string}")
    public void userChooseOption(String option) {
        holdings.selectOption(option);
    }

    @And("user selects product from the dropdown list and proceeds to enter the desired amount")
    public void userSelectsProductFromDropdownAndEntersTheAmount() throws Throwable {

        select.userClicksOnDropdownMenuAndSelect(data.getReinvestProducts(), data.getReinvestAmounts());
    }

    @And("User clicks confirm button to finalize product and amount for reinvestment")
    public void userClicksOnConfirmButton() {
        try {
            select.confirmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Click confirm button on review page and finalize the transaction")
    public void clickOnTheReviewPageConfirmButton() {
        try {
            select.clickReviewConfirmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User enter valid otp on the security code page$")
    public void userEnterTheValidOtp() {
        try {
            secure.enterOtp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Confirm transaction by clicking confirm button on verification code page")
    public void clickOnConfirmButton() {
        try {
            secure.clkCnfrmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("open Email and click on link {string}")
    public void openEmailAndClickOnLink(String email) {
        try {

            String token = inbox.emailVerificationToken(email);
            inbox.launchEmailToken(token);

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("open link and validate Title {string}")
    public void openLinkAndValidateTitle(String linkName) {
        try {
            switch (linkName) {
                case "NewComms-Repay Reinvest":
                    RepayReinvest.launchRepayReinvestpageUrl();
                    break;

                case "NewComms-PrizeBond-WinningPaymentOption":
                    PBWinningPayment.launchPBpageUrl();
                    break;

                case "NewComms-Celebrate100Years":
                    Celebrate.launchCelebating100YpageUrl();
                    break;
                case "NewComms-SSCN":
                    homePage.launchSSCNpageUrl();
                    break;

            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

//        @And("open link and validate Title NewComms-SSCN")
//    public void openLinkAndValidateTitleSSCN() {
//        try {
//            Celebrate.launchCelebating100YpageUrl();
////            login.launchUrl(link);
//        } catch (Exception e) {
//            org.junit.Assert.fail(e.getMessage());
//        }
//    }

    @Given("Launch k13 application")
    public void LaunchK13application() {
        try {
            login.launchUrl("https://statesavings-qa.dev-anpost.com/");
            webUtil.clickLog(By.xpath("//button[@id='accept-recommended-btn-handler']"), "help");
            webUtil.clickLog(By.xpath("//a[text()='Help and Support']"), "help");
            webUtil.waitForPageLoaded();
            webUtil.scrollToView(By.xpath("//*[@href='/help-support/help-articles/how-to-purchase']"));
            webUtil.clickLog(By.xpath("//*[@href='/help-support/help-articles/how-to-purchase']"), "help");
            webUtil.clickLog(By.xpath("//a[text()='How to Purchase?']"), "help");
            webUtil.gettextlog(By.xpath("//*[@class='m14-help_content--article']/section"), String::equals, "");
            webUtil.waitForPageLoaded();
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }


    @Then("Validate Title {string}")
    public void validateTitle(String expected_page) {

        String title = driver.getTitle();

        webUtil.CompareString(title, String::equals, expected_page);


    }


    @And("Click on Back to your savings")
    public void clickOnBackToYourSavings() {
        driver.findElement(By.xpath("//a[text()='Back to Your Savings']")).click();
    }

    @And("Click on Backtostatesavings")
    public void clickOnBacktostatesavings() {
        driver.findElement(By.id("backLinkWithinLoginBlock")).click();

    }

    @And("Click on Backtostatesavings from Thank you")
    public void clickOnBacktostatesavingsfromThankyou() {
        driver.findElement(By.xpath("//a[@class='gtm-link-click header__nav-link header__nav-link--back ']")).click();
    }

    @And("User click on Backtostatesavings")
    public void userclickOnBacktostatesavings() {
        driver.findElement(By.xpath("//a[text()='Back to statesavings.ie']")).click();
    }





    @And("Validate Password reset page")
    public void validatePasswordResetPage() {
        webUtil.waitForPageLoaded();
        driver.findElement(By.id("reset-form")).isDisplayed();
        driver.getTitle();

    }
}
