package com.accuweather.runner;

import com.accuweather.common.CommonCode;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
        glue = "com.accuweather.steps",
        tags = "@API",
        plugin = {"pretty", "html:target/cucumber",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
//Runner class to trigger the scenarios mentioned in the feature file
public class Runner {

    //Runs after class and quit all active browser session.
    @AfterClass
    public static void quit() {
        CommonCode.quit();
    }
}