package com.ing.tech.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = "client")
public class Account {
    @Id
    private String number;

    private double balance;

    @OneToOne (mappedBy = "account")
    private Client client;

    public Account (String number, double balance) {
        this.number = number;
        this.balance = balance;
    }
}
