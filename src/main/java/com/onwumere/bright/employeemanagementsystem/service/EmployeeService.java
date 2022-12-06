package com.onwumere.bright.employeemanagementsystem.service;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 06/12/2022
 */

import com.onwumere.bright.employeemanagementsystem.model.Employee;
import com.onwumere.bright.employeemanagementsystem.repository.EmployeeRepository;
import com.onwumere.bright.employeemanagementsystem.repository.FileStorageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final FileStorageRepository fileStorageRepository;

    public EmployeeService(EmployeeRepository employeeRepository, FileStorageRepository fileStorageRepository) {
        this.employeeRepository = employeeRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional //@Transactional means that if there is an error, don't commit the execution
    public Employee save(Employee employee, InputStream photoStream) {
        Employee savedEmployee = employeeRepository.save(employee);
        fileStorageRepository.save(savedEmployee.getPhotoFileName(), photoStream);
        return savedEmployee;
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee with id: " + id + " not found"));
    }

    public void deleteById(Long id) {
        String deleteEmployeePhotoFile = findById(id).getPhotoFileName();
        employeeRepository.deleteById(id);
        fileStorageRepository.deleteByName(deleteEmployeePhotoFile);
    }

    public void deleteAllById(Iterable<? extends Long> longs) {
        employeeRepository.deleteAllById(longs);
    }

    public Employee findEmployeeByFirstName(String firstName){
        return employeeRepository.findEmployeeByFirstName(firstName);
    }
}
