package com.accuweather.elements;

import com.accuweather.common.CommonCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.accuweather.common.helper.WebElementHelper.*;

//Class to find all web elements required for validation of scenarios in the feature file
public class AccuWeatherElements extends CommonCode {

    public AccuWeatherElements(WebDriver driver) {
        CommonCode.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locating elements
    @FindBy(className = "search-input")
    private WebElement searchBox;
    @FindBy(partialLinkText = "Bengaluru,")
    private WebElement cityLink;
    @FindBy(className = "temp")
    private WebElement temperature;

    //Method to implement the entering city functionality in the search box of web page
    public boolean enterCity(String city) {
        try {
            boolean flag;
            if (waitForElement(searchBox)) {
                enterText(searchBox, city);
                flag = true;
            } else flag = false;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //This method search for the temperature of the entered city
    public boolean loadTempOfCity() {
        try {
            boolean flag;
            pressEnterKey(searchBox);
            if (waitForElement(cityLink)) {
                click(cityLink);
                flag = true;
            } else flag = false;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //This method returns the temperature value of the city in String format
    public String getCityTemp() {
        try {
            String temp;
            if (waitForElement(temperature)) {
                temp = temperature.getText();
            } else temp = null;
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}