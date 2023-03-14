package com.inventory.assetmanagement.service;

import com.inventory.assetmanagement.data.AssetData;
import com.inventory.assetmanagement.exception.ResourceNotFoundException;
import com.inventory.assetmanagement.mapper.AssetEntityToDataMapper;
import com.inventory.assetmanagement.model.Asset;
import com.inventory.assetmanagement.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;

    public Long saveNewAsset(AssetData assetData) {
        return assetRepository.save(AssetEntityToDataMapper.dataToEntity(assetData)).getId();
    }

    public AssetData fetchAssetById(Long id) {
        Asset asset = assetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Asset"));
        return AssetEntityToDataMapper.entityToData(asset);
    }
}
