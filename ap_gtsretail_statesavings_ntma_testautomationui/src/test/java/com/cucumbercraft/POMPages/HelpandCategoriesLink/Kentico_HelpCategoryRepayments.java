package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class Kentico_HelpCategoryRepayments extends CategoryUtils {

    public Excelutils testData = new Excelutils();
    String sheetName = "Repayments";
//    List<TestData> ExcelRows = testData.getTestData(config.getK13ContentExcelPath(), sheetName, TestData.class);
List<TestData> ExcelRows = testData.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);
    public Kentico_HelpCategoryRepayments(WebDriver driver) {
        super(driver);

    }


    public By Content1Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)");


    public By Content5Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)[1]");
    public By Content5Link2 = By.xpath("(//article[@class='m14-help_content--article']//a)[2]");


    public By Content4Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)[1]");
    public By Content4Link2 = By.xpath("(//article[@class='m14-help_content--article']//a)[2]");
    public By Content4Link3 = By.xpath("(//article[@class='m14-help_content--article']//a)[3]");
    public By Content4Link4 = By.xpath("(//article[@class='m14-help_content--article']//a)[4]");
    public By Content4Link5 = By.xpath("(//article[@class='m14-help_content--article']//a)[5]");
    public By Content4Link6 = By.xpath("(//article[@class='m14-help_content--article']//a)[6]");
    public By Content4Link7 = By.xpath("(//article[@class='m14-help_content--article']//a)[7]");
    public By Content4Link8 = By.xpath("(//article[@class='m14-help_content--article']//a)[8]");


    public void CanISplitReinvestmentsAcrossMultipleProductsInIrelandStateSavings() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Can I split reinvestments across multiple products in Ireland State Savings?");
        webUtil.gettextlog(articleHdr, String::equals, "Can I split reinvestments across multiple products in Ireland State Savings?");
        int index = testData.getRowIndex("Can I split reinvestments across multiple products in Ireland State Savings?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        compareHeader(ExcelRows, index);
        webUtil.gettextlog(Content1Link1, String::equals, "How do I manage my Bank Account details?");
        webUtil.gettextByAttribute(Content1Link1, String::equals, getUrl() + "/help-support/help-articles/how-do-i-add-my-bank-account-details-to-state-savi", "href");

    }


    public void HowDoICashInOneOfMyProductsInIrelandStateSavingsOnline() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How do I cash in one of my products in Ireland State Savings Online?");
        webUtil.gettextlog(articleHdr, String::equals, "How do I cash in one of my products in Ireland State Savings Online?");
        int index = testData.getRowIndex("How do I cash in one of my products in Ireland State Savings Online?", ExcelRows) - 1;
        compareArticle(ExcelRows, index);

    }


    public void HowDoIRequestARepaymentForAJointHoldingInIrelandStateSavingsOnline() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How do I request a repayment for a Joint Holding in Ireland State Savings Online?");
        webUtil.gettextlog(articleHdr, String::equals, "How do I request a repayment for a Joint Holding in Ireland State Savings Online?");
        int index = testData.getRowIndex("How do I request a repayment for a Joint Holding in Ireland State Savings Online?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);

    }


    public void TheMaturityValueOfMyNSBIssue12Or3HasTransferredToMyIrelandStateSavingsAccountHowDoIReinvestTheValueOfThisHolding() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles The maturity value of my NSB Issue 1,2 or 3 has transferred to my Ireland State Savings Account. How do I reinvest the value of this holding?");
        webUtil.gettextlog(articleHdr, String::equals, "The maturity value of my NSB Issue 1,2 or 3 has transferred to my Ireland State Savings Account. How do I reinvest the value of this holding?");
        int index = testData.getRowIndex("The maturity value of my NSB Issue 1,2 or 3 has transferred to my Ireland State Savings Account. How do I reinvest the value of this holding?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);

    }

    public void HowLongWillItTakeForRepaymentRequestToBeProcessed() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How long will it take for repayment request to be processed?");
        webUtil.gettextlog(articleHdr, String::equals, "How long will it take for repayment request to be processed?");
        int index = testData.getRowIndex("How long will it take for repayment request to be processed?", ExcelRows) - 1;
        compareArticle(ExcelRows, index);

        webUtil.gettextlog(Content5Link1, String::equals, "How do I cash in one of my products in Ireland State Savings Online?");
        webUtil.gettextlog(Content5Link2, String::equals, "Register for Ireland State Savings Online.");

        webUtil.gettextByAttribute(Content5Link1, String::equals, getUrl() + "/help-support/help-articles/how-do-i-cash-in-one-of-my-products-in-state-savin", "href");
        webUtil.gettextByAttribute(Content5Link2, String::equals, getUrl() + "/begin-registration", "href");

    }


    public void HowDoIRequestARepaymentOfMyIrelandStateSavingsproducts() {
        webUtil.scrollToView(breadcrumbs);
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How do I request a repayment of my Ireland State Savings products?");
        webUtil.gettextlog(articleHdr, String::equals, "How do I request a repayment of my Ireland State Savings products?");
        int index = testData.getRowIndex("How do I request a repayment of my Ireland State Savings products?", ExcelRows) - 1;
        compareArticle(ExcelRows, index);

        webUtil.gettextlog(Content4Link1, String::equals, "here");
        webUtil.gettextlog(Content4Link2, String::equals, "Prize Bonds Repayment Form");
        webUtil.gettextlog(Content4Link3, String::equals, "National Solidarity Bonds Repayment Form");
        webUtil.gettextlog(Content4Link4, String::equals, "Savings Bonds Repayment Form");
        webUtil.gettextlog(Content4Link5, String::equals, "Savings Certificates Repayment Form");
        webUtil.gettextlog(Content4Link6, String::equals, "Deposit Account (POSB) Repayment Form");
        webUtil.gettextlog(Content4Link7, String::equals, "All other Ireland State Savings Products");
        webUtil.gettextlog(Content4Link8, String::equals, "‘How do I cash in one of my products in Ireland State Savings Online?’");


        webUtil.gettextByAttribute(Content1Link1, String::equals, getUrl() + "/getmedia/08e807f6-a641-4606-8adc-2022c822e7bb/prizebonds_repayment_form.pdf", "href");
        webUtil.gettextByAttribute(Content4Link2, String::equals, getUrl() + "/getmedia/08e807f6-a641-4606-8adc-2022c822e7bb/prizebonds_repayment_form.pdf", "href");
        webUtil.gettextByAttribute(Content4Link3, String::equals, getUrl() + "/getmedia/494a563c-ffc1-4719-9845-9a28478e5cb8/nsbrepaymentformissue5.pdf", "href");
        webUtil.gettextByAttribute(Content4Link4, String::equals, getUrl() + "/getmedia/494a563c-ffc1-4719-9845-9a28478e5cb8/nsbrepaymentformissue5.pdf", "href");
        webUtil.gettextByAttribute(Content4Link5, String::equals, getUrl() + "/getmedia/494a563c-ffc1-4719-9845-9a28478e5cb8/nsbrepaymentformissue5.pdf", "href");
        webUtil.gettextByAttribute(Content4Link6, String::equals, getUrl() + "/getmedia/28972916-330b-4dbb-bdcb-50754ef30bda/ordinary_depositaccountrepayment_form.pdf", "href");
        webUtil.gettextByAttribute(Content4Link7, String::equals, getUrl() + "/getmedia/d9d0e8fe-f3b9-4639-931e-87b41f11e176/nsb-repaymentform-1-4.pdf", "href");
        webUtil.gettextByAttribute(Content4Link8, String::equals, getUrl() + "/help-support/help-articles/how-do-i-cash-in-one-of-my-products-in-state-savin", "href");


        webUtil.openPDFInNewTab(By.xpath("(//article[@class='m14-help_content--article']//a)[1]"), "prizebonds_repayment_form.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article[@class='m14-help_content--article']//a)[2]"), "prizebonds_repayment_form.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article[@class='m14-help_content--article']//a)[3]"), "nsbrepaymentformissue5.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article[@class='m14-help_content--article']//a)[4]"), "nsbrepaymentformissue5.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article[@class='m14-help_content--article']//a)[5]"), "nsbrepaymentformissue5.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article[@class='m14-help_content--article']//a)[6]"), "ordinary_depositaccountrepayment_form");
        webUtil.openPDFInNewTab(By.xpath("(//article[@class='m14-help_content--article']//a)[7]"), "nsb-repaymentform-1-4.pdf");

    }


    public void repayments() {
        try {
            By repayments = By.xpath("(//div[@class='m13-3col_category_listing--container'])[1]//a[6]");
            webUtil.scrollToView(repayments);
            webUtil.clickLog(repayments, "Repayments");

            webUtil.gettextlog(hdrlinkLbl, String::equals, "Repayments", "Header");
            String[] repayLinks = {"How do I request a repayment of my Ireland State Savings products?",
                    "How long will it take for repayment request to be processed?",
                    "Can I split reinvestments across multiple products in Ireland State Savings?",
                    "How do I cash in one of my products in Ireland State Savings Online?",
                    "How do I request a repayment for a Joint Holding in Ireland State Savings Online?",
                    "The maturity value of my NSB Issue 1,2 or 3 has transferred to my Ireland State Savings Account. How do I reinvest the value of this holding?"};
            validateSize(commonFAQLinks, repayLinks, "Repayments");
            for (int b = 1; b <= repayLinks.length; b++) {
                String repayTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a"));
                By repayElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a");
                if (repayTab.equalsIgnoreCase(repayLinks[b - 1])) {
                    switch (repayTab) {
                        case "How do I request a repayment of my Ireland State Savings products?":
                            webUtil.scrollToView(repayElement);
                            webUtil.clickLog(repayElement, repayTab);
                            HowDoIRequestARepaymentOfMyIrelandStateSavingsproducts();
                            webUtil.navigateBack();
                            break;
                        case "How long will it take for repayment request to be processed?":
                            webUtil.scrollToView(repayElement);
                            webUtil.clickLog(repayElement, repayTab);
                            HowLongWillItTakeForRepaymentRequestToBeProcessed();
                            webUtil.navigateBack();
                            break;
                        case "Can I split reinvestments across multiple products in Ireland State Savings?":
                            webUtil.scrollToView(repayElement);
                            webUtil.clickLog(repayElement, repayTab);
                            CanISplitReinvestmentsAcrossMultipleProductsInIrelandStateSavings();
                            webUtil.navigateBack();
                            break;
                        case "How do I cash in one of my products in Ireland State Savings Online?":
                            webUtil.scrollToView(repayElement);
                            webUtil.clickLog(repayElement, repayTab);
                            HowDoICashInOneOfMyProductsInIrelandStateSavingsOnline();
                            webUtil.navigateBack();
                            break;
                        case "How do I request a repayment for a Joint Holding in Ireland State Savings Online?":
                            webUtil.scrollToView(repayElement);
                            webUtil.clickLog(repayElement, repayTab);
                            HowDoIRequestARepaymentForAJointHoldingInIrelandStateSavingsOnline();
                            webUtil.navigateBack();
                            break;
                        case "The maturity value of my NSB Issue 1,2 or 3 has transferred to my Ireland State Savings Account. How do I reinvest the value of this holding?":
                            webUtil.scrollToView(repayElement);
                            webUtil.clickLog(repayElement, repayTab);
                            TheMaturityValueOfMyNSBIssue12Or3HasTransferredToMyIrelandStateSavingsAccountHowDoIReinvestTheValueOfThisHolding();
                            webUtil.navigateBack();
                            break;
                    }
                }
            }
            webUtil.clickLog(lnkHelpandSupport, "help and support link");


        } catch (Exception e) {

            Assert.fail("RepaymentsLinks -" + e.getMessage());
        }
    }

}
