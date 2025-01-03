package com.kcare.kcare.Department.DepartmentController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcare.kcare.common.Response;

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
    public ResponseEntity<Response<DepartmentRequest>> createDepartment(
            @RequestBody DepartmentRequest departmentRequest) {
        return ResponseEntity.ok(departmentService.createDepartment(departmentRequest));
    }

    @GetMapping(path = "search/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<Response<DepartmentRequest>> updateDeparmentDetails(@PathVariable("id") Integer id,
            @RequestBody DepartmentRequest departmentRequest) {
        return ResponseEntity.ok(departmentService.updateDeparmentDetails(id, departmentRequest));
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable("id") Integer Id) {
        departmentService.deleteDepartmentById(Id);

        return ResponseEntity.noContent().build();

    }

}
