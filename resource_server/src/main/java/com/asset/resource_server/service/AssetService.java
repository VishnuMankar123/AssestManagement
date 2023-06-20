package com.asset.resource_server.service;

import com.asset.resource_server.data.AssetData;
import com.asset.resource_server.embeddable.Components;
import com.asset.resource_server.entity.Asset;
import com.asset.resource_server.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                        .warrantyExpiryDate(assetData.warrantyStartDate())
                .build());
    }
}
