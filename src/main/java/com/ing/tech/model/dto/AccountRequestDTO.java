package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequestDTO {
    private String number;
    private double balance;
    private Long clientId;

    public AccountRequestDTO (String number, double balance) {
        this.number = number;
        this.balance = balance;
    }
}
