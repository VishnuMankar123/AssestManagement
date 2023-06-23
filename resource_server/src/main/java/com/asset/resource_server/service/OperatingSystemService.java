package com.asset.resource_server.service;

import com.asset.resource_server.entity.OperatingSystem;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.OperatingSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OperatingSystemService {

    private final OperatingSystemRepository operatingSystemRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String newOperatingSystem) {
        this.operatingSystemRepository.save(OperatingSystem.builder()
                .operatingSystemName(newOperatingSystem)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newOperatingSystem) {
        OperatingSystem previous = this.operatingSystemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OperatingSystem", "id", id));

        previous.setOperatingSystemName(newOperatingSystem);

        this.operatingSystemRepository.save(previous);
    }

    public List<OperatingSystem> fetchAll() {
        return this.operatingSystemRepository.findAll()
                .stream()
                .toList();
    }

    public OperatingSystem fetchById(Integer id) {
        return this.operatingSystemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OperatingSystem", "id", id));
    }

    public void deleteById(Integer id) {
        this.operatingSystemRepository.deleteById(id);
    }
}
