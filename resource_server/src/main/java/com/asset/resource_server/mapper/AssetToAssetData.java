package com.asset.resource_server.mapper;

import com.asset.resource_server.data.AssetData;
import com.asset.resource_server.entity.Asset;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AssetToAssetData implements Function<Asset, AssetData> {
    @Override
    public AssetData apply(Asset asset) {
        return AssetData.builder()
                .name(asset.getName())
                .employeeId(asset.getEmployeeId())
                .departmentId(asset.getDepartment().getId())
                .assetAllocated(asset.getAssetAllocated())
                .assetType(asset.getAssetType().getId())
                .assignDate(asset.getAssignDate())
                .manufacturerId(asset.getManufacturer().getId())
                .serialNumber(asset.getSerialNumber())
                .modelId(asset.getModel().getId())
                .operatingSystemId(asset.getOperatingSystem().getId())
                .locationId(asset.getLocation().getId())
                .processorId(asset.getComponents().getProcessor().getId())
                .driveTypeId(asset.getComponents().getDriveType().getId())
                .memoryTypeId(asset.getComponents().getMemoryType().getId())
                .warrantyStartDate(asset.getWarrantyStartDate())
                .warrantyExpiryDate(asset.getWarrantyExpiryDate())
                .status(asset.getStatus())
                .previousUserAssetReturnDate(asset.getPreviousUserAssetReturnDate())
                .build();
    }
}
