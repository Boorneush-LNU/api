package com.cucumbercraft.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton class that encapsulates the user settings specified in the
 * properties file of the framework
 *
 * @author Cognizant
 */
public class Settings {

    // Single shared instance — Environment is a test-run-wide value set by @BeforeTest
    // (TestNG thread) and read by Cucumber step-def threads. Using ThreadLocal means
    // each thread gets its own fresh copy with no Environment set, causing null paths.
    private static final Properties properties = loadFromPropertiesFile();

//    private static final ThreadLocal<Properties> threadLocalProperties =
//            ThreadLocal.withInitial(() -> loadFromPropertiesFile());

    static Logger log = LogManager.getLogger(Settings.class);

    private Settings() {
        // To prevent external instantiation of this class
    }

    public static Properties getInstance() {
        return properties;   // ← all threads share the same instance
    }

    // No-op kept for backward compatibility (called in CukeHooks @After)
    public static void removeInstance() {
        // nothing to remove — shared instance is intentional
    }

    private static Properties loadFromPropertiesFile() {
        Properties properties = new Properties();
        String relativePath = new File(System.getProperty("user.dir"))
                .getAbsolutePath();
        relativePath = relativePath + Util.getFileSeparator() + "src"
                + Util.getFileSeparator() + "test" + Util.getFileSeparator()
                + "resources";

        try {
            properties.load(new FileInputStream(relativePath
                    + Util.getFileSeparator() + "Global Settings.properties"));
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}