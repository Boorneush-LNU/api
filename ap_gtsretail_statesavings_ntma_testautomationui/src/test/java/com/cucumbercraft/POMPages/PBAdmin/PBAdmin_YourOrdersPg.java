package com.cucumbercraft.POMPages.PBAdmin;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;

import lombok.extern.log4j.Log4j2;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class PBAdmin_YourOrdersPg extends Kentico13_MasterPages {
    private WebDriver driver;
    PropertyConfig config= ConfigFactory.create(PropertyConfig.class);
    private PBAdmin_YourDetailsPg pbAdminYourDetailsPg;

    public PBAdmin_YourOrdersPg(WebDriver driver) {
        this.driver = driver;
        pbAdminYourDetailsPg = new PBAdmin_YourDetailsPg(driver);
    }

    private final By signInUserNameTxt = By.xpath("//input[@name='loginfmt']");
    private final By nxtBtn = By.xpath("//input[@id='idSIButton9']");
    private final By pwdTxt = By.xpath("//input[@name='passwd']");
    private final By signInBtn = By.xpath("//input[@id='idSIButton9']");
    private final By yesBtn = By.xpath("//input[@id='idSIButton9']");
    private final By hdrAdminHomePg = By.xpath("//div/h1");
    private final By hdrAdminYourOrders = By.xpath("//div/h1");
    private final By drpDwnResultsPerPg = By.xpath("//select[@id='ctl00_PageBodyPlaceholder_ddlRecsPerPage']");
    private final By searchBtn = By.xpath("//input[@name='ctl00$PageBodyPlaceholder$btnGetOrders']");
    private final By tableAllRes = By.xpath("//table[@id='tabOrders']//tbody");
    private final By tableRow = By.tagName("tr");
    private final By tableColumn = By.tagName("td");
    private final By sourceDrpDwn = By.id("ctl00_PageBodyPlaceholder_cntrlOrderSource_ddlOrderSource");
    private final By statusDrpDwn = By.id("ctl00_PageBodyPlaceholder_cntrlOrderStaus_ddlOrderStatus");
    private final By payRefInput = By.id("ctl00_PageBodyPlaceholder_txtPayRef_txtValue");
    private final By processSelectedBtn = By.id("ctl00_PageBodyPlaceholder_btnProcessOrders");
    private final By sumAmountApprovedProcessSel = By.id("ctl00_PageBodyPlaceholder_approvedTotals");
    private final By sumAmountParkedProcessSel = By.id("ctl00_PageBodyPlaceholder_parkedTotals");
    private final By processSelOkBtn = By.id("ctl00_PageBodyPlaceholder_btnCommitOrders");
    private final By processSelCancelBtn = By.id("ctl00_PageBodyPlaceholder_btnCancel");
    private final By newSearchBtn = By.name("ctl00$PageBodyPlaceholder$btnNewSearch");
    private final By sourceLbl=By.xpath("//div[@id='ctl00_PageBodyPlaceholder_sourcePanel']");
    private final By fromDateLbl=By.xpath("//span[@id='ctl00_PageBodyPlaceholder_lblDateFrom']");
    private final By toDateLbl=By.xpath("//span[@id='ctl00_PageBodyPlaceholder_lblDateTo']");
    private final By statusLbl=By.xpath("//span[@id='ctl00_PageBodyPlaceholder_lblDateTo']/../following-sibling::td");
    private final By payRefLbl=By.xpath("//*[@id='ctl00_PageBodyPlaceholder_txtPayRef_txtValue']/..");
    private final By resPerPgLbl=By.xpath("//*[@id='ctl00_PageBodyPlaceholder_txtPayRef_txtValue']/../following-sibling::td/span");



    private By chkBoxApprove(String payRefValue) {
        String tempVarLoc = "//*[text()='" + payRefValue + "']/../../td/*[contains(@id,'chkApprove')]";
        return By.xpath(tempVarLoc);
    }

    private By chkBoxPark(String payRefValue) {
        String tempVarLoc = "//*[text()='" + payRefValue + "']/../../td/*[contains(@id,'chkPark')]";
        return By.xpath(tempVarLoc);
    }

    private By purchaseOrderRef(String pmtRef) {
        return By.linkText(pmtRef);
    }

    public PBAdmin_YourOrdersPg clickPurchaseOrderRef(String pmtRef) {
        webUtil.clickLog(purchaseOrderRef(pmtRef), pmtRef);
        return this;
    }

    private final By menuItem(String menuName) {
        return By.linkText(menuName);
    }

    public void clickNewSearchBtn() {
        webUtil.waitUntilElementToBeClickable(newSearchBtn, 4);
        webUtil.click(newSearchBtn);
    }

    public String hdrPBAdminHomePg() {
        webUtil.waitUntilElementVisible(hdrAdminHomePg, 4);
        return driver.findElement(hdrAdminHomePg).getText();
    }

    public void loginPBAdminOld(String credInD) {
        if (credInD.equalsIgnoreCase("SS Admin User")) {
            driver.get("http://Shahapurep:GoldenBrick!2345@qaprizebondsadmin");
        } else if (credInD.equalsIgnoreCase("PB Admin User")) {
            driver.get("http://Gulves:GoldenGood2025@qaprizebondsadmin");
        } else {
            log.error("Please check the SS/PB user indicator or check username and password");
        }
    }

    public void clickMenu(String menuName) {
        webUtil.waitUntilElementVisible(menuItem(menuName), 4);
        webUtil.clickLog(menuItem(menuName), menuName);
    }

    public String hdrOrdersTab() {
        webUtil.waitUntilElementVisible(hdrAdminYourOrders, 4);
        return driver.findElement(hdrAdminYourOrders).getText();
    }

    public PBAdmin_YourOrdersPg selectResultsPerPgDropDown(String selectValue) {
        if (!(selectValue.equalsIgnoreCase("Default"))) {
            selectDrpDwn(drpDwnResultsPerPg, selectValue);
        } else {
            log.info("No selection done as value expected is Default");
        }
        return new PBAdmin_YourOrdersPg(driver);
    }

    public PBAdmin_YourOrdersPg selectSourceDropDown(String selectValue, String adminUserType) {
        if (adminUserType.equalsIgnoreCase("PB Admin User")) {
            if (!(selectValue.equalsIgnoreCase("Default"))) {
                selectDrpDwn(sourceDrpDwn, selectValue);
            } else {
                log.info("No selection done as value expected is Default");
            }
        }else{
            log.info("No selection done as adminUserType is SS Admin User");
        }
        return new PBAdmin_YourOrdersPg(driver);
    }

    public PBAdmin_YourOrdersPg selectStatusDropDown(String selectValue) {
        if (!(selectValue.equalsIgnoreCase("Default"))) {
            selectDrpDwn(statusDrpDwn, selectValue);
        } else {
            log.info("No selection done as value expected is Default");
        }
        return new PBAdmin_YourOrdersPg(driver);
    }

    private void selectDrpDwn(By xpathDrpDwn, String selectValue) {
        webUtil.waitUntilElementVisible(xpathDrpDwn, 4);
        webUtil.selectListItem(xpathDrpDwn, selectValue);
    }

    public void clickProcessSelectedBtn() {
        webUtil.waitUntilElementVisible(processSelectedBtn, 4);
        webUtil.clickLog(processSelectedBtn, "Process Selected");
    }

    public void clickOK_ProcessSelectedBtn() {
        webUtil.waitUntilElementVisible(processSelOkBtn, 4);
        webUtil.clickLog(processSelOkBtn, "OK");
    }

    public void clickCancel_ProcessSelectedBtn() {
        webUtil.waitUntilElementVisible(processSelCancelBtn, 4);
        webUtil.clickLog(processSelCancelBtn, "Cancel");
    }

    public String getProcessSelectedLbl(String strApprovePark) {
        String tempLbl = null;
        if (strApprovePark.equalsIgnoreCase("Approve")) {
            tempLbl = getSumAmountApprovedProcessSelectedLbl();
        } else if (strApprovePark.equalsIgnoreCase("Park")) {
            tempLbl = getSumAmountParkedProcessSelectedLbl();
        } else if (strApprovePark.equalsIgnoreCase("Approve and Park")) {
            tempLbl = String.format(getSumAmountApprovedProcessSelectedLbl(), ";", getSumAmountParkedProcessSelectedLbl());
        } else {
            tempLbl = "ERROR";
            log.error("Check the strApprove paramEter referenced");
        }
        tempLbl = tempLbl.replace("€", "");
        return tempLbl;
    }


    private String getSumAmountApprovedProcessSelectedLbl() {
        return getTextByLocator(sumAmountApprovedProcessSel);
    }

    private String getSumAmountParkedProcessSelectedLbl() {
        return getTextByLocator(sumAmountParkedProcessSel);
    }

    public void clickSearchBtn() {
        webUtil.waitUntilElementVisible(searchBtn, 4);
        webUtil.clickLog(searchBtn, "Search");
    }

    public PBAdmin_YourOrdersPg enterPayRefField(String payRefValue) {
        webUtil.waitUntilElementVisible(payRefInput, 4);
        webUtil.sendKeys(payRefInput, payRefValue);
        return this;
    }

    private void clickApprovePurchaseTrn(String paymentTrnsRef) {
        webUtil.waitUntilElementVisible(chkBoxApprove(paymentTrnsRef), 4);
        driver.findElement(chkBoxApprove(paymentTrnsRef)).click();
    }

    private void clickParkPurchaseTrn(String paymentTrnsRef) {
        webUtil.waitUntilElementVisible(chkBoxPark(paymentTrnsRef), 4);
        driver.findElement(chkBoxPark(paymentTrnsRef)).click();
    }

    public void selectProcessCheckbox(String processParameter, String paymentTransRef) {
        if (processParameter.equalsIgnoreCase("Approve")) {
            clickApprovePurchaseTrn(paymentTransRef);
        } else if (processParameter.equalsIgnoreCase("Park")) {
            clickParkPurchaseTrn(paymentTransRef);
        } else {
            log.error("Please check the process Parameter");
        }
    }

    public boolean searchPaymentTransRef(String paymentTrnsRef) {
        System.out.println(paymentTrnsRef);
        webUtil.waitUntilElementVisible(tableAllRes, 4);
        List<WebElement> tableRowsList = driver.findElement(tableAllRes).findElements(tableRow);
        log.info("Table rows size is : " + tableRowsList.size());
        log.info("Payment Transaction Reference: " + paymentTrnsRef);
        log.info("Actual Payment transactions extract List is as follows: " + tableRowsList.stream()
                .map(WebElement::getText).collect(Collectors.toList()));
        return tableRowsList.stream()
                .map(WebElement::getText)
                .anyMatch(s -> s.contains(paymentTrnsRef));
    }

    public List<String> getSpecificPaymentTransSummary(String paymentTrnsRef) {
        List<WebElement> tableColumnList = new ArrayList<>();
        List<String> listColumnPmtRef = null;
        webUtil.waitUntilElementVisible(tableAllRes, 4);
        List<WebElement> tableRowsList = driver.findElement(tableAllRes).findElements(tableRow);
        listColumnPmtRef = new ArrayList<>();
        for (WebElement rowValue : tableRowsList) {
            if (rowValue.getText().contains(paymentTrnsRef)) {
                tableColumnList = rowValue.findElements(tableColumn);
                for (WebElement columnValue : tableColumnList) {
                    listColumnPmtRef.add(columnValue.getText());
                }
            }
        }


        return listColumnPmtRef;
    }


    public List<String> getSpecificPaymentTransSummary1(String paymentTrnsRef) {
        List<WebElement> tableColumnList = new ArrayList<>();
        List<String> listColumnPmtRef = null;
        webUtil.waitUntilElementVisible(tableAllRes, 4);
        List<WebElement> tableRowsList = driver.findElement(tableAllRes).findElements(tableRow);
        listColumnPmtRef = new ArrayList<>();
        for (WebElement rowValue : tableRowsList) {
            if (rowValue.getText().contains(paymentTrnsRef)) {
                tableColumnList = rowValue.findElements(tableColumn);
                for (WebElement columnValue : tableColumnList) {
                    listColumnPmtRef.add(columnValue.getText());
                }
            }
        }

        return listColumnPmtRef;
    }


    public HashMap<String, List<String>> extractAllPaymentTransSummary() {
        List<WebElement> tableColumnList = new ArrayList<>();
        List<String> listColumnPmtRef = null;
        webUtil.waitUntilElementVisible(tableAllRes, 15);
        List<WebElement> tableRowsList = driver.findElement(tableAllRes).findElements(tableRow);
        HashMap<String, List<String>> tempHMap = new HashMap<>();
        listColumnPmtRef = new ArrayList<>();

        for (WebElement rowValue : tableRowsList) {
            tableColumnList = rowValue.findElements(tableColumn);
            listColumnPmtRef = dataVar(tableColumnList);
            tempHMap.put(listColumnPmtRef.get(5), listColumnPmtRef);
            tableColumnList = null;
            listColumnPmtRef = null;
        }
        return tempHMap;
    }


    private List<String> dataVar(List<WebElement> tableColumnList) {
        List<String> listColumnPmtRef = new ArrayList<>();
        for (WebElement columnValue : tableColumnList) {
            listColumnPmtRef.add(columnValue.getText());
        }
        return listColumnPmtRef;
    }

    private List<String> dataVarReport(List<WebElement> tableColumnList) {
        List<String> listColumnPmtRef = new ArrayList<>();
        for (int counter = 1; counter < tableColumnList.size() - 2; counter++) {
            listColumnPmtRef.add(tableColumnList.get(counter).getText());
        }
        return listColumnPmtRef;
    }

    private String getDropDownValueSelected(By locator) {
        webUtil.waitUntilElementVisible(locator, 4);
        Select selectTerm = new Select(driver.findElement(locator));
        return selectTerm.getFirstSelectedOption().getText();
    }

    public String getSourceDropDownValueSelected(String adminUserType) {
        if(adminUserType.equalsIgnoreCase("SS Admin User")){
            log.info("Source field validation not expected for SS Admin User");
        return adminUserType;
            }
        else{
        return getDropDownValueSelected(sourceDrpDwn);
    }
    }

    public String getStatusDropDownValueSelected() {
        return getDropDownValueSelected(statusDrpDwn);
    }

    public String getResultsPerPageDropDownValueSelected() {
        return getDropDownValueSelected(drpDwnResultsPerPg);
    }

    public String getExpSumProcessSelLbl(String selectChkbx, List<String> transRefSet, HashMap<String, String> tempMap) {
        String tempStr = null;
        if (selectChkbx.equalsIgnoreCase("Approve") || selectChkbx.equalsIgnoreCase("Park")) {
            amtCntTransBuilder(selectChkbx, transRefSet, tempMap);
            tempStr = procSelStrBuilder(selectChkbx, String.valueOf(counter), String.valueOf(countApp));
        } else if (selectChkbx.equalsIgnoreCase("Approve and Park")) {
            amtCntTransBuilder("Approve", transRefSet, tempMap);
            tempStr = procSelStrBuilder("Approve", String.valueOf(counter), String.valueOf(countApp));
            amtCntTransBuilder("Park", transRefSet, tempMap);
            tempStr = tempStr.concat(";").concat(procSelStrBuilder("Park", String.valueOf(counter), String.valueOf(countApp)));
        } else {
            tempStr = "ERROR";
            System.out.println("Please check the select checkbox parameter");
        }
        return tempStr;
    }

    public String getExpSumProcessSelLbl1(String selectChkbx, String counter, String smAmt) {
        String tempStr = null;
        if (selectChkbx.equalsIgnoreCase("Approve") || selectChkbx.equalsIgnoreCase("Park")) {
            tempStr = procSelStrBuilder(selectChkbx, counter, smAmt);
        } else {
            tempStr = "ERROR";
            System.out.println("Please check the select checkbox parameter");
        }
        return tempStr;
    }

    private String procSelStrBuilder(String selectChkbx, String countTrans, String amt) {
        log.info(selectChkbx + " - - - - - " + countTrans + " - - - - - " + amt);
        return selectChkbx + " " + countTrans + " record(s) with a value of " + amt;
    }

    double countApp;
    int counter;

    public void amtCntTransBuilder(String selectChkbx, List<String> transRefSet, HashMap<String, String> tempMap) {
        countApp = 0;
        counter = 0;
        for (String purTrans : transRefSet) {
            if (tempMap.containsKey(purTrans)) {
                if (tempMap.get(purTrans).contains(selectChkbx)) {
                    countApp = countApp + Double.parseDouble(tempMap.get(purTrans).split(";")[0]);
                    counter++;
                } else {
                    System.out.println("not approve");
                }
            } else {
                System.out.println("Key not contained" + purTrans);
            }
        }
    }

    private By cartPaymentRefOrders = By.xpath("//table[@id='tabOrders']/tbody/tr/td[6]");

    public HashMap<String, List<String>> getExpPurTransMap() throws InterruptedException, ParseException {
        List<String> tempList = new ArrayList<>();
        List<WebElement> elemList = driver.findElements(cartPaymentRefOrders);
        List<String> purchaseTransList = new ArrayList<>();
        HashMap<String, List<String>> tempMapTransDet = new HashMap<>();
        List<String> tempOrderDetList = new ArrayList<>();
        List<String> strReprtFormModList = new ArrayList<>();

        for (WebElement elemvar : elemList) {
            purchaseTransList.add(elemvar.getText());
        }

        for (String strPur : purchaseTransList) {
            log.info(strPur + " >>>>>>>>>>>>>>>>>>>>Purchase transaction details load initiated>>>>>>>>>>>>>>>>>>>>>>>>>");

            clickPurchaseOrderRef(strPur);
            tempList = pbAdminYourDetailsPg.transDetailsReportMap();
            tempOrderDetList = pbAdminYourDetailsPg.getPurchaseOrderDetailsListReport();
            for (String tempLoad : tempOrderDetList) {
                strReprtFormModList = reportFormLoadList(tempList, tempLoad);
                tempMapTransDet.put(strReprtFormModList.get(8), strReprtFormModList);
            }

            log.info(strPur + " <<<<<<<<<<<<<<<<<<<<Purchase transaction details loaded<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

            pbAdminYourDetailsPg.clickBackToOrdersBtn("Back to Orders");
            Thread.sleep(2000);
            selectResultsPerPgDropDown("All");
            tempList.clear();
        }

        return tempMapTransDet;
    }

    public HashMap<String, List<String>> getOrderDetailsPurTransMap() throws InterruptedException {
        List<WebElement> elemList = driver.findElements(cartPaymentRefOrders);
        List<String> purchaseTransList = new ArrayList<>();
        HashMap<String, List<String>> tempMapTransDet = new HashMap<>();

        for (WebElement elemvar : elemList) {
            purchaseTransList.add(elemvar.getText());
        }

        for (String strPur : purchaseTransList) {
            List<String> tempList1=new ArrayList<>();
            log.info(strPur + " >>>>>>>>>>>>>>>>>>>>Purchase transaction details load initiated>>>>>>>>>>>>>>>>>>>>>>>>>");
            clickPurchaseOrderRef(strPur);
            tempList1 = pbAdminYourDetailsPg.transDetailsOrderDetailsMap();
            tempMapTransDet.put(strPur, tempList1);
            log.info(strPur + " <<<<<<<<<<<<<<<<<<<<Purchase transaction details loaded<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            pbAdminYourDetailsPg.clickBackToOrdersBtn("Back to Orders");
            Thread.sleep(2000);
            selectResultsPerPgDropDown("All");
        }
        log.info(tempMapTransDet);
        return tempMapTransDet;
    }

    public HashMap<String, List<String>> getExpPurTransMapSourceOptionLoad() throws InterruptedException, ParseException {
        List<String> tempList = new ArrayList<>();
        List<WebElement> elemList = driver.findElements(cartPaymentRefOrders);
        List<String> purchaseTransList = new ArrayList<>();
        HashMap<String, List<String>> tempMapTransDet = new HashMap<>();
        List<String> tempOrderDetList = new ArrayList<>();
        List<String> strReprtFormModList = new ArrayList<>();

        for (WebElement elemvar : elemList) {
            purchaseTransList.add(elemvar.getText());
        }

        for (String strPur : purchaseTransList) {
            log.info(strPur + " >>>>>>>>>>>>>>>>>>>>Purchase transaction details load initiated>>>>>>>>>>>>>>>>>>>>>>>>>");

            clickPurchaseOrderRef(strPur);
            tempList = pbAdminYourDetailsPg.transDetailsReportMap();
            tempOrderDetList = pbAdminYourDetailsPg.getPurchaseOrderDetailsListReport();
            for (String tempLoad : tempOrderDetList) {
                strReprtFormModList = reportFormLoadList(tempList, tempLoad);
                tempMapTransDet.put(strReprtFormModList.get(8), strReprtFormModList);
            }

            log.info(strPur + " <<<<<<<<<<<<<<<<<<<<Purchase transaction details loaded<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

            pbAdminYourDetailsPg.clickBackToOrdersBtn("Back to Orders");
            Thread.sleep(2000);
            selectResultsPerPgDropDown("All");
            tempList.clear();
        }

        return tempMapTransDet;
    }


    private List<String> reportFormLoadList(List<String> nonOrderDetList, String orderDetRowStr) throws ParseException {
        log.info(nonOrderDetList);
        log.info(orderDetRowStr);

        List<String> reportFormLoadListTemp = new ArrayList<>();
        String tempArr[] = orderDetRowStr.split(";");

        reportFormLoadListTemp.add(dateTimeLoadConvertor(nonOrderDetList.get(0))); //date/time field
        reportFormLoadListTemp.add(tempArr[3]); //Product field
        reportFormLoadListTemp.add(amountLoadConvertor(tempArr[4])); //Amount field
        reportFormLoadListTemp.add(nonOrderDetList.get(1)); //Status field
        reportFormLoadListTemp.add(nonOrderDetList.get(2)); //CardType field
        reportFormLoadListTemp.add(nonOrderDetList.get(3)); //Card No field
        reportFormLoadListTemp.add(nonOrderDetList.get(4)); //Name field
        reportFormLoadListTemp.add(nonOrderDetList.get(5)); //AuthCode field
        reportFormLoadListTemp.add(tempArr[1]); //RefTransOrderDetails field
        reportFormLoadListTemp.add(nonOrderDetList.get(6)); //PurchaseCartRefID field
        return reportFormLoadListTemp;
    }

    private String amountLoadConvertor(String amountArrTemp){
        String revAmountTemp=null;
        if(amountArrTemp.contains(" ")){
            revAmountTemp=amountArrTemp.replaceAll(" ","").trim();
        }else{
            revAmountTemp=amountArrTemp.trim();
        }
        return revAmountTemp;
    }

    private String dateTimeLoadConvertor(String dateExpT) throws ParseException {
        String dateExp=dateExpT.split(" ")[0];
        SimpleDateFormat sdfOld = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfNew = new SimpleDateFormat("dd MMM yyyy");
        return sdfNew.format(sdfOld.parse(dateExp));
    }

    private final By datePickerFrom = By.xpath("//input[@id='ctl00_PageBodyPlaceholder_cntrlDateFrom_ibCalendarStartDate']");
    private final By datePickerTo = By.xpath("//input[@id='ctl00_PageBodyPlaceholder_cntrlDateTo_ibCalendarStartDate']");
    private final By calendarContainer = By.xpath("//div[@class='ajax__calendar_container']");
    private final By calendarContainerTo = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_cntrlDateTo_ceDrawDate_popupDiv' and @class='ajax__calendar_container']");
    private final By prevButton = By.xpath("//div[@class='ajax__calendar_prev']");
    private final By calendarTitle = By.xpath("//div[@class='ajax__calendar_title']");
    private final By nextButton = By.xpath("//div[@class='ajax__calendar_next']");
    private final By calendarBody = By.xpath("//div[@class='ajax__calendar_body']");
    private final By calendarYears = By.xpath("//div[@class='ajax__calendar_years']");
    private final By calendarMonths = By.xpath("//div[@class='ajax__calendar_months']");
    private final By calendarDays = By.xpath("//div[@class='ajax__calendar_days']");

    private final By calendarFromTextField = By.xpath("//input[@id='ctl00_PageBodyPlaceholder_cntrlDateFrom_txtDate']");
    private final By calendarToTextField = By.xpath("//input[@id='ctl00_PageBodyPlaceholder_cntrlDateTo_txtDate']");
    private final By shadowRefFromToField = By.tagName("div");

    public String getCalendarFromFieldValue() {
        return getTextByLocatorAttribute(calendarFromTextField);
    }

    private WebElement shadowRefElemVar(By locatorV, By shadowRootLoc) {
        WebElement elemVarHost = driver.findElement(locatorV);
        SearchContext shadownElem = elemVarHost.getShadowRoot();
        return shadownElem.findElement(shadowRootLoc);
    }

    private String getTextByLocatorAttribute(By locatorV) {
        return driver.findElement(locatorV).getDomAttribute("value");
    }

    public String getCalendarToFieldValue() {
        return getTextByLocatorAttribute(calendarToTextField);
    }

    public void setCalendarFromField(String fromDate) {
        setTextFields(calendarFromTextField, fromDate);
    }

    public void setCalendarToField(String toDate) {
        setTextFields(calendarToTextField, toDate);
    }

    public void setTextFields(By locatorV, String valueInputField)  {
        try{
            webUtil.waitUntilElementVisible(locatorV, 4);
            webUtil.clear(locatorV);
            webUtil.sendKeys(locatorV, valueInputField);

        }
            catch(Exception e){
            log.info(e);
            }
    }

    private final By selectValue(String value) {
        return By.xpath("//*[@text()='" + value + "'");
    }

    private final By calendarYrValue = By.xpath("//table/tbody/tr/td");
    private final By calendarDayV = By.xpath("//table/tbody/tr/td[not(@class='ajax__calendar_other')]");


    public PBAdmin_YourOrdersPg selectDateFromCalendar(String fromToCalendarInd, String strDateValue) throws InterruptedException {
        if (fromToCalendarInd.equalsIgnoreCase("Default")) {
            log.info("No Date selected as value expected is Default");
        } else {
            selectDateFromCalendarInterim(fromToCalendarInd, strDateValue);
        }
        return new PBAdmin_YourOrdersPg(driver);
    }

    private void selectDateFromCalendarInterim(String fromToCalendarInd, String strDateValue) throws InterruptedException {
        String arrDateValue[] = strDateValue.split("_");
        String date = dateModifier(arrDateValue[0]);
        int year = Integer.parseInt(arrDateValue[2]);
        String month = arrDateValue[1];
        clickCalendarFromTo(fromToCalendarInd);
        clickToRange(fromToCalendarInd);

        WebElement elemContainer = getCalendarContainer(fromToCalendarInd);
        while (!(getCalendarRangeInd(year, elemContainer, fromToCalendarInd))) {
            log.info("Value is false");
        }
        clickCalendarDayMonthYr(elemContainer, String.valueOf(year), "Year");
        clickCalendarDayMonthYr(elemContainer, month, "Month");
        clickCalendarDayMonthYr(elemContainer, date, "Date");

    }


    private final By calendarTitleTo = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_cntrlDateTo_ceDrawDate_title' and @class='ajax__calendar_title']");

    public void clickToRange(String fromToCalendarInd) throws InterruptedException {
        By calendarTitleTemp;
        if (fromToCalendarInd.equalsIgnoreCase("To")) {
            calendarTitleTemp = calendarTitleTo;
        } else {
            calendarTitleTemp = calendarTitle;
        }
        webUtil.waitUntilElementToBeClickable(calendarTitleTemp, 4);
        driver.findElement(calendarTitleTemp).click();
        Thread.sleep(1000);
        driver.findElement(calendarTitleTemp).click();
        Thread.sleep(1000);
    }

    private String dateModifier(String dateInp) {
        String tempDate = null;
        if (Integer.parseInt(dateInp) < 10) {
            tempDate = String.valueOf(dateInp.charAt(1));
        } else {
            tempDate = dateInp;
        }

        return tempDate;
    }

    public String getExpTransformedDate(String dateValue) throws ParseException {
        SimpleDateFormat sdfInt = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy");
        String newCurr = dateValue.replaceAll("_", " ");
        return sdfOut.format(sdfInt.parse(newCurr));
    }

    static final String[] MONTHSArr = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private void clickDatePickerFrom() {
        webUtil.clickLog(datePickerFrom, "From-Calendar icon");
    }

    private void clickDatePickerTo() {
        webUtil.clickLog(datePickerTo, "To-Calendar icon");
    }

    private WebElement getCalendarContainer(String fromToCalendarInd) {
        WebElement masterElemVar = null;
        if (fromToCalendarInd.equalsIgnoreCase("To")) {
            masterElemVar = driver.findElement(calendarContainerTo);
        } else if (fromToCalendarInd.equalsIgnoreCase("From")) {
            masterElemVar = driver.findElement(calendarContainer);
        } else {
            log.error("Please check value of FROMTOIND");
        }
        return masterElemVar;
    }

    private String[] getValueArrMinMax(WebElement elemContainer, String fromToIndicator) {
        if (fromToIndicator.equalsIgnoreCase("To")) {
            return elemContainer.findElement(calendarTitleTo).getText().split("-");
        } else {
            return elemContainer.findElement(calendarTitle).getText().split("-");
        }

    }

    private void clickCalendarDayMonthYr(WebElement elemContainer, String dateMonthYr, String dateMonthYrInd) throws InterruptedException {
        List<WebElement> elemActList = new ArrayList<>();
        if (dateMonthYrInd.equalsIgnoreCase("Date")) {
            elemActList = elemContainer.findElement(calendarDays).findElements(calendarDayV);
        } else if (dateMonthYrInd.equalsIgnoreCase("Month")) {
            elemActList = elemContainer.findElement(calendarMonths).findElements(calendarYrValue);
        } else if (dateMonthYrInd.equalsIgnoreCase("Year")) {
            elemActList = elemContainer.findElement(calendarYears).findElements(calendarYrValue);
        } else {
            log.error("Please check value of the dateMonthInd passed");
            Assert.fail("Please check value of the dateMonthInd passed");
        }

        for (WebElement elemVar : elemActList) {
            String comV = elemVar.getText();
            if (comV.equalsIgnoreCase(dateMonthYr)) {
                elemVar.click();
                Thread.sleep(1000);
                log.info(dateMonthYrInd + " clicked - " + dateMonthYr);
                break;
            }
        }

    }


    private void clickCalendarFromTo(String fromToCalendarInd) {
        if (fromToCalendarInd.equalsIgnoreCase("From")) {
            clickDatePickerFrom();
        } else if (fromToCalendarInd.equalsIgnoreCase("To")) {
            clickDatePickerTo();
        } else {
            Assert.fail("Please check the FROMTOINDICATOR");
        }
    }

    private boolean getCalendarRangeInd(int year, WebElement elemContainer, String fromToInd) throws InterruptedException {
        boolean iterInd = false;
        String[] hdrVArr = getValueArrMinMax(elemContainer, fromToInd);
        int minV = Integer.parseInt(hdrVArr[0]);
        int maxV = Integer.parseInt(hdrVArr[1]);

        if (minV < year && year < maxV) {
            iterInd = true;
        } else if (year < minV) {
            driver.findElement(prevButton).click();
            iterInd = false;
        } else if (year > maxV) {
            driver.findElement(nextButton).click();
            iterInd = false;
        } else {
            System.out.println("Something is wrong in the data supplied to validate");
            Assert.fail("Something is wrong in the data supplied to validate");
        }
        return iterInd;
    }


    public List<String> searchResultsStatusValidation(HashMap<String, List<String>> expExtractMap, String status) {
        List<String> tempList = new ArrayList<>();
        List<String> valuesFailedList = null;
        String statusTemp = null;
        for (String key : expExtractMap.keySet()) {
            tempList = expExtractMap.get(key);
            statusTemp = tempList.get(7);
            if (statusTemp.equalsIgnoreCase(status)) {
                log.info(key + " is having correct value as " + status);
            } else {
                log.info(key + " is having incorrect value as " + statusTemp);
                valuesFailedList.add(key.concat("-").concat(statusTemp));
            }
        }
        return valuesFailedList;
    }

    public List<String> searchResultsDateValidation(HashMap<String, List<String>> expExtractMap, String fromDate, String toDate) throws ParseException {
        List<String> tempList = new ArrayList<>();
        List<String> valuesFailedList = new ArrayList<>();
        String dateTempValue = null;
        boolean dateValidationInterim = false;
        for (String key : expExtractMap.keySet()) {
            tempList = expExtractMap.get(key);
            dateTempValue = tempList.get(0);
            dateValidationInterim = dateValidationTemp(key, dateTempValue, fromDate, toDate);
            if (!dateValidationInterim) {
                valuesFailedList.add(key.concat("-").concat(dateTempValue));
            }
        }
        return valuesFailedList;
    }

    public boolean dateValidationTemp(String purchaseOrderRef, String dateExtracted, String fromDate, String toDate) throws ParseException {
        boolean dateComparisonInd = false;

        SimpleDateFormat sdfFromToSet = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat sdfExtractDate = new SimpleDateFormat("dd/MM/yyyy");

        Date dataCurr = sdfExtractDate.parse(dateExtracted.split(" ")[0]);
        Date dataTo = sdfFromToSet.parse(toDate.replaceAll("_", " "));
        Date dataFrom = sdfFromToSet.parse(fromDate.replaceAll("_", " "));

        if (dataCurr.before(dataFrom)) {
            log.error("FAILED - " + purchaseOrderRef + "-" + dateExtracted + " less than From Date");
            dateComparisonInd = false;
        } else if (dataCurr.after(dataTo)) {
            log.error("FAILED - " + purchaseOrderRef + "-" + dateExtracted + " greater than to Date");
            dateComparisonInd = false;
        } else if (dataCurr.before(dataTo) && dataCurr.after(dataFrom)) {
            log.info("PASSED - " + purchaseOrderRef + "-" + dateExtracted + " is between FROM and TO Dates");
            dateComparisonInd = true;
        } else if (dataCurr.equals(dataFrom)) {
            log.info("PASSED - " + purchaseOrderRef + "-" + dateExtracted + " is equal to FROM Date");
            dateComparisonInd = true;
        } else if (dataCurr.equals(dataTo)) {
            log.info("PASSED - " + purchaseOrderRef + "-" + dateExtracted + " is equal to TO Date");
            dateComparisonInd = true;
        } else {
            Assert.fail("Check date validation code");
            log.error("Check date validation code");
        }
        return dateComparisonInd;
    }

    private final By pagination_PageNextButton = By.xpath("//a[@class='pageButton' and not(@disabled='disabled')]");
    private final By pagination_PageNextButtonCheck = By.xpath("//a[@class='pageButton']");
    private final By paginationButtons = By.xpath("//*[@id='ctl00_PageBodyPlaceholder_dpBottom']/a[@class='pageSpacing']");

    private final By currentPgNo = By.xpath("//span[@class='pageSpacingCurrent']");
    private final By pageNoLbl = By.xpath("//span[@id='ctl00_PageBodyPlaceholder_dpBottom_ctl03_CurrentPageLabel']/..");

    public void clickPaginationNxtBtn() {
        webUtil.waitUntilElementToBeClickable(pagination_PageNextButton, 4);
        webUtil.clickLog(pagination_PageNextButton, "Next button-Pagination");
    }

    public String getPgNoLblSumm() {
        webUtil.waitUntilElementVisible(pageNoLbl, 4);
        return driver.findElement(pageNoLbl).getText();
    }

    public String getCurrentPgNoLbl() {
        webUtil.waitUntilElementVisible(currentPgNo, 4);
        return driver.findElement(currentPgNo).getText();
    }

    public boolean isNxtBtnEnabled_Pagination() {
        try {
            return driver.findElement(pagination_PageNextButton).isEnabled();
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isNxtBtnEnabledCheck_Pagination() {
        return driver.findElement(pagination_PageNextButtonCheck).isEnabled();
    }

    public String [] expToFromDateConvertor() {
        LocalDateTime currentDateObj = LocalDateTime.now().minusDays(1);
        LocalDateTime currDateMinus1MonthObj = currentDateObj.minusMonths(1);
        DateTimeFormatter myDtFormatObt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return new String[]{myDtFormatObt.format(currDateMinus1MonthObj),myDtFormatObt.format(currentDateObj)};
    }

    final By paymentRefNoIds=By.xpath("//a[contains(@id,'paymentRef')]");
    public List<String> getPaymentRefNoIds(){
        return driver.findElements(paymentRefNoIds).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean paymentRefNo_SourceValidator(String validatorSource){
        List<String> actErrorPayRefNoList=new ArrayList<>();
        List<String> actPaymentRefNoIds=getPaymentRefNoIds();
        log.info("Actual Payment Reference No List : "+actPaymentRefNoIds);
        log.info("Actual Payment Reference No List size is : "+ actPaymentRefNoIds.size());
        boolean returnInd=false;
        for(String payRefId:actPaymentRefNoIds){
            if(!((payRefId.substring(0,2)).equalsIgnoreCase(validatorSource))){
                    log.info(payRefId+" value is incorrect in the search results");
                    actErrorPayRefNoList.add(payRefId);
                }
    }
        if(actErrorPayRefNoList.size()>0){
            returnInd=false;
        }
        else{
            returnInd=true;
        }
        return returnInd;
    }

    public String getSourceLbl() {
        return getTextByLocator(sourceLbl).split(":")[0].concat(":");
    }

    public String getFromDateLbl() {
        return getTextByLocator(fromDateLbl);
    }

    public String getToDateLbl() {
        return getTextByLocator(toDateLbl);
    }

    public String getStatusLbl() {
        return getTextByLocator(statusLbl);
    }

    public String getResPerPgLbl() {
        return getTextByLocator(resPerPgLbl);
    }

    public String getPayRefLbl() {
        return getTextByLocator(payRefLbl).trim();
    }

    public String getProcessSelectedBtnLbl() {
        return getTextByLocatorAttribute(processSelectedBtn);
    }


    public List<String> getStatusDrpDwnValues(){
        return getDropDownValues(statusDrpDwn);
    }

    public List<String> getSourceDrpDwnValues(){
        return getDropDownValues(sourceDrpDwn);
    }

    public List<String> getResultsPerPgDrpDwnValues(){
        return getDropDownValues(drpDwnResultsPerPg);
    }

    private final By tableHeaders=By.xpath("//table[@id='tabOrders']/thead/tr/th/a");

    public List<String> getColumnHeaderValues(){
        return driver.findElements(tableHeaders).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }


}