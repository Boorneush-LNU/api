package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.BuyNow.*;
import com.cucumbercraft.POMPages.HomePage;
import com.cucumbercraft.POMPages.Modals;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.formula.functions.T;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;

public class BuyNowSteps extends MasterStepDefs{

    private final WebDriver driver = DriverManager.getWebDriver();
    private final HomePage homePage = new HomePage(driver);
    private final AdditionalInformation additionalInformation = new AdditionalInformation(driver);
    private final SignInPg signInPg=new SignInPg(driver);
    Excelutils excelRow = new Excelutils();
    PurchaseModel data;
    private AnpostPayment payment;
    private ThankYou thankYou;
    private YourOrder yourOrder;
    private JourneyHandler journeyHandler;
    private YourDetail yourDetail;
    private Review reviewPage;
    WebDriverUtil webUtil;
    private Modals Modal;
    private final By fldFirstName = By.id("txtGuest_FirstName");
    private final By fldPrimarySurname = By.id("txtGuest_Surname");
    private final By ddlGuestTitle = By.id("ddlGuest_Title");
    private final By SSCNError = By.xpath("//span[@id='CVtxtUser_PPSNumber']");
    private final By SSCNErrorPrimary = By.xpath("//span[@id='CVtxtUser_PPSNumber']");
    private final By SSCNErrorJoint = By.xpath("//span[@id='CVtxtUser_PPSNumber2']");
    private final By PPSN = By.xpath("//div/h1");
    private final By DOB = By.xpath("//div[./a[@href=\"/help-support/forms-downloads/application-forms\"]]");
    private final By DOB_PB = By.xpath("//div/h1");
    public final By productText = By.xpath(".//h4");
    private final By InvalidEmail = By.xpath("//span[@id='REVtxtUser_Email']");
    private final By InvalidEmailSec = By.xpath("//span[@id='REVtxtUser_Email2']");
    private final By InvalidEmailPbGift = By.xpath("//span[@id='REVtxtGuest_Email']");
    private final By PrepopulatedAmount = By.xpath("//span[@class='error-validation']");

    private final By RemoveAmount = By.xpath(".//td/input[@type='number']");
    private final By SameApplicantsFirst = By.xpath("//span[@id='SamePPSNUser']");
    private final By SameApplicantsSec = By.xpath("//span[@id='SamePPSNUser2']");
    private final By InvalidDOBFirst = By.xpath("//span[@id='dob-custom-error']");
    private final By PBAmount = By.xpath("//span[@class='error-validation']");
    private final By InvalidDOBSec = By.xpath("//span[@id='dob-custom-error2']");
    private final By InvalidDOBPBGiftTwo = By.xpath("//span[@id='dob-custom-error_2']");
    public final By issueText = By.xpath(".//p[1]");
    private final By yourOrderAmtList = By.xpath("//tr[@class='js-ec-your-order-row']");
    private final By chkTermsPB1 = By.xpath("//div[@class='checkbox']");
    private final By chkAgreeTerms = By.id("chkUser_Terms");
    private final By ddlSourceOfFunds = By.xpath("//select[@id='source-of-funds']");
    private final By cardNumber = By.xpath("//input[@id='_card_number']");
    private final By cardxpiry = By.xpath("//input[@id='_expiry']");
    private final By cardCvv = By.xpath("//input[@id='_cvv']");
    private final By tyheader = By.xpath("//h1[contains(text(),'Thank you')]");
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Given("^Click buy now for \"([^\"]*)\"$")
    public void clickBuyNowFor(String testcase) {
        try {
            String purchaseWk=FrameworkConstants.getExcelLocationAutomationRegression();
            String purchaseSheet=config.getBuyNowSheetName();

            log.info("Purchase Test Data Workbook Name is: "+purchaseWk);
            log.info("Purchase Test Data Sheet Name is: "+purchaseSheet);

            data = excelRow.getData(purchaseWk,purchaseSheet,testcase,PurchaseModel.class,"getTestcase");

            System.out.println(data.getJourney());
            yourOrder = homePage.clickBuyNowOnHomePage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Given("^Click buy now$")
    public void clickBuyNowForIrish() {
        try {
           homePage.clickBuyNowOnHomePageSS();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Given("^User click on Continue Button$")
    public void clickContinueIrish() {
        try {
            homePage.clickContinueSignIn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }





    @When("User select product for purchase")
    public void chooseProductForPurchase() {
        try {

            journeyHandler = yourOrder
                    .verifyProducts()
                    .addProductToCart(data.getProduct().get(0));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @When("User select product for purchase in Irish")
    public void chooseProductForPurchaseIrish() {
        try {

            journeyHandler = yourOrder
                    .verifyProductsIrish()
                    .addProductToCart(data.getProduct().get(0));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @When("User select product for purchase in Irish SignIn")
    public void chooseProductForPurchaseIrishSignIn() {
        try {

             yourOrder
                    .verifyProductsIrishSignIn();
//                    .addProductToCart(data.getProduct().get(0));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Then("Validate your order for buy now")
    public void VerifyOrderPage() {
        try {
            yourOrder
                    .assignDefaultValues(data.getProduct(), data.getAmount(),data)
                    .addMultipleProduct(data)
                    .enterAmount();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("User click on cancel button on your details page")
    public void clickOnCancelBtn() {
        try {
            yourDetail.clickCancelBtnYourDetails();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }




    @Then("User can continue as guest or sign-in during the journey")
    public void selectJourney() {
        try {
            boolean isGuest = data.getJourney().contains("Guest");
            yourDetail = isGuest
                    ? journeyHandler.guestJourney()
                    : journeyHandler.signinJourney(data.getFirstUserEmail(), data.getPassword(), data.getMobileNum());

            if (data.getProduct().indexOf("Prize Bonds as a Gift") != 0) {
                journeyHandler
                        .getJourney(data.getJourney(), data)
                        .run();
            } else if(data.getProduct().indexOf("Prize Bonds as a Gift") == 0 && !isGuest){
                yourDetail
                        .validatePBApplicantDetails(data.getFirstUserEmail())
                        .agreeTermsPB()
                        .submitDetailsForPB();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("User can continue as guest or sign-in during the journey in Irish")
    public void selectJourneyIrish() {
        try {
            boolean isGuest = data.getJourney().contains("Guest");
            yourDetail = isGuest
                    ? journeyHandler.guestJourneyIrish()
                    : journeyHandler.signinJourneyIrish(data.getFirstUserEmail(), data.getPassword(), data.getMobileNum());

            if (data.getProduct().indexOf("Duaisbhannaí mar Bhronntanas") != 0) {
                journeyHandler
                        .getJourneyIrish(data.getJourney(), data)
                        .run();
            } else if(data.getProduct().indexOf("Duaisbhannaí mar Bhronntanas") == 0 && !isGuest){
                yourDetail
                        .validatePBApplicantDetails(data.getFirstUserEmail())
                        .agreeTermsPB()
                        .submitDetailsForPB();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


//Shan 11.8.25
    @Then("Validate error message on Buynow details page when {string}")
    public void validateErrorMessageOnBuyNowDetailsPageError(String scenario) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: " + sheetName);
            TestData data = excelutils.getData(path, sheetName, scenario, TestData.class, "getTestcaseName");
            Assertions.assertThat(data).isNotNull();
            String expectedErrorMessage = data.getErrormessages().get(0);
            String actualErrorMessage;
            WebDriverWait wait;

            switch (scenario) {
                case "EnterRandomSSCN":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SSCNError));
                    actualErrorMessage = errorElement.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Random SSCN error message");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Random SSCN error message");
                    break;
                case "InvalidSSCNJoint":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(SSCNErrorJoint));
                    actualErrorMessage = errorElement3.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("SCD N Joint SSCN Number");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "SCD N Joint SSCN Number");
                    break;
                case "InvalidSSCNPrimaryJoint":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement5 = wait.until(ExpectedConditions.visibilityOfElementLocated(SSCNError));
                    Thread.sleep(5000);
                    WebElement errorElement6 = wait.until(ExpectedConditions.visibilityOfElementLocated(SSCNErrorJoint));
                    actualErrorMessage = errorElement5.getText();
                    String actualErrorMessage1 = errorElement6.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Joint Invalid SSCN");
                    Assertions.assertThat(actualErrorMessage1).isEqualTo(expectedErrorMessage).as("Joint Invalid SSCN");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Joint Invalid SSCN");
                    break;
                case "PPSN":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(PPSN));
//                   System.out.println(errorElement1.getText());
                    actualErrorMessage = errorElement1.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Random SSCN error message");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Details not matched screen appears");
                    break;
                case "PPSN_SSCN":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(PPSN));
//                   System.out.println(errorElement1.getText());
                    actualErrorMessage = errorElement2.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("SSCN/PPSN error message");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Details not matched screen appears");
                    break;
                case "JointApplicantSCD-N":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                    WebElement errorElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(PPSN));
//                   System.out.println(errorElement1.getText());
                    actualErrorMessage = errorElement4.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("SSCN/PPSN error message");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Details not matched screen appears");
                    break;
                case "MinorDOB":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                    Thread.sleep(8000);
                    WebElement errorElement8 = wait.until(ExpectedConditions.visibilityOfElementLocated(DOB));
//                   System.out.println(errorElement1.getText());
                    actualErrorMessage = errorElement8.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Minor DOB error message");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Minor DOB Error message appears");
                    break;
                case "InvalidEmail":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement9 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidEmail));
                    actualErrorMessage = errorElement9.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Invalid Email in FT");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Invalid Email in FT");
                    break;
                case "InvalidSSCN":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement10 = wait.until(ExpectedConditions.visibilityOfElementLocated(SSCNError));
                    actualErrorMessage = errorElement10.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Invalid SSCN in FT");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Invalid SSCN in FT");
                    break;
                case "InvalidEmailPbGift":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement11 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidEmailPbGift));
                    actualErrorMessage = errorElement11.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Invalid Email in Pb as Gift");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Invalid Email in Pb as Gift");
                    break;
                case "SameApplicants":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement12 = wait.until(ExpectedConditions.visibilityOfElementLocated(SameApplicantsFirst));
                    actualErrorMessage = errorElement12.getText();
                    WebElement errorElement13 = wait.until(ExpectedConditions.visibilityOfElementLocated(SameApplicantsSec));
                    actualErrorMessage1 = errorElement13.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("FT Joint Same Applicant Details");
                    Assertions.assertThat(actualErrorMessage1).isEqualTo(expectedErrorMessage).as("FT Joint Same Applicant Details");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "FT Joint Same Applicant Details");
                    break;
                case "InvalidDOB":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement14 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidDOBFirst));
                    actualErrorMessage = errorElement14.getText();
                    WebElement errorElement15 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidDOBSec));
                    actualErrorMessage1 = errorElement15.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("FT Joint Invalid DOB Details");
                    Assertions.assertThat(actualErrorMessage1).isEqualTo(expectedErrorMessage).as("FT Joint Invalid DOB Details");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "FT Joint Invalid DOB Details");
                    break;
                case "InvalidEmailPrimarySec":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement16 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidEmail));
                    actualErrorMessage = errorElement16.getText();
                    WebElement errorElement17 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidEmailSec));
                    actualErrorMessage1 = errorElement17.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Invalid Joint Email in FT");
                    Assertions.assertThat(actualErrorMessage1).isEqualTo(expectedErrorMessage).as("Invalid Joint Email in FT");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Invalid Joint Email in FT");
                    break;
                case "PrepopulatedAmount":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement18 = wait.until(ExpectedConditions.visibilityOfElementLocated(PrepopulatedAmount));
                    System.out.println(errorElement18.getText());
                    actualErrorMessage = errorElement18.getText();
                    String normalizedActual = actualErrorMessage.replace(",", "");
                    Assertions.assertThat(normalizedActual).isEqualTo(expectedErrorMessage).as("Minimum-Maximum amount Error message");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Minimum-Maximum amount Error message");
                    break;
                case "SameApplicantsJoint":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement19 = wait.until(ExpectedConditions.visibilityOfElementLocated(SameApplicantsSec));
                    Thread.sleep(4000);
                    actualErrorMessage = errorElement19.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("FT Joint Same Applicant Details");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "FT Joint Same Applicant Details");
                    break;
                case "SecInvalidDOB":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement20 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidDOBSec));
                    actualErrorMessage = errorElement20.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("FT Joint Second Applicant Invalid DOB Details");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "FT Joint Second Applicant Invalid DOB Details");
                    break;
                case "SecInvalidEmail":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement21 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidEmailSec));
                    actualErrorMessage = errorElement21.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("FT Joint Second Applicant Invalid DOB Details");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "FT Joint Second Applicant Invalid Email Details");
                    break;
                case "PrepopulatedAmountJoint":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement22 = wait.until(ExpectedConditions.visibilityOfElementLocated(PrepopulatedAmount));
                    System.out.println(errorElement22.getText());
                    actualErrorMessage = errorElement22.getText();
                    String normalizedActual1 = actualErrorMessage.replace(",", "");
                    Assertions.assertThat(normalizedActual1).isEqualTo(expectedErrorMessage).as("Joint Minimum-Maximum amount Error message");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Joint Minimum-Maximum amount Error message");
                    break;
                case "PBInvalidDOB":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement23 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidDOBFirst));
                    actualErrorMessage = errorElement23.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("FT Guest/SignIn Pb as Gift Invalid DOB Details");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "FT Guest/SignIn Pb as Gift Invalid DOB Details");
                    break;

                case "InvalidDOBPBTwoHolders":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement24 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidDOBFirst));
                    actualErrorMessage = errorElement24.getText();
//                    WebElement errorElement25 = wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidDOBPBGiftTwo));
//                    actualErrorMessage1 = errorElement25.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Invalid 2 Holders PB as Gift");
//                    Assertions.assertThat(actualErrorMessage1).isEqualTo(expectedErrorMessage).as("Invalid 2 Holders PB as Gift");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Invalid 2 Holders PB as Gift");
                    break;
                case "PBMiniMax":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement25 = wait.until(ExpectedConditions.visibilityOfElementLocated(PBAmount));
                    actualErrorMessage = errorElement25.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Mini/maxi PB amount");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Mini/maxi PB amount");
                    break;
                case "PBMiniMaxJoint":
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement errorElement26 = wait.until(ExpectedConditions.visibilityOfElementLocated(PBAmount));
                    actualErrorMessage = errorElement26.getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Mini/maxi PB Joint amount");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Mini/maxi PB Joint amount");
                    break;
            }

        } catch (Exception e) {
            Assert.fail("An unexpected error occurred during error message validation for the scenario.", e);

        }

    }

    @Then("User can sign-in & perform the journey")
    public void selectJourneyfromDB() {
        try {
            Thread.sleep(3000);
//            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@id='CVchkUser_Terms']/ancestor::div[@class='checkbox']//input[@id='chkUser_Terms']")));
            ExtentCucumberAdapter.addTestStepLog("User clicks on the Terms & condition Checkbox");
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[normalize-space()='Continue to your order']")));
            ExtentCucumberAdapter.addTestStepLog("User clicks on Continue to your order");
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("validate Your Order Page Prize Bond")
    public void selectOrderfromDB() {
        try{
            Thread.sleep(2000);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[normalize-space()='Continue to review']")));
            ExtentCucumberAdapter.addTestStepLog("User clicks on Continue to Review");
            Thread.sleep(2000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("Enter payment details click pay PB")
    public void PayPageDB() {
        try {
            Thread.sleep(8000);
            js.executeScript("document.getElementById('_card_number').value='4539791001730106';");
            js.executeScript("document.getElementById('_expiry').value='12/26';");
            js.executeScript("document.getElementById('_cvv').value='000';");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            log.info("Scroll down to the botton of the screen");
            Thread.sleep(3000);
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@id='checbox-checkmak']")));
            js.executeScript("arguments[0].click();", driver.findElement(By.id("btnPayNow")));
            ExtentCucumberAdapter.addTestStepLog("User entered the payment details & clicked on Pay");
            Thread.sleep(15000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("validate Your Review Page Prize bond")
    public void SOFfromDB() throws InterruptedException {
        try{
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        js.executeScript("document.getElementById('source-of-funds').value='Salary';");
        js.executeScript("arguments[0].click();", driver.findElement(By.id("order-review-submit")));
            ExtentCucumberAdapter.addTestStepLog("User select the Source of found & click on submit");
            } catch (Exception e) {
                throw new ExceptionUtils("Source of fund not selected");
            }
    }


@Then("Verify Thank You page for Db")
    public void validateTyPage12() {
        thankYou.validateTyPage1();
//    if (webUtil.isElementVisible(tyheader, 25)) {
//        webUtil.gettextlog(tyheader, String::equals, "Thank you for your purchase");
//
//    }
}

//    @Then("^Validate product and amount on review page$")
//    public void validateAndOnReviewPagePB() {
//        try {
//            reviewPage.verifyUserDetails(data.getProduct(), data.getFirstUserEmail(), data.getSecondUserEmail(), data)
//                    .initializeProductFlags(data.getProduct())
//                    .validateProductAndAmount(data.getProduct(), data.getAmount())
//                    .selectAccountCDE(data.getSourceOfFunds(), data.getPurposeOfAccount())
//                    .proceedToPayment(data.getAmlFlag(), data.getJourney());
//
//
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//
//    }




    @Then("User select journey as guest")
    public void userEnterDetails() {
        try {

            boolean isGuest = data.getJourney().contains("Guest");

            if (isGuest) {
                yourDetail =  journeyHandler.guestJourney();

            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("User enter personal details on your details page for first applicant")
    public void userEnterPersonDetail() {
        try {

            String[] birthdate = data.getFirstUserDOB()
                    .replaceAll("-", "/")
                    .split("/");
            yourOrder = yourDetail.validateContentSoleYourDetails()
                    .withPrimaryApplicantSurname(data.getFirstUserSurname())
                    .withPrimaryApplicantBirthdate(birthdate)
                    .withPrimaryApplicantSSCN(data.getFirstUserSSCN())
                    .withPrimaryApplicantEmail(data.getFirstUserEmail())
                    .agreeTerms()
                    .submitDetails();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("Users enter personal details for both applicants as part of the guest journey")
    public void userEnterJointDetail() {
        try {
            String[] birthdatePrimary = data.getFirstUserDOB()
                    .replaceAll("-", "/")
                    .split("/");
            String[] birthdateSecondary = data.getFirstUserDOB()
                    .replaceAll("-", "/")
                    .split("/");
            yourOrder = yourDetail.validateContentSoleYourDetails()
                    .validateContentJointYourDetails()
                    .selectJoint()
                    .withPrimaryApplicantSurname(data.getFirstUserSurname())
                    .withPrimaryApplicantBirthdate(birthdatePrimary)
                    .withPrimaryApplicantSSCN(data.getFirstUserSSCN())
                    .withPrimaryApplicantEmail(data.getFirstUserEmail())
                    .withSecondaryApplicantSurname(data.getSecondUserSurname())
                    .withSecondaryApplicantBirthdate(birthdateSecondary)
                    .withSecondaryApplicantSSCN(data.getSecondUserSSCN())
                    .withSecondaryApplicantEmail(data.getSecondUserEmail())
                    .agreeTerms()
                    .submitDetails();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User enters person details Prize Bond Gift$")
    public void userEntersPersonDetails() {
        try {

            yourDetail.enterPBApplicantDetails(data.getFirstName(), data.getFirstUserSurname(), data.getFirstUserEmail(), data.getAddress());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^User enters person details Prize Bond Gift in Irish$")
    public void userEntersPersonDetailsIrish() {
        try {

            yourDetail.enterPBApplicantDetailsIrish(data.getFirstName(), data.getFirstUserSurname(), data.getFirstUserEmail(), data.getAddress());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^User enters Prize Bond holder details$")
    public void userEntersJointDetail() {
        try {
            yourDetail.enterPBHolderDetails(data.getPbHolderFlag(), data).run();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Then("^User enters Prize Bond holder details in Irish$")
    public void userEntersJointDetailIrish() {
        try {
            yourDetail.enterPBHolderDetailsIrish(data.getPbHolderFlag(), data).run();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("^User enters Prize Bond holder details PG as Gift$")
    public void userEntersJointDetailPBGift() {
        try {
            yourDetail.enterPBHolderDetailsJoint(data.getPbHolderFlag(), data).run();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


//
//    //Shan. Delete it if not needed
//    @Then ("^User Enter the PB Sole Details$")
//    public void validateYourOrde1r(String holderType) {
//        try {
//            if (data.getPbHolderFlag().equalsIgnoreCase("No")) {
//                log.info("Pass");
//                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ddlGuestTitle));
//            }
////                driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
////            } else {
////                driver.findElement(cashInEnterAmount).sendKeys(data.getReinvestAmounts().get(0), Keys.TAB);
////            }
////            data.getPbHolderFlag(), data).run()
//                webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
//                webUtil.sendKeys(fldFirstName, "Alan");
//                webUtil.sendKeys(fldPrimarySurname, "John");
////div[@class='dob-input']
//                webUtil.sendKeys(DOB_PB, "00-12-2000");
//            } catch(Exception e){
//                Assert.fail(e.getMessage());
//            }
//        }

    @Then("^Validate Your Order page$")
    public void validateYourOrder() {
        try {

            System.out.println(data.getProduct());
            Thread.sleep(2000);
            reviewPage = yourOrder
                    .assignDefaultValues(data.getProduct(), data.getAmount(), data)
                    .addMultipleProduct(data)
                    .enterAmount()
                    .clickContReview();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^Validate Your Order page in Irish$")
    public void validateYourOrderIrish() {
        try {

            System.out.println(data.getProduct());
            Thread.sleep(2000);
            reviewPage = yourOrder
                    .assignDefaultValuesIrish(data.getProduct(), data.getAmount(), data)
                    .addMultipleProductIrish(data)
                    .enterAmountIrish()
                    .clickContReview();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    //Shan. Added for PB Min max
    @Then("^Validate Your Order page PB$")
    public void validateYourOrderPB() {
        try {

            System.out.println(data.getProduct());
            reviewPage = yourOrder
                    .assignDefaultValues(data.getProduct(), data.getAmount(), data)
                    .addMultipleProduct(data)
                    .enterAmount1()
                    .clickContReview();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }




    //Shan 15.7.25.
    @Then("^Validate Your Order page SignIn Joint$")
    public void validateYourOrderJoint() {
        try {

            System.out.println(data.getProduct());
            reviewPage = yourOrder
                    .assignDefaultValues(data.getProduct(), data.getAmount(), data)
                    .addMultipleProduct1(data)
//                    .enterAmount()
                    .clickContOrder();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @Then("^Validate Your Order page & Remove Product$")
    public void validateYourOrderRemove() {
        try {
            System.out.println(data.getProduct());
            reviewPage = yourOrder.assignDefaultValues(data.getProduct(), data.getAmount(),data)
                    .addMultipleProduct(data)
                    .enterAmount()
                   .clickContReviewCheck();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }




    @Then("^Validate product and amount on review page$")
    public void validateAndOnReviewPage() {
        try {
            reviewPage.verifyUserDetails(data.getProduct(), data.getFirstUserEmail(), data.getSecondUserEmail(), data)
                    .initializeProductFlags(data.getProduct())
                    .validateProductAndAmount(data.getProduct(), data.getAmount())
                    .selectAccountCDE(data.getSourceOfFunds(), data.getPurposeOfAccount())
                    .proceedToPayment(data.getAmlFlag(), data.getJourney());


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("^Validate product and amount on review page in Irish$")
    public void validateAndOnReviewPageIrish() {
        try {
            reviewPage.verifyUserDetailsIrish(data.getProduct(), data.getFirstUserEmail(), data.getSecondUserEmail(), data)
                    .initializeProductFlagsIrish(data.getProduct())
                    .validateProductAndAmountIrish(data.getProduct(), data.getAmount())
                    .selectAccountCDE(data.getSourceOfFunds(), data.getPurposeOfAccount())
                    .proceedToPaymentIrish(data.getAmlFlag(), data.getJourney());


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }




    @Then("User selects customer level critical data elements")
    public void userSelectsCustomerLevelCDE() {
        try {
            System.out.println(data.getCDE());
            if (data.getAmlFlag().contains("N")) {
                payment = additionalInformation
                        .manageCde(data)
                        .submitCDEdetails();
            }
            payment = new AnpostPayment(driver);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Then("User selects customer level critical data elements in Irish")
    public void userSelectsCustomerLevelCDEIrish() {
        try {
            System.out.println(data.getCDE());
            if (data.getAmlFlag().contains("N")) {
                payment = additionalInformation
                        .manageCdeIrish(data)
                        .submitCDEdetailsIrish();
            }
            payment = new AnpostPayment(driver);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }






    @Then("Enter payment details click pay")
    public void enterDetailsOnPaymentPage() {
        try {
            String[] paymentArr = data.getPaymentDetails().split(",");
            thankYou = payment.waitForPaymentPage()
                    .enterCardDetails(paymentArr[1], paymentArr[2], paymentArr[3])
//                    .enterCardDetailsInfo(paymentArr[0],paymentArr[1], paymentArr[2], paymentArr[3])
                    .fillBillingCity(paymentArr[7])
                    .clickCheckbox()
                    .clickPayButton()
                    .retryPaymentIfFailed(paymentArr[1], paymentArr[2], paymentArr[3]);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

//Shan
    public void InvalidDOB() {
        try {
            String[] DOB = data.getFirstUserDOB().split(",");
            String[] paymentArr = data.getPaymentDetails().split(",");
            thankYou = payment.waitForPaymentPage()
                    .enterCardDetails(DOB[1], paymentArr[2], paymentArr[3])
//                    .enterCardDetailsInfo(paymentArr[0],paymentArr[1], paymentArr[2], paymentArr[3])
                    .fillBillingCity(paymentArr[7])
                    .clickCheckbox()
                    .clickPayButton()
                    .retryPaymentIfFailed(paymentArr[1], paymentArr[2], paymentArr[3]);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }




    @Then("Verify Thank You page")
    public void verifyThankYouPage() {
        try {
            thankYou.validateTyPage(data);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Shan
//    @Then("Error message")
//    public void verifyErrorMessage() {
//        try {
//            reviewPage.validateErrorMessageOnBuyNowDetailsPageError(String scenario);
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }

    @Given("Navigate to telesales {string}")
    public void navigateToTelesales(String testcase) {


            try {

                data=excelRow.getData(FrameworkConstants.getExcelLocationAutomationRegression(), config.getBuyNowSheetName(), testcase,PurchaseModel.class,"getTestcase");
                System.out.println("Data: "+data);
                yourOrder=signInPg.loginTelesales();
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }


    }


    @Then("Navigate to YourOrderPage {string}")
    public void navigateToPBOrder(String testcase) {
        try {

            data=excelRow.getData(FrameworkConstants.getExcelLocationAutomationRegression(), config.getBuyNowSheetName(), testcase,PurchaseModel.class,"getTestcase");
            System.out.println("Data: "+data);
//            yourOrder=signInPg.loginTelesales();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


    }





    @Then("Validate your order page amount:")
    public void validateYourOrderPageAmount(DataTable table) {
        reviewPage=yourOrder.errorYourOrder(table.asList(),data);
    }





    @Then("Validate error on review page")
    public void validateErrorOnReviewPage() {
        reviewPage.errorReview(data.getProduct());
    }

    @Then("Validate error on additional information page")
    public void validateErrorOnAdditionalInformationPage() {
        String indicator =data.getAmlFlag();
        if(indicator.contains("N"))
            additionalInformation.errorCDE(indicator,data.getProduct(), data.getJourney());
    }
}
