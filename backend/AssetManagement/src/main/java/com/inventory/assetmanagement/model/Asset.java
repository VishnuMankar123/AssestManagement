package com.inventory.assetmanagement.model;

import com.inventory.assetmanagement.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "department")
    private String department;

    @Column(name = "is_asset_allocated")
    private Boolean isAssetAllocated;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "asset_type_id", referencedColumnName = "id")
    private AssetType assetType;

    @Column(name = "assign_date")
    @Temporal(value = TemporalType.DATE)
    private Date assignDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "operating_system_id", referencedColumnName = "id")
    private OperatingSystem operatingSystem;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

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
