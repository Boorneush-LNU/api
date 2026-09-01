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


public class Kentico_HelpCategoryPrizeBonds extends CategoryUtils {
    public Excelutils testdata = new Excelutils();
    String sheetName = "PrizeBonds";
//    List<TestData> ExcelRows = testdata.getTestData(config.getK13ContentExcelPath(), sheetName, TestData.class);
List<TestData> ExcelRows = testdata.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);
    public static Map<String, String> getdata = new HashMap<>();

    static Logger log = LogManager.getLogger(Kentico_HelpCategoryPrizeBonds.class);


    public Kentico_HelpCategoryPrizeBonds(WebDriver driver) {
        super(driver);


    }

    public By atag = By.xpath("//article//a");


    public void prizeBonds() {
        try {
            By prizeBonds = By.xpath("//a[@href='/help-support/help-articles/prize-bonds']");
            webUtil.scrollToView(prizeBonds);
            webUtil.clickLog(prizeBonds, "Prize Bonds ");

            webUtil.gettextlog(hdrlinkLbl, String::equals, "Prize Bonds", "Header");

            String[] PBLinks = {
                    "How do Prize Bonds work?",
                    "I have had Prize Bonds for many years, what has happened to them?",
                    "What are my Prize Bond winnings payment options?",
                    "What happens to unclaimed prizes?",
                    "How is the Prize Bond Fund calculated?",
                    "What is the Prize Bond Company?",
                    "What is a Prize Bond certificate?",
                    "What happens if I choose automatic reinvestment of my Prizes in new Prize Bonds?",
                    "How do I check my Prize Bond Numbers for Prizes?",
                    "Can I purchase Prize Bonds as a gift?",
            };
            validateSize(commonFAQLinks, PBLinks, "Prize Bonds");
            for (int b = 1; b <= PBLinks.length; b++) {
                String PBTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a"));
                By PBElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a");
                webUtil.waitFor(1000);

                if (PBTab.equals(PBLinks[b - 1])) {
                    switch (PBTab) {
                        case "How do Prize Bonds work?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            HowDoPrizeBondsWork();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How do Prize Bonds work?");
                            webUtil.navigateBack();
                            break;
                        case "I have had Prize Bonds for many years, what has happened to them?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            IHaveHadPrizeBondsForManyYearsWhatHasHappenedToThem();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles I have had Prize Bonds for many years, what has happened to them?");
                            webUtil.navigateBack();
                            break;
                        case "What are my Prize Bond winnings payment options?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            WhatAreMyPrizeBondWinningsPaymentOptions();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What are my Prize Bond winnings payment options?");
                            webUtil.navigateBack();
                            break;

                        case "What happens to unclaimed prizes?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            Whathappenstoanunclaimedprizes();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What happens to unclaimed prizes?");
                            webUtil.navigateBack();
                            break;
                        case "How is the Prize Bond Fund calculated?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            HowIsThePrizeBondFundCalculated();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How is the Prize Bond Fund calculated?");
                            webUtil.navigateBack();
                            break;
                        case "What is the Prize Bond Company?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            WhatIsThePrizeBondCompany();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What is the Prize Bond Company?");
                            webUtil.navigateBack();
                            break;
                        case "What is a Prize Bond certificate?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            WhatIsAPrizeBondCertificate();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What is a Prize Bond certificate?");
                            webUtil.navigateBack();
                            break;
                        case "What happens if I choose automatic reinvestment of my Prizes in new Prize Bonds?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            WhatHappensIfIChooseAutomaticReinvestmentOfMyPrizesInNewPrizeBonds();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What happens if I choose automatic reinvestment of my Prizes in new Prize Bonds?");
                            webUtil.navigateBack();
                            break;
                        case "Can I purchase Prize Bonds as a gift?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            CanIPurchasePrizeBondsAsAGift();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Can I purchase Prize Bonds as a gift?");
                            webUtil.navigateBack();
                            break;

                        case "How do I check my Prize Bond Numbers for Prizes?":
                            webUtil.scrollToView(PBElement);
                            webUtil.clickLog(PBElement, PBTab);
                            HowDoICheckMyPrizeBondNumbersForPrizes();
                            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How do I check my Prize Bond Numbers for Prizes?");
                            webUtil.navigateBack();
                            break;

                    }
                }

            }
            webUtil.clickLog(lnkHelpandSupport, "help and support link");

        } catch (Exception e) {
            Assert.fail("PrizeBondsLinks -" + e.getMessage());
        }
    }


    public void HowDoPrizeBondsWork()  {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in HowDoPrizeBondsWork(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "How do Prize Bonds work?", "Content");
        int index = testdata.getRowIndex("How do Prize Bonds work?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.gettextByAttribute(By.xpath("//article/p[2]/a"), String::equals, getUrl() + "/help-support/help-articles/what-are-my-prize-bond-winnings-payment-options", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a"), String::equals, getUrl() + "/help-and-support/help-articles/what-happens-if-i-choose-to-have-my-prizes-automat", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[5]/a"), String::equals, getUrl() + "/help-and-support/help-articles/what-happens-if-i-choose-automatic-reinvestment-of", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[6]/a[1]"), String::equals, getUrl() + "/getmedia/47bd2603-61ca-4e99-af5a-e13aa937c74f/Prize-Payment-Option-Form_1.pdf", "href");
//        webUtil.gettextByAttribute(By.xpath("//article/p[6]/a[2]"), String::equals, getUrl() + "/getmedia/47bd2603-61ca-4e99-af5a-e13aa937c74f/Prize-Payment-Option-Form_1.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[7]/a"), String::equals, getUrl() + "/prize-bonds/results", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[9]/a"), String::equals, getUrl() + "/prize-bonds/results", "href");
//        webUtil.gettextByAttribute(By.xpath("//article/p[12]/a"), String::equals, getUrl() + "/prize-bonds/results", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[12]/a"), String::equals, getUrl() + "/getmedia/e3044347-960a-41af-807f-230ad9d60f16/ChangeName_or_AddressForm.pdf", "href");
 //       webUtil.gettextByAttribute(By.xpath("//article/p[13]/a[1]"), String::equals, getUrl() + "/getmedia/e3044347-960a-41af-807f-230ad9d60f16/ChangeName_or_AddressForm.pdf", "href");
//        webUtil.openPDFInNewTab(By.xpath("//article/p[13]/a[1]"),"Prize-Payment-Option-Form.pdf");
   //     webUtil.gettextByAttribute(By.xpath("//article/p[13]/a[2]"), String::equals, getUrl() + "/begin-registration", "href");

    }

    public void IHaveHadPrizeBondsForManyYearsWhatHasHappenedToThem() {
        try{
            webUtil.waitForPageLoaded();
       ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in IHaveHadPrizeBondsForManyYearsWhatHasHappenedToThem(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "I have had Prize Bonds for many years, what has happened to them?", "Content");
        int index = testdata.getRowIndex("I have had Prize Bonds for many years, what has happened to them?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);

    }


    public void WhatAreMyPrizeBondWinningsPaymentOptions() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhatAreMyPrizeBondWinningsPaymentOptions(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "What are my Prize Bond winnings payment options?", "Content");
        int index = testdata.getRowIndex("What are my Prize Bond winnings payment options?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.gettextByAttribute(By.xpath("//article/p[1]/a[1]"), String::equals, getUrl() + "/help-support/help-articles/what-happens-if-i-choose-to-have-my-prizes-automat", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[1]/a[2]"), String::equals, getUrl() + "/help-support/help-articles/what-happens-if-i-choose-automatic-reinvestment-of", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[2]/a[1]"), String::equals, getUrl() + "/getmedia/964f7e69-84dc-4776-824d-b1d8e802d0f9/prize-payment-option-form.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[1]"), String::equals, getUrl() + "/begin-registration", "href");

        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[2]"), String::equals, getUrl() + "/news-comm/your-prize-bond-winnings-payment-options", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[4]/a"), String::equals, getUrl() + "/begin-registration", "href");

        webUtil.gettextByAttribute(By.xpath("//article/p[5]/a[1]"), String::equals, getUrl() + "/help-support/help-articles/how-do-i-add-my-bank-account-details-to-state-savi", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[7]/a[1]"), String::equals, getUrl() + "/getmedia/47bd2603-61ca-4e99-af5a-e13aa937c74f/Prize-Payment-Option-Form_1.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[1]"), String::equals, getUrl() + "/begin-registration", "href");

        webUtil.gettextByAttribute(By.xpath("//article/p[8]/a[1]"), String::equals, getUrl() + "/getmedia/e3044347-960a-41af-807f-230ad9d60f16/ChangeName_or_AddressForm.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[8]/a[2]"), String::equals, getUrl() + "/begin-registration", "href");

        webUtil.gettextByAttribute(By.xpath("//article/ul[2]/li[1]/a"), String::equals, getUrl() + "/", "href");
        webUtil.gettextByAttribute(By.xpath("//article/ul[2]/li[3]/a"), String::equals, getUrl() + "/begin-registration", "href");

        webUtil.gettextByAttribute(By.xpath("//article/p[9]/a[1]"), String::equals, getUrl() + "/help-support/help-articles/what-happens-if-i-choose-automatic-reinvestment-of", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[9]/a[2]"), String::equals, getUrl() + "/help-support/help-articles/how-do-i-request-a-repayment-of-my-state-savings-p", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[10]/a"), String::equals, getUrl() + "/help-support/help-articles/what-happens-if-i-choose-automatic-reinvestment-of", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[11]/a"), String::equals, getUrl() + "/help-support/help-articles/what-happens-if-i-choose-automatic-reinvestment-of", "href");

        webUtil.openPDFInNewTab(By.xpath("//article/p[7]/a[1]"), "Prize-Payment-Option-Form.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p[5]/a[2]"), "Prize-Payment-Option-Form.pdf");

    }


    public void Whathappenstoanunclaimedprizes() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in Whathappenstoanunclaimedprizes(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "What happens to unclaimed prizes?", "Content");
        int index = testdata.getRowIndex("What happens to unclaimed prizes?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.gettextByAttribute(By.xpath("//article/p/a[1]"), String::equals, getUrl() + "/getmedia/e3044347-960a-41af-807f-230ad9d60f16/changename_or_addressform.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[2]"), String::equals, getUrl() + "/prize-bonds", "href");

        webUtil.openPDFInNewTab(By.xpath("//article/p/a[1]"), "changename_or_addressform.pdf");

    }

    public void HowIsThePrizeBondFundCalculated() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in HowIsThePrizeBondFundCalculated(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "How is the Prize Bond Fund calculated?", "Content");

        int index = testdata.getRowIndex("How is the Prize Bond Fund calculated?", ExcelRows);
        String actual = webUtil.getText(By.xpath("//article"));
        String expected = ExcelRows.get(index - 1).getPara();
        webUtil.CompareString(actual, String::equals, expected);
        webUtil.gettextlog(By.xpath("//article/a"), String::equals, "here");
        webUtil.gettextByAttribute(By.xpath("//article/a"), String::equals, getUrl() + "/help-and-support/help-articles/how-do-prize-bonds-work", "href");

    }


    public void WhatIsThePrizeBondCompany() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhatIsThePrizeBondCompany(): " + e.getMessage());
        }

        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "What is the Prize Bond Company?", "Content");
        int index = testdata.getRowIndex("What is the Prize Bond Company?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextByAttribute(By.xpath("//article/p/a[3]"), String::equals, getUrl() + "/getmedia/c3404f77-571e-43c8-af44-2d9d53d856b2/Prizebonds_AnnualReport_2025.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[4]"), String::equals, getUrl() + "/getmedia/8f40085e-ba5a-466e-bbdf-cc714994bc06/Prizebonds_AnnualReport_2024.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[5]"), String::equals, getUrl() + "/getmedia/4448a0f2-44a1-4c72-a066-8decef948b3f/Prizebonds_AnnualReport_2023.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[6]"), String::equals, getUrl() + "/getmedia/e63e1f34-b218-4a4f-aaff-b66b8248fdc7/prizebonds_annualreport_2022.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[7]"), String::equals, getUrl() + "/getmedia/edfe2354-db5c-4b54-9045-9da1b416ff3d/prizebonds_annualreport_2021.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p/a[8]"), String::equals, getUrl() + "/getmedia/b0bbba0f-f230-458f-908a-8c9e6574e550/prizebonds_annualreport_2020.pdf", "href");
        webUtil.openPDFInNewTab(By.xpath("//article/p/a[3]"), "Prizebonds_AnnualReport_2025.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p/a[4]"), "Prizebonds_AnnualReport_2024.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p/a[5]"), "Prizebonds_AnnualReport_2023.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p/a[6]"), "prizebonds_annualreport_2022.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p/a[7]"), "prizebonds_annualreport_2021.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p/a[8]"), "prizebonds_annualreport_2020.pdf");

    }

    public void WhatIsAPrizeBondCertificate() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhatIsAPrizeBondCertificate(): " + e.getMessage());
        }
       
        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "What is a Prize Bond certificate?", "Content");
        int index = testdata.getRowIndex("What is a Prize Bond certificate?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
    }


    public void WhatHappensIfIChooseAutomaticReinvestmentOfMyPrizesInNewPrizeBonds() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in WhatHappensIfIChooseAutomaticReinvestmentOfMyPrizesInNewPrizeBonds(): " + e.getMessage());
        }
        
        log.info("Validating:" + webUtil.getText(articleHdr));
        int index = testdata.getRowIndex("What happens if I choose automatic reinvestment of my Prizes in new Prize Bonds?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextByAttribute(By.xpath("//article/p[1]/a"), String::equals, getUrl() + "/help-support/help-articles/what-are-my-prize-bond-winnings-payment-options", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[2]/a[1]"), String::equals, getUrl() + "/getmedia/47bd2603-61ca-4e99-af5a-e13aa937c74f/Prize-Payment-Option-Form_1.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[2]/a[2]"), String::equals, getUrl() + "/your-savings/login", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a"), String::equals, getUrl() + "/help-support/help-articles/manage-my-details/how-do-i-change-my-name-and-or-address-on-my-state", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[6]/a"), String::equals, getUrl() + "/help-support/help-articles/how-do-i-request-a-repayment-of-my-state-savings-p", "href");

    }

    public void CanIPurchasePrizeBondsAsAGift() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in CanIPurchasePrizeBondsAsAGift(): " + e.getMessage());
        }
        
        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "Can I purchase Prize Bonds as a gift?", "Content");
        int index = testdata.getRowIndex("Can I purchase Prize Bonds as a gift?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        comparesubLinks(ExcelRows, index);
        webUtil.gettextByAttribute(atag, String::equals, getUrl() + "/getmedia/a04ad920-4f0c-4ea1-b240-291070db4e06/prizebonds_gift_application_form.pdf", "href");
        webUtil.openPDFInNewTab(atag, "prizebonds_gift_application_form.pdf");
    }

    public void HowDoICheckMyPrizeBondNumbersForPrizes()  {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        }
        catch(Exception e){
            log.error("Error in HowDoICheckMyPrizeBondNumbersForPrizes(): " + e.getMessage());
        }
        
        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "How do I check my Prize Bond Numbers for Prizes?", "Content");
        int index = testdata.getRowIndex("How do I check my Prize Bond Numbers for Prizes?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.openPDFInNewTab(By.xpath("//article/p[6]/a"), "SS_StatementRequestForm.pdf");
        webUtil.gettextByAttribute(By.xpath("//article//li/a"), String::equals, getUrl() + "/prize-bonds", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[2]/a"), String::equals, getUrl() + "/help-and-support/help-articles/how-do-i-register-for-state-savings-online", "href");
        webUtil.gettextByAttribute(By.xpath("//article/p[6]/a"), String::equals, getUrl() + "/getmedia/daccea0b-3e4e-4224-9146-a76afa87da01/SS_StatementRequestForm_1.pdf", "href");

    }
}