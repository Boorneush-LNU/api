package com.cucumbercraft.POMPages;

import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import com.cucumbercraft.framework.WebDriverUtil;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Notifications_Resource {
    public By notificationPath = By.xpath("//li[@class='primary-nav__notifications']//a[@class='gtm-nav header__nav-link header__nav-link--dashboard' and contains(text(),'Notifications')]");
    public By notificationSetting = By.xpath("//a[@class='gtm-sidenav' and contains(text(),'Notification Settings')]");
    public By annualStatement = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_annualStatementLink']");
    public By alerts = By.xpath("//label[@for='alertsNotification']//span[2]");
    public By profile_Change = By.xpath("//p[@id='NotificationsConfirmationMessage']");
    public By dropDown = By.id("notificationTypeFilter");
    public By action = By.xpath("//select[@class='select product-select']//option");
    public By confirm_Message = By.xpath("//p[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_ConfirmationMessage']");
    public By Notification_Confirm_Button = By.id("btnConfirmChanges");
    public By alert_Meassge = By.xpath("//div[@class='notification-box__text']/strong");
    public By message = By.xpath("//label[@for='messagesNotification']");
    public By notificationMessage = By.id("notification_");
 //   public By btnShow = By.name("show-notification-button");
    public By btnShow = By.cssSelector("button[aria-label='Show notification']");
    public By Show = By.xpath(".//button[starts-with(@id, 'show_')]");
    //button[@id='show_256583']//span[text()='Show']
//    public By Para = By.xpath("//div[@class='m41-notifications__item__body']//p");
    public By paragraph = By.xpath("//div[@class='m41-notifications__item__body']//p");
    public By P = By.cssSelector(".m41-notifications__item__body p");
    public By Message = By.xpath("//select[@id='notificationTypeFilter']");
    public By Checkbox = By.xpath("//div[@class='m41-notifications__item m41-notifications__item--alert  js-notification']");
    public By Remove = By.id("btnDeleteMultipleNotificationsOne");



    WebDriver driver;
    WebDriverUtil webUtil;

    public Notifications_Resource(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }


    public WebElement getFirstNotificationElement()
    {
        return webUtil.getElements(notificationMessage).get(0);

    }



    public List<WebElement> getAllNotificationElement()
    {
        return webUtil.getElements(notificationMessage);

    }
    public void clickShowBtn()
    {
        webUtil.scroll(getFirstNotificationElement().findElement(Show));
        getFirstNotificationElement().findElement(Show).click();
    }



    public String getNotificationDate(WebElement element) {
        return element.findElement(By.xpath("//div[contains(@class,'item__date')]")).getText();
    }


    public  String getNotificationTitle(WebElement element) {
        return element.findElement(By.xpath("//div[contains(@class,'item__title')]")).getText();
    }
}

