package com.inventory.assetmanagement.mapper;

import com.inventory.assetmanagement.data.AssetData;
import com.inventory.assetmanagement.model.Asset;
import com.inventory.assetmanagement.model.AssetType;
import com.inventory.assetmanagement.model.Location;
import com.inventory.assetmanagement.model.Manufacturer;
import com.inventory.assetmanagement.model.OperatingSystem;

public class AssetEntityToAssetDataMapper {
    private AssetEntityToAssetDataMapper() {}

    public static Asset dataToEntity(AssetData assetData) {
        return Asset.builder()
                .employeeName(assetData.getEmployeeName())
                .assetType(new AssetType(assetData.getAssetType()))
                .assignDate(assetData.getAssignDate())
                .isAssetAllocated(assetData.getIsAssetAllocated())
                .driverType(assetData.getDriverType())
                .employeeId(assetData.getEmployeeId())
                .memory(assetData.getMemory())
                .department(assetData.getDepartment())
                .location(new Location(assetData.getLocation()))
                .manufacturer(new Manufacturer(assetData.getManufacturer()))
                .processor(assetData.getProcessor())
                .status(assetData.getStatus())
                .serialNumber(assetData.getSerialNumber())
                .operatingSystem(new OperatingSystem(assetData.getOperatingSystem()))
                .warrantyExpirationDate(assetData.getWarrantyExpirationDate())
                .warrantyStartDate(assetData.getWarrantyStartDate())
                .previousUserReturnDate(assetData.getPreviousUserReturnDate())
                .model(assetData.getModel())
                .build();
    }

    public static AssetData entityToData(Asset asset) {
        return AssetData.builder()
                .assetType(asset.getAssetType().getType())
                .operatingSystem(asset.getOperatingSystem().getOperatingSystemName())
                .employeeName(asset.getEmployeeName())
                .isAssetAllocated(asset.getIsAssetAllocated())
                .location(asset.getLocation().getLocationName())
                .memory(asset.getMemory())
                .driverType(asset.getDriverType())
                .assignDate(asset.getAssignDate())
                .department(asset.getDepartment())
                .processor(asset.getProcessor())
                .status(asset.getStatus())
                .employeeId(asset.getEmployeeId())
                .manufacturer(asset.getManufacturer().getManufacturerName())
                .previousUserReturnDate(asset.getPreviousUserReturnDate())
                .warrantyStartDate(asset.getWarrantyStartDate())
                .serialNumber(asset.getSerialNumber())
                .warrantyExpirationDate(asset.getWarrantyExpirationDate())
                .model(asset.getModel())
                .build();
    }
}
