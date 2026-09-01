package com.cucumbercraft.framework;


import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.HeadersForAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class HoldingHelper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String BASE_URI = FrameworkConstants.baseUriMyAccountFEAPILinkFetch();

    /**
     * Fetch portfolio list for a given username
     *
     * @param username User email/username
     * @return Response object containing API response
     */
    public static Response getPortfolioList(String username) {

        Response response = RestAssured
                .given().relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .headers(HeadersForAPI.getMyAccountFEHeaders())
                .queryParam("username", username)
                .queryParam("holdingType", "all")
                .queryParam("includeMatured", true)
                .queryParam("productId", 0)
                .when()
                .get("api/v5/portfolio/list")
                .then().log().all()
                .extract()
                .response();

        return response;
    }

    /**
     * Fetch portfolio list for a given username
     *
     * @param username User email/username
     * @return Response object containing API response
     */
    public static Response getHoldingDetails(String username, String holdingId) {

        Response response = RestAssured
                .given().relaxedHTTPSValidation()
                .baseUri(BASE_URI)
                .headers(HeadersForAPI.getMyAccountFEHeaders())
                .queryParam("username", username)
                .queryParam("holdingId", holdingId)
                .when()
                .get("api/v5/holding/details")
                .then().log().all()
                .extract()
                .response();

        return response;
    }

    public static String getEligibleHoldingId(Response response) {

        try {
            JsonNode holdings = OBJECT_MAPPER.readTree(response.asString());

            return StreamSupport.stream(holdings.spliterator(), false)
                    .filter(holding ->
                            holding.path("isRepayable").asBoolean(false)
                                    && holding.path("isReinvestible").asBoolean(false))
                    .map(holding -> holding.path("holdingId").asText())
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "No holding found with isRepayable=true and isReinvestible=true"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to extract eligible holdingId", e);
        }
    }

        /**
         * Extracts bond ranges in the format:
         * rangeFrom-rangeTo
         *
         * @param response API Response
         * @return List of formatted bond ranges
         */
        public static List<String> getBondRanges(Response response) {

            List<Map<String, Object>> bondRanges =
                    response.jsonPath().getList("bondRanges");

            return bondRanges.stream()
                    .filter(bond -> Boolean.TRUE.equals(bond.get("isRepayable")))
                    .map(bond -> bond.get("rangeFrom") + " - " + bond.get("rangeTo"))
                    .collect(Collectors.toList());

        }
}


