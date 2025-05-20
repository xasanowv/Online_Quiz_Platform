package com.company.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<?> users() {
        return userService.users();
    }


    @GetMapping("/admin/user/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return userService.getById(id);
    }

}
