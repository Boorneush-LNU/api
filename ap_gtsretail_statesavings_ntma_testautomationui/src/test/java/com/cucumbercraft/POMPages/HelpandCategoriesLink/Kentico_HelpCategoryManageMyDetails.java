package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import com.cucumbercraft.framework.DriverManager;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

@Log4j2
public class Kentico_HelpCategoryManageMyDetails extends CategoryUtils {

    
    public Excelutils testData = new Excelutils();
    private final WebDriver driver = DriverManager.getWebDriver();
    String sheetName = "Manage My Details";
//    List<TestData> ExcelRows = testData.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class);
List<TestData> ExcelRows = testData.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);
    String locatorAnchor="(//article//a)[%d]";


    public Kentico_HelpCategoryManageMyDetails(WebDriver driver) { super(driver);}





    public By ContentLink1 = By.xpath("//article//a");


    public By Content3Link1 = webUtil.getXPathWithIndex.apply(locatorAnchor,1);

    public By Content3Link2 = By.xpath("(//article//a)[2]");
    public By Content3Link3 = By.xpath("(//article//a)[3]");
    public By Content3Link4 = By.xpath("(//article//a)[4]");

    public By Content5Link1 = By.xpath("(//article//a)[1]");
    public By Content5Link2 = By.xpath("(//article//a)[2]");
    public By Content5Link3 = By.xpath("(//article//a)[3]");

    public By Content6Link1 = By.xpath("(//article//a)");

    public By Content9Link1 = By.xpath("(//article//a)[1]");

    public By Content10Link1 = By.xpath("(//article//a)");

    public By Content11Link1 = By.xpath("(//article//a)");


    public By Content12Link1 = By.xpath("(//article//a)[1]");
    public By Content12Link2 = By.xpath("(//article//a)[2]");
    public By Content12Link3 = By.xpath("(//article//a)[3]");
    public By Content12Link4 = By.xpath("(//article//a)[4]");
    public By Content12Link5 = By.xpath("(//article//a)[5]");
    public By Content12Link6 = By.xpath("(//article//a)[6]");
    public By Content12Link7 = By.xpath("(//article//a)[7]");
    public By Content12Link8 = By.xpath("(//article//a)[8]");
    public By Content12Link9 = By.xpath("(//article//a)[9]");


    public By Content13Link1 = By.xpath("(//article//a)[1]");
    public By Content13Link2 = By.xpath("(//article//a)[2]");


    public By Content15Link1 = By.xpath("(//article//a)[1]");
    public By Content15Link2 = By.xpath("(//article//a)[2]");
    public By Content15Link3 = By.xpath("(//article//a)[3]");
    public By Content15Link4 = By.xpath("(//article//a)[4]");
    public By Content15Link5 = By.xpath("(//article//a)[5]");
    public By Content15Link6 = By.xpath("(//article//a)[6]");
    public By Content15Link7 = By.xpath("(//article//a)[7]");
    public By Content15Link8 = By.xpath("(//article//a)[8]");




   


    public void CanIHaveRepaymentsMadeIntoAnInternationalBankAccount(){
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles Can I have repayments made into an international bank account?");
        webUtil.gettextlog(articleHdr, String::equals, "Can I have repayments made into an international bank account?");
        int index = testData.getRowIndex("Can I have repayments made into an international bank account?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        webUtil.gettextlog(ContentLink1, String::equals,"How do I manage my Bank Account details?");
        webUtil.gettextByAttribute(ContentLink1, String::equals, getUrl()+"/help-support/help-articles/how-do-i-add-my-bank-account-details-to-state-savi", "href");
    }

    public void HowDoIChangeTheBankAccountAssociatedWithMyIrelandStateSavingsHoldings(){
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How do I change the bank account associated with my Ireland State Savings holdings?");
        webUtil.gettextlog(articleHdr, String::equals, "How do I change the bank account associated with my Ireland State Savings holdings?");
        int index = testData.getRowIndex("How do I change the bank account associated with my Ireland State Savings holdings?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);

    }

    public void HowDoIChangeMyNameAndOrAddressOnMyIrelandStateSavingsProducts() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How do I change my name and/or address on my Ireland State Savings products?");
        webUtil.gettextlog(articleHdr, String::equals, "How do I change my name and/or address on my Ireland State Savings products?");
        int index = testData.getRowIndex("How do I change my name and/or address on my Ireland State Savings products?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareHeader(ExcelRows,index);
        webUtil.gettextlog(Content3Link1, String::equals, "here");
        webUtil.gettextlog(Content3Link2, String::equals, "certified copies of required proof documentation");
        webUtil.gettextlog(Content3Link3, String::equals, "here");
        webUtil.gettextlog(Content3Link4, String::equals, "certified copies of required proof documentation");
        webUtil.gettextByAttribute(Content3Link1, String::equals, getUrl()+"/getmedia/e3044347-960a-41af-807f-230ad9d60f16/changename_or_addressform.pdf", "href");
        webUtil.gettextByAttribute(Content3Link2, String::equals, getUrl()+"/help-and-support/help-articles/what-is-a-certified-copy-and-how-do-i-get-my-docum", "href");
        webUtil.gettextByAttribute(Content3Link3, String::equals, getUrl()+"/getmedia/713b9089-7b7e-450e-ba18-9c493ff7dd95/changeofaddressforsavingsandinvestment.pdf", "href");
        webUtil.gettextByAttribute(Content3Link4, String::equals, getUrl()+"/help-and-support/help-articles/what-is-a-certified-copy-and-how-do-i-get-my-docum", "href");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[1]"),"changename_or_addressform.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[3]"),"changeofaddressforsavingsandinvestment.pdf");
    }




    public void HowDoIChangeWhichBankAccountMyPrizeBondWinningsArePaidIntoInIrelandStateSavingsOnline(){
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How do I change which bank account my Prize Bond winnings are paid into in Ireland State Savings Online?");
        webUtil.gettextlog(articleHdr,String::equals,"How do I change which bank account my Prize Bond winnings are paid into in Ireland State Savings Online?");
        int index = testData.getRowIndex("How do I change which bank account my Prize Bond winnings are paid into in Ireland State Savings Online?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);

    }



    public void HowDoIRequestAStatementOfMyIrelandStateSavingsproducts() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How do I request a Statement of my Ireland State Savings products ?");
        webUtil.gettextlog(articleHdr, String::equals, "How do I request a Statement of my Ireland State Savings products ?");
        int index = testData.getRowIndex("How do I request a Statement of my Ireland State Savings products ?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareHeader(ExcelRows,index);
        webUtil.gettextlog(Content5Link1,String::equals,"Request For Statement of Accounts/Holdings Form");
        webUtil.gettextlog(Content5Link2,String::equals,"Request For Statement of Accounts/Holdings Form");
        webUtil.gettextlog(Content5Link3,String::equals,"Ireland State Savings Online");
        webUtil.gettextByAttribute(Content5Link1, String::equals, getUrl()+"/getmedia/daccea0b-3e4e-4224-9146-a76afa87da01/SS_StatementRequestForm_1.pdf", "href");
        webUtil.gettextByAttribute(Content5Link2, String::equals, getUrl()+"/getmedia/79666b3c-29c8-49f0-9206-7634a1fccdf0/ss_statementrequestform.pdf", "href");
        webUtil.gettextByAttribute(Content5Link3, String::equals, getUrl()+"/begin-registration", "href");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[1]"),"SS_StatementRequestForm.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[2]"),"ss_statementrequestform.pdf");


    }

    public void WhatAreMyRightsUnderTheDataProtectionLaw(){
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Redirecting to new Url : " + webUtil.getDriver().getCurrentUrl());
        }
        catch(Exception e){
            log.error("Error in WhatAreMyRightsUnderTheDataProtectionLaw(): " + e.getMessage());
        }

        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles What are my rights under the Data Protection Law?");
        webUtil.gettextlog(articleHdr, String::equals, "What are my rights under the Data Protection Law?");
        int index = testData.getRowIndex("What are my rights under the Data Protection Law?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[1]"), String::equals, getUrl() + "/help-support/help-articles/manage-my-details/state-savings-data-protection-statement", "href");
//        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[2]"), String::equals, getUrl() + "/our-products", "href"); // Not able to open the link.
    }



    public void HowDoYouUseMyData() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How do you use my personal data?");
        webUtil.gettextlog(articleHdr, String::equals, "How do you use my personal data?");
        int index = testData.getRowIndex("How do you use my personal data?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextlog(Content6Link1,String::equals,"Ireland State Savings Data Protection Notice");
        webUtil.gettextByAttribute(Content6Link1, String::equals, getUrl()+"/data-protection", "href");
    }

    public void HowHaveMyTermsAndConditionsChangedForGDPR() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles How have my Terms and Conditions changed for GDPR?");
        webUtil.gettextlog(articleHdr, String::equals, "How have my Terms and Conditions changed for GDPR?");
        int index = testData.getRowIndex("How have my Terms and Conditions changed for GDPR?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);
        compareHeader(ExcelRows,index);
    }



    public void IHaveLostTheDocumentForMyIrelandStateSavingProductWhatCanIdo() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles I have lost the document for my Ireland State Savings product. What can I do?");
        webUtil.gettextlog(articleHdr, String::equals, "I have lost the document for my Ireland State Savings product. What can I do?");
        int index = testData.getRowIndex("I have lost the document for my Ireland State Savings product. What can I do?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareHeader(ExcelRows,index);

    }

    public void IHaveNotReceivedAMaturityOptionFormForMyIrelandStateSavingsproductWhatCanIDo() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles I have not received a Maturity Option form for my Ireland State Savings product. What can I do?");
        webUtil.gettextlog(articleHdr, String::equals, "I have not received a Maturity Option form for my Ireland State Savings product. What can I do?");
        int index = testData.getRowIndex("I have not received a Maturity Option form for my Ireland State Savings product. What can I do?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);
        webUtil.gettextlog(Content9Link1,String::equals,"Change of Address form");
        webUtil.gettextByAttribute(Content9Link1, String::equals, getUrl()+"/getmedia/713b9089-7b7e-450e-ba18-9c493ff7dd95/changeofaddressforsavingsandinvestment.pdf", "href");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[1]"),"changeofaddressforsavingsandinvestment.pdf");
    }

    public void IfIAmACustomerAged65AndOverDoIQualifyToReceiveInterestWithoutDeductionOfDIRT() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles If I'm a customer aged 65 and over, do I qualify to receive interest without deduction of DIRT?");
        webUtil.gettextlog(articleHdr, String::equals, "If I'm a customer aged 65 and over, do I qualify to receive interest without deduction of DIRT?");
        int index = testData.getRowIndex("If I'm a customer aged 65 and over, do I qualify to receive interest without deduction of DIRT?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);
        webUtil.gettextlog(Content10Link1,String::equals,"service@statesavings.ie");
        webUtil.gettextByAttribute(Content10Link1, String::equals, "mailto:Service@statesavings.ie", "href");


    }
    public void IfIAmANonResidentCustomerCanIClaimInterestWithoutDeductionOfDIRT() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles If I'm a non-resident customer, can I claim interest without deduction of DIRT?");
        webUtil.gettextlog(articleHdr, String::equals, "If I'm a non-resident customer, can I claim interest without deduction of DIRT?");
        int index = testData.getRowIndex("If I'm a non-resident customer, can I claim interest without deduction of DIRT?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);
        webUtil.gettextlog(Content11Link1,String::equals,"service@statesavings.ie");
        webUtil.gettextByAttribute(Content11Link1, String::equals, "mailto:service@statesavings.ie", "href");


    }



    public void IsItPossibleToAmendTransferOwnershipOfMyIrelandStateSavingsproducts() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles Is it possible to amend/transfer ownership of my Ireland State Savings products?");
        webUtil.gettextlog(articleHdr, String::equals, "Is it possible to amend/transfer ownership of my Ireland State Savings products?");
        int index = testData.getRowIndex("Is it possible to amend/transfer ownership of my Ireland State Savings products?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareHeader(ExcelRows,index);
        webUtil.gettextlog(Content13Link1,String::equals,"Addition of Name To Deposit Account Form");
//        webUtil.gettextlog(Content13Link2,String::equals,"Addition of Name To Deposit Account Form");
//        webUtil.gettextByAttribute(Content13Link1, String::equals, getUrl()+"/getmedia/e3f3d692-a144-4ab9-91fb-cda40e655ae9/pb-_apptransferbonds_2017.pdf", "href");
        webUtil.gettextByAttribute(Content13Link1, String::equals, getUrl()+"/getmedia/f11fe2e3-4b4d-4430-b3c0-7da10b0e62d0/CSAdditionof-Name-Deposit-Account-10-2017.pdf", "href");
//        webUtil.openPDFInNewTab(By.xpath("(//article//a)[1]"),"pb-_apptransferbonds_2017.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[1]"),"CSAdditionof-Name-Deposit-Account-10-2017.pdf");
    }


    public void WhatIsACertifiedCopyAndHowDoIGetMyDocumentCertified() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles What is a certified copy and how do I get my document certified?");
        webUtil.gettextlog(articleHdr, String::equals, "What is a certified copy and how do I get my document certified?");
        int index = testData.getRowIndex("What is a certified copy and how do I get my document certified?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);

    }


    public void WhatIsTheStateSavingsCustomerNumber() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles What is the State Savings Customer Number (SSCN)?");
        webUtil.gettextlog(articleHdr, String::equals, "What is the State Savings Customer Number (SSCN)?");
        int index = testData.getRowIndex("What is the State Savings Customer Number (SSCN)?",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareHeader(ExcelRows,index);
        compareBullet2(ExcelRows, index);
        webUtil.gettextlog(Content15Link1,String::equals,"Watch Video: ‘Your State Savings Customer Number (SSCN)’");
        webUtil.gettextlog(Content15Link2,String::equals,"Evidence of Identity documentation");
        webUtil.gettextlog(Content15Link3,String::equals,"Contact us");
        webUtil.gettextlog(Content15Link4,String::equals,"here");
        webUtil.gettextlog(Content15Link5,String::equals,"Contact us");
        webUtil.gettextlog(Content15Link6,String::equals,"Contact us");
        webUtil.gettextlog(Content15Link7,String::equals,"Prize Bond Gift Application Form");


        webUtil.gettextByAttribute(Content15Link1, String::equals, getUrl()+"/sscn", "href");
        webUtil.gettextByAttribute(Content15Link2, String::equals, getUrl()+"/help-support/help-articles/what-documentation-is-required-when-purchasing-a-s", "href");
        webUtil.gettextByAttribute(Content15Link3, String::equals, getUrl()+"/help-support/contact-us", "href");
        webUtil.gettextByAttribute(Content15Link4, String::equals, getUrl()+"/help-support/forms-downloads/application-forms", "href");
        webUtil.gettextByAttribute(Content15Link5, String::equals, getUrl()+"/help-support/contact-us", "href");
        webUtil.gettextByAttribute(Content15Link6, String::equals, getUrl()+"/help-support/contact-us", "href");
        webUtil.gettextByAttribute(Content15Link7, String::equals, getUrl()+"/getmedia/a04ad920-4f0c-4ea1-b240-291070db4e06/prizebonds_gift_application_form.pdf", "href");

        webUtil.openPDFInNewTab(By.xpath("(//article//a)[7]"),"prizebonds_gift_application_form.pdf");




    }


    @SneakyThrows
    public void IrelandStateSavingsDataProtectionStatement() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles Ireland State Savings Data Protection Statement");
        webUtil.gettextlog(articleHdr, String::equals, "Ireland State Savings Data Protection Statement");
        int index = testData.getRowIndex("Ireland State Savings Data Protection Statement",ExcelRows)-1;
        compareParagrph(ExcelRows,index);
        compareBullets(ExcelRows,index);
        compareHeader(ExcelRows,index);
        compareSubHeader(ExcelRows,index);
        compareTable(ExcelRows,index);
        comparesubLinks(ExcelRows,index);




        webUtil.gettextByAttribute(Content12Link1, String::equals, "https://www.ntma.ie/information-pages/data-protection-statement/", "href");
        webUtil.gettextByAttribute(Content12Link2, String::equals, "mailto:dpo@ntma.ie", "href");
        webUtil.gettextByAttribute(Content12Link3, String::equals, "mailto:Service@StateSavings.ie", "href");
        webUtil.gettextByAttribute(Content12Link4, String::equals, "mailto:PrizeBonds@StateSavings.ie", "href");
        webUtil.gettextByAttribute(Content12Link5, String::equals, "https://www.facebook.com/legal/terms/page_controller_addendum", "href");
        webUtil.gettextByAttribute(Content12Link6, String::equals, "mailto:Service@StateSavings.ie", "href");
        webUtil.gettextByAttribute(Content12Link7, String::equals, "mailto:PrizeBonds@StateSavings.ie", "href");
        webUtil.gettextByAttribute(Content12Link8, String::equals, "http://dataprotection.ie/", "href");
        webUtil.gettextByAttribute(Content12Link9, String::equals, "mailto:privacyoffice@anpost.ie", "href");

        webUtil.openLinkInNewTab(By.xpath("(//article//a)[1]"),"Data Protection Statement | NTMA");
//        webUtil.openLinkInNewTab(Content12Link8,"Privacy error"); // Change to "Privacy error". If this fails



    }


    public void manageMyDetails(){
        try{
            By manageMyDetails= By.xpath("(//div[@class='m13-3col_category_listing--container'])[1]//a[4]");
            webUtil.scrollToView(manageMyDetails);
            webUtil.clickLog(manageMyDetails, "Manage My Details");
            webUtil.gettextlog(hdrlinkLbl,String::equals, "Manage My Details", "Header");
            String MMDLinks[]={
                    "How do I change my name and/or address on my Ireland State Savings products?",
                    "How do I request a Statement of my Ireland State Savings products ?",
                    "How do you use my personal data?",
                    "What are my rights under the Data Protection Law?",
                    "I have lost the document for my Ireland State Savings product. What can I do?",
                    "I have not received a Maturity Option form for my Ireland State Savings product. What can I do?",
                    "Is it possible to amend/transfer ownership of my Ireland State Savings products?",
                    "What is a certified copy and how do I get my document certified?",
                    "If I'm a customer aged 65 and over, do I qualify to receive interest without deduction of DIRT?",
                    "If I'm a non-resident customer, can I claim interest without deduction of DIRT?",
                    "How do I change which bank account my Prize Bond winnings are paid into in Ireland State Savings Online?",
                    "How do I change the bank account associated with my Ireland State Savings holdings?",
                    "Can I have repayments made into an international bank account?",
                    "What is the State Savings Customer Number (SSCN)?",
                    "Ireland State Savings Data Protection Statement",
                    };
            validateSize(commonFAQLinks,MMDLinks,"Manage My Details");
            for(int b=1;b<=MMDLinks.length;b++){
                String MMDTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li["+b+"]/a"));
                By MMDElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li["+b+"]/a");
                if(MMDTab.equalsIgnoreCase(MMDLinks[b-1])){
                    switch (MMDTab){
                        case "How do I change my name and/or address on my Ireland State Savings products?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            HowDoIChangeMyNameAndOrAddressOnMyIrelandStateSavingsProducts();
                            webUtil.navigateBack();
                            break;
                        case "How do I request a Statement of my Ireland State Savings products ?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            HowDoIRequestAStatementOfMyIrelandStateSavingsproducts();
                            webUtil.navigateBack();
                            break;
                        case "What are my rights under the Data Protection Law?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            WhatAreMyRightsUnderTheDataProtectionLaw();
                            webUtil.navigateBack();
                            break;
                        case "How do you use my personal data?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            HowDoYouUseMyData();
                            webUtil.navigateBack();
                            break;
                        case "How have my Terms and Conditions changed for GDPR?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            HowHaveMyTermsAndConditionsChangedForGDPR();
                            webUtil.navigateBack();
                            break;
                        case "I have lost the document for my Ireland State Savings product. What can I do?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            IHaveLostTheDocumentForMyIrelandStateSavingProductWhatCanIdo();
                            webUtil.navigateBack();
                            break;
                        case "I have not received a Maturity Option form for my Ireland State Savings product. What can I do?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            IHaveNotReceivedAMaturityOptionFormForMyIrelandStateSavingsproductWhatCanIDo();
                            webUtil.navigateBack();
                            break;

                        case "Is it possible to amend/transfer ownership of my Ireland State Savings products?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            IsItPossibleToAmendTransferOwnershipOfMyIrelandStateSavingsproducts();
                            webUtil.navigateBack();
                            break;
                        case "What is a certified copy and how do I get my document certified?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            WhatIsACertifiedCopyAndHowDoIGetMyDocumentCertified();
                            webUtil.navigateBack();
                            break;
                        case "If I'm a customer aged 65 and over, do I qualify to receive interest without deduction of DIRT?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            IfIAmACustomerAged65AndOverDoIQualifyToReceiveInterestWithoutDeductionOfDIRT();
                            webUtil.navigateBack();
                            break;
                        case "If I'm a non-resident customer, can I claim interest without deduction of DIRT?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            IfIAmANonResidentCustomerCanIClaimInterestWithoutDeductionOfDIRT();
                            webUtil.navigateBack();
                            break;
                        case "How do I change which bank account my Prize Bond winnings are paid into in Ireland State Savings Online?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            HowDoIChangeWhichBankAccountMyPrizeBondWinningsArePaidIntoInIrelandStateSavingsOnline();
                            webUtil.navigateBack();
                            break;
                        case "How do I change the bank account associated with my Ireland State Savings holdings?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            HowDoIChangeTheBankAccountAssociatedWithMyIrelandStateSavingsHoldings();
                            webUtil.navigateBack();
                            break;
                        case "Can I have repayments made into an international bank account?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            CanIHaveRepaymentsMadeIntoAnInternationalBankAccount();
                            webUtil.navigateBack();
                            break;

                        case "What is the State Savings Customer Number (SSCN)?":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            WhatIsTheStateSavingsCustomerNumber();
                            webUtil.navigateBack();
                            break;

                        case "Ireland State Savings Data Protection Statement":
                            webUtil.scrollToView(MMDElement);
                            webUtil.clickLog(MMDElement, MMDTab);
                            IrelandStateSavingsDataProtectionStatement();
                            webUtil.navigateBack();
                            break;


                    }
                }

            }
            webUtil.clickLog(lnkHelpandSupport, "help and support link");
            
           
        }catch(Exception e){
            
            Assert.fail("ManageMyDetailsLinks -" + e.getMessage());
        }
    }


    public void clickStateSavingsNumber() {
        try {
            By sscnLink = By.xpath("//a[text()='What is the State Savings Customer Number (SSCN)?']");
            webUtil.scrollToView(sscnLink);
            webUtil.clickLog(sscnLink, "SSCN FAQ");

        } catch (Exception e) {
            Assert.fail("StateSavingsNumber - " + e.getMessage());
        }
    }

    public void clickWatchVideoSSCN() {
        try {
            By watchVideoLink = By.xpath("//a[contains(text(),'Watch Video') and contains(text(),'Your State Savings Customer Number')]");
            webUtil.scrollToView(watchVideoLink);
            webUtil.clickLog(watchVideoLink, "Watch SSCN Video");

        } catch (Exception e) {
            Assert.fail("StateSavingsNumber - " + e.getMessage());
        }
    }


}


