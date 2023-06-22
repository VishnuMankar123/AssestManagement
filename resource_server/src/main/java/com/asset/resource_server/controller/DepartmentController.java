package com.asset.resource_server.controller;

import com.asset.resource_server.entity.Department;
import com.asset.resource_server.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public void save(String departmentName) {
        this.departmentService.save(departmentName);
    }

    @PutMapping(path = "{id}")
    public void modify(@PathVariable(name = "id") Integer id, String newDepartmentName) {
        this.departmentService.modify(id, newDepartmentName);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(this.departmentService.fetchAll());
    }

    @DeleteMapping(path = "{id}")
    public void remove(@PathVariable(name = "id") Integer id) {
        this.departmentService.deleteById(id);
    }
}
