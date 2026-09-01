package com.cucumbercraft.POMPages.PBAdmin;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
public class PBAdmin_YourDetailsPg extends Kentico13_MasterPages {
    private WebDriver driver;

    public PBAdmin_YourDetailsPg(WebDriver driver) {
        this.driver = driver;
    }

    private final By hdr = By.tagName("h1");
    private final By purchaseDetailsPanel = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_Panel1']");
    private final By purchaseOrdersListPanel = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_pnlOrderList']");
    private final By purchasePaymentDetailsPanel = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_pnlPaymentDetails']");
    private final By purchaseUpdatePanel2 = By.xpath("//div[@id='ctl00_PageBodyPlaceholder_Panel2']");
    private final By sectionTitle = By.tagName("legend");
    private final By rowCountRef = By.tagName("tr");
    private final By rowColRef = By.tagName("td");
    private final By changeStatusLbl = By.id("ctl00_PageBodyPlaceholder_lblChangeStatus");
    private final By changeStatusDrpDwn = By.id("ctl00_PageBodyPlaceholder_cntrlOrderStaus_ddlOrderStatus");
    private final By adminCommentslbl = By.id("ctl00_PageBodyPlaceholder_Label11");
    private final By adminCommentsTxt = By.id("ctl00_PageBodyPlaceholder_txtComments");
    private final By lastModifiedLbl = By.id("ctl00_PageBodyPlaceholder_lblAdminDate");
    private final By lastModifiedValue = By.id("ctl00_PageBodyPlaceholder_lblAdminModifiedDate");
    private final By backToOrdersBtn = By.id("ctl00_PageBodyPlaceholder_btnPrevious");
    private final By updateOrdersBtn = By.xpath("//input[@id='ctl00_PageBodyPlaceholder_btnUpdateOrder']");

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Section Titles
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String getSectionTitle(By masterElem) {
        webUtil.waitUntilElementVisible(masterElem, 4);
        return driver.findElement(masterElem).findElement(sectionTitle).getText().trim();
    }

    public String getPurchaserDetailsSectionTitle() {
        return getSectionTitle(purchaseDetailsPanel);
    }

    public String getOrderDetailsSectionTitle() {
        return getSectionTitle(purchaseOrdersListPanel);
    }

    public String getPaymentDetailsSectionTitle() {
        return getSectionTitle(purchasePaymentDetailsPanel);
    }

    public String getAdminSectionTitle() {
        return getSectionTitle(purchaseUpdatePanel2);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Page Header
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private String getHdr() {
        webUtil.waitUntilElementVisible(hdr, 4);
        return driver.findElement(hdr).getText();
    }

    public boolean validateHdr(String expHeader) {
        return getHdr().contains(expHeader);
    }

    public boolean validatePurchaseIDHdr(String pmtTransRef) {
        return getHdr().contains(pmtTransRef);
    }

    public boolean validatePurchaseDetailsHeader(String expHeader, String pmtTransferRef){
        return ((validatePurchaseIDHdr(pmtTransferRef)) && (validateHdr(expHeader)));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Purchases Details section
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public HashMap<String, String> getPurchaserDetailsMap() {
        HashMap<String, String> dataMap = new HashMap<>();
        String tempKey = null;
        String tempValue = null;
        List<WebElement> elemRowList = driver.findElements(By.xpath("//div[@id='ctl00_PageBodyPlaceholder_Panel1']//tbody/tr"));
//    List <WebElement> elemRowList=driver.findElements(xpath);
        for (int i = 0; i < elemRowList.size(); i++) {
            List<WebElement> elemColList = elemRowList.get(i).findElements(By.tagName("td"));
            if (i > 0 && i < 4) {
                tempKey = "AddressLine" + i+" :";
            } else {
                tempKey = elemColList.get(0).getText().trim();
            }
            tempValue = dataFormatter(elemColList);
            dataMap.put(tempKey, tempValue);
        }
//        transDetailsMap.putAll(dataMap);
        return dataMap;
    }

    private String dataFormatter(List<WebElement> elemColList) {
        String tempValue = null;
        if (elemColList.get(1).getText().isEmpty()) {
            tempValue = "Blank";
        } else if (elemColList.get(0).getText().contains("Order Date :")){
            tempValue=elemColList.get(1).getText().trim().split(" ")[0];
        }else {
            tempValue = elemColList.get(1).getText();
        }
        return tempValue;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Payment Details section
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final By checkBoxEdit = By.id("ctl00_PageBodyPlaceholder_chkEditPaymentDetails");

    public boolean editCheckbox() {
        webUtil.waitUntilElementVisible(checkBoxEdit, 4);
        return driver.findElement(checkBoxEdit).isSelected();
    }

    public void clickEditCheckbox() {
        webUtil.waitUntilElementVisible(checkBoxEdit, 4);
        webUtil.clickLog(checkBoxEdit, "Edit Checkbox");
    }

    public HashMap<String, String> getPaymentDetailsMap() {
        HashMap<String, String> dataMap = new HashMap<>();
        String tempKey = null;
        String tempValue = null;
        List<WebElement> elemRowList = driver.findElements(By.xpath("//div[@id='ctl00_PageBodyPlaceholder_pnlPaymentDetails']//tbody/tr"));
//    List <WebElement> elemRowList=driver.findElements(xpath);
        for (int i = 0; i < elemRowList.size(); i++) {
            List<WebElement> elemColList = elemRowList.get(i).findElements(By.tagName("td"));
            tempKey = elemColList.get(0).getText().trim();
            if (tempKey.contains("Edit")) {
                if (driver.findElement(By.id("ctl00_PageBodyPlaceholder_chkEditPaymentDetails")).isEnabled()) {
                    tempValue = "Enabled";
                } else {
                    tempValue = "Disabled";
                }
            } else {
                tempValue = dataFormatter(elemColList);
            }
            dataMap.put(tempKey, tempValue);
        }
//        transDetailsMap.putAll(dataMap);
        return dataMap;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Purchase Order Details section
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public HashMap<String, String> getPurchaseOrderDetailsMap() {
        HashMap<String, String> dataMap = new HashMap<>();
        String tempKey = null;
        String tempValue=null;
        List<WebElement> elemRowList = driver.findElements(By.xpath("//div[@id='ctl00_PageBodyPlaceholder_pnlOrderList']//tbody/tr"));
        for (int i = 0; i < elemRowList.size(); i++) {
            List<WebElement> elemColList = elemRowList.get(i).findElements(By.tagName("td"));
            tempKey = "Order Details_Item"+elemColList.get(0).getText().trim();
            tempValue=String.join(";",dataUpload(elemColList));
            dataMap.put(tempKey, tempValue);
            tempKey = null;
        }
//        transDetailsMap.putAll(dataMap);
        return dataMap;
    }

    public List<String> dataUpload(List<WebElement> elemColList) {
        List<String> tempList = new ArrayList<>();
        for (int j = 0; j < elemColList.size(); j++) {
            if (!(j == 1)) {
                if (!(j == 5)) {
                    tempList.add(elemColList.get(j).getText().trim());
                } else {
                    try {
                        if (elemColList.get(j).findElement(By.tagName("img")).getDomAttribute("src").length() > 1) {
                            tempList.add("GIFT");
                        }
                    } catch (Exception e) {
                        tempList.add("No GIFT");
                    }
                }
            }
            else{
                log.info("Individual Purchase Transaction ID not loaded - "+elemColList.get(j).getText().trim());
            }

        }

        return tempList;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Admin Details Section
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getChangeStatusLbl() {
        return getTextByLocator(changeStatusLbl).trim();
    }

    public String getChangeStatusDrpDwn() {
        webUtil.waitUntilElementVisible(changeStatusDrpDwn, 4);
        Select chngStatus = new Select(driver.findElement(changeStatusDrpDwn));
        return chngStatus.getFirstSelectedOption().getText();
    }

    public PBAdmin_YourDetailsPg selectStatus_ChangeStatusDrpDwn(String optionSelect) {
        webUtil.waitUntilElementVisible(changeStatusDrpDwn, 4);
        Select chngStatus = new Select(driver.findElement(changeStatusDrpDwn));
        chngStatus.selectByVisibleText(optionSelect);
        return this;
    }

    public boolean verifyChangeStatusDrpDwn(String optionSelect) {
        webUtil.waitUntilElementVisible(changeStatusDrpDwn, 4);
        return getChangeStatusDrpDwn().equalsIgnoreCase(optionSelect);
    }

    public String getAdminCommentslbl() {
        return getTextByLocator(adminCommentslbl).trim();
    }

    public String getAdminCommentsTxt() {
        return getTextByLocator(adminCommentsTxt);
    }

    public void setAdminCommentsTxt(String commentsValue) {
            webUtil.waitUntilElementVisible(adminCommentsTxt,4);
            webUtil.sendKeys(adminCommentsTxt,commentsValue);
    }

    public String getLastModifiedLbl() {
        return getTextByLocator(lastModifiedLbl).trim();
    }

    public String getLastModifiedValue() {
        return getTextByLocator(lastModifiedValue).trim().split(" ")[0];
    }

    public boolean getBackToOrdersBtnStr(String buttonName) {
        return getTextByLocator(backToOrdersBtn).equalsIgnoreCase(buttonName);
    }

    public boolean getUpdateOrdersBtnStr(String buttonName) {
        return getTextByLocator(updateOrdersBtn).equalsIgnoreCase(buttonName);
    }

    public void clickBackToOrdersBtn(String buttonName) {
        webUtil.clickLog(backToOrdersBtn, buttonName);
    }

    public void clickUpdateOrdersBtnStr() {
        webUtil.waitUntilElementToBeClickable(updateOrdersBtn,4);
        WebElement elemVarSH=driver.findElement(updateOrdersBtn);
        webUtil.click(updateOrdersBtn);
        webUtil.waitFor(2000);
    }

    public HashMap<String, String> getAdminDetailsMap() {
        HashMap<String, String> tempMap = new HashMap<>();
        tempMap.put(getChangeStatusLbl(), getChangeStatusDrpDwn());
        tempMap.put(getLastModifiedLbl(), getLastModifiedValue());
//        transDetailsMap.putAll(tempMap);
        return tempMap;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Master Map including all the details
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public HashMap<String, HashMap<String, String>> masterMap() {
        HashMap<String, HashMap<String, String>> tempMasterMap = new HashMap<>();
        tempMasterMap.put("Purchaser Details", getPurchaserDetailsMap());
        tempMasterMap.put("Purchase Order Details", getPurchaseOrderDetailsMap());
        tempMasterMap.put("Payment Details", getPaymentDetailsMap());
        tempMasterMap.put("Admin Details", getAdminDetailsMap());
        return tempMasterMap;
    }

    public HashMap<String, String> transDetailsMap(){
        HashMap<String, String> tempInterMap=new HashMap<>();
        tempInterMap.putAll(getPurchaserDetailsMap());
        tempInterMap.putAll(getPurchaseOrderDetailsMap());
        tempInterMap.putAll(getPaymentDetailsMap());
        tempInterMap.putAll(getAdminDetailsMap());
        return tempInterMap;
    }
    private final By cardNumber=By.id("ctl00_PageBodyPlaceholder_lblCardNumber");
    private final By cardType=By.id("ctl00_PageBodyPlaceholder_lblCardType");
    private final By cardHolderName=By.id("ctl00_PageBodyPlaceholder_lblCardHolderName");
    private final By authCode=By.id("ctl00_PageBodyPlaceholder_lblAuthCode");
    private final By orderDate=By.id("ctl00_PageBodyPlaceholder_lblOrderDateValue");
    private final By cartPaymentRefNo=By.id("ctl00_PageBodyPlaceholder_lblPaymentRef");
    private final By amountDetails=By.id("ctl00_PageBodyPlaceholder_lblAmount");


    public String getCardNumber() {
        return getTextByLocator(cardNumber);
    }

    public String getCardType() {
        return getTextByLocator(cardType);
    }

    public String getCardHolderName() {
        return getTextByLocator(cardHolderName);
    }

    public String getAuthCode() {
        return getTextByLocator(authCode);
    }

    public String getOrderDate() {
        return getTextByLocator(orderDate);
    }

    public String getCartPaymentRefNo() {
        return getTextByLocator(cartPaymentRefNo);
    }
    public String getAmountDetails() {
        return getTextByLocator(amountDetails);
    }

    public List<String> transDetailsReportMap(){
        List<String> tempList=new ArrayList<>();
        tempList.add(getOrderDate());
        tempList.add(getChangeStatusDrpDwn());
        tempList.add(getCardType());
        tempList.add(getCardNumber());
        tempList.add(getCardHolderName());
        tempList.add(getAuthCode());
        tempList.add(getCartPaymentRefNo());
        return tempList;
    }

    public List<String> transDetailsOrderDetailsMap(){
        List<String> tempListOrder=new ArrayList<>();
        tempListOrder.add(getOrderDate());
        tempListOrder.add(getCardType());
        tempListOrder.add(getCardNumber());
        tempListOrder.add(getCardHolderName());
        tempListOrder.add(getAuthCode());
        tempListOrder.add(getCartPaymentRefNo());
        tempListOrder.add(getAmountDetails());
        tempListOrder.add(getChangeStatusDrpDwn());
        return tempListOrder;
    }

    private By purchaseOrderRef(String pmtRef) {
        return By.linkText(pmtRef);
    }


    public List <String> getPurchaseOrderDetailsList() {
        List< String> dataList = new ArrayList<>();
        String tempValue=null;
        List<WebElement> elemRowList = driver.findElements(By.xpath("//div[@id='ctl00_PageBodyPlaceholder_pnlOrderList']//tbody/tr"));
        for (int i = 0; i < elemRowList.size(); i++) {
            List<WebElement> elemColList = elemRowList.get(i).findElements(By.tagName("td"));
            tempValue=String.join(";",dataUpload(elemColList));
            dataList.add(tempValue);
            tempValue = null;
        }
        return dataList;
    }

    public List <String> getPurchaseOrderDetailsListReport() {
        List< String> dataList = new ArrayList<>();
        String tempValue=null;
        List<WebElement> elemRowList = driver.findElements(By.xpath("//div[@id='ctl00_PageBodyPlaceholder_pnlOrderList']//tbody/tr"));
        for (int i = 0; i < elemRowList.size(); i++) {
            List<WebElement> elemColList = elemRowList.get(i).findElements(By.tagName("td"));
            tempValue=String.join(";",dataUploadReport(elemColList));
            dataList.add(tempValue);
            tempValue = null;
        }
        return dataList;
    }

    public List<String> dataUploadReport(List<WebElement> elemColList) {
        List<String> tempList = new ArrayList<>();
        for (int j = 0; j < elemColList.size(); j++) {

                if (!(j == 5)) {
                    tempList.add(elemColList.get(j).getText().trim());
                } else {
                    try {
                        if (elemColList.get(j).findElement(By.tagName("img")).getDomAttribute("src").length() > 1) {
                            tempList.add("GIFT");
                        }
                    } catch (Exception e) {
                        tempList.add("No GIFT");
                    }
                }

        }

        return tempList;
    }




    public List <String> getExpKeyOrderDetailsLblList() {
        List <String> tempList1= Arrays.asList(
                "Card Holder Name :",
                "Name :",
                "Order Date :",
                "Last Modified :",
                "County :" ,
                "Card Number :" ,
                "AddressLine3 :",
                "Amount :" ,
                "Card type :" ,
                "AddressLine2 :",
                "AddressLine1 :",
                "Payment Ref :" ,
                "Edit :" ,
                "Order Details_Item1",
                "Order Details_Item2",
                "Order Details_Item3",
                "Order Details_Item4",
                "Order Details_Item5",
                "Order Details_Item6",
                "Contact Number :" ,
                "Email Address :" ,
                "Auth Code :" ,
                "Eircode :" ,
                "Change Status :" ,
                "Account No :" );
        return tempList1;
    }


    public HashMap <String,String> expOrderMapTempRef (PurchaseModel data, String paymentRefNo) {
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

}
