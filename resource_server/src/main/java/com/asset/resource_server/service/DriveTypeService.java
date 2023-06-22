package com.asset.resource_server.service;

import com.asset.resource_server.entity.DriveType;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.DriveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DriveTypeService {

    private final DriveTypeRepository driveTypeRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String driveType) {
        this.driveTypeRepository.save(DriveType.builder()
                .secondaryMemory(driveType)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newDriveType) {
        DriveType previous = this.driveTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DriveType", "id", id));

        previous.setSecondaryMemory(newDriveType);

        this.driveTypeRepository.save(previous);
    }

    public List<DriveType> fetchAll() {
        return this.driveTypeRepository.findAll()
                .stream()
                .toList();
    }

    public DriveType fetchById(Integer id) {
        return this.driveTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DriveType", "id", id));
    }

    public void deleteById(Integer id) {
        this.driveTypeRepository.deleteById(id);
    }
}
