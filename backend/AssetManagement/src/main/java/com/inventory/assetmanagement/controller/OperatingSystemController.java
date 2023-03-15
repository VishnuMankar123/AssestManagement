package com.inventory.assetmanagement.controller;

import com.inventory.assetmanagement.model.OperatingSystem;
import com.inventory.assetmanagement.service.OperatingSystemService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/operating_system")
public class OperatingSystemController {
    private OperatingSystemService operatingSystemService;

    @GetMapping(path = "/all")
    public List<OperatingSystem> getAllOperatingSystems() {
        return operatingSystemService.fetchAllOperatingSystems();
    }

    @GetMapping(path = "/{operating_system_id}")
    public String getOperatingSystemById(@PathVariable(name = "operating_system_id") Long id) {
        return operatingSystemService.fetchOperatingSystemById(id);
    }

    @PostMapping(path = "/{operating_system_name}")
    public Long newOperatingSystem(@PathVariable(name = "operating_system_name") String newOperatingSystemName) {
        return operatingSystemService.saveNewOperatingSystem(newOperatingSystemName);
    }

    @PutMapping(path = "/{operating_system_id}/{operating_system_name}")
    public void editOperatingSystem(@PathVariable(name = "operating_system_id") Long id,
                                    @PathVariable(name = "operating_system_name") String newName) {
        operatingSystemService.editOperatingSystem(id, newName);
    }

    @DeleteMapping(path = "/{operating_system_id}")
    public void deleteOperatingSystem(@PathVariable(name = "operating_system_id") Long id) {
        operatingSystemService.deleteOperatingSystem(id);
    }
}
