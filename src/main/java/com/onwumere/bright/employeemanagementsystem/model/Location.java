package com.onwumere.bright.employeemanagementsystem.model;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17/02/2023
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    private String county;
    private String city;
    private String state;
    private String zip;
    private Region region;

    public Location(String county, String city, String state, String zip, Region region) {
        this.county = county;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.region = region;
    }

    @Override
    public String toString() {
        return "Location{" +
                "county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", region=" + region +
                '}';
    }
}
