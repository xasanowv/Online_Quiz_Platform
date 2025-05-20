package com.company.users.dto;

import com.company.users.Role;

public record UserResp(
        String gmail,
        Role role
) {

}
