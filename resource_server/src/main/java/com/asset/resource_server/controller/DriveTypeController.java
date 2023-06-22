package com.asset.resource_server.controller;

import com.asset.resource_server.entity.DriveType;
import com.asset.resource_server.service.DriveTypeService;
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
@RequestMapping(path = "api/v1/drive-type")
@RequiredArgsConstructor
public class DriveTypeController {

    private final DriveTypeService driveTypeService;

    @PostMapping
    public void save(String driveType) {
        this.driveTypeService.save(driveType);
    }

    @PutMapping(path = "{id}")
    public void modify(@PathVariable(name = "id") Integer id, String newDriveType) {
        this.driveTypeService.modify(id, newDriveType);
    }

    @GetMapping
    public ResponseEntity<List<DriveType>> getAll() {
        return ResponseEntity.ok(this.driveTypeService.fetchAll());
    }

    @DeleteMapping
    public void remove(Integer id) {
        this.driveTypeService.deleteById(id);
    }
}
