package com.asset.resource_server.controller;

import com.asset.resource_server.entity.AssetType;
import com.asset.resource_server.service.AssetTypeService;
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
@RequestMapping(value = "api/v1/asset-type")
@RequiredArgsConstructor
public class AssetTypeController {

    private final AssetTypeService assetTypeService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void save(@RequestBody String assetType) {
        this.assetTypeService.save(assetType);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void modify(@PathVariable(name = "id") Integer id, @RequestBody String modifiedAssetType) {
        this.assetTypeService.modify(id, modifiedAssetType);
    }

    @GetMapping
    public ResponseEntity<List<AssetType>> getAll() {
        return ResponseEntity.ok(this.assetTypeService.fetchAll());
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void remove(@PathVariable(name = "id") Integer id) {
        this.assetTypeService.deleteById(id);
    }
}
