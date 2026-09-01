package com.cucumbercraft.POMPages;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Settings;
import com.cucumbercraft.framework.WebDriverUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class PerformanceMailUtility {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final Properties properties;
    Properties prop = Settings.getInstance();

    // --- Locators ---
    private final By mailTime = By.xpath("//table[@class='table-striped jambo_table']//tr[1]/td[4][normalize-space()='just now']");
    private final By mailSubject = By.xpath("//table[@class='table-striped jambo_table']/tbody/tr[1]/td[3]");
    private final By msgBody = By.xpath("//iframe[@id='html_msg_body']");
    private final By tokenLink = By.xpath("//kbd");
    // Placeholder locator for the element on the password reset page
    private final By newPasswordInput = By.id("newPasswordInput");

    public PerformanceMailUtility(WebDriver driver, WebDriverUtil webUtil, Properties properties) {
        this.driver = driver;
        this.webUtil = webUtil;
        this.properties = properties;
    }

    public String extractTokenForSingleEmail(String emailID, String originalWindow) {
        String verToken = "EXTRACTION_FAILED";
        ArrayList<String> tabs = null;

        try {
            // --- 1. Open Mailinator in a new tab ---
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open()");

            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            String mailinatorUrlTemplate = prop.getProperty("mailinator");
            if (mailinatorUrlTemplate == null || !mailinatorUrlTemplate.contains("%s")) {
                throw new IllegalStateException("Mailinator URL property 'mailinator' is not set or missing '%s' placeholder.");
            }

            String inboxName = emailID.replaceAll("@mailinator.com", "");
            String mailinatorUrl = String.format(mailinatorUrlTemplate, inboxName);

            webUtil.waitFor(3000);
            driver.get(mailinatorUrl);

            // --- 2. Click the email and extract token ---
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

            wait.until(ExpectedConditions.visibilityOfElementLocated(mailTime));
            boolean clickFirst = webUtil.getText(mailTime).trim().equalsIgnoreCase("just now");

            if (webUtil.getText(mailSubject).equalsIgnoreCase("Reset your Ireland State Savings Online Password") && clickFirst) {
                webUtil.click(mailSubject);
            } else {
                System.out.println("Email not received or subject mismatch for: " + emailID);
                throw new ExceptionUtils("Email not received or subject mismatch.");
            }

            // Switch to the iFrame
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(msgBody));
            driver.switchTo().frame(element);

            // --- Extract Token/Link ---
            WebElement tokenElement = wait.until(ExpectedConditions.presenceOfElementLocated(tokenLink));
            verToken = tokenElement.getText().trim();

        } catch (Exception e) {
            System.err.println("Error processing email " + emailID + ": " + e.getMessage());
            verToken = "Fails: " + e.getMessage();
        } finally {
            // --- 3. Cleanup: Close the current tab and switch back ---
            try {
                driver.switchTo().defaultContent();
                if (tabs != null && tabs.size() > 1) {
                    driver.close();
                    driver.switchTo().window(originalWindow);
                }
            } catch (Exception cleanupEx) {
                System.err.println("Cleanup failed for " + emailID + ". Error: " + cleanupEx.getMessage());
                try {
                    driver.switchTo().window(originalWindow);
                } catch (Exception fatalEx) {
                    // Cannot return to original window.
                }
            }
        }
        return verToken;
    }


    public Map<String, String> captureAllEmailTokens(List<String> emailIDs) {
        Map<String, String> emailTokenMap = new HashMap<>();
        String originalWindow = driver.getWindowHandle();

        System.out.println("Starting token extraction for " + emailIDs.size() + " emails...");

        for (String email : emailIDs) {
            String token = extractTokenForSingleEmail(email, originalWindow);
            emailTokenMap.put(email, token);
            System.out.println("Processed: " + email + " | Result: " + (token.startsWith("Fails") ? "FAIL" : "SUCCESS"));
        }

        System.out.println("\n--- Final Extraction Results ---");
        emailTokenMap.forEach((key, val) -> System.out.println("Email: " + key + " | Link/Token: " + val));

        return emailTokenMap;
    }


    public Map<String, String> extractLinksForPerformanceUsers() {
        List<String> emailsToProcess = Arrays.asList(
                "perf-test-user-01@mailinator.com",
                "perf-test-user-02@mailinator.com",
                "perf-test-user-03@mailinator.com",
                "Perf-Test-User-9953@mailinator.com"
        );

        try {
            return captureAllEmailTokens(emailsToProcess);
        } catch (Exception e) {
            throw new ExceptionUtils("Failed to capture links for performance test: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    //  NEW METHOD TO PERFORM SUBSEQUENT ACTION
    // ------------------------------------------------------------------
    /**
     * Navigates to the extracted link/token and performs a subsequent action (e.g., password reset).
     * @param email The performance user's email ID.
     * @param tokenOrLink The extracted token or full URL.
     */
    public void useExtractedTokenForNextAction(String email, String tokenOrLink) {
        System.out.println("--> Performing action for: " + email);

        try {
            // Assuming the extracted tokenOrLink is a complete, navigable URL
            driver.get(tokenOrLink);

            // Wait for the next page/element to load
//            webUtil.waitForElementPresent(newPasswordInput, 30);

            // --- INSERT YOUR SPECIFIC ACTIONS HERE ---
            // Example: Set a new password
            // webUtil.type(newPasswordInput, "NewPerfP@ss123");
            // webUtil.type(By.id("confirmPasswordInput"), "NewPerfP@ss123");
            // webUtil.click(By.id("submitResetButton"));

            System.out.println("    Action successful for " + email);

        } catch (Exception e) {
            System.err.println("    Action FAILED for " + email + ". Error: " + e.getMessage());
            // Optionally, take a screenshot here before continuing
        }
    }
    // ------------------------------------------------------------------


    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            // ... (rest of ChromeOptions setup)
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            WebDriverUtil webUtil = new WebDriverUtil(driver);
            Properties properties = new Properties();
            properties.setProperty("mailinator", "https://www.mailinator.com/v4/public/inboxes.jsp?to=NUAT-WF-User45");

            PerformanceMailUtility runner = new PerformanceMailUtility(driver, webUtil, properties);

            System.out.println("--- PHASE 1: Extracting Tokens/Links ---");

            // --- This line executes the full Mailinator extraction process ---
            Map<String, String> results = runner.extractLinksForPerformanceUsers();

            System.out.println("\n--- PHASE 2: Performing Subsequent Actions ---");

            // --- NEW CODE: ITERATE OVER RESULTS AND PERFORM NEXT ACTION ---
            for (Map.Entry<String, String> entry : results.entrySet()) {
                String email = entry.getKey();
                String tokenOrLink = entry.getValue();

                if (tokenOrLink.startsWith("Fails:")) {
                    System.out.println("Skipping action for " + email + " due to extraction failure.");
                    continue;
                }

                // Call the new method to use the extracted link/token
                runner.useExtractedTokenForNextAction(email, tokenOrLink);
            }
            // --------------------------------------------------------------------

        } catch (Exception e) {
            System.err.println("Fail: An exception occurred during execution.");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}