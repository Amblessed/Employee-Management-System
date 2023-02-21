package com.onwumere.bright.employeemanagementsystem.service;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 06/12/2022
 */

import com.onwumere.bright.employeemanagementsystem.model.Employee;
import com.onwumere.bright.employeemanagementsystem.model.Region;
import com.onwumere.bright.employeemanagementsystem.repository.EmployeeRepository;
import com.onwumere.bright.employeemanagementsystem.repository.FileStorageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipInputStream;

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

    public List<Employee> findEmployeesByCounty(String county){
        return employeeRepository.findEmployeesByCounty(county);
    }

    public List<Employee> findEmployeesByState(String state){
        return employeeRepository.findEmployeesByState(state);
    }

    public List<Employee> findEmployeesByCity(String city){
        return employeeRepository.findEmployeesByCity(city);
    }

    public List<Employee> findEmployeesByZip(String zip){
        return employeeRepository.findEmployeesByZip(zip);
    }

    public List<Employee> findEmployeesByRegion(String region){
        Region enumRegion = Region.valueOf(region);
        return employeeRepository.findEmployeesByRegion(enumRegion);
    }



    public List<Employee> findEmployeesByName(String name){
        return employeeRepository.findEmployeeByName(name);
    }
    public List<Employee> findEmployeesByFNameOrLName(String name){
        return employeeRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name);
    }

    public Page<Employee> findEmployeesByNameOrEmpId(String search, Pageable pageable){
        return employeeRepository.findEmployeesByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSsnContains(search,search,search, pageable);
    }

    public List<Employee> findEmployeeByEmpId(int empId){
        return employeeRepository.findEmployeesByEmpId(empId);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> findEmployeeByLocationCounty(String county){
        return employeeRepository.findEmployeesByLocationCounty(county);
    }

    public void importCSV(InputStream csvFileStream) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(csvFileStream);
            zipInputStream.getNextEntry();
            InputStreamReader inputStreamReader = new InputStreamReader(zipInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines()
                    .skip(20001)
                    .limit(70000)
                    .map(Employee::parse)
                    .forEach(employeeRepository::save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
