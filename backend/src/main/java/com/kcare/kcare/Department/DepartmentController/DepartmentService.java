package com.kcare.kcare.Department.DepartmentController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.Department.Department;
import com.kcare.kcare.Department.DepartmentRepository;
import com.kcare.kcare.common.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public Response<DepartmentRequest> createDepartment(DepartmentRequest departmentRequest) {
        Department department = departmentMapper.toDepartment(departmentRequest);
        departmentRepository.save(department);
        return new Response<DepartmentRequest>(
                departmentRequest,
                LocalDateTime.now(),
                "successfully saved",
                HttpStatus.CREATED);

    }

}
