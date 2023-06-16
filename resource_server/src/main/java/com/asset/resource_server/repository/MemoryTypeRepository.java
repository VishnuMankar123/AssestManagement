package com.asset.resource_server.repository;

import com.asset.resource_server.entity.MemoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryTypeRepository extends JpaRepository<MemoryType, Integer> {
}