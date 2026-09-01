package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class Kentico_HelpCategoryFinancialLiteracy extends CategoryUtils {
    public By FinancialLiteracy = By.xpath("(//div[@class='m13-3col_category_listing--container'])[1]//a[11]");
    public By CountLinks = By.xpath("//article//ul/li/a");
    public By CountLinks1 = By.xpath("//article/ul/li");
    public By SideBar = By.linkText("Financial Literacy");
    public By FAQFL = By.xpath("//a[@href='/help-support/help-articles/what-is-financial-literacy']");

    public Excelutils testdata = new Excelutils();
    String sheetName = "Financial Literacy";
//    List<TestData> ExcelRows = testdata.getTestData(config.getK13ContentExcelPath(), sheetName, TestData.class);
    List<TestData> ExcelRows = testdata.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class); // Merged 2 Excel
    public By RelatedLink1 = By.xpath("//article[@class='m14-help_content--article']//li[1]//p/a");
    public By RelatedLink2 = By.xpath("//article[@class='m14-help_content--article']//li[2]//p/a");
    public By RelatedLink3 = By.xpath("//article[@class='m14-help_content--article']//li[3]//p/a");

    public Kentico_HelpCategoryFinancialLiteracy(WebDriver driver) {
        super(driver);
    }

    public void WhatisFinanacialLiteracy() {
        try {
            List<WebElement> NoOfLinks = webUtil.getDriver().findElements(CountLinks1);
            ExtentCucumberAdapter.addTestStepLog("Total Links Found: " + NoOfLinks.size());
            for (WebElement link : NoOfLinks) {
                ExtentCucumberAdapter.addTestStepLog("Validating Link: " + link.getText());
            }

            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What is Financial Literacy?");
            ExtentCucumberAdapter.addTestStepLog("Validating BreadCrumbs: " + webUtil.getText(breadcrumbs));
            webUtil.gettextlog(articleHdr, String::equals, "What is Financial Literacy?");
            ExtentCucumberAdapter.addTestStepLog("Validating Article Header: " + webUtil.getText(articleHdr));

            if (webUtil.isElementDisplayed(SideBar, 2000)) {
                log.info("Financial Literacy Header is PRESENT in the Side Bar.");
                ExtentCucumberAdapter.addTestStepLog("Validating Financial Literacy Header in the Side Bar : " + webUtil.getText(SideBar));
            } else {
                log.error("Financial Literacy Header is NOT present.");
            }
            int index = testdata.getRowIndex("What is Financial Literacy?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);
            webUtil.gettextlog(RelatedArticle, String::equals, "Related articles:");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(RelatedArticle));
            webUtil.gettextlog(RelatedLink1, String::equals, "Why is Financial Literacy important to you?");
            webUtil.gettextlog(RelatedLink2, String::equals, "How can I start improving my own Financial Literacy?");
            webUtil.gettextlog(RelatedLink3, String::equals, "Where can I find more information about Financial Literacy?");
        } catch (Exception e) {
            Assert.fail("validateLinks " + e.getMessage());
        }
    }

    public void WhyIsFinancialLiteracyImportantToYou() {
        try {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Why is Financial Literacy important to you?");
            ExtentCucumberAdapter.addTestStepLog("Validating BreadCrumbs: " + webUtil.getText(breadcrumbs));
            webUtil.gettextlog(articleHdr, String::equals, "Why is Financial Literacy important to you?");
            ExtentCucumberAdapter.addTestStepLog("Validating Article Header: " + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("Why is Financial Literacy important to you?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);
        } catch (Exception e) {
            Assert.fail("validateLinks " + e.getMessage());
        }

    }

    public void HowCanIStartImprovingMyOwnFinancialLiteracy() {
        try {

            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How can I start improving my own Financial Literacy?");
            ExtentCucumberAdapter.addTestStepLog("Validating BreadCrumbs: " + webUtil.getText(breadcrumbs));
            webUtil.gettextlog(articleHdr, String::equals, "How can I start improving my own Financial Literacy?");
            ExtentCucumberAdapter.addTestStepLog("Validating Article Header: " + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("How can I start improving my own Financial Literacy?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);
            compareBullets(ExcelRows, index);
            comparesubLinks(ExcelRows, index);
            webUtil.gettextByAttribute(By.xpath("//article/ul/li[4]/p/a"), String::equals, getUrl() + "/help-support/help-articles/where-can-i-find-more-information-about-financial-literacy", "href");
            webUtil.gettextByAttribute(By.xpath("//article/p[2]/a"), String::equals, getUrl() + "/our-products", "href");
        } catch (Exception e) {
            Assert.fail("validateLinks " + e.getMessage());
        }

    }


    public void WherecanIfindmoreinformationaboutFinancialLiteracy() {
        try {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Where can I find more information about Financial Literacy?");
            ExtentCucumberAdapter.addTestStepLog("Validating BreadCrumbs: " + webUtil.getText(breadcrumbs));
            webUtil.gettextlog(articleHdr, String::equals, "Where can I find more information about Financial Literacy?");
            ExtentCucumberAdapter.addTestStepLog("Validating Article Header: " + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("Where can I find more information about Financial Literacy?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);
            compareBullets(ExcelRows, index);
            comparesubLinks(ExcelRows, index);
            webUtil.gettextByAttribute(By.xpath("//article/ul/li[1]/p/a"), String::equals, "https://www.mabs.ie/", "href");
            webUtil.gettextByAttribute(By.xpath("//article/ul/li[2]/p/a"), String::equals, "https://www.ccpc.ie/", "href");
            webUtil.gettextByAttribute(By.xpath("//article/ul/li[3]/p/a[1]"), String::equals, "https://www.adultliteracyforlife.ie/", "href");
            webUtil.gettextByAttribute(By.xpath("//article/ul/li[3]/p/a[2]"), String::equals, "https://www.adultliteracyforlife.ie/free-courses/", "href");
        } catch (Exception e) {
            Assert.fail("validateLinks " + e.getMessage());
        }
    }


    public void FinancialLiteracy() {
        try {
            webUtil.HoverOnElement(FAQFL,3000);
            if (webUtil.isElementDisplayed(FAQFL, 1000)) {
                ExtentCucumberAdapter.addTestStepLog("Validating Financial Literacy Question in FAQ : " + webUtil.getText(FAQFL));
            } else {
                log.error("Financial Literacy Question link is NOT present.");
            }

            webUtil.isElementDisplayed(FinancialLiteracy, 5);
            webUtil.clickLog(FinancialLiteracy, "Financial Literacy");

            webUtil.gettextlog(hdrlinkLbl, String::equals, "Financial Literacy", "Header");
            String[] FinancialLiteracyLinks = {"What is Financial Literacy?",
                    "Why is Financial Literacy important to you?",
                    "How can I start improving my own Financial Literacy?",
                    "Where can I find more information about Financial Literacy?"};

            List<WebElement> NoOfLinks = webUtil.getDriver().findElements(CountLinks);
            ExtentCucumberAdapter.addTestStepLog("Total Links Found: " + NoOfLinks.size());
            for (WebElement link : NoOfLinks) {
                ExtentCucumberAdapter.addTestStepLog("Validating Link: " + link.getText());
            }

            validateSize(commonFAQLinks, FinancialLiteracyLinks, "What is Financial Literacy");
            for (int b = 1; b <= FinancialLiteracyLinks.length; b++) {
                String FLTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a"));
                By FLElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a");
                webUtil.waitFor(1000);
                if (FLTab.equals(FinancialLiteracyLinks[b - 1])) {
                    switch (FLTab) {
                        case "What is Financial Literacy?":
                            webUtil.scrollToView(FLElement);
                            webUtil.clickLog(FLElement, FLTab);
                            WhatisFinanacialLiteracy();
                            webUtil.navigateBack();
                            break;
                        case "Why is Financial Literacy important to you?":
                            webUtil.scrollToView(FLElement);
                            webUtil.clickLog(FLElement, FLTab);
                            WhyIsFinancialLiteracyImportantToYou();
                            webUtil.navigateBack();
                            break;
                        case "How can I start improving my own Financial Literacy?":
                            webUtil.scrollToView(FLElement);
                            webUtil.clickLog(FLElement, FLTab);
                            HowCanIStartImprovingMyOwnFinancialLiteracy();
                            webUtil.navigateBack();
                            break;
                        case "Where can I find more information about Financial Literacy?":
                            webUtil.scrollToView(FLElement);
                            webUtil.clickLog(FLElement, FLTab);
                            WherecanIfindmoreinformationaboutFinancialLiteracy();
                            webUtil.navigateBack();
                            break;

                    }
                }


            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}










