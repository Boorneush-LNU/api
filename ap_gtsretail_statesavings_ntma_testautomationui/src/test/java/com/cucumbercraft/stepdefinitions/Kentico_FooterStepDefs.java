package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.HomePage;
import com.cucumbercraft.POMPages.Kentico_FooterContentPage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Log4j2
public class Kentico_FooterStepDefs extends MasterStepDefs{
    WebDriver driver = DriverManager.getWebDriver();
    HomePage home=new HomePage(driver);

    Kentico_FooterContentPage footers= new Kentico_FooterContentPage(driver);






    @Given("Select the About Ireland State Savings")
    public void select_the_about_ireland_state_savings() {
        try {
            home.clickFooterLink("About Ireland State Savings");
            ExtentCucumberAdapter.addTestStepLog("Footer links Clicked");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("Footer links Clicked", e);
        }
    }

    @Given("Validate About Ireland State Savings content")
    public void validate_about_ireland_state_savings_content() {
        try {
            footers.AboutUsPage();
            ExtentCucumberAdapter.addTestStepLog("Footer links Clicked");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("Footer links Clicked", e);
        }


    }

    @Given("Select the Site Use Terms and conditons")
    public void select_the_site_use_terms_and_conditons() {
        try {
            home.clickFooterLink("Site Use Terms and Conditions");
            ExtentCucumberAdapter.addTestStepLog("Footer links Clicked");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("Footer links Clicked", e);
        }
    }

    @Given("Validate Site Use Terms and conditons Content")
    public void validate_site_use_terms_and_conditons_content() {
        try {
            footers.SiteUseTermsAndConditionsPage();
            ExtentCucumberAdapter.addTestStepLog("Footer links Clicked");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("Footer links Clicked", e);
        }
    }



    @Given("Select the Terms and conditions")
    public void select_the_terms_and_conditions() {
        try {
            home.clickFooterLink("Terms & Conditions");
            ExtentCucumberAdapter.addTestStepLog("Footer links Clicked");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("Footer links Clicked", e);
        }

    }

    @Given("Validate Terms and conditions Content")
    public void validate_terms_and_conditions_content() {
        try {
            footers.TermsAndConditionsPage();
            ExtentCucumberAdapter.addTestStepLog("Footer links Clicked");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("Footer links Clicked", e);
        }
    }


    @And("Validate Accessibility Contents")
    public void validateAccessibilityContents() {
        try {
            footers.validateAccessibilityLinkContent();
            System.out.println("Accessibility Links validated");
            ExtentCucumberAdapter.addTestStepLog("Accessibility links content Validated");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("validateAccessibilityLinks", e);
        }


    }

    @And("Validate DataProtection Contents")
    public void validateDataProtectionContents() {
        try {
            footers.validateDataProtectionLinkContent();
            System.out.println("Data Protection Links validated");
            ExtentCucumberAdapter.addTestStepLog("Data Protection links content Validated");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("validateDataProtectionLinks", e);
        }

    }

    @And("Validate CookiePolicy Contents")
    public void validateCookiePolicyContents() {
        try {
            footers.validateCookiePolicyLinkContent();
            System.out.println("Cookie Policy Links validated");
            ExtentCucumberAdapter.addTestStepLog("Cookie policy links content Validated");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("validateCookiePolicyLinks", e);

        }
    }

    @And("Validate CookieWeUse Contents")
    public void validateCookieWeUseContents() {
        try {
            footers.validateCookieWeUseLinkContent();
            System.out.println("Cookie we use Links validated");
            ExtentCucumberAdapter.addTestStepLog("Cookie we use links content Validated");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("validateCookieweuseLinks", e);

        }
    }

    @And("Validate PrizeBondCompany Contents")
    public void validatePrizeBondCompanyContents() {
        try{
            footers.validatePrizeBondCompanyLinkContent();
            System.out.println("Prize Bond Company Links validated");
            ExtentCucumberAdapter.addTestStepLog("Prize Bond Company links content Validated");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("validatePrizeBondsLinks", e);

        }
    }

    @And("Validate ContactUs Contents")
    public void validateContactUsContents() {
        try {
            footers.validateContactUsLinkContent();
            System.out.println("Contact Us validated");
            ExtentCucumberAdapter.addTestStepLog("Contact Us content Validated");
        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", currentScenario.getName());
            Assert.fail("validateContactUsLinks", e);

        }
    }
    @And("Click Contact Us Link")
    public  void ClickContactUs(){
        footers.ClickContactus();
    }

}
