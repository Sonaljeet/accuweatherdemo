package com.accuweather.common;

import com.accuweather.config.DriverBase;
import org.openqa.selenium.WebDriver;

import java.util.ResourceBundle;

public class CommonCode extends DriverBase {
    protected static WebDriver driver = getDriver();

    //This method returns the driver instance from the driver base class.
    public static WebDriver getDriver() {
        try {
            return initiateDriver();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Use this method to quit all open browser sessions.
    public static void quit() {
        try {
            driver.quit();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method used to fetch the value from properties file.
     * It takes the key as the input parameter.
     */
    public static String getPropertyValue(String key) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("env/common");
            return rb.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}