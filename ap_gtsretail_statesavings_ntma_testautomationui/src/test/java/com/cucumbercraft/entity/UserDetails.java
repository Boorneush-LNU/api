package com.cucumbercraft.entity;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.HeadersForAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;


@Builder
@Getter
@ToString
public class UserDetails {
    private final Map<String, Object> userDetails;

    public static UserDetails getUserDetails(String username) {
        String baseUri= FrameworkConstants.baseUriMyAccountFEAPILinkFetch();
        String endpoint="/api/v5/account/get-user-details";
        Map<String, String> headerMap = HeadersForAPI.getMyAccountFEHeaders();

        Response response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .headers(headerMap)
                .baseUri(baseUri+endpoint)
                .queryParam("username", username)
                .get();

        return UserDetails.builder()
                .userDetails(response.jsonPath().getMap(""))
                .build();
    }


}
