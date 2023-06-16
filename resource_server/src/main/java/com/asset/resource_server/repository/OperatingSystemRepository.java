package com.asset.resource_server.repository;

import com.asset.resource_server.entity.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Integer> {
}