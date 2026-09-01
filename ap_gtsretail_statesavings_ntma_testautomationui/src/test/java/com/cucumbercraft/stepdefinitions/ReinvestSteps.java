package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.*;
import com.cucumbercraft.POMPages.RepayReinvest.*;
import com.cucumbercraft.framework.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class ReinvestSteps extends MasterStepDefs {
    private WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);

    //POM Pages
    private final Holdings holdings = new Holdings(driver);
    private final SelectProduct select = new SelectProduct(driver);
    HoldingHelper hHelper= new HoldingHelper();
    private final StateSavingsDashboardPage dashboard = new StateSavingsDashboardPage(driver);
    private final Modals modal = new Modals(driver);
    private final Cashin_Reinvest cashin_reinvest = new Cashin_Reinvest(driver);
    private final Prize_Bond prize = new Prize_Bond(driver);
    private final Security_Page secure = new Security_Page(driver);
    private final ReviewTransactionPage review = new ReviewTransactionPage(driver);
    private final Thank_You_Page thank = new Thank_You_Page(driver);
    private final Cashin_Reinvest cashin = new Cashin_Reinvest(driver);
    private final SignInPg signInPg = new SignInPg(driver);
    private final By Allocate = By.id("btnAllocateFull");
    private final By TickCheckBox = By.xpath("//span[@class='checkbox']");
    private final String ExpectedHeader = "Prize Bond winnings payment option";
    private final String ExpectedSubHeader = "Our records show that you are not a holder of Prize Bonds.";
    private final String ExpectedPara = "In this section only Prize Bond holders can choose to have their Prize Bond winnings automatically reinvested into Prize Bonds or transferred to their nominated bank account for Ireland State Savings.";
    public By SelectFromDropDown = By.name("product-select");
    private final By AccNo = By.name("txtDepositAccount");
    public By cashInEnterAmount = By.xpath("//input[@id='txtCashAmount']");
    public By ExistingAccountBox = By.xpath("(//input[@name='amount-input'])[1]");
    public By GoBack = By.xpath("//button[@class='gtm-cta button button--primary js-closeModal']");

    public By Close = By.xpath("//button[@class='gtm-cta button button--primary  js-closeModal']");
    public By InsufficientModal = By.xpath("//button[@class='gtm-cta button button--primary  js-closeModal']");

    @And("^User logout from portal$")
    public void userLogoutFromPortal() {
        try {
            signInPg.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @When("user clicks View and Manage button to access summary")
    public void userSelectsTheProductAndClicksOnManageButton() {
        try {
            dashboard.clickProduct(data.getProduct());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Verify repay reinvest link is displayed$")
    public void verifyRepayReinvestLinkIsDisplayed() {
        try {
            holdings.checkRepReinvestLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    private void userClickonManagebtn(String pro) {
        try {
            dashboard.clickProduct(pro);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("user selects a product from the dropdown list and proceeds to enter the desired amount")
    public void userSelectsProductFromDropdownAndEntersTheAmount() throws Throwable {
        select.userClicksOnDropdownMenuAndSelect(data.getReinvestProducts(), data.getReinvestAmounts());

    }

    @And("user selects one product from the dropdown list and proceeds to enter the desired amount")
    public void userSelectsProductFromDropdownAndEntersTheAmountSingle() throws Throwable {
        select.userClicksOnDropdownMenuAndSelect(data.getReinvestProducts(), data.getReinvestAmounts());
    }


    @And("user selects other product from the dropdown list")
    public void userSelectsProductFromDropdownAndEntersTheAmountSingleProduct() throws Throwable {
        select.userClicksOnDropdownMenuAndSelectSingle(data.getReinvestProducts());
    }

    @And("user selects other product from the dropdown list & enter the Amount")
    public void userSelectsProductFromDropdownAndEntersExistingAccount() throws Throwable {
        select.userClicksOnDropdownMenuAndSelectSingleDeposit(data.getReinvestAmounts());
    }

//    @And("user selects other product from the dropdown list & enter the Amount")
//    public void userSelectsProductFromDropdownAndEntersExistingAccount() throws Throwable {
//        select.userClicksOnDropdownMenuAndSelectSingle(data.getReinvestAmounts());
//    }

//    @And("User again rechange the Product") // Shan
//    public void ReSelectProduct() throws InterruptedException {
//        Thread.sleep(4000);
//        log.info("Rechoose");
//        if (webUtil.isElementDisplayed(SelectFromDropDown, 20)) {
////            webUtil.scrollToView(SelectFromDropDown);
//            webUtil.click(SelectFromDropDown);
//            Thread.sleep(4000);
//            log.info("pp");
//
////            if (data.getReinvestProducts().get(1).equalsIgnoreCase("Blank")) {
////                log.info("sdb");
//////                driver.findElement(SelectFromDropDown).sendKeys("", Keys.TAB);
////            } else {
////                driver.findElement(SelectFromDropDown).sendKeys(data.getReinvestAmounts().get(1), Keys.TAB);
////            }
//        }
//
//    }

//    @And("user selects a product from the dropdown list and proceeds to enter the desired amount Cash In")
//    public void userSelectsProductFromDropdownAndEntersTheAmountCashIn() throws Throwable {
//        select.userClicksOnDropdownMenuAndSelectOne(data.getReinvestAmounts());
//    }


    @And("user selects other product from the dropdown list and proceeds to enter the desired amount")
    public void userSelectsProductFromDropdownAndEntersTheAmountSecondProduct() throws Throwable {
        List<String> productsList = data.getReinvestAmounts();

        if (productsList != null && productsList.size() > 1) {
            // Get the second element from the list (index 1)
            String secondProduct = productsList.get(1);

            // If userClicksOnDropdownMenuAndSelect expects a List<String>,
            // and you only want to pass the second product as a list of one element:
            List<String> productToSelect = Arrays.asList(secondProduct);

            select.userClicksOnDropdownMenuAndSelect(productToSelect, data.getReinvestAmounts());
        } else {
            System.out.println("Error: No second product found in the data list or list is null/empty.");
        }
    }


    @And("^User click on allocate full amount button$")
    public void userClickOnAllocateFullAmountAndClickOnConfirmButton() {
        try {
            Thread.sleep(3000);
            select.allocate_fullamountandclickmodelconfrmbutton(Integer.parseInt(data.getAllocateAllAmountIndex()));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on allocate full amount button after edit$")
    public void userClickOnAllocateFullAmountAndClickOnConfirmButtonCashIn() {
        try {
            webUtil.click(Allocate);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


// Shan 31.7.25
    @And("^User clicks on Existing account checkbox & enter the Account Number$")
    public void userClickOnExistingAccount() {
        try {
            webUtil.isElementDisplayed(TickCheckBox, 5);
            webUtil.clickLog(TickCheckBox, "Click on the CheckBox");
            webUtil.sendKeys(AccNo, "1234567890");
            webUtil.click(ExistingAccountBox);
//            webUtil.sendKeys(ExistingAccountBox,"3000");
            Thread.sleep(2000);
            if (data.getReinvestAmounts().get(0).equalsIgnoreCase("Blank")) {
                driver.findElement(ExistingAccountBox).sendKeys("", Keys.TAB);
                Thread.sleep(3000);
            } else {
                driver.findElement(ExistingAccountBox).sendKeys(data.getReinvestAmounts().get(0), Keys.TAB);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User click on allocate modal confirm button$")
    public void userClickOnAllocateModalConfirmButton() {
        try {
            modal.yesSureBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on PB allocate modal Add IBAN button$")
    public void userClickOnPBAllocateModalAddIBANButton() {
        try {
            modal.addIbanPBModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // Shan 31.7.25
    @And("^User click on Close button in Insufficient Model$")
    public void userClickOnCloseModalButton() {
        try {
            modal.InsuffFunds();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on Didn't Receive Verification Code link$")
    public void userClickOnDidnotVerificationCode() {
        try {
            modal.NotReceive();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User click on PB allocate modal goBack button$")
    public void userClickOnPBAllocateModalGoBackButton() {
        try {
            modal.goBackModalPB();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on allocate modal cancel button$")
    public void userClickOnAllocateModalCancelButton() {
        try {
            modal.cancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Validate details with previous page and review transaction page,and check feilds, tootip as per fsd$")
    public void validateDetailsWithPreviousPageAndReviewTransactionPageAndCheckFeildsTootipAsPerFsd() throws Throwable {
        select.reviewPage1();
    }


    @And("User clicks on confirm button to finalize product and amount for reinvestment")
    public void userClicksOnConfirmButton() {
        try {
            select.confirmButton();
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("User select the Product & amount")
    public void userselectProdAndamount() {
        try {
            cashin_reinvest.selectProduct("Savings Bond - 3 Year");
            log.info("che3");
                    cashin_reinvest.enterAmount("49");

                    cashin_reinvest.getConfirmButtonClick();
            log.info("ches");
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("User validate the modal & clicks on Go Back button")
    public void userClicksOnGobackButton() {
        try {
            webUtil.click(GoBack);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User validate the modal & clicks on Close button")
    public void userClicksOnCloseButton() {
        try {
            webUtil.click(Close);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the details on the review page$")
    public void validateTheDetailsOnTheReviewPage() {
        try {
            Thread.sleep(2000);
            review.reviewPagedetails(data.getHoldingID(), data.getReinvestProducts(), data.getReinvestAmounts(), data.getAllocateAllAmountIndex());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("^Verify content on the otp page$")
    public void verifyContentonTheOtpPage() {
        try {
            secure.vrfySecurityPage();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Verify the error message displayed on otp page$")
    public void userEnterTheValidOtpAndVerifyTheErrorMessageDisplayed() {
        try {
//            secure.otpPageError(OTPValidationType.EXPIRED);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^User enter the valid otp on the security code page$")
    public void userEnterTheValidOtp() {
        try {
            secure.enterOtp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Confirm the transaction by clicking confirm button on verification code page")
    public void clickOnConfirmButton() {
        try {
            secure.clkCnfrmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on cancel button on the security code page$")
    public void clickOnCancelButton() {
        try {
            secure.clickCancelBtn();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^user click on didnn't recive link$")
    public void userClickOnDidnnTReciveLink() {
        try {
            secure.didnotLink();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("Click confirm button on review page to finalize the transaction")
    public void clickOnTheReviewPageConfirmButton() {
        try {
            select.clickReviewConfirmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Click on the review page cancel button$")
    public void clickOnTheReviewPageCancelButton() {
        try {
            select.clickReviewCancelButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on the review page checkbox$")
    public void UserClickOnTheReviewPageCheckbox() {
        try {
            select.clickReviewCheckBox();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Verify error message displayed on review page$")
    public void verifyErrorMessageDisplayedOnReviewPage() {
        try {
            select.verifyErrorMessageReviewPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Click on the reinvest and cashin link$")
    public void clickOnTheReinvestAndCashinLink() {
        try {
            prize.sort();
            prize.reinvestOption();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Check if the Repay-Reinvest link displayed$")
    public void checkIfTheRepayReinvestLinkDisplayed() {
        try {
            prize.checkReinvestLink();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("^Click on the reinvest tile$")
    public void clickOnTheReinvestTile() {
        try {
            prize.chooseReinvest();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("^Verify the choose amount page$")
    public void verifyTheChooseAmountPage() {
        try {
            prize.contentVerify();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User enter the amount on choose amount page")
    public void enterTheAmountForReinvest() {
        try {
            prize.enterAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("Click confirm button to finalize the chosen amount on choose amount page")
    public void clickOnConfirmButtonOfAmountPage() {
        try {
            prize.clickConfirmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Click on cancel button of Amount Page$")
    public void clickOnCancelButtonOfAmountPage() {
        try {
            prize.clickCancelButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on allocate full amount of Amount page$")
    public void clickOnAllocateFullAmountOfAmountPage() {
        try {
            prize.clickAllocateFullAmount();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("amount entered is {string} ensure error message displayed on Choose Amount page")
    public void verifyDisplayedErrorMessageContentOnAmountPage(String amountType) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: " + sheetName);
            TestData data = excelutils.getData(path, sheetName, amountType, TestData.class, "getTestcaseName");
            Assertions.assertThat(data).isNotNull();
            String expectedErrorMessage = data.getErrormessages().get(0);
            prize.error(amountType, expectedErrorMessage);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User click on confirm button on choose amount page$")
    public void userClickOnConfirmButtonOnChooseAmountPage() {
        try {
            prize.clickConfirmButton();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("^User click on choose bond range window$")
    public void userClickOnChooseBondRangeWindow() {
        try {
            prize.clickBondRangeWindow();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the choose bond range window$")
    public void verifyTheChooseBondRangeWindow() {
        try {
            prize.verifyBondRangeWindow();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User selects all prize bonds$")
    public void userSelectsAllPrizeBonds() {
        try {
            prize.selectAllPBRange();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User select from given list$")
    public void userSelectFromGivenList() {
        try {
            prize.SelectPrizeBonds(data.getBondRange());
//            prize.SelectPrizeBonds("ACK816063 - ACK816070");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Select from bond range: {string}")
    public void userSelectBondRange(String str) {
        try {
            if(str.equalsIgnoreCase("SanitySuite-BondRange")){
                String username=data.getUsername();
                Response responseBodyPortfolioList=hHelper.getPortfolioList(username);
                log.info(responseBodyPortfolioList.toString());
                String holdingIdValue = hHelper.getEligibleHoldingId(responseBodyPortfolioList);
                log.info(holdingIdValue);
                Response holdingDetailsResp=hHelper.getHoldingDetails(username,holdingIdValue);
                String holdingDetailsValue=holdingDetailsResp.toString();
//                log.info(holdingDetailsValue);
                String bondRangeValue = hHelper.getBondRanges(holdingDetailsResp).get(0);
                log.info(bondRangeValue);
                prize.SelectPrizeBonds(bondRangeValue);
            }else{
                prize.SelectPrizeBonds(str);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on the confirm button$")
    public void userClickOnTheConfirmButton() {
        try {
            prize.clickPrizeBondConfirmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the details on review transaction page prize amount$")
    public void validateTheDetailsOnReviewTransactionPagePrizeAmount() {
        try {
            review.bondRangeReviewPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify error message displayed on prize bond range window$")
    public void verifyErrorMessageDisplayedOnPrizeBondRangeWindow() {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: " + sheetName);
            TestData data = excelutils.getData(path, sheetName, "BondRange-Checkbox", TestData.class, "getTestcaseName");
            Assertions.assertThat(data).isNotNull();
            String expectedErrorMessage = data.getErrormessages().get(0);
            prize.error("BondRange-Checkbox", expectedErrorMessage);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify selected prize bond is not displayed in choose bond range$")
    public void verifySelectedPrizeBondIsNotDisplayedInChooseBondRange() throws Exception {

        try {
            prize.goBackHomepage();
            userClickonManagebtn("Prize Bond");
            prize.reinvestOption();
            prize.chooseReinvest();
            prize.clickBondRangeWindow();
            if (!prize.chooseBondRangesVerify()) {
                log.info("Selected prize bonds are not in the list");
            } else {
                log.info("Selected prize bonds are in the list");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @And("^User click on cancel button$")
    public void userClickOnCancelButton() throws Exception {
        try {
            select.cancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on allocate to cash modal$")
    public void userClicksOnAllocateToCashModal() {
        try {
            modal.allocateToCashBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User clicks on cancel button modal$")
    public void userClicksOnCancelButtonModal() {
        try {
            modal.goBackModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on yes cancel button modal$")
    public void userClickOnYesCancelButtonModal() {


    }

    @And("^User click on go back button modal$")
    public void userClickOnGoBackCancelButtonModal() {
        try {
            if (data.getOptionType().contentEquals("Cash-In")) {
                modal.cashinCancelBtnModalGoBackBtn();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^user click on goback button modal$")
    public void userClickOnGobackButtonModal() {
        try {
            modal.goBackModalFt();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on close button modal$")
    public void userClickOnCloseButtonModal() {
        try {
//            modal.clickOnCloseButtonModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on review Page yes cancel button modal$")
    public void userClickOnReviewPageYesCancelButtonModal() {
        try {
//            modal.cancelBtnModalYesCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on review Page go back button modal$")
    public void userClickOnReviewPageGoBackCancelButtonModal() {
        try {
//            modal.cancelBtnModalgoBaclBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on Security Page yes cancel button modal$")
    public void userClickOnSecurityPageYesCancelButtonModal() {
        try {
            modal.securityPageYesCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on Security Page go back button modal$")
    public void userClickOnSecurityPageGoBackCancelButtonModal() {
        try {
            modal.securityPagenoBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^User click on the manage button and select the parent Account$")
    public void UserclickonthemanagebuttonandselecttheparentAccount() {
        try {
            Cashin_Reinvest cashin_reinvest = new Cashin_Reinvest(driver);
            dashboard.clickProduct(data.getProduct());
            cashin_reinvest.clickmonthlysavings(data.getPosbAccountNumber().replaceAll("[^0-9]", ""));
            ExtentCucumberAdapter.addTestStepLog("Instalment view and manage button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("^Click on back button thank you page$")
    public void clickOnBackButtonThankYouPage() throws Throwable {
        try {
            if (data.getProduct().contentEquals("Prize Bond") && data.getOptionType().contentEquals("Cash-In")) {
                thank.verifyCashinPBThankYouPage();
            } else if (data.getProduct().contentEquals("Prize Bond") && data.getOptionType().contentEquals("Reinvest")) {
                thank.verifyReinvestPBThankYouPage();
            } else {
                thank.verifyThankYouPage();
            }
            thank.clickBackBtn();
        } catch (Exception E) {
            Assert.fail(E.getMessage(), E);
        }
    }

    @And("^Click on The cancel button$")
    public void clickOnTheCancelButton() {
        try {
            secure.clickCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User navigates to profile and setting$")
    public void Usernavigatestoprofileandsetting() {
        try {
            prize.clickProfileAndSettings();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User select prize bond settings from profile and settings menu$")
    public void userSelectPrizeBondSettingsFromProfileAndSettingsMenu() {
        try {
            prize.clickPrizeBondSettings();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User decides \"([^\"]*)\" for existing winnings$")
    public void userDecidesForExistingWinnings(String option) {
        try {
            prize.clickPrizePaymentOption(option);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @And("^User clicks confirm on PPO modal$")
    public void userClicksConfirmOnPPOModal() {
        try {
            prize.prizePaymentModal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^User verifies the notification banner displayed for PPO settings changed$")
    public void userVerifiesTheNotifiactionDisplayedForPPOSettingsChanged() {
        try {
            prize.validateNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @And("^User validates if one option is enabled then other option is disabled$")
    public void userValidatesIfOneOptionIsEnabledThenOtherOptionIsDisabled() {
        prize.validatePPoption();
    }

    @And("^User add IBAN according to \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
    public void userAddIBANAccordingTo(String journey, String IBAN, String number) {
        try {
            prize.addBankJourney(journey, IBAN, number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^User click add bank details on PPO modal$")
    public void userClickAddBankDetailsOnPPOModal() {
        try {
            prize.addBankDetailsModal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^Validate both PrizePayment option are disabled$")
    public void validateBothPrizePaymentOptionAreDisabled() {
        prize.checkAutoreinvest();
        prize.checkTransfer();
    }


    @SneakyThrows
    @When("User selects the product and clicks on manage button")
    public void userSelectTheProductAndClicksOnManageButton() {
        Thread.sleep(3000);
        dashboard.clickProduct(data.getProduct());
//        dashboard.clickProduct("Saving Bond");

    }

    @Then("validate {string} modal content")
    public void validateModalIsDisplayed(String sliderName) throws FileNotFoundException {
        switch (sliderName) {
            case "Allocate full amount":
                modal.allocateFullAmountModal(sliderName);
                break;
            case "Remove Product":
                modal.removeProductModal(sliderName);
                break;
            case "Allocate full amount (reinvest into PB No IBAN) modal":
                modal.AddIbanPBAllocateModal(sliderName);
                break;
            case "Purchase Prize Bonds Modal":
                modal.PrizeBondModal(sliderName);
                break;
            case "Allocate full amount (reinvest into PB modal)":
                String IBAN = apiDriver.getUserDetails(data.getUsername()).get("bankAccountIban").replaceAll("[^0-9]", "");
                modal.allocateButtonModalPB(sliderName, IBAN);
                break;
            case "Available amount exceeded":
                modal.availableAmountExceededModal(sliderName);
                break;
            case "You must allocate full amount(less amount entered)":
                modal.lessAmountEnteredModal(sliderName);
                break;
            case "You must allocate full amount (allocate to cash)":
                modal.allocateToCashModal(sliderName);
                break;
            case "You must allocate full amount (IBAN not added)":
                modal.mustAllocateAllNoIBANModal(sliderName);
                break;
            case "PB :You must allocate full amount(allocate to cash)":
                modal.reinvestPBAllocateToCashModal(sliderName);
                break;
            case "Insufficient Fund":
                modal.insufficientFundsModal(sliderName);
                break;
            case "Cancel Transaction":
                modal.cancelTransactionModal(sliderName);
                break;
            case "PB Joint Holdings:":
            case "FT Joint Holdings: Non matured":
            case "FT Joint Holdings: Matured":
                modal.jointHoldingsModal(sliderName);
                break;
            case "Add Holding Cancel":
                modal.addHoldingCancelModal(sliderName);
                break;
//            case "Sign Out":
//                modal.signOutModal(sliderName);
//                break;
//            case "Pending Transaction":
//                modal.pendingTransactionModal(sliderName);
//                break;
//            case "Buy now-Registered for Ireland State Savings Online?"
//                modal.buyNowIrelandModal(sliderName);
//                break;
//            case "Dashboard PB-Buy now":
//                modal.buyNowPBModal(sliderName);
//                break;
//            case "Buy now:Cancel transaction":
//                modal.buyNowCancelTransactionModal(sliderName);
//                break;
//            case "Remove Product":
//                modal.removeProductModal(sliderName);
//                break;
//            case "PPO: Toggle enable disable":
//                modal.PPOToggleEnableDisableModal(sliderName);
//                break;
//            case "PPO: Toggle enable disable(IBAN not added)":
//                modal.PPOAddIBANModal(sliderName);
//                break;
//            case "Notification Settings:Alerts/Messages enable disable:":
//                modal.notificationSettingsModal(sliderName);
//                break;
//            case "ECR Pin Modal:Continue registration":
//                modal.ecrPinModal(sliderName);
//                break;
//            case "ECR Re-issue limit extended":
//                modal.ecrPinReissueModal(sliderName);
//                break;
//            case "ECR cancel registration":
//                modal.ecrPinCancelModal(sliderName);
//                break;

            default:
                throw new IllegalStateException("Unexpected value: " + sliderName);

        }

    }

    // Comparing Actual as Xpath & compares with Expected as String
    @And("Validate PB Page content") // Shan code
    public void validatePBPageContent() {
        try {
            webUtil.gettextlog(prize.HeaderContent, String::equals, ExpectedHeader, "Main Header");
            webUtil.gettextlog(prize.ParagraphSubHeader, String::equals, ExpectedSubHeader, "Paragraph Header");
            webUtil.gettextlog(prize.Paragraph, String::equals, ExpectedPara, "Paragraph");

        } catch (NoSuchElementException e) {
            log.info("Element not found: \" + e.getMessage()");
        } catch (StaleElementReferenceException e) {
            log.info("Stale element issue: \" + e.getMessage()");
        }

    }

    @Then("validate {string} modal content for Modals")
    public void validateModalIsDisplayedModal(String sliderName) throws FileNotFoundException {
        switch (sliderName) {
            case "Insufficient Fund":
                modal.insufficientFundsModal(sliderName);
                break;
            case "Remove Product":
                modal.removeProductModal(sliderName);
                break;
        }
    }

    @And("Validate the details on review transaction page prize amount for Sanity Suite-BondRange")
    public void validateTheDetailsOnReviewTransactionPagePrizeAmountForSanitySuiteBondRange() {
        try {
            review.lightBondRangeReviewPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
