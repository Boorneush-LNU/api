package com.cucumbercraft.framework;

/**
 * @author Saahil Sunil Gulve
 * Use this class to handle multiple exception
 * send any message which you want to print
 */
public class ExceptionUtils extends RuntimeException {
    public ExceptionUtils(String message) {

        super(message);
    }


}
