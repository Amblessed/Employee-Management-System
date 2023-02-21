package com.onwumere.bright.employeemanagementsystem.repository;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.onwumere.bright.employeemanagementsystem.model.Employee;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;


/*ApplicationRunner runs immediately after some minutes spring is started*/
@Component
public class EmployeeDataLoader implements ApplicationRunner {

    private final EmployeeRepository employeeRepository;


    public EmployeeDataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override  //To have some initial data inside the database
    public void run(ApplicationArguments args) {
        /*if (employeeRepository.count() == 0) {
            List<Employee> people = getRandomEmployees(20);
            employeeRepository.saveAll(people);
        }*/
    }

}
