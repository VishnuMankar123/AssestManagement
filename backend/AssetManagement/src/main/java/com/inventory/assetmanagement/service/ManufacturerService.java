package com.inventory.assetmanagement.service;

import com.inventory.assetmanagement.constant.EntityConstants;
import com.inventory.assetmanagement.exception.ResourceNotFoundException;
import com.inventory.assetmanagement.model.Manufacturer;
import com.inventory.assetmanagement.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> fetchAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public String fetchManufacturerById(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.MANUFACTURER, id));
        return manufacturer.getManufacturerName();
    }

    public Long saveNewManufacturer(String manufacturerName) {
        Manufacturer newManufacturer = new Manufacturer(manufacturerName);
        return manufacturerRepository.save(newManufacturer).getId();
    }

    @Transactional
    public void editManufacturer(Long id, String newName) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.MANUFACTURER, id));
        manufacturer.setManufacturerName(newName);
    }

    public void deleteManufacturerById(Long id) {
        if (!manufacturerRepository.existsById(id)) {
            throw new ResourceNotFoundException(EntityConstants.MANUFACTURER, id);
        }
        manufacturerRepository.deleteById(id);
    }
}
