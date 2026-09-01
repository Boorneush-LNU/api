package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.PerformanceMailUtility;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Properties;

public class PerformanceSteps {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final Properties properties;
    private final PerformanceMailUtility mailUtility;


    public PerformanceSteps(WebDriver driver, WebDriverUtil webUtil, Properties properties) {
        this.driver = driver;
        this.webUtil = webUtil;
        this.properties = properties;
        this.mailUtility = new PerformanceMailUtility(driver, webUtil, properties);
    }

    @When("I capture the password reset links for all performance users")
    public void GetLinks() {

        Map<String, String> extractedLinks = mailUtility.extractLinksForPerformanceUsers();


    }

}
