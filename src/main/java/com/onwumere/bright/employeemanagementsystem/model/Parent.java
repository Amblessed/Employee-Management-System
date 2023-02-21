package com.onwumere.bright.employeemanagementsystem.model;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17/02/2023
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_parent")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    private String fathersName;
    private String mothersName;
    private String mothersMaidenName;

    public Parent(String fathersName, String mothersName, String mothersMaidenName) {
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.mothersMaidenName = mothersMaidenName;
    }
}
