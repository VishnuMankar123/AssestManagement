package com.asset.resource_server.service;

import com.asset.resource_server.entity.AssetType;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.AssetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AssetTypeService {
    private final AssetTypeRepository assetTypeRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String newAssetType) {
        this.assetTypeRepository.save(AssetType.builder()
                .type(newAssetType)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newAssetType) {
        AssetType previous = this.assetTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetType", "type", id));

        previous.setType(newAssetType);

        this.assetTypeRepository.save(previous);
    }

    public List<AssetType> fetchAll() {
        return this.assetTypeRepository.findAll()
                .stream()
                .toList();
    }

    public AssetType findById(Integer id) {
        return this.assetTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetType", "type", id));
    }

    public void deleteById(Integer id) {
        this.assetTypeRepository.deleteById(id);
    }
}
