package com.asset.resource_server.controller;

import com.asset.resource_server.entity.Manufacturer;
import com.asset.resource_server.service.ManufacturerService;
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
@RequestMapping(path = "api/v1/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void save(@RequestBody String manufacturer) {
        this.manufacturerService.save(manufacturer);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void modify(@PathVariable(name = "id") Integer id, @RequestBody String newManufacturer) {
        this.manufacturerService.modify(id, newManufacturer);
    }

    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAll() {
        return ResponseEntity.ok(this.manufacturerService.fetchAll());
    }

    @DeleteMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void remove(Integer id) {
        this.manufacturerService.deleteById(id);
    }
}
