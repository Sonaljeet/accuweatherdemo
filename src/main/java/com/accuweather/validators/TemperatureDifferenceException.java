package com.accuweather.validators;

//Custom Exception to be thrown when there is variance in temperature
public class TemperatureDifferenceException extends Exception {
    public TemperatureDifferenceException(String s) {
        super(s);
    }
}