package com.cucumbercraft.POMPages.DigitalRegistration;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.framework.*;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TermsOfService  extends MasterStepDefs {

    WebDriver driver;
    WebDriverUtil webUtil;
//    Context testContext;

    public TermsOfService(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private TermsOfService termsPage;
    Excelutils excelutils = new Excelutils();
    private final String JSON_FILE_PATH = Settings.getInstance().getProperty("JSON.Content.DigitalRegistration");
    private final String EXCEL_FILE_PATH= FrameworkConstants.getExcelLocationContentSliderModalFooterArticles();



    private final By TOSHeader = By.xpath("//div[@class='medium-8 columns']/h2");
    private final By Paragraph1 = By.xpath("//div[@class='medium-8 columns']//p[1]");
    private final By Paragraph2 = By.xpath("//div[@class='medium-8 columns']//p[2]");
    private final By checkboxLocator = By.xpath("//div[@class='consent-item']//input[@type='checkbox']");
    private final By TermCondition = By.xpath("//label[@class='consent-text' and ./a[text()='Terms and Conditions']]");
    private final By DataProctection = By.xpath("//label[@class='consent-text' and ./a[text()='Data Protection Statement']]");
    private final By AcceptTC = By.xpath(" //label[@for='biometric' and @class='consent-text']");
    private final By nextBtn = By.id("btnNext");


    public String getExpectedText(String key) {  // Shan
     // Get the flag value from the glob prop
        String source = properties.getProperty("ContentSource");

        switch (source.toUpperCase()) {
            case "JSON":
                return getValueFromJson(key);
            case "EXCEL":
                return getValueFromExcel(key);
            default:
                Assert.fail("Invalid ContentSource flag: " + source + " Must be 'json/Excel.");
                return null;
        }
    }


    private String getValueFromExcel1(String key) {

        data = excelutils.getData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), "DigitalRegistrationContent", key, TestData.class,""); // Try Shan
//        webUtil.gettextlog(Paragraph1, String::equals, "Congratulations! You have successfully reset your password. Sign in below");
        webUtil.gettextlog(Paragraph1, String::equals, data.getExpectedResult());  //Try Exp from Excel


        String path = FrameworkConstants.getExcelLocationContentSliderModalFooterArticles();
        String sheetName = config.getDigitalRegistrationSheetName();
        data = excelutils.getData(path, sheetName, key, TestData.class, "getTestcaseName");
        if (key.equals("TermsOfServiceContent")) {
            return "This content is from the Excel file.";
        } else {
            Assert.fail("Key '" + key + "' not found in the Excel data.");
            return null;
        }
    }

    private String getValueFromExcel(String key) {
        String sheetName = "DigitalRegistrationContent";
        Excelutils excelUtils = new Excelutils();

        List<TestData> rows = excelUtils.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);

        if (rows == null || rows.isEmpty()) {
            Assert.fail("Excel sheet '" + sheetName + "' is empty or could not be read.");
            return null;
        }

        TestData matchingRow = null;
        for (TestData row : rows) {

            if (row != null && key.equalsIgnoreCase(row.getKey())) {
                matchingRow = row;
                break;
            }
        }

        if (matchingRow == null) {
            Assert.fail("Key '" + key + "' not found in the 'Key' column of the Excel data.");
            return null;
        }

        String expectedContent = matchingRow.getExpectedValue();

        if (expectedContent != null) {
            return expectedContent; // Returns "Hi this is Shankar"
        } else {
            Assert.fail("Key '" + key + "' found, but the corresponding 'Value' field is null/empty.");
            return null;
        }
    }



    private String getValueFromJson(String key) {
        String jsonFilePath = properties.getProperty("JSON.Content.DigitalRegistration");
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(new File(jsonFilePath));
            JsonNode expectedNode = rootNode.get(key);

            if (expectedNode != null) {
                return expectedNode.asText();
            } else {
                throw new IllegalArgumentException("Key not found in JSON: " + key);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to read JSON file at " + jsonFilePath + ": " + e.getMessage());
            return null;
        }
    }


    public void TermsOfService() throws Exception {
        try {

            if ((webUtil.isElementDisplayed(TOSHeader)) &&
                    (webUtil.getText(TOSHeader).equals("Terms of Service"))) {
                log.info("Successfully landed in Terms of Service Page");
            } else {
                log.info("Not landed in Terms of Service Page");
            }
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Successfully landed in Terms of Service Page");

            String expectedParaText = getExpectedText("TermsOfServiceContent");
            webUtil.gettextlog(Paragraph1, String::equals, expectedParaText);
            String expectedParaText_1 = getExpectedText("TermsOfServiceContent1");
            webUtil.gettextlog(Paragraph2, String::equals, expectedParaText_1);

//            Below is for Json
//            String expectedParaText = getExpectedText("TermsOfServiceContent");
//            String expectedParaText_1 = getExpectedText("TermsOfServiceContent1");
//            webUtil.gettextlog(Paragraph1, String::equals, expectedParaText);
//            webUtil.gettextlog(Paragraph2, String::equals, expectedParaText_1);

        } catch (NullPointerException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());

        }
    }


    public void CheckBox() throws Exception {
        try {
            String expectedTandC = getExpectedText("TermsAndCondition");
            String expectedDataProtection = getExpectedText("DataProtection");
            String expectedAcceptStatement = getExpectedText("AcceptStatement");
            webUtil.gettextlog(TermCondition, String::equals, expectedTandC);
            webUtil.gettextlog(DataProctection, String::equals, expectedDataProtection);
            webUtil.gettextlog(AcceptTC, String::equals, expectedAcceptStatement);

            List<WebElement> checkboxes = driver.findElements(checkboxLocator);

            if (checkboxes.isEmpty()) {
                System.out.println("No checkboxes found with the locator: " + checkboxLocator);
                return;
            }

            checkboxes.stream()
                    .filter(checkbox -> !checkbox.isSelected())
                    .forEach(WebElement::click);
            Thread.sleep(2000);

        } catch (NoSuchElementException e){
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }


    public void clickNextBtn() throws Exception {
        try {
            webUtil.gettextlog(nextBtn, String::equalsIgnoreCase, "Next");
            webUtil.click(nextBtn);
        } catch (NoSuchElementException e) {
            throw new ExceptionUtils("Email verification failed due to null data: " + e.getMessage());
        }
    }

}
