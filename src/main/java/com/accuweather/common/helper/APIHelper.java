package com.accuweather.common.helper;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import static com.accuweather.common.CommonCode.getPropertyValue;

public class APIHelper {

    //Method to create json file and write the API response in it
    public static boolean writeResponseToJsonFile(String response) {
        try {
            boolean flag;
            String fileName = getPropertyValue("response");
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(response);
            buffer.close();
            flag = file.length() > 0;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Implement the GET logic.
     * Takes URI value as String, and parameters to pass as Map<K,V>.
     * Returns the response in json format.
     **/
    public static Response get(String uri, Map<String, String> m) {
        try {
            Response response = RestAssured.given()
                    .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                    .pathParams(m)
                    .get(uri);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}