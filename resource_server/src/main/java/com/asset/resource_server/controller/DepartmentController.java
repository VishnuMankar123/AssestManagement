package com.asset.resource_server.controller;

import com.asset.resource_server.entity.Department;
import com.asset.resource_server.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void save(@RequestBody String departmentName) {
        this.departmentService.save(departmentName);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void modify(@PathVariable(name = "id") Integer id, @RequestBody String newDepartmentName) {
        this.departmentService.modify(id, newDepartmentName);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(this.departmentService.fetchAll());
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void remove(@PathVariable(name = "id") Integer id) {
        this.departmentService.deleteById(id);
    }
}
