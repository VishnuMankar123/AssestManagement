package com.inventory.assetmanagement.repository;

import com.inventory.assetmanagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
