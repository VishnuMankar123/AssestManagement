package com.asset.resource_server.repository;

import com.asset.resource_server.entity.DriveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriveTypeRepository extends JpaRepository<DriveType, Integer> {
}