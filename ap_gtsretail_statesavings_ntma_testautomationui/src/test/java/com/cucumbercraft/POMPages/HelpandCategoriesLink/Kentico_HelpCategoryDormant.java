package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;


public class Kentico_HelpCategoryDormant extends CategoryUtils {
    
    public Excelutils testdata = new Excelutils();
    String sheetName = "Dormant";
//    List<TestData> ExcelRows = testdata.getTestData(config.getK13ContentExcelPath(),sheetName, TestData.class);
    List<TestData> ExcelRows = testdata.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);

    


    public Kentico_HelpCategoryDormant(WebDriver driver) {
        super(driver);
      


    }



    public void dormantAccount() {
        Map<String, Consumer<Kentico_HelpCategoryDormant>> dormatMap = new HashMap<>();
        dormatMap.put("How do I prevent my account or investment product from becoming dormant?", Kentico_HelpCategoryDormant::dormatFirst);
        dormatMap.put("How long will it take for my Dormant Account to be reactivated?", Kentico_HelpCategoryDormant::dormatSecond);
        dormatMap.put("Ireland State Savings Dormant Account Customer Notice", Kentico_HelpCategoryDormant::dormatThird);
        dormatMap.put("What happens to Dormant Funds?", Kentico_HelpCategoryDormant::dormatFourth);
        dormatMap.put("What if the Dormant Account or Investment holder is deceased?", Kentico_HelpCategoryDormant::dormatFifth);
        dormatMap.put("What is a Dormant Account?", Kentico_HelpCategoryDormant::dormatSixth);
        dormatMap.put("What is the procedure to reactivate a Dormant Account?", Kentico_HelpCategoryDormant::dormatSeventh);
        dormatMap.put("Will I be notified that my account may become dormant?", Kentico_HelpCategoryDormant::dormatEighth);

        String[] list = {"How do I prevent my account or investment product from becoming dormant?", "How long will it take for my Dormant Account to be reactivated?",
                "Ireland State Savings Dormant Account Customer Notice", "What happens to Dormant Funds?", "What if the Dormant Account or Investment holder is deceased?",
                "What is a Dormant Account?", "What is the procedure to reactivate a Dormant Account?",
                "Will I be notified that my account may become dormant?"};


        By dormant = By.xpath("//a[@title='Dormant Accounts']");
        webUtil.scrollToView(dormant);
        webUtil.clickLog(dormant, "Dormant Account");
        webUtil.gettextlog(hdrlinkLbl, String::equals, "Dormant Accounts", "Header");
        validateSize(commonFAQLinks, list, "Dormant Accounts");
        List<WebElement> dormantLinks = webUtil.getElements(By.xpath("//article//a"));
        IntStream.range(0, dormantLinks.size()).forEach(j -> {
            WebElement element = dormantLinks.get(j);
            String text = element.getText();
            
            if (dormatMap.containsKey(text)) {
                webUtil.clickLog(element, text);
                dormatMap.get(text).accept(this);
                webUtil.navigateBack();
                dormantLinks.clear();
                dormantLinks.addAll(webUtil.getElements(commonFAQLinks));
            }
           
                
           
           
            
        });
        webUtil.clickLog(lnkHelpandSupport, "help and support link");
    }

   

        @SneakyThrows
        public void dormatFirst() {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How do I prevent my account or investment product from becoming dormant?");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("How do I prevent my account or investment product from becoming dormant?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);

        }

        @SneakyThrows
        public void dormatSecond()  {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles How long will it take for my Dormant Account to be reactivated?");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("How long will it take for my Dormant Account to be reactivated?", ExcelRows) - 1;
            String actual = webUtil.getText(By.xpath("//article/p[1]"));
            String expected = ExcelRows.get(index).getPara();
            webUtil.CompareString(actual,String::equals, expected);

        }

        @SneakyThrows
        public void dormatThird() {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Ireland State Savings Dormant Account Customer Notice");
            ExtentCucumberAdapter.addTestStepLog("Validating:" + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("Ireland State Savings Dormant Account Customer Notice", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);
            webUtil.gettextByAttribute(By.xpath("//article/p/a"), String::equals, getUrl() + "/getmedia/61e842ff-114d-487f-8fb9-f5d5267497b8/a4-dormancy-bilingual-customer-notice.PDF", "href");
            webUtil.openPDFInNewTab(By.xpath("//article/p/a"), "a4-dormancy-bilingual-customer-notice.PDF");
        }

        @SneakyThrows
        public void dormatFourth() {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What happens to Dormant Funds?");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("What happens to Dormant Funds?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);

        }

        @SneakyThrows
        public void dormatFifth(){
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What if the Dormant Account or Investment holder is deceased?");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("What if the Dormant Account or Investment holder is deceased?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);

        }

        @SneakyThrows
        public void dormatSixth(){
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What is a Dormant Account?");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("What is a Dormant Account?", ExcelRows) - 1;
            String actual = webUtil.getText(By.xpath("//article/p[1]"));
            String expected = ExcelRows.get(index).getPara();
            webUtil.CompareString(actual,String::equals, expected);
            webUtil.gettextByAttribute(By.xpath("//article/p/a"), String::equals, getUrl() + "/getmedia/61e842ff-114d-487f-8fb9-f5d5267497b8/a4-dormancy-bilingual-customer-notice.PDF", "href");

        }

        @SneakyThrows
        public void dormatSeventh()  {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles What is the procedure to reactivate a Dormant Account?");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("What is the procedure to reactivate a Dormant Account?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);
            compareBullets(ExcelRows, index);
            comparesubLinks(ExcelRows, index);


        }

        @SneakyThrows
        public void dormatEighth() {
            webUtil.gettextlog(breadcrumbs, String::equals, "Home Help and Support Help Articles Will I be notified that my account may become dormant?");
            ExtentCucumberAdapter.addTestStepLog("Validating: " + webUtil.getText(articleHdr));
            System.out.println("Validating:" + webUtil.getText(articleHdr));
            int index = testdata.getRowIndex("Will I be notified that my account may become dormant?", ExcelRows) - 1;
            compareParagrph(ExcelRows, index);
            compareBullets(ExcelRows, index);


        }
    
}
