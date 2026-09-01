package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.Models.PropertyConfig;
import lombok.Data;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;


@Data
public class Context{


    WebDriver driver=DriverManager.getWebDriver();
    PropertyConfig config= ConfigFactory.create(PropertyConfig.class);
    TestData data;
    private PurchaseModel purchaseModel;
    private SignInPg signInPg;





}
