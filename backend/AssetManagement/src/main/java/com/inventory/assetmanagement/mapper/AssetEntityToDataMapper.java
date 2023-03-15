package com.inventory.assetmanagement.mapper;

import com.inventory.assetmanagement.constant.EntityConstants;
import com.inventory.assetmanagement.data.AssetData;
import com.inventory.assetmanagement.exception.ResourceNotFoundException;
import com.inventory.assetmanagement.model.Asset;
import com.inventory.assetmanagement.model.AssetType;
import com.inventory.assetmanagement.model.Location;
import com.inventory.assetmanagement.model.Manufacturer;
import com.inventory.assetmanagement.model.OperatingSystem;
import com.inventory.assetmanagement.repository.AssetTypeRepository;
import com.inventory.assetmanagement.repository.LocationRepository;
import com.inventory.assetmanagement.repository.ManufacturerRepository;
import com.inventory.assetmanagement.repository.OperatingSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetEntityToDataMapper {
    private final AssetTypeRepository assetTypeRepository;
    private final LocationRepository locationRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final OperatingSystemRepository operatingSystemRepository;

    public Asset dataToEntity(AssetData assetData) {
        AssetType type = assetTypeRepository.findById(assetData.getAssetTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.ASSET_TYPE, assetData.getAssetTypeId()));

        Location employeeLocation = locationRepository.findById(assetData.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.LOCATION, assetData.getAssetTypeId()));

        Manufacturer assetManufacturer = manufacturerRepository.findById(assetData.getManufacturerId())
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.MANUFACTURER, assetData.getManufacturerId()));

        OperatingSystem assetOperatingSystem = operatingSystemRepository.findById(assetData.getOperatingSystemId())
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.OPERATING_SYSTEM, assetData.getOperatingSystemId()));

        return Asset.builder()
                .employeeName(assetData.getEmployeeName())
                .assetType(type)
                .assignDate(assetData.getAssignDate())
                .isAssetAllocated(assetData.getIsAssetAllocated())
                .driverType(assetData.getDriverType())
                .employeeId(assetData.getEmployeeId())
                .memory(assetData.getMemory())
                .department(assetData.getDepartment())
                .location(employeeLocation)
                .manufacturer(assetManufacturer)
                .processor(assetData.getProcessor())
                .status(assetData.getStatus())
                .serialNumber(assetData.getSerialNumber())
                .operatingSystem(assetOperatingSystem)
                .warrantyExpirationDate(assetData.getWarrantyExpirationDate())
                .warrantyStartDate(assetData.getWarrantyStartDate())
                .previousUserReturnDate(assetData.getPreviousUserReturnDate())
                .model(assetData.getModel())
                .build();
    }

    public static AssetData entityToData(Asset asset) {
        return AssetData.builder()
                .assetTypeId(asset.getAssetType().getId())
                .operatingSystemId(asset.getOperatingSystem().getId())
                .employeeName(asset.getEmployeeName())
                .isAssetAllocated(asset.getIsAssetAllocated())
                .locationId(asset.getLocation().getId())
                .memory(asset.getMemory())
                .driverType(asset.getDriverType())
                .assignDate(asset.getAssignDate())
                .department(asset.getDepartment())
                .processor(asset.getProcessor())
                .status(asset.getStatus())
                .employeeId(asset.getEmployeeId())
                .manufacturerId(asset.getManufacturer().getId())
                .previousUserReturnDate(asset.getPreviousUserReturnDate())
                .warrantyStartDate(asset.getWarrantyStartDate())
                .serialNumber(asset.getSerialNumber())
                .warrantyExpirationDate(asset.getWarrantyExpirationDate())
                .model(asset.getModel())
                .build();
    }
}
