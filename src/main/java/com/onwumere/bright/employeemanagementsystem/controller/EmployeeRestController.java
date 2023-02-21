package com.onwumere.bright.employeemanagementsystem.controller;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 06/12/2022
 */

import com.onwumere.bright.employeemanagementsystem.model.Employee;
import com.onwumere.bright.employeemanagementsystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{empId}")
    public ResponseEntity<List<Employee>> getEmployeesById(@PathVariable int empId){
        List<Employee> employee = employeeService.findEmployeeByEmpId(empId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByName(@PathVariable String name){
        List<Employee> employees = employeeService.findEmployeesByFNameOrLName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/county/{county}")
    public ResponseEntity<List<Employee>> getEmployeesByLocationCounty(@PathVariable String county){
        List<Employee> employees = employeeService.findEmployeesByCounty(county);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/zip/{zip}")
    public ResponseEntity<List<Employee>> getEmployeesByLocationZip(@PathVariable String zip){
        List<Employee> employees = employeeService.findEmployeesByZip(zip);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Employee>> getEmployeesByLocationState(@PathVariable String state){
        List<Employee> employees = employeeService.findEmployeesByState(state);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Employee>> getEmployeesByLocationCity(@PathVariable String city){
        List<Employee> employees = employeeService.findEmployeesByCity(city);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Employee>> getEmployeesByLocationRegion(@PathVariable String region){
        List<Employee> employees = employeeService.findEmployeesByRegion(region);
        return ResponseEntity.ok(employees);
    }


}
