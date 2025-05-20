package com.company;

import com.company.users.Role;
import com.company.users.dto.UserResp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

public class jwtUtil {

        private static final int TOKEN_LIFETIME = 1000 * 3600 * 24; // 1-day
        private static final String SECRET_KEY = "veryLongSecretmazgillattayevlasharaaxmojonjinnijonsurbetbekkiydirhonuxlatdibekloxovdangasabekochkozjonduxovmashaynikmaydagapchishularnioqiganbolsangizgapyoqaniqsizmazgi";

        public static String encode(String gmail, Role role) {

            String token = Jwts.builder()
                    .setSubject(gmail)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIFETIME))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();

            return token;
        }

        public static UserResp decode(String token) {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String gmail = body.getSubject();
            List<String> authorities = (List<String>) body.get("authorities");
            String role = authorities.get(0).replace("ROLE_", "");

            return new UserResp(gmail, Role.valueOf(role));
        }
}
