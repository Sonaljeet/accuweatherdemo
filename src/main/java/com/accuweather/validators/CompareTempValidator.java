package com.accuweather.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.accuweather.common.CommonCode.getPropertyValue;
import static com.accuweather.common.utilities.ReadWriteDataInJson.readDoubleDataInJsonFile;

public class CompareTempValidator {

    /**
     * Method to return the temperature value in accuweather web page for a city.
     * Read data from the WebData.json file where temperature is already recorded.
     **/
    public static ArrayList<Double> getWebTempValues(String city) {
        try {
            return readDoubleDataInJsonFile(getPropertyValue("webdata"), "$.." + city.toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //This method reads the Response.json file and fetch the temperature value based on the respective jsonpath
    public static ArrayList<Double> getAPITempValues() {
        try {
            return readDoubleDataInJsonFile(getPropertyValue("response"), "$..temp");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to compare the temperature value in the web page with that of value returned from server.
     * It will round the web and api value using Math.round method and then compare both values.
     * Return true if both matches else return false.
     **/
    public static boolean compareTempValues(String city) {
        try {
            List<Long> web = getWebTempValues(city).stream().map(Math::round).collect(Collectors.toList());
            List<Long> api = getAPITempValues().stream().map(Math::round).collect(Collectors.toList());
            return web.equals(api);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to implement temperature variance logic.
     * It will calculate the difference of web and api temperature value
     * And then check if the variance is in the specified range.
     * Returns true if it is within specified range else return false.
     **/
    public static boolean validateVariance(String city) {
        try {
            List<Double> web = getWebTempValues(city);
            List<Double> api = getAPITempValues();
            List<Double> variance = IntStream.range(0, web.size()).mapToObj(i -> web.get(i) - api.get(i)).collect(Collectors.toList());
            boolean flag = false;
            for (Double d : variance) {
                if (d > 0 && d < 1) flag = true;
                else throw new TemperatureDifferenceException("Temperature difference not within specified range");
            }
            return flag;
        } catch (TemperatureDifferenceException e) {
            e.printStackTrace();
            return false;
        }
    }
}