package com.asset.resource_server.entity;

import com.asset.resource_server.embeddable.Components;
import com.asset.resource_server.enumeration.AssetAllocated;
import com.asset.resource_server.enumeration.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset")
public class Asset {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "employee_id")
    private String employeeId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Enumerated(value = ORDINAL)
    @Column(name = "asset_allocated")
    private AssetAllocated assetAllocated;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private AssetType assetType;

    @Column(name = "assign_date")
    private LocalDate assignDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "operating_system_id", referencedColumnName = "id")
    private OperatingSystem operatingSystem;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Embedded
    private Components components;

    @Column(name = "warranty_start_date")
    private LocalDate warrantyStartDate;

    @Column(name = "warranty_expiry_date")
    private LocalDate warrantyExpiryDate;

    @Enumerated(value = ORDINAL)
    @Column(name = "status")
    private Status status;

    @Column(name = "previous_user_asset_return_date")
    private LocalDate previousUserAssetReturnDate;
}
