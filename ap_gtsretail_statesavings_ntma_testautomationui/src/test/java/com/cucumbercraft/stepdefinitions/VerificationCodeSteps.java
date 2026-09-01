package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.Security_Page;
import com.cucumbercraft.POMPages.SliderContent;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class VerificationCodeSteps extends  MasterStepDefs{

    WebDriver driver = DriverManager.getWebDriver();
    SliderContent sliderContent = new SliderContent(driver);
    Security_Page securityPage = new Security_Page(driver);
    WebDriverUtil webUtil = new WebDriverUtil(driver);

    @Then("enter the verification code")
    public void enterTheVerificationCode() {
        try {
            String otpResponse = apiDriver.getOTP(config.getOTPEndpoint(),200, data.getMobileNumber());
            sliderContent.enterVerCode(otpResponse);

        } catch (Exception e) {
            Assert.fail("Change Password: Failed to get OTP: " + e.getMessage());
        }


    }

    @Then("click on confirm button on verification code slider")
    public void clickOnConfirmButtonOnVerificationCodeSlider() {
        try {
            WebElement element = webUtil.waitUntilElementVisible(securityPage.verificationCodeSlider,10);
            sliderContent.clickPrimayBtn(element);
        } catch (Exception e) {
            Assert.fail("Change Password: Failed to click confirm button on verification code- " + e.getMessage());
        }
    }

    @When("verification code entered is {string} ensure error message displayed")
    public void verificationCodeEnteredIsEnsureErrorMessageDisplayed(String feild) {
        String path = FrameworkConstants.getExcelLocationAutomationRegression();
        String sheetName = config.getErrorSheetName();
        System.out.println("Sheet Name: "+sheetName);
        TestData getData = excelutils.getData(path, sheetName, feild, TestData.class,"getTestcaseName");
        Assertions.assertThat(getData).isNotNull();
        securityPage.errorCheck(feild,getData);
    }
}
