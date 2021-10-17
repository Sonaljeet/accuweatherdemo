package com.accuweather.steps;

import com.accuweather.common.CommonCode;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends CommonCode {

    /**
     * This method will invoke after every step mentioned in the feature file.
     * Method is applicable for scenarios related to Web application.
     **/
    @AfterStep("@Web")
    public void addScreenshotAfterFailedStep(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}