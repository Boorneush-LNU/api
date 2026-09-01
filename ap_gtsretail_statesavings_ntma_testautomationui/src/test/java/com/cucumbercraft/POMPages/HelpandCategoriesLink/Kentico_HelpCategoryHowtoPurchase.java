package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Kentico_HelpCategoryHowtoPurchase extends CategoryUtils {

    public Excelutils testdata= new Excelutils();
    String sheetName = "HowtoPurchase";
//    List<TestData> ExcelRows = testdata.getTestData(config.getK13ContentExcelPath(),sheetName,TestData.class);
List<TestData> ExcelRows = testdata.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);
    public static Map<String, String> getdata = new HashMap<>();
    static Logger log = LogManager.getLogger(Kentico_HelpCategoryHowtoPurchase.class);



    public Kentico_HelpCategoryHowtoPurchase(WebDriver driver) {
        super(driver);
    }

    public By tabletag = By.xpath("//article//table");

    
    public void howToPurchase(){
        try{
            By howToPurchase= By.xpath("//a[@title='How to Purchase']");
            webUtil.scrollToView(howToPurchase);
            webUtil.clickLog(howToPurchase, "How To Purchase");

            webUtil.gettextlog(hdrlinkLbl, String::equals, "How to Purchase", "Header");
            String HTPLinks[]={
                    "How to Purchase?",
                    "Can we make Joint Purchases of Ireland State Savings Products?",
                    "How can I buy Ireland State Savings products?",
                    "How to purchase online?",
                    "I am an existing customer, why am I being asked for additional information?",
                    "I don’t live in Ireland. Can I buy Ireland State Savings products?",
                    "I'd like to purchase Prize Bonds by Direct Debit, what do I need to do?",
                    "What documentation is required to become an Ireland State Savings Customer?",
                    "Why do you require Evidence of Identity?",
                    "Why do you require my PPSN?",
                    "What is a Fixed Term Confirmation Letter?",
            };
            validateSize(commonFAQLinks,HTPLinks,"How to purchase");
            for(int b=1;b<=HTPLinks.length;b++){
                String HTPTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a"));
                By HTPElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li["+b+"]/a");
                webUtil.waitFor(1000);
                if(HTPTab.equals(HTPLinks[b-1])){
                    switch (HTPTab){
                        case "How to Purchase?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            HowtoPurchase();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How to Purchase?");
                            webUtil.navigateBack();
                            break;
                        case "Can we make Joint Purchases of Ireland State Savings Products?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            CanWeMakeJointPurchasesOfStateSavingsProducts();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles Can we make Joint Purchases of Ireland State Savings Products?");
                            webUtil.navigateBack();
                            break;
                        case "How can I buy Ireland State Savings products?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            HowCanIBuyStateSavingsProductsIncludingPrizeBonds();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How can I buy Ireland State Savings products?");
                            webUtil.navigateBack();
                            break;
                        case "How to purchase online?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            HowToPurchaseOnline();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How to purchase online?");
                            webUtil.navigateBack();
                            break;

                        case "I don’t live in Ireland. Can I buy Ireland State Savings products?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            IDontLiveInIrelandCanIBuyStateSavingsProductsIncludingPrizeBonds();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles I don’t live in Ireland. Can I buy Ireland State Savings products?");
                            webUtil.navigateBack();
                            break;
                        case "I am an existing customer, why am I being asked for additional information?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            ExistingCustomerWhyamIbeingaskedforAdditionalInformation();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles I am an existing customer, why am I being asked for additional information?");
                            webUtil.navigateBack();
                            break;
                        case "I'd like to purchase Prize Bonds by Direct Debit, what do I need to do?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            ILikeToPurchasePrizeBondsByDirectDebitWhatDoINeedToDo();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles I'd like to purchase Prize Bonds by Direct Debit, what do I need to do?");
                            webUtil.navigateBack();
                            break;
                        case "What documentation is required to become an Ireland State Savings Customer?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            WhatDocumentationIsRequiredWhenPurchasingAStateSavingsProduct();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles What documentation is required to become an Ireland State Savings Customer?");
                            webUtil.navigateBack();
                            break;
                        case "Why do you require Evidence of Identity?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement,HTPTab);
                            WhyDoYouRequireEvidenceOfIdentity();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles Why do you require Evidence of Identity?");
                            webUtil.navigateBack();
                            break;
                        case "Why do you require my PPSN?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            WhyDoYouRequireMyPPSN();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles Why do you require my PPSN?");
                            webUtil.navigateBack();
                            break;
                        case "What is a Fixed Term Confirmation Letter?":
                            webUtil.scrollToView(HTPElement);
                            webUtil.clickLog(HTPElement, HTPTab);
                            WhatIsaFixedTermConfirmationLetter();
                            webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles What is a Fixed Term Confirmation Letter?");
                            webUtil.navigateBack();
                            break;
                    }
                }
            }
            webUtil.clickLog(lnkHelpandSupport, "help and support link");


        }catch(Exception e){
            Assert.fail("howtoPurchaseLinks -" + e.getMessage());
        }

    }

    public void HowtoPurchase() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in HowtoPurchase(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"How to Purchase?","Content");
        int index = testdata.getRowIndex("How to Purchase?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        String actual = webUtil.getText(tabletag);
        String expected = ExcelRows.get(index).getTable();
        webUtil.CompareString(actual,String::equals, expected);
//        webUtil.gettextByAttribute(By.xpath("//article/ul[1]/li/p/a"),String::equals,getUrl()+"/help-support/forms-downloads/application-forms","href");
//        webUtil.gettextByAttribute(By.xpath("//article/ul[3]/li[1]/p/a"),String::equals,getUrl()+"/","href");
//        webUtil.gettextByAttribute(By.xpath("//article/ul[3]/li[4]/p/a"),String::equals,getUrl()+"/getmedia/3f7d5838-f820-49d4-ac2f-a4d6dc3ed441/prizebonds_directdebit_mandate.pdf","href");
//        webUtil.openPDFInNewTab(By.xpath("//article/ul[3]/li[4]/p/a"),"prizebonds_directdebit_mandate.pdf");

        Map<String, String> linkMappings = Map.of(
                "//article/ul[1]/li/p/a", getUrl() + "/help-support/forms-downloads/application-forms",
                "//article/ul[3]/li[1]/p/a", getUrl() + "/",
                "//article/ul[3]/li[4]/p/a", getUrl() + "/getmedia/3f7d5838-f820-49d4-ac2f-a4d6dc3ed441/prizebonds_directdebit_mandate.pdf"
        );

        linkMappings.forEach((xpath, url) -> {
            webUtil.gettextByAttribute(By.xpath(xpath), String::equals, url, "href");
        });
        webUtil.openPDFInNewTab(By.xpath("//article/ul[3]/li[4]/p/a"),"prizebonds_directdebit_mandate.pdf");

    }
    public void CanWeMakeJointPurchasesOfStateSavingsProducts() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in CanWeMakeJointPurchasesOfStateSavingsProducts(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"Can we make Joint Purchases of Ireland State Savings Products?","Content");
        int index = testdata.getRowIndex("Can we make Joint Purchases of Ireland State Savings Products?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.gettextByAttribute(By.xpath("//article/p[1]/a"),String::equals,getUrl()+"/help-support/help-articles/how-to-purchase-online","href");

    }
    public void HowCanIBuyStateSavingsProductsIncludingPrizeBonds() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in HowCanIBuyStateSavingsProductsIncludingPrizeBonds(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"How can I buy Ireland State Savings products?","Content");
        int index = testdata.getRowIndex("How can I buy Ireland State Savings products?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextByAttribute(By.xpath("//article/p[1]/a"),String::equals,getUrl()+"/help-support/help-articles/how-to-purchase-online","href");
        webUtil.gettextByAttribute(By.xpath("//article/ul/li[1]/p/a"),String::equals,getUrl()+"/","href");
        webUtil.gettextByAttribute(By.xpath("//article/ul/li[3]/p/a"),String::equals,getUrl()+"/help-support/forms-downloads/application-forms","href");
        webUtil.gettextByAttribute(By.xpath("//article/ul/li[5]/p/a"),String::equals,getUrl()+"/getmedia/414315e4-a268-4124-8911-5165d4f8a7d2/PrizeBonds_DirectDebit_Mandate_1.pdf","href");
        webUtil.openPDFInNewTab(By.xpath("//article/ul/li[5]/p/a"),"PrizeBonds_DirectDebit_Mandate.pdf");

    }
    public void HowToPurchaseOnline() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in HowToPurchaseOnline(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"How to purchase online?","Content");
        int index = testdata.getRowIndex("How to purchase online?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextByAttribute(By.xpath("//article//a"),String::equals,getUrl()+"/help-support/help-articles/how-to-purchase-state-savings-products","href");

    }
    public void ExistingCustomerWhyamIbeingaskedforAdditionalInformation() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in ExistingCustomerWhyamIbeingaskedforAdditionalInformation(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"I am an existing customer, why am I being asked for additional information?","Content");
        int index = testdata.getRowIndex("I am an existing customer, why am I being asked for additional information?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        }

    public void IDontLiveInIrelandCanIBuyStateSavingsProductsIncludingPrizeBonds() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in IDontLiveInIrelandCanIBuyStateSavingsProductsIncludingPrizeBonds(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"I don’t live in Ireland. Can I buy Ireland State Savings products?","Content");
        int index = testdata.getRowIndex("I don’t live in Ireland. Can I buy Ireland State Savings products?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);


    }

    public void ILikeToPurchasePrizeBondsByDirectDebitWhatDoINeedToDo() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in ILikeToPurchasePrizeBondsByDirectDebitWhatDoINeedToDo(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"I'd like to purchase Prize Bonds by Direct Debit, what do I need to do?","Content");
        int index = testdata.getRowIndex("I'd like to purchase Prize Bonds by Direct Debit, what do I need to do?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextByAttribute(By.xpath("//article/p/a[1]"),String::equals,getUrl()+"/help-support/help-articles/how-can-i-buy-state-savings-products-(including-pr","href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[2]"),String::equals,getUrl()+"/getmedia/414315e4-a268-4124-8911-5165d4f8a7d2/PrizeBonds_DirectDebit_Mandate_1.pdf","href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[3]"),String::equals,getUrl()+"/help-support/help-articles/what-is-a-certified-copy-and-how-do-i-get-my-docum","href");
    }

    public void WhatDocumentationIsRequiredWhenPurchasingAStateSavingsProduct() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhatDocumentationIsRequiredWhenPurchasingAStateSavingsProduct(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"What documentation is required to become an Ireland State Savings Customer?","Content");
        int index = testdata.getRowIndex("What documentation is required to become an Ireland State Savings Customer?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextByAttribute(By.xpath("//article//a"),String::equals,getUrl()+"/help-and-support/help-articles/what-is-a-certified-copy-and-how-do-i-get-my-docum","href");

    }

    public void WhyDoYouRequireEvidenceOfIdentity() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhyDoYouRequireEvidenceOfIdentity(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"Why do you require Evidence of Identity?","Content");
        int index = testdata.getRowIndex("Why do you require Evidence of Identity?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        }


    public void WhyDoYouRequireMyPPSN() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhyDoYouRequireMyPPSN(): " + e.getMessage());
        }


        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"Why do you require my PPSN?","Content");
        int index = testdata.getRowIndex("Why do you require my PPSN?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);

    }



    public void WhatIsaFixedTermConfirmationLetter() throws Exception {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhatIsaFixedTermConfirmationLetter(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"What is a Fixed Term Confirmation Letter?","Content");
        int index = testdata.getRowIndex("What is a Fixed Term Confirmation Letter?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
       }

}

