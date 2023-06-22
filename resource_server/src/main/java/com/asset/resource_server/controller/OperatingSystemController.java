package com.asset.resource_server.controller;

import com.asset.resource_server.entity.OperatingSystem;
import com.asset.resource_server.service.OperatingSystemService;
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
@RequestMapping(path = "api/v1/operating-system")
@RequiredArgsConstructor
public class OperatingSystemController {

    private final OperatingSystemService operatingSystemService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void save(@RequestBody String operatingSystem) {
        this.operatingSystemService.save(operatingSystem);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void modify(@PathVariable(name = "id") Integer id, @RequestBody String newOperatingSystem) {
        this.operatingSystemService.modify(id, newOperatingSystem);
    }

    @GetMapping
    public ResponseEntity<List<OperatingSystem>> getAll() {
        return ResponseEntity.ok(this.operatingSystemService.fetchAll());
    }

    @DeleteMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void remove(Integer id) {
        this.operatingSystemService.deleteById(id);
    }
}
