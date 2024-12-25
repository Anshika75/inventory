package com.kcare.kcare.Department.DepartmentController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
// @Tag(name = "Department Controller", description = "Manage Department")
public class DepartmentController {

    private final DepartmentService departmentService;

    // @Operation(summary = "create Department", description = "Create Department by
    // Entering data ")
    @PostMapping("/createDepartment")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequest departmentRequest) {

        return ResponseEntity.ok(departmentService.createDepartment(departmentRequest));
    }

}
