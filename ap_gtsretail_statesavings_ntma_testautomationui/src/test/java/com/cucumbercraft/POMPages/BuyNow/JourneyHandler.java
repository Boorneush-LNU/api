package com.cucumbercraft.POMPages.BuyNow;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.POMPages.Modals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class JourneyHandler {

    WebDriver driver;
    Modals modals;
    YourDetail yourDetail;
    Map<String, Runnable> journeyMap;


    public JourneyHandler(WebDriver driver) {
        this.driver = driver;
        modals = new Modals(driver);
        yourDetail = new YourDetail(driver);
    }

    public YourDetail guestJourney() {
        return modals
                .validateBuyNowModalContent()
                .clickGuest();

    }

    public YourDetail guestJourneyIrish() {
        return modals
                .validateBuyNowModalContentIrish()
                .clickGuestIrish();

    }

    public YourDetail signinJourney(String username, String password, String mobile) throws Exception {

        return modals
                .clickSignIn()
                .typeInUsername(username)
                .typeInPassword(password)
                .clickBtnSignIn()
                .typeInOTP(mobile)
                .clickBtnVerify(mobile);
    }

    public YourDetail signinJourneyIrish(String username, String password, String mobile) throws Exception {

        return modals
                .clickSignIn()
                .typeInUsername(username)
                .typeInPassword(password)
                .clickBtnSignIn()
                .typeInOTP(mobile)
                .clickBtnVerifyIrish(mobile);

    }



    public Runnable getJourney(String journey, PurchaseModel data) {

        journeyMap = Map.of(
                "Guest", () ->
                        yourDetail.enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                                data.getFirstUserDOB().split("-"),
                                data.getFirstUserSSCN(),
                                data.getFirstUserEmail())
                                .agreeTerms()
                                .submitDetails()
                ,
                "GuestJoint", () -> yourDetail
                        .selectJoint()
                        .enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                                data.getFirstUserDOB().split("-"),
                                data.getFirstUserSSCN(),
                                data.getFirstUserEmail())
                        .enterSecondaryApplicantDetails(data.getSecondUserSurname(),
                                data.getSecondUserDOB().split("-"),
                                data.getSecondUserSSCN(),
                                data.getSecondUserEmail())
                        .agreeTerms()
                        .submitDetails()
                ,
                "SignIn", () ->
                        yourDetail.validatePrimaryApplicantDetails(data.getFirstUserEmail())
                                .agreeTerms()
                                .submitDetails()
                ,
                "SignInJoint", () ->
                        yourDetail.selectJoint()
                                .validatePrimaryApplicantDetails(data.getFirstUserEmail())
                                .withSecondaryApplicantSurname(data.getSecondUserSurname())
                                .withSecondaryApplicantBirthdate(data.getSecondUserDOB().split("-"))
                                .withSecondaryApplicantSSCN(data.getSecondUserSSCN())
                                .withSecondaryApplicantEmail(data.getSecondUserEmail())
                                .agreeTerms()
                                .submitDetails()
                ,"GuestError",()->{
                    yourDetail.submitDetails();
                yourDetail.validateGuestYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail.enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                                    data.getFirstUserDOB().split("-"),
                                    data.getFirstUserSSCN(),
                                    data.getFirstUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }
                ,"GuestJointError",()->{
                    yourDetail.selectJoint().submitDetails();
                    yourDetail.validateGuestYourDetailError().validateJointYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail
                            .enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                                    data.getFirstUserDOB().split("-"),
                                    data.getFirstUserSSCN(),
                                    data.getFirstUserEmail())
                            .enterSecondaryApplicantDetails(data.getSecondUserSurname(),
                                    data.getSecondUserDOB().split("-"),
                                    data.getSecondUserSSCN(),
                                    data.getSecondUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }
                ,"SignInError",()->{
                    yourDetail.submitDetails();
                    yourDetail.validateSignInYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail.validatePrimaryApplicantDetails(data.getFirstUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }
                ,"SignInJointError",()->{
                    yourDetail.selectJoint().submitDetails();
                    yourDetail.validatePrimaryApplicantDetails(data.getFirstUserEmail())
                            .validateJointYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail
                            .validatePrimaryApplicantDetails(data.getFirstUserEmail())
                            .withSecondaryApplicantSurname(data.getSecondUserSurname())
                            .withSecondaryApplicantBirthdate(data.getSecondUserDOB().split("-"))
                            .withSecondaryApplicantSSCN(data.getSecondUserSSCN())
                            .withSecondaryApplicantEmail(data.getSecondUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }

        );

        return journeyMap.get(journey);
    }


    public Runnable getJourneyIrish(String journey, PurchaseModel data) {

        journeyMap = Map.of(
                "Guest", () ->
                        yourDetail.enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                                data.getFirstUserDOB().split("-"),
                                data.getFirstUserSSCN(),
                                data.getFirstUserEmail())
                                .agreeTerms()
                                .submitDetailsIrish()
                ,
                "GuestJoint", () -> yourDetail
                        .selectJoint()
                        .enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                                data.getFirstUserDOB().split("-"),
                                data.getFirstUserSSCN(),
                                data.getFirstUserEmail())
                        .enterSecondaryApplicantDetails(data.getSecondUserSurname(),
                                data.getSecondUserDOB().split("-"),
                                data.getSecondUserSSCN(),
                                data.getSecondUserEmail())
                        .agreeTerms()
                        .submitDetails()
                ,
                "SignIn", () ->
                        yourDetail.validatePrimaryApplicantDetails(data.getFirstUserEmail())
                                .agreeTerms()
                                .submitDetails()
                ,
                "SignInJoint", () ->
                        yourDetail.selectJoint()
                                .validatePrimaryApplicantDetails(data.getFirstUserEmail())
                                .withSecondaryApplicantSurname(data.getSecondUserSurname())
                                .withSecondaryApplicantBirthdate(data.getSecondUserDOB().split("-"))
                                .withSecondaryApplicantSSCN(data.getSecondUserSSCN())
                                .withSecondaryApplicantEmail(data.getSecondUserEmail())
                                .agreeTerms()
                                .submitDetails()
                ,"GuestError",()->{
                    yourDetail.submitDetails();
                    yourDetail.validateGuestYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail.enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                            data.getFirstUserDOB().split("-"),
                            data.getFirstUserSSCN(),
                            data.getFirstUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }
                ,"GuestJointError",()->{
                    yourDetail.selectJoint().submitDetails();
                    yourDetail.validateGuestYourDetailError().validateJointYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail
                            .enterPrimaryApplicantDetails(data.getFirstUserSurname(),
                                    data.getFirstUserDOB().split("-"),
                                    data.getFirstUserSSCN(),
                                    data.getFirstUserEmail())
                            .enterSecondaryApplicantDetails(data.getSecondUserSurname(),
                                    data.getSecondUserDOB().split("-"),
                                    data.getSecondUserSSCN(),
                                    data.getSecondUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }
                ,"SignInError",()->{
                    yourDetail.submitDetails();
                    yourDetail.validateSignInYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail.validatePrimaryApplicantDetails(data.getFirstUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }
                ,"SignInJointError",()->{
                    yourDetail.selectJoint().submitDetails();
                    yourDetail.validatePrimaryApplicantDetails(data.getFirstUserEmail())
                            .validateJointYourDetailError();
                    ExtentCucumberAdapter.addTestStepLog("Field Errors are validated on Your Details page");
                    yourDetail
                            .validatePrimaryApplicantDetails(data.getFirstUserEmail())
                            .withSecondaryApplicantSurname(data.getSecondUserSurname())
                            .withSecondaryApplicantBirthdate(data.getSecondUserDOB().split("-"))
                            .withSecondaryApplicantSSCN(data.getSecondUserSSCN())
                            .withSecondaryApplicantEmail(data.getSecondUserEmail())
                            .agreeTerms()
                            .submitDetails();
                }

        );

        return journeyMap.get(journey);
    }







}
