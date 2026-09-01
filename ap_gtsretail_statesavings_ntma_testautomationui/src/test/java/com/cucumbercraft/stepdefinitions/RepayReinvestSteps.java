package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.ExcelReader;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.RepayReinvest.*;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.POMPages.StateSavingsDashboardPage;
import com.cucumbercraft.framework.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;


public class RepayReinvestSteps extends MasterStepDefs {
    private final WebDriver driver = DriverManager.getWebDriver();
    private final SignInPg signInPg = new SignInPg(driver);
    private final Holdings holdings = new Holdings(driver);
    private final StateSavingsDashboardPage dashboard = new StateSavingsDashboardPage(driver);
    private final Cashin_Reinvest cashin_reinvest = new Cashin_Reinvest(driver);
    private final ReviewTransactionPage reviewTransactionPage = new ReviewTransactionPage(driver);
    private final Joint joint = new Joint(driver);
    private final ChooseOptionPage chooseOptionPage = new ChooseOptionPage(driver);
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    private final By HoldingCC = By.xpath("//a[@class='product-summary-links__item-link']");
    public By cashInEnterAmount = By.xpath("//input[@id='txtCashAmount']");
    private final By HoldingInstalment = By.xpath("(//div[@class='product-summary-info--notification row'])[2]");
    public By Tick = By.xpath("//span[@class='error-validation']");
    public By EnterAmountSelectFull = By.xpath("(//span[@class='error-validation'])[1]");
    public By InvalidAmount = By.xpath("(//span[@class='error-validation'])[2]");


    Thank_You_Page thankYouPage = new Thank_You_Page(driver);
    private final ExcelReader testData = new ExcelReader();


//    @When("^User select \"([^\"]*)\" and clicks on manage button$")
//    public void userClickOnManageButton(String product) {
//        try {
//            dashboard.clickProduct(data.getProduct());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage(), e);
//        }
//    }

    @When("^User select StateSavingProduct and clicks on manage button$")
    public void userClickOnManageButton() {
        try {
            dashboard.clickProduct(data.getProduct());
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("User click {string} link on the summary page")
    public void userOpensMaturedHoldingAndSelectsMethodType(String option) {
        try {
            holdings.clickInvestmentOpt(option, data.getHoldingID());
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("^User choose product from dropdown and enter the amount$")
    public void userSelectsProductFromDropdownAndEnterTheAmount() {
        try {
            cashin_reinvest.selectingProduct(data.getReinvestProducts(), data.getReinvestAmounts(), data.getPosbAccountNumber());
             Thread.sleep(7000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User enter rest amount for CashIn$")
    public void userEnterRestForCashin() {
        try {
           log.info("Pass");
            cashin_reinvest.setCashIn_Enter_Amnt(data.getReinvestAmounts());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User enter rest amount for CashIn in Cashin Textbox$")
    public void userEnterRemainingForCashin() {
        webUtil.scrollToView(cashInEnterAmount);
        webUtil.click(cashInEnterAmount);
        if (data.getReinvestAmounts().get(5).equalsIgnoreCase("Blank")) {
            driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
//            Thread.sleep(3000);
        } else {
            driver.findElement(cashInEnterAmount).sendKeys(data.getReinvestAmounts().get(5), Keys.TAB);
        }
    }

    @When("^Validate IBAN status is displayed$")
    public void validateAddYourBankDetailsButtonIsDisplayed() {
        try {
            log.info("IBAN Status:" + cashin_reinvest.checkIBAN());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("^User Enters IBAN number and click on confirm bank details$")
    public void userEntersIBANNumberAndClickOnConfirmBankDetails() {
        try {
            cashin_reinvest.addIban(data.getIBAN());
            cashin_reinvest.click_Checkbox_confirm();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^User then enters otp and press confirm$")
    public void userGivenThenEntersOtpAndPressConfirm() {
        try {
            cashin_reinvest.setOTPIBAN(data.getMobileNumber()).enterOTPIBAN().clickConfirmOTP_PageIBAN();
            ExtentCucumberAdapter.addTestStepLog("OTP is generated");
        } catch (Exception e) {
            Assert.fail();
        }

    }

    @Then("^Validate OTP page and user enters valid otp$")
    public void validateOTPPageWhereUserEntersOtpWith() {
        try {
            cashin_reinvest
                    .setOTP(data.getMobileNumber())
                    .enterOTP()
                    .clickConfirmOTP_Page();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @When("^User clicks on manage button and select monthly instalment savings$")
    public void userClicksOnManageButtonAndSelectMonthlyInstalmentSavings() {
        try {
            dashboard.clickProduct(data.getProduct());
            cashin_reinvest.clickmonthlysavings(data.getPosbAccountNumber().replaceAll("[^0-9]", ""));
            ExtentCucumberAdapter.addTestStepLog("Instalment view and manage button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("^user opens matured holding \"([^\"]*)\" and select method type \"([^\"]*)\"$")
    public void userOpensMaturedHoldingAndSelectMethodType(String id, String option) {
        try {
            holdings.userSelectsMatureProductAndClickOnButton(option, id);
            ExtentCucumberAdapter.addTestStepLog("Holding found and Reinvest/cashin button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @Then("^user enter \"([^\"]*)\" for Cashin$")
    public void userEnterForCashin(String amount) {
        try {
            cashin_reinvest.setCashIn_Enter_Amnt(List.of(amount));
            ExtentCucumberAdapter.addTestStepLog("Cashin amount entered");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^User clicks on confirm button$")
    public void userClicksOnConfirmButton() {
        try {
            cashin_reinvest.getConfirmButtonClick();
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^Amount entered is less than maturity value user click on allocate to cash$")
    public void amountEnteredIsLessThanMaturityValueUserClickOnAllocateToCash() {
        try {
            cashin_reinvest.allocatetocash();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Substantiate particulars on review transaction page$")
    public void validatePreviousPageDetailsWithReviewTransactionPageAndCheckFeildTootipAsPerFsd() {
        try {
            reviewTransactionPage.reviewPagedetails(data.getHoldingID(), data.getReinvestProducts(), data.getReinvestAmounts(), data.getAllocateAllAmountIndex());
//            reviewTransactionPage.reviewPagedetails( getdata.get("Holding ID"), getdata.get("Products"), getdata.get("Amount"), "3");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }


    @And("^Substantiate particulars on review transaction page Cash In$")
    public void validatePreviousPageDetailsWithReviewTransactionPageAndCheckFeildTootipAsPerFsdCashIn() {
        try {
            log.info("Passed Method");
            reviewTransactionPage.reviewPagedetailsCashIn(data.getHoldingID(), data.getReinvestAmounts(), data.getAllocateAllAmountIndex());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on confirm button without clicking on T&C$")
    public void TermsAndCondition() {
        try {
            log.info("Passed Method");
            reviewTransactionPage.contentvalidation();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }


    @And("^User opens matured holding ID for Instalment Save and select method type$")
    public void userOpensMaturedHoldingIDAndSelectMethodType() {
        try {
            holdings.userSelectsMatureProductAndClickOnButton(data.getOptionType(), data.getHoldingID());
            ExtentCucumberAdapter.addTestStepLog("Holding found and Reinvest/cashin button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^Amount entered is less than maturity value user clicks on allocate to cash$")
    public void amountEnteredIsLessThanMaturityValueUserClicksOnAllocateToCash() {
        try {
            cashin_reinvest.allocatetocashPrizeBonds();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User able to see allocate available funds modal$")
    public void userAbleToSeeAllocateAvailableFundsModal() {
        try {
            cashin_reinvest.availablefundsmodal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^User click cancel button on review page$")
    public void userClickCancelButtonOnChooseProductPage() {
        try {
            cashin_reinvest.getCancelbutton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("verify user lands on dashboard page")
    public void verifyUserLandsOnDashboardPage() {
        try {
            cashin_reinvest.getCandashboardwelcometextcelbutton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^User redirected to thank you page$")
    public void userRedirectedToThankYouPage() {
        try {
            if (data.getProduct().contentEquals("Prize Bond") && data.getOptionType().contentEquals("Cash-In")) {
                thankYouPage.verifyCashinPBThankYouPage();
            } else if (data.getProduct().contentEquals("Prize Bond") && data.getOptionType().contentEquals("Reinvest")) {
                thankYouPage.verifyReinvestPBThankYouPage();
            } else {
                thankYouPage.verifyThankYouPage();
            }
            thankYouPage.clickBackBtn();
        } catch (Exception E) {
            Assert.fail(E.getMessage(), E);
        }
    }


    @And("^User click on Pending Transaction modal$")
    public void userClickOnPendingTransactionModal() {
        try {

            holdings.pendingTransaction(data.getHoldingID());
            cashin_reinvest.pendinTrnscModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User opens matured holding page selects method type$")
    public void userOpensMaturedHoldingPageSelectsMethodType() {
        try {
//            holdings.repayReinvest_Click(getdata.get("Holding ID")).reinvest_cashincontent().selectOption(getdata.get("MethodType"));
            joint.soleholding(data.getHoldingID());
        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage(), e);
        }
    }


    @And("^User validates error message with multiple combination \"([^\"]*)\"$")
    public void userValidatesErrorMessageWithMultipleCombination(int Name, DataTable table) {
        try {
            List<Map<String, String>> data = table.asMaps(String.class, String.class);

            cashin_reinvest.selectSingle(data.get(Name).get("Product"), data.get(Name).get("Amount"), "null");
//            cashin_reinvest.selectingProduct(getdata.get("Products"), getdata.get("Amount"), getdata.get("POSB Account Number"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User clicks on joint button$")
    public void userClicksOnJointButton() {
        try {
            joint.clickjoint();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

//    @And("User selects joint holding with mentioned {string}")
//    public void userSelectsJointHoldingWithMentionedName(String name) {
//        try {
//            joint.selectJointWithName(data.getName());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }

    @And("User selects joint holding with mentioned Name")
    public void userSelectsJointHoldingWithMentionedName() {
        try {
            joint.selectJointWithName(data.getName());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


//    @Then("^User verify notice button with mentioned \"([^\"]*)\"$")
//    public void userVerifyNoticeButtonWithMentionedHoldingId(String holding) {
//        try {
//            joint.selectHoldingWithID(data.getHoldingID());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }

    @Then("^User verify notice button with mentioned HoldingId$")
    public void userVerifyNoticeButtonWithMentionedHoldingId() {
        try {
            joint.selectHoldingWithID(data.getHoldingID());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @When("^User click on more details and verifies button present is same as on holdings page")
    public void userClickOnMoreDetailsAndVerifiesButtonPresentIsSameAsOnHoldingsPage() {
        try {
            joint.jointMoredetails();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

//    @When("^User click on more details and verifies button present is same as on holdings page & verify")
//    public void userClickOnMoreDetailsAndVerifiesButtonPresentIsSameAsOnHoldingsPageVerify() {
//        try {
//            joint.jointMoredetailsJointNotice();
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }



//    @When("^User click on more details and verifies button present is same as on holdings page Joint$")
//    public void userClickOnMoreDetailsAndVerifiesButtonPresentIsSameAsOnHoldingsPageJoint() {
//        try {
//            joint.jointMoredetailsJoint();
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }

//    @When("^User click on more details and verifies button present is same as on holdings page in CC$")
//        public void userClickOnMoreDetailsAndVerifiesButtonPresentIsSameAsOnHoldingsPageCC() {
//      try {
//            joint.jointMoredetailsCC();
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }

    @When("^User click on more details and verifies button present is same as on holdings page in IS$")
    public void userClickOnMoreDetailsAndVerifiesButtonPresentIsSameAsOnHoldingsPageIS() {
        try {
            joint.jointMoredetailsIS();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User clicks on button and verifies the modal content$")
    public void userClicksOnButtonAndVerifesYheModalContent() {
        try {
            joint.jointModals();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Validate error message on OTP page with multiple combination$")
    public void validateErrorMessageOnOTPPageWithMultipleCombination() {
        try {
            cashin_reinvest.errorOTP(data.getMobileNumber());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^User click on edit your order review page$")
    public void userClickOnEditYourOrderReviewPage() {
        try {
            reviewTransactionPage.clickEditOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @When("^User clicks on Summary page$")
    public void userClickOnSummaryPage() {
        try {
           webUtil.click(HoldingCC);
           log.info("Clicked on Summary Page");
           webUtil.waitForPageLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @When("^User clicks on Summary page$")
//    public void userClickOnSummaryPageIS() {
//        try {
//            webUtil.scrollToView(HoldingInstalment);
//            webUtil.click(HoldingInstalment);
//            log.info("Clicked on Summary Page");
//            Thread.sleep(4000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @When("^Navigate to the Holding Id$")
    public void userClickOnSummaryPageIS() {
        try {
//            webUtil.scrollToView(HoldingInstalment);
            webUtil.click(HoldingInstalment);
            log.info("Clicked on Summary Page");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Then("^User select more \"([^\"]*)\" and \"([^\"]*)\" to reinvest$")
    public void userSelectMoreAndToReinvest(String product, String amount) throws Exception {
        String[] products, amounts;
        if (product.contains(",")) {
            products = product.split(",");
            amounts = amount.split(",");
        } else {
            products = new String[1];
            amounts = new String[1];
            products[0] = product;
            amounts[0] = amount;
        }
        cashin_reinvest.slectMultipleEdit(products, amounts, data.getPosbAccountNumber());

    }


    @Then("Validate error message on details page when {string}")
    public void validateErrorMessageOnDetailsPage(String scenario) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: " + sheetName);
            TestData data = excelutils.getData(path, sheetName, scenario, TestData.class, "getTestcaseName");
            Assertions.assertThat(data).isNotNull();
            String expectedErrorMessage = data.getErrormessages().get(0);
            String actualErrorMessage;
            switch (scenario) {
                case "TickContent":
                    actualErrorMessage=webUtil.waitUntilElementVisible(Tick,10).getText(); // Xpath compares with Expected content, which is in Error scenario Excel
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("T&C is not clicked");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"T&C is not clicked");
                    break;
                case"Repayment-Blank":
                    actualErrorMessage=webUtil.waitUntilElementVisible(EnterAmountSelectFull,10).getText(); // Xpath compares with Expected content, which is in Error scenario Excel
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Directly click on Confirm without giving the Amount");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Directly click on Confirm without giving the Amount");
                    break;
                case"Repayment-Invalid":
                    actualErrorMessage=webUtil.waitUntilElementVisible(InvalidAmount,10).getText(); // Xpath compares with Expected content, which is in Error scenario Excel
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Entered Invalid Amount & click on Confirm");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Entered Invalid Amount & click on Confirm");
                    break;
                case "no product selected":
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.noProductError,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("No product error message is not displayed");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"No product error message is displayed");
                    break;
                case "PB-no amount entered":
                    cashin_reinvest.selectProduct("Prize Bonds");
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.noAmountErrorPB,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("No amount error message is displayed for PB");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"No amount error message is displayed for PB");
                    break;
                    case "FT-no amount entered":
                    cashin_reinvest.selectProduct("Savings Certificate - 5 Year");
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.noAmountErrorFT,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("No amount error message is not displayed for FT");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"No amount error message is displayed for FT");
                    break;
                case "FT-below minimum purchase":
                    cashin_reinvest.selectProduct("Savings Certificate - 5 Year");
                    cashin_reinvest.enterAmount("0");
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.belowMinReinvestErrorFT,15).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Minimum amount error message is displayed for FT");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Minimum amount error message is displayed for FT");
                    break;
                case "FT-below minimum purchase amount":
                    cashin_reinvest.selectProduct("Savings Bond - 3 Year");
                    cashin_reinvest.enterAmount("49");
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.belowMinReinvestErrorFT,15).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Minimum amount error message is displayed for FT");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Minimum amount error message is displayed for FT");
                    break;
                case "PB FT-below minimum purchase":
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.belowMinReinvestErrorFT,15).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Minimum amount error message is not displayed for FT");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Minimum amount error message is not displayed for FT");
                    break;

//                case "FT-NSB-below minimum purchase":
//                    cashin_reinvest.selectProduct("National Solidarity Bond - 10 Year");
//                    cashin_reinvest.enterAmount("20");
//                    Thread.sleep(3000);
//                    cashin_reinvest.getConfirmButtonClick();
//
//                    log.info("pass");
//                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.belowMinReinvestErrorFT,10).getText();
//                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Minimum amount error message is not displayed for FT");
//                    break;
                case "PB-below minimum purchase":
                    cashin_reinvest.selectProduct("Prize Bonds");
                    cashin_reinvest.enterAmount("0");
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.belowMinReinvestErrorPB,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Minimum amount error message is not displayed for PB");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Minimum amount error message is displayed for PB");
                    break;
                case "DA-below minimum purchase":
                    cashin_reinvest.selectProduct("Deposit Account");
                    cashin_reinvest.enterAmount("0");
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.belowMinReinvestErrorPOSB,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage).as("Minimum amount error message is not displayed for POSB");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Minimum amount error message is displayed for POSB");
                    break;
                case "DA-account number and amount error":
                    cashin_reinvest.selectProduct("Deposit Account");
                    cashin_reinvest.clickPOSBAccountCheckbox();
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.accountRequiredError,10).getText();
                    String actualErrorMessage1=webUtil.waitUntilElementVisible(cashin_reinvest.noAmountErrorFT,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(data.getErrormessages().get(0)).as("POSB account number required error message is not displayed for POSB");
                    Assertions.assertThat(actualErrorMessage1).isEqualTo(data.getErrormessages().get(1)).as("No amount error message is not displayed for POSB");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Account number and amount error message is displayed for POSB");
                    break;
                case "DA-No account number and Invalid amount error":
                    cashin_reinvest.selectProduct("Deposit Account");
                    cashin_reinvest.clickPOSBAccountCheckbox();
                    cashin_reinvest.enterAmount("0");
                    cashin_reinvest.getConfirmButtonClick();
                     actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.accountRequiredError,10).getText();
                    String actualErrorMessage2=webUtil.waitUntilElementVisible(cashin_reinvest.belowMinReinvestErrorPOSB,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(data.getErrormessages().get(0)).as("POSB no account number required error message is displayed for POSB");
                    Assertions.assertThat(actualErrorMessage2).isEqualTo(data.getErrormessages().get(1)).as("Invalid amount error message is displayed for POSB");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"No Account number and amount error message is displayed for POSB");
                    break;
                case "DA-account number error":
                    cashin_reinvest.selectProduct("Deposit Account");
                    cashin_reinvest.clickPOSBAccountCheckbox();
                    cashin_reinvest.enterPOSBAccountNumber("123");
                    cashin_reinvest.enterAmount("10");
                    cashin_reinvest.getConfirmButtonClick();
                    actualErrorMessage=webUtil.waitUntilElementVisible(cashin_reinvest.accountError,10).getText();
                    Assertions.assertThat(actualErrorMessage).isEqualTo(String.join(",", data.getErrormessages())).as("Account number format supplied is incorrect, error message is not displayed for POSB");
                    FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Account number error message is displayed for POSB");
                    break;

            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @SneakyThrows
    @Then("User click investment type on choose option page")
    public void userClickInvestmentTypeOnChooseOptionPage() {
        Thread.sleep(2000);
        chooseOptionPage.clickInvestmentType(data.getOptionType());


    }
}