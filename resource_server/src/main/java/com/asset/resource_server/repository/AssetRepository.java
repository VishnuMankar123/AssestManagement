package com.asset.resource_server.repository;

import com.asset.resource_server.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    @Query(value = """
            SELECT asset
            FROM Asset asset
            WHERE asset.name = :name
            """)
    Optional<Asset> findByName(String name);

    @Query(value = """
            SELECT asset
            FROM Asset asset
            WHERE asset.serialNumber = :serialNumber
            """)
    Optional<Asset> findBySerialNumber(String serialNumber);
}