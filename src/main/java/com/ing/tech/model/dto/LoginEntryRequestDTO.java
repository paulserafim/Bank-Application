package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntryRequestDTO {
    private ClientResponseDTO client;
    private LocationResponseDTO location;
    private LocalDateTime loginDateTime;
}
