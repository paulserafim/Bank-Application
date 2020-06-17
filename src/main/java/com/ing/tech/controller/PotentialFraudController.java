package com.ing.tech.controller;

import com.ing.tech.model.dto.PotentialFraudRequestDTO;
import com.ing.tech.model.dto.PotentialFraudResponseDTO;
import com.ing.tech.service.PotentialFraudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/potentialFraud")
@CrossOrigin(origins = "http://localhost:8081")
public class PotentialFraudController {

    private PotentialFraudService potentialFraudService;

    public PotentialFraudController (PotentialFraudService potentialFraudService) {
        this.potentialFraudService = potentialFraudService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getPotentialFraud(@PathVariable Long id) {
        PotentialFraudResponseDTO potentialFraudResponseDTO = potentialFraudService.getPotentialFraudById(id);
        return ResponseEntity.status(HttpStatus.OK).body(potentialFraudResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePotentialFraud(@PathVariable Long id) {
        boolean potentialFraudExists = potentialFraudService.deletePotentialFraud(id);
        if(potentialFraudExists)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/new")
    public ResponseEntity save(@RequestBody PotentialFraudRequestDTO potentialFraudRequestDTO) {
        PotentialFraudResponseDTO potentialFraudResponseDTO = potentialFraudService.save(potentialFraudRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(potentialFraudResponseDTO);
    }
}