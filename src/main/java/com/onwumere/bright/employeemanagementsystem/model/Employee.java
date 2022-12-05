package com.onwumere.bright.employeemanagementsystem.model;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    private int empId;
    private String firstName;

    private String lastName;

    private String email;

    private LocalDate dateOfBirth;

    private BigDecimal salary;
    private String ssn;
    private String phoneNumber;

}
