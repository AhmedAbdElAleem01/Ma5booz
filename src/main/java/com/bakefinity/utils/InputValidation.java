package com.bakefinity.utils;

public class InputValidation {
    public static boolean validatePhone(String phoneNumber) {
        String phonePattern = "^01[0-2,5]\\d{8}$";
        return phoneNumber.matches(phonePattern);
    }

    public static boolean validateName(String name) {
        String namePattern = "^[A-Za-z]+(\\s[A-Za-z]+)*$";
        return name.matches(namePattern);
    }

    public static boolean validatePassword(String password) { // at least 5 chars
        String passwordPattern = "^[A-Za-z\\d@#_.$%^&+=!]{5,}$";
        return password.matches(passwordPattern);
    }

    public static boolean validateCityStreet(String name) {
        String namePattern = "^[A-Za-z]+([\\s-][A-Za-z]+)*$";
        return name.matches(namePattern);
    }
}