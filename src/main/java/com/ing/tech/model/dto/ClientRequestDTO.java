package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRequestDTO {
    private String firstName;
    private String lastName;
    private CredentialsRequestDTO credentials;
    private AccountRequestDTO account;
}
