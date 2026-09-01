package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.StateSavingsDashboardPage;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.cucumbercraft.POMPages.StateSavingsDashboardPage.getProductName;
import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;

public class Holdings {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private static double maturity_value;
    private final By reinvestCashinButton = By.xpath(".//a[contains(text(),'Reinvest / Cash In') or contains(text(),'Cash In')]");
    private final By maturityValueText = By.xpath(".//strong");
    private WebElement available;
    private static final Logger log = LogManager.getLogger(Holdings.class);
    private final By allProductList = By.xpath("//div[@class='product-summary-info product-summary-info--card']");
    private final By installmentAllProductLit = By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div");
    private final By reinvestAndCashin = By.xpath("//*[contains(@id,'ReInvestCashInBlock')]/div[3]");
    private final String reinvestcashincontent = "//div[contains(@id,'p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_block_CashInReinvestWith%sIban')]";
    private final By goBack = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_lnk_GoBack']");
    private final By donor = By.cssSelector(".product-summary-info--card--no-bg");
    private final By downloadPDF= By.id("btnDownloadHoldings");
    private By summaryCards=By.cssSelector(".product-summary-info--card");
    private final By txtMaturityAmount = By.tagName("strong");



    public static String lastFourDigit;
    private static String methodType;
    private String string;
    APIReusuableLibrary api = new APIReusuableLibrary();
    StateSavingsDashboardPage dashboardPage;
    private By btnCashin=By.cssSelector("a.gtm-repayReinvest");
    private By btnReinvestCashin=By.cssSelector("a.gtm-repayReinvest");
    private By reinvestOption= By.cssSelector("button.gtm-repayReinvest");
    private final By btnPendingTransaction = By.cssSelector("button[data-modal='pending-transaction-modal']");



    public Holdings(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
        this.dashboardPage= new StateSavingsDashboardPage(driver);
    }



    /**
     * @param option
     * @param ID
     * @throws Exception
     */
    public void userSelectsMatureProductAndClickOnButton(String option, String ID) throws Exception {
        methodType = option;
        String val;
        try {
            List<WebElement> list = webUtil
                    .getWebDriverWait()
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(donor));
            WebElement donorholding = list
                    .stream()
                    .filter(element -> element.getText().contains(ID))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionUtils("Donor holding not found"));
            maturity_value = Double.parseDouble(donorholding.findElement(By.xpath(".//strong")).getText().replaceAll("[^0-9.]", ""));
            webUtil.scroll(donorholding);
            donorholding.findElement(By.xpath(".//a")).click();
        } catch (Exception e) {
            throw new ExceptionUtils("Holdings not available");
        }
        selectOption(option);
        webUtil.waitForPageLoaded();
    }


    /**
     * Function for selecting option on choose option page
     *
     * @param option
     * @throws Exception
     */
    public Holdings selectOption(String option) throws Exception {
        methodType = option;
        if (option.equalsIgnoreCase("Reinvest")) {
            try {
                clickReinvest();
                log.info("Reinvest is selected");
                ExtentCucumberAdapter.addTestStepLog("Reinvest option is selected");
            } catch (Exception e) {
                throw new ExceptionUtils("Reinvest button is not clicked ".concat(e.getMessage()));
            }

        } else if (option.equalsIgnoreCase("Cash-In")) {
            try {
                clickCashInOption();
                log.info("Cash in is selected");
                ExtentCucumberAdapter.addTestStepLog("Cash In option is selected");
            } catch (Exception e) {
                throw new ExceptionUtils("Cash In button is not clicked ".concat(e.getMessage()));
            }
        } else if (option.equalsIgnoreCase("Reinvest-Repay")) {
            try {
                clickReinvestAndCashIn();
                log.info("Repay-Reinvest is selected");
                ExtentCucumberAdapter.addTestStepLog("Repay-Reinvest option is selected");
            } catch (Exception e) {
                throw new ExceptionUtils("Repay-Reinvest button is not clicked ".concat(e.getMessage()));
            }
        } else if (option.equalsIgnoreCase("null")) {
            clickCashInOption();
            log.info("Cash In option is selected");
            ExtentCucumberAdapter.addTestStepLog("Cash In option is selected");
        } else {
            throw new ExceptionUtils(data.getOptionType().concat(" not selected"));
        }
        return this;
    }

    public void checkRepReinvestLink() throws Exception {
        String str = "//div[@class='product-summary-info product-summary-info--card']";
        String str1 = "//child::a";
        List<WebElement> web = driver.findElements(By.xpath(str));
        for (int i = 0; i < web.size(); i++) {
            if (webUtil.getText(By.xpath(str + "[" + (i + 1) + "]")).contains(data.getHoldingID())) {
                if (webUtil.isElementDisplayed(By.xpath(str + "[" + (i + 1) + "]" + str1), 20)) {
                    webUtil.scrollToView(By.xpath(str + "[" + (i + 1) + "]" + str1));
                    if (webUtil.isElementDisplayed(By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div[" + i + "]//a[contains(text(),'Reinvest / Cash in')]"), 20)) {
                        throw new ExceptionUtils("Repay-Reinvest link is displayed!");
                    } else {
                        log.info("Repay-Reinvest link is not displayed!");
                    }
                }
            }
        }
    }




    private final Map<String, Consumer<String>> consumerMap = Map.of("Cash In", this::clickCashin,
            "Reinvest Cash In", this::clickReinvestCashin,
            "Prize Bond Reinvest Cash In", this::clickPBReinvestCashin);


    private void clickCashin(String HoldingID) {
        fetchSummary(HoldingID, summaryCards)
                .ifPresentOrElse(webElement -> {
                    webElement.findElement(btnCashin).click();
                }, () -> {
                    throw new ExceptionUtils("Cash in button not found for: " + HoldingID);
                });
    }

    private void clickReinvestCashin(String HoldingID) {
        summaryCards = data.getProduct().equals("Installment Savings") || data.getProduct().equals("Childcare Plus") ? donor : summaryCards;
        fetchSummary(HoldingID, summaryCards)
                .ifPresentOrElse(webElement -> {
                    var matValue = webElement.findElement(txtMaturityAmount).getText().replaceAll("[^0-9.]", "");
                    maturity_value= Double.parseDouble(matValue);
                    webElement.findElement(btnReinvestCashin).click();
                }, () -> {
                    throw new ExceptionUtils("Reinvest Cash in button not found for: " + HoldingID);
                });
    }

    private void clickPBReinvestCashin(String holdingID) {
        webUtil.click(reinvestOption);
    }

    private Optional<WebElement> fetchSummary(String holdingId, By locator) {
        Predicate<WebElement> isHoldingAvailable = webElement -> webElement.getText().contains(holdingId);
        return webUtil.getElements(locator)
                .stream()
                .peek(webUtil::scroll)
                .filter(isHoldingAvailable)
                .findFirst();
    }

    public void clickInvestmentOpt(@NotNull String option, String holding) {
         consumerMap.get(option).accept(holding);
    }



    public void pendingTransaction(String ID) {
        boolean isInstallmentSave = data.getProduct().contains("Installment Savings");
        boolean isChildcare = data.getProduct().contains("Childcare Plus");
        available = (isInstallmentSave || isChildcare)
                ? fetchSummary(ID, donor).get()
                : fetchSummary(ID, summaryCards).get();
        webUtil.scroll(available);

        Optional.of(available.getText())
                .filter(text -> text.contains("Pending transaction"))
                .ifPresentOrElse(
                        text -> available.findElement(btnPendingTransaction).click(),
                        () -> {
                            throw new ExceptionUtils("Pending Transaction text not verified");
                        }
                );
    }


    private void clickReinvest()  {
        By btnReinvest = By.xpath("//*[contains(@id,'block_Reinvest')]");
        webUtil.click(btnReinvest);

    }

    private void clickCashInOption() {

        By btnCashIn = By.xpath("//*[text()='Cash In']/parent::div");
        webUtil.click(btnCashIn);
    }

    private void clickReinvestAndCashIn()  {


        webUtil.click(reinvestAndCashin);



    }

    public Holdings reinvest_cashincontent() throws Exception {
        if (Cashin_Reinvest.flag)

            try {
                webUtil.click(reinvestAndCashin);
                log.info("Reinvest and Cash In option is Clicked");
            } catch (Exception e) {
                throw new ExceptionUtils("Reinvest and Cash in link click may have been interrupted ".concat(e.getMessage()));
            }
        else if (webUtil.isElementDisplayed(reinvestAndCashin, 10)) {


            if (!webUtil.getText(By.xpath(String.format(reinvestcashincontent, "").concat("/h6"))).contains("Reinvest and Cash In")) {
                log.error("Reinvest and Cash In option header not verified");
                throw new ExceptionUtils("Reinvest and Cash In option header content not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(reinvestcashincontent, "").concat("/p"))).contentEquals("Reinvest in State Savings product(s) AND cash in")) {
                log.error("Reinvest and Cash In option paragrph not verified");
                throw new ExceptionUtils("Reinvest and Cash In option para content not matched");
            }

        } else {
            throw new ExceptionUtils("Reinvest Cash in link x-path may have been changed");
        }
        return this;
    }


    private void goBackButton() {
        try {
            webUtil.click(goBack);
        } catch (Exception e) {
            throw new ExceptionUtils("Go Back Button Click may have been interrupted");
        }
    }




    public double getMaturityValue() {
        return maturity_value;
    }

    public String getMethodType() {
        return methodType;
    }


    public String get_Last_For_Digit_IBAN() throws Exception {
        try {
            lastFourDigit = api.getUserDetails(data.getUsername()).get("bankAccountIban").replaceAll("[^0-9]", "");
//                    webUtil.getText(Ibanno).replaceAll("[^0-9]", "");
        } catch (ExceptionUtils e) {
            throw new ExceptionUtils("IBAN text is not displayed ".concat(e.getMessage()));
        }
        return lastFourDigit;
    }


    public void clickDownloadPDFSummaryLink() {
        webUtil.scrollToView(downloadPDF);
        webUtil.click(downloadPDF);
    }
}
