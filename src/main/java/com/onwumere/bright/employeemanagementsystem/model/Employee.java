package com.onwumere.bright.employeemanagementsystem.model;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private BigDecimal salary;

}
