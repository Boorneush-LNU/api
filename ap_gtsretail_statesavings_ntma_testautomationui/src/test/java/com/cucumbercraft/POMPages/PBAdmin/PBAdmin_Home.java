package com.cucumbercraft.POMPages.PBAdmin;

import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PBAdmin_Home extends Kentico13_MasterPages {
    private WebDriver driver;
    public PBAdmin_Home(WebDriver driver){
        this.driver=driver;
    }

    private final By micEmailTxtBox=By.xpath("//input[@type='email']");
    private final By micPwdTxtBox=By.xpath("//input[@type='password']");
    private final By micSubmitBtn=By.xpath("//input[@type='submit']");
    private final By hdrAdminHomePg = By.xpath("//div/h1");
    private final By subContent=By.xpath("//div[@id='sub-content']//p");
    private final By hdrDateUserInfo=By.xpath("//span[@class='date']");
    private final By footerContent1=By.xpath("//div[@id='footer']/ul/li");
    private final By footerContent2=By.xpath("//div[@id='footer']/p");
    private final By versionInformationLink=By.xpath("//div[@id='footer']/p/a");

    ///////////////////////////////// ABOUT PAGE /////////////////////////////////////

    private final By hdr2AboutPg = By.xpath("//div/h2");

    public String getHdrAdminHomePg() {
        return getTextByLocator(hdrAdminHomePg);
    }

    public String getSubContent() {
        return getTextByLocator(subContent);
    }

    public String getHdrDateUserInfo() {
        return getTextByLocator(hdrDateUserInfo);
    }

    public String getFooterContent1() {
        return getTextByLocator(footerContent1);
    }

    public String getFooterContent2() {
        return getTextByLocator(footerContent2);
    }

    public String getVersionInformationLinkText(){
        return getTextByLocator(versionInformationLink);
    }

    public void clickVersionInformationLnk(){
        webUtil.clickLog(versionInformationLink, "Version Information");
    }

    public String getHdr2AboutPg() {
        return getTextByLocator(hdr2AboutPg);
    }

    public String getExpDateAndUserInfo(String userId){
        LocalDateTime myDateObj = LocalDateTime.now();
        String date=String.valueOf(myDateObj.getDayOfMonth());
        String dayTemp=String.valueOf(myDateObj.getDayOfWeek());
        String year=String.valueOf(myDateObj.getYear());
        String monthTemp=String.valueOf(myDateObj.getMonth());
        String day=dayTemp.charAt(0)+dayTemp.substring(1).toLowerCase();
        String month=monthTemp.charAt(0)+monthTemp.substring(1).toLowerCase();
        return day+" "+date+" "+month+" "+year+"\n"+ "\n" +"User: "+userId;
    }


    public boolean validateVersionInformationLinkRedirection(String expUrlRedirect){
        return driver.getCurrentUrl().contains(expUrlRedirect);
    }

    public void loginSSPBAdminUser(String userEmail, String userPwd){
        enterMicEmail(userEmail);
        clickMicSubmitBtn();
        enterMicPwd(userPwd);
        clickMicSubmitBtn();
        clickMicSubmitBtn();
    }
    private void enterMicEmail(String userEmail){
        webUtil.sendKeys(micEmailTxtBox, userEmail);
    }

    private void clickMicSubmitBtn(){
        webUtil.click(micSubmitBtn);
    }

    private void enterMicPwd(String userPwd){
        webUtil.sendKeys(micPwdTxtBox,userPwd);
    }


}
