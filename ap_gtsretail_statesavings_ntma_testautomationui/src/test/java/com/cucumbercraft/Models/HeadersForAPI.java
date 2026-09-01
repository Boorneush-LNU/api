package com.cucumbercraft.Models;

import java.util.HashMap;
import java.util.Map;

public class HeadersForAPI {

    public static Map<String, String> getTrustXHandlerHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Ocp-Apim-Subscription-Key", FrameworkConstants.subKeyTrustXHandler());
        return map;
    }

    public static Map<String, String> getMyAccountFEHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Ocp-Apim-Subscription-Key", FrameworkConstants.subKeyMyAccountFE());
        map.put("X-StateSavings-ApiKey", FrameworkConstants.apiKeyMyAccountFE());
        return map;
    }

    public static Map<String, String> getMyAccountTestDataHeaders() {
        Map<String, String> map = new HashMap<String, String>();
//        map.put("Ocp-Apim-Subscription-Key", FrameworkConstants.subKeyMyAccountFE());
        map.put("X-StateSavings-ApiKey", "b4ce1f32-d1ae-4639-ba3b-28dcbc824cc0");
        return map;
    }

    public static Map<String, String> getMyAccountTrialFEHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Ocp-Apim-Subscription-Key", "8de68fd7a9354781aa1005d998e733e8");
        map.put("X-StateSavings-ApiKey","0bd65072-1ef5-4f5a-9714-0cbc6263225e" );
        return map;
    }



}
