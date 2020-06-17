package com.ing.tech.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private AccountResponseDTO account;
    private LocalDateTime lastTimeLogin;
    private String lastLoginLocation;
    private Long loginEntryId;


    public ClientResponseDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ClientResponseDTO(Long id, String firstName, String lastName, AccountResponseDTO account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }


    public ClientResponseDTO(Long id, String firstName, String lastName, AccountResponseDTO accountResponseDTO, Long loginEntryId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = accountResponseDTO;
        this.loginEntryId = loginEntryId;
    }

    public ClientResponseDTO(Long id, String firstName, String lastName, AccountResponseDTO accountResponseDTO, LocalDateTime dateTime, String lastLoginLocation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = accountResponseDTO;
        this.lastTimeLogin = dateTime;
        this.lastLoginLocation = lastLoginLocation;
    }
}
