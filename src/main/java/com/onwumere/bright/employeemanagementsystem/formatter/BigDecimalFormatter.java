package com.onwumere.bright.employeemanagementsystem.formatter;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Component
public class BigDecimalFormatter implements Formatter<BigDecimal> {
    @Override
    public BigDecimal parse(String text, Locale locale) {
        return null;
    }

    @Override
    public String print(BigDecimal object, Locale locale) {
        return NumberFormat.getCurrencyInstance(locale).format(object);
    }
}
