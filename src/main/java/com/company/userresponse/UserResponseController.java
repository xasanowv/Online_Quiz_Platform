package com.company.userresponse;

import com.company.userresponse.DTO.UserResponsResp;
import com.company.userresponse.DTO.UserResponseCr;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/userResponse")
@RequiredArgsConstructor
public class UserResponseController {

    private final UserResponseService userResponseService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponsResp create(@RequestBody UserResponseCr cr) {
        return userResponseService.create(cr);
    }

    @GetMapping("/{id}")
    public UserResponsResp getById(@PathVariable UUID id) {
        return userResponseService.getById(id);
    }

    @GetMapping
    public List<UserResponsResp> getAll() {
        return userResponseService.getAll();
    }

    @PutMapping("/{id}")
    public UserResponsResp update(@PathVariable UUID id, @RequestBody UserResponseCr cr) {
        return userResponseService.update(id, cr);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        userResponseService.delete(id);
    }
}
