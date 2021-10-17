package com.accuweather.common.helper;

import com.accuweather.common.CommonCode;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WebElementHelper extends CommonCode {
    private static final WebDriver driver = CommonCode.driver;

    //Method to wait for the element to gets displayed
    public static boolean waitForElement(WebElement element) {
        try {
            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            fluentWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //This method can be used to enter text in an applicable web element
    public static void enterText(WebElement element, String text) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().sendKeys(text).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to press ENTER key
    public static void pressEnterKey(WebElement element) {
        try {
            waitForElement(element);
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to click on an applicable web element
    public static void click(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}