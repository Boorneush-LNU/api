package com.cucumbercraft.framework;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import lombok.extern.log4j.Log4j2;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

@Log4j2
public final class FrameworkLogger {


    private FrameworkLogger() {
        // Private constructor to prevent instantiation
    }

    private static final Map<LogType, Consumer<String>> LOG_MAP = new EnumMap<>(LogType.class);

    static {
        LOG_MAP.put(LogType.PASS, FrameworkLogger::pass);
        LOG_MAP.put(LogType.FAIL, FrameworkLogger::fail);
        LOG_MAP.put(LogType.SKIP, FrameworkLogger::skip);
        LOG_MAP.put(LogType.INFO, FrameworkLogger::info);
        LOG_MAP.put(LogType.CONSOLE, log::info);
        LOG_MAP.put(LogType.EXTENT_AND_CONSOLE_PASS, FrameworkLogger::extentAndConsolePass);
        LOG_MAP.put(LogType.EXTENT_AND_CONSOLE_FAIL, FrameworkLogger::extentAndConsoleFail);
    }

    /**
     * Logs a message of the specified type to the appropriate destinations.
     *
     * @param type    The type of log message (e.g., PASS, FAIL, INFO).
     * @param message The message to be logged.
     */
    public static void log(LogType type, String message) {
        if (LOG_MAP.containsKey(type)) {
            LOG_MAP.get(type).accept(message);
        } else {
            log.warn("Attempted to log with an unknown LogType: {}", type);
        }
    }

    /**
     * Logs an informational message to both Extent Report and the console.
     *
     * @param message The informational message to log.
     */
    private static void info(String message) {
        log.info(message);
        ExtentCucumberAdapter.getCurrentStep().info(message);
    }

    /**
     * Logs a successful step message to both Extent Report and the console.
     *
     * @param message The success message to log.
     */
    private static void pass(String message) {
        log.info("PASS: {}", message);
    }

    /**
     * Logs a failed step message to both Extent Report and the console.
     *
     * @param message The failure message to log.
     */
    private static void fail(String message) {
        log.error("FAIL: {}", message);
    }



    /**
     * Logs a skipped step message to both Extent Report and the console.
     *
     * @param message The skipped message to log.
     */
    private static void skip(String message) {
        ExtentCucumberAdapter.getCurrentStep().skip("SKIP: " + message);
        log.warn("SKIP: {}", message);
    }

    /**
     * Logs a message only to the console.
     *
     * @param message The message to log to the console.
     */
    private static void console(String message) {
        System.out.println(message);
    }

    /**
     * Logs a successful step message to both Extent Report and the console.
     *
     * @param message The success message to log.
     */
    private static void extentAndConsolePass(String message) {
        ExtentCucumberAdapter.getCurrentStep().pass("PASS: " + message);
        pass(message);
    }

    /**
     * Logs a failed step message to both Extent Report and the console.
     *
     * @param message The failure message to log.
     */
    private static void extentAndConsoleFail(String message) {
        ExtentCucumberAdapter.getCurrentStep().fail("FAIL: " + message);
        fail(message);
    }
}