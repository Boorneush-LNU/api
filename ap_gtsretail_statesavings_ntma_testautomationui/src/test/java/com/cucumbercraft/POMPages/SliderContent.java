package com.cucumbercraft.POMPages;

import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.ExpeditedReg.Expedited_SecurityPage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.framework.WebDriverUtil;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class handles the content and validation of various sliders in the application.
 * It provides methods to validate slider content against expected values from an Excel file.
 */
public class SliderContent {
    public static final By CLOSE_SLIDER = By.xpath(".//button[@aria-label='Close modal']");
    /**
     * Locator for the header element using h4 tag
     */
    private static final By HDR4 = By.tagName("h4");
    private static final By PARAGRAPH2 = By.xpath(".//p[2]");
    private final WebDriver driver = DriverManager.getWebDriver();
    private static final By LABEL = By.tagName("label");
    private Expedited_SecurityPage expeditedSecurityPage;
    /**
     * Locator for the old password label element
     */
    private static final By OLD_PASSWORD = By.cssSelector("label[for='txtOldPassword']");

    /**
     * Locator for the new password label element
     */
    private static final By NEW_PASSWORD = By.cssSelector("label[for='txtNewPassword']");

    /**
     * Locator for the paragraph element containing password requirements heading
     */
    private static final By PARAGRAPH = By.tagName("p");
    public static final By FORGOT_PASSWORD_PRIMARY_BUTTON = By.xpath(".//div[contains(@class,'btn--container')]//input[2]");
    public static final By FORGOT_PASSWORD_SECONDARY_BUTTON = By.xpath(".//div[contains(@class,'btn--container')]//input[1]");
    /**
     * Locator for list items containing password requirements
     */  // //*[@id="sectionResend"]/ul    By.tagName("li");
    private static final By LIST_TAG = By.xpath("//*[@id=\"sectionResend\"]/ul");


    private static final By LIST_TAG1 = By.xpath("//*[@id=\"sectionResend\"]/ul/li[1]");
    private static final By LIST_TAG2 = By.xpath("//*[@id=\"sectionResend\"]/ul/li[2]");
    private static final By LIST_TAG3 = By.xpath("//*[@id=\"sectionResend\"]/ul/li[3]");

    private static final By Paragraph = By.xpath("//*[@id=\"sectionResend\"]/p[2]");
    //section[@id="sectionResendEmailPassword"]/ul
//    private static final By LIST_TAG = By.xpath("//section[@id=\"sectionResendEmailPassword\"]/ul");
    private static final By SECONDARY_BUTTON = By.xpath(".//div[contains(@class,'btn--container')]//button[1]");

    public static final By PRIMARY_BUTTON = By.xpath(".//div[contains(@class,'btn--container')]//button[2]");

    public static final By TEXT_FIELD = By.tagName("input");

    private static final By LINK = By.tagName("a");

    /**
     * Utility class for web element interactions
     */
    private final WebDriverUtil webUtil;

    /**
     * Utility class for Excel file operations
     */
    private final Excelutils excelutils;

    /**
     * Configuration properties for the application
     */
    private PropertyConfig config;

    private ProfileAndSettingsPg profileAndSettingsPg;

    private StateSavingsDashboardPage dashboardPage;

    private Security_Page securityPage;

    private SignInPg sign;

    /**
     * Constructs a new SliderContent instance.
     *
     * @param driver WebDriver instance to be used for browser interactions
     */
    public SliderContent(WebDriver driver) {
        this.webUtil = new WebDriverUtil(driver);
        this.excelutils = new Excelutils();
        this.config = ConfigFactory.create(PropertyConfig.class);
        this.profileAndSettingsPg = new ProfileAndSettingsPg(driver);
        this.dashboardPage = new StateSavingsDashboardPage(driver);
        this.securityPage = new Security_Page(driver);
        this.sign = new SignInPg(driver);
    }

    /**
     * Retrieves slider configuration regstrnData from Excel file.
     *
     * @param sliderName Name of the slider to retrieve configuration for
     * @return TestData object containing slider configuration
     */
    private TestData retrieveSliderConfigurationFromExcel(String sliderName) {
        return excelutils.getData(
//                config.getSliderContentExcelPath(),
                FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(),   // Merged Slider/Modal & K13Content
                "Sliders",
                sliderName,
                TestData.class,
                "getSliderName"

        );
    }





    /**
     * Parses slider content into a key-value map.
     * Each line in the slider content is expected to be in "key:value" format.
     *
     * @param sliderName Name of the slider whose content needs to be parsed
     * @return Map containing parsed key-value pairs from slider content
     */
    private Map<String, String> parseSliderContentByKeyValue(String sliderName) {
        return Arrays.stream(retrieveSliderConfigurationFromExcel(sliderName)
                        .getSliderContent().split("\n"))
                .map(String::trim)
                .map(line -> line.split(":", 2))
                .filter(parts -> parts.length == 2)
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> parts[1].trim()
                ));
    }


    /**
     * Extracts the list of password requirements from the slider element.
     *
     * @param rootElement Root element of the slider containing the requirements
     * @return List of requirement texts
     */
    private List<String> extractPasswordRequirementsList(WebElement rootElement) {
        return rootElement.findElements(LIST_TAG)
                .stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll("\n", ""))
                .collect(Collectors.toList());
    }

    /**
     * Validates the list of password requirements against expected content.
     *
     * @param actualRequirements List of actual requirement texts from UI
     * @param expectedContent    Map containing expected requirement texts
     */
    private void validatePasswordRequirementsList(List<String> actualRequirements, Map<String, String> expectedContent) {
        for (int i = 1; i <= actualRequirements.size(); i++) {
            String expectedReq = expectedContent.get("Password Requirement " + i);
            webUtil.CompareString(
                    actualRequirements.get(i - 1),
                    String::equals,
                    expectedReq,
                    "Password requirement " + i
            );
        }
    }



    /**
     * Validates the text content of a specific element against expected value.
     *
     * @param root      Root element containing the target element
     * @param locator   Locator to find the target element
     * @param expected  Expected text content
     * @param fieldName Name of the field for reporting purposes
     */
    private void validateElementTextContent(WebElement root, By locator, String expected, String fieldName) {
        String actual = root.findElement(locator).getText().trim();
//        webUtil.CompareString(actual, String::equals, expected, fieldName);
        webUtil.CompareString(actual, String::contains, expected, fieldName);
    }


    private void validateElementTextContent1(WebElement root, By locator, String fieldName) {
        String actual = root.findElement(locator).getText().trim();
//        webUtil.CompareString(actual, String::equals, expected, fieldName);
        webUtil.CompareString(actual, String::contains,  fieldName);
    }

    /**
     * Enters the verification code into the input field of the slider.
     *
     * @param verCode
     */
    public SliderContent enterVerCode(String verCode) {
        WebElement element = webUtil.waitUntilElementVisible(securityPage.verificationCodeSlider, 20).findElement(By.tagName("input"));
        element.sendKeys(verCode);
        return this;

    }




    public void enterTextField(By element,String text) {
        WebElement fieldElement=webUtil.waitUntilElementVisible(element,10).findElement(TEXT_FIELD);
        webUtil.scroll(fieldElement);
                fieldElement.sendKeys(text);
    }

    /**
     * Clicks the "Confirm" button on the verification code change password slider.
     */
    public void clickConfirmButtonVerCodeChangePassword() {
        webUtil.click(profileAndSettingsPg.confirmChangePasswordSecurityCode);
    }

    /**
     * Clicks the "Confirm" button on the verification code change email slider.
     */
    public void enterExistingEmail(String email) {
        webUtil.sendKeys(TEXT_FIELD, email);
    }

    /**
     * Clicks the "Confirm" button on the verification code change email slider.
     */
    public void clickPrimayBtn(WebElement element) {
        element.findElement(PRIMARY_BUTTON).click();
    }

    /**
     * Clicks the "Cancel" button on the verification code slider.
     */
    public void clickSecondaryBtn(WebElement element) {
        element.findElement(SECONDARY_BUTTON).click();
    }

    public void inputTextField(WebElement element,String text) {
        element.findElement(TEXT_FIELD).sendKeys(text);
    }

    /**
     * Validates the content of the password change slider against expected values.
     * Compares headers, labels, and password requirements with expected content
     * from configuration.
     *
     * @param sliderName Name of the slider to validate
     */
    public void validatePasswordSliderContentAgainstExpected(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(By.id("sectionChangePassword"), 20);
        TestData data = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, data.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, OLD_PASSWORD, expectedContent.get("Current Password Label"), "Current Password Label");
        validateElementTextContent(sliderRootElement, NEW_PASSWORD, expectedContent.get("New Password Label"), "New Password Label");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Password Requirements Heading"), "Password Requirements Heading");

        // Validate password requirements
        List<String> actualRequirements = extractPasswordRequirementsList(sliderRootElement);
        validatePasswordRequirementsList(actualRequirements, expectedContent);

        // Validate confirm button text
        String txtConfirmBtn = sliderRootElement.findElement(profileAndSettingsPg.btnConfirmChangePassword).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Confirm Button");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(profileAndSettingsPg.btnCancelChangePassword).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");

    }


    /**
     * Validates the content of the verification code slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName   Name of the slider to validate
     * @param mobileNumber Mobile number to be displayed in the slider
     */
    public void validateVerificationCodeSlider(String sliderName, String mobileNumber) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(securityPage.verificationCodeSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        String last4digits = mobileNumber.substring(mobileNumber.length() - 4);
        String txtEnterSecCode = expectedContent.get("Verification Code Description").replaceAll("\\*{4}", last4digits);
        validateElementTextContent(sliderRootElement, PARAGRAPH, txtEnterSecCode, "Verification Code Description");


        // Validate confirm button text
        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Confirm Button");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");
    }

//Shan
    public void validateVerificationCodeSliderReceive(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(securityPage.VerificationSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, sliderData.getSliderContent(), "Paragraph");
        validateElementTextContent1(sliderRootElement,LIST_TAG , "Allow 1-2 minutes for the code to arrive");


        // Validate confirm button text
        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, sliderData.getSliderPrimaryButton(), "Resend Button");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, sliderData.getSliderSecondaryButton(), "Cancel Button");
    }






    /**
     * Validates the content of the change email slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName Name of the slider to validate
     */
    public void validateChangeEmailSlider(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.changeEmailSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, LABEL, expectedContent.get("Change Email Label"), "Current Password Label");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Change Email Description"), "Change Email Description");

        // Validate confirm button text
        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Confirm Button");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");
    }


    /**
     * Validates the content of the check inbox slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName Name of the slider to validate
     */
    public void validateCheckInboxSlider(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.checkInboxSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Check Inbox Description"), "Check Inbox Description");

        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Primary Button"), "Close Button");


    }

    /**
     * Validates the content of the change name slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName Name of the slider to validate
     */
    public void validateChangeNameSlider(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.changeFullNameSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        List<String> descriptionList = sliderRootElement.findElements(PARAGRAPH)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
        for (int i = 0; i < descriptionList.size(); i++) {
            String expectedReq = expectedContent.get("Change Name Description " + (i + 1));
            webUtil.CompareString(
                    descriptionList.get(i),
                    String::equals,
                    expectedReq,
                    "Change Email Description " + (i + 1)
            );

        }

        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Primary Button"), "Close Button");
    }

    /**
     * Validates the content of the change mobile number slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName Name of the slider to validate
     */
    public void validateChangeMobileNumberSlider(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.changMobileNumberSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        List<String> descriptionList = sliderRootElement.findElements(PARAGRAPH)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
        for (int i = 0; i < descriptionList.size(); i++) {
            String expectedReq = expectedContent.get("Change Number Description " + (i + 1));
            webUtil.CompareString(
                    descriptionList.get(i),
                    String::equals,
                    expectedReq,
                    "Change Email Description " + (i + 1)
            );

        }
        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Primary Button"), "Close Button");
    }

    /**
     * Validates the content of the change address slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName Name of the slider to validate
     */
    public void validateChangeAddressSlider(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.changAddressSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        List<String> descriptionList = sliderRootElement.findElements(PARAGRAPH)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
        for (int i = 0; i < descriptionList.size(); i++) {
            String expectedReq = expectedContent.get("Change Address Description " + (i + 1));
            webUtil.CompareString(
                    descriptionList.get(i),
                    String::equals,
                    expectedReq,
                    "Change Address Description " + (i + 1)
            );

        }
        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Primary Button"), "Close Button");
    }


    /**
     * Validates the content of the "Show SSCN Code" slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName Name of the slider to validate
     * @param sscnCode   SSCN code to be displayed in the slider
     */
    public void validateShowSSCNCodeSlider(String sliderName, String sscnCode) {

        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.showSCCNSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, By.id("SCNTitleTablete"), sliderData.getSliderHdr(), "Header");
        List<String> descriptionList = sliderRootElement.findElements(PARAGRAPH)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
        for (int i = 0; i < descriptionList.size(); i++) {
            String expectedReq = expectedContent.get("Show SSCN Description " + (i + 1));
            webUtil.CompareString(
                    descriptionList.get(i),
                    String::equals,
                    expectedReq,
                    "Show SSCN Description " + (i + 1)
            );

        }
        webUtil.gettextlog(By.id("sscnDescription"), String::equals, "SSCN: " + sscnCode, "SSCN Code");
        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Primary Button"), "Cancel Button");
    }

    /**
     * Validates the content of the "Add Holding" slider against expected values.
     * Compares headers, labels, and descriptions with expected content from configuration.
     *
     * @param sliderName Name of the slider to validate
     */
    public void validateAddHoldingSlider(String sliderName) {

        WebElement sliderRootElement = webUtil.waitUntilElementVisible(dashboardPage.addHoldingSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, LABEL, expectedContent.get("Add Holding Sub Header"), "Add Holding Sub Header");
        validateElementTextContent(sliderRootElement, By.tagName("h6"), expectedContent.get("Add Holding Product Label"), "Add Holding Label");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Add Holding Description"), "Add Holding Description");

        // Validate confirm button text
        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Confirm Button");

        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");

    }

    public void validateThankYouAddHolding(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(dashboardPage.thankYouAddHoldingSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Thank You Add Holding Description"), "Thank You Add Holding Description");

        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Primary Button"), "Close Button");
    }

    public void validateResetPasswordSlider(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(sign.resetPasswordSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Forgot Password Description"), "Forgot Password Description");
        validateElementTextContent(sliderRootElement, LABEL, expectedContent.get("Forgot Password Email Label"), "Forgot Password Email Label");
        validateElementTextContent(sliderRootElement, LINK, expectedContent.get("Anchor Link"), "Forgot Password Link");

        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");

        // Validate confirm button text
        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Next Button");
    }


//    @SneakyThrows
//    public void validateCntExpDidNotReceieveEmail(String sliderName) {
//        try {
//            Thread.sleep(2000);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            // WebElement sliderRootElement = webUtil.waitUntilElementVisible(By.xpath("//section[@class='dashboard-modal__content js-modal active']"), 20);
//            WebElement sliderRootElement = webUtil.waitUntilElementVisible(expeditedSecurityPage.emailNotReceived, 20);
//
//            TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
//            Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);
//            System.out.println("Pass");
//            // Validate header and labels
//            validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
//            validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Description"), "Description");
//
//            //Validate bullet points
//            List<WebElement> bullets = sliderRootElement.findElements(LIST_TAG);
//            for (int i = 0; i < bullets.size(); i++) {
//                String actual = bullets.get(i).getText().trim();
//                String expected = expectedContent.get("Bullet" + (i + 1));
//                webUtil.CompareString(actual, String::equals, expected, "Bullet" + (i + 1));
//
//            }
//
//            // Validate Resend code button text
//            String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
//            webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Resend email Button");
//
//            // Validate cancel button text
//            String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
//            webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//


//    }
public void validateCntExpDidNotReceieveEmail(){
    WebElement sliderRootElement = webUtil.waitUntilElementVisible(By.xpath("//section[@class='dashboard-modal__content js-modal active']"), 20);

    // Validate header and labels

    List<WebElement> bullets = sliderRootElement.findElements(By.xpath("//section[@class=\"dashboard-modal__content js-modal active\"]//ul//li"));
    String header = sliderRootElement.findElement(HDR4).getText();
    webUtil.CompareString(header, String::equals, "Didn't get an email?");


    String paragraph = sliderRootElement.findElement(PARAGRAPH).getText();
    webUtil.CompareString(paragraph, String::equals, "Before requesting the email is resent please:");


    webUtil.CompareString(bullets.get(0).getText().trim(), String::equals, "Allow 1-2 minutes for the email to be received.");

    webUtil.CompareString(bullets.get(1).getText().trim(), String::equals, "Check your spam folder.");
    webUtil.CompareString(bullets.get(2).getText().trim(), String::equals, "Check that your device is online and that there are no network issues in your area.");

    // Validate Resend code button text
    String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
    webUtil.CompareString(txtConfirmBtn, String::equals, "Resend email");

    // Validate cancel button text
    String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
    webUtil.CompareString(txtCancelBtn, String::equals, "Close");


}




    public void validateContentDidNotGetCodeChgPsw(String sliderName) {
        // WebElement sliderRootElement = webUtil.waitUntilElementVisible(By.id("sectionChangePasswordResendSecurityCode"), 20);

        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.changePasswordDidNotReceiveCodeSlider, 20);

        // Validate header and labels

        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH2, expectedContent.get("Description"), "Description");

        //Validate bullet points
        List<WebElement> bullets = sliderRootElement.findElements(LIST_TAG);
        for (int i = 0; i < bullets.size(); i++) {
            String actual = bullets.get(i).getText().trim();
            String expected = expectedContent.get("Bullet" + (i + 1));
            webUtil.CompareString(actual, String::equals, expected, "Bullet" + (i + 1));

        }

        // Validate Resend code button text
        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Resend code Button");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");


        //clicking on change button
        sliderRootElement.findElement(PRIMARY_BUTTON).click();




    }


    public void validateCheckEmailSlider(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(sign.checkEmailSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Check Email Description"), "Check Email Description");
        validateElementTextContent(sliderRootElement, LINK, expectedContent.get("Anchor link"), "Didn't get email Link");


        // Validate close button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Primary Button"), "Close Button");
    }

    public void validateAddIBANSliderContent(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.addIBANSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        List<String> descriptionList = sliderRootElement.findElements(PARAGRAPH)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
        for (int i = 0; i < descriptionList.size(); i++) {
            String expectedReq = expectedContent.get("Add IBAN Description " + (i + 1));
            webUtil.CompareString(
                    descriptionList.get(i),
                    String::equals,
                    expectedReq,
                    "Add IBAN Description " + (i + 1)
            );

        }

        List<String> labelList = sliderRootElement.findElements(LABEL)
                .stream()
                .map(WebElement::getText)
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());

        for (int i = 0; i < labelList.size(); i++) {
            String expectedReq = expectedContent.get("Add IBAN Label " + (i + 1));
            webUtil.CompareString(
                    labelList.get(i),
                    String::equals,
                    expectedReq,
                    "Add IBAN Label " + (i + 1)
            );

        }

        // Validate close button text
        String txtVerifyBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtVerifyBtn, String::equals, expectedContent.get("Primary Button"), "Verify bank details button");

        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel button");
    }


//    public void validateCntExpMobileDidNotReceieveCode(String sliderName) {
//        WebElement sliderRootElement = webUtil.waitUntilElementVisible(expeditedSecurityPage.mobileCodeNotReceived, 20);
//        // Validate header and labels
//
//        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
//        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);
//
//        // Validate header and labels
//        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
//        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Description"), "Description");
//
//        //Validate bullet points
//        List<WebElement> bullets = sliderRootElement.findElements(LIST_TAG);
//        for (int i = 0; i < bullets.size(); i++) {
//            String actual = bullets.get(i).getText().trim();
//            String expected = expectedContent.get("Bullet" + (i + 1));
//            webUtil.CompareString(actual, String::equals, expected, "Bullet" + (i + 1));
//
//        }
//
//        // Validate Resend code button text
//        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
//        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Resend code Button");
//
//        // Validate cancel button text
//        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
//        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");
//
//
//
//
//
//    }

    public void validateCntExpMobileDidNotReceieveCode(){
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(By.id("sectionResend"), 20);

        List<WebElement> bullets = sliderRootElement.findElements(By.xpath("//*[@id='sectionResend']/ul/li"));
        // Validate header and labels

        String header = sliderRootElement.findElement(HDR4).getText();
        webUtil.CompareString(header, String::equals, "Didn't get your Verification Code?");


        String paragraph = sliderRootElement.findElement(PARAGRAPH).getText();
        webUtil.CompareString(paragraph, String::equals, "Before requesting the code is resent please check the following:");

        //Validate bullet points


        webUtil.CompareString(bullets.get(0).getText().trim(), String::equals, "Allow 1-2 minutes for the code to be sent");

        webUtil.CompareString(bullets.get(1).getText().trim(), String::equals, "You have entered your mobile number correctly");
        webUtil.CompareString(bullets.get(2).getText().trim(), String::equals, "There are no network issues in your area");

//        String bullet1 = sliderRootElement.findElements(LIST_TAG).get(0).getText();
//        webUtil.CompareString(bullet1.trim(), String::equals, "Allow 1-2 minutes for the code to be sent");
//
//        String bullet2 = sliderRootElement.findElements(LIST_TAG).get(1).getText();
//        webUtil.CompareString(bullet2, String::equals, "You have entered your mobile number correctly");
//
//
//        String bullet3 = sliderRootElement.findElements(LIST_TAG).get(2).getText();
//        webUtil.CompareString(bullet3, String::equals, "There are no network issues in your area");
//
//        String bullet1 = sliderRootElement.findElement(LIST_TAG1).getText();
//        webUtil.CompareString(bullet1.trim(), String::equals, "Allow 1-2 minutes for the code to be sent");
//
//        String bullet2 = sliderRootElement.findElement(LIST_TAG2).getText();
//        webUtil.CompareString(bullet2, String::equals, "You have entered your mobile number correctly");
//
//
//        String bullet3 = sliderRootElement.findElement(LIST_TAG3).getText();
//        webUtil.CompareString(bullet3, String::equals, "There are no network issues in your area");






//Shan added
        String bullet4 = sliderRootElement.findElement(Paragraph).getText();
        webUtil.CompareString(bullet4, String::equals, "If you still haven’t received the verification code, you may have entered your mobile number incorrectly, in which case you will not be able to proceed with registration at this time. You may retry your registration by clicking the link in the registration email we sent you. Alternatively, you may restart the registration process once our systems have refreshed. Please allow 8 hours after your initial registration attempt before registering again.");



        // Validate Resend code button text
        String txtConfirmBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtConfirmBtn, String::equals, "Resend code");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, "Cancel");


    }




    public void validateChangeIBANSliderContent(String sliderName,String currentIBAN) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(profileAndSettingsPg.addIBANSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        List<String> descriptionList = sliderRootElement.findElements(PARAGRAPH)
                .stream()
                .map(WebElement::getText)
                .map(s->s.replaceAll("\n", ""))
                .map(String::trim)
                .collect(Collectors.toList());
        for (int i = 0; i < descriptionList.size(); i++) {
            String expectedReq = expectedContent.get("Change IBAN Description " + (i + 1));
            if(i == 4) {
                expectedReq = expectedContent.get("Change IBAN Description " + (i + 1)).replace("~IBAN", "~"+currentIBAN);
            }
            webUtil.CompareString(
                    descriptionList.get(i),
                    String::equals,
                    expectedReq,
                    "Change IBAN Description " + (i + 1)
            );

        }

        List<String> labelList = sliderRootElement.findElements(LABEL)
                .stream()
                .map(WebElement::getText)
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());

        for (int i = 0; i < labelList.size(); i++) {

            String expectedReq = expectedContent.get("Change IBAN Label " + (i + 1));
            webUtil.CompareString(
                    labelList.get(i),
                    String::equals,
                    expectedReq,
                    "Change IBAN Label " + (i + 1)
            );

        }

        // Validate close button text
        String txtVerifyBtn = sliderRootElement.findElement(PRIMARY_BUTTON).getText();
        webUtil.CompareString(txtVerifyBtn, String::equals, expectedContent.get("Primary Button"), "Verify bank details button");

        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel button");
    }


    public void validateContentOnDidNotGetCheckEmail(String sliderName) {
        // WebElement sliderRootElement = webUtil.waitUntilElementVisible(By.id("sectionResendEmailPassword"), 20);

        WebElement sliderRootElement = webUtil.waitUntilElementVisible(sign.emailnotreceived, 20);
        // Validate header and labels

        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Description"), "Description");

        //Validate bullet points
        List<WebElement> bullets = sliderRootElement.findElements(LIST_TAG);
        System.out.println("Pass");
        for (int i = 0; i < bullets.size(); i++) {
            System.out.println("Pass1");
            String actual = bullets.get(i).getText().trim();
            String expected = expectedContent.get("Bullet" + (i + 1));
            System.out.println("Pass2");
            webUtil.CompareString(actual, String::equals, expected, "Bullet" + (i + 1));
            System.out.println("Pass3");


        }
    }

    public void validateDidNotGetCodeSliderOnForgotPassword(String sliderName) {
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(securityPage.forgotPasswordDidNotReceiveSlider, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Description"), "Description");

        //Validate bullet points
        List<WebElement> bullets = sliderRootElement.findElements(LIST_TAG);
        for (int i = 0; i < bullets.size(); i++) {
            String actual = bullets.get(i).getText().trim();
            String expected = expectedContent.get("Bullet" + (i + 1));
            webUtil.CompareString(actual, String::equals, expected, "Bullet" + (i + 1));

        }

        // Validate Resend code button text
        String txtConfirmBtn = sliderRootElement.findElement(FORGOT_PASSWORD_PRIMARY_BUTTON).getAttribute("value");
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Resend code Button");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(FORGOT_PASSWORD_SECONDARY_BUTTON).getAttribute("value");
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");

    }



    public void validateContentOnOtpPgeDidNotGetCode(String sliderName) {
        //WebElement sliderRootElement = webUtil.waitUntilElementVisible(By.id("sectionResendCodeVerify"), 20);
        WebElement sliderRootElement = webUtil.waitUntilElementVisible(sign.codenotreceivedOnOTP, 20);
        TestData sliderData = retrieveSliderConfigurationFromExcel(sliderName);
        Map<String, String> expectedContent = parseSliderContentByKeyValue(sliderName);

        // Validate header and labels
        validateElementTextContent(sliderRootElement, HDR4, sliderData.getSliderHdr(), "Header");
        validateElementTextContent(sliderRootElement, PARAGRAPH, expectedContent.get("Description"), "Description");

        //Validate bullet points
        List<WebElement> bullets = sliderRootElement.findElements(LIST_TAG);
        for (int i = 0; i < bullets.size(); i++) {
            String actual = bullets.get(i).getText().trim();
            String expected = expectedContent.get("Bullet" + (i + 1));
            webUtil.CompareString(actual, String::equals, expected, "Bullet" + (i + 1));

        }

        // Validate Resend code button text
        String txtConfirmBtn = sliderRootElement.findElement(FORGOT_PASSWORD_SECONDARY_BUTTON).getAttribute("value");
        webUtil.CompareString(txtConfirmBtn, String::equals, expectedContent.get("Primary Button"), "Resend code Button");

        // Validate cancel button text
        String txtCancelBtn = sliderRootElement.findElement(SECONDARY_BUTTON).getText();
        webUtil.CompareString(txtCancelBtn, String::equals, expectedContent.get("Secondary Button"), "Cancel Button");

    }
    }