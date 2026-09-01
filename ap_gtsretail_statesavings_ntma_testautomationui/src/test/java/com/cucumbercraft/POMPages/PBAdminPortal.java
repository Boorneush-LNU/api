package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.PBAdmin;
import com.cucumbercraft.framework.WebDriverUtil;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PBAdminPortal {
    WebDriver driver;
    WebDriverUtil webUtil;

    public PBAdminPortal(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }

    Map<String, Consumer<WebDriverUtil>> tabMap = Map.of("Home",webDriverUtil -> webDriverUtil.clickLog(By.cssSelector("#nav li:nth-child(1)"),"Home"),
            "Orders",webDriverUtil -> webDriverUtil.clickLog(By.cssSelector("#nav li:nth-child(2)"),"Orders"),
            "Reports",webDriverUtil -> webDriverUtil.clickLog(By.cssSelector("#nav li:nth-child(3)"),"Reports"));

    public void launchAdminPortal()
    {
        driver.get("https://app-anpost-sswbepbadminportal-q-ne01.azurewebsites.net");
        webUtil.waitUntilElementVisible(By.xpath("//input[@type='email']"),10).sendKeys("prashant.shahapure@anpost.ie");
        webUtil.waitUntilElementVisible(By.xpath("//input[@type='submit']"),10).click();
        webUtil.waitUntilElementVisible(By.xpath("//input[@type='password']"),10).sendKeys("GoldenBrick!2345");
        webUtil.waitUntilElementVisible(By.xpath("//input[@type='submit']"),10).click();
        webUtil.waitUntilElementVisible(By.xpath("//div[text()='Stay signed in?']"),10);
        webUtil.waitUntilElementVisible(By.xpath("//input[@type='submit']"),10).click();

        webUtil.getWebDriverWait().until(ExpectedConditions.titleContains("Home"));
        boolean navigationLocated=webUtil.isElementVisible(By.id("nav"),10);
        if (navigationLocated) System.out.println("navigation tabs is visible");
        else System.out.println("navigation tabs not visible");
    }

    public void clickOrdersTab(String tabName)
    {
        tabMap.get(tabName).accept(webUtil);
        webUtil.getWebDriverWait().until(ExpectedConditions.titleContains("Orders"));
        webUtil.selectDropDown(By.xpath("//select[@name='ctl00$PageBodyPlaceholder$ddlRecsPerPage']"),select ->select.selectByVisibleText("All"));

    }



    public  void scrapeTableData() {

        WebElement table =  webUtil.waitUntilElementVisible(By.cssSelector("#tabOrders"),10); // Select the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        List<PBAdmin> data = new ArrayList<>();

        for (int i = 1; i < rows.size(); i++) { // Skip header row
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            PBAdmin cellName =new PBAdmin();
                cellName.setDate(cells.get(0).getText());
                cellName.setCardType(cells.get(1).getText());
                cellName.setCardNumber(cells.get(2).getText());
                cellName.setName(cells.get(3).getText());
                cellName.setAuthCode(cells.get(4).getText());
                cellName.setPaymetRefNo(cells.get(5).getText());
                cellName.setValue(cells.get(6).getText());
                data.add(cellName);

        }
        ExtentCucumberAdapter.addTestStepLog("Total records scrapped: "+rows.size());
        ExtentCucumberAdapter.addTestStepLog("Data scrapped: "+data);
        var pathname="src/test/resources/Test_Data/Orderdetails.xlsx";
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .sheetName("Sheet1") // Optional: set sheet name
                .build();
        Poiji.toExcel(new File(pathname),PBAdmin.class,data, options);

    }


}
