package com.ing.tech.controller;

import com.ing.tech.model.dto.AccountResponseDTO;
import com.ing.tech.model.dto.LoginEntryRequestDTO;
import com.ing.tech.model.dto.LoginEntryResponseDTO;
import com.ing.tech.service.LoginEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/loginEntry")
@CrossOrigin(origins = "http://localhost:8081")
public class LoginEntryController {

    private LoginEntryService loginEntryService;

    public LoginEntryController (LoginEntryService loginEntryService) {
        this.loginEntryService = loginEntryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getLoginEntry(@PathVariable Long id) {
        LoginEntryResponseDTO loginEntryResponseDTO = loginEntryService.getLoginEntryByIdDTO(id);
        return ResponseEntity.status(HttpStatus.OK).body(loginEntryResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLocation(@PathVariable Long id) {
        boolean loginEntryExists = loginEntryService.deleteLoginEntry(id);
        if(loginEntryExists)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/new")
    public ResponseEntity save(@RequestBody LoginEntryRequestDTO loginEntryRequestDTO) {
        LoginEntryResponseDTO loginEntryResponseDTO = loginEntryService.save(loginEntryRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginEntryResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity getAllLoginEntries() {
        Set<LoginEntryResponseDTO> loginEntryResponseDTOSet = loginEntryService.getAllLoginEntries();
        return ResponseEntity.status(HttpStatus.OK).body(loginEntryResponseDTOSet);
    }
}