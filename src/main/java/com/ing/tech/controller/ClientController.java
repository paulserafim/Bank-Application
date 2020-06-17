package com.ing.tech.controller;

import com.ing.tech.model.dto.ClientRequestDTO;
import com.ing.tech.model.dto.ClientResponseDTO;
import com.ing.tech.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
@CrossOrigin(origins = "http://localhost:8081")
public class ClientController {

    private ClientService clientService;

    public ClientController (ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getClientById(@PathVariable Long id) {
        ClientResponseDTO clientResponseDTO = clientService.getClientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientById(@PathVariable Long id) {
        boolean clientExists = clientService.deleteClient(id);
        if(clientExists)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/custom")
    public ResponseEntity getClientByAccountNumber(@RequestParam String accountNumber) {

        ClientResponseDTO clientResponseDTO = clientService.getClientByAccountNumber(accountNumber);

        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO savedClient = clientService.save(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@RequestBody ClientRequestDTO clientRequestDTO, @PathVariable Long id) {
        ClientResponseDTO updatedClient = clientService.update(clientRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedClient);
    }

}