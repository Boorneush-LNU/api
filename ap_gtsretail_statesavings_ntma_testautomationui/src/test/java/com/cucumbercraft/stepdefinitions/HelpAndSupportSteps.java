package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.POMPages.HelpAndSupportPage;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.*;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.*;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_HelpAndSupportPage;
import com.cucumbercraft.POMPages.PreLoginPages.Kentico13_OurProducts;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class HelpAndSupportSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();

    HelpAndSupportPage supportPage = new HelpAndSupportPage(driver);
    Kentico_HelpCategory_About kentico_helpCategory_about = new Kentico_HelpCategory_About(driver);
    Kentico_HelpCategory_GettingStarted kenticoHelpCategoryGettingStarted = new Kentico_HelpCategory_GettingStarted(driver);
    Kentico_HelpCategory_PersonalInformation kenticoHelpCategoryPersonalInformation = new Kentico_HelpCategory_PersonalInformation(driver);
    Kentico_HelpCategory_YourSavings yourSavings = new Kentico_HelpCategory_YourSavings(driver);
    Kentico_HelpCategory_Security kenticoHelpCategorySecurity = new Kentico_HelpCategory_Security(driver);
    Kentico_HelpCategory_Notification kenticoHelpCategoryNotification = new Kentico_HelpCategory_Notification(driver);
    Kentico_HelpCategory_CloseIrelandService closeService = new Kentico_HelpCategory_CloseIrelandService(driver);
    Kentico_HelpCategoryBereavement bereavement = new Kentico_HelpCategoryBereavement(driver);
    Kentico_HelpCategoryComplaints complaints = new Kentico_HelpCategoryComplaints(driver);
    Kentico_HelpCategoryDormant dormant = new Kentico_HelpCategoryDormant(driver);
    Kentico_HelpCategoryHowtoPurchase howtoPurchase = new Kentico_HelpCategoryHowtoPurchase(driver);
    Kentico_HelpCategoryIrelandStateSavingsProducts irelandStateSavingsProducts=new Kentico_HelpCategoryIrelandStateSavingsProducts(driver);
    Kentico_HelpCategoryManageMyDetails manageMyDetails = new Kentico_HelpCategoryManageMyDetails(driver);
    Kentico_HelpCategoryPrizeBonds prizeBonds = new Kentico_HelpCategoryPrizeBonds(driver);
    Kentico_HelpCategoryRepayments repayments = new Kentico_HelpCategoryRepayments(driver);
    Kentico_HelpCategorySecurityAndFinancialCrime securityAndFinancialCrime = new Kentico_HelpCategorySecurityAndFinancialCrime(driver);
    Kentico_HelpCategoryFinancialLiteracy FinancialLiteracy = new Kentico_HelpCategoryFinancialLiteracy(driver);
    Kentico13_OurProducts ourProducts=new Kentico13_OurProducts(driver);
    Kentico13_HelpAndSupportPage hAndS=new Kentico13_HelpAndSupportPage(driver);

    @And("Select HelpandSupport tab")
    public void selectHelpandSupportTab() {
        try {
            supportPage.udfHelpAndSupportSelection();
            ExtentCucumberAdapter.addTestStepLog("Help and Support Page displayed");
            log.info("Help and Support Page displayed");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Validate HelpandSupport Page")
    public void validateHelpandSupportPage() {
        try {
            supportPage.udfValidateHelpandSupportPage();
            supportPage.udfValidateHelpCategories();
            supportPage.udfValidateFormsDownloads();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Validate HelpandSupport FAQ Links")
    public void validateHelpandSupportFAQLinks() {
        try {
            supportPage.validateFAQlinks();
        } catch (Exception e) {
            Assert.fail("validateHelpandSupportFAQLinks : " + e.getMessage());
        }
    }

    @And("Validate About Links")
    public void validateAboutLinks() {
        kentico_helpCategory_about.aboutLinks();

    }

    @And("Validate GettingStarted Links")
    public void validateGettingStartedLinks() {
        kenticoHelpCategoryGettingStarted.gettingStarted();
    }

    @And("Validate Notification Links")
    public void validateNotificationLinks() {
        kenticoHelpCategoryNotification.notificationLinks();
    }

    @And("Validate PersonalInformation Links")
    public void validatePersonalInformationLinks() {
        kenticoHelpCategoryPersonalInformation.personlaInformation();
    }

    @And("Validate Security Links")
    public void validateSecurityLinks() {
        kenticoHelpCategorySecurity.securityLinks();
    }

    @And("Validate YourSavings Links")
    public void validateYourSavingsLinks() {
        yourSavings.yourSavingsLinks();
    }

    @And("Validate Closing Your Service Links")
    public void validateClosingYourServiceLinks() {
        closeService.closeServiceLinks();
    }
    @And("Validate IrelandStateSavings Links")
    public void validateIrelandStateSavingsLinks(){irelandStateSavingsProducts.stateSavingsProduct();}
    @And("Validate PrizeBonds Links")
    public void validatePrizeBondsLinks() {
        prizeBonds.prizeBonds();
    }



    @And("Validate HowToPurchase Links")
    public void validateHowToPurchaseLinks() {
        howtoPurchase.howToPurchase();
    }

    @And("Validate ManageMyDetails Links")
    public void validateManageMyDetailsLinks() {
        manageMyDetails.manageMyDetails();
    }

    @And("Validate Repayments Links")
    public void validateRepaymentsLinks() {
        repayments.repayments();
    }

    @And("Validate BereavementGuideSupport Links")
    public void validateBereavementGuideSupportLinks() {
        bereavement.bereavementGuideSupport();
    }

    @And("Validate Complaints Links")
    public void validateComplaintsLinks() {
        complaints.complaints();
    }

    @And("Validate DormantAccounts Links")
    public void validateDormantAccountsLinks() {
        dormant.dormantAccount();
    }

    @And("Validate ContactUs Links")
    public void validateContactUsLinks() {
    }

    @And("Validate SecurityFinancialCrime Links")
    public void validateSecurityFinancialCrimeLinks() {
        securityAndFinancialCrime.securityAndFinancialCrime();
    }

    @And("Validate FinancialLiteracy Links")
    public void validateFinancialLiteracyLinks() {
        FinancialLiteracy.FinancialLiteracy();
    }


    @And("Validate FormsDownloads Links as English {string}")
    public void validateFormsDownloadsLinksAsEnglish(String enableDownload) {
        String flagVal;
        try {
            flagVal = FrameworkConstants.getEnableDownloadInd();

            if (flagVal.equalsIgnoreCase("Yes") && enableDownload.equalsIgnoreCase("download")) {
                flagVal = "Yes";
                supportPage.udfValidateFormsDownloadsLinks("English", flagVal, Boolean.parseBoolean(properties.getProperty("ValidateEnvironment")));
                ExtentCucumberAdapter.addTestStepLog("Forms and Download link validated");
            } else {
                flagVal = "No";
                supportPage.udfValidateFormsDownloadsLinks("English", flagVal, Boolean.parseBoolean(properties.getProperty("ValidateEnvironment")));
                ExtentCucumberAdapter.addTestStepLog("Forms links/page validated");
            }
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("validateFormsDownloadsLinks: " + e.getMessage());
            throw new ExceptionUtils(e.getMessage());
        }
    }

    @And("Validate FormsDownloads Links as Gaeilge {string}")
    public void validateFormsDownloadsLinksAsGaeilge(String enableDownload) {
        String flagVal;
        try {
            flagVal = FrameworkConstants.getEnableDownloadInd();;

            if (flagVal.equalsIgnoreCase("Yes") && enableDownload.equalsIgnoreCase("download")) {
                flagVal = "Yes";
                supportPage.udfValidateFormsDownloadsLinks("Irish", flagVal, Boolean.parseBoolean(properties.getProperty("ValidateEnvironment")));
                ExtentCucumberAdapter.addTestStepLog("Forms and Download link validated");
            } else {
                flagVal = "No";
                supportPage.udfValidateFormsDownloadsLinks("Irish", flagVal, Boolean.parseBoolean(properties.getProperty("ValidateEnvironment")));
                ExtentCucumberAdapter.addTestStepLog("Forms links/page validated");
            }
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("validateFormsDownloadsLinks: " + e.getMessage());
            throw new ExceptionUtils(e.getMessage());
        }
    }

    @And("Validate Pdf Contents {string}")
    public void validatePdfContents(String str) throws IOException {

        File sourcePath = new File(Util.getTargetPath() + "/" + str);
        File[] listOfFiles = sourcePath.listFiles();
        assert listOfFiles != null;
        String targetPath = new File(Util.getTargetPath()).getParentFile().getAbsolutePath() + "/ExcelReports";
        File dir = new File(targetPath);
        if (!dir.exists())
            dir.mkdirs();

        String excelFilePath = new File(dir, "Results.xlsx").getAbsolutePath();  // Path to the Excel file
        System.out.println(excelFilePath);
        Workbook workbook = null;

        // Check if the workbook already exists
        File excelFile = new File(excelFilePath);
        if (excelFile.exists()) {
            // If the file exists, open it
            try (FileInputStream fis = new FileInputStream(excelFilePath)) {
                workbook = new XSSFWorkbook(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // If the file does not exist, create a new workbook
            workbook = new XSSFWorkbook();
        }
        // Create a new workbook and a sheet
//        Workbook workbook = new XSSFWorkbook();
        assert workbook != null;
        Sheet sheet = workbook.createSheet(str);

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("File Name");
        headerRow.createCell(1).setCellValue("Result");

        int rowCount = 1; // Start from the second row

        for (File file : listOfFiles) {
            if (file.isFile() && (file.getName().endsWith(".pdf") || file.getName().endsWith(".PDF"))) {
                try {

                    PDDocument document = Loader.loadPDF(file);

                    String pdfContent = new PDFTextStripper().getText(document).replaceAll("\\r\\n", "");

                    Row row = sheet.createRow(rowCount++);
                    row.createCell(0).setCellValue(file.getName());

                    if (pdfContent.contains("102024") || pdfContent.contains("October 2024")) {
                        row.createCell(1).setCellValue("Pass - Date");
                    } else if (pdfContent.contains("Ireland State Savings")) {
                        row.createCell(1).setCellValue("Pass - Ireland State Savings");

                    } else if (pdfContent.contains("Coigilteas Stáit na hÉireann")) {
                        row.createCell(1).setCellValue("Pass - Coigilteas Stáit na hÉireann");

                    } else {
                        row.createCell(1).setCellValue("Failed - Ireland State Savings");
                    }

                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        @Then("enter keyword in search box")
    public void enterKeywordInSearchBox() {

        supportPage.enterKeywordForSearch();


    }

    @Then("validate results are displayed")
    public void validateResultsAreDisplayed() {
        supportPage.validateSearchResults();
    }

    @When("User Click {string} link")
    public void userClickLinkOnHelpAndSupport(String linkName) {
        try {
            switch (linkName) {
                case "Manage my details":
                    supportPage.clickManageMyDetailsLink();
                    break;

                case "What is the State Savings Customer Number (SSCN)?":
                    manageMyDetails.clickStateSavingsNumber();
                    break;
                case "Watch Video: ‘Your State Savings Customer Number (SSCN)’":
                    manageMyDetails.clickWatchVideoSSCN();
                    break;

            }
        }
        catch (Exception e) {
            Assert.fail("ManageMyDetailsLinks - " + e.getMessage());
        }
    }

    @And("Validate the content on SSCN page")
    public void validateTheContentForCheckMyNumbersSection() {
        extractExcelValue= FrameworkConstants.getExpMapNewCommsPg();
        this.pageName="SSCN Page";
        logReportStepValidationStart("Check SCCN page content validation Initiated");
        strCompareString(ourProducts.getBannerTitle(pageName),extractExcelValue.get("expBannerTitle"),"Banner Title");
        strCompareString(ourProducts.getBannerText(),extractExcelValue.get("expBannerText"),"Banner Text");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expSSCNFAQLink")),hAndS.getSSCNFAQ(),"SSCN FAQ", "SSCN");
        getListItemsComparisonBtnLink(expListContent(extractExcelValue.get("expSSCNiFrameLink")),hAndS.getiFrameSSCN(),"SSCN FAQ", "SSCN iFrame");
        strCompareString(hAndS.getTipHdr(),extractExcelValue.get("expTipHdr"),"Tip Header ");
        strCompareString(hAndS.getSaveHdr(),extractExcelValue.get("expSaveHdr"),"Save Header ");
        strCompareString(hAndS.getSSCNDesc(),extractExcelValue.get("expSSCNDesc"),"SSCN Description ");
        strCompareString(hAndS.getHowToUseHdr(),extractExcelValue.get("expHowDoIUseSSCNHdr"),"How Do I Use my SSCN Header");
        hAndS.validateImageLoaded();
        hAndS.validateiFrame();

        logReportStepValidationEnd("Check SSCN page content validation Completed");
    }



}
