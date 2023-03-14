package com.inventory.assetmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "operating_system")
@NoArgsConstructor
public class OperatingSystem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operating_system_name")
    private String operatingSystemName;

    public OperatingSystem(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }
}
