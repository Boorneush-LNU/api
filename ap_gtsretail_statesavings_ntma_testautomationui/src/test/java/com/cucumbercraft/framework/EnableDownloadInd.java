package com.cucumbercraft.framework;

/**
 * Enumeration to represent the Environment Name to be used for execution
 *
 * @author Saahil Sunil Gulve
 */
public enum EnableDownloadInd {            // Automation tools.
    Yes("Yes"),
    No("No"),
    NA("NA");


    private final String value;

    EnableDownloadInd(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }
}