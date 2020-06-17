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
public class TransactionResponseDTO {
    private Long transactionId;

    private String fromAccount;
    private String toAccount;
    private double amount;
    private String description;
    private LocalDateTime dateTime;
    private Long loginEntryId;

    public TransactionResponseDTO(Long transactionId, String fromAccount, String toAccount, double amount, String description, LocalDateTime dateTime) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
    }
}
