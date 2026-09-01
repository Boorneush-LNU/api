package com.cucumbercraft.POMPages.PBAdmin;

import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class utility {

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        utility data111=new utility();
        data111.utilityRunMethod();
    }

    public void utilityRunMethod() throws InterruptedException {
        WebDriver driver=new ChromeDriver();
//        driver.get("https://app-anpost-sswbepbadminportal-q-ne01.azurewebsites.net/Orders/Orders.aspx");
        driver.get("http://Shahapurep:GoldenBrick!2345@qaprizebondsadmin");

        driver.manage().window().maximize();
//        By signInUserNameTxt=By.xpath("//input[@name='loginfmt']");
//        By nxtBtn=By.xpath("//input[@id='idSIButton9']");
//        By pwdTxt=By.xpath("//input[@name='passwd']");
//        By signInBtn=By.xpath("//input[@id='idSIButton9']");
//        By yesBtn=By.xpath("//input[@id='idSIButton9']");
Thread.sleep(3000);
driver.findElement(By.linkText("Orders")).click();
        Thread.sleep(3000);
//        driver.findElement(signInUserNameTxt).sendKeys("prashant.shahapure@anpost.ie");
//        driver.findElement(nxtBtn).click();
//        Thread.sleep(2000);
//        driver.findElement(pwdTxt).sendKeys("GoldenBrick!2345");
//        driver.findElement(signInBtn).click();
//        Thread.sleep(2000);
//        driver.findElement(yesBtn).click();
//        Thread.sleep(2000);


        for(int i=0;i<3;i++) {

            By fromfield = By.id("ctl00_PageBodyPlaceholder_cntrlDateFrom_txtDate");
            driver.findElement(fromfield).clear();
            driver.findElement(fromfield).sendKeys("03 Dec 2024");
            Thread.sleep(2000);

            By tofield = By.id("ctl00_PageBodyPlaceholder_cntrlDateTo_txtDate");
            driver.findElement(tofield).clear();
            driver.findElement(tofield).sendKeys("07 May 2025");
            Thread.sleep(2000);

            By dropdown = By.id("ctl00_PageBodyPlaceholder_cntrlOrderStaus_ddlOrderStatus");
            Select drp = new Select(driver.findElement(dropdown));
            drp.selectByVisibleText("Pending");
            Thread.sleep(2000);
            By res = By.id("ctl00_PageBodyPlaceholder_ddlRecsPerPage");
            Select drp1 = new Select(driver.findElement(res));
            drp1.selectByVisibleText("All");
            Thread.sleep(4000);
            By searchBtn=By.id("ctl00_PageBodyPlaceholder_btnGetOrders");
            driver.findElement(searchBtn).click();

//            By idTransRef=By.xpath("//table[@id='tabOrders']/tbody/tr/td[6]/a");
//            List<WebElement> transrefID1=driver.findElements(idTransRef);
            By idRef = By.id("ctl00_PageBodyPlaceholder_lvOrderList_ctrl0_ctl00_paymentRef");

            try {
                String valueref = driver.findElement(idRef).getText();
                driver.findElement(idRef).click();

                System.out.println("**********************" + valueref + "**********************");
                Thread.sleep(4000);
                By dropdown2 = By.id("ctl00_PageBodyPlaceholder_cntrlOrderStaus_ddlOrderStatus");
                Select drp2 = new Select(driver.findElement(dropdown2));
                drp2.selectByVisibleText("Closed");
                Thread.sleep(2000);
                By commentsArea = By.xpath("//textarea[@id='ctl00_PageBodyPlaceholder_txtComments']");
                driver.findElement(commentsArea).sendKeys("automation closure");
                Thread.sleep(2000);
                By updateOrd = By.id("ctl00_PageBodyPlaceholder_btnUpdateOrder");
                driver.findElement(updateOrd).click();
                Thread.sleep(4000);
                System.out.println("**********************" + valueref + "------COMPLETED**********************");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }






    }


}
