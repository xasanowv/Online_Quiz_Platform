package com.company.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public ResponseEntity<?> users() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<?> getById(int id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }
}
