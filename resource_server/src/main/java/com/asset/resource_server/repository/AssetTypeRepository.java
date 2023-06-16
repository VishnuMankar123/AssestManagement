package com.asset.resource_server.repository;

import com.asset.resource_server.entity.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetTypeRepository extends JpaRepository<AssetType, Integer> {
}