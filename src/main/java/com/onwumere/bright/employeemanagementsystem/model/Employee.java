package com.onwumere.bright.employeemanagementsystem.model;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_employee", uniqueConstraints = @UniqueConstraint(columnNames = {"emp_id","email"}))
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column(name = "emp_id")
    private int empId;
    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;
    private String gender;
    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth must be entered")
    private LocalDate dateOfBirth;

    private Integer weight;

    @Past(message = "Date of Employment must be in the past")
    @NotNull(message = "Date of Employment must be entered")
    private LocalDate dateOfEmployment;

    @DecimalMin(value = "30000.00",message = "Salary must be at least 30000")
    @DecimalMax(value = "400000.00",message = "Salary must not be greater 400000")
    @NotNull(message = "Salary cannot be empty")
    private BigDecimal salary;
    @NotEmpty(message = "SSN cannot be empty.")
    @Pattern(regexp = "\\d{3}-\\d{2}-\\d{4}", message = "Did not match the pattern ddd-dd-dddd")
    private String ssn;
    @NotEmpty(message = "Phone number cannot be empty")
    //@Pattern(regexp = "\\+1 \\(\\d{3}\\) \\d{3}-\\d{4}", message = "Did not match the pattern +1 (xxx) xx-xxxx")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Parent parent;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    private String photoFileName;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;



    public Employee(int empId, String firstName, String lastName, String email, LocalDate dateOfBirth, BigDecimal salary, String ssn, String phoneNumber) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
    }

    public Employee(int empId, String firstName, String lastName, String gender, String email, LocalDate dateOfBirth, Integer weight,
                    LocalDate dateOfEmployment, BigDecimal salary, String ssn, String phoneNumber, Parent parent, Location location) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.dateOfEmployment = dateOfEmployment;
        this.salary = salary;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.parent = parent;
        this.location = location;
    }

    public static Employee parse(String csvLine) {
        String[] fields = csvLine.split(",\\s*");
        LocalDate dob = LocalDate.parse(fields[8], DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate doe = LocalDate.parse(fields[10], DateTimeFormatter.ofPattern("M/d/yyyy"));
        Parent employeeParent = new Parent(fields[5], fields[6], fields[7]);
        Region region = Region.valueOf(fields[18]);
        Location employeeLocation = new Location(fields[14], fields[15], fields[16], fields[17], region);
        return new Employee(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4], dob, Integer.parseInt(fields[9]), doe,
                new BigDecimal(fields[11]), fields[12], fields[13], employeeParent, employeeLocation);
    }
}
