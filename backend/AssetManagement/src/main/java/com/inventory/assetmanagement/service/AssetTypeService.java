package com.inventory.assetmanagement.service;

import com.inventory.assetmanagement.constant.EntityConstants;
import com.inventory.assetmanagement.exception.ResourceNotFoundException;
import com.inventory.assetmanagement.model.AssetType;
import com.inventory.assetmanagement.repository.AssetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetTypeService {
    private final AssetTypeRepository assetTypeRepository;

    public List<AssetType> retrieveAllAssetTypes() {
        return assetTypeRepository.findAll();
    }

    public Long saveNewAssetType(String assetType) {
        AssetType newAssetType = new AssetType(assetType);
        return assetTypeRepository.save(newAssetType).getId();
    }

    public String getAssetTypeById(Long id) {
        AssetType assetType = assetTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(EntityConstants.ASSET_TYPE));
        return assetType.getType();
    }

    @Transactional
    public void editAssetTypeById(Long id, String newType) {
        AssetType assetType = assetTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(EntityConstants.ASSET_TYPE));
        assetType.setType(newType);
    }

    public void deleteAssetTypeById(Long id) {
        if (!assetTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException(EntityConstants.ASSET_TYPE);
        }
        assetTypeRepository.deleteById(id);
    }

    public boolean existByAssetType(String assetType) {
        return assetTypeRepository.existsByType(assetType);
    }
}
