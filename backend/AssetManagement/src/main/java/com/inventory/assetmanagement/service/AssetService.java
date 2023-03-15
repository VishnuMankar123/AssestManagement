package com.inventory.assetmanagement.service;

import com.inventory.assetmanagement.constant.EntityConstants;
import com.inventory.assetmanagement.data.AssetData;
import com.inventory.assetmanagement.exception.ResourceNotFoundException;
import com.inventory.assetmanagement.mapper.AssetEntityToDataMapper;
import com.inventory.assetmanagement.model.Asset;
import com.inventory.assetmanagement.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetEntityToDataMapper assetEntityToDataMapper;

    public List<AssetData> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(AssetEntityToDataMapper::entityToData)
                .collect(Collectors.toList());
    }

    public AssetData fetchAssetById(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.ASSET, id));
        return AssetEntityToDataMapper.entityToData(asset);
    }

    public Long saveNewAsset(AssetData assetData) {
        return assetRepository.save(assetEntityToDataMapper.dataToEntity(assetData)).getId();
    }

    public void editAsset(Long id, AssetData assetData) {
        if (!assetRepository.existsById(id)) {
            throw new ResourceNotFoundException(EntityConstants.ASSET, id);
        }
        assetRepository.deleteById(id);

        Asset newAsset = assetEntityToDataMapper.dataToEntity(assetData);
        newAsset.setId(id);

        assetRepository.save(newAsset);
    }

    public void deleteAsset(Long id) {
        if (!assetRepository.existsById(id)) {
            throw new ResourceNotFoundException(EntityConstants.ASSET, id);
        }
        assetRepository.deleteById(id);
    }
}
