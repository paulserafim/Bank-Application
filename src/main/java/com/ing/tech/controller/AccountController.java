package com.ing.tech.controller;

import com.ing.tech.model.dto.AccountRequestDTO;
import com.ing.tech.model.dto.AccountResponseDTO;
import com.ing.tech.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/account")
@CrossOrigin(origins = "http://localhost:8081")
public class AccountController {

    private AccountService accountService;

    public AccountController (AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAccount(@PathVariable String id) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountByAccountNumber(id);
        return ResponseEntity.status(HttpStatus.OK).body(accountResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity getAllAccounts() {
        Set<AccountResponseDTO> accountSet = accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accountSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAccount(@RequestBody AccountRequestDTO accountRequestDTO, @PathVariable String id) {
        AccountResponseDTO updatedAccount = accountService.update(accountRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAccount);
    }


}
