package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoginEntry implements Comparable<LoginEntry>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Location location;

    @OneToOne(mappedBy = "loginEntry",cascade = CascadeType.ALL)
    @JoinColumn
    private PotentialFraud potentialFraud;

    @OneToMany(mappedBy = "loginEntry", cascade = CascadeType.ALL)
    private Set<Ledger> ledgerSet;

    private LocalDateTime dateTime;

    public LoginEntry(Client client, Location location, LocalDateTime dateTime) {
        this.client = client;
        this.location = location;
        this.dateTime = dateTime;
    }

    @Override
    public int compareTo(LoginEntry other) {
        return this.getId().compareTo(other.getId());
    }
}
