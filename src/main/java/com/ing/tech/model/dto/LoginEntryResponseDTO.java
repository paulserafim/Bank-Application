package com.ing.tech.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginEntryResponseDTO {
    private Long id;
    private ClientResponseDTO client;
    private LocationResponseDTO location;
    private LocalDateTime loginDateTime;
}
