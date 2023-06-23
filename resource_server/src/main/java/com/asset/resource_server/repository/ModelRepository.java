package com.asset.resource_server.repository;

import com.asset.resource_server.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}