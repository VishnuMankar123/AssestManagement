package com.inventory.assetmanagement.repository;

import com.inventory.assetmanagement.model.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long> {
    boolean existsByOperatingSystemName(String operatingSystemName);
}
