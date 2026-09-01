package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.Registration;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.PBAdmin.ExpPayRefData;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Scenario;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.BiPredicate;

public abstract class MasterStepDefs {

    static Logger log = LogManager.getLogger(MasterStepDefs.class);

    protected static Scenario currentScenario;
    protected static Properties properties;
    protected APIReusuableLibrary apiDriver = new APIReusuableLibrary();
    Map<String, Object> perfectoCommand = new HashMap<>();
    private static HttpURLConnection httpURLConnect;
    public PropertyConfig config= ConfigFactory.create(PropertyConfig.class);
    Excelutils excelutils = new Excelutils();
    public static Registration REGISTRATION_DATA;
    public static TestData data;
    int responseStatus;

    static String adminUserType=null;
    int responseCode;

    //////////////////////
    static String testCaseName;
    static HashMap<String,List<String>> expPurTransSummaryMap;
    static HashMap<String,HashMap<String,String>> expPurTransDetailsMasterMap;
    static String paymentRefNo;

    static List<String> payRefList;
    static  List<ExpPayRefData> masterPayRefTestCase=new ArrayList<>(); // SSG: add the class reference and uncomment this

    String masterTestCaseName;

    //////////////////////////////////



    // Gets the objects X location in pixels
    private String getLocationX(WebElement me) {
        int x = me.getLocation().x;
        int width = (Integer.parseInt(me.getAttribute("width")) / 2) + x;
        return width + "";
    }

    // Gets the objects X location in pixels
    private String getLocationY(WebElement me) {
        int y = me.getLocation().y;
        int height = (Integer.parseInt(me.getAttribute("height")) / 2) + y;
        return height + "";
    }

    // Parses webelement to retrieve the xpath used for identification
    private String getXpathFromElement(WebElement me) {
        return (me.toString().split("-> xpath: ")[1]).substring(0, (me.toString().split("-> xpath: ")[1]).length() - 1);
    }

    /**
     * Function to check the broken links
     *
     * @param Url
     */
    protected void brokenLinkValidator(String Url) {
        urlLinkStatus(validationOfLinks(Url));
    }

    private String[] validationOfLinks(String urlToValidate) {
        String[] responseArray = new String[3];
        try {
            URL url = new URL(urlToValidate);
            httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            responseStatus = httpURLConnect.getResponseCode();
            responseCode = responseStatus / 100;
        } catch (Exception e) {
        }
        responseArray[0] = urlToValidate;
        responseArray[1] = String.valueOf(responseCode);
        responseArray[2] = String.valueOf(responseStatus);
        return responseArray;
    }

    private void urlLinkStatus(String[] responseArray) {
        try {
            String linkValue = responseArray[0];
            String responseValue = responseArray[1];
            responseCode = Integer.valueOf(responseValue);
            String responseStatus = responseArray[2];
            switch (responseCode) {
                case 2:
                    /*
                     * System.out.println("It's a Non- Broken Link with URL " +
                     * linkValue + " - " + responseCode + " - with Response code " +
                     * HttpURLConnection.HTTP_OK + " OK ");
                     */
                    currentScenario.log(linkValue + ": Response code : " + responseStatus + " - OK" + " & STATUS:PASS");
                    break;
                case 3:
                    currentScenario.log(linkValue + ": Unknown Responce Code" + " & STATUS:FAIL");
                    break;
                case 4:
                    /*
                     * System.out.println("It's a Broken link with URL " + linkValue
                     * + " - " + responseCode + " - Client Error " +
                     * HttpURLConnection.HTTP_CLIENT_TIMEOUT + " Bad Request ");
                     */
                    currentScenario
                            .log(linkValue + ": Response code : " + responseStatus + " - Client error & STATUS:FAIL");
                    break;

                case 5:
                    /*
                     * System.out.println("It's a Broken link with URL " + linkValue
                     * + " - " + responseCode + " - Internal Server Error " +
                     * HttpURLConnection.HTTP_SERVER_ERROR);
                     */
                    currentScenario.log(
                            linkValue + ": Response code : " + responseStatus + " - Internal Server Error & STATUS:FAIL");
                    break;
                default:
                    currentScenario.log(linkValue + ": Unknown Responce Code & STATUS:FAIL");
                    break;
            }

        } catch (Exception e) {

        } finally {
            httpURLConnect.disconnect();

        }
    }


    static List<String> extExpPBAdminSumList;

    // Re-usable methods required for Pre-Login Code - Needs to be reviewed to be optimised

    static HashMap<String,String> extractExcelValue;
    String pageName;

    public void logReportStepValidationStart(String message){
        log.info(String.format("***********************************************************%s***********************************************************", message));
        ExtentCucumberAdapter.addTestStepLog(String.format("***********************************************************<font color='%s'><div>%s</div></font>", "blue", message));
    }

    public void logReportStepValidationEnd(String message){
        log.info(String.format("***********************************************************%s***********************************************************", message));
        ExtentCucumberAdapter.addTestStepLog(String.format("<font color='%s'><div>%s</div></font>***********************************************************", "blue", message));
    }

    public void logReportNotification(String message){
        log.info(String.format("***********************************************************%s***********************************************************", message));
        ExtentCucumberAdapter.addTestStepLog(String.format("**********************<font color='%s'><div>%s</div></font>**********************", "blue", message));
    }

    public void strCompareString(String actualResult, String expectedResult, String sectionReportLog) {
        try {
            BiPredicate<String, String> comparisonMethod=String::equalsIgnoreCase;
            boolean condition = comparisonMethod.test(actualResult, expectedResult);
            String color = condition ? "yellow" : "red";
            String resultText = condition ? "PASS" : "FAIL";
            String message = condition ?String.format("For <font color='%s'><span>%s</span></font> ,The Text: <font color='%s'><span>%s</span></font> : contains the expected text : <font color='%s'><span>%s</span></font>",color, sectionReportLog,color, actualResult, color, resultText):
                    String.format("The Text: <font color='%s'><span>%s</span></font> : does not match the expected text : <font color='%s'><span>%s</span></font> : <font color='%s'><span>%s</span></font>", color, actualResult,color,expectedResult, color, resultText);
            if (condition) {
                ExtentCucumberAdapter.getCurrentStep().pass(message);
                log.info(">>>"+sectionReportLog+">> Actual-" + actualResult + " >> Expected -" + expectedResult);
            } else {
                ExtentCucumberAdapter.getCurrentStep().fail(message);
                log.error(">>>"+sectionReportLog+">> Actual-" + actualResult + " >> Expected -" + expectedResult);
            }
        } catch (Exception ex) {
            ExtentCucumberAdapter.addTestStepLog("Exception Found </b>:Fail " + ex);
            org.testng.Assert.fail("Failed " + ex.getMessage());
        }
    }

    public void strCompareStringNewTab(String actualResult, BiPredicate<String, String> comparisonMethod, String expectedResult, String sectionReportLog) {
        try {
            boolean condition = comparisonMethod.test(actualResult, expectedResult);
            String color = condition ? "yellow" : "red";
            String resultText = condition ? "PASS" : "FAIL";
            String message = condition ?String.format("For <font color='%s'><span>%s</span></font> ,The target attribute: <font color='%s'><span>%s</span></font> : contains the expected value : <font color='%s'><span>%s</span></font>",color, sectionReportLog,color, actualResult, color, resultText):
                    String.format("For <font color='%s'><span>%s</span></font> , The target attribute:<font color='%s'><span>%s</span></font> : does not match the expected value : <font color='%s'><span>%s</span></font> : <font color='%s'><span>%s</span></font>", color, sectionReportLog,color, actualResult,color,expectedResult, color, resultText);
            if (condition) {
                ExtentCucumberAdapter.getCurrentStep().pass(message);
                log.info(">>>"+sectionReportLog+">> Actual-" + actualResult + " >> Expected -" + expectedResult);
            } else {
                ExtentCucumberAdapter.getCurrentStep().fail(message);
                log.error(">>>"+sectionReportLog+">> Actual-" + actualResult + " >> Expected -" + expectedResult);
            }
        } catch (Exception ex) {
            ExtentCucumberAdapter.addTestStepLog("Exception Found </b>:Fail " + ex);
            org.testng.Assert.fail("Failed " + ex.getMessage());
        }
    }

    public void getListItemsComparison(List<String> expList, List<String> actList, String commonReportLog){
        String expValue=null;
        String actValue=null;
        log.info("getListItemsComparison : "+"commonReportLog:"+expList+actList);
        for (int iterator=0;iterator< expList.size();iterator++){
            expValue=expList.get(iterator);
            actValue=actList.get(iterator);
            if(expValue.equalsIgnoreCase("Not Present")){
                expValue="";
                strCompareString(actValue,  expValue, commonReportLog + "List Item" + "[" + iterator + "]");
            }
            else {
                strCompareString(actValue,  expValue, commonReportLog + "List Item" + "[" + iterator + "]");
            }
            expValue=null;
            actValue=null;
        }
    }



    public void btnLinkLblClickAndNewTabValidation(List<String> actValue, BiPredicate<String, String> compareType,List<String> expValue, List<String> messageLog){
        for (int iterator=0;iterator<expValue.size();iterator++){
            strCompareString(actValue.get(iterator),expValue.get(iterator),messageLog.get(iterator));
        }
    }

    public void getListItemsComparisonBtnLink(List<String> expList, List<String> actList, String buttonName, String pageName){
        String expValue=null,actValue=null;
        List <String> commonReportLog=messageLogCreator(buttonName,pageName);
        String tempCommonReportLog=null;
        log.info("getListItemsComparisonBtnLink : "+expList+ " : "+actList+ " : "+commonReportLog);
        for (int iterator=0;iterator< expList.size();iterator++){
            expValue=expList.get(iterator);
            actValue=actList.get(iterator);
            tempCommonReportLog=commonReportLog.get(0).concat(commonReportLog.get(iterator+1));
            if(tempCommonReportLog.contains("New Tab")){
                reporterLogNewTab(expValue, actValue,tempCommonReportLog);
            }
            else{
                reporterLogNonNewTab(expValue,actValue,tempCommonReportLog);
            }
            tempCommonReportLog=null;
            expValue=null;
            actValue=null;
        }
    }

    private List <String> messageLogCreator(String buttonLinkName,String pageName){
        String tempMasterMessageLog=" button ; button Navigation ; button Click Link New Tab ";
        String tempLogArr=pageName.concat(" ").concat(buttonLinkName).concat(" ;").concat(tempMasterMessageLog);
        return expListContent(tempLogArr);
    }

    public List <String>expListContent(String expList){
        log.info("expListContent: "+ expList);
        return Arrays.asList(expList.split(";"));
    }

    public void strStringHashMapCarouselCompare(HashMap<String,HashMap<String,String>> actHashOfHashMap,HashMap<String,HashMap<String,String>> expHashOfHashMap, String tileName, String searchInternalMap, String messageLog){
        strCompareString(actHashOfHashMap.get(tileName).get(searchInternalMap),expHashOfHashMap.get(tileName).get(searchInternalMap),tileName+" "+searchInternalMap+" "+messageLog+" ");
    }

    private void reporterLogNewTab(String expValue,String actValue, String tempCommonReportLog){
        if(expValue.equalsIgnoreCase("Not Present")){
            expValue="";
            if(actValue.equalsIgnoreCase("Not Present")){
                actValue="";
            }
            strCompareStringNewTab(actValue, String::equalsIgnoreCase, expValue, tempCommonReportLog);
        }
        else {
            if(actValue.equalsIgnoreCase("Not Present")){
                actValue="";
            }
            strCompareStringNewTab(actValue, String::equalsIgnoreCase, expValue, tempCommonReportLog);
        }
    }

    private void reporterLogNonNewTab(String expValue,String actValue, String tempCommonReportLog){
        if(expValue.equalsIgnoreCase("Not Present")){
            expValue="";
            strCompareString(actValue, expValue, tempCommonReportLog);
        }
        else {
            strCompareString(actValue, expValue, tempCommonReportLog);
        }
    }

    public void getListItemsComparisonMultipleBtnLink(List<String> expList, List<String> actList, String sectionName,String pageName){
        String expValue=null,actValue=null;
        List <String> commonReportLog=messageLogCreator(sectionName,pageName);
        String tempCommonReportLog=null;
        List <String>expTempListItemBtnLink=new ArrayList<>();
        List <String>actTempListItemBtnLink=new ArrayList<>();
        log.info("getListItemsComparisonMultipleBtnLink : "+expList+" : "+actList+" : "+commonReportLog);
        for (int i=0;i<expList.size();i++) {
            expTempListItemBtnLink = Arrays.asList(expList.get(i).split(";"));
            actTempListItemBtnLink = Arrays.asList(actList.get(i).split(";"));

            for (int iterator = 0; iterator < expTempListItemBtnLink.size(); iterator++) {
                expValue = expTempListItemBtnLink.get(iterator);
                actValue = actTempListItemBtnLink.get(iterator);
                tempCommonReportLog = commonReportLog.get(0).concat(commonReportLog.get(iterator + 1));
                if (tempCommonReportLog.contains("New Tab")) {
                    reporterLogNewTab(expValue, actValue, tempCommonReportLog);
                } else {
                    reporterLogNonNewTab(expValue, actValue, tempCommonReportLog);
                }
                tempCommonReportLog = null;
                expValue = null;
                actValue = null;

            }
        }
    }

    public void logNavigation(boolean validator, String modalPageNameInd,String messageLog){
//        log.info(validator);
        if(validator){
            log.info(messageLog+" "+modalPageNameInd+" is displayed successfully");
            ExtentCucumberAdapter.getCurrentStep().pass(messageLog+" "+modalPageNameInd+" is displayed successfully");
        }
        else{
            ExtentCucumberAdapter.getCurrentStep().fail(messageLog+" "+modalPageNameInd+" is not displayed successfully");
            log.error(messageLog+" "+modalPageNameInd+" is not displayed successfully");
        }
    }

    public void carouselSectionContentValidation(List<String> expListHeaderTileCarousel,List <String> carouselOrder,HashMap<String, HashMap<String,String>> getCarouselTileDetailsMap,HashMap<String, HashMap<String,String>> expCarouselTileDetailsMap,String pageName){
        ExtentCucumberAdapter.addTestStepLog("Carousel Tile Order validation initiated");
        log.info("Carousel Tile Order validation initiated");
        getListItemsComparison(expListHeaderTileCarousel,carouselOrder,"Carousel Tile ");
        ExtentCucumberAdapter.addTestStepLog("Carousel Tile Order validation completed");
        log.info("Carousel Tile Order validation completed");
        for(String tileHeaderName:expListHeaderTileCarousel){
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap, expCarouselTileDetailsMap,tileHeaderName,"Tile Year Issue","");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Tile Header","");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Tile Bullet points","");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Buy now button","");
            strStringHashMapCarouselCompare(getCarouselTileDetailsMap,expCarouselTileDetailsMap,tileHeaderName,"Learn More button","");
        }
    }

    public void successFailureLog(boolean conditionInd, String passLog, String failLog){
        if(conditionInd){
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,passLog);
        }
        else{
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,failLog);
        }
    }

    public void successFailureLogBUT(boolean conditionInd, String passLog, String failLog){
        if(conditionInd){
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,failLog);
        }
        else{
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS,passLog);
        }
    }

    public List<List<String>> extractDataJson() throws IOException {
        String filePath=System.getProperty("user.dir") + File.separator + "src\\test\\resources" + File.separator+"Test_Data"+ File.separator+"tempJSONFILE.json";
        File file=new File(filePath);
        FileReader filer=new FileReader(file);
        List<List<String>> dataVarList=new ArrayList<>();
        Gson gson=new Gson();
        ExpPayRefData[] expd1=gson.fromJson(filer, ExpPayRefData[].class);
        for (ExpPayRefData exp: expd1){
            dataVarList.add(exp.getExpListValuesSumm());
        }
        filer.close();
        return dataVarList;
    }

    public HashMap<String,List<String>> extractDataJsonMapSummPBSSUser(String pbSSUserType) throws IOException {
        String filePath=System.getProperty("user.dir") + File.separator + "src\\test\\resources" + File.separator+"Test_Data"+ File.separator+"tempJSONFILE.json";
        File file=new File(filePath);
        FileReader filer=new FileReader(file);
        HashMap<String,List<String>> dataVarList=new HashMap<>();
        Gson gson=new Gson();
        ExpPayRefData[] expd1=gson.fromJson(filer, ExpPayRefData[].class);
        String pbSSUserJsonV=null;
        for (ExpPayRefData exp: expd1){
            pbSSUserJsonV=exp.getPbSSUserInd();
            if(pbSSUserJsonV.equalsIgnoreCase(pbSSUserType)){
                dataVarList.put(exp.getPayRefNumber(),exp.getExpListValuesSumm());
            }else{
                log.info(pbSSUserJsonV + "is not equal to "+ pbSSUserType);
            }
        }
        filer.close();
        return dataVarList;
    }

    public List<String> extractDataJsonTCName() throws IOException {
        String filePath=System.getProperty("user.dir") + File.separator + "src\\test\\resources" + File.separator+"Test_Data"+ File.separator+"tempJSONFILE.json";
        File file=new File(filePath);
        FileReader filer=new FileReader(file);
        List<String> dataVarList=new ArrayList<>();
        Gson gson=new Gson();
        ExpPayRefData[] expd1=gson.fromJson(filer, ExpPayRefData[].class);
        for (ExpPayRefData exp: expd1){
            dataVarList.add(exp.getTestCaseNumber());
        }
        filer.close();
        return dataVarList;
    }

    public List<String> extractDataJsonPayRef() throws IOException {
        String filePath=System.getProperty("user.dir") + File.separator + "src\\test\\resources" + File.separator+"Test_Data"+ File.separator+"tempJSONFILE.json";
        File file=new File(filePath);
        FileReader filer=new FileReader(file);
        List<String> dataVarList=new ArrayList<>();
        Gson gson=new Gson();
        ExpPayRefData[] expd1=gson.fromJson(filer, ExpPayRefData[].class);
        for (ExpPayRefData exp: expd1){
            dataVarList.add(exp.getPayRefNumber());
        }
        filer.close();
        return dataVarList;
    }

    public ExpPayRefData loadJsonMethod(List<String> valueList)  {
        log.info(valueList);
        String expDataSetName=null;
        String expTestCaseNumber=null;
        String expSource=null;
        String expPBSSUserInd=null;
        String expOrderDetails=null ;
        String expChangeStatus=null;
        String expDateTime=null;
        String expCardType=null;
        String expCardNo=null;
        String expName=null;
        String expAuthCode=null;
        String expPayRefNumber=null;
        String expValue=null;
        try {
            expDataSetName = valueList.get(0);
            expTestCaseNumber = valueList.get(1);
            expSource = valueList.get(2);
            expPBSSUserInd = valueList.get(3);
            expOrderDetails = valueList.get(4);
            expChangeStatus = valueList.get(11);
            expDateTime = valueList.get(5);
            expCardType = valueList.get(6);
            expCardNo = valueList.get(7);
            expName = valueList.get(8);
            expAuthCode = valueList.get(9);
            expPayRefNumber = valueList.get(10);
            expValue = valueList.get(12);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }

        return new ExpPayRefData(
                expDataSetName,
                expTestCaseNumber,
                expSource,
                expPBSSUserInd,
                expOrderDetails,
                expChangeStatus,
                expDateTime,
                expCardType,
                expCardNo,
                expName,
                expAuthCode,
                expPayRefNumber,
                expValue
        );
    }

    public void printJson(List <ExpPayRefData> arrPayRefData) throws IOException {
        String filePath=System.getProperty("user.dir") + File.separator + "src\\test\\resources" + File.separator+"Test_Data"+ File.separator+"tempJSONFILE.json";
        File file=new File(filePath);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWr=new FileWriter(file);
        gson.toJson(arrPayRefData, fileWr);
        fileWr.close();
    }




}