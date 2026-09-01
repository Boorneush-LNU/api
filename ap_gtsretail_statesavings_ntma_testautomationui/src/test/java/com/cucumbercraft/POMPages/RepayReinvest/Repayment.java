package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Security_Page;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.PortalLoginSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;


public class Repayment {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final Holdings holdings;
    public Security_Page sp;
    static final Logger log = LogManager.getLogger(Repayment.class);
    private final PortalLoginSteps portal = new PortalLoginSteps();
    private final APIReusuableLibrary api = new APIReusuableLibrary();

    public Repayment(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
        holdings = new Holdings(driver);
    }

    public int matureAmount;
    public static double avilableAmount;
    public By blankAmountError = By.id("cashInRequiredAmountError");
    public By invalidAmountError = By.id("cashInAmountError");
    public By verifyCashInPage = By.xpath("//div[@class='product-options-info__heading']/h2");
    public By verifyPartialNumb = By.xpath("//p[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_para_Title']");
    public By cashInEnterAmount = By.xpath("//input[@id='txtCashAmount']");
    public By fullAmount = By.xpath("//button[@class='button button--secondary button--alt js-allocate-full-amount']");
    public By cnfrmButton = By.xpath("//input[@id='btnSubmitAmount']");
    public By cashinConfirmbtn = By.id("submitButton");
    public By ReinvestTextbox = By.xpath("//input[@name='amount-input']");
    private final By cancelButton = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btn_ConfirmNew']//preceding-sibling::button");
    public By goBackBtn = By.xpath("//a[@class='gtm-cta button button--secondary  js-closeModal' and contains(text(),'Go back')]");

    public By cnfrmBtnModal = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lbCashInAllocateFull");
    public By cancelBtnModal = By.xpath("//button[@class='gtm-cta button button--primary  js-confirm-allocate-all']/following-sibling::a");
    public By matureAmountXpath = By.xpath("//span[@class='footer__ec-total-amount js-available-funds']");
    public By editYourAmount = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lbEditOrder']");
    public By cancelBtn = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']");

    public By cnfrmTransactionBtn = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']/following-sibling::input");
    public By confirmTransactionReview = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_btnConfirmReview']");

    public By checkboxTermCondition = By.xpath("//label[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lblTermCondition']");

    static public String ibanNumber;

    private final By commonallocateFullAmount = By.xpath("//button[contains(@class,\"allocate-full-amount\")]");

    //Security page when iban adding jouney
    By header = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/h4");
    By Securityheader = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/h4");
    By para = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/p");
    String paraContent = "Enter the verification code we've sent to the registered mobile phone number ending with ~";
    By label = By.xpath("//label[@for='txtOneTimeSecurityCodePin']");
    By enterOTPField = By.xpath("//*[@id='securityCodeText']");
    By didnotreciveLink = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_lnkResetPinCode']");
    By confirmBtn = By.id("btnVerifyCode");
    By cancelBtn1 = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_btnVerifySecurityCodePin']/preceding-sibling::button");
    By thankYouMessage = By.xpath("//strong[contains(text(),'Thank you')]");
    By addIbanBtn = By.xpath("//button[@id='btnCashInAddBankDetail']");


    /**
     * verify the content on cashin page
     *
     * @throws Exception
     */
    public void verifyCashPage() throws Exception {
        if (webUtil.isElementDisplayed(verifyCashInPage, 20)) {
            if (webUtil.getText(verifyCashInPage).equals("Cash In")) {
//                if (webUtil.isElementDisplayed(IBAN, 20)) {
//                 String  iban= getdata.get("IBAN_Number");
//                    String lstFourDigit=iban.substring()
//                    String actual = webUtil.getText(By.xpath("//p[contains(@id,'Repay_ReInvest_Detail_para')]")).replaceAll("\\n"," ");
//                    String expected = "All repayments of a State Savings product are subject to Terms and Conditions, requiring 7 business days’ notice. In the case of a maturing account the repayment will not be verified before the maturity date.  When your repayment has been verified you will be issued with a Payment Advice Notice by email. The repayment value will be transferred to the bank account ending x5678 within 5 days of you receiving the Payment Advice Notice.";
//                    Assertions.assertThat(actual).contains(expected);
//                    ibanNumber = webUtil.getText(IBAN).replaceAll("[^0-9]", "");
//                }
            }
        }
    }

    /**
     * function used for verifying number on
     *
     * @param number
     * @throws Exception
     */
    public void verifyNumber(String number) throws Exception {
        if (webUtil.isElementDisplayed(verifyPartialNumb, 20)) {
            if (!webUtil.getText(verifyPartialNumb).contains(number.substring(number.length() - 4))) {
                System.out.println("Phone number same nahi hai...");
            }
        }
    }

    /**
     * function used for entering amount in the cashin page
     *
     * @throws InterruptedException
     */
    public void enterAmount() throws InterruptedException {
        if (webUtil.isElementDisplayed(cashInEnterAmount, 20)) {
            webUtil.scrollToView(cashInEnterAmount);
            if (data.getReinvestAmounts().get(0).equalsIgnoreCase("Blank")) {
                driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(cashInEnterAmount).sendKeys(data.getReinvestAmounts().get(0), Keys.TAB);
            }
        }
    }





    // Shan. 29.7.25. Takes the 2nd value from the cell.
    public void ReEnterAmount() throws InterruptedException {
        if (webUtil.isElementDisplayed(cashInEnterAmount, 20)) {
            webUtil.scrollToView(cashInEnterAmount);
            webUtil.clear(cashInEnterAmount);
            if (data.getReinvestAmounts().get(1).equalsIgnoreCase("Blank")) {
                driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(cashInEnterAmount).sendKeys(data.getReinvestAmounts().get(1), Keys.TAB);
            }
        }

    }


    public void ReInvestAmount() throws InterruptedException {
        if (webUtil.isElementDisplayed(ReinvestTextbox, 20)) {
            webUtil.scrollToView(ReinvestTextbox);
            webUtil.clear(ReinvestTextbox);
            if (data.getReinvestAmounts().get(1).equalsIgnoreCase("Blank")) {
                driver.findElement(ReinvestTextbox).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(ReinvestTextbox).sendKeys(data.getReinvestAmounts().get(1), Keys.TAB);
            }
        }

    }

    public void ClearReInvestAmount() throws InterruptedException {
        if (webUtil.isElementDisplayed(ReinvestTextbox, 20)) {
            webUtil.scrollToView(ReinvestTextbox);
            webUtil.clear(ReinvestTextbox);
            if (data.getReinvestAmounts().get(3).equalsIgnoreCase("Blank")) {
                driver.findElement(ReinvestTextbox).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(ReinvestTextbox).sendKeys(data.getReinvestAmounts().get(3), Keys.TAB);
            }
        }
        }



        public void CashInAmount() throws InterruptedException {
        if (webUtil.isElementDisplayed(cashInEnterAmount, 20)) {
            webUtil.scrollToView(cashInEnterAmount);
            webUtil.clear(cashInEnterAmount);
            if (data.getReinvestAmounts().get(2).equalsIgnoreCase("Blank")) {
                driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(cashInEnterAmount).sendKeys(data.getReinvestAmounts().get(2), Keys.TAB);
            }
        }

    }


    public void clickAddIBAN() throws Exception {

            webUtil.click(addIbanBtn);

    }
    /**
     * function used for entering amount in the cashin page
     *
     * @throws InterruptedException
     */
    public void enterAmount(String amount) throws InterruptedException {
        if (webUtil.isElementDisplayed(cashInEnterAmount, 20)) {
            webUtil.scrollToView(cashInEnterAmount);
            if (data.getReinvestAmounts().get(0).equalsIgnoreCase("Blank")) {
                driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(cashInEnterAmount).sendKeys(amount, Keys.TAB);
            }
        }
    }

    public void pbVerify() throws Exception {
        String iban = api.getUserDetails(data.getUsername()).get("bankAccountIban").replaceAll("[^0-9]", "");
        String pbParagraph2 = "//section[@class='dashboard-modal__content js-modal active']//p[%d]";
        webUtil.waitUntilElementVisible(By.xpath(String.format(pbParagraph2, 1)), 20);
        if (webUtil.getText(By.xpath(String.format(pbParagraph2, 1))).contentEquals("You have selected to allocate all available funds to Prize Bonds.")) {
            throw new ExceptionUtils("Prize bond paragraph 1 content not verified");
        }
        if (!webUtil.getText(By.xpath(String.format(pbParagraph2, 1))).contentEquals("Each prize bond has a value of €6.25 so the amount will be rounded down to the nearest multiple of €6.25. Any remaining " +
                "funds will be transferred to your bank account ending ~".concat(iban))) {
            throw new ExceptionUtils("Prize bond paragraph 2 content not verified");
        }
    }

    /**
     * function used for clicking on allocating full amount to cashin
     *
     * @param var1
     * @throws InterruptedException
     */
    public void clickFullAmountandConfirm(String var1) throws Exception {
        String str = "//button[@class='button button--secondary button--alt js-allocate-full-amount' and @data-current-id='%s']";
        avilableAmount = Double.parseDouble(webUtil.getText(By.id("spanAvailableFundCI")).replaceAll("[^0-9.]", ""));
        List<String> pro = data.getReinvestProducts();
        boolean result = pro.stream().anyMatch(i -> i.contains("Prize Bonds"));
        if (data.getOptionType().contentEquals("Reinvest")) {
            if (result) {
                if (pro.get(Integer.parseInt(data.getAllocateAllAmountIndex())).contentEquals("Prize Bonds")) {
                    webUtil.click(By.xpath(String.format(str, data.getAllocateAllAmountIndex())));
                    pbVerify();
                }
            } else {
                webUtil.click(By.xpath(String.format(str, "1")));
                if (result)
                    pbVerify();
                else {

                }
            }
        } else {
            if (webUtil.isElementDisplayed(commonallocateFullAmount, 20)) {
                if (webUtil.getText(commonallocateFullAmount).contentEquals("Allocate full amount")) {
                    webUtil.scrollToView(commonallocateFullAmount);
                    webUtil.click(commonallocateFullAmount);
                    log.info("Allocate full amount button clicked!");
                } else
                    throw new ExceptionUtils("Allocate full amount content not verified!");
            } else {
                throw new ExceptionUtils("Allocate full amount x-path not found!");
            }
        }
    }



    /**
     * function used for clicking confirm button on cash in
     *
     * @throws InterruptedException
     */
    public void clickCnfrmButton() throws InterruptedException {
        try {
            webUtil.waitForPageLoaded();
            boolean isPrizeBond = data.getReinvestProducts().stream().anyMatch(i -> i.contains("Prize Bonds"));
            if (isPrizeBond)
                reuse(cnfrmButton);
            else
                reuse(cashinConfirmbtn);
        } catch (Exception e) {
            throw new ExceptionUtils("Confirm button not clicked");
        }
    }

    /**
     * function user for clicking cancel button on cash in
     *
     * @throws Exception
     */
    public void clickCancelButton() throws Exception {
        try {
            reuse(cancelButton);
        } catch (Exception e) {
            throw new ExceptionUtils("Cancel button no clicked");
        }
    }


    /**
     * reuseable function to click on the allocate full amount
     *
     * @param locator
     * @throws InterruptedException
     */
    public void reuse(By locator) throws InterruptedException {
        if (webUtil.isElementclickable(locator, 20)) {
            webUtil.scrollToView(locator);
            try {
                webUtil.click(locator);
            } catch (Exception e) {
                throw new ExceptionUtils("Enter Amount page confirm button click is not clicked");
            }
        } else {
            throw new ExceptionUtils("Confirm button x-path not found!");
        }
    }

    /**
     * function used for clicking the edit your order
     *
     * @throws InterruptedException
     */
    public void editYourOrder() throws InterruptedException {
        reuse(editYourAmount);
    }
//    public void remainingAmount(String amount) throws InterruptedException {
//       int remainingAmount=holdings.maturity_value - Integer.parseInt(amount);
//       enterAmount(Integer.toString(remainingAmount));
//    }

    public void verifyThankYouMessage() throws Exception {
        if (!webUtil.getText(thankYouMessage).contentEquals("Thank you, your Bank Account details have been added to your profile.")) {
            throw new ExceptionUtils("Thank you message not verified!");
        }
    }

    public void verifyErrorMessage(String errorType,String expectedMessage) throws Exception {
        switch (errorType)
        {
            case "Repayment-Blank":
                enterAmount("");
                clickCnfrmButton();
                webUtil.gettextlog(blankAmountError,String::equals,expectedMessage);
                break;
            case "Repayment-Invalid":
                enterAmount("0");
                clickCnfrmButton();
                webUtil.gettextlog(invalidAmountError,String::equals,expectedMessage);
                break;
            case "Repayment-MoreThan":
                enterAmount("0123457");
                clickCnfrmButton();
//                modal.insufficientFundsModal(sliderName);
                webUtil.gettextlog(invalidAmountError,String::equals,expectedMessage);
                break;
        }
    }
}
