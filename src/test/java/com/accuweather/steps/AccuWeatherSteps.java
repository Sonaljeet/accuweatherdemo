package com.accuweather.steps;

import com.accuweather.common.CommonCode;
import com.accuweather.elements.AccuWeatherElements;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.accuweather.validators.AccuWeatherAPIValidator.*;
import static com.accuweather.validators.AccuWeatherWebValidator.recordWebTempData;
import static com.accuweather.validators.AccuWeatherWebValidator.validateWebTemp;
import static com.accuweather.validators.CommonValidators.loadPage;
import static com.accuweather.validators.CompareTempValidator.*;
import static com.accuweather.validators.CompareTempValidator.validateVariance;
import static org.junit.Assert.assertNotEquals;
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

    @Given("^user create the uri and make the API call$")
    public void createAndCallAPI() {
        try {
            assertTrue("Issue in response.", validateResponse());
            ExtentCucumberAdapter.addTestStepLog("**INFO:User successfully made the API call.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:Issue while making the API call.**");
        }
    }

    @Then("^the status of the response should be 200$")
    public void validateStatusCode() {
        try {
            assertTrue("Status code is not 200", validateResponseStatusCode());
            ExtentCucumberAdapter.addTestStepLog("**INFO:Response status code is 200.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:Issue while validating the status code.**");
        }
    }

    @And("^user create file and write response in it$")
    public void writeResponseInFile() {
        try {
            assertTrue("Response not written.", writeAPIResponseInJsonFile());
            ExtentCucumberAdapter.addTestStepLog("**INFO:Successfully recorded response in json file.**");
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("**INFO:Issue while writing the response in file.**");
        }
    }

    @Given("^user read temperature values of \"([^\"]*)\"$")
    public void readTemperatureValues(String city) {
        assertNotEquals("Temperature value in web is not correct.", null, getWebTempValues(city));
        assertNotEquals("Temperature value returned from API is not correct.", null, getAPITempValues());
        ExtentCucumberAdapter.addTestStepLog("**INFO:Read both web and API temperature value.**");
    }

    @When("^user compare both temperature values of \"([^\"]*)\"$")
    public void compareBothTemp(String city) {
        assertTrue("Web and API Temperature values are not matching.", compareTempValues(city));
        ExtentCucumberAdapter.addTestStepLog("**INFO:Temperature value in both web and api are same.**");
    }

    @Then("^temperature difference should be within range for \"([^\"]*)\"$")
    public void validateTemperatureDiffRange(String city) {
        assertTrue("Temperature difference is not within range.", validateVariance(city));
        ExtentCucumberAdapter.addTestStepLog("**INFO:Temperature difference between web and api are within range.**");
    }
}