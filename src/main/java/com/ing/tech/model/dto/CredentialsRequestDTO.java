package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredentialsRequestDTO {
    private String accountNumber;
    private String password;
}
