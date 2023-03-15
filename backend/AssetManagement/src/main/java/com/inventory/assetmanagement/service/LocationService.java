package com.inventory.assetmanagement.service;

import com.inventory.assetmanagement.constant.EntityConstants;
import com.inventory.assetmanagement.exception.ResourceNotFoundException;
import com.inventory.assetmanagement.model.Location;
import com.inventory.assetmanagement.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private LocationRepository locationRepository;

    public List<Location> fetchAllLocations() {
        return locationRepository.findAll();
    }

    public String fetchLocationById(Long id) {
        Location fetchedLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.LOCATION, id));
        return fetchedLocation.getLocationName();
    }

    public Long saveNewLocation(String newLocation) {
        Location location = new Location(newLocation);
        return locationRepository.save(location).getId();
    }

    @Transactional
    public void editLocationById(Long id, String newLocation) {
        Location fetchedLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityConstants.LOCATION, id));
        fetchedLocation.setLocationName(newLocation);
    }

    public void deleteLocationById(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new ResourceNotFoundException(EntityConstants.LOCATION, id);
        }
        locationRepository.deleteById(id);
    }
}
