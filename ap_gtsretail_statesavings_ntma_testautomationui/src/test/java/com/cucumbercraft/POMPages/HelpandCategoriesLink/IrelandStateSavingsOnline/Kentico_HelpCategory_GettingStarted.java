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
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Log4j2
public class Kentico_HelpCategory_GettingStarted extends CategoryUtils {

    protected static Properties properties;
    HelpAndSupportPage helpandSupportPage;
    Kentico_HelpCategory_IrelandStateSavingOnline kenticoHelpCategoryIrelandStateSavingOnline;
    String sheetName = "GettingStarted";
    Excelutils data = new Excelutils();

    List<TestData> rows = data.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName,TestData.class);
    private By registrationFormLink;
    private By evidenceOfIdentityDocumentationLink;
    private By statesavingsLink;

    public Kentico_HelpCategory_GettingStarted(WebDriver driver) {
        super(driver);
        helpandSupportPage = new HelpAndSupportPage(driver);
        kenticoHelpCategoryIrelandStateSavingOnline = new Kentico_HelpCategory_IrelandStateSavingOnline(driver);
    }
   // If you want to directly/Change the Sheetname means use this. If you want to get the sheetname as per the env means use the below methods.


    public void gettingStarted() {
        Map<String, Consumer<Kentico_HelpCategory_GettingStarted>> gettingStartedMap = new LinkedHashMap<>();
        gettingStartedMap.put("How do I register for Ireland State Savings Online?", Kentico_HelpCategory_GettingStarted::HowdoIregisterforStateSavingsOnline);
        gettingStartedMap.put("When can I start using Ireland State Savings Online?", Kentico_HelpCategory_GettingStarted::WhencanIstartusingStateSavingsOnline);
        webUtil.scrollToView(helpandSupportPage.hdrlblHelp);
        helpandSupportPage.clickCategory("Ireland State Savings Online");
        kenticoHelpCategoryIrelandStateSavingOnline.clickSubCategory("Getting Started");
        webUtil.gettextlog(helpandSupportPage.hdrlinkLbl, String::equals, "Getting Started", "Header");
        validateSize(helpandSupportPage.commonFAQLinks, gettingStartedMap.keySet().toArray(new String[0]), "Getting Started");
        int size = gettingStartedMap.keySet().toArray(new String[0]).length;
        List<WebElement> webElements =webUtil.getElements(helpandSupportPage.commonFAQLinks);
        IntStream.range(0, size).forEach(j -> {
            WebElement element = webElements.get(j);
            String text = element.getText();

            if (gettingStartedMap.containsKey(text) ) {
                webUtil.clickLog(element, text);
                gettingStartedMap.get(text).accept(this);
               webUtil.navigateBack();
                webElements.clear();
                webElements.addAll(webUtil.getElements(helpandSupportPage.commonFAQLinks));
            }

        });


        webUtil.navigateBack();
    }



    public void HowdoIregisterforStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr) + " </b>");
        }
        catch (Exception e){
            log.error("Error in HowdoIregisterforStateSavingsOnline(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles How do I register for Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        int index = data.getRowIndex("How do I register for Ireland State Savings Online?", rows) - 1;
        compareHeader(rows,index);
        compareBullets(rows,index);
        compareParagrph(rows,index);
        Map<String, String> linkMappings = Map.of(
                "//article/p[7]/a", getUrl() + "/help-support/forms-downloads/application-forms"

        );

        linkMappings.forEach((xpath, url) -> {
            webUtil.gettextByAttribute(By.xpath(xpath), String::equals, url, "href");
        });

        ExtentCucumberAdapter.addTestStepLog("************** Validated : " +webUtil.getText(helpandSupportPage.pageHdr)+ " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));
    }

    public void WhencanIstartusingStateSavingsOnline() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr)+ " </b>");
        }
        catch (Exception e){
            log.error("Error in WhencanIstartusingStateSavingsOnline(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb, String::equals, "Home Help and Support Help Articles When can I start using Ireland State Savings Online?", "BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
       int index=data.getRowIndex("When can I start using Ireland State Savings Online?", rows) - 1;
//        System.out.println("Diff: "+ StringUtils.difference(webUtil.getText(By.xpath("//article/descendant::li[2]")), "A Welcome letter to your registered address containing a Personal Identification Number (PIN) that you will use to sign in to the Ireland State Savings Online service for the first time."));
//        compareBullets(rows,index);
       compareParagrph(rows,index);

        ExtentCucumberAdapter.addTestStepLog("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));
    }

}
