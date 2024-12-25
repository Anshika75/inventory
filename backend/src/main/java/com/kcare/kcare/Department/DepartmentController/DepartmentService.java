package com.kcare.kcare.Department.DepartmentController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Department.Department;
import com.kcare.kcare.Department.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public String createDepartment(DepartmentRequest departmentRequest) {

        Department department = departmentMapper.toDepartment(departmentRequest);
        departmentRepository.save(department);
        return "department Created ";

    }

}
