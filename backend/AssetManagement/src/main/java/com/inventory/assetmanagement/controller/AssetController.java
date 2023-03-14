package com.inventory.assetmanagement.controller;

import com.inventory.assetmanagement.data.AssetData;
import com.inventory.assetmanagement.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class AssetController {
    private final AssetService assetService;

    @PostMapping(path = "/")
    public Long newAsset(@RequestBody AssetData assetData) {
        return assetService.saveNewAsset(assetData);
    }

    @GetMapping(path = "/get/{asset_id}")
    public AssetData retrieveAssetById(@PathVariable(name = "asset_id") Long id) {
        return assetService.fetchAssetById(id);
    }
}
