package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Log4j2
public class Kentico_HelpCategoryBereavement extends CategoryUtils {
    public Excelutils testdata = new Excelutils();
    String sheetName = "Bereavement";
//    List<TestData> ExcelRows = testdata.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class);
List<TestData> ExcelRows = testdata.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);

    public Kentico_HelpCategoryBereavement(WebDriver driver) {
        super(driver);
       

    }



    public void bereavementGuideSupport(){
        By bereavement = By.xpath("//a[@title='Bereavement Guide and Support']");
        webUtil.scrollToView(bereavement);
        webUtil.clickLog(bereavement, "Bereavement Guide Support");
        webUtil.gettextlog(hdrlinkLbl, String::equals, "Bereavement Guide and Support", "Header");
        String BGASLinks[] = {"How do I initiate a Deceased Case for an Ireland State Savings customer?",
                "How long does it take to complete a Deceased Case?",
                "If I inherit an investment, what options are available to me?",
                "When does Ireland State Savings require a Grant of Probate / Letter of Administration?"};
        validateSize(commonFAQLinks, BGASLinks, "Bereavement Guide and Support");
        for (int b = 1; b <= BGASLinks.length; b++) {
            String BGASTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a"));
            By BGASElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li[" + b + "]/a");
            if (BGASTab.equals(BGASLinks[b - 1])) {
                switch (BGASTab) {
                    case "How do I initiate a Deceased Case for a Ireland State Savings customer?":
                        webUtil.scrollToView(BGASElement);
                        webUtil.clickLog(BGASElement, BGASTab);
                        HowdoIinitiateaDeceasedCaseforaStateSavingscustomer();
                        webUtil.navigateBack();
                        break;
                    case "How long does it take to complete a Deceased Case?":
                        webUtil.scrollToView(BGASElement);
                        webUtil.clickLog(BGASElement, BGASTab);
                        HowlongdoesittaketocompleteaDeceasedCase();
                        webUtil.navigateBack();
                        break;
                    case "If I inherit an investment, what options are available to me?":
                        webUtil.scrollToView(BGASElement);
                        webUtil.clickLog(BGASElement, BGASTab);
                        IfIinheritaninvestmentwhatoptionsareavailabletome();
                        webUtil.navigateBack();
                        break;
                    case "When does Ireland State Savings require a Grant of Probate / Letter of Administration?":
                        webUtil.scrollToView(BGASElement);
                        webUtil.clickLog(BGASElement, BGASTab);
                        WhendoesStateSavingsrequireaGrantofProbateLetterofAdministration();
                        webUtil.navigateBack();
                        break;
                }
            }
        }
        webUtil.clickLog(lnkHelpandSupport, "help and support link");
    }

    public void HowdoIinitiateaDeceasedCaseforaStateSavingscustomer()  {
        webUtil.scrollToView(articleHdr);
        ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "How do I initiate a Deceased Case for a Ireland State Savings customer?", "Content");
        int index = testdata.getRowIndex("How do I initiate a Deceased Case for a Ireland State Savings customer?",ExcelRows ) - 1;
        compareParagrph(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.gettextByAttribute(By.xpath("//article/ol//a"), String::equals, getUrl() + "/getmedia/e58170f7-8244-44c5-8948-2cc496c1be82/notificationofdeath.pdf", "href");
        webUtil.gettextByAttribute(By.xpath("//article/ul//a"), String::equals, "mailto:Service@StateSavings.ie", "href");
        webUtil.openPDFInNewTab(By.xpath("//article/ol//a"), "notificationofdeath.pdf");

    }

    public void HowlongdoesittaketocompleteaDeceasedCase()  {
        webUtil.scrollToView(articleHdr);
        ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "How long does it take to complete a Deceased Case?", "Content");
        int index = testdata.getRowIndex("How long does it take to complete a Deceased Case?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);


    }

    public void IfIinheritaninvestmentwhatoptionsareavailabletome()  {
        webUtil.scrollToView(articleHdr);
        ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "If I inherit an investment, what options are available to me?", "Content");
        int index = testdata.getRowIndex("If I inherit an investment, what options are available to me?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
//        webUtil.gettextByAttribute(By.xpath("//article/p[2]/a[1]"), String::equals, getUrl() + "/help-support/help-articles/is-there-any-limit-on-my-overall-holding-of-state", "href");
//        webUtil.gettextByAttribute(By.xpath("//article/p[2]/a[2]"), String::equals, getUrl() + "/help-support/contact-us", "href");
    }

    public void WhendoesStateSavingsrequireaGrantofProbateLetterofAdministration()  {
        webUtil.scrollToView(articleHdr);
        ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
        log.info("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr, String::equals, "When does Ireland State Savings require a Grant of Probate / Letter of Administration?", "Content");
        int index = testdata.getRowIndex("When does Ireland State Savings require a Grant of Probate / Letter of Administration?", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);


    }
}
