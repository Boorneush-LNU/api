package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.POMPages.PBAdmin.PBAdmin_Home;
import com.cucumbercraft.POMPages.PBAdmin.PBAdmin_Reports;
import com.cucumbercraft.POMPages.PBAdmin.PBAdmin_YourDetailsPg;
import com.cucumbercraft.POMPages.PBAdmin.PBAdmin_YourOrdersPg;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class PBAdminHomeStepDefinitions extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    PBAdmin_Home pbAdminHomePg=new PBAdmin_Home(driver);
    String userEmail=null;

    @Then("Validate the Page Body Structure of the Home page")
    public void validateThePageBodyStructureOfTheHomePage() {
        strCompareString(pbAdminHomePg.getHdrAdminHomePg(),"State Savings Administration Home", "Header Home Page");
        strCompareString(pbAdminHomePg.getSubContent(),"This application is used for the administration of State Savings and Prize Bonds website orders including the authorisation of draw presentations.","Sub Content Home Page");
    }

    @And("Click on {string} link from the footer section")
    public void clickOnLinkFromTheFooterSection(String linkName) throws InterruptedException {
        strCompareString(pbAdminHomePg.getVersionInformationLinkText(),linkName, "Version Information Link Name");
        pbAdminHomePg.clickVersionInformationLnk();
        Thread.sleep(3000);
    }

    @And("Validate the About Page")
    public void validateTheAboutPage() {
        pbAdminHomePg.validateVersionInformationLinkRedirection("About.aspx");
        strCompareString(pbAdminHomePg.getHdrAdminHomePg(),"About State Savings Administration","Header - About Page");
        strCompareString(pbAdminHomePg.getHdr2AboutPg(),"Version Info", "Header 2 - About Page");
    }

//SSG: Re-instae this method after discussing with Shankar as looks like there is duplicate step definition in Admin Portal step definition class which is used for Sanity/health run mostly

//    @Given("Launch the PB Admin portal and login with valid credentials as {string}")
//    public void launchThePBAdminPortalAndLoginWithValidCredentialsAs(String pbAdminUser) throws IOException {
//        this.adminUserType=pbAdminUser;
//        driver.get(config.getPbWebAdminUrl());
//        if (pbAdminUser.equalsIgnoreCase("SS Admin User")) {
//            pbAdminHomePg.loginSSPBAdminUser(config.getSSAdminUser_Email(),config.getSSAdminUser_Pwd());
//            userEmail=config.getSSAdminUser_Email();
//        } else if (pbAdminUser.equalsIgnoreCase("PB Admin User")) {
//            pbAdminHomePg.loginSSPBAdminUser(config.getPBAdminUser_Email(),config.getPBAdminUser_Pwd());
//            userEmail=config.getPBAdminUser_Email();
//        } else {
//            log.error("Please check the SS/PB user indicator or check username and password");
//        }
//        strCompareString(pbAdminHomePg.getHdrAdminHomePg(),"State Savings Administration Home","header");
//        if(!(masterPayRefTestCase.isEmpty())) {
//            printJson(masterPayRefTestCase);
//            log.info(extractDataJson());
//        }
//    }


    @And("Validate the Header and Footer section")
    public void validateTheHeaderAndFooterSection() {
        log.info("Header Content validation initiated");
        strCompareString(pbAdminHomePg.getHdrDateUserInfo(),pbAdminHomePg.getExpDateAndUserInfo(userEmail), "Header User Info and Date");
        log.info("Header Content validation completed");

        log.info("Footer Content validation initiated");
        strCompareString(pbAdminHomePg.getFooterContent1(),"All content © An Post 2025", "Footer Content 1");
        strCompareString(pbAdminHomePg.getFooterContent2(),"An Post is incorporated with limited liability. Registered Office: General Post Office, O’Connell Street Lower, Dublin 1, D01 F5P2.\n" +
                "Registered in Dublin, Ireland. Registered Number: 98788\n" +
                "Version information", "Footer Content 2");
        log.info("Footer Content validation completed");
    }
}
