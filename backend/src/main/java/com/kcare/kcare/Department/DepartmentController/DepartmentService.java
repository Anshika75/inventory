package com.kcare.kcare.Department.DepartmentController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.Department.Department;
import com.kcare.kcare.Department.DepartmentRepository;
import com.kcare.kcare.common.Response;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
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

    public DepartmentResponse getDepartmentById(Integer id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department Not Found with Id: " + id));

        DepartmentResponse departmentResponse = departmentMapper.toDoDepartmentResponse(department);
        return departmentResponse;
    }

    public Response<DepartmentRequest> updateDeparmentDetails(Integer id, DepartmentRequest departmentRequest) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department Not Found With Id:" + id));
        merge(departmentRequest, department);

        departmentRepository.save(department);

        return new Response<>(
                departmentRequest,
                LocalDateTime.now(),
                "successfully updated",
                HttpStatus.OK);

    }

    private void merge(DepartmentRequest departmentRequest, Department department) {

        if (StringUtils.isNotBlank(departmentRequest.getDepartmentName())) {
            department.setDepartmentName(departmentRequest.getDepartmentName());
        }
        if (StringUtils.isNotBlank(departmentRequest.getDepartmentMail())) {
            department.setDepartmentMail(departmentRequest.getDepartmentMail());
        }
        if (StringUtils.isNotBlank(departmentRequest.getContactNumber())) {
            department.setContactNumber(departmentRequest.getContactNumber());
        }
        if (StringUtils.isNotBlank(departmentRequest.getLocation())) {
            department.setLocation(departmentRequest.getLocation());
        }
        if (StringUtils.isNotBlank(departmentRequest.getDepartmentHead())) {
            department.setDepartmentHead(departmentRequest.getDepartmentHead());
        }
        if (StringUtils.isNotBlank(departmentRequest.getDepartmentHeadContactNumber())) {
            department.setDepartmentHeadContactNumber(departmentRequest.getDepartmentHeadContactNumber());
        }
        if (StringUtils.isNotBlank(departmentRequest.getDepartmentHeadMail())) {
            department.setDepartmentHeadMail(departmentRequest.getDepartmentHeadMail());
        }
        if (departmentRequest.getActiveSinceDate() != null) {
            department.setActiveSinceDate(departmentRequest.getActiveSinceDate());
        }

    }

    public void deleteDepartmentById(Integer id) {

        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department Not Found with Id: " + id);
        }
        departmentRepository.deleteById(id);

    }

}
