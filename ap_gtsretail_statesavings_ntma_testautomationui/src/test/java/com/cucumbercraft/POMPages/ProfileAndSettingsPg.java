package com.cucumbercraft.POMPages;

import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProfileAndSettingsPg {

    //welcome back section
    public By greetings = By.xpath("//h4[@class='m40-dashboard-intro--greeting']");
    public By changePasswordDidNotReceiveCodeSlider= By.id("sectionChangePasswordResendSecurityCode");
    public By didnotreceivecancelbtn = By.xpath("//button[@id='btnEmailChangeResendCode']/preceding-sibling::button[text()='Cancel']");
    public By changeemailsecurityclosebtn = By.xpath("//section[@id='sectionChangeEmailSecurityCode']//button[@aria-label='Close modal']");
    public  By YourPersonalDetailsHeader = By.xpath("//h3[@id='personalTitle']");
    public By changeemailclosebtn = By.xpath("//section[@id='sectionChangeEmail']//button[@class='modal_close js-closeModal' and @aria-label='Close modal']");
    public By didnotreceivecodelink = By.xpath("//a[@id='lnkOpenChangeEmailResendCode' and @onclick='ShowChangeEmailNoSecurityCode()']");
    public By PassDidNotReceive = By.xpath("(//a[@id='lnkOpenChangeEmailResendCode'])[2]");
    public By Secondheader = By.xpath("//h3[@id='personalSecondTitle']");
//   Your Personal Details section
public By BuynowDashboard = By.xpath("//a[@class='m40-dashboard-cards__link js-modalTrigger gtm-linkclick']");
    public String genericRequestUpdate = "//*[text()='%s']/../..//button";
    //   Your Full name:
    public By headerYourPersonalDetails = By.xpath("//h3[text()='Your Personal Details']");
    public By yourFullName = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_profileFullName']");
    public By YourFullName = By.xpath("//div[@id='personalYourName']");
    public By ActualName = By.xpath("//span[@id='profileFullName']");
    public By reqUpdatelink = By.xpath("//*[@regstrnData-modal='update-name-modal']");
    public By updatenamelabl = By.xpath("//h4[text()='Update your full name']");
    public By updateClosepopup = By.xpath("//section[@regstrnData-name='update-name-modal']/button");
    public By changeFullNameSlider = By.id("sectionChangeFullName");
    public By Buynow = By.id("lnkPrizeBondsForMyself");
    //  Your email Address section
    public By didnotreceiveclosebtn = By.xpath("//section[@id='sectionChangeEmailNoSecurityCode']//button[@aria-label='Close modal']");
    public By yourMailtitlelabel = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalEmail']");
    public By emailId = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_profileEmailAddress'] ");
    public By changeEmailbutton = By.xpath("//button[@data-modal='change-email-modal']");
    public By changeEmailSlider = By.id("sectionChangeEmail");
    public By EmailAddress = By.xpath("//div[@id='personalEmail']");
    public By ActualEmailAddress = By.xpath("//span[@id='profileEmailAddress']");
    public By ActualMobileNumber = By.xpath("//span[@id='profilePhoneNumber']");
    //  Change Email Address pop-up
    public By existingEmail = By.xpath("//span[@id='txtNewEmailValidator']");
    public By errorEmail = By.xpath("//span[@id='txtNewEmailValidator']");
    public By emptyEmail = By.id("errorEmptyEmail");
    public By emailPopupTitle = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmail']/h4");
    public By newEmailtxt = By.id("txtNewEmail");
    public By cancelbutton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmail']/div[3]/button");
    public By emailChangeConfirmButton = By.id("btnConfirmChangeEmail");
    public By CancelButton = By.xpath("//section[@id='sectionChangeEmail']//div[2]//button[1]");
    public By sameEmailPopup = By.xpath("//span[@id='txtNewEmailValidator']");

    //  Change Email Security Code pop-up
    public final By emailSecurityCode = By.id("sectionChangeEmailSecurityCode");
    public By EmailSectitlePopup = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmailSecurityCode']/h4");
    public By EmailSecCodelabeltitle = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmailSecurityCode']/div[2]/div/label");
    public By EmailEnterSecCodeText = By.xpath("//input[@id='txtChangeEmailSecurityCode']");
    public By EmailCancelButton = By.xpath("//button[@id='btnConfirmChangeEmailSecurityCode']/preceding-sibling::button[text()='Cancel']");
    public By EmailVerifyButton = By.xpath("//button[@id='btnConfirmChangeEmailSecurityCode']");
    public By EmailDidntgetcodebutton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_lnkOpenChangeEmailResendCode'] ");
    public By EmailCheck = By.xpath("//span[@id='profileEmailAddress' and contains(text(),'@mailinator.com')]");
    // Email Check Your inbox slider
    public By checkInboxSlider = By.id("sectionChangeEmailCheckInbox");
    //  Your Password section
    public By yourpasswordtitlelabel = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalPassword']");
    public By changePasswordbutton = By.xpath("//button[@onclick='ResetPasswordChangeModal()']");
    public final By changePasswordSlider = By.id("sectionChangePassword");

    //  Change Password Security Code pop-up
    public By pwdSectitlePopup = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangePasswordSecurityCode']/h4");
    public By pwdEnterSecCodeText = By.xpath("//input[@id='txtChangePasswordSecurityCode']");
    public By pwdCancelButton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangePasswordSecurityCode']/div[3]/button");
    public By pwdVerifyButton = By.xpath("//button[@id='btnConfirmChangePasswordSecurityCode']");
    public By YourPassword = By.xpath("//div[@id='personalPassword']");
    public By ActualYourPassword = By.xpath("(//div[contains(@class,'m43-settings__row__value')])[3]");
    //  Password Change pop-up
    public By pwdOldPwdTxt = By.id("txtOldPassword");
    public By pwdChangePwdTxt = By.id("txtNewPassword");
    public By btnCancelChangePassword = By.xpath(".//button[text()='Cancel']");
    public By btnConfirmChangePassword = By.id("btnConfirmChangePassword");
    public final By passwordSecurityCode = By.id("sectionChangePasswordSecurityCode");
    public By confirmChangePasswordSecurityCode=By.id("btnConfirmChangePasswordSecurityCode");
    public By cancelChangePasswordSecurityCode=By.xpath(".//button[text()='Cancel']");
    public By errorNewPwd = By.xpath("//span[@id='txtNewPasswordValidator']");
    public By wrongPwd = By.xpath("//span[@id='txtNewPasswordValidator']");
    public By errorOldPwd = By.xpath("//span[@id='txtOldPasswordValidator']");
    public By requiredPwd = By.xpath("//*[@id='REVPassword']");
    public By nonRegisteredEmail = By.xpath("//span[@id='EmailForgetPasswordApiError']");
    public By nullEmail = By.xpath("//span[@id='RFVEmailForgetPasswordRequired']");
    public By invalidEmail = By.xpath("//span[@id='REVEmailForgetPasswordRegex']");
    //    Success Pop-up
    public By bannerMessage = By.xpath("//strong[@id='alertMessage']");
    public By Address = By.xpath("//div[@id='personalAddress']");
    public By ActualAddress = By.xpath("//div[@class='m43-settings__row__value']/span[@id='profileAddress']");
    public By SSCN = By.xpath("//div[@id='personalSSCN']");
    public By ActualSSCN = By.xpath("//span[@id='profileSSCN']");
    public By BankDetails = By.xpath("//div[@id='personalBankDetails']");
    public By ActualBankDetails = By.xpath("//span[@id='profileBankDetails']");
    //Update Mobile number section
    public By changMobileNumberSlider = By.id("sectionChangeMobileNumber");
    public By mobnumlabel = By.xpath("//div[@id='personalMobileNumber']");
    public By requstbtn = By.xpath("//*[@regstrnData-modal=\"update-mobile-modal\"]");
    public By updatemblnumtitle = By.xpath("//h4[text()='Update your mobile number']");
    public By closbutn = By.xpath("//section[@id='sectionChangeMobileNumber']//button[text()='Close']");

    //Your address section
    public By changAddressSlider = By.id("sectionChangeAddress");
    public By youraddtitle = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalAddress']");
    public By requestbutn = By.xpath("//*[@regstrnData-modal='update-address-modal']");
    public By updateadstitle = By.xpath("//h4[text()='Update your address']");
    public By closebtn = By.xpath("//section[@id='sectionChangeAddress']//button[text()='Close']");

    //Your account details section
    public By showSCCNSlider = By.id("sectionDisplaySSCN");
    public By accntdetaillble = By.xpath("//h3[text()='Your Account Details']");
    public By SSCNtext = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalSSCN']");
    public By sscn = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalSSCN']/following-sibling::div/span");
    public By showsscnbtn = By.xpath("//button[text()='Show your SSCN code']");
    public By yourSSNlabl = By.xpath("//h4[text()='Your SSCN Code']");
    public By dwnldbtn = By.xpath("//button[@id='btnDownloadSSCN']");
    public By cancelbtn = By.xpath("//div[@class='small-12 medium-8 medium-pull-4 columns']//button[text()='Cancel']");

    //Your Bank Details section
    public By addIBANSlider=By.id("sectionConfirmChangeIban");
    public By btnAddNow = By.id("btnChangeIban");
    public String ExpName ="HYMIE LEUPOLDT";
    public String ExpMail= "UAT-Test-User-54@mailinator.com";
    public String ExpPassword = "********";
    public String ExpMobileNumber = "XXX XXX9916";
    public String ExpeAddress = "40 BARTELT JUNCTION\n" +
            "\n" +
            "BIRR\n" +
            "LAOIS";
    public String ExpSSCN ="242597416";
    public String ExpBankDetails = "**************5678";

    WebDriver driver;
    WebDriverUtil webUtil;

    public ProfileAndSettingsPg(WebDriver driver) {
        this.driver = driver;
        this.webUtil=new WebDriverUtil(driver);
    }

    public SliderContent clickChangePassword()
    {
        webUtil.click(changePasswordbutton);
        return new SliderContent(driver);
    }

    public void ClickEmailSecurityCodeCancelButton() {
        webUtil.click(EmailCancelButton);
    }

    public void ClickDidNotReceiveCloseButton()
    {
        webUtil.click(didnotreceiveclosebtn);
    }

    public void ClickDidNotReceiveCancelButton()
    {
        webUtil.click(didnotreceivecancelbtn);
    }


    public SliderContent clickChangeEmail()
    {
        webUtil.click(changeEmailbutton);
        return new SliderContent(driver);
    }

    public void ClickDidNotReceiveLink() {

        webUtil.click(didnotreceivecodelink);

    }

    public void ClickEmailCloseButton() {
        webUtil.click(changeemailclosebtn);
    }

    public void ClickEmailSecurityCodeCloseButton() {
        webUtil.click(changeemailsecurityclosebtn);
    }

    /**
     * Clicks the "Change Password" button to initiate the password change process.
     */
    public void clickConfirmChangePassword()
    {
        webUtil.click(btnConfirmChangePassword);
    }

    /**
     * Clicks the "Cancel" button to close the password change slider.
     */
    public void clickCancelChangePassword()
    {
        webUtil.click(btnCancelChangePassword);
    }

    /**
     * Clicks the "Cancel" button to close the email change slider.
     */
    public void clickCancelChangeEmail()
    {
        webUtil.click(cancelbutton);
    }

    /**
     * Clicks the "Confirm" button to initiate the email change process.
     */
    public void clickConfirmChangeEmail()
    {
        webUtil.click(emailChangeConfirmButton);
    }

    /**
     * Clicks the Request and update button to according to section name.
     */
    public void ClickDidNotReceivePassword() {
        webUtil.click(PassDidNotReceive);

    }


    public SliderContent clickRequestUpdate(String sectionName)
    {
        By btnXpath;
        switch (sectionName) {
            case "Your Full Name":
            case "Your Mobile Number":
            case "Your Address":
                btnXpath = By.xpath(String.format(genericRequestUpdate, sectionName));
                webUtil.click(btnXpath);
                break;
            default:
                throw new IllegalArgumentException("Invalid section name: " + sectionName);
        }
        return new SliderContent(driver);
    }


    public SliderContent clickShowSSCNCode() {
        webUtil.click(showsscnbtn);
        return new SliderContent(driver);
    }

    public void clickAddNowButton() {
        webUtil.click(btnAddNow);
    }


}
