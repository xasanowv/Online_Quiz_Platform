package com.company.auth;



import com.company.auth.DTO.AuthDto;
import com.company.auth.DTO.AuthResp;
import com.company.auth.DTO.AuthVersificationCode;
import com.company.auth.DTO.RegisterDto;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    //authorization
    private final AuthService authService;

//    @PermitAll
//    @PostMapping("/register")
//    public ResponseEntity<AuthResp> register(@RequestBody RegisterDto registerDto) {
//        return authService.registerPassword(registerDto);
//    }

    @PermitAll
    @PostMapping("/register")
    public ResponseEntity<?> registerGmail(@RequestBody RegisterDto registerDto) {
        return authService.registerGmail(registerDto);
    }

    @PermitAll
    @PostMapping("/registerVerificationCode")
    public ResponseEntity<?> registerGmailVerificationCode(@RequestBody AuthVersificationCode authVersificationCode) {
        return  authService.registerGmailVerificationCode(authVersificationCode);
    }

    @PermitAll
    @PostMapping("/login")
    public ResponseEntity<AuthResp> login(@RequestBody AuthDto authDto) {
        return authService.login(authDto);
    }

}
