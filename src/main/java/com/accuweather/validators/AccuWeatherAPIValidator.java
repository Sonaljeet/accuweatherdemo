package com.accuweather.validators;

import io.restassured.response.Response;

import java.util.Map;

import static com.accuweather.common.CommonCode.getPropertyValue;
import static com.accuweather.common.utilities.ReadWriteDataInJson.readDataInJsonFile;
import static com.accuweather.common.helper.APIHelper.*;

public class AccuWeatherAPIValidator {
    private static final Response response = getResponse();

    /**
     * Method to validate the status code of API response
     * Returns true if the api response code is 200
     * else will return false
     **/
    public static boolean validateResponseStatusCode() {
        try {
            int statusCode = response.statusCode();
            boolean flag;
            flag = statusCode == 200;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Method to fetch response by making accuweather API call
    public static Response getResponse() {
        try {
            String fileName = getPropertyValue("testdata");
            return get(readDataInJsonFile(fileName, "$.api.uri"),
                    Map.of("id", readDataInJsonFile(fileName, "$.city.id"), "appid", readDataInJsonFile(fileName, "$.city.apiKey"),
                            "units", readDataInJsonFile(fileName, "$.city.units")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //This method validate if there is any response retrieved from the server
    public static boolean validateResponse() {
        try {
            boolean flag;
            flag = response != null;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Method to write accuweather API response in json file
    public static boolean writeAPIResponseInJsonFile() {
        try {
            return writeResponseToJsonFile(response.asString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}