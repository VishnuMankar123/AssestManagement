package com.asset.resource_server.service;

import com.asset.resource_server.entity.Manufacturer;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String newManufacturerName) {
        this.manufacturerRepository.save(Manufacturer.builder()
                .manufacturerName(newManufacturerName)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newName) {
        Manufacturer previous = this.manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer", "id", id));

        previous.setManufacturerName(newName);

        this.manufacturerRepository.save(previous);
    }

    public List<String> fetchAll() {
        return this.manufacturerRepository.findAll()
                .stream()
                .map(Manufacturer::getManufacturerName)
                .toList();
    }

    public Manufacturer findById(Integer id) {
        return this.manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer", "id", id));
    }

    public void deleteById(Integer id) {
        this.manufacturerRepository.deleteById(id);
    }
}
