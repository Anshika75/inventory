package com.kcare.kcare.Department.DepartmentController;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class DepartmentRequest {

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
