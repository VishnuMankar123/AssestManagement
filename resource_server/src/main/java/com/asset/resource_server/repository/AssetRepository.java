package com.asset.resource_server.repository;

import com.asset.resource_server.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    @Query(value = """
            SELECT asset
            FROM Asset asset
            WHERE asset.serialNumber = :serialNumber
            """)
    Optional<Asset> findBySerialNumber(String serialNumber);
}