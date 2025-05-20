package com.company.auth.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthResp {

    private String gmail;
    private String token;
    private UUID id;

    public AuthResp(String gmail, String token) {
        this.gmail = gmail;
        this.token = token;
    }
}
