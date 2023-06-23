package com.asset.resource_server.data;

import com.asset.resource_server.enumeration.AssetAllocated;
import com.asset.resource_server.enumeration.Status;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AssetData (
    String name,
    String employeeId,
    Integer departmentId,
    AssetAllocated assetAllocated,
    Integer assetType,
    LocalDate assignDate,
    Integer manufacturerId,
    String serialNumber,
    Integer modelId,
    Integer operatingSystemId,
    Integer locationId,
    Integer processorId,
    Integer driveTypeId,
    Integer memoryTypeId,
    LocalDate warrantyStartDate,
    LocalDate warrantyExpiryDate,
    Status status,
    LocalDate previousUserAssetReturnDate
) {}
