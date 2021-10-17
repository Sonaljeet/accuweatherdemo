package com.accuweather.common.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import org.json.simple.JSONObject;

public class ReadWriteDataInJson {

    //This method reads input as filename and path to the json value and return the value in String format
    public static String readDataInJsonFile(String filePath, String jsonPath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            Configuration conf = Configuration.builder()
                    .jsonProvider(new GsonJsonProvider())
                    .mappingProvider(new GsonMappingProvider())
                    .build();
            DocumentContext context = JsonPath.using(conf).parse(content);
            return context.read(jsonPath, String.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //This method reads input as filename and path to the json value and return the value in List format
    public static ArrayList readDoubleDataInJsonFile(String filePath, String jsonPath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            Configuration conf = Configuration.builder()
                    .jsonProvider(new GsonJsonProvider())
                    .mappingProvider(new GsonMappingProvider())
                    .build();
            DocumentContext context = JsonPath.using(conf).parse(content);
            return context.read(jsonPath, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Use this method to write data in json format in a json file.
     * Method takes filename, and key and value to be added in the json file.
     **/
    public static void writeDataInJsonFile(String fileName, String key, int value) {
        try {
            JSONObject obj = new JSONObject();
            obj.put(key, value);
            try (FileWriter file = new FileWriter(fileName)) {
                file.write(obj.toJSONString());
                file.flush();
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}