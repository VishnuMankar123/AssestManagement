package com.asset.resource_server.repository;

import com.asset.resource_server.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}