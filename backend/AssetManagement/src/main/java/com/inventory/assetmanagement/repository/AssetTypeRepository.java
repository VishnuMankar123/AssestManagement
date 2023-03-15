package com.inventory.assetmanagement.repository;

import com.inventory.assetmanagement.model.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {
}
