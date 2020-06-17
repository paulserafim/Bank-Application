package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountNumber;
    private String password;

    @OneToOne (mappedBy = "credentials", cascade = CascadeType.ALL)
    private Client client;

    public Credentials (String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }
}
