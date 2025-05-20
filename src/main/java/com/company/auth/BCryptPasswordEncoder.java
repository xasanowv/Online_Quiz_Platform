package com.company.auth;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BCryptPasswordEncoder {

    // Parolni encode qilish
    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Parolni tekshirish (decode)
    public static boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
