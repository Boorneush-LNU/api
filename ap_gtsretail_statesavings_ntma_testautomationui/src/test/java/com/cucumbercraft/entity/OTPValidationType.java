package com.cucumbercraft.entity;

import lombok.Getter;

@Getter
public enum OTPValidationType {


    BLANK("Please enter your verification code.", "Blank Verification Code"),
    LESS("Please enter the six-digit verification code sent to your registered mobile number.", "Less than six digits"),
    INVALID("Verification code is invalid or has expired. Please try again.", "Wrong Verification Code"),
    EXPIRED("Verification code is invalid or has expired. Please try again.", "Expired Verification Code");

    private final String errorMessage;
    private final String scenario;

    OTPValidationType(String errorMessage, String scenario) {
        this.errorMessage = errorMessage;
        this.scenario = scenario;
    }



}
