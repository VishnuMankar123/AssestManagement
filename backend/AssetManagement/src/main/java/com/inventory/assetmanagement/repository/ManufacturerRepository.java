package com.inventory.assetmanagement.repository;

import com.inventory.assetmanagement.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    boolean existsByManufacturerName(String manufacturerName);
}
