package com.asset.resource_server.controller;

import com.asset.resource_server.data.AssetData;
import com.asset.resource_server.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "api/v1/asset")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public void save(@RequestBody AssetData assetData) {
        this.assetService.save(assetData);
    }

    @PutMapping(path = "{id}")
    public void modify(@PathVariable(name = "id") Long id, @RequestBody AssetData newAssetData) {
        this.assetService.modify(id, newAssetData);
    }

    @GetMapping
    public ResponseEntity<List<AssetData>> getAll() {
        return ResponseEntity.ok(this.assetService.fetchAll());
    }

    @GetMapping(path = "name/{asset-name}")
    public AssetData getByName(@PathVariable(name = "asset-name") String name) {
        return this.assetService.findByName(name);
    }

    @GetMapping(path = "serial-number/{asset-serial-number}")
    public AssetData getBySerialNumber(@PathVariable(name = "asset-serial-number") String serialNumber) {
        return this.assetService.findBySerialNumber(serialNumber);
    }

    @DeleteMapping
    public void remove(Long id) {
        this.assetService.deleteById(id);
    }
}
