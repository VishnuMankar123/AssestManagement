package com.asset.resource_server.controller;

import com.asset.resource_server.entity.Model;
import com.asset.resource_server.service.ModelService;
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
@RequestMapping(path = "api/v1/model")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void save(@RequestBody String model) {
        this.modelService.save(model);
    }

    @PutMapping(path = "{id}")
    public void modify(@PathVariable(name = "id") Integer id, @RequestBody String newModel) {
        this.modelService.modify(id, newModel);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public ResponseEntity<List<Model>> getAll() {
        return ResponseEntity.ok(this.modelService.fetchAll());
    }

    @DeleteMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void remove(Integer id) {
        this.modelService.deleteById(id);
    }
}
