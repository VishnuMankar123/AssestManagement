package com.inventory.assetmanagement.model;

import com.inventory.assetmanagement.enumeration.AssetType;
import com.inventory.assetmanagement.enumeration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "department")
    private String department;

    @Column(name = "is_asset_allocated")
    private Boolean isAssetAllocated;

    @Column(name = "asset_type")
    private AssetType assetType;

    @Column(name = "assign_date")
    @Temporal(value = TemporalType.DATE)
    private Date assignDate;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "operating_system")
    private String operatingSystem;

    @Column(name = "location")
    private String location;

    @Column(name = "processor")
    private String processor;

    @Column(name = "driver_type")
    private String driverType;

    @Column(name = "memory")
    private String memory;

    @Column(name = "warranty_start_date")
    @Temporal(value = TemporalType.DATE)
    private Date warrantyStartDate;

    @Column(name = "warranty_expiration_date")
    @Temporal(value = TemporalType.DATE)
    private Date warrantyExpirationDate;

    @Column(name = "status")
    private Status status;

    @Column(name = "previous_user_return_date")
    @Temporal(value = TemporalType.DATE)
    private Date previousUserReturnDate;
}
