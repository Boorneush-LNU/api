package com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpAndSupportPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Log4j2
public class Kentico_HelpCategory_PersonalInformation extends CategoryUtils{


    int index;
    Kentico_HelpCategory_IrelandStateSavingOnline kenticoHelpCategoryIrelandStateSavingOnline;
    HelpAndSupportPage helpandSupportPage;
    Kentico_HelpCategory_About kenticoHelpCategoryAbout;
    String sheetName = "Personal Information";
    Excelutils data = new Excelutils();
//    List<TestData> rows = data.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class);
    List<TestData> rows = data.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName,TestData.class);

    public Kentico_HelpCategory_PersonalInformation(WebDriver driver) {
       super(driver);
        kenticoHelpCategoryIrelandStateSavingOnline = new Kentico_HelpCategory_IrelandStateSavingOnline(driver);
        helpandSupportPage = new HelpAndSupportPage(driver);
        kenticoHelpCategoryAbout = new Kentico_HelpCategory_About(driver);
    }

    public void personlaInformation() {
        Map<String, Consumer<Kentico_HelpCategory_PersonalInformation>> consumerMap = new LinkedHashMap<>();
        consumerMap.put("How do I manage my Bank Account details?", Kentico_HelpCategory_PersonalInformation::HowdoImanagemyBankAccountdetails);
        consumerMap.put("Why do I have to provide my mobile phone number?", Kentico_HelpCategory_PersonalInformation::WhyDoIHaveToProvideMyMobilePhoneNumber);
        consumerMap.put("How do I manage my Ireland State Savings Online password?", Kentico_HelpCategory_PersonalInformation::HowDoIChangeMyIrelandStateSavingsOnlinePassword);
        consumerMap.put("How do I update my personal details for Ireland State Savings Online?", Kentico_HelpCategory_PersonalInformation::HowdoIupdatemypersonaldetailsforIrelandStateSavingsOnline);
        consumerMap.put("Where can I check my SSCN in Ireland State Savings Online?", Kentico_HelpCategory_PersonalInformation::WhereCanICheckMySSCNInStateSavingsOnline);
        consumerMap.put("Having trouble signing in to Ireland State Savings Online?", Kentico_HelpCategory_PersonalInformation::HavingTroubleSigningInToStateSavingsOnline);
        consumerMap.put("How do I change my Prize Bond winnings payment option in Ireland State Savings Online?", Kentico_HelpCategory_PersonalInformation::HowDoIChangeMyPrizeBondWinningsPaymentOptionInStateSavingsOnline);


        webUtil.scrollToView(helpandSupportPage.hdrlblHelp);
        helpandSupportPage.clickCategory("Ireland State Savings Online");
        kenticoHelpCategoryIrelandStateSavingOnline.clickSubCategory("Personal Information");
        webUtil.gettextlog(helpandSupportPage.hdrlinkLbl, String::equals, "Personal Information", "Header");
        validateSize(helpandSupportPage.commonFAQLinks, consumerMap.keySet().toArray(new String[0]), "Personal Information");
        int size = consumerMap.keySet().toArray(new String[0]).length;
        List<WebElement> webElements = webUtil.getElements(helpandSupportPage.commonFAQLinks);
        IntStream.range(0, size).forEach(j -> {
            WebElement element = webElements.get(j);
            String text = element.getText();
            
            if (consumerMap.containsKey(text)) {
                webUtil.clickLog(element, text);
                consumerMap.get(text).accept(this);
               webUtil.navigateBack();
                webElements.clear();
                webElements.addAll(webUtil.getElements(helpandSupportPage.commonFAQLinks));
            }
           
        });


        webUtil.navigateBack();
    }


    public void HowdoImanagemyBankAccountdetails() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in HowdoImanagemyBankAccountdetails(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles How do I manage my Bank Account details?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("How do I manage my Bank Account details?",rows)-1;
        compareHeader(rows,index);
        compareParagrph(rows,index);
        compareBullets(rows,index);
//        webUtil.openLinkInNewTab(helpandSupportPage.subLink, "IBAN Generator");
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void WhyDoIHaveToProvideMyMobilePhoneNumber() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhyDoIHaveToProvideMyMobilePhoneNumber(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Why do I have to provide my mobile phone number?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Why do I have to provide my mobile phone number?",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);
        webUtil.gettextlog(By.linkText("What is a Verification Code?"), String::equals, "What is a Verification Code?", "Link text");
        webUtil.gettextByAttribute(By.linkText("What is a Verification Code?"), String::equals, getUrl() + "/help-support/help-articles/what-is-a-verification-code", "href");
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void HowDoIChangeMyIrelandStateSavingsOnlinePassword() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in HowDoIChangeMyIrelandStateSavingsOnlinePassword(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles How do I manage my Ireland State Savings Online password?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("How do I manage my Ireland State Savings Online password?",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }

    public void HowdoIupdatemypersonaldetailsforIrelandStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in HowdoIupdatemypersonaldetailsforIrelandStateSavingsOnline(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles How do I update my personal details for Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("How do I update my personal details for Ireland State Savings Online?",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);
        compareHeader(rows,index);
        webUtil.gettextlog(By.linkText("contact Ireland State Savings."), String::equals, "contact Ireland State Savings.");
        webUtil.gettextByAttribute(By.linkText("contact Ireland State Savings."), String::equals, getUrl() + "/help-support/contact-us", "href");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[2]"), "change-of-mobile-number-form.pdf");
        webUtil.openPDFInNewTab(By.xpath("(//article//a)[3]"), "change-of-mobile-number-form.pdf");
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void WhereCanICheckMySSCNInStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhereCanICheckMySSCNInStateSavingsOnline(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Where can I check my SSCN in Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Where can I check my SSCN in Ireland State Savings Online?",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);
        webUtil.gettextlog(webUtil.getXPathWithIndex.apply(helpandSupportPage.paraString, 1), String::equals, "To access your SSCN and SSCN QR code:", "Content");
        webUtil.gettextlog(helpandSupportPage.bulletPoint1, String::equals, "Sign in to Ireland State Savings Online using your email address\n" +
                "Go to ‘Profile and Settings’ and select ‘Download SSCN Code’ beside ‘State Savings Customer Number’.\n" +
                "You will see your SSCN and SSCN QR code\n" +
                "Select Download or, if you are using a smartphone ‘Save image’.", "Content");
        webUtil.gettextlog(webUtil.getXPathWithIndex.apply(helpandSupportPage.paraString, 2), String::equals, "For more information about the SSCN and how you can use it, see:\n" +
                "‘What is the State Savings Customer Number (SSCN)?’", "Content");
        webUtil.gettextByAttribute(By.partialLinkText("State Savings Customer Number (SSCN)"), String::equals, getUrl() + "/help-support/help-articles/what-is-the-state-savings-customer-number-(sscn)", "href");
        webUtil.gettextlog(By.partialLinkText("State Savings Customer Number (SSCN)"), String::equals, "‘What is the State Savings Customer Number (SSCN)?’", "Link Text");
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }

    public void HavingTroubleSigningInToStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in HavingTroubleSigningInToStateSavingsOnline(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Having trouble signing in to Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index=data.getRowIndex("Having trouble signing in to Ireland State Savings Online?",rows)-1;
        compareHeader(rows,index);
        compareParagrph(rows,index);
        compareBullets(rows,index);
        webUtil.gettextByAttribute(webUtil.getXPathWithIndex.apply("(//article//a)[%d]", 1), String::equals, getUrl() + "/help-support/help-articles/how-do-i-change-my-state-savings-online-password", "href");
        webUtil.gettextByAttribute(webUtil.getXPathWithIndex.apply("(//article//a)[%d]", 2), String::equals, getUrl() + "/help-support/help-articles/how-do-i-change-the-email-address-for-my-state-sav#mobile", "href");
        webUtil.gettextlog(webUtil.getXPathWithIndex.apply("(//article//a)[%d]", 1), String::equals, "How do I manage my Ireland State Savings Online password?");
        webUtil.gettextlog(webUtil.getXPathWithIndex.apply("(//article//a)[%d]", 2), String::equals, "I changed my mobile phone number. How do I update the mobile number for Ireland State Savings Online?");
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }

    public void HowDoIChangeMyPrizeBondWinningsPaymentOptionInStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in HowdoIregisterforStateSavingsOnline(): " + e.getMessage());
        }
        ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles How do I change my Prize Bond winnings payment option in Ireland State Savings Online?", "BreadCrumb");

        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index=data.getRowIndex("How do I change my Prize Bond winnings payment option in Ireland State Savings Online?",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);

        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }
}