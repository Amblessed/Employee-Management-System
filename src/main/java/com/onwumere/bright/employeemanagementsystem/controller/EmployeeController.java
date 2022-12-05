package com.onwumere.bright.employeemanagementsystem.controller;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.onwumere.bright.employeemanagementsystem.model.Employee;
import com.onwumere.bright.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @ModelAttribute("employees")   //This method would run before any handler method
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @ModelAttribute("employee")
    public Employee getEmployee(){
        return new Employee();
    }

    @GetMapping
    public String showEmployees(){
        //List<Employee> employees = RandomEmployee.getRandomEmployees(8);
        //model.addAttribute("employees", employees);
        return "employee";   //this means show a view called
    }

    @GetMapping("/add")
    public String displayEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employee/add";
    }


}
