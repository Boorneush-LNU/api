package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.StateSavingsDashboardPage;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class Joint {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final By jointButton = By.xpath("//ul[@class='product-sidebar__options js-sidebar-options']/li[2]/a");
    private final By jointname = By.xpath("//div[@class='product-summary-links__item']");
    private final By jointHoldingsbutton = By.xpath("//div[@class='product-summary-info product-summary-info--card']");
    private final By RecashinOnDetails = By.cssSelector(".gtm-repayReinvest"); //Shan
private final By cashinOnDetails = By.xpath("//button[@class='js-modalTrigger icon-link icon-link--transfer link-blue gtm-linkclick']");
    private final By cashinChildcare = By.xpath("//button[@class='js-modalTrigger icon-link icon-link--transfer link-blue gtm-repayReinvest']");
    private final String notice = "This %s is reaching maturity or has matured. Action required.";
    private final String Noticecheck ="This %s is reaching maturity or has matured. The maturity value will transfer to your State Savings Account 30 days from maturity date.";
    private final String noticeNSB30days = "This %s is reaching maturity or has matured.The maturity value will transfer to your State Savings Account 30 days from maturity date. Action Required.";
    private final String noticePrior2014 = "This %s has matured. To cash in or reinvest please complete the product maturity form you received at account maturity and return it to State Savings.";
    private final String noticecashin = "This %s has been cashed in or reinvested.";
    private final String noticeSSA = "To Reinvest funds that may have transferred from a matured National Solidarity Bond, please use the maturity form you received in the post.";
    private final By modal = By.xpath("//div[@class=\"dashboard-modal__content js-modal dashboard-popup active\"]");
    private final By closemodal = By.xpath(".//button[@class='gtm-cta button button--secondary js-closeModal']");
    private final By textOnMoreDetails = By.xpath("//div[@class='product-summary-info--notification row']");
    private final By Summary = By.xpath("//div[@class='product-summary-info']");
    private final By donor = By.xpath("//div[contains(@class,'product-summary-info product-summary-info--card')]");
    private final By Exp = By.xpath("(//div[@class='large-8 columns'])[2]");
    private final String ExpectedPara = "This Instalment Save is reaching maturity or has matured. Action required.";

    public Joint(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(Joint.class);
    private String button;
    private WebElement element;
    private HashMap<String, String> productMap;

    private String product() {
        productMap = new HashMap<>();
        productMap.put("Prize Bond", "Prize Bonds");
        productMap.put("Saving Bond", "Savings Bond");
        productMap.put("Savings Certificate", "Savings Certificate");
        productMap.put("NSB 4 Year", "National Solidarity Bond");
        productMap.put("NSB 10 Year", "National Solidarity Bond");
        productMap.put("Installment Savings", "Instalment Save");
        productMap.put("Childcare Plus", "Instalment Save");
        productMap.put("SSA", "State Savings Account");
        return productMap.get(StateSavingsDashboardPage.getProductName());
    }

    public void clickjoint() throws Exception {
        if (webUtil.isElementclickable(jointButton, 10)) {
            if(!webUtil.getDriver().findElement(jointButton).getAttribute("class").contains("active"))
                webUtil.click(jointButton);
            else
                log.info("Already on joint page");
        }
    }

    public void selectJointWithName(String name) throws Exception {
        WebElement avail;
        if (webUtil.isElementDisplayed(jointname, 10)) {
            List<WebElement> names = driver.findElements(jointname);
            avail = names.stream()
                    .parallel()
                    .filter(nam -> nam.getText().contains(name))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionUtils("Joint Holding name not found"));
        } else
            throw new ExceptionUtils("Joint xpath not found");
        if (avail.isDisplayed()) {
            try {
                avail.click();
            } catch (Exception e) {
                throw new ExceptionUtils("Joint holding not clicked " + e.getMessage());
            }
        }
    }

    public void selectHoldingWithID(String ID) throws Exception {
        if (webUtil.isElementDisplayed(jointHoldingsbutton, 10)) {
            List<WebElement> elements = driver.findElements(jointHoldingsbutton);
            element = elements.stream().parallel().filter(i -> i.getText().contains(ID)).findFirst().orElseThrow(() -> new ExceptionUtils("Holding ID not found"));
            log.info("Passed it");
            String productname = element.findElement(By.xpath(".//h4")).getText().replaceAll("[^A-Za-z]", " ").trim();
            if (element.getText().contains("Reinvest / Cash In")) {
                String noticemsg = element.findElement(By.xpath(".//div[contains(@class,'large')]")).getText();
                button = element.findElement(By.xpath(".//button")).getText();
                System.out.println(productname);
                if (noticemsg.contentEquals(String.format(notice, productname))) {
                    log.info("notice verified");
                }
                log.info(button);
                element.findElement(By.xpath(".//button")).click();
                webUtil.waitForPageLoaded();

            } else if (element.getText().contains("Cash In")) {
                button = element.findElement(By.xpath(".//button")).getText();
                log.info(button);
                element.findElement(By.xpath(".//button")).click();
                webUtil.waitForPageLoaded();
            } else if (element.getText().contains("This National Solidarity Bond is reaching maturity or has matured. The maturity value will transfer to your State Savings Account 30 days from maturity date.")) {
                String Message = element.getText();
                log.info("Maturity message found: " + Message);
                log.info("Pass");
               webUtil.waitForPageLoaded();
            }
            else {
                String noticemsg = element.findElement(By.xpath(".//div[contains(@class,'large')]")).getText();
                if (!noticemsg.contentEquals(String.format(noticecashin, productname))) {
                    throw new ExceptionUtils("Account flushed msg not verified");
                }
                log.info("Account flushed notice verified");
            }
        } else {
            throw new ExceptionUtils("Joint holdings not found");
        }
    }

    public void jointModals() throws InterruptedException {
        String para = "To cash in or reinvest joint holdings, we require all account holders' signatures. Please download, complete and post the appropriate form to us.";
        String pararc = "To cash in or reinvest joint holdings we require both account holders' signatures. Please use the form you received in the post.";
        WebElement element1=webUtil.waitUntilElementVisible(modal,8);
        String actualHdr=element1.findElement(By.tagName("h2")).getText();
        String actualPara=element1.findElement(By.tagName("p")).getText();
        webUtil.CompareString(actualHdr,String::equals,"Joint Holdings");
        if (button.equals("Cash In")) {
            webUtil.CompareString(actualPara,String::equals,para);
            element1.findElement(closemodal).click();
        } else {
            webUtil.CompareString(actualPara,String::equals,pararc);
            element1.findElement(By.cssSelector(".dashboard-modal-btn--container button:first-child")).click();
        }
    }

        public void jointMoredetailsIS() throws Exception {
        webUtil.gettextlog(Exp, String::equals, ExpectedPara, "compare");
    }

    public void jointMoredetails() throws Exception {  // In 1st scenario
        element.findElement(By.tagName("a")).click();
        if (button.equals("Cash In")) {
            webUtil.scrollToView(Summary);
//            if (webUtil.getText(cashinOnDetails).contentEquals(button)) {  //Shan added RecashinOnDetails
            if (webUtil.getText(RecashinOnDetails).contentEquals(button)) {
                log.info("Cashin Link Matched on More Details Page");
            } else if(webUtil.getText(cashinChildcare).contentEquals(button)){
                log.info("Cashin Link Matched on More Details Page");
            }
            else {
                throw new ExceptionUtils("Cashin link not present on more details page");
            }
        } else {
//            if (webUtil.getText(cashinOnDetails).contentEquals(button)) { Shan comm
            if (webUtil.getText(RecashinOnDetails).contentEquals(button)) {
                log.info("Reinvest Cashin link present on details page");
            }
            else {
                throw new ExceptionUtils("Reinvest Cashin link not present on details page");
            }
        }
    }

    public void soleholding(String ID) throws Exception {
        String str = "//div[@class='product-summary-info product-summary-info--card']";
        String str1 = "//child::a";
        String str2 = "//strong";
        String message;
        List<WebElement> list;
        WebElement moreDetails;
        WebElement messageElement = null;
        if (!StateSavingsDashboardPage.getProductName().contains("Installment Savings") && !StateSavingsDashboardPage.getProductName().contains("Childcare Plus")) {
            list = webUtil.getElements(By.xpath(str));
            element = list
                    .stream()
                    .peek(webUtil::scroll)
                    .filter(i -> i.getText().contains(ID))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionUtils("Holdings id not available"));
            moreDetails = element.findElement(By.xpath(".//a[normalize-space(text())='More Details']"));
//            moreDetails = element.findElement(By.xpath(".//input[contains(@name,'SoleHoldingProducts')]"));
            webUtil.scroll(element);

        } else {
            list = driver.findElements(donor);
            element = list.stream().parallel().filter(i -> i.getText().contains(ID)).findFirst().orElseThrow(() -> new ExceptionUtils("Holdings id not available"));
            moreDetails = element.findElement(By.xpath("(//a[normalize-space(text())='More Details'])[2]"));
            System.out.println(moreDetails.getText());
        }
        try {
            messageElement = element.findElement(By.xpath(".//div[contains(@class,'large')]"));
        } catch (Exception e) {
            log.info("message not dispalyed");
        }
        String productName = element.findElement(By.xpath(".//h4")).getText().replaceAll("[^A-Za-z]", " ").trim();
        webUtil.scroll(element);
        if (element.getText().contains("Reinvest / Cash In")) {
            try {
                assert messageElement != null;
                message = messageElement.getText();
                log.info(message);
                if (message.contentEquals(String.format(notice, productName))) {
                    log.info("notice verified" +productName);
                    ExtentCucumberAdapter.addTestStepLog("Notice verified on" +productName+"summary page" );
                    String val = moreDetails.getAttribute("value");
                    if (val == null || val.equals("More Details")) {
                        moreDetails.click();

                    }
                    if (webUtil.getText(textOnMoreDetails).contains(message + "\n" + "Reinvest / Cash In")) {
                        log.info("Notice and link verified on more details page");
                        ExtentCucumberAdapter.addTestStepLog("Notice and link verified on more details page");
                    }else{
                        log.error("Notice and link not verified on more details page");
                        ExtentCucumberAdapter.addTestStepLog("Notice and link not verified on more details page");
                        throw new ExceptionUtils("Notice or link is not verified on more details page");
                    }
                } else {

                    if (message.contentEquals(String.format(noticeNSB30days, productName))) {
                        log.info("NSB 30 days notice verified");
                        if (moreDetails.getText().contains("More Details")) {
                            moreDetails.click();
                            webUtil.waitForPageLoaded();
                            webUtil.scrollToView(textOnMoreDetails);  //Shan Commented


                            System.out.println(webUtil.getText(textOnMoreDetails));
                            System.out.println(message.replaceFirst("\\.",". ") + "\n" + "Reinvest / Cash In");
//                            System.out.println(StringUtils.difference(webUtil.getText(textOnMoreDetails), message + "\n" + "Reinvest / Cash In"));
                            if (!webUtil.getText(textOnMoreDetails).contains(message.replaceFirst("\\.",". ") + "\n" + "Reinvest / Cash In")) {
                                log.error("Notice and link not verified on more details page");
                                ExtentCucumberAdapter.addTestStepLog("Notice and link not verified on more details page");
                                throw new ExceptionUtils("Notice or link is not verified on more details page");
                            }
                        }
                    }
                }

            } catch (Exception e) {
                throw new ExceptionUtils(e.getMessage());
            }

        }
        else if (!element.getText().contains("Reinvest / Cash In") && !element.getText().contains("Cash In") && !element.getText().contains("Pending transaction")) {
            assert messageElement != null;
            message = messageElement.getText();
            System.out.println(product());
            System.out.println(StringUtils.difference(message, String.format(noticePrior2014, product())));
            if (message.contains(String.format(noticePrior2014, product()))) {
                log.info("Prior 2014 notice verified");
                    moreDetails.click();
                    webUtil.waitForPageLoaded();
                if (webUtil.getText(textOnMoreDetails).contains(message)) {
                    log.info("Notice and link verified on more details page");
                }
            } else if (message.contentEquals(String.format(noticecashin, productName))) {
                log.info("Product cashin and reinvested notice veified");
                ExtentCucumberAdapter.addTestStepLog(message+" "+":PASSED");
                if (moreDetails.getText().contains("More Details")) {
                    moreDetails.click();
                }
                try {
                    String moreDetailsText=webUtil.getText(textOnMoreDetails);
                    if(moreDetailsText.contains("Reinvest / Cash In"))
                        throw new ExceptionUtils("Reinvest/Cashin link  displayed on more details page");
                    else if(moreDetailsText.contains("Cash In"))
                        throw new ExceptionUtils("Cash In link displayed");
                    else if(moreDetailsText.contains("Pending transaction"))
                        throw new ExceptionUtils("Pending link displayed");
                    else
                        if(moreDetailsText.contains(String.format(noticecashin,product()))) {
                            log.info("Notice verified on more details");
                            ExtentCucumberAdapter.addTestStepLog(message + " verified on more details page" + " :PASSED");
                        } else if(moreDetailsText.contains(String.format(notice,product()))) {
                            log.info("Notice verified on more details");
                        }
                        else
                            System.out.println(StringUtils.difference(moreDetailsText, String.format(noticecashin, product())));
                    throw new ExceptionUtils(String.format(noticecashin,product())+" not verified on more details page");

                } catch (NoSuchElementException e) {
                    log.info("Message not present on more details page.");
                }

            } else if (message.contentEquals(String.format(notice, productName))) {
                log.info("NSB 30 days after maturity notice verified");
                if (moreDetails.getAttribute("value").contains("More Details")) {
                    moreDetails.click();
                    webUtil.waitForPageLoaded();
                }
                if (webUtil.getText(textOnMoreDetails).contains(message)) {
                    log.info("Notice and link verified on more details page for NSB 30 days after maturity");
                }
            }
        }
        else if (element.getText().contains("Cash In") && element.getText().contains("Pending transaction") && StateSavingsDashboardPage.getProductName().equals("SSA")) {
            assert messageElement != null;
            message = messageElement.getText();
            if (message.contains(noticeSSA)) {
                log.info("SSA notice verified");
                if (moreDetails.getText().contains("More Details")) {
                    moreDetails.click();
                    webUtil.waitForPageLoaded();
                }
                System.out.println(webUtil.getText(By.xpath("//*[normalize-space(text())='Cash In']")));
                if (webUtil.getText(By.xpath("//*[normalize-space(text())='Cash In']")).contains("Cash In")) {
                    log.info("Link verified for SSA on more details page");
                } else if (webUtil.getText(By.xpath("//button[contains(@class,'js-modalTrigger') and contains(text(),'Pending transaction')]")).contains("Pending transaction")) {
                    log.info("Pending transaction link displayed on more details page.");
                }
            }
        }
        else {   // From here Shan Changed the code
            if (element.getText().contains("Cash In")) {
                log.info("Cash In link verified on Holdings Page");
            } else if (element.getText().contains("Pending transaction")) {
                log.info("Pending Transaction link verified on Holdings Page ");
            }

            if (moreDetails.getText().contains("More Details") || moreDetails.getAttribute("value").contains("More Details")) {
                moreDetails.click();
                webUtil.waitForPageLoaded();
                webUtil.scrollToView(Summary);
                log.info("Pass 2");

                // Declared Locators outside for my ref. //Shan
                By pendingTransactionButtonLocator = By.cssSelector("button[data-modal='pending-transaction-modal']");
                By cashInLinkDivLocator = By.xpath("//div[@class='cash-in-link']");

                if (webUtil.isElementDisplayed(pendingTransactionButtonLocator,10)) {
                    WebElement pendingTransactionButton = null;
                    try {
                        pendingTransactionButton = webUtil.waitUntilElementToBeClickable(pendingTransactionButtonLocator, 10);
                        String pendingText = pendingTransactionButton.getText();
                        System.out.println("Text from Pending transaction button: " + pendingText);
                        if (pendingText.contains("Pending transaction")) {
                            log.info("Pending transaction link verify on more details page.");
                            ExtentCucumberAdapter.addTestStepLog("Pending transaction link verified on more details page" + " :PASSED");
                        } else {
                            log.warn("Pending transaction button found, but text mismatch. Expected 'Pending transaction', Found: '" + pendingText + "'");
                            ExtentCucumberAdapter.addTestStepLog("Pending transaction button text mismatch" + " :FAILED");
                        }
                    } catch (Exception e) {
                        log.error("Pending transaction button not visible/clickable within timeout: " + e.getMessage());
                        ExtentCucumberAdapter.addTestStepLog("Pending transaction button not found or not interactable" + " :FAILED");
                    }
                } else {
                    log.info("Pending Tran not found");
                        }

                if (webUtil.isElementDisplayed(cashInLinkDivLocator,10)) {
                    String cashInText = webUtil.getText(cashInLinkDivLocator);
                    if (cashInText.contains("Cash In")) {
                        log.info("Cash In link verify on more details page.");
                        ExtentCucumberAdapter.addTestStepLog("Cash In link verified on more details page" + " :PASSED"); // Added report step

                    } else {
                        ExtentCucumberAdapter.addTestStepLog("Cash In link container text mismatch" + " :FAILED");
                    }
                } else {
                    log.info("Cash In link Not found in Ui");
                }
            }
        }

    }
}
