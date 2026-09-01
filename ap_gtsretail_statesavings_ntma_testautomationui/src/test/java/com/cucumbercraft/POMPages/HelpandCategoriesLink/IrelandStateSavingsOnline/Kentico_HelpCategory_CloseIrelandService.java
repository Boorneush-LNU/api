package com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpAndSupportPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Log4j2
public class Kentico_HelpCategory_CloseIrelandService extends CategoryUtils{

   
    HelpAndSupportPage helpandSupportPage;
    Kentico_HelpCategory_IrelandStateSavingOnline kenticoHelpCategoryIrelandStateSavingOnline;
    String sheetName = "Close Ireland State Savings";
    Excelutils data = new Excelutils();
//    List<TestData> rows = data.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class);
List<TestData> rows = data.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);
    int index;
    public Kentico_HelpCategory_CloseIrelandService(WebDriver driver) {
        super(driver);
        kenticoHelpCategoryIrelandStateSavingOnline = new Kentico_HelpCategory_IrelandStateSavingOnline(driver);
        helpandSupportPage = new HelpAndSupportPage(driver);
    }

    public void closeServiceLinks()
    {
        Map<String, Consumer<Kentico_HelpCategory_CloseIrelandService>> aboutMap=Map.of(
                "How do I close my Ireland State Savings Online service?", Kentico_HelpCategory_CloseIrelandService::HowdoIclosemyIrelandStateSavingsOnlineservice);



        webUtil.scrollToView(helpandSupportPage.hdrlblHelp);
        helpandSupportPage.clickCategory("Ireland State Savings Online");
        kenticoHelpCategoryIrelandStateSavingOnline.clickSubCategory("Closing your Ireland State Savings Online service");

        webUtil.gettextlog(helpandSupportPage.hdrlinkLbl, String::equals, "Closing your Ireland State Savings Online service", "Header");
        validateSize(helpandSupportPage.commonFAQLinks,aboutMap.keySet().toArray(new String[0]), "Closing your Ireland State Savings Online service");
        int size=aboutMap.keySet().toArray(new String[0]).length;
        List<WebElement> webElements=webUtil.getElements(helpandSupportPage.commonFAQLinks);
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



    }

    public void HowdoIclosemyIrelandStateSavingsOnlineservice() {
        try{
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Validating: <b>" +webUtil.getText(helpandSupportPage.pageHdr)+" </b>");
        }
        catch (Exception e){
            log.error("Error in HowdoIclosemyIrelandStateSavingsOnlineservice(): " + e.getMessage());
        }

        webUtil.gettextlog(helpandSupportPage.breadCrumb,String::equals,"Home Help and Support Help Articles How do I close my Ireland State Savings Online service?","BreadCrumb");
        log.info("Validating:" + webUtil.getText(helpandSupportPage.pageHdr));
        index=data.getRowIndex("How do I close my Ireland State Savings Online service?",rows)-1;
        compareParagrph(rows,index);
        ExtentCucumberAdapter.addTestStepLog("************** Validated : "+webUtil.getText(helpandSupportPage.pageHdr)+" **************");
        log.info(("************** Validated : " + webUtil.getText(helpandSupportPage.pageHdr) + " **************"));


    }
}
