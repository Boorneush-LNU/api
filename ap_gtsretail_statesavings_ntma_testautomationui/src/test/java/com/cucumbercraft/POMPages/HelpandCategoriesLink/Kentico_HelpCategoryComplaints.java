package com.cucumbercraft.POMPages.HelpandCategoriesLink;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class Kentico_HelpCategoryComplaints extends CategoryUtils {
    public Excelutils testdata = new Excelutils();
    String sheetName = "Complaints";
//    List<TestData> ExcelRows = testdata.getTestData(config.getK13ContentExcelPath(), sheetName,TestData.class);
    List<TestData> ExcelRows = testdata.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), sheetName, TestData.class);

    public Kentico_HelpCategoryComplaints(WebDriver driver) {
        super(driver);
        

    }

    public void complaints()  {
        By complaints= By.xpath("//a[@href='/help-support/help-articles/complaints']");
        webUtil.scrollToView(complaints);
        webUtil.clickLog(complaints,"Complaints");
        webUtil.gettextlog(hdrlinkLbl, String::equals, "Complaints", "Header");

        String complaintLinks[]={"Ireland State Savings Customer Complaints Process"};
       
        validateSize(By.xpath("//article//a"),complaintLinks,"Complaints");
        for(int b=1;b<=complaintLinks.length;b++){
            String complaintTab = webUtil.getText(By.xpath("//*[@class='m14-help_content--listing']/ul/li["+b+"]/a"));
            By complaintElement = By.xpath("//*[@class='m14-help_content--listing']/ul/li["+b+"]/a");
            webUtil.waitFor(1000);
            if(complaintTab.equals(complaintLinks[b-1])){
                switch (complaintTab){
                    case "Ireland State Savings Customer Complaints Process":
                        webUtil.scrollToView(complaintElement);
                        webUtil.clickLog(complaintElement,complaintTab);
                        webUtil.gettextlog(breadcrumbs,String::equals,"Home Help and Support Help Articles Ireland State Savings Customer Complaints Process");
                        IrelandStateSavingsCustomerComplaintsProcess();
                        webUtil.navigateBack();
                        break;

                }
            }
        }
        webUtil.clickLog(lnkHelpandSupport, "help and support link");
       

    }
    public void IrelandStateSavingsCustomerComplaintsProcess()  {
        ExtentCucumberAdapter.addTestStepLog("Validating: " +webUtil.getText(articleHdr));
        System.out.println("Validating:" + webUtil.getText(articleHdr));
        webUtil.gettextlog(articleHdr,String::equals,"Ireland State Savings Customer Complaints Process","Content");
        int index = testdata.getRowIndex("Ireland State Savings Customer Complaints Process", ExcelRows) - 1;
        compareParagrph(ExcelRows, index);
        compareHeader(ExcelRows, index);
        compareBullets(ExcelRows, index);
        comparesubLinks(ExcelRows, index);

        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[1]"),String::equals,getUrl()+"/getmedia/ead16fd2-f95f-4021-9088-074fdea9e376/PrizeBonds_Customer_ComplaintForm_1.pdf","href");
        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[2]"),String::equals,getUrl()+"/getmedia/4d4065f8-d9ce-419f-b419-18b5086c21dc/StateSavings_Customer_ComplaintForm_1.pdf","href");
        webUtil.gettextByAttribute(By.xpath("//article/p[3]/a[3]"),String::equals,"mailto:Complaints@StateSavings.ie","href");
        webUtil.gettextByAttribute(By.xpath("//article/ul[2]/li[3]/p/a"),String::equals,"mailto:Appeals@StateSavings.ie","href");
        webUtil.gettextByAttribute(By.xpath("//article/p[6]/a[1]"),String::equals,getUrl()+"/getmedia/60bc8ddd-b36f-4d93-99fd-4ff6dcbf3040/prizebonds_complainthandlingprocess.pdf","href");
        webUtil.gettextByAttribute(By.xpath("//article/p[6]/a[2]"),String::equals,getUrl()+"/getmedia/3567162b-a2a2-47a6-98b5-87de8d53c2d9/complaints_statesavings.pdf","href");
        webUtil.gettextByAttribute(By.xpath("//article/ul[3]/li[1]/ul[1]/li[2]/p/a"),String::equals,"mailto:Prizebonds@StateSavings.ie","href");
        webUtil.gettextByAttribute(By.xpath("//article/ul[3]/li[2]/ul[1]/li[2]/p/a"),String::equals,"mailto:Service@StateSavings.ie","href");

        webUtil.openPDFInNewTab(By.xpath("//article/p[3]/a[1]"),"PrizeBonds_Customer_ComplaintForm.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p[3]/a[2]"),"StateSavings_Customer_ComplaintForm.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p[6]/a[1]"),"prizebonds_complainthandlingprocess.pdf");
        webUtil.openPDFInNewTab(By.xpath("//article/p[6]/a[2]"),"complaints_statesavings.pdf");


    }


}