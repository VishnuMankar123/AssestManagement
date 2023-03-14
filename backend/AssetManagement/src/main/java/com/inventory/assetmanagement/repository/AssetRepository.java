package com.inventory.assetmanagement.repository;

import com.inventory.assetmanagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
