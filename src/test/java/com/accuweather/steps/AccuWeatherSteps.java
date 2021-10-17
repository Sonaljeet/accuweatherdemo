package com.accuweather.steps;

import com.accuweather.common.CommonCode;
import com.accuweather.elements.AccuWeatherElements;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.accuweather.validators.AccuWeatherWebValidator.recordWebTempData;
import static com.accuweather.validators.AccuWeatherWebValidator.validateWebTemp;
import static com.accuweather.validators.CommonValidators.loadPage;
import static org.junit.Assert.assertTrue;

//Step Definition class for the steps mentioned in the feature file
public class AccuWeatherSteps extends CommonCode {
    private final WebDriver driver = CommonCode.driver;
    private final AccuWeatherElements obj = new AccuWeatherElements(driver);

    @Given("^user is on accuweather landing page$")
    public void userOnLandingPage() {
        try {
            assertTrue("Page not loaded", loadPage());
            ExtentCucumberAdapter.addTestStepLog("**INFO:AccuWeather page opened.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:AccuWeather page not loaded properly.**");
        }
    }

    @When("^user enters city as \"([^\"]*)\"$")
    public void enterCity(String city) {
        try {
            assertTrue("City not entered", obj.enterCity(city));
            ExtentCucumberAdapter.addTestStepLog("**INFO:City entered successfully.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:Issue while entering city.**");
        }
    }

    @And("^user search for the temperature$")
    public void userSearchForCity() {
        try {
            assertTrue("Temperature not searched", obj.loadTempOfCity());
            ExtentCucumberAdapter.addTestStepLog("**INFO:Temperature searched successfully.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:Issue while searching temperature.**");
        }
    }

    @Then("^temperature of the city gets displayed$")
    public void validateTemp() {
        try {
            assertTrue("Temperature value not correct", validateWebTemp());
            ExtentCucumberAdapter.addTestStepLog("**INFO:Temperature value validated successfully.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:Temperature value is not correct.**");
        }
    }

    @And("^user records the temperature of \"([^\"]*)\"$")
    public void recordTemp(String city) {
        try {
            assertTrue("Value not recorded.", recordWebTempData(city));
            ExtentCucumberAdapter.addTestStepLog("**INFO:Successfully recorded temperature value.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:Issue while recording temperature value.**");
        }
    }
}