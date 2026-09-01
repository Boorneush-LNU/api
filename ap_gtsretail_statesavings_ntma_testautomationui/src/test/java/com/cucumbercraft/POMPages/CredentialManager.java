package com.cucumbercraft.POMPages;

import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.PropertyConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CredentialManager {

    private static final PropertyConfig config = ConfigFactory.create(PropertyConfig.class);
    private static final String CREDENTIALS_FILE = FrameworkConstants.getTxtCredentialFileLocation();
    private static final String DEFAULT_PASSWORD = config.getDefaultInitialPassword();
    private static final String DEFAULT_PASSWORDOTP = config.getDefaultInitialPasswordOTP();
    private static final String DEFAULT_PASSWORDNOTI = config.getDefaultInitialPasswordNoti();
    private static final String DEFAULT_EMAIL = config.getDefaultInitialEmail();

    // Reads credentials from the .txt file into a Map
    private static Map<String, String> readCredentials() {
        Map<String, String> credentials = new HashMap<>();
        File file = new File(CREDENTIALS_FILE);

        if (!file.exists()) {
            return credentials; // Return empty map if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    credentials.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials file: " + e.getMessage());
        }

        return credentials;
    }

    // Writes credentials from a Map to the .txt file
    private static void writeCredentials(Map<String, String> credentials) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE))) {
            for (Map.Entry<String, String> entry : credentials.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to credentials file: " + e.getMessage());
        }
    }

    public static String getCredential(String key, String defaultValue) {
        Map<String, String> credentials = readCredentials();
        return credentials.getOrDefault(key, defaultValue);
    }

    public static void saveCredential(String key, String value) {
        Map<String, String> credentials = readCredentials();
        credentials.put(key, value);
        writeCredentials(credentials);
    }

    public static String getOldPassword() {
        return getCredential("password", DEFAULT_PASSWORD);
    }

    public static String getOldPasswordOTP() {
        return getCredential("passwordOTP", DEFAULT_PASSWORDOTP);
    }



    public static String getOldPasswordNoti() {
        return getCredential("passwordNoti", DEFAULT_PASSWORDNOTI);
    }




    public static String generateNewPassword() {
        return "Dummy!" + RandomStringUtils.randomNumeric(5);
    }



    public static void saveNewPassword(String newPassword) {
        saveCredential("password", newPassword);
    }

    public static void saveNewPasswordNoti(String newPasswordNoti) {
        saveCredential("passwordNoti", newPasswordNoti);
    }


    public static void saveNewPasswordOTP(String newPasswordOTP) {

        saveCredential("passwordOTP", newPasswordOTP);
    }


    public static String getOldEmail() {
        return getCredential("email", DEFAULT_EMAIL);
    }

    public static String generateNewEmail() {
        return DEFAULT_EMAIL+RandomStringUtils.randomNumeric(2) + "@mailinator.com";
    }

    public static void saveNewEmail(String newEmail) {
        saveCredential("email", newEmail);
    }

//    public static void main(String[] args) {
////         Test password methods
//        String oldPassword = getOldPassword();
//        System.out.println("Old Password: " + oldPassword);
//
//        String newPassword = generateNewPassword();
//        System.out.println("Generated New Password: " + newPassword);
//        saveNewPassword(newPassword);
//
//        // Test email methods
//        String oldEmail = getOldEmail();
//        System.out.println("Old Email: " + oldEmail);
//
//        String newEmail = generateNewEmail();
//        System.out.println("Generated New Email: " + newEmail);
//        saveNewEmail(newEmail);
//
//        // Test PasswordOTP methods/ //Shan
//
//        String oldPasswordOTP = getOldPasswordOTP();
//        System.out.println("Old Password: " + oldPasswordOTP);
//
//        String newPasswordOTP = generateNewPassword();
//        System.out.println("Generated New Password: " + newPasswordOTP);
//        saveNewPasswordOTP(newPasswordOTP);
//
//        String oldPasswordNoti = getOldPassword();
//        System.out.println("Old Password: " + oldPasswordNoti);
//
//        String newPasswordNoti = generateNewPassword();
//        System.out.println("Generated New Password: " + newPasswordNoti);
//        saveNewPasswordNoti(newPasswordNoti);
//    }





}