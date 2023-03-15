package com.inventory.assetmanagement.controller;

import com.inventory.assetmanagement.model.Manufacturer;
import com.inventory.assetmanagement.service.ManufacturerService;
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
@RequestMapping(path = "/api/v1/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping(path = "/all")
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerService.fetchAllManufacturers();
    }

    @GetMapping(path = "/{manufacturer_id}")
    public String getManufacturerById(@PathVariable(name = "manufacturer_id") Long id) {
        return manufacturerService.fetchManufacturerById(id);
    }

    @PostMapping(path = "/{manufacturer_name}")
    public Long newManufacturer(@PathVariable(name = "manufacturer_name") String manufacturerName) {
        return manufacturerService.saveNewManufacturer(manufacturerName);
    }

    @PutMapping(path = "/{manufacturer_id}/{manufacturer_name}")
    public void editManufacturer(@PathVariable(name = "manufacturer_id") Long id,
                                 @PathVariable(name = "manufacturer_name") String newName) {
        manufacturerService.editManufacturer(id, newName);
    }

    @DeleteMapping(path = "/{manufacturer_id}")
    public void deleteManufacturer(@PathVariable(name = "manufacturer_id") Long id) {
        manufacturerService.deleteManufacturerById(id);
    }
}
