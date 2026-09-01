package com.cucumbercraft.runners;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.framework.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.RestAssured;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Platform;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@CucumberOptions(
        tags = "@PurchaseJourneys and @Regression",
        features = "src/test/resources/features",
        glue = "com.cucumbercraft.stepdefinitions",
        monochrome = true,
        plugin = {
        "pretty:target/cucumber-report/Smoke/pretty.txt",
        "html:target/cucumber-report/Smoke/index.html",
        "json:target/cucumber-report/Smoke/cucumber.json",
        "junit:target/cucumber-report/Smoke/cucumber-junitreport.xml",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
}
)

@Log4j2
public class RunCucumberTests_PurchaseJourneys extends AbstractTestNGCucumberTests {
    static String resultFolder;
    Properties properties = Settings.getInstance();

    public static List<Map<String, String>> statusMap = new ArrayList<>();

    @BeforeSuite
    private void beforeSuite() {
        if (properties.getProperty("ProxyFlag").equalsIgnoreCase("Yes")) {
            int port = Integer.parseInt(properties.getProperty("APIProxyPort"));
            RestAssured.proxy(properties.getProperty("APIProxyHost"), port);
            log.info("Proxy is Enabled");
        } else {
            log.info("Proxy Not Enabled");
        }
        if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
            resultFolder = TimeStamp.getInstance();
        }
    }

    @BeforeClass
    private void beforeClass(ITestContext context) {

        System.out.println(context.getCurrentXmlTest().getParameter("ExecutionMode"));


        String folderName = "target/ExtentReport"; // Change this to your desired folder name
        Path extenPath = Paths.get("").toAbsolutePath().resolve(folderName);
        try {
            Path path = !Files.exists(extenPath) ? Files.createDirectories(extenPath).toAbsolutePath() : extenPath;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @AfterClass
    private void afterClass() {
        Properties properties = Settings.getInstance();
        System.out.println(statusMap);

        statusMap.stream()
                .flatMap(map -> map.entrySet().stream())
                .forEach(entry -> ExtentCucumberAdapter.getCurrentScenario().getExtent().setSystemInfo(entry.getKey(), entry.getValue()));

        for (Map<String, String> map : statusMap) {
//            ExcelReader.statusUpdate("C:\\TuhinRebrandPostLogin\\src\\test\\resources\\Test_Data\\Buy Now Automation.xlsx", map);

        }


    }

    @Parameters({"Environment", "EnableDownloadFlag"})
    @BeforeTest
    private void beforeTest(ITestContext testContext, String Environment, String EnableDownloadFlag) {
        SeleniumTestParameters testParameters = new SeleniumTestParameters();
        try {
            setDefaultTestParameters(testContext, testParameters);

            // Set EnableDownloadFlag
            if (EnableDownloadFlag == null) {
                log.error("EnableDownloadFlag is not mentioned in the TestNG XML file");
                Assert.fail("EnableDownloadFlag is not mentioned in the TestNG XML file");
            } else {
                testParameters.setEnableDownloadInd(
                        EnableDownloadInd.valueOf(EnableDownloadFlag));
                properties.setProperty("EnableDownloadInd", EnableDownloadFlag);
                log.info("Enable Download Ind is set to : " + EnableDownloadFlag);
            }

            /*Fetching and setting up the environment variable at the config file*/
            String environmentValue = Environment;
            if (environmentValue == null) {
                log.error("Environment Name is not mentioned in the TestNG XML file");
                Assert.fail("Environment Name is not mentioned in the TestNG XML file");
            } else {
                testParameters.setEnvironmentName(
                        EnvironmentName.valueOf(environmentValue));
                properties.setProperty("Environment", environmentValue);
                log.info("Environment is set to : " + properties.getProperty("Environment"));
            }

            // setTestParameters only after all values have been set on testParameters
            DriverManager.setTestParameters(testParameters);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @AfterTest
    private void afterTest() throws IOException {
        FrameworkConstants.clearThreadLocalCache();
        DriverManager.setTestParameters(null);
    }

    @AfterSuite()
    private void afterSuite() throws IOException {

        if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
            copyReportsFolder();
        }
    }


    private void copyReportsFolder() {

        File sourceCucumber = new File(Util.getTargetPath());
        File sourceExtent = new File(Util.getTargetExtentReportPath());
        File destination = new File(resultFolder);
        try {
            FileUtils.copyDirectory(sourceCucumber, destination);
            FileUtils.copyDirectory(sourceExtent, destination);
            // try {
            // FileUtils.cleanDirectory(sourceCucumber);
            // } catch (Exception e) {
            //
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TimeStamp.reportPathWithTimeStamp = null;
    }

//    public static void main(String[] args) throws IOException {
//        ExtentReports extent = new ExtentReports();
//        ExtentSparkReporter spark = new ExtentSparkReporter("master-report.html");
//        extent.attachReporter(spark);
//
//        File jsonDir = new File("target"); // Directory containing your JSON files
//        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
//
//        if (jsonFiles != null) {
//            Arrays.stream(jsonFiles).forEach(jsonFile -> {
//                try {
//                    extent.createDomainFromJsonArchive(jsonFile.getPath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        extent.flush();
//    }

    private void setDefaultTestParameters(ITestContext testContext, SeleniumTestParameters testParameters) {

        try {
            String executionMode = testContext.getCurrentXmlTest().getLocalParameters().get("ExecutionMode");
            String toolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");

//            /*Fetching and setting up the environment variable at the config file*/
//            String environmentValue=testContext.getCurrentXmlTest().getLocalParameters().get("EnvironmentName");
//            if (environmentValue == null) {
//                log.error("Environment Name is not mentioned in the TestNG XML file");
//                Assert.fail("Environment Name is not mentioned in the TestNG XML file");
//            } else {
//                testParameters.setEnvironmentName(
//                        EnvironmentName.valueOf(environmentValue));
//                properties.setProperty("Environment",environmentValue);
//                log.info("Environment is set to : " + environmentValue);
//
//
//            }

            ///////////////////////////////////
            isMobileExecution(executionMode, toolName, testParameters);
            isAPIExecution(executionMode, toolName, testParameters);

            switch (executionMode) {

                case "API":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

                    break;

                case "LOCAL":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
                        testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

                    } else {
                        testParameters.setBrowser(
                                Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
                    }

                    break;

                case "GRID":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
                        testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

                    } else {
                        testParameters.setBrowser(
                                Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
                        testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));

                    } else {
                        testParameters.setBrowserVersion(
                                testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
                        testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));

                    } else {
                        testParameters
                                .setBrowserVersion(testContext.getCurrentXmlTest().getLocalParameters().get("Platform"));
                    }

                    break;
                case "MOBILE":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
                        testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
                    } else {
                        String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
                        testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
                        testParameters.setMobileExecutionPlatform(
                                MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
                    } else {
                        String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
                                .get("MobileExecutionPlatform");
                        testParameters
                                .setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
                        testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

                    } else {
                        testParameters
                                .setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
                    }

                    break;

                case "PERFECTO":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
                        testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
                    } else {
                        String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
                        testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
                        testParameters.setMobileExecutionPlatform(
                                MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
                    } else {
                        String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
                                .get("MobileExecutionPlatform");
                        testParameters
                                .setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
                        testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

                    } else {
                        testParameters
                                .setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
                    }

                    // For One Lab Integration - Desktop Browser Execution

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
                        testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

                    } else {
                        testParameters.setBrowser(
                                Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
                        testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));

                    } else {
                        testParameters.setBrowserVersion(
                                testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
                        testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));

                    } else {
                        testParameters.setPlatform(
                                Platform.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("Platform")));
                    }

                    break;

                case "SEETEST":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
                    testParameters.setMobileToolName(ToolName.valueOf("DEFAULT"));

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
                        testParameters.setMobileExecutionPlatform(
                                MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
                    } else {
                        String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
                                .get("MobileExecutionPlatform");
                        testParameters
                                .setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
                        testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

                    } else {
                        testParameters
                                .setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("SeeTestPort") == null) {
                        testParameters.setSeeTestPort(properties.getProperty("SeeTestDefaultPort"));

                    } else {
                        testParameters
                                .setSeeTestPort(testContext.getCurrentXmlTest().getLocalParameters().get("SeeTestPort"));
                    }

                    break;

                case "SAUCELABS":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
                        testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
                    } else {
                        String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
                        testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
                        testParameters.setMobileExecutionPlatform(
                                MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
                    } else {
                        String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
                                .get("MobileExecutionPlatform");
                        testParameters
                                .setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
                        testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

                    } else {
                        testParameters
                                .setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
                        testParameters.setmobileOSVersion(properties.getProperty("DefautMobileOsVersion"));

                    } else {
                        testParameters.setmobileOSVersion(
                                testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion"));
                    }

                    // For Sauce Labs Browser based execution

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
                        testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

                    } else {
                        testParameters.setBrowser(
                                Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
                        testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));

                    } else {
                        testParameters.setBrowserVersion(
                                testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
                        testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));

                    } else {
                        testParameters
                                .setBrowserVersion(testContext.getCurrentXmlTest().getLocalParameters().get("Platform"));
                    }

                    break;

                case "FASTEST":

                    testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
                        testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
                    } else {
                        String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
                        testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
                        testParameters.setMobileExecutionPlatform(
                                MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
                    } else {
                        String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
                                .get("MobileExecutionPlatform");
                        testParameters
                                .setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
                        testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

                    } else {
                        testParameters
                                .setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
                        testParameters.setmobileOSVersion(properties.getProperty("DefautMobileOsVersion"));

                    } else {
                        testParameters.setmobileOSVersion(
                                testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion"));
                    }

                    // For FASTEST Browser based execution

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
                        testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

                    } else {
                        testParameters.setBrowser(
                                Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
                        testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));

                    } else {
                        testParameters.setBrowserVersion(
                                testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
                        testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));

                    } else {
                        testParameters
                                .setBrowserVersion(testContext.getCurrentXmlTest().getLocalParameters().get("Platform"));
                    }

                    break;

                case "TESTOBJECT":

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
                        testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
                    } else {
                        String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
                        testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
                        testParameters.setMobileExecutionPlatform(
                                MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
                    } else {
                        String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
                                .get("MobileExecutionPlatform");
                        testParameters
                                .setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
                        testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

                    } else {
                        testParameters
                                .setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
                    }

                    if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
                        testParameters.setmobileOSVersion(properties.getProperty("DefautMobileOsVersion"));

                    } else {
                        testParameters.setmobileOSVersion(
                                testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion"));
                    }

                    break;

                default:
                    testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
                    if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowerName") == null) {
                        testParameters.setBrowser(
                                Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowerName")));
                    } else {
                        testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
                    }
            }

        } catch (Exception ex) {
            log.error(
                    "Error at settings TestParameters , please check the TestNG XML suite File and pass valid key & values"
                            + ex.getMessage());
        }
    }

    private void isAPIExecution(String executionMode, String toolName, SeleniumTestParameters testParameters) {

        testParameters.setAPIExecution(false);
        if (executionMode.equals(ExecutionMode.API.toString())) {
            testParameters.setAPIExecution(true);
        }
    }

    private void isMobileExecution(String executionMode, String toolName, SeleniumTestParameters testParameters) {

        testParameters.setMobileExecution(false);
        if (!(toolName == null)) {
            if (toolName.equals(ToolName.APPIUM.toString())) {
                testParameters.setMobileExecution(true);
            }
        }
    }


}