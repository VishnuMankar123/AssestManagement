package com.asset.resource_server.service;

import com.asset.resource_server.constant.CommonConstants;
import com.asset.resource_server.data.AssetData;
import com.asset.resource_server.entity.Asset;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.mapper.AssetDataToAssetMapper;
import com.asset.resource_server.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetDataToAssetMapper assetDataToAssetMapper;

    @Transactional(isolation = SERIALIZABLE)
    public void save(AssetData assetData) {
        this.assetRepository.save(this.assetDataToAssetMapper.fromAssetData(assetData));
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(String serialNumber, AssetData assetData) {
        Optional<Asset> fetchedAsset = this.assetRepository.findBySerialNumber(serialNumber);

        if (fetchedAsset.isEmpty()) {
            this.assetRepository.save(this.assetDataToAssetMapper.fromAssetData(assetData));
        } else {
            this.assetDataToAssetMapper.updateAssetFromAssetData(assetData, fetchedAsset.get());
            this.assetRepository.save(fetchedAsset.get());
        }
    }

    public List<AssetData> fetchAll() {
        return this.assetRepository.findAll()
                .stream()
                .map(this.assetDataToAssetMapper::fromAsset)
                .toList();
    }

    public AssetData findById(Long id) {
        return this.assetRepository.findById(id)
                .map(this.assetDataToAssetMapper::fromAsset)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.ASSET, CommonConstants.ID, id));
    }

    public AssetData findBySerialNumber(String serialNumber) {
        return this.assetRepository.findBySerialNumber(serialNumber)
                .map(this.assetDataToAssetMapper::fromAsset)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.ASSET, "serialNumber", serialNumber));
    }

    @Transactional
    public void deleteBySerialNumber(String serialNumber) {
        this.assetRepository.deleteBySerialNumber(serialNumber);
    }
}
