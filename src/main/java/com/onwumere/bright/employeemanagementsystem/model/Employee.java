package com.onwumere.bright.employeemanagementsystem.model;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Min(message = "Employee Id must be greater than 100000", value = 100000)
    @Max(message = "Employee Id must be less than 900000", value = 900000)
    private int empId;

    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth must be entered")
    private LocalDate dateOfBirth;

    @DecimalMin(value = "100000.00",message = "Salary must be at least 100000")
    @NotNull(message = "Salary cannot be empty")
    private BigDecimal salary;
    @NotEmpty(message = "SSN cannot be empty. Must match the pattern xxx-xx-xxxx")
    //@Pattern(regexp = "\\d{3}-\\d{2}-\\d{4}")
    private String ssn;
    @NotEmpty(message = "Phone number cannot be empty. Must match the pattern +1 (xxx) xx-xxxx")
    //@Pattern(regexp = "\\+1 \\(\\d{3}\\) \\d{3}-\\d{4}")
    private String phoneNumber;


}
