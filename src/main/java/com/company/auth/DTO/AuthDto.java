package com.company.auth.DTO;

import com.company.users.Role;
import lombok.Data;

@Data
public class AuthDto {
    private String gmail;
    private String password;
}
