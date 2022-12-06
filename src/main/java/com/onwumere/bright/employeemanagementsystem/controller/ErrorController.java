package com.onwumere.bright.employeemanagementsystem.controller;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 05/12/2022
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {

    @GetMapping
    public String getErrorPage(){
        return "error";
    }
}
