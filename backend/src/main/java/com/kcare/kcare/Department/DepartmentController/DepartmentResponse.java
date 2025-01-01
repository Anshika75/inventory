package com.kcare.kcare.Department.DepartmentController;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentResponse {

    private Integer id;
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
