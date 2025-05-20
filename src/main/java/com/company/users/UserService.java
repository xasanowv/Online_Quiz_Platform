package com.company.users;

import com.company.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> myProfile() {
        String gmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(userRepository.findByGmail(gmail)
                .orElseThrow(ItemNotFoundException::new));
    }


    public ResponseEntity<?> users() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<?> getById(int id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }
}
