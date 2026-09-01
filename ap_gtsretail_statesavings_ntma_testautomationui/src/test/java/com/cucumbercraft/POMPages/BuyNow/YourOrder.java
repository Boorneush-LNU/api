package com.cucumbercraft.POMPages.BuyNow;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.Context;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;

public class YourOrder {

    private final By fldAmount = By.xpath(".//td//input");
    WebDriver driver;
    WebDriverUtil webUtil;
    Context testContext;
    private final By ftProduct = By.xpath("//div[@class='ec-products_card']");
    private final By hdrYourOrder = By.xpath("// div[@class='wrapper__ecommerce']// h3[@class='ec-form__title']");
    private final By productName = By.tagName("h3");
    private final By txtIssue = By.tagName("p");
    private final By btnBuyNow = By.xpath("//li[contains(@id,'buyMoreBlock')]/a");


    List<WebElement> productList;
    public List<String> products, amounts;
    List<String> lblProductList = List.of(
            "Prize Bonds",
            "Prize Bonds as a Gift",
            "National Solidarity Bonds",
            "Savings Certificates",
            "Savings Bonds"
    );
    private final Map<String, Consumer<Integer>> consumerMap = Map.of(
            lblProductList.get(0), this::verifyProduct,
            lblProductList.get(1), this::verifyProduct,
            lblProductList.get(2), this::verifyNationalSolidarityBonds,
            lblProductList.get(4), this::verifySavingsBonds,
            lblProductList.get(3), this::verifySavingsCertificates
    );

    public Map<String, String[]> productMap = Map.of(
            lblProductList.get(0), new String[]{null, "true"},
            lblProductList.get(1), new String[]{null, "true"},
            lblProductList.get(2), new String[]{"10 year | Issue 9", "false"},
            lblProductList.get(4), new String[]{"3 year | Issue 18", "false"},
            lblProductList.get(3), new String[]{"5 year | Issue 25", "false"}
    );


    List<WebElement> productListIrish;
    public List<String> productsIrish, amountsIrish;
    List<String> lblProductListIrish = List.of(
            "Duaisbhannaí",
            "Duaisbhannaí mar Bhronntanas",
            "Banna Dlúthpháirtíochta Náisiúnta",
            "Cairteacha Coigiltis",
            "Bannaí Coigiltis"
    );
    private final Map<String, Consumer<Integer>> consumerMapIrish = Map.of(
            lblProductListIrish.get(0), this::verifyProductIrish,
            lblProductListIrish.get(1), this::verifyProductIrish,
            lblProductListIrish.get(2), this::verifyNationalSolidarityBondsIrish,
            lblProductListIrish.get(4), this::verifySavingsBondsIrish,
            lblProductListIrish.get(3), this::verifySavingsCertificatesIrish
    );



    public Map<String, String[]> productMapIrish = Map.of(
            lblProductListIrish.get(0), new String[]{null, "true"},
            lblProductListIrish.get(1), new String[]{null, "true"},
            lblProductListIrish.get(2), new String[]{"10 mBliana | 9ú Eisiúint", "false"},
            lblProductListIrish.get(4), new String[]{"3 Bliana | 18ú Eisiúint", "false"},
            lblProductListIrish.get(3), new String[]{"5 Bliana | 25ú Eisiúint", "false"}
    );

    private final By yourOrderPrdList = By.xpath("//td/h4");
    YourDetail detail;
    JourneyHandler journeyHandler;
    private Map<String, String> cartMap;
    private final By yourOrderAmtList = By.xpath("//tr[@class='js-ec-your-order-row']");
    public final By productText = By.xpath(".//h4");
    private final By amountText = By.xpath(".//td[2]//input");
    private final By pbAmountText = By.xpath(".//td//span[contains(@class,'js-input-total')]");
    public final By issueText = By.xpath(".//p[1]");
    private final By btnContinueToReview = By.xpath("//button[contains(@id,'SubmitCart')]");
    private final By btnContinueToOrder = By.xpath("//button[@id='btnSubmitUser']");

    private final By Remove = By.xpath(".//td//button");

    public YourOrder(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        detail = new YourDetail(driver);
        journeyHandler = new JourneyHandler(driver);
    }


    public JourneyHandler addProductToCart(String product) {
        String btnAdd = "//a[@title='%s']/following-sibling::a";
        Function<String, WebElement> stringFunction = string -> webUtil.waitUntilElementVisible(By.xpath(String.format(btnAdd, string)), 10);
        WebElement element = stringFunction.apply(product);
        webUtil.scroll(element);
        element.click();
        return new JourneyHandler(driver);

    }

    public YourOrder addMultipleProduct(PurchaseModel data) {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("your-order"));
        for (int i = 1; i < products.size(); i++) {
            switch (products.get(i)) {
                case "Prize Bonds":
                case "National Solidarity Bonds":
                case "Savings Bonds":
                case "Savings Certificates":
                    addProductToCart(products.get(i));
                    if (products.indexOf("Prize Bonds as a Gift") == 0) {
                        if (i == 1) journeyHandler.getJourney(data.getJourney(), data).run();
                    }
                    break;
                case "Prize Bonds as a Gift":
                    addProductToCart(products.get(i));
                    String flag= data.getPbHolderFlag() == null ? "No" : data.getPbHolderFlag();
                    data.setPbHolderFlag(flag);
                    detail.enterPBHolderDetails(data.getPbHolderFlag(), data).run();
                    break;
            }

        }
        return this;
    }

    public YourOrder addMultipleProductIrish(PurchaseModel data) {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("your-order"));
        for (int i = 1; i < productsIrish.size(); i++) {
            switch (productsIrish.get(i)) {
                case "Duaisbhannaí":
                case "Banna Dlúthpháirtíochta Náisiúnta":
                case "Bannaí Coigiltis":
                case "Cairteacha Coigiltis":
                    addProductToCart(productsIrish.get(i));
                    if (productsIrish.indexOf("Duaisbhannaí mar Bhronntanas") == 0) {
                        if (i == 1) journeyHandler.getJourney(data.getJourney(), data).run();
                    }
                    break;
                case "Duaisbhannaí mar Bhronntanas":
                    addProductToCart(productsIrish.get(i));
                    String flag= data.getPbHolderFlag() == null ? "No" : data.getPbHolderFlag();
                    data.setPbHolderFlag(flag);
                    detail.enterPBHolderDetailsIrish(data.getPbHolderFlag(), data).run();
                    break;
            }

        }
        return this;
    }

    public YourOrder addMultipleProductIrishSignIn(PurchaseModel data) {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("your-order"));
        for (int i = 1; i < productsIrish.size(); i++) {
            switch (productsIrish.get(i)) {
                case "Duaisbhannaí":
                case "Banna Dlúthpháirtíochta Náisiúnta":
                case "Bannaí Coigiltis":
                case "Cairteacha Coigiltis":
                    addProductToCart(productsIrish.get(i));
                    if (productsIrish.indexOf("Duaisbhannaí mar Bhronntanas") == 0) {
                        if (i == 1) journeyHandler.getJourney(data.getJourney(), data).run();
                    }
                    break;
                case "Duaisbhannaí mar Bhronntanas":
                    addProductToCart(productsIrish.get(i));
                    String flag= data.getPbHolderFlag() == null ? "No" : data.getPbHolderFlag();
                    data.setPbHolderFlag(flag);
                    detail.enterPBHolderDetailsIrish(data.getPbHolderFlag(), data).run();
                    break;
            }

        }
        return this;
    }





// Shan added for SignIn Joint Same user. 102TC
    public YourOrder addMultipleProduct1(PurchaseModel data) {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("your-details"));
        for (int i = 1; i < products.size(); i++) {
            switch (products.get(i)) {
                case "Prize Bonds":
                case "National Solidarity Bonds":
                case "Savings Bonds":
                case "Savings Certificates":
                    addProductToCart(products.get(i));
                    if (products.indexOf("Prize Bonds as a Gift") == 0) {
                        if (i == 1) journeyHandler.getJourney(data.getJourney(), data).run();
                    }
                    break;
                case "Prize Bonds as a Gift":
                    addProductToCart(products.get(i));
                    String flag= data.getPbHolderFlag() == null ? "No" : data.getPbHolderFlag();
                    data.setPbHolderFlag(flag);
                    detail.enterPBHolderDetails(data.getPbHolderFlag(), data).run();
                    break;
            }

        }
        return this;
    }






    public YourOrder assignDefaultValues(List<String> product, List<String> amount,PurchaseModel data) {
        products = product;
        amounts = new ArrayList<>(Optional.ofNullable(amount).orElse(new ArrayList<>())); // Create a new mutable ArrayList

        Function<Integer, String> getAmountOrDefault = i -> amounts.size() > i ? amounts.get(i) :
                (products.get(i).equals("Prize Bonds") || products.get(i).equals("Prize Bonds as a Gift") ? "4" : "50");


//        List<String> orderedAmounts = new ArrayList<>();
        cartMap = IntStream.range(0, products.size())
                .boxed()
                .collect(Collectors.toMap(
                        products::get,
                        i -> {
                            String calculatedAmount = getAmountOrDefault.apply(i);
                            amounts.add(calculatedAmount);
                            return calculatedAmount;
                        }
                ));
        data.setAmount(amounts);
        return this;
    }


    public YourOrder assignDefaultValuesIrish(List<String> product, List<String> amount,PurchaseModel data) {
        productsIrish = product;
        amountsIrish = new ArrayList<>(Optional.ofNullable(amount).orElse(new ArrayList<>())); // Create a new mutable ArrayList

        Function<Integer, String> getAmountOrDefault = i -> amountsIrish.size() > i ? amountsIrish.get(i) :
                (productsIrish.get(i).equals("Duaisbhannaí") || productsIrish.get(i).equals("Duaisbhannaí mar Bhronntanas") ? "4" : "50");


//        List<String> orderedAmounts = new ArrayList<>();
        cartMap = IntStream.range(0, productsIrish.size())
                .boxed()
                .collect(Collectors.toMap(
                        productsIrish::get,
                        i -> {
                            String calculatedAmount = getAmountOrDefault.apply(i);
                            amountsIrish.add(calculatedAmount);
                            return calculatedAmount;
                        }
                ));
        data.setAmount(amountsIrish);
        return this;
    }



//Shan. Delete if not used
    public YourOrder assignDefaultValuesCheck(List<String> product,List<String> amount,PurchaseModel data) {
        products = product;
        amounts = new ArrayList<>(Optional.ofNullable(amount).orElse(new ArrayList<>())); // Create a new mutable ArrayList

       Function<Integer, String> getAmountOrDefault = i -> amounts.size() > i ? amounts.get(i) :
                (products.get(i).equals("Prize Bonds") || products.get(i).equals("Prize Bonds as a Gift") ? "4" : "50");



//        List<String> orderedAmounts = new ArrayList<>();
        cartMap = IntStream.range(0, products.size())
                .boxed()
                .collect(Collectors.toMap(
                        products::get,
                        i -> {
                            String calculatedAmount = getAmountOrDefault.apply(i);
                            amounts.add(calculatedAmount);
                            amounts.clear();
                            return calculatedAmount;

                        }
                ));
        amounts.clear();
        return this;
    }




    @SneakyThrows
    public YourOrder enterAmount() {

        ExtentCucumberAdapter.addTestStepLog("<font color='cyan'><b>Added these product for purchase</b></font>");
       By increment = By. xpath("//span[@class='incrementor__up']");
       By pbValue=By.xpath("//input[@class='incrementor__value js-input-value']");


        for (int i = 0; i < products.size(); i++) {

            List<WebElement> webElements = webUtil.getElements(yourOrderAmtList);

            if (products.size() != amounts.size()) {
                amounts.add(i, cartMap.get(products.get(i)));

            }

            if (!products.get(i).equals("Prize Bonds as a Gift")) {
                webUtil.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElements.get(i)));
                WebElement element = webElements.get(i).findElement(fldAmount);
                webUtil.scroll(element);
                element.clear();
                element.sendKeys(amounts.get(i));
                webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(element, "value", amounts.get(i)));
            }else{
                int clik;
                int j=4;
               clik = Integer.parseInt(amounts.get(i));
                System.out.println(clik);
//                int s =0 ;

                while (j < clik) {
//                   try{
////                       Thread.sleep(5000);
//                       s++;
//                       System.out.println(s);
                       webUtil.scrollToView(increment);
                        webUtil.clickLog(increment, "Increment PB gift");
//                       }
//                   catch(Exception e){
//                     System.out.println("Failed at Increment" + e);


//                   }
                        j++;
                }
                Thread.sleep(1000);
            }
            validateProductAndAmount(products.get(i), amounts.get(i), i);
        }
        return this;

    }


    @SneakyThrows
    public YourOrder enterAmountIrish() {

        ExtentCucumberAdapter.addTestStepLog("<font color='cyan'><b>Added these product for purchase</b></font>");
        By increment = By. xpath("//span[@class='incrementor__up']");
        By pbValue=By.xpath("//input[@class='incrementor__value js-input-value']");


        for (int i = 0; i < productsIrish.size(); i++) {

            List<WebElement> webElements = webUtil.getElements(yourOrderAmtList);

            if (productsIrish.size() != amountsIrish.size()) {
                amountsIrish.add(i, cartMap.get(productsIrish.get(i)));

            }

            if (!productsIrish.get(i).equals("Duaisbhannaí mar Bhronntanas")) {
                webUtil.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElements.get(i)));
                WebElement element = webElements.get(i).findElement(fldAmount);
                webUtil.scroll(element);
                element.clear();
                element.sendKeys(amountsIrish.get(i));
                webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(element, "value", amountsIrish.get(i)));
            }else{
                int clik;
                int j=4;
                clik = Integer.parseInt(amountsIrish.get(i));
                System.out.println(clik);
//                int s =0 ;

                while (j < clik) {
//                   try{
////                       Thread.sleep(5000);
//                       s++;
//                       System.out.println(s);
                    webUtil.scrollToView(increment);
                    webUtil.clickLog(increment, "Increment PB gift");
//                       }
//                   catch(Exception e){
//                     System.out.println("Failed at Increment" + e);


//                   }
                    j++;
                }
                Thread.sleep(1000);
            }

            validateProductAndAmountIrish(productsIrish.get(i), amountsIrish.get(i), i);
        }
        return this;

    }






    //Shan. ENter amount for PB. Remove the validation
    public YourOrder enterAmount1() {

        ExtentCucumberAdapter.addTestStepLog("<font color='cyan'><b>Added these product for purchase</b></font>");
        By increment = By. xpath("//span[@class='incrementor__up']");
        By pbValue=By.xpath("//input[@class='incrementor__value js-input-value']");


        for (int i = 0; i < products.size(); i++) {

            List<WebElement> webElements = webUtil.getElements(yourOrderAmtList);

            if (products.size() != amounts.size()) {
                amounts.add(i, cartMap.get(products.get(i)));

            }

            if (!products.get(i).equals("Prize Bonds as a Gift")) {
                webUtil.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElements.get(i)));
                WebElement element = webElements.get(i).findElement(fldAmount);
                webUtil.scroll(element);
                element.clear();
                element.sendKeys(amounts.get(i));
                webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(element, "value", amounts.get(i)));
            }else{
                int clik;
                int j=4;
                clik = Integer.parseInt(amounts.get(i));
                System.out.println(clik);
//                int s =0 ;

                while (j < clik) {
//                   try{
////                       Thread.sleep(5000);
//                       s++;
//                       System.out.println(s);
                    webUtil.scrollToView(increment);
                    webUtil.clickLog(increment, "Increment PB gift");
//                       }
//                   catch(Exception e){
//                     System.out.println("Failed at Increment" + e);


//                   }
                    j++;

                }


            }


//            validateProductAndAmount(products.get(i), amounts.get(i), i);
        }
        return this;

    }



    private void validateProductAndAmount(String product, String amount, int i) {
        List<WebElement> prdctRow = webUtil.getElements(yourOrderAmtList);

        amount = product.contains("Prize Bonds") ? "€" + String.format("%,.2f", Double.parseDouble(amount) * 6.25) : amount;

        String[] details = productMap.get(product);

        if (details != null) {
            if (details[0] != null) webUtil.verifyText(prdctRow, issueText, "", details[0], i, true);
            webUtil.verifyText(prdctRow, productText, "", product, i, true);
            if (product.contains("Prize Bonds"))
                webUtil.verifyText(prdctRow, pbAmountText, "", amount, i, Boolean.parseBoolean(details[1]));
            else
                webUtil.verifyText(prdctRow, amountText, "value", amount, i, Boolean.parseBoolean(details[1]));
        }
    }

    private void validateProductAndAmountIrish(String product, String amount, int i) {
        List<WebElement> prdctRow = webUtil.getElements(yourOrderAmtList);

        amount = product.contains("Duaisbhannaí") ? "€" + String.format("%,.2f", Double.parseDouble(amount) * 6.25) : amount;

        String[] details = productMap.get(product);

        if (details != null) {
            if (details[0] != null) webUtil.verifyText(prdctRow, issueText, "", details[0], i, true);
            webUtil.verifyText(prdctRow, productText, "", product, i, true);
            if (product.contains("Duaisbhannaí"))
                webUtil.verifyText(prdctRow, pbAmountText, "", amount, i, Boolean.parseBoolean(details[1]));
            else
                webUtil.verifyText(prdctRow, amountText, "value", amount, i, Boolean.parseBoolean(details[1]));
        }
    }




    @SneakyThrows
    public Review clickContReviewCheck() {
        webUtil.scrollToView(btnContinueToReview);
        webUtil.scrollToView(Remove);
       webUtil.javascriptClick(Remove);
        Thread.sleep(2000);
        return new Review(driver);

    }

    public Review clickContReview() {
        webUtil.scrollToView(btnContinueToReview);
        webUtil.clickLog(btnContinueToReview, "Continue to review button on your order page");
        return new Review(driver);
    }

    //Shan 15.7.25
    public Review clickContOrder() {
        webUtil.scrollToView(btnContinueToOrder);
        webUtil.clickLog(btnContinueToOrder, "Continue to Order button on your Details page");
        return new Review(driver);
    }

    public YourOrder verifyProducts() {
        if (webUtil.isElementVisible(ftProduct, 20)) {
            String txtHdrYourOrder = "You have no products selected, please add them below";
            webUtil.gettextlog(hdrYourOrder, String::equals, txtHdrYourOrder, "Product Page Header");
            productList = webUtil.getDriver().findElements(ftProduct);
        } else
            throw new ExceptionUtils("Product not displayed");

        IntStream.range(0, lblProductList.size()).forEach(index -> {
            String expectedValue = lblProductList.get(index);
            Consumer<Integer> consumer = consumerMap.get(expectedValue);
            if (consumer == null) throw new IllegalArgumentException("Product is not available");

            consumer.accept(index);
        });
        return this;
    }

    public YourOrder verifyProductsIrish() {
        if (webUtil.isElementVisible(ftProduct, 20)) {
            String txtHdrYourOrder = "Níl aon táirgí roghnaithe agat go fóill, cuir isteach iad thíos, le do thoil";
            webUtil.gettextlog(hdrYourOrder, String::equals, txtHdrYourOrder, "Product Page Header");
            productListIrish = webUtil.getDriver().findElements(ftProduct);
        } else
            throw new ExceptionUtils("Product not displayed");

        IntStream.range(0, lblProductListIrish.size()).forEach(index -> {
            String expectedValue = lblProductListIrish.get(index);
            Consumer<Integer> consumer = consumerMapIrish.get(expectedValue);
            if (consumer == null) throw new IllegalArgumentException("Product is not available");
            consumer.accept(index);
        });
        return this;
    }


    public YourOrder verifyProductsIrishSignIn() {
        if (webUtil.isElementVisible(ftProduct, 20)) {
            String txtHdrYourOrder = "Cuir níos mó le d’ordú";
            webUtil.gettextlog(hdrYourOrder, String::equals, txtHdrYourOrder, "Product Page Header");
            productListIrish = webUtil.getDriver().findElements(ftProduct);
        } else
            throw new ExceptionUtils("Product not displayed");

        IntStream.range(0, lblProductListIrish.size()).forEach(index -> {
            String expectedValue = lblProductListIrish.get(index);
            Consumer<Integer> consumer = consumerMapIrish.get(expectedValue);
            if (consumer == null) throw new IllegalArgumentException("Product is not available");
            consumer.accept(index);
        });
        return this;
    }


    private void verifyProduct(Integer index) {
        String expectedValue = lblProductList.get(index);
        webUtil.verifyText(productList, productName, "", expectedValue, index, true);
    }

    private void verifyProductIrish(Integer index) {
        String expectedValue = lblProductListIrish.get(index);
        webUtil.verifyText(productListIrish, productName, "", expectedValue, index, true);
    }

    private void verifyNationalSolidarityBonds(Integer index) {
        String expectedNSBIssue = "10 year Issue 9";
        verifyProduct(index);
        verifyBondIssue(index, expectedNSBIssue);
    }

    private void verifyNationalSolidarityBondsIrish(Integer index) {
        String expectedNSBIssue = "10 mBliana 9ú Eisiúint";
        verifyProductIrish(index);
        verifyBondIssueIrish(index, expectedNSBIssue);
    }

    private void verifySavingsBonds(Integer index) {
        String expectedSBIssue = "3 year Issue 18";
        verifyProduct(index);
        verifyBondIssue(index, expectedSBIssue);
    }

    private void verifySavingsBondsIrish(Integer index) {
        String expectedSBIssue = "3 Bliana 18ú Eisiúint";
        verifyProductIrish(index);
        verifyBondIssueIrish(index, expectedSBIssue);
    }


    private void verifySavingsCertificates(Integer index) {
        String expectedSCIssue = "5 year Issue 25";
        verifyProduct(index);
        verifyBondIssue(index, expectedSCIssue);
    }

    private void verifySavingsCertificatesIrish(Integer index) {
        String expectedSCIssue = "5 Bliana 25ú Eisiúint";
        verifyProductIrish(index);
        verifyBondIssueIrish(index, expectedSCIssue);
    }


    private void verifyBondIssue(Integer index, String expectedIssue) {
        String bondIssueActual = productList.get(index).findElement(txtIssue).getText();
        if (bondIssueActual != null) {
            webUtil.verifyText(productList, txtIssue, "", expectedIssue, index, true);
        }
    }

    private void verifyBondIssueIrish(Integer index, String expectedIssue) {
        String bondIssueActual = productListIrish.get(index).findElement(txtIssue).getText();
        if (bondIssueActual != null) {
            webUtil.verifyText(productListIrish, txtIssue, "", expectedIssue, index, true);
        }
    }



    public Review errorYourOrder(List<String> amount, PurchaseModel purchaseModel) {
        products=purchaseModel.getProduct();
        boolean prize_bonds_for_me = products.indexOf("Prize Bonds") == 0;
        String Min = prize_bonds_for_me ? "3" : "49";
        String Max = prize_bonds_for_me && purchaseModel.getJourney().contains("Joint") ? "80001"
                : prize_bonds_for_me && !purchaseModel.getJourney().contains("Joint") ? "40001"
                : products.indexOf("Prize Bonds for Me") != 0 && !purchaseModel.getJourney().contains("Joint") ? "120001" : "240001";
        String msg = prize_bonds_for_me && !purchaseModel.getJourney().contains("Joint") ? "Minimum: 4 Maximum: 40000"
                : prize_bonds_for_me && purchaseModel.getJourney().contains("Joint") ? "Minimum: 4   Maximum: 80000"
                : products.indexOf("Prize Bonds for Me") != 0 && !purchaseModel.getJourney().contains("Joint") ? "Minimum: €50.00 Maximum: €120,000.00"
                : "Minimum: €50.00 Maximum: €240,000.00";
        for (String key : amount) {
            switch (key) {
                case "Blank":
                    webUtil.sendKeys(By.xpath("//tr[@class='js-ec-your-order-row']//td//input"), "");
//                    clickContReview();
                    var element=driver.findElement(By.xpath("//tr[@class='js-ec-your-order-row']//td//input"));
                    element.clear();
                    element.sendKeys("", Keys.ENTER);
                    webUtil.waitForPageLoaded();
                    String error = driver.findElement(RelativeLocator.with(By.xpath("//span[@class='error-validation']")).below(By.xpath("//td//input"))).getText();
                    webUtil.CompareString(error, String::equals, msg);
                    break;
                case "Min":
                    webUtil.sendKeys(By.xpath("//tr[@class='js-ec-your-order-row']//td//input"), Min);
                    clickContReview();
                    webUtil.waitForPageLoaded();
                    webUtil.gettextlog(RelativeLocator.with(By.xpath("//span[@class='error-validation']")).below(By.xpath("//td//input")), String::equals, msg);

                    break;
                case "Max":
                    webUtil.sendKeys(By.xpath("//tr[@class='js-ec-your-order-row']//td//input"), Max);
                    clickContReview();
                    webUtil.waitForPageLoaded();
                    webUtil.gettextlog(RelativeLocator.with(By.xpath("//span[@class='error-validation']")).below(By.xpath("//td//input")), String::equals, msg);
                    break;

            }
        }

              return  assignDefaultValues(purchaseModel.getProduct(), purchaseModel.getAmount(),purchaseModel)
                .addMultipleProduct(purchaseModel)
                .enterAmount()
                .clickContReview();
    }


}

