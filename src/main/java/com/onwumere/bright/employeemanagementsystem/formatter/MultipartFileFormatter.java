package com.onwumere.bright.employeemanagementsystem.formatter;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Locale;


//To


@Component
public class MultipartFileFormatter implements Formatter<MultipartFile> {
    @Override
    public MultipartFile parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(MultipartFile object, Locale locale) {
        return object.getOriginalFilename();
    }
}
