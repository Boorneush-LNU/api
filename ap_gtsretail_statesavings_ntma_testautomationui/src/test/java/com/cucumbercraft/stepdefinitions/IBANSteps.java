package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.*;
import com.cucumbercraft.POMPages.IBAN.Add_IBAN;
import com.cucumbercraft.POMPages.IBAN.Add_IBAN_Profile_And_Setting;
import com.cucumbercraft.POMPages.IBAN.Change_IBAN;
import com.cucumbercraft.POMPages.RepayReinvest.Holdings;
import com.cucumbercraft.POMPages.RepayReinvest.SelectProduct;
import com.cucumbercraft.entity.OTPValidationType;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class IBANSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    Change_IBAN change = new Change_IBAN(driver);
//    private final WebDriverUtil webUtil;
    Add_IBAN add = new Add_IBAN(driver);
    Add_IBAN_Profile_And_Setting addiban = new Add_IBAN_Profile_And_Setting(driver);
    Holdings holdings = new Holdings(driver);
    StateSavingsDashboardPage dashboardPage = new StateSavingsDashboardPage(driver);
    SliderContent sliderContent = new SliderContent(driver);
    ProfileAndSettingsPg profile = new ProfileAndSettingsPg(driver);
    Security_Page securityPage = new Security_Page(driver);
    private final SelectProduct select = new SelectProduct(driver);
    String number1;
    String details;
    String journey;
    private final By chgIbanVericancel = By.xpath("//button[@class='gtm-cta button button--secondary' and @onclick='ShowIbanInitialModal()'  and text()='Cancel']");


//    public IBANSteps(WebDriverUtil webUtil) {
//        this.webUtil = webUtil;
//    }

    @And("User click on close button on changeIban Verification Slider")
    public void userClickOnChangeIbanSliderVerificationCloseButton() {
        try {
            select.chgIbanVerificationCloseBtn();;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User click on cancel button on code not received Slider")
    public void userClickOnCodeNotReceivedSliderlCancelButton() throws Exception {
        try {
            select.chgIbanNotReceivedCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User click on close button on code not received Slider")
    public void userClickOnCodeNotReceivedSliderCloseButton() throws Exception {
        try {
            select.chgIbanNotReceivedCloseBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User click on close button on changeIban Slider")

    public void userClickOnChangeIbanSliderCloseButton() {
        try {
            select.changeIbanCloseBtn();;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @And("^user Enter the \"([^\"]*)\" \"([^\"]*)\" and tick the checkbox$")
    public void userEnterTheAndTickTheCheckbox(String ibanDetail, String iban) {

        try {
            add.enterIbanField(iban);
            add.clickCheckBox();
            this.details = ibanDetail;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user Enter the \"([^\"]*)\" \"([^\"]*)\" and not tick the checkbox$")
    public void userEnterTheAndNotTickTheCheckbox(String ibanDetail, String iban) {
        try {
            add.enterIbanField(iban);
            this.details = ibanDetail;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("User click on cancel button on changeIban verification Slider")
    public void clickCancelBtnOnVerificationSlider() {
        try {
            select.chgIbanVerificationCanceleBtn();;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }




//    public void chgIbanVerificationCanceleBtn() throws Exception {
//        if (webUtil.isElementclickable(chgIbanVericancel, 20)) {
//            webUtil.click(chgIbanVericancel);
//        } else {
//            throw new ExceptionUtils("Close button x-path changed");
//        }
//    }


    @And("^Verify the checkbox error message$")
    public void userClickOnVerifyBankDetailButtonAndVerifyTheCheckboxErrorMessage() {

        try {
            add.checkBoxErrorFun();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @And("^user click on cancel button$")
    public void userClickOnCancelButton() {

        try {
            add.cancelBtnClick();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on verify bank detail button$")
    public void userClickOnVerifyBankDetailButton() {
        try {
            add.confirmBtnClick();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @And("^User click on your Savings Tab$")
    public void userClickOnYourSavingsTab() {
        try {
            change.clickYourSavings();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User click on cancel button on Slider")
    public void userClickOnSliderCancelButton() throws Exception {
        try {
            select.cancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User click on cancel button on Slider bar")
    public void userClickOnSliderCancelButtonIban() throws Exception {
        try {
            select.cancelBtnIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }




    @And("User click on close button on addIban Slider")
    public void userClickOSliderCloseButton() {
        try {
            select.addIbanCloseBtn();;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @And("^user click on the add now iban button$")
    public void userClickOnTheAddNowIbanButton() {
        try {
            add.clickAddNowBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @And("^user click on the change button$")
    public void userClickOnTheChangeButton() {
        try {
            change.clickChangeButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the add iban modal displayed$")
    public void verifyTheAddIbanModalDisplayed() {
        try {
            change.verifyContentEnterIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^user click on didn't receive link$")
    public void userClickOnDidnTReceiveLink() {
        try {
            add.clickDidntReciveLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user clicks on cancel button$")
    public void userClicksOnCancelButton() {
        try {
            change.clickcnclBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^verify the iban is not updated$")
    public void verifyTheIbanIsNotUpdated() {
        try {
            add.verifyCurrentIbanNewIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("When IBAN entered is {string} ensure error message displayed")
    public void verifyTheErrorMessageDisplayedOnIBANPage(String ibanType) {
        try {
            String path = FrameworkConstants.getExcelLocationAutomationRegression();
            String sheetName = config.getErrorSheetName();
            data = excelutils.getData(path, sheetName, ibanType, TestData.class, "getTestcaseName");
            Assertions.assertThat(data).isNotNull();
            var expectedError = data.getErrormessages().get(0);

            switch (ibanType) {

                case "IBAN-Blank":
                    add.clickCheckBox();
                    add.confirmBtnClick();
                    change.error("Blank", expectedError);
                    break;
                case "IBAN-Invalid":
                    add.enterIbanField(data.getIBAN());
                    add.clickCheckBox();
                    add.confirmBtnClick();
                    change.error("Invalid", expectedError);
                    break;
                case "IBAN-Non Sepa":
                    add.enterIbanField(data.getIBAN());
                    add.clickCheckBox();
                    add.confirmBtnClick();
                    change.error("Non-Sepa", expectedError);
                    break;
                case "IBAN-ConsentError":
                    add.enterIbanField(data.getIBAN());
                    add.confirmBtnClick();
                    change.error("Consent Error", expectedError);
                    break;
                default:
                    Assert.fail("Invalid IBAN type");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user clicks on the verify bank detail \"([^\"]*)\" button and verify the error message displayed$")
    public void userClicksOnTheVerifyBankDetailButtonAndVerifyTheErrorMessageDisplayed(String detailIban) {
        try {
            change.clickConfrmBtn();
            change.error(detailIban, "");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the otp page content$")
    public void verifyTheOtpPageContent() throws Throwable {
        try {
            change.otpPageContent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user enters the expired otp and click on the confirm button$")
    public void userEntersTheExpiredOtpAndClickOnTheConfirmButton() {
        try {
            change.number1 = number1;
            change.otpPageEnterOtpExpired();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Verify iban is displayed in your bank detail$")
    public void VerifyIbanIsDisplayedInYourBankDetail() {
        try {
            add.verifyCurrentIbanNewIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User enter the iban$")
    public void UserEntersTheAnd() {
        try {

            sliderContent.enterTextField(profile.addIBANSlider, data.getIBAN());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Tick the checkbox on the iban page$")
    public void TickTheCheckboxOnTheIbanPage() {
        try {

            addiban.clickCheckBox();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on the verify bank button$")
    public void UserClickOnTheVerifyBankButton() {
        try {

            addiban.cnfrmBtnClick();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User click on Didn't receive the code")
    public void userClickOnCodeNotReceived() throws Exception {
        try {
            change.userClickOnCodeNotReceivedIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @And("^User clicks on otp cancel button$")
    public void UserClicksOnOtpCancelButton() {
        try {
            WebElement element=new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(add.addIBANOtpSlider));
            sliderContent.clickSecondaryBtn(element);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on didn't receive link$")
    public void UserClicksOnDidnTReceiveLink() {
        try {

            addiban.clickDidntReciveLink();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User click on the cancel button$")
    public void UserClickOnTheCancelButton() {
        try {
                WebElement element =new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(add.addIBANSlider));
                sliderContent.clickSecondaryBtn(element);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @When("otp submitted is:{string} ensure error message displayed")
    public void UserEntersTheExpiredOtpAndClickOnConfirmButton(String otpType) {

        try {
            securityPage.validateAddIBANOtpError(OTPValidationType.valueOf(otpType));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User selects the Option$")
    public void userSelectsTheOption() {
        try {
            holdings.selectOption(data.getOptionType());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


}
