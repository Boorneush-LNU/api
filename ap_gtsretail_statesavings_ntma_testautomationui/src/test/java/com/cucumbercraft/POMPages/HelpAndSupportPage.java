package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Settings;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
public class HelpAndSupportPage {

    public final By commonFAQLinks = By.xpath("//*[@class='m14-help_content--listing']/ul/li/a");
    public final By subLink = By.xpath("//article//a");
    public final By pageHdr = By.xpath("//article/../h1");
    public final By pageHdrGS = By.xpath("//h1[@class='m14-help_content--title']");
    public final By bulletPoint1 = By.xpath("//*[@class='m14-help_content--article']/ul[1]");
    private final By btnCategories = By.xpath("//section[@class='m13-3col_category_listing m13-3col_category_listing brand-grey--light']//a");
    private final By btnSearch = By.id("btnSearch");
    private final By txtSearchKeyword = By.id("txtSearchKeyword");
    public By lnkHelpandSupport = By.xpath("//a[@title='Help and Support']");
    public By hdrHelpandSupport = By.xpath("//*[@id='ltrTitle']");
    public By txtSearch = By.id("txtSearchKeyword");
    public By hdflblFaq = By.xpath("//h2[text()='Frequently asked questions']");
    public By hdrlblHelp = By.xpath("//h2[text()='Help Categories']");
    public By hdrlblHelpFormsDownloads = By.xpath("//h2[text()='Forms & Downloads']");
    public By hdrbanner = By.xpath("//*[@class='m9-CTA_banner--title']");
    public By hdrFAQ = By.xpath("//h1[contains(@class,'content--title')]");
    public By txtFAQ = By.xpath("//h2[text()='Frequently asked questions']");
    public By breadCrumb = By.xpath("//section[@class='m-breadcrumb']//ul");
    public String paraString = "//*[@class='m14-help_content--article']/p[%d]";
    public By hdrlinkLbl = By.xpath("//*[@id='ltrCatTitle']");
    public By TabIrish = By.xpath("//*[contains(text(),'As Gaeilge')]");
    public By errpage = By.xpath("//*[text()='denied']");
    public By ErrPage1 = By.xpath("//*[contains(text(),'Tunnel or SSL Forbidden')]");
    Map<String,String > hdrMap=null;
    WebDriver driver;
    WebDriverUtil webUtil;

    public HelpAndSupportPage(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }

    public void udfHelpAndSupportSelection() {
        try {
            webUtil.clickLog(lnkHelpandSupport, "Help and Support");
            ExtentCucumberAdapter.addTestStepLog("Help and Support button clicked");
            log.info("Help and Support button clicked");
            var urlflag = webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("help-support"));
            if (urlflag){
                webUtil.gettextlog(hdrHelpandSupport, String::equals, "Help and Support", "Header");
            }else
                throw new ExceptionUtils("Help and Support Page not displayed");
        } catch (Exception e) {
            Assert.fail("udfHelpAndSupportSelection -" + e.getMessage());
        }
    }

    public void udfValidateHelpandSupportPage() {
        try {
            webUtil.isElementDisplayedLog(txtSearch, 2, "Search by Keyword text box");
            webUtil.gettextlog(hdflblFaq, String::equals, "Frequently asked questions", "Label");
            String[] links = {"What are the rates as of 1st October 2023?", "How do I request a repayment of my State Savings products (including Prize Bonds)?", "When are the Prize Bond draws?", "Can I purchase Prize Bonds as a gift?", "How to purchase online?"};
            for (int i = 1; i <= links.length; i++) {
                String tab = driver.findElement(By.xpath("//div[@class='m04-2col_text--block']/div[1]/ul/li[" + i + "]/a")).getText();
                if (tab.equalsIgnoreCase(links[i - 1])) {
                    ExtentCucumberAdapter.addTestStepLog("Link Element Found--->Actual Element: " + tab + "\n" + "Expected Element: " + links[i - 1]);
                    log.info("Link Element Found--->Actual Element: " + tab + " " + "Expected Element: " + links[i - 1]);
                } else {
                    ExtentCucumberAdapter.addTestStepLog("Link Element NOT Found--->Actual Element: " + tab + "\n" + "Expected Element: " + links[i - 1]);
                    log.info("Link Element NOT Found--->Actual Element: " + tab + " " + "Expected Element: " + links[i - 1]);
                }
            }
            String[] links1 = {"What happens to unclaimed prizes?", "What are my Prize Bond winnings payment options?", "How do Prize Bonds work?", "What is the current value of Prize Bond Fund and how is it calculated?"};
            for (int i = 1; i <= links1.length; i++) {
                String tab = driver.findElement(By.xpath("//div[@class='m04-2col_text--block']/div[2]/ul/li[" + i + "]/a")).getText();
                if (tab.equalsIgnoreCase(links1[i - 1])) {
                    ExtentCucumberAdapter.addTestStepLog("Link Element Found--->Actual Element: " + tab + "\n" + "Expected Element: " + links1[i - 1]);
                    log.info("Link Element Found--->Actual Element: " + tab + " " + "Expected Element: " + links1[i - 1]);
                } else {
                    ExtentCucumberAdapter.addTestStepLog("Link Element NOT Found--->Actual Element: " + tab + "\n" + "Expected Element: " + links1[i - 1]);
                    log.info("Link Element NOT Found--->Actual Element: " + tab + " " + "Expected Element: " + links1[i - 1]);
                }
            }


        } catch (Exception e) {
            Assert.fail("udfValidateHelpandSupportPage -" + e.getMessage());
        }
    }

    public void udfValidateHelpCategories() {
        try {
            webUtil.scrollToView(hdrlblHelp);
         // In Alt Uat "Contact us" Header has been removed. So I removed Contact us alone in thi string
//            String[] links1 = {"Ireland State Savings Online", "Prize Bonds", "How to Purchase", "Manage My Details", "Ireland State Savings Products", "Repayments", "Bereavement Guide and Support", "Complaints", "Dormant Accounts", "Contact Us", "Security and Financial Crime","Financial Literacy"};
            String[] links1 = {"Ireland State Savings Online", "Prize Bonds", "How to Purchase", "Manage My Details", "Ireland State Savings Products", "Repayments", "Bereavement Guide and Support", "Complaints", "Dormant Accounts", "Security and Financial Crime","Financial Literacy"};
            for (int i = 1; i <= links1.length; i++) {
                String tab = driver.findElement(By.xpath("//a[@title='" + links1[i - 1] + "']")).getText();
                webUtil.CompareString(tab, String::equals, links1[i - 1]);

            }

        } catch (Exception e) {
            Assert.fail("udfValidateHelpCategories -" + e.getMessage());
        }
    }

    public void udfValidateFormsDownloads() {
        try {
            webUtil.scrollToView(hdrlblHelpFormsDownloads);
            String[] links1 = {"Prize Bonds", "Application Forms", "Fixed Term Products", "Deposit Accounts", "Regular Saving Products", "Terms & Conditions", "Repayments", "Deceased Cases", "Complaints", "Change of Details", "Brochures", "Other"};
            for (int i = 1; i <= links1.length; i++) {
                String tab = driver.findElement(By.xpath("//a[@title='" + links1[i - 1] + "']")).getText();
                if (tab.equalsIgnoreCase(links1[i - 1])) {
                    ExtentCucumberAdapter.addTestStepLog("Button Element Found--->Actual Element: " + tab + "\n" + "Expected Element: " + links1[i - 1]);
                    log.info("Button Element Found--->Actual Element: " + tab + " " + "Expected Element: " + links1[i - 1]);
                } else {
                    ExtentCucumberAdapter.addTestStepLog("Button Element NOT Found--->Actual Element: " + tab + "\n" + "Expected Element: " + links1[i - 1]);
                    log.info("Button Element NOT Found--->Actual Element: " + tab + " " + "Expected Element: " + links1[i - 1]);
                }
            }

            webUtil.gettextlog(hdrbanner, String::equals, "If you need to contact us a member of our customer service team would be very happy to assist you", "Banner");

        } catch (Exception e) {
            Assert.fail("udfValidateFormsDownloads -" + e.getMessage());
        }
    }

    public void validateFAQlinks() {
        hdrMap=Map.of("What are the rates as of 1st October 2023?","What are the rates as of 1st October 2023?",
                "How do I request a repayment of my State Savings products (including Prize Bonds)?","How do I request a repayment of my Ireland State Savings products?",
                "When are the Prize Bond draws?","How do Prize Bonds work?",
                "What is the current value of Prize Bond Fund and how is it calculated?","How is the Prize Bond Fund calculated?");


        try {
            String[] links = {
                    "What are the rates as of 1st October 2023?",
                    "How do I request a repayment of my State Savings products (including Prize Bonds)?",
                    "When are the Prize Bond draws?",
                    "Can I purchase Prize Bonds as a gift?",
                    "How to purchase online?"
            };
            validateLinks(links, "((//div[@class='m04-2col_text--block']/div/ul)[1]/li/a)");

            String[] links1 = {
                    "What happens to unclaimed prizes?",
                    "What are my Prize Bond winnings payment options?",
                    "How do Prize Bonds work?",
                    "What is the current value of Prize Bond Fund and how is it calculated?"
            };
            validateLinks(links1, "((//div[@class='m04-2col_text--block']/div/ul)[2]/li/a)");
        } catch (Exception e) {
            Assert.fail("validateFAQlinks -" + e.getMessage());
        }
    }

    private void validateLinks(String[] links, String xpathPrefix) {

        for (int i = 1; i <= links.length; i++) {
            webUtil.scrollToView(txtFAQ);
            String tab = driver.findElement(By.xpath(xpathPrefix + "[" + i + "]")).getText();
            By element = By.xpath(xpathPrefix + "[" + i + "]");
            if (tab.equalsIgnoreCase(links[i - 1])) {
                logLinkFound(tab, links[i - 1]);
                handleLinkClick(tab, element);
            } else {
                logLinkNotFound(tab, links[i - 1]);
            }
        }
    }

    private void logLinkFound(String actual, String expected) {
        ExtentCucumberAdapter.addTestStepLog("Link Element Found--->Actual Element: " + actual + "\n" + "Expected Element: " + expected);
        log.info("Link Element Found--->Actual Element: " + actual + " " + "Expected Element: " + expected);
    }

    private void logLinkNotFound(String actual, String expected) {
        ExtentCucumberAdapter.addTestStepLog("Link Element NOT Found--->Actual Element: " + actual + "\n" + "Expected Element: " + expected);
        log.info("Link Element NOT Found--->Actual Element: " + actual + " " + "Expected Element: " + expected);
    }

    public String getHeaderMapValue(String key){
         return hdrMap.get(key);

    }

    private void handleLinkClick(String tab, By element) {
        String expectedHdr = hdrMap.containsKey(tab)?getHeaderMapValue(tab):tab;
        webUtil.clickLog(element, tab);
        webUtil.gettextlog(hdrFAQ, String::equals, expectedHdr, "Header");
        webUtil.clickLog(lnkHelpandSupport, "help and support link");

    }


    public void clickCategory(String category) {
        webUtil.getElements(btnCategories)
                .stream()
                .filter(webElement -> webElement.getText().equals(category))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void udfValidateFormsDownloadsLinks(String language, String strVar, boolean val) {
        try {
            webUtil.scrollToView(hdrlblHelpFormsDownloads);

            // List of links to validate
            String[] links = {"Prize Bonds", "Application Forms", "Fixed Term Products", "Deposit Accounts",
                    "Regular Saving Products", "Terms & Conditions", "Repayments", "Deceased Cases",
                    "Complaints", "Change of Details", "Brochures", "Other"};

            for (int i = 1; i <= links.length; i++) {
                webUtil.waitFor(3000);
                String tab = driver.findElement(By.xpath("(//div[@class='m13-3col_category_listing--container'])[2]//a[ " + i + " ]//h3")).getText();   // Forms & download Common link
                By element = By.xpath("(//div[@class='m13-3col_category_listing--container'])[2]//a[ " + i + " ]");

                if (tab.equalsIgnoreCase(links[i - 1])) {
                    handleTabClick(language, tab, element, strVar, val);
                } else {
                    webUtil.CompareString(tab, String::equals, links[i - 1], "Forms & Downloads Category " + language + " Element");
                    log.info("Button Element NOT Found--->Actual Element: " + tab + " Expected Element: " + links[i - 1]);
                }
            }
        } catch (Exception e) {
            Assert.fail("udfValidateFormsDownloadsLinks - " + e.getMessage());
        }
    }

    private void handleTabClick(String language, String tab, By element, String strVar, boolean val) {
        Map<Integer, String> expectedFiles = getExpectedFiles(tab, language);
        webUtil.scrollToView(element);
        webUtil.clickLog(element, tab);

        if ("Irish".equalsIgnoreCase(language)) {
            webUtil.clickLog(TabIrish, "As Gaeilge");
        }

        String active = listofforms(strVar, val, language);
        Assert.assertEquals(active, tab);

        if (strVar.contains("Yes")) {
            udfisallfiledownloaded(getFilePrefix(tab), expectedFiles);
        }

        ExtentCucumberAdapter.addTestStepLog("Link for " + active + " is active/displayed");
        webUtil.clickLog(lnkHelpandSupport, "help and support link");
    }

    private Map<Integer, String> getExpectedFiles(String tab, String language) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<Integer, String> expectedFiles = new HashMap<>();
        try {
            // Read expected file names from JSON
//            JsonNode rootNode = objectMapper.readTree(new File();
            JsonNode rootNode = objectMapper.readTree(new File(Settings.getInstance().getProperty("JSON.Content.PDFNames")));
            JsonNode fileMappingsNode = rootNode.get("fileMappings");
            if (fileMappingsNode != null) { // Correct null check here
                JsonNode categoryNode = fileMappingsNode.get(tab);
                if (categoryNode != null && categoryNode.has(language)) {
                    JsonNode languageNode = categoryNode.get(language);
                    if (languageNode != null && languageNode.isArray()) {
                        int index = 1;
                        for (JsonNode fileNode : languageNode) {
                            expectedFiles.put(index, fileNode.asText());
                            index++;
                        }
                        return expectedFiles;
                    }
                }
            }


        } catch (IOException e) {
            log.error("Error reading expected file names: " + e.getMessage());
        }
       return expectedFiles;
    }

    private String getFilePrefix(String tab) {
        Map<String, String> prefixes = new HashMap<>();
        prefixes.put("Prize Bonds", "PZB");
        prefixes.put("Application Forms", "APF");
        prefixes.put("Fixed Term Products", "FTP");
        prefixes.put("Deposit Accounts", "DAC");
        prefixes.put("Regular Saving Products", "RSP");
        prefixes.put("Terms & Conditions", "TAC");
        prefixes.put("Repayments", "RPM");
        prefixes.put("Deceased Cases","DEC");
        prefixes.put("Complaints", "COM");
        prefixes.put("Change of Details", "COD");
        prefixes.put("Brochures", "BRU");
        prefixes.put("Other", "OTH");
        return prefixes.getOrDefault(tab, "Unknown Tab");
    }

    public void udfisallfiledownloaded(String formPrefix, Map<Integer, String> expectedFiles) {
        try {
            String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
            System.out.println(methodName);

            // Determine folder based on method name
            String folderName = methodName.equals("validateFormsDownloadsLinksAsGaeilge") ? "/Irish" : "/English";
            File sourcePath = new File(Util.getTargetPath()+folderName);
            File[] listOfFiles = sourcePath.listFiles();

            if (listOfFiles == null || listOfFiles.length == 0) {
                ExtentCucumberAdapter.addTestStepLog("<U> <I> No files found in the directory. </U> </I>");
                return;
            }

            HashMap<Integer, String> actualFiles = new HashMap<>();
            int index = 1;

            for (File file : listOfFiles) {
                String fileName = file.getName();
                System.out.println(fileName);

                if (fileName.startsWith(formPrefix)) { // Filter files based on prefix
                    String[] arrval = fileName.split("]_");
                    if (arrval.length > 1) {
                        actualFiles.put(index, arrval[1]);
                        System.out.println(index + " " + arrval[1]);
                        index++;
                    }
                }
            }


            // Validate file count
            if (actualFiles.size() == expectedFiles.size()) {
                ExtentCucumberAdapter.addTestStepLog("Files count to be downloaded is matched");
            } else {
                ExtentCucumberAdapter.addTestStepLog("<U> <I> Files count to be downloaded is not matched </U> </I>");
            }

            // Validate file contents
            for (int j = 1; j <= expectedFiles.size(); j++) {
                webUtil.CompareString(actualFiles.get(j), String::equals, expectedFiles.get(j), "File downloaded ");
            }

        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("<U> <I> Exception occurred: " + e.getMessage() + " </U> </I>");
            e.printStackTrace();
        }
    }

    public String listofforms(String strVar, boolean envchk, String language) {
        String active = null;

        By pdfLinks = language.equals("Irish") ? By.xpath("//*[@class='tab-content' and @id='tab-two']/ol/li") : By.xpath("//*[@class='tab-content' and @id='tab-one']/ol/li");
        List<WebElement> forms = webUtil.isElementVisible(pdfLinks, 7) ? webUtil.getElements(pdfLinks) : new ArrayList<>();

        try {
            active = webUtil.getText(By.xpath("//a[@class='gtm-sidenav category-sidebar__link-main  active']"));
            if (forms.size() != 0) {
                ExtentCucumberAdapter.addTestStepLog("Forms of <b><u>" + active + "</u></b> Found");
                log.info("Forms of <b><u>" + active + "</u></b> Found");

                for (WebElement form : forms) {
                    var locator = form.findElement(By.tagName("a"));
                    String tab = locator.getText();
                    webUtil.gettextByAttribute(locator, String::equals, "_blank", "target");
                    webUtil.clickLog(locator, tab);
                    Thread.sleep(1000);

                    boolean pageError = driver.findElements(errpage).size() != 0 || driver.findElements(ErrPage1).size() != 0;

                    if ("yes".equalsIgnoreCase(strVar)) {
                        if (envchk && pageError) {
                            logAndFail("Form " + tab + " not displayed / downloaded");
                        } else {
                            logSuccess("Form " + tab + " is displayed and downloaded");
                            if (!envchk) {
                                udfPDFNameformatter(active);
                                var folderPath = language.equals("Irish") ? Util.getTargetPath() + "/Irish" : Util.getTargetPath() + "/English";
                                moveFiles(Util.getTargetPath(), folderPath);
                            }
                        }
                    } else {
                        handleNoCase(tab, envchk);
                    }
                }
            }

        } catch (Exception e) {
            logAndFail(e.getLocalizedMessage());
        }
        return active;
    }

    private void logAndFail(String message) {
        ExtentCucumberAdapter.getCurrentStep().fail(message);
        log.info(message);
        Assert.fail(message);
    }

    private void logSuccess(String message) {
        ExtentCucumberAdapter.addTestStepLog(message);
        log.info(message);
    }

    private void handleNoCase(String tab, boolean envchk) {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        if (envchk && (webUtil.getElements(errpage).size() != 0 || webUtil.getElements(ErrPage1).size() != 0)) {
            logAndFail("Link " + tab + " not displayed");
        } else {
            webUtil.skip_switchToNewWindow(1);
            logSuccess("Page displayed for Form " + tab + "\n" + driver.getTitle() + " : " + driver.getCurrentUrl());
            driver.close();
            driver.switchTo().window(windows.get(0));

        }
    }

    public boolean udfPDFNameformatter(String category) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
        File sourcePath = new File(Util.getTargetPath());
        File[] listOfFiles = Optional.ofNullable(sourcePath.listFiles()).orElse(new File[0]);

        if (listOfFiles.length == 0) {
            System.err.println("Error: No files found in directory.");
            return false;
        }

        // Wait for file download to complete
        boolean downloadComplete = waitForDownloadCompletion(listOfFiles);
        if (!downloadComplete) {
            return false;
        }

        // Refresh file list after waiting for downloads
        listOfFiles = Optional.ofNullable(sourcePath.listFiles()).orElse(new File[0]);

        // Rename matching files
        return Arrays.stream(listOfFiles)
                .filter(file -> file.isFile() && file.getName().toLowerCase().endsWith(".pdf"))
                .filter(file -> udfArrString(file.getName().substring(0, 3)))
                .map(file -> renameFile(file, category, formatter))
                .reduce(false, Boolean::logicalOr);
    }

    private boolean waitForDownloadCompletion(File[] files) {
        int maxRetries = 8;
        int retryCount = 1;

        while (retryCount <= maxRetries) {
            boolean inProgress = Arrays.stream(files)
                    .map(File::getName)
                    .peek(name -> System.out.println("Checking file: " + name))
                    .anyMatch(name -> {
                        if (name.endsWith(".crdownload")) {
                            System.out.println("Download in progress: " + name);
                            ExtentCucumberAdapter.addTestStepLog("Download in progress for: " + name);
                            return true;
                        }
                        return false;
                    });

            if (!inProgress) {
                return true;
            }

            retryCount++;
        }

        System.err.println("Download incomplete after exceeding expected time.");
        ExtentCucumberAdapter.addTestStepLog("<b><i>Download incomplete after exceeding expected time</i></b>");
        return false;
    }

    private boolean renameFile(File file, String category, DateTimeFormatter formatter) {
        String categoryCode = getFilePrefix(category);
        if (categoryCode == null) {
            System.err.println("Invalid category: " + category);
            return false;
        }

        String timestamp = LocalDateTime.now().format(formatter);
        String newName = String.format("%s_[%s]_%s", categoryCode, timestamp, file.getName());
        File newFile = Paths.get(file.getParent(), newName).toFile();

        boolean isRenamed = file.renameTo(newFile);
        if (isRenamed) {
            System.out.println("Renamed file: " + newFile.getName());
        } else {
            System.err.println("Failed to rename file: " + file.getName());
        }
        return isRenamed;
    }

    public boolean udfArrString(String arrVal) {
        boolean flag = true;
        String[] arr = {"PZB", "APF", "FTP", "DAC", "RSP", "TAC", "RPM", "DEC", "COM", "COD", "BRU", "OTH", "NS4", "N10", "SAC", "SAB", "CCA", "DEP", "INS", "PZb"};

        for (String var : arr) {
            if (arrVal.equals(var)) {
                flag = false;
                break;
            }
        }

        return flag;
    }


    public void moveFiles(String sourceFolderPath, String destinationFolderPath) {
        // Create the destination folder if it doesn't exist
        File destinationFolder = new File(destinationFolderPath);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdir();
        }

        File[] files = new File(sourceFolderPath).listFiles();

        if (files != null) {
            Arrays.stream(files)
                    .filter(File::isFile)
                    .forEach(file -> {
                        try {
                            Files.move(file.toPath(), Path.of(destinationFolderPath, file.getName()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    public void enterKeywordForSearch() {
        webUtil.sendKeys(txtSearchKeyword,"Deposit");
        webUtil.click(btnSearch);
    }

    public void validateSearchResults() {
        if(webUtil.isElementVisible(By.id("ResultsTitle"),10)) {
            ExtentCucumberAdapter.addTestStepLog("Search results  header is displayed");
            System.out.println("Search results  header is displayed");
            if (webUtil.isElementVisible(By.id("tab-one"), 10)) {
                ExtentCucumberAdapter.addTestStepLog("Help & Support article links are displayed");
                System.out.println("Help & Support article links are displayed");
            }else {
                ExtentCucumberAdapter.addTestStepLog("Help & Support article links not displayed");
                System.out.println("Help & Support article links not displayed");
            }
        }else {
            ExtentCucumberAdapter.addTestStepLog("Search results  header is not displayed");
            System.out.println("Search results  header is not displayed");
        }

    }

    public  void clickManageMyDetailsLink() {

        try {

            By manageMyDetails = By.xpath("(//div[@class='m13-3col_category_listing--container'])[1]//a[4]");
            webUtil.scrollToView(manageMyDetails);
            webUtil.clickLog(manageMyDetails, "Manage My Details");

        } catch (Exception e) {
            Assert.fail("ManageMyDetailsLinks - " + e.getMessage());
        }
    }







}
