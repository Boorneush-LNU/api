package com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpAndSupportPage;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Log4j2
public class Kentico_HelpCategory_Security extends CategoryUtils{

    HelpAndSupportPage helpandSupportPage;
    Kentico_HelpCategory_IrelandStateSavingOnline kenticoHelpCategoryIrelandStateSavingOnline;
    String sheetName = "Security";
    Excelutils data = new Excelutils();
//    List<TestData> rows = data.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class);
    List<TestData> rows = data.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName,TestData.class);
    int index;
    private By contentLocator;

    public Kentico_HelpCategory_Security(WebDriver driver) {
       super(driver);
        kenticoHelpCategoryIrelandStateSavingOnline = new Kentico_HelpCategory_IrelandStateSavingOnline(driver);
        helpandSupportPage = new HelpAndSupportPage(driver);
    }

    public void securityLinks()
    {
        Map<String, Consumer<Kentico_HelpCategory_Security>> consumerMap=new LinkedHashMap<>();
        consumerMap.put("Security of Ireland State Savings Online", Kentico_HelpCategory_Security::SecurityOfStateSavingsOnline);
        consumerMap.put("What is a Verification Code?", Kentico_HelpCategory_Security::WhatIsAVerificationCode);
        consumerMap.put("What do I do if my Ireland State Savings Online has been locked?", Kentico_HelpCategory_Security::WhatDoIDoIfMyStateSavingsOnlineHasBeenLocked);
        webUtil.scrollToView(helpandSupportPage.hdrlblHelp);
        helpandSupportPage.clickCategory("Ireland State Savings Online");
        kenticoHelpCategoryIrelandStateSavingOnline.clickSubCategory("Security");
        webUtil.gettextlog(helpandSupportPage.hdrlinkLbl, String::equals, "Security", "Header");
        validateSize(helpandSupportPage.commonFAQLinks, consumerMap.keySet().toArray(new String[0]), "Security");
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

    public void SecurityOfStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in SecurityOfStateSavingsOnline(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb,String::equals,"Home Help and Support Help Articles Security of Ireland State Savings Online","BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("Security of Ireland State Savings Online",rows)-1;
        compareParagrph(rows,index);
        compareBullets(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : "+webUtil.getText(helpandSupportPage.pageHdr)+" **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));
    }

    public void WhatDoIDoIfMyStateSavingsOnlineHasBeenLocked() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhatDoIDoIfMyStateSavingsOnlineHasBeenLocked(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb,String::equals,"Home Help and Support Help Articles What do I do if my Ireland State Savings Online has been locked?","BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("What do I do if my Ireland State Savings Online has been locked?",rows)-1;
        compareParagrph(rows,index);

        ExtentCucumberAdapter.addTestStepLog("************** Validated : "+webUtil.getText(helpandSupportPage.pageHdr)+" **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));

    }
    public void WhatIsAVerificationCode() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" + webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in WhatIsAVerificationCode(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb,String::equals,"Home Help and Support Help Articles What is a Verification Code?","BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index= data.getRowIndex("What is a Verification Code?",rows)-1;
        contentLocator = By.xpath("//article");
        webUtil.gettextlog(webUtil.getXPathWithIndex.apply("//article/h4[%d]",1), String::equals,"What will the Verification Code text message say?","Sub Header");
        webUtil.gettextlog(webUtil.getXPathWithIndex.apply("//article/h4[%d]",2),String::equals, "How long is the Verification Code I receive by text message valid for?","Sub Header");
        webUtil.gettextlog(webUtil.getXPathWithIndex.apply("//article/h4[%d]",3),String::equals,"What if I did not receive the Verification Code or the Code expires?","Sub Header");
        webUtil.gettextlog(contentLocator, String::equals, rows.get(index).getPara(), "Content");
        log.info("Difference: "+StringUtils.difference(webUtil.getText(contentLocator), rows.get(index).getPara()));
        ExtentCucumberAdapter.addTestStepLog("************** Validated : "+webUtil.getText(helpandSupportPage.pageHdr)+" **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }
}
