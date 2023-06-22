package com.asset.resource_server.controller;

import com.asset.resource_server.entity.Location;
import com.asset.resource_server.service.LocationService;
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
@RequestMapping(path = "api/v1/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public void save(String city) {
        this.locationService.save(city);
    }

    @PutMapping(path = "{id}")
    public void modify(@PathVariable(name = "id") Integer id, String newCity) {
        this.locationService.modify(id, newCity);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAll() {
        return ResponseEntity.ok(this.locationService.fetchAll());
    }

    @DeleteMapping
    public void remove(Integer id) {
        this.locationService.deleteById(id);
    }
}
