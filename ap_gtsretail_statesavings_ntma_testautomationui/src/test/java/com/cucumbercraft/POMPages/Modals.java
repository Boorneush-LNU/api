package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.ModalData;
import com.cucumbercraft.POMPages.BuyNow.YourDetail;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.framework.WebDriverUtil;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.data;


public class Modals {

    WebDriver driver;
    WebDriverUtil webUtil;

    APIReusuableLibrary apiResuableLibrary = new APIReusuableLibrary();


    //For PB
    // Allocate full amount button modal
    private final By allocateFullAmountElement = By.xpath("//section[@data-name='allocate-all-modal']");
    private final By allocateAllPBNoIBANElement = By.xpath("//section[@data-name='allocate-all-pb-noiban-modal']");
    private final By allocateAmountHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']//h1");
    private final By availableAmountExceeded = By.xpath("//section[@data-name='insufficient-funds-modal']");
    private final By mustAllocateAll = By.cssSelector("section[data-name='must-allocate-all-modal']");
    private By insufficientFunds = By.cssSelector("section[data-name='insufficient-funds-modal']");
    private By cancelTransaction = By.cssSelector("section[data-name='cancel-transaction-modal']");
    private By jointHoldings = By.cssSelector("section[data-name='joint-holdings-modal']");
    private By addHoldingCancel = By.cssSelector("section[data-name='confirm-cancel-modal']");
    private final By Remove = By.xpath(".//td//button");
    private final By AmountExceed = By.xpath("//section[@class='dashboard-modal__content js-modal active']");
    private By PBModal = By.xpath("//div[@class='resistance_modal js-modal prize-bond-purchase-modal active']");
    private By RemoveProduct = By.xpath("//div[@id='resistance-modal']");
    public By InsufficientModal = By.xpath("//button[@class='gtm-cta button button--primary  js-closeModal']");
    private final By paragraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']//p");
    private By VerificationCode = By.xpath("//button[@data-modal='resend-security-code']");
    private final By cancelBtn = By.xpath("//div[@class='dashboard-modal-btn--container']/button[contains(text(),'Cancel')]");
    private final By yesImSureBtn = By.xpath("//div[@class='dashboard-modal-btn--container']/button[contains(text(),'Yes')]");
    private final By closeBtn = By.xpath("//section[@class='dashboard-modal__content js-modal active']/button[@class='modal_close js-closeModal']");

    // Confirm Button Modal
    private final By confirmBtnModalHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h1");
    private final String confirmBtnModalHeaderContent = "You must allocate the full amount";
    private final By confirmBtnModalParagraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']//p");
    private final String ibanNotAddedConfirmButtonModalParagraph = "//section[@class='dashboard-modal__content js-modal active']//p[%d]";
    private final String ibanNotAddedConfirmButtonModalButtons = "//section[@class='dashboard-modal__content js-modal active']/div/button[%d]";
    private final String confirmBtnModalParagraphContent = "Reinvest the remaining amount to a State Savings product or request the remaining amount as a cash repayment to your bank account ending";
    private final By confirmBtnModalAllocateToCashBtn = By.id("btnAllocateToCash");
    private final String confirmBtnModalAllocateToCashBtnContent = "Allocate to cash";
    private final By confirmBtnModalGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnAllocateToCashReInvest']/preceding-sibling::button");
    private final By pbGoBackButtonModal = By.xpath("//button[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_goBackButtonReinvest']");
    private final By pbAddIbanButtonModal = By.xpath("//button[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_addBankDetails']");
    private final String confirmBtnModalGoBackBtnContent = "Go Back";
    public By confirmBtnModalcloseBtn = By.xpath("//section[@class='dashboard-modal__content js-modal active']/button");
    //Cancel Button Modal
    private final By cancelBtnModalHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h1");
    private final String cancelBtnModalHeaderContent = "Cancel transaction";
    private final By cancelBtnModalParagraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']//div/p");
    private final String cancelBtnModalParagraphContent = "You have selected to cancel this transaction. Any fund allocations you have made will be cancelled. Are you sure you want to proceed?";
    private final By cancelBtnModalYesCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lnk_CancelReInvest']");
    private final String cancelBtnModalYesCancelBtnContent = "Yes, cancel";
    private final By cancelBtnModalNoGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lnk_CancelReInvest']//preceding-sibling::button");
    private final String cancelBtnModalNoGoBackBtnContent = "No, go back";
    private final By reviewPageYesCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']");
    private final By reviewPageGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']//preceding-sibling::button");
    private final By securityPageYesCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnCancelRRProcess']");
    private final By securityPageGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnCancelRRProcess']//preceding-sibling::button");
    private final By CloseInsufficient = By.xpath("//button[@class='gtm-cta button button--primary  js-closeModal']");

    //Verify
    private final By cancelbtnmodalcontentHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h1");
    private final By cancelbtnmodalcontentVerify = By.xpath("//section[@class='dashboard-modal__content js-modal active']/div[1]");
    private final By cancelbtnmodalgobackBtn = By.xpath("//div[@class='dashboard-modal-btn--container']//button[contains(text(),'No, go back')]");
    private final By cancelbtnmodalcancelBtn = By.xpath("//div[@class='dashboard-modal-btn--container']//button[contains(text(),'No, go back')]/following-sibling::input");

    //For FT Product
    String ftProductParagraph = "As this product has matured you must allocate the full amount. Please review your product allocation(s) and adjust accordingly.";
    private final By ftProductGoBackBtn = By.xpath("//section[@class='dashboard-modal__content js-modal active']//div/button");
    String ftProductMoreAmountPara = "The amount(s) entered exceed the available amount. Please review your product allocation(s) and adjust accordingly.";


    //For Cash-In Modal Allocate Button
    private final By cashinYesSureBtnModal = By.xpath("//a[@class='gtm-cta button button--primary']");
    private final By cashincancelBtnModal = By.xpath("//a[@class='gtm-cta button button--primary']/preceding-sibling::button");


    //For Cash-In Modal Cancel Button
    private final By cashinYesCancelBtnModal = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lnk_CancelCashIn']");
    private final By cashinGoBackBtnModal = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnCancelRRProcess']/preceding-sibling::button");

    //For Cash-In Modal Confirm Button
    private final By cashinConfirmBtnAllocateToCashinBtnModal = By.id("btnAllocateToCash");
    private final By cashinGoBackBtnmodal = By.xpath("(//section[contains(@class,'modal active')]//button)[2]");
    private final String cashinModalPara = "As this product has matured you must allocate the full amount. Please review your product allocation(s) and adjust accordingly. Alternatively you may allocate the residual amount to cash.";


    //Allocate full amount button for cash-in
    private final By allocatebutton = By.xpath("//div[@class='product-details-card--row']//button[@class='button button--secondary button--alt']");
    public static String amount;
    private By purchaseModaLocator;
    private By hdrLocator;
    private By paragraphLocator;

    //Modal content
    private final By HDR1 = By.tagName("h1");

    private final By PARAGRAPH = By.tagName("p");
    private final By PARAGRAPHREMOVEPRODUCT = By.xpath("//span[@id='resistance-modal-productname']/parent::p");

    private final By PARAGRAPHREMOVE = By.xpath("//p[contains(., 'Are you sure you want to remove') and contains(., 'from your order?')]");
    private final By Buynow = By.id("lnkPrizeBondsForMyself");
    private final By SECONDARY_BUTTON = By.cssSelector(".dashboard-modal-btn--container button:nth-of-type(1)");
    private final By Gift = By.id("lnkPrizeBondsAsGift");
    private final By PRIMARY_BUTTON = By.cssSelector(".dashboard-modal-btn--container button:nth-of-type(2)");


    private final By PRIMARY_BUTTON_REMOVEPROD = By.xpath("//button[@class='button button--primary']");
    private final By SECONDARY_BUTTON_REMOVEPROD = By.xpath("//button[@class='button button--secondary js-closeModal']");

    private WebElement modalRootElement = null;

    PropertyConfig config = ConfigFactory.create(PropertyConfig.class);


    public Modals(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private ModalData fetchModalContent(String modalName) throws Exception {
//      return Excelutils.getInstance().getData(config.getSliderContentExcelPath(), "Modals", modalName, ModalData.class, "getModalName");
        return Excelutils.getInstance().getData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), "Modals", modalName, ModalData.class, "getModalName");  //Merged 2 Excel
    }

    private void validateElementTextContent(WebElement root, By locator, String expected, String fieldName) {
        String actual = root.findElement(locator).getText().trim();
        webUtil.CompareString(actual, String::equals, expected, fieldName);
    }

//Common Method
    private void validateModalParagraphs(WebElement modalRoot, String expectedContent) {
        if (expectedContent.contains("\n\n")) {
            List<String> expectedParagraphs = Arrays.stream(expectedContent.split("\n\n"))
                    .map(s -> s.contains("Iban") ? s.replaceAll("Iban", getIBAN()) : s)
                    .collect(Collectors.toList());
            List<String> actualParagraphs = modalRoot.findElements(PARAGRAPH)
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.trim().isEmpty())
                    .collect(Collectors.toList());

            if (expectedParagraphs.size() != actualParagraphs.size()) {
                throw new ExceptionUtils("Number of paragraphs doesn't match. Expected: " +
                        expectedParagraphs.size() + ", Actual: " + actualParagraphs.size());
            }

            for (int i = 0; i < expectedParagraphs.size(); i++) {
                webUtil.CompareString(expectedParagraphs.get(i), String::equals, actualParagraphs.get(i),
                        "Paragraph " + (i + 1));
            }
        } else {
            expectedContent = expectedContent.replaceAll("Iban", getIBAN());
            validateElementTextContent(modalRoot, PARAGRAPH, expectedContent, "Paragraph");
        }
    }


    private void validateModalParagraphsRemove(WebElement modalRoot, String expectedContent) {
        if (expectedContent.contains("\n\n")) {
            List<String> expectedParagraphs = Arrays.stream(expectedContent.split("\n\n"))
                    .map(s -> s.contains("Iban") ? s.replaceAll("Iban", getIBAN()) : s)
                    .collect(Collectors.toList());
            List<String> actualParagraphs = modalRoot.findElements(PARAGRAPHREMOVE)
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.trim().isEmpty())
                    .collect(Collectors.toList());

            if (expectedParagraphs.size() != actualParagraphs.size()) {
                throw new ExceptionUtils("Number of paragraphs doesn't match. Expected: " +
                        expectedParagraphs.size() + ", Actual: " + actualParagraphs.size());
            }

            for (int i = 0; i < expectedParagraphs.size(); i++) {
                webUtil.CompareString(expectedParagraphs.get(i), String::equals, actualParagraphs.get(i),
                        "Paragraph " + (i + 1));
            }
        } else {
            expectedContent = expectedContent.replaceAll("Iban", getIBAN());
            validateElementTextContent(modalRoot, PARAGRAPHREMOVE, expectedContent, "Paragraph");
        }
    }





    @SneakyThrows
    private String getIBAN(){
        return apiResuableLibrary.getUserDetails(data.getUsername()).get("bankAccountIban").replaceAll("[^0-9]", "");
    }

    public void allocateFullAmountModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(allocateFullAmountElement, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Allocate full amount modal not displayed");
        }
    }

    public void AddIbanPBAllocateModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(allocateAllPBNoIBANElement, 25);
        if (modalRootElement!= null) validateModalContent(modalRootElement, sliderName);
        else throw new ExceptionUtils("Add IBAN PB modal not displayed");
    }



    public void allocateButtonModalPB(String sliderName, String IBAN) {
        modalRootElement = webUtil.waitUntilElementVisible(allocateFullAmountElement, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Allocate full amount modal not displayed");
        }
    }

    // Insuff Model.... No called.
    public void availableAmountExceededModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(availableAmountExceeded, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Allocate full amount modal not displayed");
        }
    }

    // Amount Exceed NSB
    public void availableAmountExceededModalNSB(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(availableAmountExceeded, 10);
        System.out.println("sdb");
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Allocate full amount modal not displayed");
        }
    }



    public void PrizeBondModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(PBModal, 10);
        if (modalRootElement != null) {
            validateModalContentBuynow(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Allocate full amount modal not displayed");
        }
    }


    public void lessAmountEnteredModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(mustAllocateAll, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Less amount entered modal not displayed");
        }
    }

    public void allocateToCashModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(mustAllocateAll, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Allocate to cash modal not displayed");
        }
    }


    public void mustAllocateAllNoIBANModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(mustAllocateAll, 12);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Allocate to cash modal not displayed");
        }
    }

    public void reinvestPBAllocateToCashModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(mustAllocateAll, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("PB Reinvest Allocate to cash modal not displayed");
        }
    }

    public void insufficientFundsModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(insufficientFunds, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Insufficient funds modal not displayed");
        }
    }

    public void cancelTransactionModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(cancelTransaction, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Cancel transaction modal not displayed");
        }
    }

    public void jointHoldingsModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(jointHoldings, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Joint holdings modal not displayed");
        }
    }

    //Shan 31.7.25
    public void removeProductModal(String sliderName) {
        modalRootElement = webUtil.waitUntilElementVisible(RemoveProduct, 10);
        if (modalRootElement != null) {
            validateModalContentRemoveProduct(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Remove Product modal not displayed");
        }
    }


    public  void addHoldingCancelModal(String sliderName)  {
        modalRootElement = webUtil.waitUntilElementVisible(addHoldingCancel, 10);
        if (modalRootElement != null) {
            validateModalContent(modalRootElement, sliderName);
        } else {
            throw new ExceptionUtils("Joint holdings modal not displayed");
        }
    }


















    //Cancel button content code
    private void cancelModalContentCheck() throws Exception {
        if (webUtil.getText(cancelBtnModalHeader).equalsIgnoreCase(cancelBtnModalHeaderContent)) {
            if (!webUtil.getText(cancelBtnModalParagraph).equalsIgnoreCase(cancelBtnModalParagraphContent)) {
                throw new ExceptionUtils("cancel button Modal paragraph content not verified");
            }
        } else {
            throw new ExceptionUtils("cancel button modal Modal Header content not verified");
        }
    }

    public void yesCancelBtn() throws Exception {
        if (webUtil.isElementclickable(cancelBtnModalYesCancelBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!driver.findElement(cancelBtnModalYesCancelBtn).getAttribute("value").equals(cancelBtnModalYesCancelBtnContent)) {
                    throw new ExceptionUtils("cancel button model Yes cancel button content not verified");
                }
                webUtil.click(cancelBtnModalYesCancelBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("Yes, Cancel button x-path not found!");
    }

    public void noBackBtn() throws Exception {
        if (webUtil.isElementclickable(cancelBtnModalNoGoBackBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(cancelBtnModalNoGoBackBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Go back button content not verified!");
                }
                webUtil.click(cancelBtnModalNoGoBackBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("No, Go back button x-path not found!");
    }


    //Cancel button content code for review page review page
    public void reviewPageYesCancelBtn() throws Exception {
        if (webUtil.isElementclickable(reviewPageYesCancelBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(reviewPageYesCancelBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Yes button content not verified!");
                }
                webUtil.click(reviewPageYesCancelBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else {
            throw new ExceptionUtils("Yes, Cancel button x-path not found!");
        }
    }

    public void reviewPagenoBackBtn() throws Exception {
        if (webUtil.isElementclickable(reviewPageGoBackBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(reviewPageGoBackBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Go back button content not verified!");
                }
                webUtil.click(reviewPageGoBackBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("No, Go back button x-path not found!");
    }

    //Cancel button content code for Security page review page
    public void securityPageYesCancelBtn() throws Exception {
        if (webUtil.isElementclickable(securityPageYesCancelBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(securityPageYesCancelBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Yes button content not verified!");
                }
                webUtil.click(securityPageYesCancelBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else {
            throw new ExceptionUtils("Yes, Cancel button x-path not found!");
        }
    }

    public void securityPagenoBackBtn() throws Exception {
        if (webUtil.isElementclickable(securityPageGoBackBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(securityPageGoBackBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Go back button content not verified!");
                }
                webUtil.click(securityPageGoBackBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("No, Go back button x-path not found!");
    }


    //Confirm button content code
    private void confirmModalContentCheck() throws Exception {
        System.out.println(webUtil.getText(confirmBtnModalHeader));
        if (webUtil.getText(confirmBtnModalHeader).contentEquals(confirmBtnModalHeaderContent)) {
            if (!webUtil.getText(confirmBtnModalParagraph).contains(confirmBtnModalParagraphContent + " ~")) {
                throw new ExceptionUtils("confirm button modal paragraph content is changed");
            }
            if (!driver.findElement(confirmBtnModalAllocateToCashBtn).getAttribute("value").equals(confirmBtnModalAllocateToCashBtnContent)) {
                throw new ExceptionUtils("confirm button modal Yes cancel button content is changed");
            }
            if (!webUtil.getText(confirmBtnModalGoBackBtn).equals(confirmBtnModalGoBackBtnContent)) {
                throw new ExceptionUtils("confirm button modal Go back button content is changed");
            }
        } else {
            throw new ExceptionUtils("confirm button modal Header content is not same");
        }
    }

    public void allocateToCashBtn() throws Exception {
        try {
            webUtil.click(confirmBtnModalAllocateToCashBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // Shan 31.7.25
    public void InsuffFunds() throws Exception {
        try {
            webUtil.click(InsufficientModal);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

//Shan 31.7.25
    public void NotReceive() throws Exception {
        try {
            webUtil.click(VerificationCode);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    public void goBackModal() throws Exception {
        try {
            confirmModalContentCheck();
            webUtil.click(confirmBtnModalGoBackBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void goBackModalPB() {
        try {
            AddIbanPBAllocateModal("");
            webUtil.click(pbGoBackButtonModal);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    public void addIbanPBModal() {
        try {
            Thread.sleep(2000);
          WebElement Ele =  modalRootElement.findElement(PRIMARY_BUTTON);
          webUtil.scroll(Ele);
          Ele.click();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    private void validateModalContent(WebElement modalRootElement, String sliderName) {
        try {
            ModalData testData = fetchModalContent(sliderName);
            validateElementTextContent(modalRootElement, HDR1, testData.getModalHdr(), "Header");
            validateModalParagraphs(modalRootElement, testData.getModalContent());
            if(testData.getModalSecondaryButton()==null){
//                webUtil.scrollToView(SECONDARY_BUTTON);
                validateElementTextContent(modalRootElement, SECONDARY_BUTTON, testData.getModalPrimaryButton(), "Primary Button");

            }else {
                validateElementTextContent(modalRootElement, SECONDARY_BUTTON, testData.getModalSecondaryButton(), "Secondary Button");
                validateElementTextContent(modalRootElement, PRIMARY_BUTTON, testData.getModalPrimaryButton(), "Primary Button");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Shan 31.7.25. RemoveProduct
    private void validateModalContentRemoveProduct(WebElement modalRootElement, String sliderName) {
        try {
            ModalData testData = fetchModalContent(sliderName);
            validateElementTextContent(modalRootElement, HDR1, testData.getModalHdr(), "Header");
//            validateModalParagraphsRemove(modalRootElement, testData.getModalContent()); Product will be changing dynamically So Commenting paragraph method
            if(testData.getModalSecondaryButton()==null){
                validateElementTextContent(modalRootElement, SECONDARY_BUTTON_REMOVEPROD, testData.getModalPrimaryButton(), "Primary Button");
            }else {
                validateElementTextContent(modalRootElement, SECONDARY_BUTTON_REMOVEPROD, testData.getModalSecondaryButton(), "Secondary Button");
                validateElementTextContent(modalRootElement, PRIMARY_BUTTON_REMOVEPROD, testData.getModalPrimaryButton(), "Primary Button");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Buynow Modal Shan 31.7.25. Buynow Modal
    private void validateModalContentBuynow(WebElement modalRootElement, String sliderName) {
        try {
            ModalData testData = fetchModalContent(sliderName);
            validateElementTextContent(modalRootElement, HDR1, testData.getModalHdr(), "Header");
            validateModalParagraphs(modalRootElement, testData.getModalContent());
            if(testData.getModalSecondaryButton()==null){
                validateElementTextContent(modalRootElement, Buynow, testData.getModalPrimaryButton(), "Primary Button");

            }else {
                validateElementTextContent(modalRootElement, Gift, testData.getModalSecondaryButton(), "Secondary Button");
                validateElementTextContent(modalRootElement, Buynow, testData.getModalPrimaryButton(), "Primary Button");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    public void cancelBtn() throws Exception {
        try {
            allocateFullAmountModal("");
            webUtil.click(cancelBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void yesSureBtn() {
        try {
            modalRootElement.findElement(PRIMARY_BUTTON).click();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    /**
     * Reinvest Choose Product page Confirm button Modal
     *
     * @throws Exception
     */
    //For FT product
    private void ftProductConfirmBtnModal() throws Exception {
        if (webUtil.getText(confirmBtnModalHeader).trim().contentEquals(confirmBtnModalHeaderContent)) {
            if (!webUtil.getText(confirmBtnModalParagraph).trim().contentEquals(ftProductParagraph)) {
                throw new ExceptionUtils("confirm button modal paragraph content not verified");
            }
            if (!webUtil.getText(ftProductGoBackBtn).trim().contentEquals(confirmBtnModalGoBackBtnContent)) {
                throw new ExceptionUtils("confirm button modal Go back button content not verified");
            }
        } else {
            throw new ExceptionUtils("confirm button modal Header content not verified!");
        }
    }

    /**
     * When amount entered is lesser and confirm button is clicked modal displayed go back button
     */
    public void goBackModalFt() {
        try {
            ftProductConfirmBtnModal();
            webUtil.click(ftProductGoBackBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

//    public void ClickOnRemove() {
//        try {
//            Thread.sleep(4000);
//           System.out.println("Check");
//            webUtil.isElementclickable(Remove, 20);
//            webUtil.scrollToView(Remove);
//            webUtil.click(Remove);
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//
//        }
//    }



    /**
     * Cashin Modals
     */

    /**
     * Allocate button confirm button modal
     *
     * @throws Exception
     */
    public void cashinAllocateModalConfirmBtn() throws Exception {
        webUtil.waitForPageLoaded();
        if (webUtil.isElementDisplayed(cashinYesSureBtnModal, 20)) {
            try {
                if (webUtil.getText(cashinYesSureBtnModal).contains("Yes, I'm sure"))
                    webUtil.click(cashinYesSureBtnModal);
                else
                    throw new ExceptionUtils("Allocate cash modal Yes, I'm Sure Button content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cash in allocate modal -> Confirm Button");
            }
        } else {
            throw new ExceptionUtils("Cashin Yes sure button of allocate modal x-path may have changed");
        }
    }

    /**
     * Allocate button cancel button modal
     *
     * @throws Exception
     */
    public void cashinAllocateModalCancelBtn() throws Exception {
        if (webUtil.isElementDisplayed(cashincancelBtnModal, 20)) {
            try {
                if (webUtil.getText(cashincancelBtnModal).contains("Cancel"))
                    webUtil.click(cashincancelBtnModal);
                else
                    throw new ExceptionUtils("Allocate cash modal cancel button content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cashin allocate modal -> cancel button");
            }
        } else {
            throw new ExceptionUtils("Cashin Allocate modal cancel button x-path may have been changed");
        }
    }

    /**
     * Cancel button modal Yes Cancel button
     *
     * @throws Exception
     */
    public void cashinCancelBtnModalYesCancelBtn() throws Exception {
        if (webUtil.isElementDisplayed(cashinYesCancelBtnModal, 20)) {
            try {
                if (webUtil.getText(cashinYesCancelBtnModal).contains("Yes, Cancel"))
                    webUtil.click(cashinYesCancelBtnModal);
                else
                    throw new ExceptionUtils("Cancel button modal cancel content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cancel modal -> yes cancel button ");
            }
        } else {
            throw new ExceptionUtils("Cashin Cancel modal Yes cancel button x-path may have been changed");
        }
    }

    /**
     * Cancel button modal Go Back button
     *
     * @throws Exception
     */
    public void cashinCancelBtnModalGoBackBtn() throws Exception {
        if (webUtil.isElementDisplayed(cashinGoBackBtnModal, 20)) {
            try {
                if (!webUtil.getText(cashinGoBackBtnModal).contains("No, go back"))
                    webUtil.click(cashinGoBackBtnModal);
                else
                    throw new ExceptionUtils("Cash in cancel button modal go back button content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cancel modal -> Go Back button ");
            }
        } else {
            throw new ExceptionUtils("Cashin Cancel modal Go-Back button x-path may have been changed");
        }
    }


    /**
     * Allocate full amount button
     */
    public void clickAllocateFullAmountBtn() throws Exception {
        webUtil.waitForPageLoaded();
        if (webUtil.isElementDisplayed(allocatebutton, 20)) {
            if (!webUtil.getText(allocatebutton).contains("Allocate full amount")) {
                throw new ExceptionUtils("Allocate button content is changed");
            }
            try {
                webUtil.click(allocatebutton);
            } catch (Exception e) {
                throw new ExceptionUtils("Allocate full amount button at cash in journey is interrupted");
            }
        } else {
            throw new ExceptionUtils("Maybe x-path for allocate full amount is changed");
        }
    }


    /**
     * you must allocate full amount modal content check
     *
     * @throws Exception
     */
    public void confirmAllocateToCashModalContent() throws Exception {
        ExtentCucumberAdapter.addTestStepLog("Validating Allocate to Cash Modal Content");
        webUtil.gettextlog(confirmBtnModalHeader, String::equals, confirmBtnModalHeaderContent);
        webUtil.gettextlog(confirmBtnModalParagraph, String::equals, cashinModalPara);
        webUtil.gettextlog(cashinGoBackBtnmodal, String::equals, confirmBtnModalGoBackBtnContent);
        webUtil.gettextlog(cashinConfirmBtnAllocateToCashinBtnModal, String::equals, confirmBtnModalAllocateToCashBtnContent);

    }

    /**
     * you must allocate full amount allocate to cash button click
     *
     * @throws Exception
     */
    public void cashinconfirmbuttonAllocatetocashinmodal() throws Exception {
        confirmAllocateToCashModalContent();
        webUtil.click(cashinConfirmBtnAllocateToCashinBtnModal);

    }

    /**
     * you must allocate full amount modal go back button click
     *
     * @throws Exception
     */
    public void cashingoBackBtnModal() throws Exception {
        confirmAllocateToCashModalContent();
        if (webUtil.isElementDisplayed(cashinGoBackBtnmodal, 20)) {
            try {
                webUtil.click(cashinGoBackBtnmodal);
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe cash in confirm button modal cancel button is interrupted");
            }
        } else {
            throw new ExceptionUtils("Cancel button of confirm button of cash in journey x-path is changed");
        }
    }

    public void confirmButtonReinvestIbanNotAdded() throws Exception {
        if (webUtil.getText(allocateAmountHeader).trim().contentEquals(confirmBtnModalHeaderContent)) {
            if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalParagraph, 1))).trim().contentEquals("Reinvest the remaining amount to a Ireland State Savings product or request the remaining amount as a cash repayment to your bank account.")) {
                throw new ExceptionUtils("Modal 1st Paragraph not verified!");
            }
            if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalParagraph, 2))).trim().contentEquals("As you have not added IBAN details you can add your bank details now or go back.")) {
                throw new ExceptionUtils("Modal 2nd Paragraph not verified!");
            }
        } else
            throw new ExceptionUtils("Modal Header not verified!");
    }

    public void confirmButtonReinvestIbanNoteAddedClickAddIbanButton() throws Exception {
        webUtil.waitUntilElementVisible(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1)), 20);
        confirmButtonReinvestIbanNotAdded();
        if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 2))).trim().contentEquals("Add IBAN details")) {
            throw new ExceptionUtils("Add IBAN button content not verified!");
        }
        webUtil.click(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 2)));
    }

    public void confirmButtonReinvestIbanNoteAddedClickGoBackButton() throws Exception {
        webUtil.waitUntilElementVisible(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1)), 20);
        confirmButtonReinvestIbanNotAdded();
        if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1))).trim().contentEquals(confirmBtnModalGoBackBtnContent)) {
            throw new ExceptionUtils(confirmBtnModalGoBackBtnContent.concat("button content not verified!"));
        }
        webUtil.click(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1)));
    }

    public Modals validateBuyNowModalContent() {
        purchaseModaLocator = By.xpath("//section[contains(@class,'modal active')]");
        hdrLocator = By.xpath(".//h1");
        paragraphLocator = By.xpath(".//p");
        WebElement element = webUtil.waitUntilElementVisible(purchaseModaLocator, 7);
        String actualModalHdr = element.findElement(hdrLocator).getText();
        String actualContent = element.findElement(paragraphLocator).getText();
        String expectedModalHdr = "Registered for Ireland State Savings Online?";
        String expectedContent = "If you are already registered with Ireland State Savings Online, you may sign in now for fast, easy purchase.\n" +
                "\n" +
                "Alternatively, click ‘Buy now’ to proceed with your purchase.";
        webUtil.CompareString(actualModalHdr, String::equals, expectedModalHdr);
        webUtil.CompareString(actualContent, String::equals, expectedContent);
        return this;
    }

    public Modals validateBuyNowModalContentIrish() {
        purchaseModaLocator = By.xpath("//section[contains(@class,'modal active')]");
        hdrLocator = By.xpath(".//h1");
        paragraphLocator = By.xpath(".//p");
        WebElement element = webUtil.waitUntilElementVisible(purchaseModaLocator, 7);
        String actualModalHdr = element.findElement(hdrLocator).getText();
        String actualContent = element.findElement(paragraphLocator).getText();
        String expectedModalHdr = "Cláraithe le haghaidh Coigiltis Stáit na hÉireann Ar Líne?";
        String expectedContent = "Má tá tú cláraithe cheana féin le Coigiltis Stáit na hÉireann Ar Líne, is féidir leat síniú isteach le ceannach go mear éasca.\n" +
                "\n" +
                "Nó, cliceáil ar ‘Ceannaigh anois’ chun dul ar aghaidh le do cheannachán.";
        webUtil.CompareString(actualModalHdr, String::equals, expectedModalHdr);
        webUtil.CompareString(actualContent, String::equals, expectedContent);
        return this;
    }



    public YourDetail clickGuest() {
        By btnBuyNow = By.xpath("//section[@data-name='sign-in-modal']//a[contains(@class,'add-product-guest') or text()='Buy now']");
        webUtil.click(btnBuyNow);
        return new YourDetail(driver);
    }



    public YourDetail clickGuestIrish() {
        By btnBuyNow = By.xpath("//section[@data-name='sign-in-modal']//a[contains(@class,'add-product-guest') or text()='Ceannaigh anois']");
//        WebElement BuynowIrish = driver.findElement(By.xpath("//section[@data-name='sign-in-modal']//a[contains(@class,'add-product-guest') or text()='Ceannaigh anois']"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", BuynowIrish);
        webUtil.click(btnBuyNow);
        return new YourDetail(driver);

    }


    public SignInPg clickSignIn() {
        By btnSignIn = By.xpath("//section[@data-name='sign-in-modal']//a[contains(@class,'add-product-sign-in') or text()='Sign in now']");
        webUtil.click(btnSignIn);
        return new SignInPg(driver);
    }



}
