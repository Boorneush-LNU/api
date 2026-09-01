package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.Modals;
import com.cucumbercraft.POMPages.RepayReinvest.Repayment;
import com.cucumbercraft.POMPages.RepayReinvest.ReviewTransactionPage;
import com.cucumbercraft.POMPages.RepayReinvest.SelectProduct;
import com.cucumbercraft.POMPages.RepayReinvest.Thank_You_Page;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExceptionUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;


public class RepaymentSteps extends MasterStepDefs {
    private final WebDriver driver = DriverManager.getWebDriver();
    private final Repayment cashIn = new Repayment(driver);
    private final SelectProduct select = new SelectProduct(driver);
    private final Repayment cashin = new Repayment(driver);
    private final ReviewTransactionPage review = new ReviewTransactionPage(driver);
    private final Thank_You_Page thank = new Thank_You_Page(driver);
    private final Modals modal = new Modals(driver);
    static final Logger log = LogManager.getLogger(RepaymentSteps.class);

    @Then("^User enters Amount for Cashin$")
    public void userEnterAmountForCashin() {
        try {
            cashIn.verifyCashPage();
            cashIn.enterAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Then("^User Reenters Amount for Cashin$")
    public void userReEnterAmountForCashin() {
        try {
            cashIn.verifyCashPage();
            cashIn.ReEnterAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Then("^Enter the Amount in the Reinvest Textbox$")
    public void userReEnterAmountForReinvest() {
        try {
            cashIn.ReInvestAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @Then("^user clear the Reinvest Amount$")
    public void userClearAmountForReinvest() {
        try {
            cashIn.ClearReInvestAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Then("^User enter amount in CashIn Section$")
    public void userEnterAmountINCashIn() {
        try {
            cashIn.CashInAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @Then("User enters {string} for Cashin")
    public void userEnterAmount(String str) {
        try {
            cashIn.verifyCashPage();
            cashIn.enterAmount(str);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Validate details with previous page and review transaction page as per fsd$")
    public void validateDetailsWithPreviousPageAndReviewTransactionPageAsPerFsdAnd() {
        try {
            ReviewTransactionPage review = new ReviewTransactionPage(driver);
            review.reviewAllocated();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^User click on allocate full amount$")
    public void userClickOnAllocateFullAmount() {
        try {
            cashIn.clickFullAmountandConfirm(data.getAllocateAllAmountIndex());
//            cashIn.clickFullAmountandConfirm("yes");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on the confirm button$")
    public void userClicksOnTheConfirmButton() throws Exception {
        try {
            cashIn.clickCnfrmButton();
            log.info("Confirm button on enter amount page is clicked");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("Verify the error message displayed when amount entered is {string}")
    public void verifyTheErrorMessageDisplayed(String errorType) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            System.out.println("Sheet Name: " + sheetName);
            TestData data = excelutils.getData(path, sheetName, errorType, TestData.class, "getTestcaseName");
            Assertions.assertThat(data).isNotNull();
            String expectedErrorMessage = data.getErrormessages().get(0);
            cashIn.verifyErrorMessage(errorType, expectedErrorMessage);


            log.info("Error message displayed");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }














    @And("^Verify the thank you message displayed$")
    public void verifyTheThankYouMessageDisplayed() {
        try {
            cashIn.verifyThankYouMessage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @And("^User clicks on the Allocate to cash-in button$")
    public void userClicksOnTheAllocateToCashInButtonandClickonconfirmbutton() {
        try {
            modal.cashinconfirmbuttonAllocatetocashinmodal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on the Allocate Go back button$")
    public void userClicksOnTheAllocateGoBackButton() {
        try {
            modal.cashingoBackBtnModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on allocate full amount and click on the cancel button$")
    public void userClickOnAllocateFullAmountAndClickOnTheCancelButton() {
        try {
            modal.clickAllocateFullAmountBtn();
            modal.cashinAllocateModalCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Validate the details with previous page and review transaction page as per fsd$")
    public void validateTheDetailsWithPreviousPageAndReviewTransactionPageAsPerFsd() {
        try {
            review.reviewAllocatedCashin();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User clicks on the cancel button$")
    public void userClicksOnTheCancelButton() {
        try {
            cashIn.clickCancelButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the details on review transaction page$")
    public void validateTheDetailsOnReviewTransactionPage() {
        try {
            if (data.getOptionType().contentEquals("Reinvest") && data.getOptionType().contentEquals("Prize Bond")) {
                review.reviewPBReinvest();
            }
            review.cashInReviewPB();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on allocate full amount button and click on confirm button$")
    public void clickOnAllocateFullAmountButtonAndClickOnConfirmButton() {
        try {
            modal.clickAllocateFullAmountBtn();
            cashIn.clickCnfrmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the detail on review transaction page$")
    public void validateTheDetailOnReviewTransactionPage() {
        try {
            review.reviewAllocatedCashin();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Tick in the detail on review transaction page$")
    public void TickInReviewTransactionPage() {
        try {
            review.contentvalidation();
        } catch (Exception e) {
            log.info("Content is not verified on the review page");
            throw new ExceptionUtils("Review page content is not verified");
        }
    }


    @And("^Click on the delay checkbox$")
    public void clickOnTheDelayCheckbox() {
        try {
            review.delayCheckbox();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify thank you page$")
    public void verifyThankYouPage() {
        try {
            thank.verifyThankYouPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Thank you page is displayed$")
    public void thankYouPageIsDisplayed() {
        try {
            thank.verifyHeaderThankYouPage();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @Then("User click add bank details on details page")
    public void userClickAddBankDetailsOnDetailsPage() {
        try {
            cashIn.clickAddIBAN();
            log.info("Add bank details button is clicked");
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
