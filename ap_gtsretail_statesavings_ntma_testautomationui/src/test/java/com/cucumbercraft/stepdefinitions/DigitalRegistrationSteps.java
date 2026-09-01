package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.DigitalRegistration.*;
import com.cucumbercraft.POMPages.ExpeditedReg.Expedited_Registration;
import com.cucumbercraft.POMPages.WebReg.CheckDetailsAndContin;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DigitalRegistrationSteps extends MasterStepDefs {

    private final WebDriver driver = DriverManager.getWebDriver();
    private final WebDriverUtil webUtil = new WebDriverUtil(driver);
    TermsOfService Terms = new TermsOfService(driver);
    CheckList checklist = new CheckList(driver);
    CheckDetailsAndContin checkDetails = new CheckDetailsAndContin(driver);
    ContactDetailsAndContinue ContactDetails = new ContactDetailsAndContinue(driver);
    CheckDetails checkDetailsDig = new CheckDetails(driver);
    ConfirmMobileNo confirmMobileNo = new ConfirmMobileNo(driver);
    MobileNumOTP mobileNumOTP = new MobileNumOTP(driver);
    CheckInbox checkInbox = new CheckInbox(driver);
    ScanQRCode scanQRCode = new ScanQRCode(driver);



    @Then("^User validates content in Terms of service page$")
    public void userLaunchesTheStateSavingsPortalAndClickOnRegister() {
        try {
            Terms.TermsOfService();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Validate Checkbox content & Click on all the checkbox$")
    public void ValidateAndClickCB() {
        try {
            Terms.CheckBox();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^click on next button on Terms of service Page$")
    public void clickOnNextButtonTermsOfServicePage() {
        try {
            Terms.clickNextBtn();
        } catch (Exception e) {
           Assert.fail(e.getMessage());
        }
    }


    @Then("^Validate contents in Checklist Page$")
    public void CheckListPage() {
        try {
            checklist.CheckList();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    @Then("^click on next button on Checklist Page$")
    public void clickOnNextButtonChecklistPage() {
        try {
            checklist.clickNextBtn();
        } catch (Exception e) {
           Assert.fail(e.getMessage());
        }
    }

    @And("^User reaches Contact details page & enter the details$")
    public void ValidateContactDetailsPage() {
        try {
            ContactDetails.ContactDetailsPage();
            ContactDetails.enterAllDetailsForDigitalUser();
        } catch (Exception e) {
           Assert.fail(e.getMessage());
        }
    }

    @Then("^click on Consent Checkbox & next button on Contact Details Page$")
    public void clickOnConsentAndNextButtonContactDetailsPage() {
        try {

            ContactDetails.MobileReg();
            ContactDetails.checkConsent();
            ContactDetails.clickNextBtn();
        } catch (Exception e) {
           Assert.fail(e.getMessage());
        }
    }


    @And("^Validate the entered mail & mobileNumber in checkDetails Page & clicks next$")
    public void ValidateAndclickOnNextButtonCheckDetailsPage() {
        try {
            checkDetailsDig.ContactDetailsPage();
            checkDetailsDig.verifyEmail();
            checkDetailsDig.verifyNumber();
            checkDetailsDig.clickNextBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^User lands on Confirm Mobile Number Page & click on Send code$")
    public void ValidateAndclickOnConfrimMobileNumPage() {
        try {
            confirmMobileNo.ConfirmMobileNumDetailsPage();
            confirmMobileNo.clickSendCodeBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^Enter the security code for Digital Registration$")
    public void EnterOTPPage() {
        try {
            mobileNumOTP.ConfirmMobileOTPPage();
            mobileNumOTP.enterSecuritycode();
            mobileNumOTP.clickConfirmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^verify check your inbox page for DigitalReg$")
    public void verifyCheckYourInboxPage() {
        try {
            checkInbox.InboxPage();
            checkInbox.UserInbox();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^Scan QR code displays & click on Scan QR code$")
    public void verifyScanQRPage() {
        try {
            scanQRCode.ContinueMobilePage();
            scanQRCode.ContinueMobileSteps();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


}
