package com.kcare.kcare.Department.DepartmentController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Department.Department;

@Service
public class DepartmentMapper {

    public Department toDepartment(DepartmentRequest departmentRequest) {
        return Department.builder()
                .image(departmentRequest.getImage())
                .departmentName(departmentRequest.getDepartmentName())
                .departmentMail(departmentRequest.getDepartmentMail())
                .contactNumber(departmentRequest.getContactNumber())
                .Location(departmentRequest.getLocation())
                .departmentHead(departmentRequest.getDepartmentHead())
                .departmentHeadContactNumber(departmentRequest.getDepartmentHeadContactNumber())
                .departmentHeadMail(departmentRequest.getDepartmentHeadMail())
                .activeSinceDate(departmentRequest.getActiveSinceDate())
                .build();
    }

    public DepartmentResponse toDoDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentMail(department.getDepartmentMail())
                .contactNumber(department.getContactNumber())
                .Location(department.getLocation())
                .departmentHead(department.getDepartmentHead())
                .departmentHeadContactNumber(department.getDepartmentHeadContactNumber())
                .departmentHeadMail(department.getDepartmentHeadMail())
                .activeSinceDate(department.getActiveSinceDate())
                .build();
    }

}
