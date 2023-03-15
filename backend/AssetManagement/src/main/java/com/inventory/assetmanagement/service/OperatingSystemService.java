package com.inventory.assetmanagement.service;

import com.inventory.assetmanagement.constant.EntityConstants;
import com.inventory.assetmanagement.exception.ResourceNotFoundException;
import com.inventory.assetmanagement.model.OperatingSystem;
import com.inventory.assetmanagement.repository.OperatingSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatingSystemService {
    private final OperatingSystemRepository operatingSystemRepository;

    public List<OperatingSystem> fetchAllOperatingSystems() {
        return operatingSystemRepository.findAll();
    }

    public String fetchOperatingSystemById(Long id) {
        OperatingSystem fetchedOperatingSystem = operatingSystemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.OPERATING_SYSTEM));
        return fetchedOperatingSystem.getOperatingSystemName();
    }

    public Long saveNewOperatingSystem(String newOperatingSystemName) {
        OperatingSystem newOperatingSystem = new OperatingSystem(newOperatingSystemName);
        return operatingSystemRepository.save(newOperatingSystem).getId();
    }

    @Transactional
    public void editOperatingSystem(Long id, String newName) {
        OperatingSystem fetchedOperatingSystem = operatingSystemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.OPERATING_SYSTEM));
        fetchedOperatingSystem.setOperatingSystemName(newName);
    }

    public void deleteOperatingSystem(Long id) {
        if (!operatingSystemRepository.existsById(id)) {
            throw new ResourceNotFoundException(EntityConstants.OPERATING_SYSTEM);
        }
        operatingSystemRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return operatingSystemRepository.existsByOperatingSystemName(name);
    }
}
