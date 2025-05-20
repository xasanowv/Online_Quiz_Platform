package com.company.auth.DTO;

import lombok.Data;

@Data
public class AuthVersificationCode {
    private String gmail;
    private String verificationCode;
}
