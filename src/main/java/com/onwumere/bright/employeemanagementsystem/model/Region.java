package com.onwumere.bright.employeemanagementsystem.model;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17/02/2023
 */


public enum Region {

    Midwest("Midwest"),
    Northeast("Northeast"),
    South("South"),
    West("West");

    private final String displayValue;

    private Region(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
