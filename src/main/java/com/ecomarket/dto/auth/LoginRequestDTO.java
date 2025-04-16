package com.ecomarket.dto.auth;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}