package com.onwumere.bright.employeemanagementsystem.repository;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 18/02/2023
 */


import com.onwumere.bright.employeemanagementsystem.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {


}

