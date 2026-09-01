package com.cucumbercraft.Models;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:Global Settings.properties"})
public interface PropertyConfig extends Config {   // Owners library

//    @Key("environment")
//    @DefaultValue("uat")  // Changed from UAT
//    String env();

//    @Key("statesavings.${environment}.url")
//    String getUrl();

//    @Key("statesavings.${environment}.baseuri") String getbaseURI();

//    Endpoint variables

    @Key("OTPEndPoint") String getOTPEndpoint();

    @Key("MyAccountFE_pingEndpoint") String getMyAccountFEPingEndpoint();

    @Key("TrustXHandler_HealthEndpoint") String getTrustXHandlerHealthEndpoint();

    @Key("MyAccountFE_pingEndpoint_queryParam") String getPingEndpointQueryParams();

    @Key("TrustXHandler_HealthEndpoint_queryParam") String getTrustXHandlerHealthEndpointQueryParams();

//    Endpoint variables

    @Key("TestDataSheet.PurchaseJourneys") String  getBuyNowSheetName();

    @Key("TestDataSheet.Regression") String getSheetName();

//    @Key("EnableDownload") String enableDownload();

    @Key("TestDataSheet.ErrorScenarios") String getErrorSheetName();

    @Key("TestDataSheet.DigitalRegistrationContent") String getDigitalRegistrationSheetName();

    @Key("credential.filename.${environment}") String getCredentialFileName();

    @Key("initial.password") String getDefaultInitialPassword();

    @Key("initial.passwordOTP") String getDefaultInitialPasswordOTP();

    @Key("initial.passwordNoti") String getDefaultInitialPasswordNoti();

    @Key("initial.email") String getDefaultInitialEmail();

    // Saahil shared the below

    @Key("SSAdminUser_Email") String getSSAdminUser_Email();

    @Key("SSAdminUser_Pwd") String getSSAdminUser_Pwd();

    @Key("PBAdminUser_Email") String getPBAdminUser_Email();

    @Key("PBAdminUser_Pwd") String getPBAdminUser_Pwd();

    @Key("pbWebAdmin.${Environment}.url") String getPbWebAdminUrl();



}
