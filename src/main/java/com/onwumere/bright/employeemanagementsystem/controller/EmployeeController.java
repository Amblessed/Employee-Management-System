package com.onwumere.bright.employeemanagementsystem.controller;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.onwumere.bright.employeemanagementsystem.model.Employee;
import com.onwumere.bright.employeemanagementsystem.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/view/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("viewEmployee", employeeRepository.findById(id).get());
        return "view-employee";
    }

    @PostMapping("/save")
    public String createEmployee(@Valid Employee employee, Errors errors){
        if (!errors.hasErrors()){
            employeeRepository.save(employee);
            return "redirect:/employee"; //if everything is okay, go back to the home page
        }
        return "add-employee";  //stay on the add-employee page if there are errors
    }

    @GetMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "edit-employee";
    }

    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee){
        //get employee from database by id
        Employee existingEmployee = employeeRepository.findById(id).get();
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        employeeRepository.save(existingEmployee);
        return "redirect:/employee";
    }


}
