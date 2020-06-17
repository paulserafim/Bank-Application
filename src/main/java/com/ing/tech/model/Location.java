package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "loginEntry")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double latCoordinate;
    private Double longCoordinate;
    private String address;

    @OneToOne(mappedBy = "location")
    private LoginEntry loginEntry;

    public Location (Double latCoordinate, Double longCoordinate) {
        this.latCoordinate = latCoordinate;
        this.longCoordinate = longCoordinate;
    }

    public Location(Double latCoordinate, Double longCoordinate, String address) {
        this.latCoordinate = latCoordinate;
        this.longCoordinate = longCoordinate;
        this.address = address;
    }
}
