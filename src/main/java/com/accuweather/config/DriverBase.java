package com.accuweather.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.util.Collections;
import java.util.ResourceBundle;

//Base driver class to initiate browser session
public class DriverBase {
    private static final ResourceBundle rb = ResourceBundle.getBundle("env/common");
    private static final String browser = System.getProperty("browser");

    //Initiate browser session for chrome/edge based on value passed in the command.
    public static WebDriver initiateDriver() {
        WebDriver driver = null;
        try {
            switch (browser) {
                case "chrome":
                case "Chrome":
                    String path = System.getProperty("user.dir") + rb.getString("chromedriverpath");
//                    String crxPath = System.getProperty("user.dir") + rb.getString("chromecrx");
                    System.setProperty("webdriver.chrome.driver", path);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--no-proxy-server");
                    chromeOptions.addArguments("--disable-cache");
                    chromeOptions.addArguments("--disable-web-security");
//                    chromeOptions.addExtensions(new File(crxPath));
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "edge":
                case "Edge":
                    path = System.getProperty("user.dir") + rb.getString("edgedriverpath");
//                    crxPath = System.getProperty("user.dir") + rb.getString("edgecrx");
                    System.setProperty("webdriver.edge.driver", path);
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    edgeOptions.addArguments("--start-maximized");
                    edgeOptions.addArguments("--no-proxy-server");
                    edgeOptions.addArguments("--disable-cache");
                    edgeOptions.addArguments("--disable-web-security");
//                    edgeOptions.addExtensions(new File(crxPath));
                    driver = new EdgeDriver(edgeOptions);
                    break;
            }
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}