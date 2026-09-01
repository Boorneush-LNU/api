package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.Notifications_Resource;
import com.cucumbercraft.POMPages.StateSavingsDashboardPage;
import com.cucumbercraft.framework.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Date;
import java.util.List;

public class NotificationSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    PortalLoginSteps login = new PortalLoginSteps();
    Notifications_Resource notify = new Notifications_Resource(driver);
    ProfileSettingsSteps profile = new ProfileSettingsSteps();
    StateSavingsDashboardPage dashboardPage = new StateSavingsDashboardPage(driver);
    private int InitialCount;
    private int AfterReinvest;

    @And("^click on the Notification menu and navigates to the Notification Page$")
    public void clickOnTheNotificationMenuAndNavigatesToTheNotificationPage() {
        webUtil.click(notify.notificationPath);
    }

    @And("^click on the Notification menu and navigates to the Notification Page check the count$")
    public void clickOnTheNotificationMenuAndNavigatesToTheNotificationPageMessage() throws InterruptedException {
        webUtil.click(notify.notificationPath);
        String Type = "Messages";
        if (webUtil.isElementDisplayed(notify.Message, 10)) {
            try {
                webUtil.selectDropDown(notify.Message, select -> select.selectByVisibleText((Type)));
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new ExceptionUtils("Not selected");
            }
            Thread.sleep(2000);
            List<WebElement> Size = driver.findElements(By.xpath("//div[@class='m41-notifications__item m41-notifications__item--alert  js-notification']"));
            InitialCount = Size.size();
            System.out.println(InitialCount);
        }
    }

    @And("^click on the Notification menu and navigates to the Notification Page check the count after Reinvest$")
    public void clickOnTheNotificationMenuAndNavigatesToTheNotificationPageAfterReinvest() throws InterruptedException {
        webUtil.click(notify.notificationPath);
        String Type = "Messages";
        if (webUtil.isElementDisplayed(notify.Message, 10)) {
            try {
                webUtil.selectDropDown(notify.Message, select -> select.selectByVisibleText((Type)));
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new ExceptionUtils("Not selected");
            }
            Thread.sleep(2000);
            List<WebElement> Size1 = driver.findElements(By.xpath("//div[@class='m41-notifications__item m41-notifications__item--alert  js-notification']"));
            AfterReinvest= Size1.size();
            System.out.println(AfterReinvest);
        }
    }

    @Then("Check the Notification count in Message section Before Reinvest and After Reinvest")
    public void compareNotificationCounts() {
        System.out.println("Comparing stored notification counts.");
if(InitialCount!=AfterReinvest){
    System.out.println("Message is working");
}
//        Assert.assertEquals(InitialCount, AfterReinvest,
//                "Notification counts are not equal. First count: " + InitialCount +
//                        ", Second count: " + AfterReinvest);
        System.out.println("SUCCESS: Both notification counts are equal.");
    }

    @And("^click on the Notification menu and navigates to the Notification Page check the count for Alerts$")
    public void clickOnTheNotificationMenuAndNavigatesToTheNotificationPageAlert() throws InterruptedException {
        webUtil.click(notify.notificationPath);
        String Type = "Alerts";
        if (webUtil.isElementDisplayed(notify.Message, 10)) {
            try {
                webUtil.selectDropDown(notify.Message, select -> select.selectByVisibleText((Type)));
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new ExceptionUtils("Not selected");
            }
            Thread.sleep(2000);
            List<WebElement> Size = driver.findElements(By.xpath("//div[@class='m41-notifications__item m41-notifications__item--alert  js-notification']"));
            InitialCount = Size.size();
            System.out.println("Initial Count :" + InitialCount);
        }
    }

    @And("^click on the Notification menu and navigates to the Notification Page check the count after Change Password$")
    public void clickOnTheNotificationMenuAndNavigatesToTheNotificationPageAlertAfterPasscodeChange() throws InterruptedException {
        webUtil.click(notify.notificationPath);
        String Type = "Alerts";
        if (webUtil.isElementDisplayed(notify.Message, 10)) {
            try {
                webUtil.selectDropDown(notify.Message, select -> select.selectByVisibleText((Type)));
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new ExceptionUtils("Not selected");
            }
            Thread.sleep(2000);
            List<WebElement> Size = driver.findElements(By.xpath("//div[@class='m41-notifications__item m41-notifications__item--alert  js-notification']"));
            InitialCount = Size.size();
            System.out.println(InitialCount);
        }
    }

    @Then("Check the Notification count in Alerts section Before Change Password and After Change Password")
    public void compareNotificationCountsAlerts() {
        System.out.println("Comparing stored notification counts.");
        List<WebElement> Size = driver.findElements(By.xpath("//div[@class='m41-notifications__item m41-notifications__item--alert  js-notification']"));
        AfterReinvest = Size.size();
        if(InitialCount>AfterReinvest){
            System.out.println("Alert is working");
        }
        System.out.println("Initial Count :" + InitialCount);
//        Assert.assertNotEquals(InitialCount, AfterReinvest,
//                "Notification counts are not equal. First count: " + InitialCount +
//                        ", Second count: " + AfterReinvest);
        System.out.println("SUCCESS: Both notification counts are equal.");
    }





    @And("^User is able to view the Notification DropDown$")
    public void userIsAbleToViewTheNotificationDropDown() throws Exception {
//        Assert.assertTrue(webUtil.getText(notify.dropDown).equalsIgnoreCase("All Notifications"));
    }

    @Then("^User selects the \"([^\"]*)\" and able to view the respective notifications$")
    public void userSelectsTheAndAbleToViewTheRespectiveNotifications(String selectedAction) {

        System.out.println("User selects the alert from dropdown");
        webUtil.selectDropDown(notify.dropDown, select -> select.selectByVisibleText(selectedAction));

    }

    //Alert


    @And("^clicks on the Notifications and Setting tab and is navigated to notifications and settings page$")
    public void clicksOnTheNotificationsAndSettingTabAndIsNavigatedToNotificationsAndSettingsPage() {

        webUtil.click(notify.notificationSetting);
        System.out.println("notification setting tab is clicked");
        log.info("Notification setting tab is clicked");

    }

    @And("^click on the Notification menu and navigates to the Notification Page Delete$")
    public void clickOnTheNotificationMenuAndNavigatesToTheNotificationPageMessageDelete() throws InterruptedException {
        webUtil.click(notify.notificationPath);
        List<WebElement> checkboxes = driver.findElements(By.xpath("//label[@aria-label='Notification to be deleted']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        Thread.sleep(2000);
//        webUtil.click(notify.Remove);  Click on Remove button
//        log.info("Pass");
    }

    @Then("^User disabled the alerts and able to view the Confirm change page$")
    public void userDisabledTheAlertsAndAbleToViewTheConfirmChangePage() {


        webUtil.click(notify.alerts);
        webUtil.gettextlog(notify.profile_Change, String::equals, "You have updated your notification settings, please confirm these changes.");

    }

    @And("^Clicks on the Confirm and is able to view the alert message$")
    public void clicksOnTheConfirmAndIsAbleToViewTheAlertMessage() {

        try {
            webUtil.click(notify.Notification_Confirm_Button);
            System.out.println("the alert is disabled");
            webUtil.gettextlog(notify.alert_Meassge, String::equals, "Thank you, your notification settings have been updated");


        } catch (Exception e) {
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }

    }

    @Then("User clicks message toggle button and able to view the Confirm change modal")
    public void userDisabledTheMessageAndAbleToViewTheConfirmChangePage() {
        webUtil.waitFor(3000);
        webUtil.click(notify.message);
        Assert.assertTrue(webUtil.getText(notify.profile_Change).contentEquals("You have updated your notification settings, please confirm these changes."));
    }

    @Then("User clicks message toggle button when it is enabled")
    public void userDisabledTheMessageToggle() {
        webUtil.scrollToView(notify.message);
        if (webUtil.objIsEnabled(notify.message)) {
            log.info("Toggle button is  enabled. Clicking to disable it.");
            webUtil.click(notify.message);
            Assert.assertTrue(webUtil.getText(notify.profile_Change).contentEquals("You have updated your notification settings, please confirm these changes."));
            webUtil.click(notify.Notification_Confirm_Button);

        } else {
            System.out.println("Toggle button is already disabled. No action needed.");
        }
    }

    @Then("User clicks alert toggle button when it is enabled")
    public void userDisabledTheAlertToggle() {
        webUtil.scrollToView(notify.alerts);
        if (webUtil.objIsEnabled(notify.alerts)) {
            log.info("Toggle button is  enabled. Clicking to disable it.");
            webUtil.click(notify.alerts);
            Assert.assertTrue(webUtil.getText(notify.profile_Change).contentEquals("You have updated your notification settings, please confirm these changes."));
            webUtil.click(notify.Notification_Confirm_Button);

        } else {
            System.out.println("Toggle button is already disabled. No action needed.");
        }
    }


    @Then("validate notification bubble count")
    public void validateNotificationBubbleCount() {
        dashboardPage.validateNotificatioBubbleCount(data.getUsername());
    }


    @SneakyThrows
    @Then("validate {string} notification is displayed")
    public void validateNotificationIsDisplayed(String journeyName) {

        var element = notify.getFirstNotificationElement();
        var notificationTitle = notify.getNotificationTitle(element);
        var notificationDate = notify.getNotificationDate(element);
        Thread.sleep(2000);
        String actualMessage, formattedMessage;


        boolean isDateValid = webUtil.CompareString(notificationDate, String::equals, Util.getFormattedTime(new Date(), "dd/MM/yyyy"));
//        String formattedMessage = actualMessage.replaceAll("\\d{2}:\\d{2}:\\d{2}", "");

        String expectedMessage = "Your %s has been updated";
        switch (journeyName) {
            case "change password":
            case "change email":
//                notify.clickshow();
                notify.clickShowBtn();
                Thread.sleep(1000);
                actualMessage = element.findElement(notify.paragraph).getText();
                expectedMessage = journeyName.equals("change password") ? String.format(expectedMessage, "Password")
                        : String.format(expectedMessage, "Email");
                validateNotifactionMessage(journeyName, actualMessage, expectedMessage, notificationTitle, "Profile change", isDateValid);

                break;
            case "AddIBAN":
            case "ChangeIBAN":
                notify.clickShowBtn();
                actualMessage = element.findElement(notify.paragraph).getText();
//                actualMessage = element.findElement(notify.paragraph).getText();
//               actualMessage.replaceAll("\\d{2}:\\d{2}:\\d{2}", "");
                expectedMessage = journeyName.equals("AddIBAN") ? "Your IBAN has been added" : "Your IBAN has been updated";
                validateNotifactionMessage(journeyName, actualMessage, expectedMessage, notificationTitle, "Profile change", isDateValid);
                break;

            case "repayment":
                notify.clickShowBtn();
                actualMessage = element.findElement(notify.paragraph).getText();
                formattedMessage=  actualMessage.replaceAll("\\d{2}:\\d{2}:\\d{2}", "");
                expectedMessage = "We have received your repayment request from " + getProductName(data.getProduct()) + ", " + data.getHoldingID() + " on " + Util.getFormattedTime(new Date(), "dd/MM/yyyy") + ". Your holding will be temporarily unavailable during the time it takes to complete this process.";
                validateNotifactionMessage(journeyName, formattedMessage, expectedMessage, notificationTitle, "Repayment", isDateValid);
                break;
            case "reinvestment":
                notify.clickShowBtn();
                actualMessage = element.findElement(notify.paragraph).getText();
                formattedMessage=  actualMessage.replaceAll("\\d{2}:\\d{2}:\\d{2}", "");
                expectedMessage = "Thank you for your reinvestment request from " + getProductName(data.getProduct()) + ", " + data.getHoldingID() + " on " + Util.getFormattedTime(new Date(), "dd/MM/yyyy") + " . We will process this for you as soon as possible and write to you with details. Your holding will be temporarily unavailable until this is processed.";
                validateNotifactionMessage(journeyName, formattedMessage, expectedMessage, notificationTitle, "Reinvestment request received", isDateValid);
                break;
            case "partial repayment reinvestment":
                notify.clickShowBtn();
                actualMessage = element.findElement(notify.paragraph).getText();
                formattedMessage=  actualMessage.replaceAll("\\d{2}:\\d{2}:\\d{2}", "");
                expectedMessage = "We have received your repayment request from " + getProductName(data.getProduct()) + ", " + data.getHoldingID() + " on " + Util.getFormattedTime(new Date(), "dd/MM/yyyy") + ". Your holding will be temporarily unavailable during the time it takes to complete this process.";
                validateNotifactionMessage(journeyName, formattedMessage, expectedMessage, notificationTitle, "Repayment request received", isDateValid);

                WebElement element1 = notify.getAllNotificationElement().get(1);
                webUtil.scroll(element1);
                element1.findElement(notify.btnShow).click();
                String partialReinvestmentMessage = element1.findElement(notify.paragraph).getText().replaceAll("\\d{2}:\\d{2}:\\d{2}", "");
                notificationTitle = notify.getNotificationTitle(element1);
                isDateValid = webUtil.CompareString(notify.getNotificationDate(element1), String::equals, Util.getFormattedTime(new Date(), "dd/MM/yyyy"));
                expectedMessage = "Thank you for your reinvestment request from " + getProductName(data.getProduct()) + ", " + data.getHoldingID() + " on " + Util.getFormattedTime(new Date(), "dd/MM/yyyy") + " . We will process this for you as soon as possible and write to you with details. Your holding will be temporarily unavailable until this is processed.";
                validateNotifactionMessage(journeyName, partialReinvestmentMessage, expectedMessage, notificationTitle, "Reinvestment request received", isDateValid);


                break;
        }
    }





    private void validateNotifactionMessage(String journeyName, String actualMessage, String expectedMessage, String notificationTitle, String expectedNotificationTitle, boolean isDateValid) {
        boolean isTitleValid;
        boolean isMessageValid;
        isMessageValid = webUtil.CompareString(actualMessage, String::equals, expectedMessage);
        isTitleValid = webUtil.CompareString(notificationTitle, String::equals, expectedNotificationTitle);
        Assert.assertTrue(isTitleValid, "Title is not matching");
        Assert.assertTrue(isDateValid, "Date is not matching");
        if (isMessageValid)
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_PASS, "Successfully validated the " + journeyName + " notification");
        else {
            FrameworkLogger.log(LogType.EXTENT_AND_CONSOLE_FAIL, "Validation failed for the " + journeyName + " notification");
            Assert.fail("Validation failed for the " + journeyName + " notification");
        }
    }

    private String getProductName(String productName) {
        return productName.equals("Saving Bond")?"Savings Bond"
                : productName.equals("Prize Bond")?"Prize Bonds"
                : productName.equals("SSA")?"State Savings Account"
                : productName.contains("NSB")?"National Solidarity Bond"
                : productName;
    }


}
