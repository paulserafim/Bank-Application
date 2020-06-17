package com.ing.tech.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PotentialFraudResponseDTO {
    private Long id;
    private Long loginEntryId;
    private LocationResponseDTO firstLocation;
    private LocationResponseDTO secondLocation;
    private Long estimatedArrivalTime;
    private Long actualArrivalTime;
    private boolean fraudSuspect;
}
