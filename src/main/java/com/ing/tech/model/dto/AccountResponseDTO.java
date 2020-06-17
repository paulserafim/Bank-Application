package com.ing.tech.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseDTO {
    private String number;
    private double balance;
    private Long clientId;

    public AccountResponseDTO(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }
}
