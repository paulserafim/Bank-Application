package com.ing.tech.controller;

import com.ing.tech.model.dto.LocationRequestDTO;
import com.ing.tech.model.dto.LocationResponseDTO;
import com.ing.tech.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/location")
@CrossOrigin(origins = "http://localhost:8081")
public class LocationController {

    private LocationService locationService;

    public LocationController (LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getLocation(@PathVariable Long id) {
        LocationResponseDTO locationResponseDTO = locationService.getLocationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(locationResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLocation(@PathVariable Long id) {
        boolean locationExists = locationService.deleteLocation(id);
        if(locationExists)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/new")
    public ResponseEntity save(@RequestBody LocationRequestDTO locationRequestDTO) {
        LocationResponseDTO location = locationService.save(locationRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(location);
    }
}