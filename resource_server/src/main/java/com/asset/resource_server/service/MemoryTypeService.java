package com.asset.resource_server.service;

import com.asset.resource_server.entity.MemoryType;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.MemoryTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemoryTypeService {

    private final MemoryTypeRepository memoryTypeRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String newMemoryType) {
        this.memoryTypeRepository.save(MemoryType.builder()
                .primaryMemory(newMemoryType)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newMemoryType) {
        MemoryType previous = this.memoryTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MemoryType", "id", id));

        previous.setPrimaryMemory(newMemoryType);

        this.memoryTypeRepository.save(previous);
    }

    public List<String> fetchAll() {
        return this.memoryTypeRepository.findAll()
                .stream()
                .map(MemoryType::getPrimaryMemory)
                .toList();
    }

    public MemoryType fetchById(Integer id) {
        return this.memoryTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MemoryType", "id", id));
    }

    public void deleteById(Integer id) {
        this.memoryTypeRepository.deleteById(id);
    }
}
