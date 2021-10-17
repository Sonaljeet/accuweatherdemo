package com.accuweather.validators;

import com.accuweather.common.CommonCode;
import org.openqa.selenium.WebDriver;

public class CommonValidators extends CommonCode {
    private static final WebDriver driver = CommonCode.driver;

    //Method to open the landing page.
    public static boolean loadPage() {
        try {
            String url = getPropertyValue("url");
            driver.get(url);
            return driver.getCurrentUrl().equalsIgnoreCase(url);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}