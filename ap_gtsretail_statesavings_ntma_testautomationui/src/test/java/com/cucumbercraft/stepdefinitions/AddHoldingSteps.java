package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.ReqToAddHoldings;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.POMPages.SliderContent;
import com.cucumbercraft.POMPages.StateSavingsDashboardPage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class AddHoldingSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    ReqToAddHoldings reqToAddHold = new ReqToAddHoldings(driver);
    SliderContent sliderContent;
    StateSavingsDashboardPage dashboardPage = new StateSavingsDashboardPage(driver);
    Context testContext;

    SignInPg signInPg = new SignInPg(driver);


    @And("click on add holding url and add different holding {string}")
    public void userClicksOnAddHoldingUrlAndAddDifferentHolding(String userProduct) throws Throwable {
        try {

            if (userProduct.equalsIgnoreCase("Prize Bonds")) {
                webUtil.selectListItem(reqToAddHold.selectProduct1, select -> select.selectByVisibleText(userProduct));
                System.out.println("Selected the product from dropdown" + ": " + userProduct);
                webUtil.sendKeys(reqToAddHold.bondNumber, "584698");
                webUtil.sendKeys(reqToAddHold.commenttextbox1, "this is my bond");

            } else {

                webUtil.selectListItem(reqToAddHold.selectProduct1, s -> s.selectByVisibleText(userProduct));
                System.out.println("Selected the product from dropdown" + ": " + userProduct);
                webUtil.sendKeys(reqToAddHold.accountNumber1, "748596");
                webUtil.sendKeys(reqToAddHold.commenttextbox1, "this is my bond");

            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("select {string} from dropdown and add different holding")
    public void user_clicks_on_add_holding_url_and_add_different(String userProduct) {
        try {

            if (userProduct.equalsIgnoreCase("Prize Bonds")) {
                webUtil.selectListItem(reqToAddHold.selectProduct1, select -> select.selectByVisibleText(userProduct));
                System.out.println("Selected the product from dropdown" + ": " + userProduct);
                webUtil.isElementVisible(reqToAddHold.bondNumber, 20);
                webUtil.sendKeys(reqToAddHold.bondNumber, "584698");
                webUtil.sendKeys(reqToAddHold.commenttextbox1, "PB Product");
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

            } else {
                webUtil.selectListItem(reqToAddHold.selectProduct1, s -> s.selectByVisibleText(userProduct));
                System.out.println("Selected the product from dropdown" + ": " + userProduct);
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                webUtil.sendKeys(reqToAddHold.accountNumber1, "748596");
                webUtil.sendKeys(reqToAddHold.commenttextbox1, "FT Product");

            }
        } catch (Exception e) {
            Assert.fail("Add Holding: Failed to select product from dropdown: " + e.getMessage());
        }
    }

    @And("click on add another product {string} and add different holding")
    public void userClicksOnAddAnotherProduct(String userAnotherProduct) throws Throwable {

        webUtil.click(reqToAddHold.addAnotherProductLink);
        log.info("Add another product link is clicked ");
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        webUtil.selectListItem(reqToAddHold.selectProduct2, select -> select.selectByVisibleText(userAnotherProduct));
        System.out.println("Selected the product from dropdown" + ": " + userAnotherProduct);
        webUtil.sendKeys(reqToAddHold.accountNumber2, "748596");
        webUtil.sendKeys(reqToAddHold.commenttextbox2, "new holding");


    }

    @And("click remove button to remove {string} product from the list")
    public void userRemovesAnotherProduct(String productName) {
        webUtil.click(reqToAddHold.removeProduct1);
        log.info("remove product link is clicked ");
    }

    @Then("click on confirm and download form button")
    public void userClicksOnDownloadButtonAndSuccessFulMessageIsDisplayed() {
        try {
            sliderContent = reqToAddHold.clickConfirmAndDownloadForm();
            Assert.assertTrue(webUtil.isElementVisible(dashboardPage.thankYouAddHoldingSlider, 20));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("validate {string} is downloaded")
    public void validateAddHoldingFormIsDownloaded(String formName) {

        String downloadPath = Util.getTargetPath()+Util.getFileSeparator()+"AddHoldings";
        File dir = new File(downloadPath);
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".pdf"));

        org.testng.Assert.assertNotNull(files, "No files found in downloads directory");
        org.testng.Assert.assertTrue(files.length > 0, "No CSV files found in downloads directory");

        // Get the most recently downloaded file
        File lastModifiedFile = Arrays.stream(files)
                .max(Comparator.comparingLong(File::lastModified))
                .orElseThrow(() -> new AssertionError("No PDF file found"));

        // Verify file was downloaded in last 1 minute
        long timeDiff = System.currentTimeMillis() - lastModifiedFile.lastModified();
        org.testng.Assert.assertTrue(timeDiff < 60000, "File was not downloaded recently");
    }

    @Then("click close button on {string} slider")
    public void clickCloseButtonOnSlider(String SliderName) {
        try {
            WebElement element;

            switch (SliderName) {
                case "add holding Thank you":
                    element = webUtil.waitUntilElementVisible(dashboardPage.thankYouAddHoldingSlider, 10);
                    sliderContent.clickSecondaryBtn(element);
                    log.info("Close button is clicked");
                    break;
                case "add holding":
                    element = webUtil.waitUntilElementVisible(dashboardPage.addHoldingSlider, 10);
                    new SliderContent(driver).clickSecondaryBtn(element);
                    log.info("Close button is clicked");
                    webUtil.gettextlog(dashboardPage.ConfirmHeader,String::equals, "Cancel request");
                    webUtil.gettextlog(dashboardPage.ConfirmDescription,String::equals, "Please confirm you wish to cancel this request to add a product(s)");
                    webUtil.click(dashboardPage.ConfirmButton);
                    break;
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
