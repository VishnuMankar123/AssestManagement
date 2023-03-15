package com.inventory.assetmanagement.repository;

import com.inventory.assetmanagement.model.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {
    boolean existsByType(String type);
}
