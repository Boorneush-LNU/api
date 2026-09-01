package com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpAndSupportPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Log4j2
public class Kentico_HelpCategory_YourSavings extends CategoryUtils{
   
    Kentico_HelpCategory_IrelandStateSavingOnline kenticoHelpCategoryIrelandStateSavingOnline;
    HelpAndSupportPage helpandSupportPage;
    String sheetName = "Your Savings";
    Excelutils data = new Excelutils();
//    List<TestData> rows = data.getTestData(config.getK13ContentExcelPath(),sheetName,TestData.class);
List<TestData> rows = data.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(),sheetName,TestData.class);
    int index;

    public Kentico_HelpCategory_YourSavings(WebDriver driver) {
        super(driver);
        kenticoHelpCategoryIrelandStateSavingOnline = new Kentico_HelpCategory_IrelandStateSavingOnline(driver);
        helpandSupportPage = new HelpAndSupportPage(driver);
    }

    public void yourSavingsLinks() {
        Map<String, Consumer<Kentico_HelpCategory_YourSavings>> consumerMap = new LinkedHashMap<>();
        consumerMap.put("How do I purchase a product using Ireland State Savings Online service?", Kentico_HelpCategory_YourSavings::HowDoIPurchaseAProductUsingStateSavingsOnlineService);
        consumerMap.put("Are all of your holdings appearing in your Ireland State Savings Online?", Kentico_HelpCategory_YourSavings::AreAllOfYourHoldingsAppearingInYourStateSavingsOnline);
        consumerMap.put("How do I view my transactions and holdings online?", Kentico_HelpCategory_YourSavings::howdoIviewmytransactionsandholdingsonline);
        consumerMap.put("What is the State Savings Account (SSA)?", Kentico_HelpCategory_YourSavings::WhatIsTheStateSavingsAccount);
        consumerMap.put("Why is my holding temporarily unavailable for transactions online?", Kentico_HelpCategory_YourSavings::WhyIsMyHoldingTemporarilyUnavailableForTransactionsOnline);
        consumerMap.put("Why are all my Prize Bond ranges not available for repayment/reinvestment in Ireland State Savings Online?", Kentico_HelpCategory_YourSavings::WhyAreAllMyPrizeBondRangesNotAvailableForRepaymentReinvestmentInStateSavingsOnline);
        consumerMap.put("Why is the full value of my Prize Bond holding not available for repayment in Ireland State Savings Online?", Kentico_HelpCategory_YourSavings::WhyIsTheFullValueOfMyPrizeBondsHoldingNotAvailableForRepaymentInStateSavingsOnline);
        consumerMap.put("Which Prize Bond Numbers will be used for repayment?", Kentico_HelpCategory_YourSavings::WhichPrizeBondNumbersWillBeUsedForRepayment);

        webUtil.scrollToView(helpandSupportPage.hdrlblHelp);
        helpandSupportPage.clickCategory("Ireland State Savings Online");
        kenticoHelpCategoryIrelandStateSavingOnline.clickSubCategory("Your Savings");
        webUtil.gettextlog(helpandSupportPage.hdrlinkLbl, String::equals, "Your Savings", "Header");
        validateSize(helpandSupportPage.commonFAQLinks, consumerMap.keySet().toArray(new String[0]), "Your Savings");
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

    public void HowDoIPurchaseAProductUsingStateSavingsOnlineService() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in HowDoIPurchaseAProductUsingStateSavingsOnlineService(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles How do I purchase a product using Ireland State Savings Online service?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("How do I purchase a product using Ireland State Savings Online service?",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void AreAllOfYourHoldingsAppearingInYourStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in AreAllOfYourHoldingsAppearingInYourStateSavingsOnline(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Are all of your holdings appearing in your Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Are all of your holdings appearing in your Ireland State Savings Online?",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void howdoIviewmytransactionsandholdingsonline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in howdoIviewmytransactionsandholdingsonline(): " + e.getMessage());
        }


        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles How do I view my transactions and holdings online?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("How do I view my transactions and holdings online?",rows)-1;
        compareHeader(rows,index);
        compareParagrph(rows,index);
        compareBullets(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void WhatIsTheStateSavingsAccount() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhatIsTheStateSavingsAccount(): " + e.getMessage());
        }


        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles What is the State Savings Account (SSA)?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("What is the State Savings Account (SSA)?",rows)-1;
        compareParagrph(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void WhyIsMyHoldingTemporarilyUnavailableForTransactionsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhyIsMyHoldingTemporarilyUnavailableForTransactionsOnline(): " + e.getMessage());
        }


        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Why is my holding temporarily unavailable for transactions online?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Why is my holding temporarily unavailable for transactions online?",rows)-1;
        compareHeader(rows,index);
        compareParagrph(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }

    public void WhyAreAllMyPrizeBondRangesNotAvailableForRepaymentReinvestmentInStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhyAreAllMyPrizeBondRangesNotAvailableForRepaymentReinvestmentInStateSavingsOnline(): " + e.getMessage());
        }


        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Why are all my Prize Bond ranges not available for repayment/reinvestment in Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Why are all my Prize Bond ranges not available for repayment/reinvestment in Ireland State Savings Online?",rows)-1;
        compareParagrph(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }

    public void WhyIsTheFullValueOfMyPrizeBondsHoldingNotAvailableForRepaymentInStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhyIsTheFullValueOfMyPrizeBondsHoldingNotAvailableForRepaymentInStateSavingsOnline(): " + e.getMessage());
        }


        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Why is the full value of my Prize Bond holding not available for repayment in Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Why is the full value of my Prize Bond holding not available for repayment in Ireland State Savings Online?",rows)-1;
        compareParagrph(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }

    public void WhichPrizeBondNumbersWillBeUsedForRepayment() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhichPrizeBondNumbersWillBeUsedForRepayment(): " + e.getMessage());
        }


        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Which Prize Bond Numbers will be used for repayment?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Which Prize Bond Numbers will be used for repayment?",rows)-1;
        compareParagrph(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }
}
