package com.inventory.assetmanagement.data;

import com.inventory.assetmanagement.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetData {
    @NonNull
    private String employeeName;
    @NonNull
    private String employeeId;
    @NonNull
    private String department;
    @NonNull
    private Boolean isAssetAllocated;
    @NonNull
    private String assetType;
    @NonNull
    private Date assignDate;
    @NonNull
    private String manufacturer;
    @NonNull
    private String serialNumber;
    @NonNull
    private String model;
    @NonNull
    private String operatingSystem;
    @NonNull
    private String location;
    @NonNull
    private String processor;
    @NonNull
    private String driverType;
    @NonNull
    private String memory;
    @NonNull
    private Date warrantyStartDate;
    @NonNull
    private Date warrantyExpirationDate;
    @NonNull
    private Status status;
    @NonNull
    private Date previousUserReturnDate;
}
