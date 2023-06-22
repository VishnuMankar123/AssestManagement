package com.asset.resource_server.controller;

import com.asset.resource_server.entity.MemoryType;
import com.asset.resource_server.service.MemoryTypeService;
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
@RequestMapping(path = "api/v1/memory-type")
@RequiredArgsConstructor
public class MemoryTypeController {

    private final MemoryTypeService memoryTypeService;

    @PostMapping
    public void save(String memoryType) {
        this.memoryTypeService.save(memoryType);
    }

    @PutMapping(path = "{id}")
    public void modify(@PathVariable(name = "id") Integer id, String newMemoryType) {
        this.memoryTypeService.modify(id, newMemoryType);
    }

    @GetMapping
    public ResponseEntity<List<MemoryType>> getAll() {
        return ResponseEntity.ok(this.memoryTypeService.fetchAll());
    }

    @DeleteMapping
    public void remove(Integer id) {
        this.memoryTypeService.deleteById(id);
    }
}
