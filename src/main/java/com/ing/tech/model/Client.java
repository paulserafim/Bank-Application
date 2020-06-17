package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn
    private Credentials credentials;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Account account;


    public Client(String firstName, String lastName, Credentials credentials, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
        this.account = account;
    }


    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
