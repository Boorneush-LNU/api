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
public class Kentico_HelpCategory_About extends CategoryUtils{
    WebDriver driver;

   HelpAndSupportPage helpandSupportPage;
    Kentico_HelpCategory_IrelandStateSavingOnline kenticoHelpCategoryIrelandStateSavingOnline;

    String sheetName = "About";
    Excelutils data = new Excelutils();
//    List<TestData> rows = data.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class)
    List<TestData> rows = data.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName,TestData.class);

    public Kentico_HelpCategory_About(WebDriver driver) {
        super(driver);
        this.driver=driver;

        kenticoHelpCategoryIrelandStateSavingOnline = new Kentico_HelpCategory_IrelandStateSavingOnline(driver);
        helpandSupportPage=new HelpAndSupportPage(driver);

    }

    public void aboutLinks() {

        Map<String, Consumer<Kentico_HelpCategory_About>> aboutMap = new LinkedHashMap<>();
        aboutMap.put("What is Ireland State Savings Online?", Kentico_HelpCategory_About::WhatisIrelandStateSavingsOnline);
        aboutMap.put("Why register for Ireland State Savings Online?", Kentico_HelpCategory_About::WhyregisterforStateSavingsOnline);
        aboutMap.put("Who can register for Ireland State Savings Online?", Kentico_HelpCategory_About::WhocanregisterforStateSavingsOnline);


        webUtil.scrollToView(helpandSupportPage.hdrlblHelp);
        helpandSupportPage.clickCategory("Ireland State Savings Online");
        kenticoHelpCategoryIrelandStateSavingOnline.clickSubCategory("About");

        webUtil.gettextlog(helpandSupportPage.hdrlinkLbl, String::equals, "About", "Header");
        validateSize(helpandSupportPage.commonFAQLinks, aboutMap.keySet().toArray(new String[0]), "About");
        int size = aboutMap.keySet().toArray(new String[0]).length;
        List<WebElement> webElements = webUtil.getElements(helpandSupportPage.commonFAQLinks);
        IntStream.range(0, size).forEach(j -> {
            WebElement element = webElements.get(j);
            String text = element.getText();

            if (aboutMap.containsKey(text)) {
                webUtil.clickLog(element, text);
                aboutMap.get(text).accept(this);
                webUtil.navigateBack();
                webElements.clear();
                webElements.addAll(webUtil.getElements(helpandSupportPage.commonFAQLinks));
            }

        });

        webUtil.navigateBack();
    }

    private void WhatisIrelandStateSavingsOnline() {
        try {
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }catch(Exception e){
            log.error("Error in WhatisIrelandStateSavingsOnline() " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles What is Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" +webUtil.getText(helpandSupportPage.pageHdr));
        int index=data.getRowIndex("What is Ireland State Savings Online?", rows) - 1;
        compareParagrph(rows,index);
        webUtil.gettextlog(helpandSupportPage.subLink, String::equals, "Who can register for Ireland State Savings Online’", "Link Text");
        webUtil.gettextByAttribute(helpandSupportPage.subLink, String::equals, getUrl() + "/help-and-support/help-articles/who-can-register-for-state-savings-online", "href");
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }

    private void WhyregisterforStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");

        }
        catch(Exception e){
            log.error("Error in WhyregisterforStateSavingsOnline() " + e.getMessage());
        }
        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Why register for Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        int index = data.getRowIndex("Why register for Ireland State Savings Online?", rows) - 1;
       compareParagrph(rows,index);
       compareBullets(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }

    private void WhocanregisterforStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch(Exception e){
            log.error("Error in WhocanregisterforStateSavingsOnline() " + e.getMessage());
        }


        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles Who can register for Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        int index = data.getRowIndex("Who can register for Ireland State Savings Online?", rows) - 1;
       compareParagrph(rows,index);
        compareHeader(rows,index);
        webUtil.gettextlog(helpandSupportPage.subLink, String::equals, "'How do I register for Ireland State Savings Online?'", "Link Text");
        webUtil.gettextByAttribute(helpandSupportPage.subLink, String::equals, getUrl() + "/help-and-support/help-articles/how-do-i-register-for-state-savings-online", "href");
        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }


}
