package com.onwumere.bright.employeemanagementsystem.repository;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.onwumere.bright.employeemanagementsystem.model.Employee;
import com.onwumere.bright.employeemanagementsystem.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findEmployeesByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSsnContains(String fName, String lName, String ssn, Pageable pageable);

    List<Employee> findEmployeesByFirstName(String firstName);

    @Query(value = "select emp from Employee as emp where emp.firstName like %:name% or emp.lastName like %:name%")  //This is using the class Instructor.
    List<Employee> findEmployeeByName(@Param("name") String name);

    @Query(value = "select emp from Employee as emp where emp.location.county like %:county%")
    List<Employee> findEmployeesByCounty(@Param("county") String county);

    @Query(value = "select emp from Employee as emp where emp.location.city like %:city%")
    List<Employee> findEmployeesByCity(@Param("city") String city);

    @Query(value = "select emp from Employee as emp where emp.location.state like %:state%")
    List<Employee> findEmployeesByState(@Param("state") String state);

    @Query(value = "select emp from Employee as emp where emp.location.zip like %:zip%")
    List<Employee> findEmployeesByZip(@Param("zip") String zip);

    @Query(value = "select emp from Employee as emp where emp.location.region = :#{#region}")
    List<Employee> findEmployeesByRegion(@Param("region") Region region);

    List<Employee> findEmployeesByEmpId(int empId);

    List<Employee> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);

    List<Employee> findEmployeesByLocationCounty(String county);





}

