package com.cucumbercraft.POMPages.PBAdmin;

import java.util.Arrays;
import java.util.List;

public class ExpPayRefData {
    private String dataSetName;
    private String testCaseNumber;
    private String source;
    private String pbSSUserInd;
    private String orderDetails;
    private String changeStatus;
    private String dateTime;
    private String cardType;
    private String cardNo;
    private String name;
    private String authCode;
    private String payRefNumber;
    private String value;

    public void setPbSSUserInd(String pbSSUserInd) {
        this.pbSSUserInd = pbSSUserInd;
    }

    public String getPbSSUserInd() {
        return pbSSUserInd;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String expOrderDetails) {
        this.orderDetails = expOrderDetails;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String expAdmin_ChangeStatus) {
        this.changeStatus = expAdmin_ChangeStatus;
    }

    public String getPayRefNumber() {
        return payRefNumber;
    }

    public void setPayRefNumber(String payRefNumber) {
        this.payRefNumber = payRefNumber;
    }

    public String getTestCaseNumber() {
        return testCaseNumber;
    }

    public void setTestCaseNumber(String testCaseNumber) {
        this.testCaseNumber = testCaseNumber;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getExpListValues(){
        return Arrays.asList(dataSetName,testCaseNumber,source,payRefNumber,changeStatus,orderDetails);
    }

    public List<String> getExpListValuesSumm(){
        return Arrays.asList(dataSetName,testCaseNumber,source,changeStatus,dateTime,cardType,cardNo,name,authCode,payRefNumber,value);
    }

        public ExpPayRefData(String expDataSetName, String expTestCaseNumber, String expSource, String expPBSSUserInd,String expOrderDetails, String expChangeStatus,
                             String expDateTime,String expCardType,String expCardNo,String expName,String expAuthCode, String expPayRefNumber,String expValue) {
            this.dataSetName = expDataSetName;
            this.testCaseNumber = expTestCaseNumber;
            this.source = expSource;
            this.pbSSUserInd=expPBSSUserInd;
            this.orderDetails = expOrderDetails;
            this.changeStatus = expChangeStatus;
            this.dateTime = expDateTime;
            this.cardType = expCardType;
            this.cardNo = expCardNo;
            this.name = expName;
            this.authCode = expAuthCode;
            this.payRefNumber = expPayRefNumber;
            this.value = expValue;
        }



}
