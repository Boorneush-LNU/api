package com.cucumbercraft.framework;

/**
 * Enumeration to represent the Environment Name to be used for execution
 *
 * @author Saahil Sunil Gulve
 */
public enum EnvironmentName {            // Automation tools.
    UAT("UAT"),
    ALTQA("ALTQA"),
    PREPROD("PREPROD"),
    PROD("PROD"),
    PILOT("PILOT"),
    DEV("DEV"),
    //below to be removed
    K13_ALTUAT("K13_ALTUAT"),
    K13_PREPROD("K13_PREPROD");


    private final String value;

    EnvironmentName(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }
}