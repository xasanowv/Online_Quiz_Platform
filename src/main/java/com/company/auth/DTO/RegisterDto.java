package com.company.auth.DTO;

import com.company.users.Role;
import lombok.Data;

@Data
public class RegisterDto {

    private String username;
    private String gmail;
    private String password;
}
