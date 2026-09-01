package com.cucumbercraft.POMPages.ExpeditedReg;


import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import com.cucumbercraft.stepdefinitions.ReinvestSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.cucumbercraft.stepdefinitions.MasterStepDefs.*;
import static com.cucumbercraft.stepdefinitions.TitleSteps.regstrnData;

public class ExpeditedLetsGetStarted {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;

    public ExpeditedLetsGetStarted(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    private final String enterFields = "//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationPersonalDetails_%s']";
    private final String tooltipCont = "Your Personal Public Service Number (PPSN) can be found on your Public Services Card or on any documentation from Revenue. Your State Savings Customer Number (SSCN) can be found on correspondence you receive from us.";
    private final By firstName = By.id("FirstName");
    private final By surname = By.id("Surname");
    private final By sscnTooltip = By.xpath("//span[@class='tooltip']/button");
    private final By sscnTooltipCont = By.xpath("//span[@id='ppsn_sscn_info']");
    private final By backbtn = By.id("btnBack");
    private final By dobError =By.xpath("//div[@id='dob-error']//span");
    private final By nextbtn = By.id("btnNext");


    public void enterFirstName() throws InterruptedException {

        webUtil.sendKeys(firstName, REGISTRATION_DATA.getFirstName());
    }

    public void enterSurName() throws InterruptedException {

        webUtil.sendKeys(surname, REGISTRATION_DATA.getSecondName());

    }

    public void enterDOB() throws InterruptedException {
        System.out.println(REGISTRATION_DATA.getDob());
        if (!REGISTRATION_DATA.getDob().equalsIgnoreCase("")) {
            String[] DOBArray = REGISTRATION_DATA.getDob().split("-");

            webUtil.sendKeys(By.id("DayOfBirth"), DOBArray[0]);


            webUtil.sendKeys(By.id("MonthOfBirth"), DOBArray[1]);


            webUtil.sendKeys(By.id("YearOfBirth"), DOBArray[2]);

        } else {
            log.info("DOB is Blank");
        }
    }

    public void enterInvalidDOB(List<String> errorMessage, List<String> dobList) throws InterruptedException {

        for(int i=0; i<dobList.size();i++) {

            String dob = dobList.get(i).trim();

            String[] DOBArray = dob.split("-");

            webUtil.sendKeys(By.id("DayOfBirth"), DOBArray[0]);

            webUtil.sendKeys(By.id("MonthOfBirth"), DOBArray[1]);

            webUtil.sendKeys(By.id("YearOfBirth"), DOBArray[2]+ Keys.ENTER);
            driver.findElement(By.xpath("//input[@id='Surname']")).click();
           webUtil.waitFor(3000);

            String dobErrorMessage = driver.findElement(dobError).getText();

            webUtil.CompareString(dobErrorMessage, String::equals, errorMessage.get(0), "Error message");


            driver.navigate().refresh();


        }



    }





    public void enterSSCN() throws InterruptedException {

        webUtil.sendKeys(By.id("PPSN"), REGISTRATION_DATA.getSscn());

    }

    public void click_SSCN_Tooltip() throws InterruptedException {

        webUtil.click(sscnTooltip);

    }

    public void verify_SSCN_TooltipCont() throws Exception {

        webUtil.gettextlog(sscnTooltipCont, String::equalsIgnoreCase, tooltipCont);

    }

    public void clickBackBtn() throws Exception {
        webUtil.gettextlog(backbtn, String::equalsIgnoreCase, "Back");
        webUtil.click(backbtn);
        log.info("Back button Clicked on lets get started page");

    }

    public void clickNextBtn() throws Exception {

        webUtil.gettextlog(nextbtn, String::equalsIgnoreCase, "Next");
        webUtil.click(nextbtn);
        webUtil.waitForPageLoaded();
        log.info("Next button Clicked on lets get started page");

    }

    public void enterdetails() throws Exception {

        webUtil.sendKeys(firstName, regstrnData.get("FirstName1"));
        webUtil.sendKeys(surname,  regstrnData.get("SecondName1"));
        if (! regstrnData.get("DOB1").equalsIgnoreCase("")) {
            String[] DOBArray =  regstrnData.get("DOB1").split("-");

            webUtil.sendKeys(By.id("DayOfBirth"), DOBArray[0]);


            webUtil.sendKeys(By.id("MonthOfBirth"), DOBArray[1]);


            webUtil.sendKeys(By.id("YearOfBirth"), DOBArray[2]);

        } else {
            log.info("DOB is Blank");
        }
        webUtil.sendKeys(By.id("PPSN"),  regstrnData.get("SSCN1"));

    }

    public void enterdetailsecr() throws Exception {

        webUtil.sendKeys(firstName,  regstrnData.get("FirstName"));
        webUtil.sendKeys(surname,  regstrnData.get("SecondName"));
        if (! regstrnData.get("DOB").equalsIgnoreCase("")) {
            String[] DOBArray =  regstrnData.get("DOB").split("-");

            webUtil.sendKeys(By.id("DayOfBirth"), DOBArray[0]);


            webUtil.sendKeys(By.id("MonthOfBirth"), DOBArray[1]);


            webUtil.sendKeys(By.id("YearOfBirth"), DOBArray[2]);

        } else {
            log.info("DOB is Blank");
        }
        webUtil.sendKeys(By.id("PPSN"),  regstrnData.get("SSCN"));

    }

    public void enterinvaliddetails() throws Exception {
        webUtil.sendKeys(firstName, "fname");
        webUtil.sendKeys(surname, "SecondName");
        webUtil.sendKeys(By.id("DayOfBirth"), "12");
        webUtil.sendKeys(By.id("MonthOfBirth"), "3");
        webUtil.sendKeys(By.id("YearOfBirth"), "1994");
        webUtil.sendKeys(By.id("PPSN"), "242599664");

    }
}
