package com.ing.tech.controller;

import com.ing.tech.model.InvalidCredentialsMessage;
import com.ing.tech.model.LoginRequest;
import com.ing.tech.model.dto.CredentialsRequestDTO;
import com.ing.tech.model.dto.CredentialsResponseDTO;
import com.ing.tech.service.CredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(path = "/credentials")
@CrossOrigin(origins = "http://localhost:8081")
public class CredentialsController {

    private CredentialsService credentialsService;

    public CredentialsController (CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping
    public ResponseEntity getLoginAccept(@RequestBody LoginRequest loginRequest) throws IOException {
        if(credentialsService.getLoginAccept(loginRequest)) {
            return ResponseEntity.status(HttpStatus.OK).body(credentialsService.getLoggedClientDetails(loginRequest.getAccountNumber())); /*-> to be completed with other parameters return*/
        }
            else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InvalidCredentialsMessage("Invalid username or password"));
    }

    @PostMapping("/new")
    public ResponseEntity save(@RequestBody CredentialsRequestDTO credentialsRequestDTO) {
        CredentialsResponseDTO credentials = credentialsService.save(credentialsRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(credentials);
    }

    @GetMapping
    public ResponseEntity getCredentialsIdByAccountNumber(@RequestParam String accountNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(credentialsService.getCredentialsByAccountNumber(accountNumber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCredentials(@PathVariable Long id) {
        boolean credentialsExist = credentialsService.deleteCredentials(id);
        if(credentialsExist)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping()
    public ResponseEntity deleteCredentialsByAccountNumber(@RequestParam String accountNumber) {
        boolean credentialsExist = credentialsService.deleteCredentials(accountNumber);
        if(credentialsExist)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
