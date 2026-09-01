package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;


public class Cashin_Reinvest {
    private WebDriver driver;
    private final Holdings holdings = new Holdings(driver);
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    public String[] products, amounts;
    private final WebDriverUtil webUtil;
    private Double matureamt;
    public static double remainingAmount;
    public static String las4digits;
    public static double sum;
    public boolean cashinFlag;
    private String otp;

    private  static Logger log = LogManager.getLogger(Cashin_Reinvest.class);

    public static boolean flag;
    private final String reinvest_Enter_amount = "(//div[@style='display: block;']//div[@class='product-details-card__amount']//input[1])[%d]";


    public static double maturity_val;
    private final By confirmButton = By.id("submitButton");
    private final By addbank = By.xpath("//button[@regstrnData-modal='add-bank-modal']");
    private final By checkbox = By.xpath("//*[@for='checkTermsAndCondition']");
    private final By confirm = By.id("btnConfirmChangeIban");
    private final By otpPagetext = By.xpath("//*[@id='txtPin']");
    private final By otpfeildIBAN = By.xpath("//*[@id='securityCodeText']");
    private final By otpConfirmbuttonIBAN = By.id("btnVerifyCode");
    private final By otpfeild = By.id("txtPin");
    private final By dashboardwelcometext = By.xpath("//h4[contains(text(), 'Welcome back')]");
    private final By otpConfirmbutton = By.xpath("//button[text()='Confirm']");
    private final By checkboxdeposit = By.cssSelector("#pnlExAccCB label");
    private final By acno = By.cssSelector("input[type='text'][maxlength='10']");
    private final By mnthSave = By.xpath("//*[@class=\"product-summary-links__item product-summary-links__item--instalments\"]");
    private final By totalamt = By.xpath("//*[@class=\"footer__ec-total-amount footer__ec-total-amount--available-funds js-available-funds\"]");
    private final By mustallocateallmodal = By.xpath("//section[@class='dashboard-modal__content js-modal active']");
    private final By modalHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h1");
    private final By modalContent = By.xpath("//section[@class='dashboard-modal__content js-modal active']//p");
    private final By modalContentPB = By.id("allocate-all-pb-description");
    private final String lessamount = "The minimum purchase value for this State Savings Product is €50.";
    private final String greateramount = "Please enter amount less than total maturity account";

    private final By modalCloseBtn = By.xpath("(//section[@class='dashboard-modal__content js-modal active']//button)[1]");
    private final By modalCancelBtn = By.xpath("(//section[@class='dashboard-modal__content js-modal active']//button)[2]");
    private final By modalConfirmBtn = By.xpath("(//section[@class='dashboard-modal__content js-modal active']//button)[3]");


    private final By allocatetocashbutton = By.id("btnAllocateToCash");
    private final By allocateocashPb = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnAllocateToCashReInvest");
    private final By gobackbutton = By.xpath("//div[@class=\"dashboard-modal-btn--container\"]/button[text()='Go back']");
    private final By cancelbutton = By.xpath("//button[@data-modal='cancel-transaction-modal']");
    private By error = By.name("ft-min-amount-error");
    private final By cartValue = By.xpath("//span[@class='footer__ec-total-amount footer__ec-total-amount--total js-cartTotal']");


    Map<String, String> map = Map.of("Savings Bond - 3 Year", "SavingBond",
            "National Solidarity Bond - 4 Year", "NSB4Amt",
            "Savings Certificate - 5 Year", "SavingCert",
            "National Solidarity Bond - 10 Year", "NSB10Amt",
            "Deposit Account (POSB - book based)", "DepositAmount",
            "Prize Bonds", "PBAmount");


    private String Iban;
    private By addMoreProduct;
    private String productValue;
    private String amountValue;
    public By noProductError= By.name("product-selector-error");
    public By noAmountErrorFT= By.name("amount-required-error");
    public By noAmountErrorPB= By.name("pb-amount-required-error");
    public By accountRequiredError= By.name("required-account-error");
    public By accountError =By.name("account-error");
    public By belowMinReinvestErrorFT = By.name("ft-min-amount-error");
    public By belowMinReinvestErrorPB = By.name("pb-min-amount-input-error");
    public By belowMinReinvestErrorPOSB = By.name("da-min-amount-error");

    /**  //span[@name='da-min-amount-error']
     * Constructor
     *
     * @param driver
     */
    public Cashin_Reinvest(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    /**
     * Function for validating  IBAN is added or not
     *
     * @return
     * @throws Exception
     */

    public boolean checkIBAN() {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("/dashboard"));
        if (!driver.findElements(addbank).isEmpty()) {
            return flag = true;
        }
        return false;
    }

    /**
     * Function for selecting products
     *
     * @param product
     * @param amount
     * @throws Exception
     */
    public void selectingProduct(List<String> product, List<String> amount, String Acno) throws Exception {
        las4digits = api.getUserDetails(data.getUsername()).get("bankAccountIban").replaceAll("[^0-9.]", "");
        maturity_val = Double.parseDouble(webUtil.getText(totalamt).replaceAll("[^0-9.]", ""));

        if (product.size()> 1) {
            products = product.toArray(new String[0]);
            amounts =  amount.toArray(new String[0]);;
            selectMultiple(products, amounts, Acno);
        } else {
            products = new String[1];
            amounts = new String[1];
            products[0] = product.get(0);
            amounts[0] = amount.get(0);
            selectSingle(product.get(0), amount.get(0), Acno);
        }
    }

    public void selectProduct(String product) throws IOException {
        String drodown1 = "(//select[@name='product-select'])[%d]";
        webUtil.selectDropDown(By.xpath(String.format(drodown1, 1)), select -> select.selectByVisibleText(product));
    }
    /**
     * Function used for selecting single product
     *
     * @param product
     * @param amount
     * @throws Exception
     */
    public void selectSingle(String product, String amount, String Acno) throws Exception {
        selectProduct(product);
        if (product.contains("Deposit Account (POSB - book based)")) {
            deposit(Acno);
        }

        setReinvest_Enter_Singleamount(product, amount);
    }

    /**
     * Function for selecting single amount for fixed term products
     *
     * @param product
     * @param amount
     * @throws Exception
     */
    public void setReinvest_Enter_Singleamount(String product, String amount) throws Exception {

        if (map.containsKey(product)) {
            enterAmount(amount);
            log.info("€" + amount + " amount entered".concat("for product: ".concat(product)));
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("€" + amount + " amount entered".concat("for product: ".concat(product)));
            if (!product.contains("Deposit Account (POSB - book based)") && !product.contains("Prize Bonds")) {
                amountvalidation(product, amount, 0);
            } else if (product.contains("Prize Bonds")) {
                amountvalidationPB(product, amount, 0);
            } else {
                amountvalidationPOSB(product, amount, 0);
            }
        }
    }
    /**
     * Function for entering amount
     *
     * @param amount
     */

    public void enterAmount(String amount) {
        webUtil.sendKeys(By.xpath(String.format(reinvest_Enter_amount, 1)), amount);
        driver.findElement(By.xpath(String.format(reinvest_Enter_amount, 1))).sendKeys(Keys.TAB);
    }

    /**
     * Function for Selecting multiple fixed term products
     *
     * @param product
     * @param amount
     * @throws Exception
     */
    public void selectMultiple(String[] product, String[] amount, String Acno) throws Exception {

        String drpdwn = "_product1CIRI']";
        String dropdown = "(//select[@name='product-select'])[%d]";
        String addAnthrprdct = "lnkAddMoreProduct";
        List<String> productList = Arrays.asList(product);
        List<String> am = Arrays.asList(amount);
        Consumer<String> enterAmount = s -> {
            try {
                setReinvest_Enter_amount(productList.get(productList.indexOf(s)), am.get(productList.indexOf(s)), productList.indexOf(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Consumer<String> selectprod = s -> {
            webUtil.selectDropDown(By.xpath(String.format(dropdown, productList.indexOf(s) + 1)), select -> select.selectByVisibleText(s));
            try {
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            } catch (IOException e) {
                e.printStackTrace();
            }

        };
        Consumer<String> posb = value -> {
            try {
                POSB(productList.get(productList.indexOf(value)), Acno);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Consumer<String> addAnother = value -> {
            try {
//                amountUtil(productList.get(productList.indexOf(value)), am.get(productList.indexOf(value)), productList.indexOf(value));
                if (productList.indexOf(value) != productList.size() - 1) {
                    try {
                        webUtil.scrollToView(By.id(addAnthrprdct));
                        webUtil.click(By.id(addAnthrprdct));
                    } catch (Exception e) {
                        throw new ExceptionUtils("Add another button is not clicked");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        productList.forEach(selectprod
                .andThen(posb)
                .andThen(enterAmount)
                .andThen(addAnother));
    }


    /**
     * @param product
     * @param acno
     * @throws InterruptedException
     */
    public void POSB(String product, String acno) throws InterruptedException {
        if (product.contains("Deposit Account (POSB - book based)")) {
            deposit(acno);
        }
    }

    /**
     * @param product
     * @param amount
     * @param index
     * @throws Exception
     */
    public void amountUtil(String product, String amount, int index) throws Exception {
        if (!product.contains("National Solidarity Bond - 4 Year")) {
            amountvalidation(product, amount, index);
        } else
            amountvalidationPB(product, amount, index);
    }


    /**
     * Function for POSB new Account creation
     *
     * @param Acno
     * @throws RuntimeException
     * @throws InterruptedException
     */
    public void deposit(String Acno) throws RuntimeException, InterruptedException {

        if (!Acno.contains("null")) {
            clickPOSBAccountCheckbox();

            webUtil.waitUntilElementLocated(acno, 10);
            enterPOSBAccountNumber(Acno);

        }
    }

    public void enterPOSBAccountNumber(String Acno) {
        webUtil.sendKeys(acno, Acno);
        driver.findElement(acno).sendKeys(Keys.TAB);
    }

    public void getCandashboardwelcometextcelbutton() throws Exception {

        webUtil.gettextlog(dashboardwelcometext, String::contains, "Welcome back");


    }



    public void clickPOSBAccountCheckbox() {
        webUtil.click(checkboxdeposit);
    }

    /**
     * Function for slecting multiple amount for multiple products
     *
     * @param product
     * @param amount
     * @param i
     * @throws Exception
     */
    public void setReinvest_Enter_amount(String product, String amount, int i) throws Exception {
        if (map.containsKey(product)) {
            webUtil.sendKeys(By.xpath(String.format(reinvest_Enter_amount, i + 1)), amount);
            log.info("€" + amount + " amount entered".concat("for product: ".concat(product)));
            driver.findElement(By.xpath(String.format(reinvest_Enter_amount, i + 1))).sendKeys(Keys.TAB);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("€" + amount + " amount entered".concat("for product: ".concat(product)));
            if (!product.contains("Deposit Account (POSB - book based)") && !product.contains("Prize Bonds")) {
                amountvalidation(product, amount, i);
            } else if (product.contains("Prize Bonds"))
                amountvalidationPB(product, amount, i);
            else
                amountvalidationPOSB(product, amount, i);

        } else
            throw new ExceptionUtils("Amount not entered");
    }

    /**
     * Function for amount error message validation
     *
     * @param product
     * @param amount
     * @param i
     * @return
     * @throws Exception
     */
    public void amountvalidation(String product, String amount, int i) throws Exception {
        matureamt = holdings.getMaturityValue();
        double amnt = Double.parseDouble(amount);
        if (webUtil.isElementVisible(error, 5)) {
            if (amnt < 50) {
                if (webUtil.getText(error).contains("The minimum purchase value for this State Savings Product is €50."))
                    log.error("Product: " + product + " Error msg verified for lessamount " + amnt);
                else
                    throw new ExceptionUtils("Error msg not verified");
            } else {
                if (webUtil.getText(error).contains("Invalid amount entered."))
                    log.error("Product: " + product + " Error msgverified for lessamount " + amnt);
            }
        }


    }

    /**
     * Function for amount error message validation for NSB
     *
     * @param product
     * @param amount
     * @param i
     * @return
     * @throws Exception
     */
    public void amountvalidationPB(String product, String amount, int i) throws Exception {
        double amnt = Integer.parseInt(amount);
        matureamt = holdings.getMaturityValue();
        error = By.name("pb-min-amount-input-error");
        if (webUtil.isElementVisible(error, 5)) {
            if (amnt < 25) {
                if (webUtil.getText(error).contains("The minimum purchase value for Prize Bonds is €25."))
                    log.error("Product: " + product + " Error msg verified for lessamount " + amnt);
                else
                    throw new ExceptionUtils("Error msg not verified PB");
            } else {
                if (webUtil.getText(error).contains("Invalid amount entered."))
                    log.error("Product: " + product + " Error msg verified for lessamount " + amnt);
                else
                    throw new ExceptionUtils("Error msg not verified PB");
            }
        }

    }

    public void amountvalidationPOSB(String product, String amount, int i) throws Exception {
        double amnt = Integer.parseInt(amount);
        matureamt = holdings.getMaturityValue();
        error = By.name("da-min-amount-error");
        if (webUtil.isElementVisible(error, 10)) {
            if (amnt < 0.01) {
                if (webUtil.getText(error).contains("The minimum purchase value for this State Savings Product is €0.01."))
                    log.error("Product: " + product + " Error msg not verified for lessamount " + amnt);
                else
                    throw new ExceptionUtils("Error msg not verified  POSB");
            } else {
                if (webUtil.getText(error).contains("Invalid amount entered."))
                    log.error("Product: " + product + " Error msg not verified for lessamount " + amnt);
                else
                    throw new ExceptionUtils("Error msg not verified POSB ");
            }
        }

    }


    /**
     * Function to set CashIn amount, which depends on how user selects
     * product it may be single or multiple
     *
     * @param amount
     * @throws Exception
     */
    public void setCashIn_Enter_Amnt(List<String> amount) throws Exception {
        log.info(holdings.getMaturityValue());
        if (amount.size()>1)
            multipleAmountUtile_forCashIn(amount);
        else
            singleAmountUtile_forCashIn(amount.get(0));
    }


    /**
     * Function for set CashIn amount
     * when multiple products are selected by user
     *
     * @param amount
     * @throws InterruptedException
     */
    public void multipleAmountUtile_forCashIn(List<String> amount) throws Exception {
        Double sum;
        amounts = amount.toArray(new String[0]);
        sum = Arrays.stream(amounts).mapToDouble(Double::parseDouble).sum();
        log.info(sum);
        String actualvalue = webUtil.getText(cartValue).replaceAll("[^0-9.]", "");
        actualvalue = new BigDecimal(actualvalue).stripTrailingZeros().toPlainString();
        String expectedvalue = String.valueOf(sum).replaceAll("\\.0*$", "");
        if (!actualvalue.contains(expectedvalue)) {
            log.error("Cart value not verified");
            throw new ExceptionUtils("Total sum amount not matched with allocated funds");
        }
        remainingAmount = BigDecimal.valueOf(maturity_val).subtract(BigDecimal.valueOf(sum)).doubleValue();
        log.info(remainingAmount);


        setCashin_confirm(remainingAmount);

    }

    /**
     * Function helps in set Cashin amount
     * when single product is selected
     *
     * @param amount
     * @throws InterruptedException
     */
    public void singleAmountUtile_forCashIn(String amount) throws Exception {
        double amt = Double.parseDouble(amount);
        remainingAmount = BigDecimal.valueOf(holdings.getMaturityValue()).subtract(BigDecimal.valueOf(amt)).doubleValue();
        log.info(remainingAmount);
        setCashin_confirm(remainingAmount);
    }


    /**
     * Function for entering CashIn amount
     *
     * @param amnt
     * @throws InterruptedException
     */
    public void setCashin_confirm(double amnt) throws Exception {
        String cashIn_Enter_Amnt = "txtCashAmount";
        if (webUtil.isElementDisplayed(By.id(cashIn_Enter_Amnt), 10)) {
            String actualText = webUtil.getText(By.id("para_Title")).replaceAll("\\n", " ");
            String expectedText = "All repayments of an Ireland State Savings product are subject to Terms and Conditions, requiring 7 business days’ notice. In the case of a maturing account the repayment will not be verified before the maturity date.  When your repayment has been verified you will be issued with a Payment Advice Notice by email. The repayment value will be transferred to the bank account ending x%s within 5 days of you receiving the Payment Advice Notice.";
            webUtil.scrollToView(By.id(cashIn_Enter_Amnt));
            if (las4digits == null || las4digits.isEmpty())
                las4digits = api.getUserDetails(data.getUsername()).get("bankAccountIban").replaceAll("[^0-9.]", "");

            expectedText = String.format(expectedText, las4digits);
            webUtil.CompareString(actualText, String::equals, expectedText);
            webUtil.sendKeys(By.id(cashIn_Enter_Amnt), Double.toString(amnt));
            cashinFlag = true;
            driver.findElement(By.id(cashIn_Enter_Amnt)).sendKeys(Keys.TAB);
            String actual = webUtil.getText(cartValue).replaceAll("[^0-9.]", "").replaceAll("\\.0*$", "");
            String expected = String.valueOf(holdings.getMaturityValue()).replaceAll("\\.0*$", "");
          /*  webUtil.CompareString(actual,String::equals,expected);
            if (!actual.contains(expected)) {
                log.error("Cart value not verified with maturity value");
                throw new ExceptionUtils("Total allocated funds not matched with maturity value");
            }*/
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Cashin amount entered: ".concat(String.valueOf(amnt)));

        } else
            throw new ExceptionUtils("Cashin amount not entered");
    }

    /**
     * Function for allocating full amount to any product
     *
     * @param counter
     */
    public void setAllocateFullAmt(String counter) throws Exception {
        if (!counter.contains("null")) {
            String allocateFullAmt = "(//div[@style='display: block;']//button[text()='Allocate full amount'])[%s]";
            if (webUtil.isElementDisplayed(By.xpath(String.format(allocateFullAmt, counter)), 10)) {
                webUtil.waitUntilElementLocated(By.xpath(String.format(allocateFullAmt, counter)), 10);
                webUtil.click(By.xpath(String.format(allocateFullAmt, counter)));

                setAllocatefullPb(counter);
            } else
                log.info("Allocate button not clicked");
        } else {
            getConfirmButtonClick();
        }
    }

    /**
     * Used for verifying content PB Allocate full Amount Modal
     *
     * @param counter
     * @throws Exception
     */
    public void setAllocatefullPb(String counter) throws Exception {
        String header = "Allocate full amount";
        String message = "You have selected to allocate all available funds to Prize Bonds.\n" +
                "Each prize bond has a value of €6.25 so the amount will be rounded down to the nearest multiple of €6.25. Any remaining funds will be transferred to your bank account ending ~ " + las4digits + ".";
        int index = Integer.parseInt(counter) - 1;
        if (products[index].contains("Prize Bonds")) {
            webUtil.gettextlog(modalHeader, String::equals, header);
            webUtil.gettextlog(modalContentPB, String::equals, message);
            webUtil.gettextlog(modalCancelBtn, String::equals, "Cancel");
            webUtil.gettextlog(modalConfirmBtn, String::equals, "Yes, I’m sure");
            webUtil.clickLog(modalConfirmBtn, "Yes, I’m sur Btn");
        } else
            setAllocatefull();
    }

    /**
     * Used for allocating full amount for all products
     * and also content is verified.
     *
     * @throws Exception
     */
    public void setAllocatefull() throws Exception {
        String header = "Allocate full amount";
        String message = "By allocating the full amount, any other selections you have made will be removed. Are you sure you want to proceed?";
        webUtil.gettextlog(modalHeader, String::equals, header);
        webUtil.gettextlog(modalContent, String::equals, message);
        webUtil.gettextlog(modalCancelBtn, String::equals, "Cancel");
        webUtil.gettextlog(modalConfirmBtn, String::equals, "Yes, I’m sure");
        webUtil.clickLog(modalConfirmBtn, "Yes, I’m sur Btn");



    }


    /**
     * Function to click on confirm button
     */
    public void getConfirmButtonClick() {
        try {

            webUtil.click(confirmButton);
            log.info("Confirm button on choose product page is clicked.");


        } catch (Exception e) {
            throw new ExceptionUtils("Confirm Button not clicked on select product page");

        }
    }


    /**
     * @param iban
     * @return
     */
    public void addIban(String iban) throws InterruptedException {
        webUtil.sendKeys(By.id("ibanText"), data.getIBAN());

    }

    /**
     * Function used click checkbox
     *
     * @return
     */
    public void click_Checkbox_confirm() throws Exception {

        if (webUtil.isElementDisplayed(checkbox, 10)) {
            webUtil.click(checkbox);
            if (webUtil.isElementclickable(confirm, 5)) {
                webUtil.click(confirm);
                webUtil.waitFor(10000);
            } else
                throw new ExceptionUtils("Confirm button not found");
        } else
            throw new ExceptionUtils("Checkbox not found");

    }

    /**
     * @param number
     * @return
     * @throws Exception
     */

    public Cashin_Reinvest setOTP(String number) throws Exception {
        String OTP = null;
        webUtil.waitUntilElementVisible(otpPagetext, 20);

        Properties prop = Settings.getInstance();
        String url = prop.getProperty("OTPEndPoint");
        otp = api.getOTP(url, 200, number);
        ExtentCucumberAdapter.addTestStepLog("OTP fetched: ".concat(otp));
        return this;
    }

    public String getOTP(String number) throws Exception {
        String OTP = null;

        Properties prop = Settings.getInstance();
        String url = prop.getProperty("OTPEndPoint");
        otp = api.getOTP(url, 200, number);
        ExtentCucumberAdapter.addTestStepLog("OTP fetched: ".concat(otp));


        return otp;
    }

    public Cashin_Reinvest setOTPIBAN(String number) throws Exception {
        String OTP = null;

        Properties prop = Settings.getInstance();
        String url = prop.getProperty("OTPEndPoint");
        otp = api.getOTP(url, 200, number);
//            ExtentCucumberAdapter.addTestStepLog("OTP fetched: ".concat(otp));


        return this;
    }

    public Cashin_Reinvest enterOTPIBAN() throws Exception {

        webUtil.sendKeys(otpfeildIBAN, otp);

        log.info("OTP is entered");



        return this;
    }

    public Cashin_Reinvest enterOTP() throws Exception {
        if (webUtil.isElementclickable(otpfeild, 10)) {
            webUtil.sendKeys(otpfeild, otp);

            log.info("OTP is entered");
            ExtentCucumberAdapter.addTestStepLog("OTP is entered");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

        } else {
            throw new ExceptionUtils("OTP not entered");
        }
        return this;
    }

    public void errorOTP(String number) throws Exception {
        final By nullmsg = By.id("RFVtxtPIN");
        final By lessdigit = By.id("CFVtxtPin");
        final By invalidmsg = By.id("lblResponse");
        String msg;
        if (webUtil.isElementVisible(otpfeild, 10)) {
            webUtil.sendKeys(otpfeild,"");
            clickConfirmOTP_Page();


                msg = webUtil.waitUntilElementVisible(nullmsg,10).getText();
                log.info("Blank OTP msg verified -> " + msg);

            webUtil.sendKeys(otpfeild,"0");
            clickConfirmOTP_Page();


                msg = webUtil.waitUntilElementVisible(lessdigit,10).getText();
                log.info("Less digit msg verified -> " + msg);

            webUtil.sendKeys(otpfeild,"345621");
//            Thread.sleep(210000);
            clickConfirmOTP_Page();
            clickConfirmOTP_Page();

                msg = webUtil.waitUntilElementVisible(invalidmsg,10).getText();
                log.info("Expired msg verified -> " + msg);

            webUtil.sendKeys(otpfeild,"123456");
            clickConfirmOTP_Page();
            clickConfirmOTP_Page();

                msg = webUtil.waitUntilElementVisible(invalidmsg,10).getText();
                log.info("Invalid msg verified -> " + msg);



        }
    }

    public void clickConfirmOTP_PageIBAN() throws InterruptedException {

        webUtil.click(otpConfirmbuttonIBAN);

    }

    public void clickConfirmOTP_Page() {
        Predicate<WebDriverUtil> web = i -> {
            if (i.isElementclickable(otpConfirmbutton, 12)) {
                i.click(otpConfirmbutton);
                log.info("Confirm Button on security code page is clicked");
                ExtentCucumberAdapter.addTestStepLog("Confirm Button on security code page is clicked");
                return true;
            }
            return false;
        };
        web.test(webUtil);
    }

    /**
     * Function used for clicking on child holdings
     *
     * @param Acno
     * @throws InterruptedException
     */
    public void clickmonthlysavings(String Acno) throws InterruptedException {

        webUtil.waitUntilElementVisible(mnthSave, 20);
        System.out.println(Acno);
        if (webUtil.isElementDisplayed(mnthSave, 5)) {
            List<WebElement> list = driver.findElements(mnthSave);
            WebElement element = list
                    .stream()
                    .parallel()
                    .filter(i -> i.getText().contains(Acno))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionUtils("Instalment ac not found"));
            element.click();
        } else
            throw new ExceptionUtils("Monthly save xpath changed");

    }

    public void allocatetocash() throws Exception {

                    webUtil.click(allocatetocashbutton);


    }

    public void allocatetocashPrizeBonds() throws Exception {
        String message = "As this product has matured you must allocate the full amount. As there is a residual amount you must allocate it to either a State Savings product or request a cash repayment.";
        webUtil.gettextlog(mustallocateallmodal, String::equals, message);
        webUtil.clickLog(allocateocashPb, "allocate ");
        if (webUtil.isElementDisplayed(mustallocateallmodal, 10)) {
            if (webUtil.getText(By.xpath("//section[@class='dashboard-modal__content js-modal active']/p")).contains(message)) {
                log.info("Modal text verified");
                if (webUtil.isElementclickable(allocateocashPb, 5)) {
                    webUtil.click(allocateocashPb);
                } else
                    throw new ExceptionUtils("Allocate to cash buttOn not clicked");
            } else
                throw new ExceptionUtils("Modal text not verified on Allocate to cash PB");
        } else
            throw new ExceptionUtils("allocate to cash modal not present for PB");

    }

    /**
     * //     * Used for verifying modal content
     * * @throws Exception
     */
    public void availablefundsmodal() throws Exception {
        String header = "You must allocate all available funds";
        String message = "As this product has matured you must allocate the full amount. " +
                "As there is a residual amount you must allocate it to either a State Savings product or request a cash repayment.";
        if (webUtil.isElementDisplayed(mustallocateallmodal, 10)) {
            if (!webUtil.getText(mustallocateallmodal).contains(header)) {
                throw new ExceptionUtils("Header not verified");
            }
            log.info("header verified");
            if (!webUtil.getText(mustallocateallmodal).contains(message)) {
                throw new ExceptionUtils("Message not verified");
            }
            log.info("msg verified");
            if (webUtil.isElementDisplayed(gobackbutton, 10)) {
                webUtil.click(gobackbutton);

            } else
                throw new ExceptionUtils("Goback button not clicked");
        } else
            throw new ExceptionUtils("Modal not available");


    }

    /**
     * Used for calling cancel button action
     *
     * @throws Exception
     */
    public void getCancelbutton() throws Exception {

        webUtil.click(cancelbutton);
        log.info("cancel button clicked on choose product page");
        cancelModal();

    }

    /**
     * Function used for verifying content on cancel modal
     *
     * @throws Exception
     */
    public void cancelModal() throws Exception {
        String header = "Cancel transaction";
        String message = "Are you sure you want to cancel this transaction?";
//        System.out.println(webUtil.getText(mustallocateallmodal));
        webUtil.gettextlog(modalHeader, String::equals, header);
        webUtil.gettextlog(modalContent, String::equals, message);
        webUtil.gettextlog(modalCancelBtn, String::equals, "No, go back");
        webUtil.gettextlog(modalConfirmBtn, String::equals, "Yes, cancel");
        webUtil.clickLog(modalConfirmBtn, "Cancel Btn");

    }

    public void pendinTrnscModal() throws Exception {
        String header = "Pending Transaction";
        String message = "This account cannot be reinvested or cashed in at present because we are currently processing a transaction for you. If you have not initiated a transaction request please contact us immediately.";
        if (webUtil.isElementVisible(mustallocateallmodal, 10)) {
            WebElement element = driver.findElement(mustallocateallmodal);
            WebElement headerContent = element.findElement(By.xpath(".//h1"));
            WebElement paraContent = element.findElement(By.xpath(".//p"));
            WebElement btnClose = element.findElement(By.xpath(".//button[normalize-space()='Close']"));
            if (!headerContent.getText().equals(header)) {
                throw new ExceptionUtils("header not verified");
            }
            log.info("Header verified");
            webUtil.CompareString(paraContent.getText(), String::equals, message);
            log.info("Message verified");
            btnClose.click();

        }
    }


    public void slectMultipleEdit(String[] product, String[] amount, String Acno) throws Exception {


        String dropdown = "(//select[@name='product-select'])[%s]";
        String enteramount = "(//input[@name='amount-input'])[%d]";
        List<String> productList = Arrays.asList(product);
        List<String> am = Arrays.asList(amount);
        addMoreProduct = By.id("lnkAddMoreProduct");

        IntStream.range(0, productList.size())
                .forEach(i -> {
                    String dropdownXPath = String.format(dropdown, i + 1);
                    String amountXPath = String.format(enteramount, i + 1);
                    productValue = productList.get(i);
                    amountValue = am.get(i);


                    webUtil.selectDropDown(By.xpath(dropdownXPath), select -> select.selectByVisibleText(productValue));

                    webUtil.sendKeys(By.xpath(amountXPath), amountValue);
                    if (i < productList.size() - 1) {
                        webUtil.scrollToView(addMoreProduct);
                        webUtil.click(addMoreProduct);
                    }
                });
    }



}




