package com.onwumere.bright.employeemanagementsystem.controller;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.github.javafaker.Faker;
import com.onwumere.bright.employeemanagementsystem.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public String getEmployee(Model model){
        Faker faker = new Faker();
        List<Employee> employees = List.of(
                new Employee(1L, faker.name().firstName(), faker.name().lastName(), LocalDate.of(1987, Month.OCTOBER, 30), new BigDecimal(103839)),
                new Employee(2L, faker.name().firstName(), faker.name().lastName(), LocalDate.of(1977, Month.NOVEMBER, 30), new BigDecimal(122513)),
                new Employee(3L, faker.name().firstName(), faker.name().lastName(), LocalDate.of(1993, Month.APRIL, 30), new BigDecimal(190587)),
                new Employee(4L, faker.name().firstName(), faker.name().lastName(), LocalDate.of(1972, Month.JUNE, 7), new BigDecimal(123209))
        );
        model.addAttribute("employees", employees);
        return "employee";
    }

}
