package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.HeadersForAPI;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.Models.PropertyConfig;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigCache;
import lombok.extern.log4j.Log4j2;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Log4j2

public class PingEndpoint {
    PropertyConfig config = ConfigCache.getOrCreate(PropertyConfig.class);
    Type type = TypeToken.getParameterized(Map.class, String.class, String.class).getType();


    @Given("Launch the PingEndPoint and validate")
    public void verifyAllPingEndpointServicesAreActive() throws FileNotFoundException, ParseException {
        Map<String, String> headerMap = HeadersForAPI.getMyAccountFEHeaders();

        Map<String, Boolean> serviceActiveMap = getHealthDetails(headerMap, FrameworkConstants.baseUriMyAccountFEAPILinkFetch(),
                config.getPingEndpointQueryParams(),
                config.getMyAccountFEPingEndpoint(),
                "service",
                "active"
        );

//        Map<String, Boolean> serviceActiveMap1 = getHealthDetailsPingEndpoint_1(); // Uncomment this line if we want to check the Active & non active endpoints explicitly.
        List<String> inactiveServices = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : serviceActiveMap.entrySet()) {
            String serviceName = entry.getKey();
            Boolean isActive = entry.getValue();

            if (Boolean.FALSE.equals(isActive)) {
                inactiveServices.add(serviceName);
            }
        }

        if (!inactiveServices.isEmpty()) {
            String failureMessage = " " + inactiveServices;
            ExtentCucumberAdapter.addTestStepLog("These Endpoints are not active " + failureMessage);
            throw new RuntimeException("These Endpoints are not active " + failureMessage);
        } else {
            System.out.println("All Pass " + serviceActiveMap.size() + " services reported active in Ping endpoint.");
        }
    }


    @Given("Launch the TrustXHandler and validate")
    public void verifyAllTrustXhandlerServicesAreActive() throws FileNotFoundException, ParseException {
        Map<String, String> headerMap = HeadersForAPI.getTrustXHandlerHeaders();

        Map<String, Boolean> serviceActiveMap = getHealthDetails(
                headerMap,
                FrameworkConstants.baseUriTrustXHandlerAPILinkFetch(),
                config.getTrustXHandlerHealthEndpointQueryParams(),
                config.getTrustXHandlerHealthEndpoint(),
                "name",
                "status"
        );

        List<String> inactiveServices = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : serviceActiveMap.entrySet()) {
            String serviceName = entry.getKey();
            Boolean isActive = entry.getValue();

            if (Boolean.FALSE.equals(isActive)) {
                inactiveServices.add(serviceName);
            }
        }

        if (!inactiveServices.isEmpty()) {
            String failureMessage = " " + inactiveServices;
            ExtentCucumberAdapter.addTestStepLog("These Endpoints are not active " + failureMessage);
        } else {
            System.out.println("All Pass " + serviceActiveMap.size() + " services reported active in TrustXHandler endpoint.");
        }
    }

    // To check the true/false explicitly. Use this for Custom check(Ping Endpoint)
//    public Map<String, Boolean> getHealthDetailsPingEndpoint_1 (){
//        Map<String, Boolean> serviceActiveMap1 = new HashMap<>();
//        serviceActiveMap1.put("sswdb",false);
//        serviceActiveMap1.put("scdquery",true);
//        serviceActiveMap1.put("inputerepayapi",false);
//        serviceActiveMap1.put("fexcoapi",true);
//        serviceActiveMap1.put("ipsmvs",true);
//        serviceActiveMap1.put("iban",true);
//        serviceActiveMap1.put("authy",true);
//        return  serviceActiveMap1;
//    }


// To check the true/false explicitly. Use this for Custom check(TrustX Endpoint)
//    public Map<String, Boolean> getHealthDetailsTrustXHandler1 (){
//        Map<String, Boolean> serviceActiveMap = new HashMap<>();
//        serviceActiveMap.put("sswMyAccountAPI",false);
//        serviceActiveMap.put("TrustXAPI",true);
//        serviceActiveMap.put("ServiceBusQueue",false);
//        serviceActiveMap.put("StorageAccountContainer",true);
//        serviceActiveMap.put("StorageAccountTable",true);
//        serviceActiveMap.put("PBWFBackOfficeAPI",true);
//        serviceActiveMap.put("ForceCacheRefresh",true);
//        return  serviceActiveMap;
//    }


    // Accepts all variable parts (headers, baseurl, query params, endpoint, JSON field names).
    public Map<String, Boolean> getHealthDetails(Map<String, String> headerMap, String baseUri, String rawQueryParams,
            String endpoint,
            String serviceFieldName,
            String statusFieldName ) throws FileNotFoundException, ParseException {

        String resp = null;
        Map<String, Boolean> serviceActiveMap = new HashMap<>();

        Map<String, Object> queryParamsMap = new HashMap<>();
        if (rawQueryParams != null && !rawQueryParams.isEmpty()) {
            String delimiter = rawQueryParams.contains("&") ? "&" : ",";
            String[] pairs = rawQueryParams.split(delimiter);
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    queryParamsMap.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            Response response = RestAssured.given()
                    .relaxedHTTPSValidation()
                    .headers(headerMap)
                    .baseUri(baseUri)
                    .queryParams(queryParamsMap)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                resp = response.getBody().asPrettyString();

                log.info("API Response: " + resp);
                serviceActiveMap = extractServiceActiveMapEndpoint(resp, serviceFieldName, statusFieldName);
                log.info("Service-Active Map: " + serviceActiveMap);
                break;
            } else {
                throw new ExceptionUtils("Endpoint API call failed :" + response.getStatusCode());
            }
        }
        return serviceActiveMap;
    }


    public static Map<String, Boolean> extractServiceActiveMapEndpoint(String jsonResponse, String serviceFieldName, String statusFieldName) {
        Map<String, Boolean> serviceActiveMap = new HashMap<>();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        JsonArray availabilityResults = jsonObject.getAsJsonArray("availabilityResults");

        for (int i = 0; i < availabilityResults.size(); i++) {
            JsonObject result = availabilityResults.get(i).getAsJsonObject();
            String service = result.get(serviceFieldName).getAsString();
            boolean active = result.get(statusFieldName).getAsBoolean();
            serviceActiveMap.put(service, active);
        }
        return serviceActiveMap;
    }


}
