package com.kcare.kcare.Department;

import java.time.LocalDate;

import com.kcare.kcare.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter

@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity

@Table(name = "department")

public class Department extends BaseEntity {

    private String image;
    private String departmentName;
    private String departmentMail;
    private String contactNumber;
    private String Location;
    private String departmentHead;
    private String departmentHeadContactNumber;

    private String departmentHeadMail;
    private LocalDate activeSinceDate;

}
