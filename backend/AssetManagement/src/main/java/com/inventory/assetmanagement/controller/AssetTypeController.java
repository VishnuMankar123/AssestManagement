package com.inventory.assetmanagement.controller;

import com.inventory.assetmanagement.model.AssetType;
import com.inventory.assetmanagement.service.AssetTypeService;
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
@RequestMapping("/api/v1/asset_type/")
@RequiredArgsConstructor
public class AssetTypeController {
    private final AssetTypeService assetTypeService;

    @GetMapping(path = "/all")
    public List<AssetType> fetchAllAssets() {
        return assetTypeService.retrieveAllAssetTypes();
    }

    @PostMapping(path = "/{type_name}")
    public Long newAssetType(@PathVariable(name = "type_name") String type) {
        return assetTypeService.saveNewAssetType(type);
    }

    @GetMapping(path = "/{type_id}")
    public String fetchAssetTypeById(@PathVariable(name = "type_id") Long id) {
        return assetTypeService.getAssetTypeById(id);
    }

    @PutMapping(path = "/{type_id}/{new_type}")
    public void editAssetType(@PathVariable(name = "type_id") Long id,
                              @PathVariable(name = "new_type") String newType) {
        assetTypeService.editAssetTypeById(id, newType);
    }

    @DeleteMapping(path = "/{type_id}")
    public void deleteAssetType(@PathVariable(name = "type_id") Long id) {
        assetTypeService.deleteAssetTypeById(id);
    }
}
