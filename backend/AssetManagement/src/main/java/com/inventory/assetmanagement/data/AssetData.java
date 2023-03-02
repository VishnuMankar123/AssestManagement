package com.inventory.assetmanagement.data;

import com.inventory.assetmanagement.enumeration.AssetType;
import com.inventory.assetmanagement.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetData {
    private String name;

    private String employeeId;

    private String department;

    private Boolean isAssetAllocated;

    private AssetType assetType;

    private Date assignDate;

    private String manufacturer;

    private String serialNumber;

    private String model;

    private String operatingSystem;

    private String operatingSystemVersion;

    private String location;

    private String processor;

    private String driverType;

    private String memory;

    private Date warrantyStartDate;

    private Date warrantyExpirationDate;

    private Status status;

    private Date previousUserReturnDate;
}
