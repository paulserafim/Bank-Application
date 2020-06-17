package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ledger implements Comparable<Ledger> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private String fromAccount;
    private String toAccount;
    private double amount;
    private String description;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn
    private LoginEntry loginEntry;

    public Ledger(String fromAccount, String toAccount, double amount, String transactionDescription, LocalDateTime dateTime, LoginEntry loginEntry) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.description = transactionDescription;
        this.dateTime = dateTime;
        this.loginEntry = loginEntry;
    }

    @Override
    public int compareTo(Ledger other) {
        return this.getTransactionId().compareTo(other.getTransactionId());
    }
}
