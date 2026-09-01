package com.cucumbercraft.POMPages.RepayReinvest;

import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;
import java.util.function.BiConsumer;

public class ChooseOptionPage  {

    private final WebDriverUtil webUtil;
    private final WebDriver driver;
    public final By btnReinvest = By.xpath("//*[contains(@id,'block_Reinvest')]");
    public final By btnCashIn = By.xpath("//*[text()='Cash In']/parent::div");
    public final By btnReinvestCashIn = By.xpath("//div[contains(@id,'ReInvestCashInBlock')]/div[3]");

    public ChooseOptionPage(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);

    }

    private final BiConsumer<WebDriverUtil, By> clickOption = WebDriverUtil::click;
    Map<String, By> buttonMap = Map.of(
            "Reinvest", btnReinvest,
            "Reinvest-Repay", btnReinvestCashIn,
            "Cash-In", btnCashIn
    );

    public void clickInvestmentType(String optionType) {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("choose-option"));
        By btnOption = buttonMap.get(optionType);
        clickOption.accept(webUtil, btnOption);

    }


}
