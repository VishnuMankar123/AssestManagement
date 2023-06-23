package com.asset.resource_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memory_type")
public class MemoryType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "primary_memory", unique = true)
    private String primaryMemory;
}
