package com.asset.resource_server.repository;

import com.asset.resource_server.entity.Processor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessorRepository extends JpaRepository<Processor, Integer> {
}