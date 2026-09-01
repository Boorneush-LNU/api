package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class Kentico_HelpCategorySecurityAndFinancialCrime extends CategoryUtils {


    public Excelutils testData = new Excelutils();
    public By Content1Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)[1]");
    public By Content1Link2 = By.xpath("(//article[@class='m14-help_content--article']//a)[2]");
    public By Content2Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)");
    public By Content3Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)[1]");
    public By Content3Link2 = By.xpath("(//article[@class='m14-help_content--article']//a)[2]");
    public By FinancialLiteracy = By.xpath("(//div[@class='m13-3col_category_listing--container'])[1]//a[12]");

    String sheetName = "Security & Financial Crimes";
//    List<TestData> ExcelRows = testData.getTestData(config.getK13ContentExcelPath(), sheetName, TestData.class);
    List<TestData> ExcelRows = testData.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);
    public Kentico_HelpCategorySecurityAndFinancialCrime(WebDriver driver) {
        super(driver);


    }


    public void WhatYouCanDoToProtectYourIdentityAndYourSavingsWithIrelandStateSavings() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What you can do to protect your identity and your savings with Ireland State Savings");
        webUtil.gettextlog(articleHdr, String::equals, "What you can do to protect your identity and your savings with Ireland State Savings");
        int index = testData.getRowIndex("What you can do to protect your identity and your savings with Ireland State Savings", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareParagrph(ExcelRows, index);
        webUtil.gettextlog(Content3Link1, String::equals, "Ireland State Savings have robust controls in place to protect you");
        webUtil.gettextlog(Content3Link2, String::equals, "Here are the password requirements when creating a password for Ireland State Savings Online.");

        webUtil.gettextByAttribute(Content3Link1, String::equals, getUrl() + "/help-support/help-articles/how-we-protect-you", "href");
        webUtil.gettextByAttribute(Content3Link2, String::equals, getUrl() + "/help-support/help-articles/how-do-i-change-my-state-savings-online-password", "href");
    }


    public void ReportUnusualOrSuspiciousActivityImmediately() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Report unusual or suspicious activity immediately");
        webUtil.gettextlog(articleHdr, String::equals, "Report unusual or suspicious activity immediately");
        int index = testData.getRowIndex("Report unusual or suspicious activity immediately", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareParagrph(ExcelRows, index);
        webUtil.gettextlog(Content2Link1, String::equals, "service@statesavings.ie");

    }


    public void HowWeProtectYou() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How we protect you");
        webUtil.gettextlog(articleHdr, String::equals, "How we protect you");
        int index = testData.getRowIndex("How we protect you", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        compareParagrph(ExcelRows, index);
        webUtil.gettextlog(Content1Link1, String::equals, "security features signing into and using the Ireland State Savings Online service");
        webUtil.gettextlog(Content1Link2, String::equals, "Verification Codes");

        webUtil.gettextByAttribute(Content1Link1, String::equals, getUrl() + "/help-support/help-articles/security-of-state-savings-online", "href");
        webUtil.gettextByAttribute(Content1Link2, String::equals, getUrl() + "/help-support/help-articles/what-is-a-verification-code", "href");

    }


    public void securityAndFinancialCrime() {
        By securityFinancialCrime = By.xpath("(//div[@class='m13-3col_category_listing--container'])[1]//a[10]");
        webUtil.scrollToView(securityFinancialCrime);
        webUtil.clickLog(securityFinancialCrime, "Security And Financial Crime");
        webUtil.gettextlog(hdrlinkLbl, String::equals, "Security and Financial Crime", "Header");
        String[] SecurityLinks = {"How we protect you",
                "Report unusual or suspicious activity immediately",
                "What you can do to protect your identity and your savings with Ireland State Savings"};

        validateSize(commonFAQLinks, SecurityLinks, "Security and Financial crime");
        for (int b = 1; b <= SecurityLinks.length; b++) {

            String SecurityTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a"));
            By SecurityElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a");
            if (SecurityTab.equalsIgnoreCase(SecurityLinks[b - 1])) {
                switch (SecurityTab) {
                    case "How we protect you":
                        webUtil.scrollToView(SecurityElement);
                        webUtil.clickLog(SecurityElement, SecurityTab);
                        HowWeProtectYou();
                        webUtil.navigateBack();
                        break;

                    case "Report unusual or suspicious activity immediately":
                        webUtil.scrollToView(SecurityElement);
                        webUtil.clickLog(SecurityElement, SecurityTab);
                        ReportUnusualOrSuspiciousActivityImmediately();
                        webUtil.navigateBack();
                        break;

                    case "What you can do to protect your identity and your savings with Ireland State Savings":
                        webUtil.scrollToView(SecurityElement);
                        webUtil.clickLog(SecurityElement, SecurityTab);
                        WhatYouCanDoToProtectYourIdentityAndYourSavingsWithIrelandStateSavings();
                        webUtil.navigateBack();
                        break;

                }
            }
        }
        webUtil.clickLog(lnkHelpandSupport, "help and support link");

    }









}