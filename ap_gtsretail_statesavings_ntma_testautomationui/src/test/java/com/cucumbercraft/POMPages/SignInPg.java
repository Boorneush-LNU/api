package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.BuyNow.YourDetail;
import com.cucumbercraft.POMPages.BuyNow.YourOrder;
import com.cucumbercraft.entity.UserDetails;
import com.cucumbercraft.framework.*;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

@Log4j2
public class SignInPg {

    public static UserDetails userDetails;
    public final By notificationBox = By.xpath("//p[@class='notification-box__text']");
    public By codenotreceivedOnOTP = By.id("sectionResendCodeVerify");
    public By BackToSS = By.id("backLinkWithinLoginBlock");
    public final String url = Settings.getInstance().getProperty("OTPEndPoint");
    private final By mailSubject = By.xpath("//table[@class='table-striped jambo_table']/tbody/tr[1]/td[3]");
    private final By mailTime = By.xpath("//table[@class='table-striped jambo_table']//tr[1]/td[4][normalize-space()='just now']");
    private final By chngPwdMail = By.xpath("//a[normalize-space()='Change your password']");
    private final By chngEmailMail = By.xpath("//p[text()='(The request will expire in 20 minutes) ']//preceding::a");
    private final By msgBody = By.xpath("//iframe[@id='html_msg_body']");
    private final By newPwdtxtbx = By.xpath("//input[@id='txtNewPassword']");
    private final By nxtChngPwd = By.xpath("//div[@class='cta-container']//input[@value='Next']");
    private final By signinError = By.xpath("//div[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M34Banner_notificationBox']/p");
    private final By secrtyCodeErrMsg = By.xpath("//span[contains(@id,'SecurityCodeErrorMessage')]");
    private final By signOutButton = By.xpath("//a[text()='Sign Out']");
    private final By emailError = By.id("RFVEmailLogin");
    private final By passwordError = By.id("RFVPasswordLogin");
    private final By invalidEmailError = By.id("REVPasswordRange");
    private final By genericError = By.cssSelector(".error-validation");
    public By userName = By.xpath("//input[@id='emailLoginAccount']");
    public By passwd = By.xpath("//input[@id='passwordLoginAccount']");
    public By signInBtn = By.id("login-form-submit");
    public By val1 = By.xpath("//h4[text()='Enter your details:']");
    public By email = By.xpath("//input[contains(@id,'emailAddressForgetPassword')]");
    public By nxtBttn = By.xpath("//button[normalize-space()='Next']");
    public By otpTextBox = By.id("txtCheckResetPasswordOtp");
    public By reqResetLink = By.xpath("//button[normalize-space()='Request reset link']");
    public By emailVerify = By.xpath("//h2[text()='Email address verified!']");
    public By confirmPassword = By.xpath("//p[@class='notification-box__text']");
    public final By paraText = By.xpath("//div[@class='m03-title_text_button--content']");
    public By resetPasswordSlider = By.id("sectionResetPassword");
    public By checkEmailSlider = By.id("sectionEmailPassword");
    public By Verificationcode = By.xpath("//p[@id='verification-modal-description']");
    public By emailnotreceived = By.id("sectionResendEmailPassword");
//    public String ExpPara = "Enter the verification code we’ve sent to the registered mobile phone number ending with ~9011";
    public String  ExpPara = "Enter the verification code we’ve sent to the registered mobile phone number";
    WebDriver driver;
    HomePage homepage;
    WebDriverUtil webUtil;
    APIReusuableLibrary apiDriver = new APIReusuableLibrary();
    PropertyConfig config = ConfigCache.getOrCreate(PropertyConfig.class);
    Security_Page securitycodepg;
    ProfileAndSettingsPg pfPg;
    Properties prop = Settings.getInstance();
    private String OTPresponse;
    private By fldUsername;
    private By fldPassword;
    private By btnSignIn;
    private By hdr;



    public SignInPg(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        homepage = new HomePage(driver);
        securitycodepg = new Security_Page(driver);
        pfPg = new ProfileAndSettingsPg(driver);

    }

    public void LightLogin(String username, String password, String number) throws Exception {
//        userDetails = UserDetails.getUserDetails(username); // Shankar ==> Could you please check the usage for this reference and if this is required ?

        ExtentCucumberAdapter.addTestStepLog("State Savings home page is displayed ");
        webUtil.click(homepage.signIn);
        ExtentCucumberAdapter.addTestStepLog("Sign In Page");

        if (webUtil.getText(val1).equalsIgnoreCase("Enter your details:")) {
            webUtil.sendKeys(userName, username);
            webUtil.sendKeys(passwd, password);


        } else {
            log.info("Sign in Page not displayed");
            ExtentCucumberAdapter.addTestStepLog("Username and password fields not displayed ");
            Assert.fail();
        }
        webUtil.click(signInBtn);


        if (webUtil.isElementDisplayed(signinError, 10)) {
            String msg = webUtil.getText(signinError);
            ExtentCucumberAdapter.addTestStepLog(msg);
            throw new ExceptionUtils(msg);
        }

        OTPresponse = apiDriver.getOTP(url, 200, number);
        System.out.println(OTPresponse);
        ExtentCucumberAdapter.addTestStepLog("OTP is received and captured");

        if (webUtil.getText(securitycodepg.val2).contains("Verification Code")) {
            webUtil.sendKeys(securitycodepg.otp, OTPresponse);
            System.out.println("Displayed " + driver.findElement(securitycodepg.confirmbtn).isDisplayed());
            System.out.println("Enabled " + driver.findElement(securitycodepg.confirmbtn).isEnabled());

            webUtil.javascriptClick(securitycodepg.confirmbtn);
//            webUtil.click(securitycodepg.confirmbtn);

            if (webUtil.isElementDisplayed(secrtyCodeErrMsg, 5)) {
                ExtentCucumberAdapter.addTestStepLog("OTP expired fetching once again");
                webUtil.waitFor(10000);
                OTPresponse = apiDriver.getOTP(url, 200, number);
                ExtentCucumberAdapter.addTestStepLog("OTP is received and captured");
                if (webUtil.getText(securitycodepg.val2).contains("Code")) {
                    webUtil.sendKeys(securitycodepg.otp, OTPresponse);
                    if (webUtil.isElementDisplayed(securitycodepg.confirmbtn, 15)) {
                        webUtil.click(securitycodepg.confirmbtn);
                    }
                } else {
                    log.info("U");
                    Assert.fail();
                }
            }
            if (webUtil.isElementDisplayed(pfPg.greetings, 15)) {
                System.out.println(webUtil.getText(pfPg.greetings));
                log.info("Login Successful");
                ExtentCucumberAdapter.addTestStepLog("Login Successful");
            } else {
                log.info("Login failed");
                if (driver.findElement(By.xpath("//*[contains(text(),'504')]")).getText().contains("504"))
                    driver.navigate().refresh();
            }

        } else
            throw new ExceptionUtils("username or pwd is wrong");

    }

    public void EnterCredentials(String username, String password) {
        typeInUsername(username)
                .typeInPassword(password)
                .clickBtnSignIn();
    }

    public void clickForgotPasswordDidNotReceiveLink() {
        webUtil.click(homepage.forgotPwddidnotreceivelink);

    }

    public void clickCheckEmailCodeNotReceivedLink() {
        webUtil.click(homepage.checkemaildidnotreceivelink);

    }

    public void enterUsernameAndIncorrectPassword(String username, String password) {

        while (!webUtil.isElementVisible(homepage.lockedUserError,10)){

            typeInUsername(username)
                    .typeInPassword(password)
                    .clickBtnSignIn();

        }
    }






    public void FirstTimeLogin(String username, String password, String number) throws Exception {
        OTPresponse = apiDriver.getOTP(url, 200, number);
        typeInUsername(username)
                .typeInPassword(password)
                .clickBtnSignIn()
                .typeInOTP(number)
                .clickBtnVerifyOTP(number);

    }

    public void clickotpPgeCodeNotReceivedLink() {
        webUtil.waitForPageLoaded();
        webUtil.click(homepage.otpPgedidnotreceivelink);


    }


    public void signOut() throws Exception {
        if (webUtil.isElementclickable(signOutButton, 5)) {
            webUtil.click(signOutButton);
            webUtil.waitForPageLoaded();
        }

    }


    @SneakyThrows
    public void validatePswdRequirementaremetAndButtonDisabled()  {
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println(driver.getWindowHandles().size());
        wait.until(ExpectedConditions.numberOfWindowsToBe(3)); // Change to
        webUtil.skip_switchToNewWindow(2);
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("reset-password"));
        Thread.sleep(3000);
        WebElement nxtBtn= driver.findElement(By.xpath("//div[@class='cta-container']//input[@value='Next']"));

        //Uppercase

        webUtil.sendKeys(newPwdtxtbx, "D");

        Assert.assertFalse(nxtBtn.isEnabled(),"Button should be disabled");
        WebDriverWait upperWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By upperCase = By.xpath("//li[@id='req-upper']//span");
        upperWait.until(ExpectedConditions.attributeContains(upperCase,"class","valid"));
        WebElement upperCaseReq = driver.findElement(upperCase);
        Assert.assertTrue(upperCaseReq.getAttribute("class").contains("val valid"),"UpperCase requirement not met");


        //Lowercase

        webUtil.sendKeys(newPwdtxtbx, "Du");

        Assert.assertFalse(nxtBtn.isEnabled(),"Button should be disabled");
        WebElement lowerCaseReq= driver.findElement(By.xpath("//li[@id='req-lower']//span"));
        upperWait.until(ExpectedConditions.attributeContains(lowerCaseReq,"class","valid"));
        Assert.assertTrue(upperCaseReq.getAttribute("class").contains("val valid"),"LowerCase requirement not met");

        //Special character

        webUtil.sendKeys(newPwdtxtbx, "Dummy!");

        Assert.assertFalse(nxtBtn.isEnabled(),"Button should be disabled");
        WebElement splCharReq= driver.findElement(By.xpath("//li[@id='req-specialchar']//span"));
        upperWait.until(ExpectedConditions.attributeContains(splCharReq,"class","val valid"));
        Assert.assertTrue(splCharReq.getAttribute("class").contains("val valid"),"special character requirement not met");

        //Numbers

        webUtil.sendKeys(newPwdtxtbx, "Dummy!202");

        Assert.assertFalse(nxtBtn.isEnabled(),"Button should be disabled");
        WebElement numbersReq= driver.findElement(By.xpath("//li[@id='req-digit']//span"));
        upperWait.until(ExpectedConditions.attributeContains(numbersReq,"class","val valid"));
        Assert.assertTrue(numbersReq.getAttribute("class").contains("val valid"),"Numbers requirement not met");


        //10digits

        webUtil.sendKeys(newPwdtxtbx, "Dummy!2025");

        Assert.assertTrue(nxtBtn.isEnabled(),"Button should be Enabled");
        WebElement tenDigitsReq= driver.findElement(By.xpath("//li[@id='req-length']//span"));
        upperWait.until(ExpectedConditions.attributeContains(tenDigitsReq,"class","val valid"));
        Assert.assertTrue(tenDigitsReq.getAttribute("class").contains("val valid"),"10 Digits requirement not met");

    }



    public void forgotPwdErrorValidation(String mail,TestData data){
        Faker faker = new Faker();
        String expectedError = data.getErrormessages().get(0);

        switch (mail){
            case "FWP-Blank Email":
                webUtil.click(nxtBttn);
                webUtil.gettextlog(genericError, String::equals, expectedError);
                ExtentCucumberAdapter.addTestStepLog("Blank email error message is displayed");
                break;
            case "FWP-Invalid Email":
                webUtil.sendKeys(email, faker.name().username());
                webUtil.click(nxtBttn);
                webUtil.gettextlog(By.id("REVEmailForgetPasswordRegex"), String::equals, expectedError);
                ExtentCucumberAdapter.addTestStepLog("Invalid email error message is displayed");
                break;
            case "FWP-Pending Registration":
                webUtil.sendKeys(email, data.getUsername());
                webUtil.click(nxtBttn);
                expectedError = String.join(",", data.getErrormessages().subList(0, 3));
                webUtil.gettextlog(By.id("EmailForgetPasswordApiError"), String::equals, expectedError);
                ExtentCucumberAdapter.addTestStepLog("Pending registration error message is displayed");
                break;
            case "FWP-Locked User":
                webUtil.waitUntilElementVisible(resetPasswordSlider,10).findElement(SliderContent.CLOSE_SLIDER).click();
                for (int i = 0; i < 3; i++) {
                    typeInUsername("Perf-Test-User-10135@mailinator.com")
                            .typeInPassword(data.getPassword())
                            .clickBtnSignIn();
                }
                homepage.clickSignIn();
                clickForgotPassword();
                webUtil.sendKeys(email, data.getUsername());
                webUtil.click(nxtBttn);
                webUtil.gettextlog(genericError, String::equals, expectedError);
                ExtentCucumberAdapter.addTestStepLog("Locked user error message is displayed");
                break;
            default:
                System.err.println("EmailType not found: " + mail);
        }




    }

    public void enterOtp(String number) throws Exception {
        String OTPresponse = apiDriver.getOTP(url, 200, number);
        webUtil.sendKeys(otpTextBox, OTPresponse);
        webUtil.click(reqResetLink);

    }

    @SneakyThrows
    public void openMailinator(String email) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");
        String window = webUtil.getDriver().getWindowHandles().stream().skip(1).findFirst().orElseThrow(() -> new ExceptionUtils("window not found"));
        webUtil.getDriver().switchTo().window(window);
        Thread.sleep(3000); // Added Shan
        webUtil.getDriver().get(String.format(prop.getProperty("mailinator"), email.replaceAll("@mailinator.com", "")));

    }

    public void getLinkMailinator() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mailTime));
        boolean clickFirst = webUtil.getText(mailTime).trim().equalsIgnoreCase("just now");
        if (webUtil.getText(mailSubject).equalsIgnoreCase("Reset your Ireland State Savings Online Password") && clickFirst) {
            webUtil.click(mailSubject);
        } else {
            System.out.println("Email not received");
            throw new ExceptionUtils("Email not received");
        }
        WebElement element = webUtil.isElementDisplayed(msgBody, 10) ? webUtil.getDriver().findElement(msgBody) : null;
        webUtil.getDriver().switchTo().frame(element);
        webUtil.click(chngPwdMail);
    }




    public void PerformanceMail() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mailTime));
        boolean clickFirst = webUtil.getText(mailTime).trim().equalsIgnoreCase("just now");
        if (webUtil.getText(mailSubject).equalsIgnoreCase("Reset your Ireland State Savings Online Password") && clickFirst) {
            webUtil.click(mailSubject);
        } else {
            System.out.println("Email not received");
            throw new ExceptionUtils("Email not received");
        }
        WebElement element = webUtil.isElementDisplayed(msgBody, 10) ? webUtil.getDriver().findElement(msgBody) : null;
        webUtil.getDriver().switchTo().frame(element);
//        webUtil.click(chngPwdMail);
        String HTPTab = webUtil.getText(By.xpath("//kbd[contains(text(), 'https://statesavingsalt-qa.dev-anpost.com/your-savings/register/create-password')]/text()"));
        System.out.println(HTPTab);

    }










    public Map<String, String> captureLinksForPerformance(List<String> emailAddresses) {

        Map<String, String> emailLinkMap = new HashMap<>();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        String expectedSubject = "Reset your Ireland State Savings Online Password";

        // Loop Mail
        for (String email : emailAddresses) {
            String verificationLink = "Link capture failed";

            try {
                webUtil.getDriver().get("https://www.mailinator.com/v4/public/inbox.jsp?query=" + email.split("@")[0]);

                wait.until(ExpectedConditions.visibilityOfElementLocated(mailTime));

                boolean clickFirst = webUtil.getText(mailTime).trim().equalsIgnoreCase("just now");

                if (webUtil.getText(mailSubject).equalsIgnoreCase(expectedSubject) && clickFirst) {
                    webUtil.click(mailSubject);
                } else {
                    System.err.println("Email not received or subject/time mismatch for: " + email);
                    emailLinkMap.put(email, verificationLink); // Store failure status
                    continue;
                }

                WebElement element = webUtil.isElementDisplayed(msgBody, 10)
                        ? webUtil.getDriver().findElement(msgBody)
                        : null;

                if (element != null) {
                    webUtil.getDriver().switchTo().frame(element);

                    verificationLink = webUtil.getText(
                            By.xpath("//kbd[contains(text(), 'https://statesavingsalt-qa.dev-anpost.com/your-savings/register/create-password')]/text()")
                    ).trim();

                    webUtil.getDriver().switchTo().defaultContent();

                } else {
                    System.err.println("Email body iframe not found for: " + email);
                }

            } catch (Exception e) {
                System.err.println("Exception while processing " + email + ": " + e.getMessage());
            }

            emailLinkMap.put(email, verificationLink);
            System.out.println("Result for " + email + ": " + verificationLink);
        }

        return emailLinkMap;
    }












    public void getLinkMailinator1() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mailTime));
        boolean clickFirst = webUtil.getText(mailTime).trim().equalsIgnoreCase("just now");
        if (webUtil.getText(mailSubject).equalsIgnoreCase("Request to change your email address for Ireland State Savings Online") && clickFirst) {
            webUtil.click(mailSubject);
        } else {
            System.out.println("Email not received");
            throw new ExceptionUtils("Email not received");

        }
        WebElement element = webUtil.isElementDisplayed(msgBody, 10) ? webUtil.getDriver().findElement(msgBody) : null;
        webUtil.getDriver().switchTo().frame(element);
        webUtil.isElementDisplayed(chngEmailMail, 20);
        webUtil.click(chngEmailMail);

    }


    public void launchUrl(String link) {

        driver.get(link);
    }

    public void newPwdTab3()  {
        // webUtil.getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(3));
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println(driver.getWindowHandles().size());
        wait.until(ExpectedConditions.numberOfWindowsToBe(3)); // Change to
        webUtil.skip_switchToNewWindow(2);
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("reset-password"));

        Faker faker = new Faker();
//        String newPassword="Dummy!7755";
         String newPassword =faker.regexify("[A-Z]{1}[a-z]{1}[0-9]{1}[a-zA-Z0-9~!@#$%^&*]{6,}");
        System.out.println(newPassword);

        webUtil.sendKeys(newPwdtxtbx, newPassword);
        webUtil.click(nxtChngPwd);

    }


    @SneakyThrows
    public void newPwd()  {
       // webUtil.getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(3));
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(35));
        System.out.println(driver.getWindowHandles().size());
        wait.until(ExpectedConditions.numberOfWindowsToBe(3)); // Change to
        webUtil.skip_switchToNewWindow(2);
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("reset-password"));
        Thread.sleep(5000);
        Faker faker = new Faker();
//        String newPassword="Dummy!7755";
        String newPassword =faker.regexify("[A-Z]{1}[a-z]{1}[0-9]{1}[a-zA-Z0-9~!@#$%^&*]{7,}");
        System.out.println(newPassword);

        webUtil.sendKeys(newPwdtxtbx, newPassword);
        webUtil.click(nxtChngPwd);

    }

    public void enternewpassword(){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println(driver.getWindowHandles().size());
        wait.until(ExpectedConditions.numberOfWindowsToBe(5));
        webUtil.skip_switchToNewWindow(4);
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("reset-password"));

        Faker faker = new Faker();
        //String newPassword="Dummy!9988";
          String newPassword =faker.regexify("[A-Z]{1}[a-z]{1}[0-9]{1}[a-zA-Z0-9~!@#$%^&*]{6,}");
        System.out.println(newPassword);
        webUtil.sendKeys(newPwdtxtbx, newPassword);
    }
    public void clickNextButton(){
        webUtil.click(nxtChngPwd);
    }

    public void oldPwd(String password){
        webUtil.skip_switchToNewWindow(2);
        webUtil.sendKeys(newPwdtxtbx, password);
        webUtil.click(nxtChngPwd);
    }

    public void validate_Sign_in_Block() {
        webUtil.gettextlog(val1, String::equals, "Enter your details:");
        webUtil.isElementDisplayedLog(userName, 10, "Email input field");
        webUtil.isElementDisplayedLog(passwd, 10, "Password input field");
        webUtil.isElementDisplayedLog(signInBtn, 10, "Sign in button");
    }

    public SignInPg typeInUsername(String username) {
        webUtil.sendKeys(userName, username);
        return this;
    }

    public SignInPg typeInPassword(String password) {
        webUtil.sendKeys(passwd, password);
        return this;
    }
//
//    public SignInPg typeInPasswordC(String password) {
//        webUtil.sendKeys(pfPg.pwdOldPwdTxt, password);
//        return this;
//    }


    public SignInPg clickBtnSignIn() {
        webUtil.click(signInBtn);
        return this;
    }

    public SignInPg typeInOTP(String number) throws Exception {
        OTPresponse = apiDriver.getOTP(url, 200, number);
        webUtil.sendKeys(securitycodepg.otp, OTPresponse);

        return this;
    }

    public SignInPg Back(){
        return this;
    }




    public StateSavingsDashboardPage clickBtnVerifyOTP(String number) throws Exception {

        webUtil.javascriptClick(securitycodepg.confirmbtn);

        if (!driver.findElements(secrtyCodeErrMsg).isEmpty()) {
            enterOtp(number);
        }
        return new StateSavingsDashboardPage(driver);
    }

    public YourDetail clickBtnVerify(String number) throws Exception {

        webUtil.javascriptClick(securitycodepg.confirmbtn);
        if (driver.findElements(secrtyCodeErrMsg).size() > 0) {
            enterOtp(number);
        }
        return new YourDetail(driver);
    }

    public YourDetail clickBtnVerifyIrish(String number) throws Exception {
        webUtil.javascriptClick(securitycodepg.confirmbtn);
        if (driver.findElements(secrtyCodeErrMsg).size() > 0) {
            enterOtp(number);
//            log.info("ss");
//            webUtil.click(BackToSS);
//            log.info("sd");
//            Thread.sleep(5000);
        }
        return new YourDetail(driver);
    }


    public SignInPg validatErrorMsg(String msg) {
        webUtil.waitFor(4000);
        webUtil.gettextlog(notificationBox, String::equals, msg, "Error Message");
        return this;
    }

    public void feildErrorMessage(String scenario, TestData data) {
        String username = data.getUsername();
        String password = data.getPassword();
        switch (scenario) {
            case "Blank Username Pwd":
                typeInUsername(username).typeInPassword(password).clickBtnSignIn();
                webUtil.gettextlog(emailError, String::equals, data.getErrormessages().get(0));
                webUtil.gettextlog(passwordError, String::equals, data.getErrormessages().get(1));
                break;
            case "Invalid Username Pwd":
            case "Valid Username Invalid Pwd":
                typeInUsername(username).typeInPassword(password).clickBtnSignIn();
                webUtil.gettextlog(invalidEmailError, String::equals, data.getErrormessages().get(0));
                break;
            case "Invalid Username Valid Pwd":

            case "Pending Registration":
                typeInUsername(username).typeInPassword(password).clickBtnSignIn();
                webUtil.gettextlog(notificationBox, String::equals, data.getErrormessages().get(0));
                break;
            case "Multiple Failed Logins":
                for (int i = 0; i < 3; i++) {
                    typeInUsername(username);
                    typeInPassword(password);
                    clickBtnSignIn();
                }
                WebElement element = driver.findElement(By.cssSelector(".locked-account.brand-white"));
                var actualHdr=element.findElement(By.tagName("h1")).getText();
                var actualPara = element.findElement(By.tagName("p")).getText();
                var actualPara2=element.findElement(By.tagName("strong")).getText();
                webUtil.CompareString(actualHdr,String::equals,data.getErrormessages().get(0));
                webUtil.CompareString(actualPara,String::equals,data.getErrormessages().get(1));
                webUtil.CompareString(actualPara2,String::equals,data.getErrormessages().get(2));
                break;
            default:
                System.out.println("No error found on login page");
        }
    }


    public YourOrder loginTelesales() {
        fldUsername = By.id("username");
        fldPassword = By.id("password");
        btnSignIn = By.xpath("//input[@value='Sign in']");
        hdr = By.xpath("//h2[contains(text(),'Telesales')]");

        driver.get(FrameworkConstants.envLinkFetch() + "telesales");
        homepage.userpermission();
        webUtil.gettextlog(hdr, String::equals, "Telesales Login");
        webUtil.sendKeys(fldUsername, "saahil.sunilgulve@anpost.ie");
        webUtil.sendKeys(fldPassword, "fY_<cFgR-dV;");
        webUtil.clickLog(btnSignIn, "SignIn on telesales page");
        return new YourOrder(driver);
    }

    public void clickForgotPassword() {
        webUtil.click(homepage.forgotPwd);

    }

    public void enterResetPasswordEmail(String username) {
       WebElement element = webUtil.waitUntilElementVisible(resetPasswordSlider,10);
       element.findElement(SliderContent.TEXT_FIELD).sendKeys(username);
    }



}


