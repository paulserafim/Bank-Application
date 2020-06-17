package com.ing.tech.model.dto;

import com.ing.tech.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PotentialFraudRequestDTO {
    private Long loginEntryId;
    private Location firstLocation;
    private Location secondLocation;
    private Long estimatedArrivalTime;
    private Long actualArrivalTime;
    private boolean fraudSuspect;
}
