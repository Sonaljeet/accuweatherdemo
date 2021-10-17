package com.accuweather.validators;

import com.accuweather.common.CommonCode;
import com.accuweather.elements.AccuWeatherElements;

import static com.accuweather.common.utilities.ReadWriteDataInJson.writeDataInJsonFile;

//Class to validate all functionalities related to web page
public class AccuWeatherWebValidator extends CommonCode {
    private final static AccuWeatherElements obj = new AccuWeatherElements(driver);

    /**
     * Validate the temperature value in accuweather web page.
     * Return true if value exists
     * else return false
     **/
    public static boolean validateWebTemp() {
        try {
            String temp = obj.getCityTemp();
            return temp != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //This method add the temperature value of the city being displayed in the web page in json file
    public static boolean recordWebTempData(String city) {
        try {
            String s = obj.getCityTemp();
            String truncated = s.substring(0, s.length() - 2);
            writeDataInJsonFile(getPropertyValue("webdata"), city.toLowerCase(), Integer.parseInt(truncated));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}