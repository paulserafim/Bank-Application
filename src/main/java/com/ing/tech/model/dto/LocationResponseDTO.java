package com.ing.tech.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double latCoordinate;
    private Double longCoordinate;
    private String address;

    public LocationResponseDTO(Double latCoordinate, Double longCoordinate, String address) {
        this.latCoordinate = latCoordinate;
        this.longCoordinate = longCoordinate;
        this.address = address;
    }
}
