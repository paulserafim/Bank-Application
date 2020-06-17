package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    private String accountNumber;
    private String password;
    private Location location;
    private LocalDateTime dateTime;
}
