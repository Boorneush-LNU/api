package com.cucumbercraft.POMPages;

import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.framework.*;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.*;

public class PerformanceTestRunner {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final Properties properties;
    public static PropertyConfig config = ConfigFactory.create(PropertyConfig.class);


    private final By emailSubjectRow = By.xpath("//td[normalize-space(.)='Confirm your Ireland State Savings Online email address']");
    private final By tokenLink = By.xpath("//kbd");
    private final By frameSwitch = By.xpath("//iframe[@id='html_msg_body']");
    private final By openEmail = By.xpath("//table[@class='table-striped jambo_table']//tr[1]/td[4][normalize-space()='just now']");


    public PerformanceTestRunner(WebDriver driver, WebDriverUtil webUtil, Properties properties) {
        this.driver = driver;
        this.webUtil = webUtil;
        this.properties = properties;
    }

    private String extractTokenForSingleEmail(String emailID) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        webUtil.waitFor(3000);
        System.out.println("Checking inbox for user: " + emailID.replaceAll("@mailinator.com", ""));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String verToken = null;
        ArrayList<String> tabs = null;
        String originalWindow = driver.getWindowHandle();

        try {

            String mailinatorUrlTemplate = properties.getProperty("mailinator");
            String inboxName = emailID.split("@")[0];

            String mailinatorUrl = mailinatorUrlTemplate.replace("EMAIL_PLACEHOLDER", inboxName);

            js.executeScript("window.open()");
            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.get(mailinatorUrl);


            wait.until(ExpectedConditions.visibilityOfElementLocated(emailSubjectRow));

            webUtil.click(emailSubjectRow);

            // 4. Switch to Frame and Extract Token
            WebElement web = wait.until(ExpectedConditions.presenceOfElementLocated(frameSwitch));
            driver.switchTo().frame(web);

            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            WebElement tokenElement = wait.until(ExpectedConditions.presenceOfElementLocated(tokenLink));
            verToken = tokenElement.getText().trim();

        } catch (Exception e) {
            System.out.println("Error for " + emailID + ": " + e.getMessage());
            verToken = "Fails: " + e.getMessage();
        } finally {

            try {
                driver.switchTo().defaultContent();
                    driver.close();
                    driver.switchTo().window(originalWindow);

            } catch (Exception e) {
                System.err.println(" failed for " + emailID + ". Error: " + e.getMessage());
                try {
                    driver.switchTo().window(originalWindow);
                } catch (Exception ignore) { }
            }
        }
        return verToken;
    }

    public Map<String, String> captureAllEmailTokens(List<String> emailIDs) {
        Map<String, String> emailTokenMap = new HashMap<>();
        for (String email : emailIDs) {
            String token = extractTokenForSingleEmail(email);
            emailTokenMap.put(email, token);
        }
        emailTokenMap.forEach((key, val) -> System.out.println("Email: " + key + " | Link: " + val));
        return emailTokenMap;
    }

    public Map<String, String> extractLinksForPerformanceUsers() {
        List<String> emailsToProcess = Arrays.asList(
//                "Perf-Test-User-9953@mailinator.com",
                "AMLOnlineUser7Changed-CDEN@mailinator.com",
                "Perf-Test-User-9953@mailinator.com"

        );
        Map<String, String> extractedLinks;
        try {
            extractedLinks = captureAllEmailTokens(emailsToProcess);
            if (extractedLinks.size() != emailsToProcess.size()) {
                System.out.println(" Expected " + emailsToProcess.size() + " links, found " + extractedLinks.size());
            }
            System.out.println("Successfully extracted for " + extractedLinks.size() + " emails.");
            return extractedLinks;
        } catch (Exception e) {
            throw new ExceptionUtils("Failed to get link: " + e.getMessage());
        }
    }


    private void writeResultsToCsv(Map<String, String> results, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Email,MailLink");
            for (Map.Entry<String, String> entry : results.entrySet()) {
                String token = entry.getValue().contains(",") ? "\"" + entry.getValue() + "\"" : entry.getValue();
                writer.println(entry.getKey() + "," + token);
            }
            System.out.println("Created " + results.size() + " save to " + fileName);
        } catch (IOException e) {
            System.out.println("Csv not created: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebDriver driver = null;
        try {

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
           driver = new ChromeDriver(options);

            driver.manage().window().maximize();
//            driver.get(config.getMailinatorPath());


            WebDriverUtil webUtil = new WebDriverUtil(driver);
            Properties properties = new Properties();
//            properties.setProperty("mailinator", "https://www.mailinator.com/v4/public/inboxes.jsp?to=NUAT-WF-User45");
            properties.setProperty("mailinator", "https://www.mailinator.com/v4/public/inboxes.jsp?to=EMAIL_PLACEHOLDER");

            PerformanceTestRunner runner = new PerformanceTestRunner(driver, webUtil, properties);

            System.out.println("Extract Link");
            Map<String, String> results = runner.extractLinksForPerformanceUsers();

            System.out.println("Total Links Extracted: " + results.size());
            runner.writeResultsToCsv(results, "CreatePasswordURLs"  + ".csv");


        } catch (Exception e) {
            System.err.println("Fail");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}