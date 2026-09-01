package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.POMPages.PBAdmin.PBAdmin_Reports;
import com.cucumbercraft.POMPages.PBAdmin.PBAdmin_YourDetailsPg;
import com.cucumbercraft.POMPages.PBAdmin.PBAdmin_YourOrdersPg;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import freemarker.template.SimpleDate;
import io.cucumber.java.en.*;
import io.cucumber.plugin.event.Node;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class PBAdminStepDefinitions extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    PBAdmin_YourOrdersPg pbAdminYourOrdersPg=new PBAdmin_YourOrdersPg(driver);
    PBAdmin_YourDetailsPg pbAdminYourDetailsPg=new PBAdmin_YourDetailsPg(driver);
    PBAdmin_Reports pbAdminReportsPg=new PBAdmin_Reports(driver);
    HashMap<String, List<String>> expExtractMap=new HashMap<>();
    String status=null;
    String fromDate=null;
    String source=null;
    String toDate=null;
    int sizeMap=0;
    int sizePurList=0;
    String resultsPerPg=null;
    List<String> failureTrans=new ArrayList<>();
    String statusToChange=null;
    HashMap<String, List<String>> extractPurTransAllSummarySearch=new HashMap<>();
    HashMap<String, List<String>> paymentTransSummMapTransformedApp=new HashMap<>();
    HashMap<String, List<String>> paymentTransSummMapTransformedPark=new HashMap<>();
    Excelutils excelRow = new Excelutils();
    PurchaseModel data;
    String commentsUpdate=null;


    @And("Store the Search Results Online Purchase transactions from Orders Summary Page and its respective Order details")
    public void storeTheSearchResultsOnlinePurchaseTransactionsFromOrdersSummaryPageAndItsRespectiveOrderDetails() throws InterruptedException, ParseException {
        expExtractMap=pbAdminYourOrdersPg.getExpPurTransMap();
        log.info("EXTRACT MAP ORDERS PAGE: "+expExtractMap);
    }

    @And("In Reports tab, For {string} - Select the Search criteria Status-{string}, Products-{string}, Source-{string}, Date From - {string}, {string} and Click Search button")
    public void inReportsTabForSelectTheSearchCriteriaStatusProductsSourceDateFromAndClickSearchButton(String testCaseRefName, String status, String product, String source, String dateFrom, String dateTo) throws InterruptedException {
        testCaseName=testCaseRefName;

        if(adminUserType.equalsIgnoreCase("SS Admin User")){
            pbAdminReportsPg.selectProductsDropDownValue(source);
        }else if(adminUserType.equalsIgnoreCase("PB Admin User")){
            pbAdminReportsPg.selectSourceDropDownValue(source);
        }else{
            log.error("Please check the value of the PB Web Admin User type");
        }

        pbAdminReportsPg.selectStatusDropDownValue(status);
        if(!((dateFrom.equalsIgnoreCase("NA")) && (dateTo.equalsIgnoreCase("NA")))){
            pbAdminYourOrdersPg.selectDateFromCalendar("From", dateFrom);
            pbAdminYourOrdersPg.selectDateFromCalendar("To",dateTo);
        }else if((dateFrom.equalsIgnoreCase("NA")) && !(dateTo.equalsIgnoreCase("NA"))){
            pbAdminYourOrdersPg.selectDateFromCalendar("To",dateTo);
        } else if ((dateTo.equalsIgnoreCase("NA")) && !(dateFrom.equalsIgnoreCase("NA"))) {
            pbAdminYourOrdersPg.selectDateFromCalendar("From", dateFrom);
        }else{
            log.info("Date From and Date to values not set in the search criteria and Values passed are 'NONE'");
        }
        pbAdminReportsPg.clickRunReportBtn();
    }

    @Then("Report generated should match the results as generated from Orders tab")
    public void reportGeneratedShouldMatchTheResultsAsGeneratedFromOrdersTab() throws InterruptedException, ParseException {
        Thread.sleep(3000);
        successFailureLog(pbAdminReportsPg.isReportDisplayed(),"Orders Report is generated","Orders Report is not generated");

        HashMap<String,List<String>> actExpPurTransMap=pbAdminReportsPg.actExpPurTransMap2();
        log.info("EXTRACT MAP ORDERS REPORT: "+actExpPurTransMap);

        if(expExtractMap.equals(actExpPurTransMap)){
            log.info("Validation PASSED. Extract Records from Orders Page and Orders Reports page match each other");
        }
        else{
            log.error("Validation FAILED. Extract Records from Orders Page and Orders Reports page DO NOT match.");
        }

    }

    @And("Click on download button and Select Excel option from dropdown")
    public void clickOnDownloadButtonAndSelectExcelOptionFromDropdown() throws IOException {
        pbAdminReportsPg.clickDownloadFileBtn();
        pbAdminReportsPg.selectDwnldOptValue("Excel");
        if(pbAdminReportsPg.isFileDownloaded(testCaseName)){
         ExtentCucumberAdapter.getCurrentStep().pass("Purchase Report downloaded successfully");
         log.info("Purchase Report downloaded successfully");
        }else{
         ExtentCucumberAdapter.getCurrentStep().fail("Purchase Report downloaded failed");
         log.info("Purchase Report downloaded failed");
         Assert.fail();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("Click on the {string} menu tab")
    public void clickOnTheMenuTab(String menuTab) {
        pbAdminYourOrdersPg.clickMenu(menuTab);
        if(menuTab.equalsIgnoreCase("Orders")){
            strCompareString(pbAdminYourOrdersPg.hdrOrdersTab(),"WebSite Orders", "header");
        } else if (menuTab.equalsIgnoreCase("Reports")) {
            strCompareString(pbAdminReportsPg.getHdr(),"State Savings Admin - Website Reports Section", "header");
        } else if (menuTab.equalsIgnoreCase("Orders Report")) {
            strCompareString(pbAdminReportsPg.getHdr(),"Orders Report", "header");
        }else{
            log.error("Please check the menu options passed from Feature file");
            Assert.fail("Please check the menu options passed from Feature file");
        }
    }


    @And("In Orders Summary Page, Assign the Search criteria status-{string}, source -{string}, From Date - {string}, To Date - {string}, Results Per Page - {string} and Click Search button")
    public void inOrdersSummaryPageAssignTheSearchCriteriaStatusSourceFromDateToDateResultsPerPageAndClickSearchButton(String status, String source, String fromDate, String toDate, String resultsPerPg) throws InterruptedException {
        this.status=status;
        this.source=source;
        this.fromDate=fromDate;
        this.toDate=toDate;
        this.resultsPerPg=resultsPerPg;
        pbAdminYourOrdersPg.selectStatusDropDown(status)
                .selectSourceDropDown(source,adminUserType)
                .selectResultsPerPgDropDown("All")
                .selectDateFromCalendar("From",fromDate)
                .selectDateFromCalendar("To",toDate)
                .clickSearchBtn();
        Thread.sleep(3000);

        extractPurTransAllSummarySearch=pbAdminYourOrdersPg.getOrderDetailsPurTransMap();
        int sizeMapTemp=extractPurTransAllSummarySearch.size();
        this.sizeMap=sizeMapTemp;

        pbAdminYourOrdersPg.selectResultsPerPgDropDown(resultsPerPg);

        defaultDrpDwnOptionValueCheck(status, pbAdminYourOrdersPg.getStatusDropDownValueSelected(),"For Approval", "Status");
        defaultDrpDwnOptionValueCheck(resultsPerPg, pbAdminYourOrdersPg.getResultsPerPageDropDownValueSelected(),"10", "Results Per Pg");
        defaultDrpDwnOptionValueCheck(fromDate.replaceAll("_"," "), pbAdminYourOrdersPg.getCalendarFromFieldValue(),pbAdminYourOrdersPg.expToFromDateConvertor()[0], "From Date");
        defaultDrpDwnOptionValueCheck(toDate.replaceAll("_"," "), pbAdminYourOrdersPg.getCalendarToFieldValue(),pbAdminYourOrdersPg.expToFromDateConvertor()[1], "To Date");
        defaultDrpDwnOptionValueCheck(source, pbAdminYourOrdersPg.getSourceDropDownValueSelected(adminUserType),"All", "Source");
    }

    public void defaultDrpDwnOptionValueCheck(String expNonDefaultValue, String actValue, String defaultValue, String sectionLog){
        if((adminUserType.equalsIgnoreCase("SS Admin User") && (sectionLog.equalsIgnoreCase("Source")))){
            log.info("Source field validation not expected for SS Admin User");
        }
        else{
            if(expNonDefaultValue.equalsIgnoreCase("Default")){
                strCompareString(actValue,defaultValue,sectionLog);
            }
            else{
                strCompareString(actValue,expNonDefaultValue,sectionLog);
            }
        }

    }


    @Then("Validate search results should be displayed as per the criteria set for Results per page and user should be able to navigate to different pages using pagination to view Website Orders Summary")
    public void validateSearchResultsShouldBeDisplayedAsPerTheCriteriaSetForResultsPerPageAndUserShouldBeAbleToNavigateToDifferentPagesUsingPaginationToViewWebsiteOrdersSummary() {
        String totalPageNo=null;
        if(resultsPerPg.equalsIgnoreCase("All")){
            try{
                if(pbAdminYourOrdersPg.isNxtBtnEnabledCheck_Pagination()){
                    ExtentCucumberAdapter.getCurrentStep().fail("Pagination is displayed. Validation FAILED");
                    log.error("Pagination is displayed. Validation FAILED");
                }
                else{
                    log.info("All Purchases are displayed without pagination. Validation PASSED");
                    ExtentCucumberAdapter.getCurrentStep().pass("All Purchases are displayed without pagination. Validation PASSED");
                }
            }
            catch(Exception NoSuchElementException){
                log.info("All Purchases are displayed without pagination. Validation PASSED");
                ExtentCucumberAdapter.getCurrentStep().pass("All Purchases are displayed without pagination. Validation PASSED");
            }

       }else{
            totalPageNo=String.valueOf((int) Math.ceil((float) sizePurList / Integer.parseInt(resultsPerPg)));
            validatePageNavigationAndResults(totalPageNo,resultsPerPg);
            clickOnTheMenuTab("Orders");
        }
    }

    public void validatePageNavigationAndResults(String totalPageNo, String resultsPerPg){
        String valuePg=null;
        String pgNoLblSum=null;
        String expPgLbl=null;

        while(pbAdminYourOrdersPg.isNxtBtnEnabled_Pagination()){
            valuePg=pbAdminYourOrdersPg.getCurrentPgNoLbl();
            pgNoLblSum=pbAdminYourOrdersPg.getPgNoLblSumm();
            expPgLbl="Page "+valuePg+" of "+totalPageNo;
            resultsPerPgValidation(resultsPerPg);
            if(pgNoLblSum.equalsIgnoreCase("Page "+valuePg+" of "+totalPageNo)){
                log.info("Page Number label-pagination is correct");
                ExtentCucumberAdapter.getCurrentStep().pass("Page Number label-pagination is correct");
            }
            else{
                log.error("Page Number label-pagination is incorrect. >>>Expected: "+ expPgLbl+ ">>> Actual: "+ pgNoLblSum);
                ExtentCucumberAdapter.getCurrentStep().fail("Page Number label-pagination is incorrect. >>>Expected: "+ expPgLbl+ ">>> Actual: "+ pgNoLblSum);
            }
            pbAdminYourOrdersPg.clickPaginationNxtBtn();
        }
    }

    public void resultsPerPgValidation(String resultsPerPg){
        HashMap<String, List<String>> extractPurTransAllSummary=pbAdminYourOrdersPg.extractAllPaymentTransSummary();
        int sizeMap=extractPurTransAllSummary.size();
        if(sizeMap == Integer.parseInt(resultsPerPg)){
            log.info("Summary Results count is validated successfully");
            ExtentCucumberAdapter.getCurrentStep().pass("Summary Results count is validated successfully");
        }
        else{
            log.error("Summary Results count is NOT correct. Validation failed >> Expected count: "+ resultsPerPg+">>> Actual Count: "+sizeMap);
            ExtentCucumberAdapter.getCurrentStep().fail("Summary Results count validation FAILED");
        }
    }


    @Then("Validate that all the purchase reference transactions are displayed on the Order Summary Page")
    public void validateThatAllThePurchaseReferenceTransactionsAreDisplayedOnTheOrderSummaryPage() {
        pbAdminYourOrdersPg.selectResultsPerPgDropDown("All");
        log.info(payRefList);
//        try{
            log.info("Expected Purchase Transaction Map details are as follows: "+ expPurTransSummaryMap);
            System.out.println("OVERALL PURCHASE REF LIST - BUY NOW>>"+payRefList);
            for (String purTrans:payRefList){
                List<String> expTransSummaryList=new ArrayList<>();
                List<String> actTransSummaryList=new ArrayList<>();
                logReportStepValidationStart("STARTED "+purTrans+" initiated");
                if(pbAdminYourOrdersPg.searchPaymentTransRef(purTrans)){
                    logReportStepValidationStart("Validation for Purchase transaction "+purTrans+" initiated");

                    expTransSummaryList=expListTransform(expPurTransSummaryMap.get(purTrans));
                    actTransSummaryList=pbAdminYourOrdersPg.getSpecificPaymentTransSummary(purTrans);
                    getListItemsComparison(expTransSummaryList,actTransSummaryList,"Order Summary Page for "+purTrans);
                }else{
                    failureTrans.add(purTrans);
                    log.error("Purchase transaction: "+purTrans+" does not exist on the Order Summary page");
                    ExtentCucumberAdapter.getCurrentStep().fail("Purchase transaction: "+purTrans+" does not exist on the Order Summary page");
                }
                logReportStepValidationEnd("Validation for Purchase transaction "+purTrans+" completed");
//                actTransSummaryList.clear();
//                expTransSummaryList.clear();
            }

        if(failureTrans.size()>0){
            log.error("Purchase transactions not displayed on Order Summary Page are : "+failureTrans);
            ExtentCucumberAdapter.getCurrentStep().fail("Purchase transactions not displayed on Order Summary Page are : "+failureTrans);
//                Assert.fail("Purchase transactions not displayed on Order Summary Page are : "+failureTrans);
        }
        else{
            log.info("All Online Submitted Purchase Transactions are present in Order Summary Page");
            ExtentCucumberAdapter.getCurrentStep().pass("All Online Submitted Purchase Transactions are present in Order Summary Page");
        }

//        }
//        catch(Exception e){
//            log.error("Failure exception: " + e);
//            ExtentCucumberAdapter.getCurrentStep().fail("Failure exception: " + e);
//        }

    }

    public List<String> expListTransform(List<String> tempExpDataList){
        List <String> tempList=new ArrayList<>();
        for(int i=4;i<tempExpDataList.size();i++){
            tempList.add(tempExpDataList.get(i));
        }
        log.info(tempList);
        return tempList;
    }

    public List<String> expListTransform1(List<String> tempExpDataList){
        List <String> tempList=new ArrayList<>();
        for(int i=4;i<tempExpDataList.size();i++){
            tempList.add(tempExpDataList.get(i));
        }
        tempList.add("");
        tempList.add("");
        return tempList;
    }

    @And("Click on each Purchase transaction to validate the transaction details on the Order Details page")
    public void clickOnEachPurchaseTransactionToValidateTheTransactionDetailsOnTheOrderDetailsPage() throws InterruptedException {
        for(String purTransfRef:payRefList){
            if(!(failureTrans.contains(purTransfRef))){
                transValidationDetails(purTransfRef);
            }
            else{
                log.error("Purchase transaction: "+purTransfRef+" does not exist on the Order Summary page");
                ExtentCucumberAdapter.getCurrentStep().fail("Purchase transaction: "+purTransfRef+" does not exist on the Order Summary page");
            }
        }
    }


    public void mapComparisonLabelsOrderDetails(HashMap<String,HashMap<String,String>> expPurchaseOrderDetailsMap, String purTransfRef){
        log.info("ACT KEYSET>>>>>>>>"+pbAdminYourDetailsPg.transDetailsMap().keySet());
        log.info("EXP KEYSET>>>>>>>"+expPurchaseOrderDetailsMap.get(purTransfRef).keySet());
        if(pbAdminYourDetailsPg.transDetailsMap().keySet().equals(expPurchaseOrderDetailsMap.get(purTransfRef).keySet())){
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Field labels validated successfully");
        }else{
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL,"Difference in base fields observed between expected and actual key labels");
        }
    }

    @And("Search the purchase transaction using Payment Reference field")
    public void searchThePurchaseTransactionUsingPaymentReferenceField()  {
        paymentTransSearchValidation(paymentRefNo);
    }

    @Then("Validate that the Purchase Reference transaction details should be displayed")
    public void validateThatThePurchaseReferenceTransactionDetailsShouldBeDisplayed() throws IOException {
        expPurTransSummaryMap=extractDataJsonMapSummPBSSUser(adminUserType);
        pbAdminYourOrdersPg.selectResultsPerPgDropDown("All");
        List<String> getSpecTransSummary=pbAdminYourOrdersPg.getSpecificPaymentTransSummary1(paymentRefNo);
        log.info("Actual Summary List details are as follows:"+ getSpecTransSummary);

        List<String> expTransSummaryList=new ArrayList<>();
        expTransSummaryList=expListTransform1(expPurTransSummaryMap.get(paymentRefNo));

                log.info("Expected Summary List details are as follows:"+ expTransSummaryList);
        successFailureLog(expTransSummaryList.equals(getSpecTransSummary),
                "Purchase Payment Reference details on Summary Orders Page validated successfully",
                "Purchase Payment Reference details on Summary Orders Page is not matching expected details");
    }

    @And("Click on {string} checkbox for the Purchase Transaction from Orders Summary Page")
    public void clickOnCheckboxForThePurchaseTransactionFromOrdersSummaryPage(String selectCheckbox) {
       try{
           pbAdminYourOrdersPg.selectProcessCheckbox(selectCheckbox,paymentRefNo);
           FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Payment Ref No-"+paymentRefNo+ " >> "+selectCheckbox+"checkbox is clicked");
       }catch(Exception e){
           Assert.fail(e.getMessage());
           FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Payment Ref No-"+paymentRefNo+ " >> "+selectCheckbox+"checkbox is NOT clicked");
       }
    }

    @And("Complete the process to {string} the selected transaction from the Order Summary Page")
    public void completeTheProcessToTheSelectedTransactionFromTheOrderSummaryPage(String selectCheckbox) {
        String expProcSelectedLbl=selectCheckbox+" 1 record(s) with a value of "+expPurTransDetailsMasterMap.get(paymentRefNo).get("Amount :").replace("€","");
        pbAdminYourOrdersPg.clickProcessSelectedBtn();
        strCompareString(pbAdminYourOrdersPg.getProcessSelectedLbl(selectCheckbox),expProcSelectedLbl,"Process Selected Sum Label details");
        pbAdminYourOrdersPg.clickOK_ProcessSelectedBtn();
        strCompareString(pbAdminYourOrdersPg.hdrOrdersTab(),"WebSite Orders", "header");
    }

    @Then("In Orders Summary Page, Assign the Search criteria {string} from status drop down and Click Search button")
    public void inOrdersSummaryPageAssignTheSearchCriteriaFromStatusDropDownAndClickSearchButton(String selectStatus) throws InterruptedException {
        this.statusToChange=selectStatus;
        pbAdminYourOrdersPg.selectStatusDropDown(selectStatus)
                .selectResultsPerPgDropDown("All").clickSearchBtn();
        Thread.sleep(2000);
        strCompareString(pbAdminYourOrdersPg.getStatusDropDownValueSelected(),selectStatus,selectStatus);
    }

    @And("Validate that the transaction should be displayed on the {string} list of purchases")
    public void validateThatTheTransactionShouldBeDisplayedOnTheListOfPurchases(String statusListToValidate) {
        strCompareString(pbAdminYourOrdersPg.getStatusDropDownValueSelected(),statusListToValidate,"Status Dropdown");
        searchThePurchaseTransactionUsingPaymentReferenceField();
    }

    @But("Validate that the transaction should not be displayed on the {string} list of purchases")
    public void validateThatTheTransactionShouldNotBeDisplayedOnTheListOfPurchases(String statusListToValidate) {
        strCompareString(pbAdminYourOrdersPg.getStatusDropDownValueSelected(),statusListToValidate,"Status Dropdown");
        purTransListNegativeDisplayValidation(paymentRefNo, statusListToValidate);
    }

    public void purTransListNegativeDisplayValidation(String paymentRefNo, String statusDropDown){
        try {
            successFailureLogBUT(pbAdminYourOrdersPg.searchPaymentTransRef(paymentRefNo),
                    "Purchase Payment Reference value is not displayed: "+paymentRefNo+" in status search list of "+ statusDropDown,
                    "Purchase Payment Reference value is displayed in incorrect search results: "+ paymentRefNo+" in status search list of "+ statusDropDown);
        }
        catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }


    @And("Validate that {string} transactions should be displayed on the {string} list of purchases")
    public void validateThatTransactionsShouldBeDisplayedOnTheListOfPurchases(String setName, String statusDrpDwn) {
        strCompareString(pbAdminYourOrdersPg.getStatusDropDownValueSelected(),statusDrpDwn,"Status Dropdown");
        searchListTrans(payRefList,statusDrpDwn);
    }


    public void searchListTrans(List <String> payRefList,String statusDrpDwn){
        try{
            for (String purTrans:payRefList){
                if(pbAdminYourOrdersPg.searchPaymentTransRef(purTrans)){
                    log.info("Purchase transaction - "+purTrans+ " displayed in the "+ statusDrpDwn+ " list");
                    ExtentCucumberAdapter.getCurrentStep().pass("Purchase transaction - "+purTrans+ " displayed in the "+ statusDrpDwn+ " list");
                }else{
                    log.error("Purchase transaction: "+purTrans+" does not exist on the Order Summary page on "+ statusDrpDwn+ " list");
                    ExtentCucumberAdapter.getCurrentStep().fail("Purchase transaction: "+purTrans+" does not exist on the Order Summary page on "+ statusDrpDwn+ " list");
                }
                logReportStepValidationEnd("Validation for Purchase transaction "+purTrans+" completed");
            }
        }
        catch(Exception e){
            log.error("Failure exception: " + e);
        }
    }

    @But("Validate that {string} transactions should not be displayed on the {string} list of purchases")
    public void validateThatTransactionsShouldNotBeDisplayedOnTheListOfPurchases(String setName, String statusDrpDwn) {
            for (String purTrans:payRefList){
                purTransListNegativeDisplayValidation(purTrans, statusDrpDwn);
            }
            logReportNotification("List of purchases validation completed");

    }

//Report

    @And("Click on {string} sub-tab")
    public void clickOnSubTab(String subMenuTab) {
        clickOnTheMenuTab(subMenuTab);
    }

    @And("Click on the Purchase Order Number from Order Summary Page")
    public void clickOnThePurchaseOrderNumberFromOrderSummaryPage()  {
        pbAdminYourOrdersPg.clickPurchaseOrderRef(paymentRefNo);
        if(pbAdminYourDetailsPg.validateHdr("Order Details")){
            ExtentCucumberAdapter.getCurrentStep().pass("Purchase Order details page is displayed successfully");
        } else {
            ExtentCucumberAdapter.getCurrentStep().fail("Purchase Order details page is not displayed");
        }
    }

    public void compareHMapValue(HashMap<String, String> actMap,HashMap<String,String> expMap, List<String> keyList){
        String value=null;
        for (String keyStr : keyList) {
            if(actMap.containsKey(keyStr) && expMap.containsKey(keyStr)){
                strCompareString(actMap.get(keyStr), expMap.get(keyStr), keyStr);
            } else if (!(actMap.containsKey(keyStr)) && !(expMap.containsKey(keyStr))) {
                FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,keyStr+" field not required to be validated in Order details section");
//                    ExtentCucumberAdapter.getCurrentStep().pass(keyStr+" field not required to be validated in Order details section");
            } else if (!(actMap.containsKey(keyStr))) {
                FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL,keyStr+" field missing in the Actual extracted Order details section");
//                    ExtentCucumberAdapter.getCurrentStep().fail(keyStr+" field missing in the Actual extracted Order details section");
            } else {
                FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL,keyStr+" field missing in the Expected Order details section");
//                    ExtentCucumberAdapter.getCurrentStep().fail(keyStr+" field missing in the Expected Order details section");
            }
        }
    }

    public void transValidationDetails(String purTransfRef) throws InterruptedException {
        pbAdminYourOrdersPg.selectResultsPerPgDropDown("All").clickPurchaseOrderRef(purTransfRef);
        successFailureLog(pbAdminYourDetailsPg.validatePurchaseDetailsHeader("Order Details", purTransfRef),
                "Purchase transaction: "+purTransfRef+" header validated successfully for Order details page",
                "Purchase transaction: "+purTransfRef+" header validation failed for Order details page");
        validateTransDetails(purTransfRef);
    }

    public void validateTransDetails (String purTransfRef) throws InterruptedException {
        HashMap<String,String> actPurMapDetails=pbAdminYourDetailsPg.transDetailsMap();
        if((expPurTransDetailsMasterMap.get(purTransfRef)).equals(actPurMapDetails)){
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,"Expected and Actual Maps are matching. Validation PASSED.");
        }
        else{
            compareHMapValue(actPurMapDetails,expPurTransDetailsMasterMap.get(purTransfRef),pbAdminYourDetailsPg.getExpKeyOrderDetailsLblList());
        }
        mapComparisonLabelsOrderDetails(expPurTransDetailsMasterMap,purTransfRef);
        pbAdminYourDetailsPg.clickBackToOrdersBtn("Back to Orders");
        Thread.sleep(2000);
        strCompareString(pbAdminYourOrdersPg.hdrOrdersTab(),"WebSite Orders", "header");
    }


    @And("Click on the Purchase Order Number from Order Summary Page and Validate the Purchase Order Details for the Payment Reference")
    public void clickOnThePurchaseOrderNumberFromOrderSummaryPageAndValidateThePurchaseOrderDetailsForThePaymentReference() throws InterruptedException {
        transValidationDetails(paymentRefNo);
    }

    @And("Bulk {string} all the purchase transactions using the checkbox on Order Summary Page")
    public void bulkAllThePurchaseTransactionsUsingTheCheckboxOnOrderSummaryPage(String selectCheckbox) {
        try{
            int counter=expPurTransDetailsMasterMap.size();
            String sumAmt=amountSumValueText(expPurTransDetailsMasterMap);
            for (String purTrans:payRefList){
                if(pbAdminYourOrdersPg.searchPaymentTransRef(purTrans)){
                    pbAdminYourOrdersPg.selectProcessCheckbox(selectCheckbox,purTrans);
                }else{
                    log.error("Purchase transaction: "+purTrans+" does not exist on the Order Summary page");
                    ExtentCucumberAdapter.getCurrentStep().fail("Purchase transaction: "+purTrans+" does not exist on the Order Summary page");
                }
            }
            pbAdminYourOrdersPg.clickProcessSelectedBtn();
            String expProcSelLbl= pbAdminYourOrdersPg.getExpSumProcessSelLbl1(selectCheckbox,String.valueOf(counter),sumAmt);
            strCompareString(pbAdminYourOrdersPg.getProcessSelectedLbl(selectCheckbox),expProcSelLbl,"Process Selected Sum Amount Label");
            pbAdminYourOrdersPg.clickOK_ProcessSelectedBtn();
            strCompareString(pbAdminYourOrdersPg.hdrOrdersTab(),"WebSite Orders", "header");
        }
        catch(Exception e){
            log.error("Failure exception: " + e.getMessage());
        }
    }

    @And("Validate search results should be displayed as per the criteria set for From Date - {string}, To Date - {string}")
    public void validateSearchResultsShouldBeDisplayedAsPerTheCriteriaSetForFromDateToDate(String fromDate, String toDate) throws ParseException {
        log.info(extractPurTransAllSummarySearch);
        resultValidation(pbAdminYourOrdersPg.searchResultsDateValidation(extractPurTransAllSummarySearch,fromDate,toDate),"Date");
    }

    @And("Validate search results should be displayed as per the criteria set for Status - {string}")
    public void validateSearchResultsShouldBeDisplayedAsPerTheCriteriaSetForStatus(String status) {
        resultValidation(pbAdminYourOrdersPg.searchResultsStatusValidation(extractPurTransAllSummarySearch,status),"Status");
    }


    public void resultValidation(List<String> validationList, String fieldValidation){
        if(validationList==null || validationList.isEmpty()){
            log.info("Search Results "+fieldValidation+  " Validation PASSED successfully");
            ExtentCucumberAdapter.getCurrentStep().pass("Search Results "+fieldValidation+  "Validation PASSED successfully");
        }else{
            log.error("Search Results "+fieldValidation+" Validation FAILED and the list of the values failed are : "+ validationList);
            ExtentCucumberAdapter.getCurrentStep().pass("Search Results "+fieldValidation+" Validation FAILED and the list of the values failed are : "+ validationList);
        }
    }


    @Then("Search results displayed in the Order Details Page should satisfy the search criteria set")
    public void searchResultsDisplayedInTheOrderDetailsPageShouldSatisfyTheSearchCriteriaSet() {
//        HashMap<String,String> expPurTransDetailsMasterMapTemp=pbAdminYourDetailsPg.expOrderMapTempRef(data,paymentRefNo);
        successFailureLog(pbAdminYourDetailsPg.validatePurchaseDetailsHeader("Order Details", paymentRefNo),
                "Purchase transaction: "+paymentRefNo+" header validated successfully for Order details page",
                "Purchase transaction: "+paymentRefNo+" header validation failed for Order details page");
//        HashMap<String,String> actPurMapDetails=pbAdminYourDetailsPg.transDetailsMap();
//        if(expPurTransDetailsMasterMapTemp.equals(actPurMapDetails)){
//            FrameworkLogger.log(LogType.EXTENTANDCONSOLEPASS,"Expected and Actual Maps are matching. Validation PASSED.");
//        }
//        else{
//            compareHMapValue(actPurMapDetails,expPurTransDetailsMasterMapTemp,pbAdminYourDetailsPg.getExpKeyOrderDetailsLblList());
//        }
    }

    @And("For {string}, In Orders Summary Page, Enter Pay Ref field value-{string} and Click Search button")
    public void forInOrdersSummaryPageEnterPayRefFieldValueAndClickSearchButton(String testCaseName, String paymentRefNoTrans) {

        if(Objects.isNull(data)) {
            data = excelRow.getTestData(FrameworkConstants.getExcelLocationAutomationRegression(), config.getBuyNowSheetName(), testCaseName);
        }
        pbAdminYourOrdersPg.clickNewSearchBtn();
        paymentRefNo=paymentRefNoTrans;
        pbAdminYourOrdersPg.enterPayRefField(paymentRefNoTrans).clickSearchBtn();
        if(pbAdminYourDetailsPg.validateHdr(paymentRefNoTrans)){
            ExtentCucumberAdapter.getCurrentStep().pass("Order Details Page is displayed successfully");
            log.info("Order Details Page is displayed successfully");
        }else{
            ExtentCucumberAdapter.getCurrentStep().fail("Order Details Page is not displayed");
            log.error("Order Details Page is not displayed");
            Assert.fail("Order Details Page is not displayed");
        }
    }

    @And("Update the order status as {string}, Update comments and click Update Order")
    public void updateTheOrderStatusAsUpdateCommentsAndClickUpdateOrder(String statusToChange) throws InterruptedException {
        this.statusToChange=statusToChange;
        commentsUpdate="Updating Status "+ statusToChange;
        pbAdminYourDetailsPg.selectStatus_ChangeStatusDrpDwn(statusToChange)
                .setAdminCommentsTxt(commentsUpdate);
        if(pbAdminYourDetailsPg.verifyChangeStatusDrpDwn(statusToChange)){
            log.info("Change Status drop down value selected as "+ statusToChange);
        }else{
            log.info("Change Status drop down value not selected as "+ statusToChange);
        }
        pbAdminYourDetailsPg.clickUpdateOrdersBtnStr();

        strCompareString(pbAdminYourOrdersPg.hdrOrdersTab(),"WebSite Orders", "header");

    }

    @And("Validate that the transaction {string} should be displayed on the {string} list of purchases")
    public void validateThatTheTransactionShouldBeDisplayedOnTheListOfPurchases(String paymentRefTransNo, String statusListToValidate) {
        strCompareString(pbAdminYourOrdersPg.getStatusDropDownValueSelected(),statusListToValidate,"Status Dropdown");
        paymentTransSearchValidation(paymentRefTransNo);
    }

    public void paymentTransSearchValidation(String paymentRefTransNo){
        try {
            pbAdminYourOrdersPg.selectResultsPerPgDropDown("All");
            successFailureLog(pbAdminYourOrdersPg.searchPaymentTransRef(paymentRefTransNo),
                    "Purchase Payment Reference value is displayed successfully",
                    "Purchase Payment Reference value is not displayed");
        }
        catch(Exception e){
            Assert.fail(e.getMessage());
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL,e.getMessage());
        }
    }


    @And("Purchase Order Details screen should be displayed with the appropriate comments and updated status")
    public void purchaseOrderDetailsScreenShouldBeDisplayedWithTheAppropriateCommentsAndUpdatedStatus() {
        strCompareString(pbAdminYourDetailsPg.getAdminCommentsTxt(),commentsUpdate,"Admin Comments field");
        strCompareString(pbAdminYourDetailsPg.getChangeStatusDrpDwn(), statusToChange, "Change Status field");
    }


    @And("For {string}, Bulk {string} alternate purchase transactions using the checkbox on Order Summary Page")
    public void forBulkAlternatePurchaseTransactionsUsingTheCheckboxOnOrderSummaryPage(String dataSetName, String statusArr) {
        HashMap<String, List<String>> paymentTransSummMap=pbAdminYourOrdersPg.extractAllPaymentTransSummary();
        if(statusArr.equalsIgnoreCase("Approve and Park")){
        for(int i=0;i<payRefList.size();i++)  {
            if(i%2==0){
               pbAdminYourOrdersPg.selectProcessCheckbox("Approve",payRefList.get(i));
                paymentTransSummMapTransformedApp.put(payRefList.get(i),paymentTransSummMap.get(payRefList.get(i)));
            }else{
                pbAdminYourOrdersPg.selectProcessCheckbox("Park",payRefList.get(i));
                paymentTransSummMapTransformedPark.put(payRefList.get(i),paymentTransSummMap.get(payRefList.get(i)));
            }
        }
        }else{
            log.info("Please check the status value passed from the feature file");
        }

        String sumCountTextApp= pbAdminYourOrdersPg.getExpSumProcessSelLbl1("Approve",String.valueOf(paymentTransSummMapTransformedApp.size()),amountSumValueTextAppPark(paymentTransSummMapTransformedApp));
        String sumCountTextPark=pbAdminYourOrdersPg.getExpSumProcessSelLbl1("Park",String.valueOf(paymentTransSummMapTransformedPark.size()),amountSumValueTextAppPark(paymentTransSummMapTransformedPark));

        pbAdminYourOrdersPg.clickProcessSelectedBtn();
        strCompareString(pbAdminYourOrdersPg.getProcessSelectedLbl("Approve"),sumCountTextApp,"Process Selected Sum Amount Label");
        strCompareString(pbAdminYourOrdersPg.getProcessSelectedLbl("Park"),sumCountTextPark,"Process Selected Park Amount Label");

        pbAdminYourOrdersPg.clickOK_ProcessSelectedBtn();
        strCompareString(pbAdminYourOrdersPg.hdrOrdersTab(),"WebSite Orders", "header");
    }
    private String amountSumValueTextAppPark (HashMap<String,List<String>> purAppParkMap){
        List<Double> amount=new ArrayList<>();
        String sumAmt=null;
        purAppParkMap.values().forEach(s->amount.add(valueConv(s.get(6).replaceAll("€",""))));
        sumAmt=String.format("%,.2f",amount.stream().mapToDouble(Double::doubleValue).sum());
        return sumAmt;
    }

    private String amountSumValueText(HashMap<String,HashMap<String,String>> purAppParkMapTemp){
        List<Double> amountList=new ArrayList<>();
        purAppParkMapTemp.values().forEach(s->amountList.add(Double.parseDouble(s.get("Amount :"))));
        return String.format("%,.2f",amountList.stream().mapToDouble(Double::doubleValue).sum());
    }
    
    public double valueConv(String valueStr)  {
        Number numValue = null;
        try{
            NumberFormat numF=DecimalFormat.getNumberInstance();
            numValue=numF.parse(valueStr);
        }
        catch(Exception e){
log.error(e.getMessage());
        }
        return numValue.doubleValue(); 
    }


    @And("Validate that {string} transactions should be displayed on the appropriate search results list of purchases")
    public void validateThatTransactionsShouldBeDisplayedOnTheAppropriateSearchResultsListOfPurchases(String dataSetName) throws InterruptedException {
        pbAdminYourOrdersPg.clickNewSearchBtn();
        pbAdminYourOrdersPg.selectStatusDropDown("Approved").selectResultsPerPgDropDown("All").clickSearchBtn();
        searchListTrans(new ArrayList<>(paymentTransSummMapTransformedApp.keySet()),"Approved");
        log.info("*********************APPROVED Transaction List validation COMPLETED***************************");

        pbAdminYourOrdersPg.clickNewSearchBtn();
        pbAdminYourOrdersPg.selectStatusDropDown("Parked").selectResultsPerPgDropDown("All").clickSearchBtn();
        searchListTrans(new ArrayList<>(paymentTransSummMapTransformedPark.keySet()),"Parked");
        log.info("*********************PARKED Transaction List validation COMPLETED***************************");

        pbAdminYourOrdersPg.clickNewSearchBtn();
        pbAdminYourOrdersPg.selectStatusDropDown("For Approval").selectResultsPerPgDropDown("All").clickSearchBtn();
        validateThatTransactionsShouldNotBeDisplayedOnTheListOfPurchases(dataSetName,"For Approval");
        log.info("*********************FOR APPROVAL Transaction List validation COMPLETED***************************");
    }

    @Then("Validate that all the {string} specific purchase reference transactions are displayed on the Order Summary Page")
    public void validateThatAllTheSpecificPurchaseReferenceTransactionsAreDisplayedOnTheOrderSummaryPage(String pbAdminUser) throws IOException {
        payRefList=extractDataJsonPayRef();
        log.info(payRefList);
        expPurTransSummaryMap=extractDataJsonMapSummPBSSUser(pbAdminUser);
        log.info(expPurTransSummaryMap);
        validateThatAllThePurchaseReferenceTransactionsAreDisplayedOnTheOrderSummaryPage();
    }

    @And("Click on New Search Button")
    public void clickOnNewSearchButton() {
        try {
            pbAdminYourOrdersPg.clickNewSearchBtn();
        }
        catch(Exception e){
            log.error("Issue in clicking New Search Button");
        }
        }

    public void searchUsingPayRefFieldValue(String payRefTextFieldValue, String testCaseName){
        pbAdminYourOrdersPg.clickNewSearchBtn();
        forInOrdersSummaryPageEnterPayRefFieldValueAndClickSearchButton(testCaseName,payRefTextFieldValue);
    }


    @And("Search the purchase transaction using Payment Reference field value as {string} for {string}")
    public void searchThePurchaseTransactionUsingPaymentReferenceFieldValueAsFor(String payRefTextFieldValue, String testCaseName) {
        paymentRefNo=payRefTextFieldValue;
        if(Objects.isNull(expPurTransDetailsMasterMap)){
            expPurTransDetailsMasterMap=new HashMap<>();
            data=excelRow.getTestData(FrameworkConstants.getExcelLocationAutomationRegression(),config.getBuyNowSheetName(),testCaseName);
            expPurTransDetailsMasterMap.put(paymentRefNo,expOrderMapTemp(data,paymentRefNo));
            System.out.println(expPurTransDetailsMasterMap);
        }
        searchUsingPayRefFieldValue(payRefTextFieldValue,testCaseName);
    }

    @And("Validate the Purchase Order Details for the Payment Reference")
    public void validateThePurchaseOrderDetailsForThePaymentReference() throws InterruptedException {
        validateTransDetails(paymentRefNo);
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private HashMap <String,String> expOrderMapTemp (PurchaseModel data,String paymentRefNo) {
        Date dateCurrent=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String formatDate=sdf.format(dateCurrent);
        HashMap <String,String> tempMap1=new HashMap<>();
        tempMap1.put("Card Holder Name :" ,data.getExpName());
        tempMap1.put("Name :" ,data.getExpPurDet_Name());
        tempMap1.put("Order Date :" ,formatDate);
        tempMap1.put("Last Modified :" ,formatDate);
        tempMap1.put("County :" ,data.getExpPurDet_County());
        tempMap1.put("Card Number :" ,data.getExpCardNo());
        tempMap1.put("AddressLine3 :",data.getExpPurDet_AddressLine3());
        tempMap1.put("Amount :" ,data.getExpValue());
        tempMap1.put("Card type :" ,data.getExpCardType());
        tempMap1.put("AddressLine2 :",data.getExpPurDet_AddressLine2());
        tempMap1.put("AddressLine1 :",data.getExpPurDet_AddressLine1());
        tempMap1.put("Payment Ref :" ,paymentRefNo);
        tempMap1.put("Edit :" ,data.getExpPayDet_Edit());
        tempMap1.put("Contact Number :" ,data.getExpPurDet_ContactNumber());
        tempMap1.put("Email Address :" ,data.getExpPurDet_EmailAddress());
        tempMap1.put("Auth Code :" ,data.getExpAuthCode());
        tempMap1.put("Eircode :" ,data.getExpPurDet_Eircode());
        tempMap1.put("Change Status :" ,data.getExpAdmin_ChangeStatus());
        tempMap1.put("Account No :" ,data.getExpPurDet_AccountNo());

        for(int i=1;i<=data.getExpOrderDetails().size();i++){
            tempMap1.put("Order Details_Item"+i,data.getExpOrderDetails().get(i-1));
        }
        return tempMap1;
    }




    @Then("Search the purchase transaction using Payment Reference field value created")
    public void searchThePurchaseTransactionUsingPaymentReferenceFieldValueCreated() {
        System.out.println(testCaseName);
        System.out.println(paymentRefNo);
        searchUsingPayRefFieldValue(paymentRefNo,testCaseName);
    }

    @And("For {string}, In Orders Summary Page, Enter Pay Ref field value created earlier and Click Search button")
    public void forInOrdersSummaryPageEnterPayRefFieldValueCreatedEarlierAndClickSearchButton(String testCaseName) {
        if(Objects.isNull(data)) {
            data = excelRow.getTestData(FrameworkConstants.getExcelLocationAutomationRegression(), config.getBuyNowSheetName(), testCaseName);
        }
        pbAdminYourOrdersPg.clickNewSearchBtn();
        pbAdminYourOrdersPg.enterPayRefField(paymentRefNo).clickSearchBtn();
        if(pbAdminYourDetailsPg.validateHdr(paymentRefNo)){
            ExtentCucumberAdapter.getCurrentStep().pass("Order Details Page is displayed successfully");
            log.info("Order Details Page is displayed successfully");
        }else{
            ExtentCucumberAdapter.getCurrentStep().fail("Order Details Page is not displayed");
            log.error("Order Details Page is not displayed");
            Assert.fail("Order Details Page is not displayed");
        }

    }

    @And("Validate search results should be displayed as per the criteria set for Source - {string}")
    public void validateSearchResultsShouldBeDisplayedAsPerTheCriteriaSetForSource(String sourceOption) {
        if (sourceOption.equalsIgnoreCase("Tele Sales (FX)")) {
            sourceStepValidator(pbAdminYourOrdersPg.paymentRefNo_SourceValidator("FX"));
        } else if (sourceOption.equalsIgnoreCase("Mixed Orders (SS)")){
            sourceStepValidator(pbAdminYourOrdersPg.paymentRefNo_SourceValidator("SS"));
    } else if (sourceOption.equalsIgnoreCase("Prize Bonds Only (PB)")) {
            sourceStepValidator(pbAdminYourOrdersPg.paymentRefNo_SourceValidator("PB"));
        } else if (sourceOption.equalsIgnoreCase("All")) {
            log.info("Source Option set is All. No Test Validations done for this Source value. Condition would be default to PASS");
        } else{
            log.error("Please check the source option value passed");
            Assert.fail("Please check the source option value passed");
        }
    }

    public void sourceStepValidator(boolean sourceValidatedInd){
        if(sourceValidatedInd){
            log.info("Source Validation completed and is successfully PASSED");
        }
        else{
            log.error("Source Validation completed and has FAILED. Please refer the actErrorList in Logs");
            Assert.fail("Source Validation completed and has FAILED. Please refer the actErrorList in Logs");
        }
    }


    @And("Click on Search Button")
    public void clickOnSearchButton() {
//            try {
                pbAdminYourOrdersPg.clickSearchBtn();
                defaultDrpDwnOptionValueCheck(status, pbAdminYourOrdersPg.getStatusDropDownValueSelected(),"For Approval", "Status");
                defaultDrpDwnOptionValueCheck(resultsPerPg, pbAdminYourOrdersPg.getResultsPerPageDropDownValueSelected(),"10", "Results Per Pg");
                defaultDrpDwnOptionValueCheck(fromDate.replaceAll("_"," "), pbAdminYourOrdersPg.getCalendarFromFieldValue(),pbAdminYourOrdersPg.expToFromDateConvertor()[0], "From Date");
                defaultDrpDwnOptionValueCheck(toDate.replaceAll("_"," "), pbAdminYourOrdersPg.getCalendarToFieldValue(),pbAdminYourOrdersPg.expToFromDateConvertor()[1], "To Date");
                defaultDrpDwnOptionValueCheck(source, pbAdminYourOrdersPg.getSourceDropDownValueSelected(adminUserType),"All", "Source");
//            }
//            catch(Exception e){
//                log.error("Issue in clicking Search Button");
//            }
    }

    @And("In Orders Summary Page, Assign the Search criteria status-{string}, source -{string}, From Date - {string}, To Date - {string}, Results Per Page - {string}")
    public void inOrdersSummaryPageAssignTheSearchCriteriaStatusSourceFromDateToDateResultsPerPage(String status, String source, String fromDate, String toDate, String resultsPerPg) throws InterruptedException {
        this.status=status;
        this.source=source;
        this.fromDate=fromDate;
        this.toDate=toDate;
        this.resultsPerPg=resultsPerPg;
        pbAdminYourOrdersPg.selectStatusDropDown(status)
                .selectSourceDropDown(source,adminUserType)
                .selectResultsPerPgDropDown("All")
                .selectDateFromCalendar("From",fromDate)
                .selectDateFromCalendar("To",toDate);
        Thread.sleep(3000);

        pbAdminYourOrdersPg.clickSearchBtn();
        Thread.sleep(3000);
        sizePurList=pbAdminYourOrdersPg.getPaymentRefNoIds().size();

        pbAdminYourOrdersPg.clickNewSearchBtn();
        pbAdminYourOrdersPg.selectResultsPerPgDropDown(resultsPerPg);
        Thread.sleep(3000);
    }

    @Given("User Submits purchase transactions from from the State Savings portal for Set-{string}")
    public void userSubmitsPurchaseTransactionsFromFromTheStateSavingsPortalForSet(String dataSetName) {
         String setDetails = "SS_QA_2.09;SS_QA_2.10;SS_QA_2.13;SS_QA_2.14";
//        Approved
//        String purTransListref="PB2025426722;PB2025426720;PB2025426719";
//        Parked
//        String purTransListref="PB2025426704;PB2025426703;PB2025426702";
//        For Approval

        String purTransListref="PB20251001422;PB20251001419;PB20251001416;PB20251001415";
        List<String>purList=Arrays.asList(purTransListref.split(";"));
        List<String> testCaseNameList1 = Arrays.asList(setDetails.split(";"));
        expPurTransSummaryMap=new HashMap<>();
        expPurTransDetailsMasterMap=new HashMap<>();
        payRefList=new ArrayList<>();
        for (int i=0;i<purList.size();i++) {
            data = excelRow.getTestData(FrameworkConstants.getExcelLocationAutomationRegression(), config.getBuyNowSheetName(),testCaseNameList1.get(i) );
            expPurTransSummaryMap.put(purList.get(i), getExpList11(data, purList.get(i)));
            expPurTransDetailsMasterMap.put(purList.get(i), expOrderMapTemp11(data, purList.get(i)));
            payRefList.add(purList.get(i));
        }
//        FrameworkLogger.log(LogType.EXTENTANDINFOPASS,getMethodExec2()+"PURLIST >>>>>>>"+purList);
        System.out.println("PURLIST >>>>>>>"+purList);
        System.out.println("PURLIST PAYREF >>>>>>>"+payRefList);
        System.out.println("MAPSUMMARYEXP >>>>>>>"+expPurTransSummaryMap);
        System.out.println("MAPMASTEREXP >>>>>>>"+expPurTransDetailsMasterMap);
    }


    public List<String> getExpList11(PurchaseModel data, String payRefNo) {
        List<String> expTempList=new ArrayList<>();
        Date dateCurrent=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String formatDate=sdf.format(dateCurrent);
        expTempList.add(formatDate);
        expTempList.add(data.getExpCardType());
        expTempList.add(data.getExpCardNo());
        expTempList.add(data.getExpName());
        expTempList.add(data.getExpAuthCode());
        expTempList.add(payRefNo);
        expTempList.add("€"+data.getExpValue());
//            add the total amount method in the data ref sheet and also in the model
        expTempList.add("");
        expTempList.add("");
        return expTempList;
    }


    private HashMap<String,String> expOrderMapTemp11 (PurchaseModel data, String paymentRefNo) {
        Date dateCurrent=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String formatDate=sdf.format(dateCurrent);
        HashMap <String,String> tempMap1=new HashMap<>();
        tempMap1.put("Card Holder Name :" ,data.getExpName());
        tempMap1.put("Name :" ,data.getExpPurDet_Name());
        tempMap1.put("Order Date :" ,formatDate);
        tempMap1.put("Last Modified :" ,formatDate);
        tempMap1.put("County :" ,data.getExpPurDet_County());
        tempMap1.put("Card Number :" ,data.getExpCardNo());
        tempMap1.put("AddressLine3 :",data.getExpPurDet_AddressLine3());
        tempMap1.put("Amount :" ,data.getExpValue());
        tempMap1.put("Card type :" ,data.getExpCardType());
        tempMap1.put("AddressLine2 :",data.getExpPurDet_AddressLine2());
        tempMap1.put("AddressLine1 :",data.getExpPurDet_AddressLine1());
        tempMap1.put("Payment Ref :" ,paymentRefNo);
        tempMap1.put("Edit :" ,data.getExpPayDet_Edit());
        tempMap1.put("Contact Number :" ,data.getExpPurDet_ContactNumber());
        tempMap1.put("Email Address :" ,data.getExpPurDet_EmailAddress());
        tempMap1.put("Auth Code :" ,data.getExpAuthCode());
        tempMap1.put("Eircode :" ,data.getExpPurDet_Eircode());
        tempMap1.put("Change Status :" ,data.getExpAdmin_ChangeStatus());
        tempMap1.put("Account No :" ,data.getExpPurDet_AccountNo());

        for(int i=1;i<=data.getExpOrderDetails().size();i++){
            tempMap1.put("Order Details_Item"+i,data.getExpOrderDetails().get(i-1));
        }

        return tempMap1;
    }


    @Then("Validate the Page Body Structure of the Orders page")
    public void validateThePageBodyStructureOfTheOrdersPage() {
        strCompareString(pbAdminYourOrdersPg.hdrOrdersTab(),"WebSite Orders","Header");
        strCompareString(pbAdminYourOrdersPg.getFromDateLbl(),"From:","From Date label:");
        strCompareString(pbAdminYourOrdersPg.getToDateLbl(),"To:","To Date label:");
        strCompareString(pbAdminYourOrdersPg.getStatusLbl(),"Status:","Status label:");
        strCompareString(pbAdminYourOrdersPg.getPayRefLbl(),"Pay Ref:","Pay Ref label:");
        strCompareString(pbAdminYourOrdersPg.getResPerPgLbl(),"Results Per Page :","Results per page label");
        strCompareString(pbAdminYourOrdersPg.getProcessSelectedBtnLbl(), "Process Selected", "Process Selected Button Label");
        try{
            if(adminUserType.equalsIgnoreCase("PB Admin User")){
                strCompareString(pbAdminYourOrdersPg.getSourceLbl(), "Source:", "Source Label");
            }else{
                log.info("Source Field is not expected for SS User");
            }

            getListItemsComparison(expDrpDwnValuesSource,pbAdminYourOrdersPg.getSourceDrpDwnValues(),"Source Drop down values");
        }
        catch(Exception TimeoutException){
            if(adminUserType.equalsIgnoreCase("SS Admin User")){
                log.info("Validation Successfull as Source Field is not expected");
            }else{
                log.error("Validation Failed as Source Field is expected but not displayed");
            }
        }
        getListItemsComparison(expDrpDwnValuesResPerPg,pbAdminYourOrdersPg.getResultsPerPgDrpDwnValues(),"Results Per Page Drop down values");
        getListItemsComparison(expDrpDwnValuesStatus,pbAdminYourOrdersPg.getStatusDrpDwnValues(),"Status Drop down values");
        getListItemsComparison(expOrdersPgTableHdrValues,pbAdminYourOrdersPg.getColumnHeaderValues(),"Table Column Header values");
    }


    List<String> expDrpDwnValuesStatus=Arrays.asList(new String[]{"All", "Approved", "Closed", "Declined", "Failed - Error", "Parked", "Pending", "Referred", "Refunded", "For Approval", "Transferred to IPS"});
    List<String> expDrpDwnValuesResPerPg=Arrays.asList(new String[]{"10", "25", "50", "100", "All"});
    List<String> expDrpDwnValuesSource=Arrays.asList(new String[]{"All", "Tele Sales (FX)", "Prize Bonds Only (PB)", "Mixed Orders (SS)"});
    List<String> expOrdersPgTableHdrValues=Arrays.asList(new String[]{"Date/Time", "Card Type", "Card No", "Name", "Auth Code", "Cart Payment No.", "Value", "Approve", "Park"});
    List<String> expDrpDwnValuesProducts=Arrays.asList(new String[]{"Savings Certificates", "Savings Bonds", "Prize Bonds", "National Solidarity Bonds (4 Year)", "National Solidarity Bonds (10 Year)"});

    @Then("Validate the Page Body Structure of the Reports page")
    public void validateThePageBodyStructureOfTheReportsPage() {
        strCompareString(pbAdminReportsPg.getHdr(),"State Savings Admin - Website Reports Section", "Header");
        strCompareString(pbAdminReportsPg.getOrdersReportLinkName(),"Orders Report", "Orders Report");
    }

    @Then("Validate the Page Body Structure of the Orders Report page")
    public void validateThePageBodyStructureOfTheOrdersReportPage() {
        pbAdminReportsPg.validateOrdersReportLinkRedirection("Reports/OrdersReport.aspx");
        strCompareString(pbAdminReportsPg.getHdr(),"Orders Report", "header");
        strCompareString(pbAdminReportsPg.getRunReportBtnLbl(),"Run Report", "Run Report Button");
        strCompareString(pbAdminReportsPg.getFromDateLbl(),"From:","From Date label:");
        strCompareString(pbAdminReportsPg.getToDateLbl(),"To:","To Date label:");
        strCompareString(pbAdminReportsPg.getStatusLbl(),"Status:","Status label:");

        try{
            if(adminUserType.equalsIgnoreCase("PB Admin User")){
                strCompareString(pbAdminReportsPg.getSourceLbl(), "Source:", "Source Label");
            }else{
                log.info("Source Field is not expected for SS User");
            }
            getListItemsComparison(expDrpDwnValuesSource,pbAdminReportsPg.getSourceDrpDwnValues(),"Source Drop down values");
        }
        catch(Exception TimeoutException){
            if(adminUserType.equalsIgnoreCase("SS Admin User")){
                log.info("Validation Successfull as Source Field is not expected");
            }else{
                log.error("Validation Failed as Source Field is expected but not displayed");
            }
        }

        getListItemsComparison(expDrpDwnValuesStatus,pbAdminReportsPg.getStatusDrpDwnValues(),"Status Drop down values");


        ///////

        try{
            if(adminUserType.equalsIgnoreCase("SS Admin User")){
                strCompareString(pbAdminReportsPg.getSourceLbl(), "Products:", "Products Label");
            }else{
                log.info("Products Field is not expected for PBN User");
            }
            getListItemsComparison(expDrpDwnValuesProducts,pbAdminReportsPg.getProductsDrpDwnValues(),"Products Drop down values");
        }
        catch(Exception TimeoutException){
            if(adminUserType.equalsIgnoreCase("PB Admin User")){
                log.info("Validation Successfull as Products Field is not expected");
            }else{
                log.error("Validation Failed as Products Field is expected but not displayed");
            }
        }


    }

    @Then("Validate the purchase order transaction details from Order Details page")
    public void validateThePurchaseOrderTransactionDetailsFromOrderDetailsPage() throws InterruptedException {
        for(String purTransfRef:payRefList){
            if(!(failureTrans.contains(purTransfRef))){
                transValidationDetails(purTransfRef);
            }
            else{
                log.error("Purchase transaction: "+purTransfRef+" does not exist on the Order Summary page");
                ExtentCucumberAdapter.getCurrentStep().fail("Purchase transaction: "+purTransfRef+" does not exist on the Order Summary page");
            }
        }
    }


    @Then("Validate that the {string} specific purchase reference transaction is displayed on the Order Summary Page")
    public void validateThatTheSpecificPurchaseReferenceTransactionIsDisplayedOnTheOrderSummaryPage(String pbAdminUser) throws IOException {
        payRefList=extractDataJsonPayRef();
        log.info(payRefList);
        expPurTransSummaryMap=extractDataJsonMapSummPBSSUser(pbAdminUser);
        log.info(expPurTransSummaryMap);
        validateThatAllThePurchaseReferenceTransactionsAreDisplayedOnTheOrderSummaryPage();

    }
}
