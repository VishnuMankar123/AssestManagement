package com.asset.resource_server.service;

import com.asset.resource_server.constant.CommonConstants;
import com.asset.resource_server.data.AssetData;
import com.asset.resource_server.embeddable.Components;
import com.asset.resource_server.entity.Asset;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.mapper.AssetToAssetData;
import com.asset.resource_server.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final DepartmentService departmentService;
    private final AssetTypeService assetTypeService;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;
    private final OperatingSystemService operatingSystemService;
    private final LocationService locationService;
    private final ProcessorService processorService;
    private final DriveTypeService driveTypeService;
    private final MemoryTypeService memoryTypeService;
    private final AssetToAssetData assetToAssetData;

    @Transactional(isolation = SERIALIZABLE)
    public void save(AssetData assetData) {
        this.assetRepository.save(Asset.builder()
                .name(assetData.name())
                .employeeId(assetData.employeeId())
                .department(departmentService.findById(assetData.departmentId()))
                .assetAllocated(assetData.assetAllocated())
                .assetType(assetTypeService.findById(assetData.assetType()))
                .assignDate(assetData.assignDate())
                .manufacturer(manufacturerService.findById(assetData.manufacturerId()))
                .serialNumber(assetData.serialNumber())
                .model(modelService.fetchById(assetData.modelId()))
                .operatingSystem(operatingSystemService.fetchById(assetData.operatingSystemId()))
                .location(locationService.findById(assetData.locationId()))
                .components(Components.builder()
                        .processor(processorService.fetchById(assetData.processorId()))
                        .driveType(driveTypeService.fetchById(assetData.driveTypeId()))
                        .memoryType(memoryTypeService.fetchById(assetData.memoryTypeId()))
                        .build())
                .warrantyStartDate(assetData.warrantyStartDate())
                .warrantyExpiryDate(assetData.warrantyExpiryDate())
                .status(assetData.status())
                .previousUserAssetReturnDate(assetData.previousUserAssetReturnDate())
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Long assetId, AssetData assetData) {
        if (this.assetRepository.existsById(assetId)) {
            this.assetRepository.deleteById(assetId);
        }

        this.assetRepository.flush();

        this.assetRepository.save(Asset.builder()
                .id(assetId)
                .name(assetData.name())
                .employeeId(assetData.employeeId())
                .department(departmentService.findById(assetData.departmentId()))
                .assetAllocated(assetData.assetAllocated())
                .assetType(assetTypeService.findById(assetData.assetType()))
                .assignDate(assetData.assignDate())
                .manufacturer(manufacturerService.findById(assetData.manufacturerId()))
                .serialNumber(assetData.serialNumber())
                .model(modelService.fetchById(assetData.modelId()))
                .operatingSystem(operatingSystemService.fetchById(assetData.operatingSystemId()))
                .location(locationService.findById(assetData.locationId()))
                .components(Components.builder()
                        .processor(processorService.fetchById(assetData.processorId()))
                        .driveType(driveTypeService.fetchById(assetData.driveTypeId()))
                        .memoryType(memoryTypeService.fetchById(assetData.memoryTypeId()))
                        .build())
                .warrantyStartDate(assetData.warrantyStartDate())
                .warrantyExpiryDate(assetData.warrantyExpiryDate())
                .status(assetData.status())
                .previousUserAssetReturnDate(assetData.previousUserAssetReturnDate())
                .build());
    }

    public List<AssetData> fetchAll() {
        return this.assetRepository.findAll()
                .stream()
                .map(assetToAssetData)
                .toList();
    }

    public AssetData findById(Long id) {
        return this.assetRepository.findById(id)
                .map(assetToAssetData)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.ASSET, CommonConstants.ID, id));
    }

    public AssetData findBySerialNumber(String serialNumber) {
        return this.assetRepository.findBySerialNumber(serialNumber)
                .map(assetToAssetData)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.ASSET, "serialNumber", serialNumber));
    }

    @Transactional
    public void deleteById(Long id) {
        this.assetRepository.deleteById(id);
    }
}
