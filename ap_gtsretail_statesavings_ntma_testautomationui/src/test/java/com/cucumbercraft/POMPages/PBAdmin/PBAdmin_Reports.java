package com.cucumbercraft.POMPages.PBAdmin;

import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class PBAdmin_Reports extends Kentico13_MasterPages {
    WebDriver driver;

    public PBAdmin_Reports(WebDriver driver) {
        this.driver = driver;
    }

    private final By hdr = By.tagName("h1");

    public String getHdr() {
        return getTextByLocator(hdr);
    }

    private final By ordersReportLink = By.xpath("//a[@title='Orders Report']");

    private final By sourceDrpDwn = By.id("ctl00_PageBodyPlaceholder_cntrlOrderSource_ddlOrderSource");

    public String getOrdersReportLinkName() {
        return getTextByLocator(ordersReportLink);
    }

    private final By prodDrpDwn = By.id("ctl00_PageBodyPlaceholder_cntrlProductsList_ddlProducts");
    private final By statusDrpDwn = By.id("ctl00_PageBodyPlaceholder_cntrlOrderStatus_ddlOrderStatus");
    private final By runReportBtn = By.id("ctl00_PageBodyPlaceholder_btnRunReport");
    private final By downloadDrpDwn = By.id("ctl00_PageBodyPlaceholder_ReportViewer1_ctl05_ctl04_ctl00_ButtonLink");
    private final By dwnldOptValue = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_ReportViewer1_ctl05_ctl04_ctl00_Menu']/div/a");
    private final By sourceLbl = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_sourcePanel']");
    private final By fromDateLbl = By.xpath("//span[@id='ctl00_PageBodyPlaceholder_lblDateFrom']");
    private final By toDateLbl = By.xpath("//span[@id='ctl00_PageBodyPlaceholder_lblDateTo']");
    private final By statusLbl = By.xpath("//span[@id='ctl00_PageBodyPlaceholder_lblDateTo']/../following-sibling::td");
    private final By productsLbl = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_productsPanel']");

    public String getSourceLbl() {
        return getTextByLocator(sourceLbl).split(":")[0].concat(":");
    }

    public List<String> getSourceDrpDwnValues() {
        return getDropDownValues(sourceDrpDwn);
    }
    public String getProductsLbl() {
        return getTextByLocator(productsLbl).split(":")[0].concat(":");
    }
    public List<String> getProductsDrpDwnValues() {
        return getDropDownValues(prodDrpDwn);
    }

    public String getStatusLbl() {
        return getTextByLocator(statusLbl);
    }

    public List<String> getStatusDrpDwnValues() {
        return getDropDownValues(statusDrpDwn);
    }

    public String getFromDateLbl() {
        return getTextByLocator(fromDateLbl);
    }

    public String getToDateLbl() {
        return getTextByLocator(toDateLbl);
    }

    public boolean validateOrdersReportLinkRedirection(String expUrlRedirect) {
        return driver.getCurrentUrl().contains(expUrlRedirect);
    }

    public void selectDwnldOptValue(String downloadOptValue) {
        String tempStr = null;
        List<WebElement> tempListDrpDwnOpt = driver.findElements(dwnldOptValue);
        for (WebElement elemVar : tempListDrpDwnOpt) {
            tempStr = elemVar.getText();
            if (tempStr.equalsIgnoreCase(downloadOptValue)) {
                webUtil.clickLog(elemVar, downloadOptValue);
            }
        }
    }

    private void selectValueOptions(String valueToSelect, By locator) {
        webUtil.waitUntilElementVisible(locator, 4);
        webUtil.selectListItem(locator, valueToSelect);
    }

    public void selectProductsDropDownValue(String valueToSelect) {
        selectValueOptions(valueToSelect, prodDrpDwn);
    }

    public void selectSourceDropDownValue(String valueToSelect) {
        selectValueOptions(valueToSelect, sourceDrpDwn);
    }

    public void selectStatusDropDownValue(String valueToSelect) {
        selectValueOptions(valueToSelect, statusDrpDwn);
    }

    public void clickRunReportBtn() {
        webUtil.clickLog(runReportBtn, "Run Report");
    }

    public String getRunReportBtnLbl() {
        return getTextByLocator(runReportBtn);
    }

    public void clickDownloadFileBtn() {
        webUtil.clickLog(downloadDrpDwn, "Download Drop Down");
    }


    private final By tableReports = By.xpath("//div[@id='VisibleReportContentctl00_PageBodyPlaceholder_ReportViewer1_ctl09']");

    public boolean isReportDisplayed() throws InterruptedException {
        return webUtil.isElementDisplayed(tableReports, 19);
    }

    private final By ordersReportMaster = By.id("ctl00_PageBodyPlaceholder_ReportViewer1_fixedTable");

    public By locatorV(int i, int j) {
        return By.xpath("//*[text()='Cart Payment No']/../../../tr[" + i + "]/td[" + j + "]/div");
    }

    public By locatorVRep(int i) {
//    return By.xpath("//td[contains(@id,'ReportCell')]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr["+i+"]/td["+j+"]/div");
////tbody/tr/td/table/tbody/tr[1]/td/div
        return By.xpath("//tbody/tr/td/table/tbody/tr[" + i + "]/td/div");
    }


    /////////////////////////////////////ReportLoad and Capture Data Methods/////////////////////////

    public HashMap<String, List<String>> actExpPurTransMap2() throws ParseException {
        String data2 = null;
        String tempV = null;
        List<String> dataStrList = new ArrayList<>();
        HashMap<String, List<String>> mapStr = new HashMap<>();
        int sizeouter = driver.findElements(By.xpath("//*[text()='Cart Payment No']/../../../tr")).size();
        By locatorV = By.xpath("//*[text()='Cart Payment No']/../../../tr");
        List<WebElement> listElem = driver.findElements(locatorV);
        int loopIden = 0;
        for (int i = 3; i < sizeouter; i++) {
            loopIden = 2;
            for (int j = 2; j < 12; j++) {
                loopIden++;
                try {
                    tempV = driver.findElement(locatorV(i, j)).getText();
                } catch (Exception NoSuchElementException) {
                    tempV = "";
                }
                System.out.println(tempV);


                if (j == 2) {
                    String tempVFormat = dateConvertorReport(tempV);
                    dataStrList.add(tempVFormat);
                } else if (j == 4) {
                    String numStr = amountConvertorReport(tempV);
                    dataStrList.add("€".concat(numStr));
                } else {
                    dataStrList.add(tempV);
                }

                if (loopIden == 12) {
                    List<String> dataStrList2 = new ArrayList<>();
                    dataStrList2.addAll(dataStrList);
                    mapStr.put(dataStrList.get(8), dataStrList2);
                    dataStrList.clear();
                    break;
                }
            }
        }
        System.out.println(dataStrList);
        return mapStr;
    }

    private String dateConvertorReport(String dataTemp) throws ParseException {
        SimpleDateFormat sdfReformat = new SimpleDateFormat("dd MMM yyyy");
        return sdfReformat.format(sdfReformat.parse(dataTemp));
    }

    private String amountConvertorReport(String amountTemp) {
        Double convertNum = Double.parseDouble(amountTemp.replaceAll("€", ""));
        String numStr = String.format("%,.2f", convertNum);
        return numStr;
    }


    /////////////////////////////////////Report Download Methods/////////////////////////
    public boolean isFileDownloaded(String testCaseName) throws IOException {
        LocalDate dateToday = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_YYYY");
        String dateFormatToday = dateToday.format(formatter);
        String expFileName = "State Savings Orders - ".concat(dateFormatToday).concat(".xls");
        List<String> filenames = listFilesUsingFilesList(Util.getTargetPath());
        if (filenames.contains(expFileName)) {
            formatFileNameTC(testCaseName, expFileName);
            return true;
        } else {
            return false;
        }

    }

    private void formatFileNameTC(String testCaseName, String expOldFileName) {
        File oldFileNameTemp = new File(Util.getTargetPath() + File.separator + expOldFileName);
        File newFile = new File(Util.getTargetPath() + File.separator + testCaseName + "_" + expOldFileName);
        if (oldFileNameTemp.renameTo(newFile)) {
            System.out.println("fileRenamed");
        } else {
            System.out.println("NO");
        }
    }

    public List<String> listFilesUsingFilesList(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}