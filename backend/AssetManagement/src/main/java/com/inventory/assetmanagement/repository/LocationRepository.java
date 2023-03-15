package com.inventory.assetmanagement.repository;

import com.inventory.assetmanagement.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByLocationName(String locationName);
}
