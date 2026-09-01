package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;


public class Kentico_HelpCategoryIrelandStateSavingsProducts extends CategoryUtils {


    public Excelutils testData = new Excelutils();
    public By Content4Link1 = By.xpath("(//article//a)[1]");
    public By Content4Link2 = By.xpath("(//article//a)[2]");
    public By Content3Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)[1]");
    public By Content3Link2 = By.xpath("(//article[@class='m14-help_content--article']//a)[2]");
    public By Content7Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)[1]");
    public By Content9Link1 = By.xpath("(//article[@class='m14-help_content--article']//a)[1]");
    public By Content9Link2 = By.xpath("(//article[@class='m14-help_content--article']//a)[2]");
    public By Content9Link3 = By.xpath("(//article[@class='m14-help_content--article']//a)[3]");
    public By Content9Link4 = By.xpath("(//article[@class='m14-help_content--article']//a)[4]");
    public By Content9Link5 = By.xpath("(//article[@class='m14-help_content--article']//a)[5]");
    public By Content9Link6 = By.xpath("(//article[@class='m14-help_content--article']//a)[6]");
    public By Content9Link7 = By.xpath("(//article[@class='m14-help_content--article']//a)[7]");
    String sheetName = "Ireland State Savings Products";
//    List<TestData> ExcelRows = testData.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class);
List<TestData> ExcelRows = testData.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);
    public Kentico_HelpCategoryIrelandStateSavingsProducts(WebDriver driver) {
        super(driver);
    }




    public void AreMyIrelandStateSavingsProductsGuaranteed() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Are my Ireland State Savings products guaranteed?");
        webUtil.gettextlog(articleHdr, String::equals, "Are my Ireland State Savings products guaranteed?");
        int index = testData.getRowIndex("Are my Ireland State Savings products guaranteed?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
    }

    public void ArePrizeBondsWinningsOrIrelandStateSavingsProductsSubjectToTax() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Are Prize Bonds winnings or Ireland State Savings products subject to Tax?");
        webUtil.gettextlog(articleHdr, String::equals, "Are Prize Bonds winnings or Ireland State Savings products subject to Tax?");
        int index = testData.getRowIndex("Are Prize Bonds winnings or Ireland State Savings products subject to Tax?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
    }

    public void CanIPurchaseIrelandStateSavingsProductsForAMinorUnder18() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Can I purchase Ireland State Savings Products for a Minor (under 18)?");
        webUtil.gettextlog(articleHdr, String::equals, "Can I purchase Ireland State Savings Products for a Minor (under 18)?");
        int index = testData.getRowIndex("Can I purchase Ireland State Savings Products for a Minor (under 18)?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        webUtil.gettextlog(Content3Link1, String::equals, "here");
        webUtil.gettextlog(Content3Link2, String::equals, "'What documentation is required when purchasing an Ireland State Savings Product?'");

        webUtil.gettextByAttribute(Content3Link1, String::equals, getUrl() + "/getmedia/c9664906-cca1-49c9-b9b8-df4439a94127/new-customer-application-form.pdf", "href");
        webUtil.gettextByAttribute(Content3Link2, String::equals, getUrl() + "/help-support/help-articles/what-documentation-is-required-when-purchasing-a-s", "href");
        webUtil.openPDFInNewTab(Content3Link1, "new-customer-application-form.pdf");

    }


    public void HowAreInterestRatesSetForIrelandStateSavingsProducts() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How are Interest Rates set for Ireland State Savings Products?");
        webUtil.gettextlog(articleHdr, String::equals, "How are Interest Rates set for Ireland State Savings Products?");
        int index = testData.getRowIndex("How are Interest Rates set for Ireland State Savings Products?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);

    }

    public void UpdatedGeneralTermsAndConditionsForIrelandStateSavingsEffectiveFromThe31stJuly2024() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Updated General Terms and Conditions for Ireland State Savings effective from the 31st July 2024");
        webUtil.gettextlog(articleHdr, String::equals, "Updated General Terms and Conditions for Ireland State Savings effective from the 31st July 2024");
        int index = testData.getRowIndex("Updated General Terms and Conditions for Ireland State Savings effective from the 31st July 2024", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        webUtil.gettextlog(Content7Link1, String::equals, "clicking here");
        webUtil.gettextByAttribute(Content7Link1, String::equals, getUrl() + "/terms-and-conditions", "href");
        webUtil.openPDFInNewTab(Content7Link1, getUrl() + "/terms-and-conditions");

    }


    public void WhatIsTheIrelandStateSavingsProductRange() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What is the Ireland State Savings product range?");
        webUtil.gettextlog(articleHdr, String::equals, "What is the Ireland State Savings product range?");
        int index = testData.getRowIndex("What is the Ireland State Savings product range?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        webUtil.gettextlog(Content9Link1, String::equals, "3 Year Savings Bonds");
        webUtil.gettextlog(Content9Link2, String::equals, "5 Year Savings Certificates");
        webUtil.gettextlog(Content9Link3, String::equals, "10 Year National Solidarity Bonds");
        webUtil.gettextlog(Content9Link4, String::equals, "Prize Bonds");
        webUtil.gettextlog(Content9Link5, String::equals, "Instalment Savings Scheme");
        webUtil.gettextlog(Content9Link6, String::equals, "Childcare Plus");
        webUtil.gettextlog(Content9Link7, String::equals, "Deposit Account (Post Office Savings Bank)");
        webUtil.gettextByAttribute(Content9Link1, String::equals, getUrl() + "/our-products/3-year-savings-bonds", "href");
        webUtil.gettextByAttribute(Content9Link2, String::equals, getUrl() + "/our-products/5-year-savings-certificates", "href");
        webUtil.gettextByAttribute(Content9Link3, String::equals, getUrl() + "/our-products/10-year-national-solidarity-bond", "href");
        webUtil.gettextByAttribute(Content9Link4, String::equals, getUrl() + "/prize-bonds", "href");
        webUtil.gettextByAttribute(Content9Link5, String::equals, getUrl() + "/our-products/installment-savings", "href");
        webUtil.gettextByAttribute(Content9Link6, String::equals, getUrl() + "/our-products/childcare-plus-6yr", "href");
        webUtil.gettextByAttribute(Content9Link7, String::equals, getUrl() + "/our-products/book-based-deposit-account", "href");

    }


    public void WhatIsTheRelationshipBetweenTheNationalTreasuryManagementAgencyAnPostAndThePrizeBondCompany() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What is the relationship between the National Treasury Management Agency, An Post and the Prize Bond Company?");
        webUtil.gettextlog(articleHdr, String::equals, "What is the relationship between the National Treasury Management Agency, An Post and the Prize Bond Company?");
        int index = testData.getRowIndex("What is the relationship between the National Treasury Management Agency, An Post and the Prize Bond Company?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);

    }


    public void WillIPayAnyFeesChargesOrCommission() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Will I pay any fees, charges or commission?");
        webUtil.gettextlog(articleHdr, String::equals, "Will I pay any fees, charges or commission?");
        int index = testData.getRowIndex("Will I pay any fees, charges or commission?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
    }


    public void IsThereAnyLimitOnMyOverallHoldingOfIrelandStateSavingsProducts() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Is there any limit on my overall holding of Ireland State Savings Products?");
        webUtil.gettextlog(articleHdr, String::equals, "Is there any limit on my overall holding of Ireland State Savings Products?");
        int index = testData.getRowIndex("Is there any limit on my overall holding of Ireland State Savings Products?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareTable(ExcelRows, index);

    }


    public void WhatAreTheRatesAsOf1stOctober2023() {
        webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What are the rates as of 1st October 2023?");
        webUtil.gettextlog(articleHdr, String::equals, "What are the rates as of 1st October 2023?");
        int index = testData.getRowIndex("What are the rates as of 1st October 2023?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareTable(ExcelRows, index);

    }

    public void CanISaveMyMonthlyChildBenefitWithIrelandStateSavings() {
        webUtil.gettextlog(articleHdr, String::equals, "Can I save my monthly Child Benefit with Ireland State Savings?");
        int index = testData.getRowIndex("Can I save my monthly Child Benefit with Ireland State Savings?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
//        compareBullet2(ExcelRows, index);
        webUtil.gettextlog(Content4Link1, String::equals, "statesavings.ie");
        webUtil.gettextlog(Content4Link2, String::equals, "https://www.gov.ie/");
        webUtil.gettextByAttribute(Content4Link1, String::equals, getUrl() + "/", "href");
        webUtil.gettextByAttribute(Content4Link2, String::equals, "https://www.gov.ie/", "href");
        webUtil.openLinkInNewTab(By.xpath("(//article//a)[2]"), "Government of Ireland: Welcome to gov.ie");


    }


    public void stateSavingsProduct() {
        try {
            By stateSavingsProduct = By.xpath("(//div[@class='m13-3col_category_listing--container'])[1]//a[5]");
            webUtil.scrollToView(stateSavingsProduct);
            webUtil.clickLog(stateSavingsProduct, "Ireland State Savings Product");
            webUtil.gettextlog(articleHdr, String::equals, "Ireland State Savings Products", "Header");
            String[] SSPLinks = {"Are my Ireland State Savings products guaranteed?",
                    "Are Prize Bonds winnings or Ireland State Savings products subject to Tax?",
                    "Can I purchase Ireland State Savings Products for a Minor (under 18)?",
                    "How are Interest Rates set for Ireland State Savings Products?",
                    "Is there any limit on my overall holding of Ireland State Savings Products?",
                    "What is the relationship between the National Treasury Management Agency, An Post and the Prize Bond Company?",
                    "What is the Ireland State Savings product range?",
                    "Will I pay any fees, charges or commission?",
                    "What are the rates as of 1st October 2023?",
                    "Can I save my monthly Child Benefit with Ireland State Savings?",
                    "Updated General Terms and Conditions for Ireland State Savings effective from the 31st July 2024"


                   };

            validateSize(commonFAQLinks, SSPLinks, "Ireland State Savings Products");
            for (int b = 1; b <= SSPLinks.length; b++) {
                String SSPTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a"));
                By SSPElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a");
                webUtil.waitFor(1000);
                if (SSPTab.equalsIgnoreCase(SSPLinks[b - 1])) {
                    switch (SSPTab) {
                        case "Are my Ireland State Savings products guaranteed?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            AreMyIrelandStateSavingsProductsGuaranteed();
                            webUtil.navigateBack();
                            break;
                        case "Are Prize Bonds winnings or Ireland State Savings products subject to Tax?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            ArePrizeBondsWinningsOrIrelandStateSavingsProductsSubjectToTax();
                            webUtil.navigateBack();
                            break;

                        case "Can I purchase Ireland State Savings Products for a Minor (under 18)?":

                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            CanIPurchaseIrelandStateSavingsProductsForAMinorUnder18();
                            webUtil.navigateBack();
                            break;
                        case "Can I save my monthly Child Benefit with Ireland State Savings?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            CanISaveMyMonthlyChildBenefitWithIrelandStateSavings();
                            webUtil.navigateBack();
                            break;

                        case "How are Interest Rates set for Ireland State Savings Products?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            HowAreInterestRatesSetForIrelandStateSavingsProducts();
                            webUtil.navigateBack();
                            break;


                        case "Is there any limit on my overall holding of Ireland State Savings Products?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            IsThereAnyLimitOnMyOverallHoldingOfIrelandStateSavingsProducts();
                            webUtil.navigateBack();
                            break;
                        case "What is the relationship between the National Treasury Management Agency, An Post and the Prize Bond Company?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            WhatIsTheRelationshipBetweenTheNationalTreasuryManagementAgencyAnPostAndThePrizeBondCompany();
                            webUtil.navigateBack();
                            break;
                        case "What is the Ireland State Savings product range?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            WhatIsTheIrelandStateSavingsProductRange();
                            webUtil.navigateBack();
                            break;
                        case "Will I pay any fees, charges or commission?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            WillIPayAnyFeesChargesOrCommission();
                            webUtil.navigateBack();
                            break;
                        case "What are the rates as of 1st October 2023?":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            WhatAreTheRatesAsOf1stOctober2023();
                            webUtil.navigateBack();
                            break;

                        case "Updated General Terms and Conditions for Ireland State Savings effective from the 31st July 2024":
                            webUtil.scrollToView(SSPElement);
                            webUtil.clickLog(SSPElement, SSPTab);
                            UpdatedGeneralTermsAndConditionsForIrelandStateSavingsEffectiveFromThe31stJuly2024();
                            webUtil.navigateBack();
                            break;
                    }
                }
            }
            webUtil.clickLog(lnkHelpandSupport, "help and support link");


        } catch (Exception e) {

            Assert.fail("howtoPurchaseLinks -" + e.getMessage());
        }
    }

}

