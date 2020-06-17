package com.ing.tech.service;

import com.ing.tech.model.Location;
import com.ing.tech.model.dto.LocationRequestDTO;
import com.ing.tech.model.dto.LocationResponseDTO;
import com.ing.tech.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationResponseDTO getLocationById(Long id) {
        Location location = locationRepository.getLocationById(id);
        return new LocationResponseDTO(
                location.getId(),
                location.getLatCoordinate(),
                location.getLongCoordinate(),
                location.getAddress()
        );

    }

    public boolean deleteLocation(Long id) {
        if(locationRepository.existsById(id)) {
            locationRepository.delete(locationRepository.getLocationById(id));
            return true;
        }
        else
            return false;
    }

    public LocationResponseDTO save(LocationRequestDTO locationRequestDTO) {
        Location location = locationRepository.save(
                new Location (
                        locationRequestDTO.getLatCoordinate(),
                        locationRequestDTO.getLongCoordinate(),
                        locationRequestDTO.getAddress()
                )
        );

        return new LocationResponseDTO(
                location.getId(),
                location.getLatCoordinate(),
                location.getLongCoordinate(),
                location.getAddress()
        );
    }

    public Location getLocationByIdDAO(Long id) {
        return locationRepository.getLocationById(id);
    }
}
