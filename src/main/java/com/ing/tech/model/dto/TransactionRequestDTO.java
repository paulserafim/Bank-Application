package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDTO {

    private String fromAccount;
    private String toAccount;
    private double amount;
    private String description;
    private Long loginEntryId;

    public TransactionRequestDTO(String fromAccount, String toAccount, double amount, String description, LocalDateTime transactionTime) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.description = description;
    }
}
