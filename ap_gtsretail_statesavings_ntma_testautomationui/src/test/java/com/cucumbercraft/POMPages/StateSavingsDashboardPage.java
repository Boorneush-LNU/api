package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.HeadersForAPI;
import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.framework.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MapReduceIterable;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StateSavingsDashboardPage {

    private static final Logger log = LogManager.getLogger(StateSavingsDashboardPage.class);
    public static By downloadPdf = By.id("btnDownloadSummary");
    public final By reqtoAddHoldingsBtn = By.xpath("//button[@data-modal='add-missing-product-modal']");
    public static String productName;
    private final WebDriverUtil webUtil;
    private final By addIBANPromptMessage = By.id("ibanNotificationBox");
    private final String navTabs = "//ul[@class='primary-nav__list']/li/a[@title=";
    private final String common = "//div[@class='m40-dashboard-cards--card card-";
    private final String navAction = "//div[@id='p_lt_WebPartZone3_zoneSecondaryHeader_SSSecondaryNav_authenticatedArea']/li/a";
    private final By buyForMyself = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_lnkPrizeBondsForMyself']");
    private final By buyGift = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_lnkPrizeBondsAsGift']");
    private final Map<String, Consumer<WebDriverUtil>> productMap = new HashMap<>();
    private final By allProductTile = By.xpath("//div[@class='m40-dashboard-cards']");
    private final By logo = By.xpath("//a[@class='gtm-nav']/img[1]");
    private final By allHoldingAppear = By.xpath("//*[@id='requestToAddHolding']");
    private final By addLink = By.id("addBankDetailText");

    private final String productXpath = "//h4[normalize-space(text())='%s']/../../..//a[normalize-space()='View & Manage']";
    private final By productRoot = By.xpath("//div[contains(@class,'m40-dashboard-cards--card card-')]");
    private final By produtName = By.xpath("//div[contains(@class,'m40-dashboard-cards--card card-')]/descendant::h4");
    //div[contains(@class,'m40-dashboard-cards--card card-')]/descendant::div[@class="m40-dashboard-cards--value"]
    private final By productAmount = By.xpath("//*[contains(@class,'m40-dashboard-cards--value')]/span");
    public By profileAndSettingstab = By.xpath("//a[@title='Profile and Settings']");
    By allHoldingTitle = By.xpath("//*[@class='medium-10 columns']/h4");
    By allHoldingDesc = By.xpath("//*[@class='medium-10 columns']/p");
    By allHoldingPBTitle = By.xpath("//*[@class='product-options-card--title']");
    By allHoldingPBdesc = By.xpath("//*[@for='ddlProductList']");
    By confirmBTN = By.xpath("//*[@id='btnDownloadForm']");
    By dropdown = By.xpath("//*[@id='ddlProductList']");
    By dropDownProd = By.xpath("//*[@id='ddlProductList']//option[3]");
public By ConfirmDescription = By.xpath("//p[contains(text(),'Please confirm you wish to cancel this request to add a product(s)')]");
public By ConfirmHeader = By.xpath("//h1[text()='Cancel request']");

    //Slider
    public final By addHoldingSlider = By.id("sectionAddNewProduct");
    public By thankYouAddHoldingSlider = By.id("sectionAddNewProductThankYou");
    public By ConfirmButton = By.xpath("//*[@id=\"sectionCancelRequest\"]/div[2]/button[2]");
    public By CloseX = By.cssSelector("button[aria-label='Close modal']");

    private WebDriver driver = null;
    private Map<Enum, String> navMap;
    PropertyConfig config = ConfigCache.getOrCreate(PropertyConfig.class);
    Type type = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
    Map<Integer, String> productNameMap = Map.of(
            0, "Prize Bonds",
            1, "Savings Bonds",
            2, "Savings Certificates",
            3, "4 Year National Solidarity Bonds",
            4, "10 Year National Solidarity Bonds",
            5, "Childcare Plus",
            6, "State Savings Account",
            7, "Instalment Savings");
    private List<Map<String, String>> holdingList;

    private static final String TITLE_XPATH_FORMAT = "//a[@title='%s']";



    public enum NavigationButton {
        YOUR_SAVINGS("Your Savings"),
        PROFILE_AND_SETTINGS("Profile and Settings"),
        NOTIFICATION("Notifications"),
        HELP_AND_SUPPORT("Help and Support");

        private final String buttonTitle;

        NavigationButton(String buttonTitle) {
            this.buttonTitle = buttonTitle;
        }

        public String getButtonTitle() {
            return buttonTitle;
        }

        public By getLocator() {
            return By.xpath(String.format(TITLE_XPATH_FORMAT, buttonTitle));
        }
    }

    public StateSavingsDashboardPage(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    APIReusuableLibrary apiLib=new APIReusuableLibrary();

    public static String getProductName() {
        return productName;
    }

    /**
     * Store all the products and help to click on the tile
     */
    private Consumer<WebDriverUtil> clickProductUtil(String product) {
        productMap.put("Prize Bond", util -> util.click(By.xpath(String.format(productXpath, "Prize Bonds"))));
        productMap.put("Saving Bond", util -> util.click(By.xpath(String.format(productXpath, "Savings Bonds"))));
        productMap.put("Savings Certificate", util -> util.click(By.xpath(String.format(productXpath, "Savings Certificates"))));
        productMap.put("NSB 4 Year", util -> util.click(By.xpath(String.format(productXpath, "4 Year National Solidarity Bonds"))));
        productMap.put("NSB 10 Year", util -> util.click(By.xpath(String.format(productXpath, "10 Year National Solidarity Bonds"))));
        productMap.put("Installment Savings", util -> util.click(By.xpath(String.format(productXpath, "Instalment Savings"))));
        productMap.put("Childcare Plus", util -> util.click(By.xpath(String.format(productXpath, "Childcare Plus"))));
        productMap.put("SSA", util -> util.click(By.xpath(String.format(productXpath, "State Savings Account"))));
        return productMap.get(product);
    }

    /**
     * Help to store the nav actions which is present on site
     *
     * @return consumer
     */

    public Consumer<NavigationButton> clickNavigationButton() {
        return button -> {
            By buttonLocator = button.getLocator();
            String buttonTitle = button.getButtonTitle();
            webUtil.clickLog(buttonLocator, buttonTitle);
        };
    }

    public void clickRequestToAddHoldingsLink() {
        webUtil.click(reqtoAddHoldingsBtn);
    }

    public void clickDownloadPDFSummaryLink() {
        webUtil.scrollToView(downloadPdf);
        webUtil.click(downloadPdf);
    }


    /**
     * Verify prompt message on dashboard
     */
    public void verifyAddIBANPromptMessage() {
        WebElement element=webUtil.waitUntilElementVisible(addIBANPromptMessage,10);
        String txtCompleteProfile=element.findElement(By.id("CompeleProfileText")).getText().trim();
        String txtAddIBAN=element.findElement(addLink).getText().trim();
        webUtil.CompareString(txtCompleteProfile, String::equals, "Complete your Ireland State Savings Online profile.");

        webUtil.CompareString(txtAddIBAN, String::equals, "Add your bank details");

        boolean btnClose=element.findElement(By.xpath(".//button[@aria-label='Close notification']")).isDisplayed();

        Assertions.assertThat(btnClose).as("Close button").isTrue();
    }

    /**
     * Click on the add now bank details link
     */
    public void addBankDetailLink() {


        webUtil.click(addLink);


    }



    public void buyMore(String product) {

        switch (product) {
            case "Prize Bonds for Me":
                webUtil.click(By.xpath("//a[@class='m40-dashboard-cards__link js-modalTrigger gtm-linkclick']"));
                webUtil.click(buyForMyself);
                break;
            case "Prize Bonds as a Gift":
                webUtil.click(By.xpath("//a[@class='m40-dashboard-cards__link js-modalTrigger gtm-linkclick']"));
                webUtil.click(buyGift);
                break;
            case "National Solidarity Bond 10Year":
//                webUtil.scrollToView(By.xpath("//div[@class='m40-dashboard-cards--card card-nationalbonds10']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                webUtil.click(By.xpath("//div[@class='m40-dashboard-cards--card card-nationalbonds10']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                break;
            case "Savings Bonds":
                webUtil.scrollToView(By.xpath("//div[@class='m40-dashboard-cards--card card-savingsbonds']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                webUtil.click(By.xpath("//div[@class='m40-dashboard-cards--card card-savingsbonds']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                break;
            case "Savings Certificates":
                webUtil.scrollToView(By.xpath("//div[@class='m40-dashboard-cards--card card-savingscert']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                webUtil.click(By.xpath("//div[@class='m40-dashboard-cards--card card-savingscert']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                break;

        }
    }

    /**
     * Click product
     *
     * @param product product which needs to be clicked
     * @throws Exception
     */
    public void clickProduct(String product) {
        productName = product;
        if (webUtil.isAllElementVisible(allProductTile, 12)) { // Gets all Product name
            clickProductUtil(product).accept(webUtil);
            log.info("Passed over here");
        } else
            throw new ExceptionUtils("view and mange x-path may have been changed for: " + product);
    }


    public void clickImage() {
        webUtil.click(logo);
        webUtil.waitForPageLoaded();
    }

    public void allHolding() throws InterruptedException {
        webUtil.click(allHoldingAppear);
        webUtil.gettextlog(allHoldingTitle, String::equals, "Request to add holdings to Ireland State Savings Online.");
        webUtil.gettextlog(allHoldingDesc, String::equals, "Complete the information below and then download the form. The completed form should be signed and posted to Ireland State Savings, Fexco Centre, Killorglin, FREEPOST, Co. Kerry");
        webUtil.gettextlog(allHoldingPBTitle, String::equals, "Product details");
        webUtil.gettextlog(allHoldingPBdesc, String::equals, "Ireland State Savings Product");

        webUtil.click(dropdown);
        webUtil.waitUntilElementVisible(dropDownProd, 5);
        webUtil.click(dropDownProd);
        webUtil.click(confirmBTN);


    }

    public void validateWelcomeMsg() throws Exception {
        By greetings = By.xpath("//h4[contains(@class,'greeting')]");
        WebElement element = webUtil.waitUntilElementVisible(greetings, 10);
        if (element.isDisplayed()) {
            webUtil.scrollToView(greetings);
            System.out.println(webUtil.getText(greetings));
            Assertions.assertThat(webUtil.getText(greetings)).as("Welcome message").contains("Welcome");
            log.info("Login Successful");
            ExtentCucumberAdapter.addTestStepLog("<b><font color='yellow'>Dashboard page displayed</b></font>");
        } else {
            log.info("Login failed");
            ExtentCucumberAdapter.addTestStepLog("<b><font color='red'>Login Failed</b></font>");

        }
    }

    public int getProductSize() throws InterruptedException {
        int size;
        if (webUtil.isElementDisplayed(productRoot, 10)) {
            List<WebElement> ssProduct = driver.findElements(productRoot);
            size = ssProduct.size();
            System.out.println("No. of product size = " + size);
            return size;
        }

        return 0;

    }

    public List<String> getProductNames() {

        final Pattern SAVINGS_ACCOUNT_PATTERN = Pattern.compile("State Savings Account\\s*i?", Pattern.CASE_INSENSITIVE);
        final String REPLACEMENT_STRING = "State Savings Account";


        return webUtil.getElements(produtName)
                .stream()
                .map(WebElement::getText)
                .map(s -> SAVINGS_ACCOUNT_PATTERN.matcher(s).matches() ? REPLACEMENT_STRING : s)
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.toList());

    }


    public String getAmount() {


        try {
            if (webUtil.isElementDisplayed(productAmount, 10)) {
                String amt;
                List<WebElement> amnt = driver.findElements(productAmount);
                for (WebElement ele : amnt) {
                    amt = ele.getText();
                    return amt;

                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void validateProductPortfolio(String username) throws FileNotFoundException {
        List<Integer> productIds = fetchPortFolio(username).jsonPath().getList("productId");
        Set<Integer> uniqueProductIds = new HashSet<>(productIds);
        List<String> productNames = uniqueProductIds.stream()
                .map(productNameMap::get)
                .collect(Collectors.toList());
        for (String name : productNames) {
            webUtil.CompareString(name, String::equals, getProductNames().get(productNames.indexOf(name)), "Product Name");
        }


    }

    public Response  fetchPortFolio(String username) throws FileNotFoundException {
        RestAssured.baseURI = FrameworkConstants.baseUriMyAccountFEAPILinkFetch();
        Map<String, Object> queryMap = Map.of("username", username, "includeMatured", "true", "holdingType", "All");
        return apiLib.sendGetRequestWithOptionalParamsFunctional("api/v5/portfolio/list", queryMap, HeadersForAPI.getMyAccountFEHeaders());
    }


    public void validateNotificatioBubbleCount(String username) {
        int expectedCount = fetchUnreadNotificationCount(username).jsonPath().getList("isRead").size();
        By actualCount = By.cssSelector(".primary-nav__notifications");
        ExtentCucumberAdapter.addTestStepLog("Validating notification count:");
        webUtil.gettextByAttribute(actualCount, String::equals, String.valueOf(expectedCount), "data-notification");
    }


    @SneakyThrows
    public Response fetchUnreadNotificationCount(String username) {
        Map<String, String> queryMap = Map.of("unreadOnly", "true");
        return apiLib.fetchUnreadNotificationCountRefFunc(username,"username",HeadersForAPI.getMyAccountFEHeaders(),queryMap,
                FrameworkConstants.baseUriMyAccountFEAPILinkFetch(),
                "/api/v5/notification/list");
    }

    public Integer getProductIdByName(String productName) {

        return productNameMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(productName))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null); // Or throw an exception if you prefer
    }

    @SneakyThrows
    public void validateSummaryPage(String username, String productName) {
        RestAssured.baseURI = FrameworkConstants.baseUriMyAccountFEAPILinkFetch();
        Map<String, Object> queryMap = Map.of("username", username, "productId", getProductIdByName(productName), "includeMatured", "true", "holdingType", "sole");
        log.info(queryMap);
        Response response = apiLib.sendGetRequestWithOptionalParamsFunctional("api/v5/portfolio/list", queryMap, HeadersForAPI.getMyAccountFEHeaders());
        String json = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        holdingList = objectMapper.readValue(json, new TypeReference<>() {});
        holdingList.forEach(this::validateEachHolding);
    }

    public String convertDate(String inputDate) {
        try {
            Instant instant = Instant.parse(inputDate);
            ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC")); // or another suitable zone
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return zonedDateTime.format(formatter);
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
            throw new ExceptionUtils("Error parsing date: " + e.getMessage());
        }
    }

    private void validateEachHolding(Map<String, String> holdingMap) {
        String holdingId = holdingMap.get("holdingId");
        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Validating Holding Id - " + holdingId);

        List<WebElement> web = webUtil.getElements(By.cssSelector(".product-summary-info.product-summary-info--card"));
        System.out.println("Api size :" + holdingList.size() + " Holding size:" + web.size());
        WebElement element = web.stream()
                .peek(webUtil::scroll)
                .filter(webElement -> webElement.getText().contains(holdingId))
                .findFirst()
                .orElseThrow(() -> new ExceptionUtils("Holdings id not available: " + holdingId));
        By locatorLink = holdingMap.get("hasPendingTransaction").equals("false") ? By.cssSelector(".product-summary-info.product-summary-info--card a:nth-child(1)") : By.cssSelector(".product-summary-info.product-summary-info--card button:nth-child(1)");
        String purchaseDate = element.findElement(By.cssSelector(".product-summary-info--details-date .product-summary-text:nth-child(2)")).getText().trim();
        String currentBalance = element.findElement(By.cssSelector(".product-summary-info--details-value .product-summary-text:nth-child(2)")).getText().replaceAll("[^0-9.]", "");
        String maturityValue = element.findElement(By.cssSelector(".product-summary-info--details-value strong")).getText().replaceAll("[^0-9.]", "");
        String linkText = null;
        if (holdingMap.get("dateClosed")==null) {
            WebElement webElement=webUtil.waitUntilElementVisible(element.findElement(locatorLink),10);
            linkText = webElement.getText().trim();
        }
        String expectedPurchaseDate = convertDate(holdingMap.get("purchasedOn"));

        String expectedCurrentBalance = holdingMap.get("dateClosed")!=null?holdingMap.get("currentValue"):holdingMap.get("amountInvested");
        String expectedMaturityValue = holdingMap.get("valueAtMaturity");
        String expectedLinkText = holdingMap.get("isRepayable").equals("true") && holdingMap.get("isReinvestible").equals("true") ? "Reinvest / Cash In" : holdingMap.get("isRepayable").equals("true") ? "Cash In" : "Pending transaction";
        maturityValue = new BigDecimal(maturityValue).stripTrailingZeros().toPlainString();
        currentBalance = new BigDecimal(currentBalance).stripTrailingZeros().toPlainString();
        webUtil.CompareString(purchaseDate, String::equals, expectedPurchaseDate,"Purchase Date");
        webUtil.CompareString(currentBalance, String::equals, expectedCurrentBalance,"Current Balance");
        webUtil.CompareString(maturityValue, String::equals, expectedMaturityValue,"Expected Maturity Value");
        System.out.println(holdingMap.get("dateClosed"));
        if (holdingMap.get("dateClosed")==null)
            webUtil.CompareString(linkText, String::equals, expectedLinkText,"Link Text");

        FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"------ Holding Id - " + holdingMap.get("holdingId") + " validated ------");


    }
}
