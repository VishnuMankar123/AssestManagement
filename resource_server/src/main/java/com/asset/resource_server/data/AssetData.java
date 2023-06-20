package com.asset.resource_server.data;

import com.asset.resource_server.enumeration.AssetAllocated;
import com.asset.resource_server.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
