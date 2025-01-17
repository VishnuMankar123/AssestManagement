package com.asset.resource_server.controller;

import com.asset.resource_server.entity.MemoryType;
import com.asset.resource_server.service.MemoryTypeService;
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
@RequestMapping(path = "api/v1/memory-type")
@RequiredArgsConstructor
public class MemoryTypeController {

    private final MemoryTypeService memoryTypeService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void save(@RequestBody String memoryType) {
        this.memoryTypeService.save(memoryType);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void modify(@PathVariable(name = "id") Integer id, @RequestBody String newMemoryType) {
        this.memoryTypeService.modify(id, newMemoryType);
    }

    @GetMapping
    public ResponseEntity<List<MemoryType>> getAll() {
        return ResponseEntity.ok(this.memoryTypeService.fetchAll());
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void remove(@PathVariable(name = "id") Integer id) {
        this.memoryTypeService.deleteById(id);
    }
}
