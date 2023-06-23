package com.asset.resource_server.service;

import com.asset.resource_server.entity.Location;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String newCity) {
        this.locationRepository.save(Location.builder()
                .city(newCity)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String city) {
        Location previous = this.locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));

        previous.setCity(city);

        this.locationRepository.save(previous);
    }

    public List<Location> fetchAll() {
        return this.locationRepository.findAll()
                .stream()
                .toList();
    }

    public Location findById(Integer id) {
        return this.locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));
    }

    public void deleteById(Integer id) {
        this.locationRepository.deleteById(id);
    }
}
