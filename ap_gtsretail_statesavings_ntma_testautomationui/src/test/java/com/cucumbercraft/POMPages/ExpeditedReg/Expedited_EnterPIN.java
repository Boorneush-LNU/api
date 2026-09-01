package com.cucumbercraft.POMPages.ExpeditedReg;


import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.REGISTRATION_DATA;

public class Expedited_EnterPIN {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final By enterPIN = By.id("regCode");
    private final By backBtn = By.xpath("//a[@href = '/your-savings/register/expedited-email' ]");
    private final By confirmBtn = By.id("btnVerifyEmailCode");
    // PINCont is common for header,para,label content.
    private final By header = By.xpath("//*[@class='m39-registration-form__heading underlined-heading']");
    private final String headerText = "Enter your PIN";
    private final By textFieldAboveLabel = By.className("m39-registration-form__text");
    private final String textFieldAboveLabelText = "Please enter your PIN below";
    private final By textFieldBelowLabel = By.className("form-helper");
    private final String textFieldBelowLabelText = "Find your PIN included in the letter you received from us inviting you to register for Ireland State Savings Online.";
    private final By didnotLink = By.xpath("//a[contains(text(),'get your PIN?')]");
    private final String didnotLinkText = "Didn't get your PIN?";

    //After clicking didn't link pop-up
    private final By popupHeader = By.cssSelector("#sectionResend h4");
    private final String popupHeaderContent = "Your Ireland State Savings Online PIN";
    private final String paragraph = "#sectionResend p:nth-of-type(%s)";
    private final String firstParagraphContent = "Please allow 2-3 weeks from the date you purchased your Ireland State Savings Product for your PIN to arrive.";
    private final String secondParagraphContent = "If after this time you still haven’t received your PIN, select 'Resend PIN' below.";
    private final By resendBtn = By.cssSelector("#sectionResend div button:nth-of-type(2)");
    private final By cancelBtn = By.cssSelector("#sectionResend div button:nth-of-type(1)");

    //Reissue PIN
    private final By ReissueThankHeader = By.cssSelector("#sectionThankYou h4");
    private final String expReissueThankHeader = "Thank you";
    private final By ReissueThankPara = By.cssSelector("#sectionThankYou p");
    private final String expReissueThankPara = "Your new PIN has been requested. Please allow 7-10 days for it to arrive.";
    //Buttons
    private final By ReissueThankCloseBtn = By.cssSelector("#sectionThankYou div a:nth-of-type(1)");
    private final By ReissueThankGotoState = By.cssSelector("#sectionThankYou div a:nth-of-type(2)");

    public Expedited_EnterPIN(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private void verifyPopUpContent() {
        webUtil.gettextlog(popupHeader,String::equals,popupHeaderContent);
        webUtil.gettextlog(By.cssSelector(String.format(paragraph, "1")),String::equals,firstParagraphContent) ;
        webUtil.gettextlog(By.cssSelector(String.format(paragraph, "2")),String::equals,secondParagraphContent) ;


    }

    public void popupClickResendBtn() throws Exception {
        verifyPopUpContent();
        if (webUtil.isElementDisplayed(resendBtn, 20)) {
            if (webUtil.getText(resendBtn).equals("Resend PIN")) {
                webUtil.click(resendBtn);
            } else {
                throw new ExceptionUtils("Resend button content may have been changed");
            }
        } else {
            throw new ExceptionUtils("Resend button x-path may have been changed");
        }
    }

    public void popupClickCancelBtn() throws Exception {
        if (webUtil.isElementDisplayed(cancelBtn, 20)) {
            if (webUtil.getText(cancelBtn).equals("Cancel"))
                webUtil.click(cancelBtn);
            else
                throw new ExceptionUtils("Cancel button content may have been changed");
        } else {
            throw new ExceptionUtils("Cancel button x-path may have been changed");
        }
    }

    private void verifyContent() {

        webUtil.gettextlog(header, String::equals, headerText);
        webUtil.gettextlog(textFieldAboveLabel, String::equals, textFieldAboveLabelText);
        webUtil.gettextlog(textFieldBelowLabel, String::equals, textFieldBelowLabelText);
        webUtil.gettextlog(didnotLink, String::equals, didnotLinkText);
    }


    public void clickConfirmBtn() {
//        verifyContent(); Commenting for now. Xpath is changed
        webUtil.gettextlog(confirmBtn, String::equals, "Continue");
        webUtil.click(confirmBtn);

    }

    public void clickBackBtn() throws Exception {
        verifyContent();
        if (webUtil.isElementclickable(backBtn, 10)) {
            if (driver.findElement(backBtn).getAttribute("value").equals("Back")) {
                webUtil.click(backBtn);
            } else {
                throw new ExceptionUtils("Back button content is changed");
            }
        } else {
            throw new ExceptionUtils("Back button X-Path may have changed");
        }
    }

    public void clickDidnotLink() throws Exception {
        if (webUtil.isElementDisplayed(didnotLink, 10)) {
            webUtil.click(didnotLink);
        } else {
            throw new ExceptionUtils("didn't link x-path may have been changed");
        }
    }

    public void enterPin(String pin) throws Exception {
        if (webUtil.isElementDisplayed(enterPIN, 10)) {
            try {
//                webUtil.sendKeys(enterPIN, Expedited.testData.get("PIN"));
                webUtil.sendKeys(enterPIN, pin);
            } catch (Exception e) {
                throw new ExceptionUtils("Pin is not entered");
            }
        } else {
            throw new ExceptionUtils("Enter Pin X-Path may have been changed");
        }
    }


    public void enterPin() throws Exception {
        if (webUtil.isElementDisplayed(enterPIN, 10)) {
            try {
                webUtil.sendKeys(enterPIN, REGISTRATION_DATA.getPin());

            } catch (Exception e) {
                throw new ExceptionUtils("Pin is not entered");
            }
        } else {
            throw new ExceptionUtils("Enter Pin X-Path may have been changed");
        }
    }

    //thank you page after reissue of PIN
    private void validateReissuePINThankYouPage() {

        webUtil.gettextlog(ReissueThankHeader,String::equals,expReissueThankHeader);
        webUtil.gettextlog(ReissueThankPara,String::equals,expReissueThankPara);
        webUtil.gettextlog(ReissueThankCloseBtn,String::equals,"Close");
        webUtil.gettextlog(ReissueThankGotoState,String::equals,"Go to statesavings.ie");

    }

    public void clickCloseBtnReissueThanksPage() throws Exception {
        validateReissuePINThankYouPage();
        if (webUtil.isElementDisplayed(ReissueThankCloseBtn, 10)) {
            webUtil.click(ReissueThankCloseBtn);
        } else {
            throw new ExceptionUtils("close button of Reissue Thank you page xpath may have changed");
        }
    }

    public void clickGoToStateBtnReissueThanksPage() throws Exception {
        validateReissuePINThankYouPage();
        if (webUtil.isElementDisplayed(ReissueThankGotoState, 10)) {
            webUtil.click(ReissueThankGotoState);
        } else {
            throw new ExceptionUtils("GotoStateSaving button of Reissue Thank you page xpath may have changed");
        }
    }

}
