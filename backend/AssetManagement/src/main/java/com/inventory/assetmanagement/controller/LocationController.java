package com.inventory.assetmanagement.controller;

import com.inventory.assetmanagement.model.Location;
import com.inventory.assetmanagement.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/location/")
public class LocationController {
    private final LocationService locationService;

    @GetMapping(path = "/all")
    public List<Location> getAllLocations() {
        return locationService.fetchAllLocations();
    }

    @GetMapping("/{location_id}")
    public String fetchLocationById(@PathVariable(name = "location_id") Long id) {
        return locationService.fetchLocationById(id);
    }

    @PostMapping(path = "/{location_name}")
    public Long newLocation(@PathVariable(name = "location_name") String locationName) {
        return locationService.saveNewLocation(locationName);
    }

    @PutMapping(path = "/{location_id}/{new_location_name}")
    public void editLocation(@PathVariable(name = "location_id") Long id,
                             @PathVariable(name = "new_location_name") String newName) {
        locationService.editLocationById(id, newName);
    }

    @DeleteMapping(path = "/{location_id}")
    public void deleteLocation(@PathVariable(name = "location_id") Long id) {
        locationService.deleteLocationById(id);
    }
}
