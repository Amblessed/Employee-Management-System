package com.onwumere.bright.employeemanagementsystem.controller;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.onwumere.bright.employeemanagementsystem.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utilities.RandomEmployee;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public String getEmployee(Model model){
        List<Employee> employees = RandomEmployee.getRandomEmployees(8);
        model.addAttribute("employees", employees);
        return "employee";
    }


}
