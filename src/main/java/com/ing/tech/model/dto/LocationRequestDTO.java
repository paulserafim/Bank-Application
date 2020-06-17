package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationRequestDTO {
    private Double latCoordinate;
    private Double longCoordinate;
    private String address;
}
