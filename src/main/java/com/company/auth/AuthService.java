package com.company.auth;

import com.company.auth.DTO.AuthDto;
import com.company.auth.DTO.AuthResp;
import com.company.auth.DTO.AuthVersificationCode;
import com.company.auth.DTO.RegisterDto;
import com.company.exception.InvalidPasswordOrGmailException;
import com.company.exception.ItemNotFoundException;
import com.company.security.jwtUtil;
import com.company.users.Role;
import com.company.users.Status;
import com.company.users.User;
import com.company.users.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private  JavaMailSender javaMailSender;

//    public ResponseEntity<AuthResp> registerPassword(RegisterDto registerDto) {
//
//        Optional<User> optionalUser = userRepository
//                .findByGmail(registerDto.getGmail());
//
//        if (optionalUser.isPresent() && optionalUser.get().getStatus() == Status.ACTIVE) {
//            throw new RuntimeException("Item Already Exists!!!");
//        }
//
//        User user = User.builder()
//                .username(registerDto.getUsername())
//                .role(Role.ADMIN)
//                .status(Status.ACTIVE)
//                .verificationExpiry(LocalDateTime.now())
//                .verificationCode(String.valueOf(new AuthVersificationCode()))
//                .gmail(registerDto.getGmail())
//                .password(bCryptPasswordEncoder.encode(registerDto.getPassword()))
//                .build();
//
//        User saved = userRepository.save(user);
//
//        return ResponseEntity.ok(
//                new AuthResp(
//                        user.getGmail(),
//                        jwtUtil.encode(user.getGmail(), user.getRole())
//                )
//        );
//
//    }


    public ResponseEntity<?> registerGmail(RegisterDto registerDto) {
        try {
            // Generate verification code
            Random random = new Random();
            String verificationCodeTemp = String.valueOf(100000 + random.nextInt(900000));
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

            System.out.println("Generated verification code: " + verificationCodeTemp);

            // Check if user exists
            Optional<User> byGmail = userRepository.findByGmail(registerDto.getGmail());
            User user;

            if (byGmail.isPresent()) {
                User existingUser = byGmail.get();
                if (existingUser.getStatus() == Status.ACTIVE) {
                    return ResponseEntity.badRequest().body("Bu gmail allaqachon ro'yxatdan o'tgan");
                }

                // Update existing user
                existingUser.setUsername(registerDto.getUsername());
                existingUser.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
                existingUser.setVerificationCode(verificationCodeTemp);
                existingUser.setVerificationExpiry(expiryTime);
                existingUser.setStatus(Status.REGISTERED);

                user = userRepository.save(existingUser);
            } else {
                // Create new user
                user = User.builder()
                        .username(registerDto.getUsername())
                        .gmail(registerDto.getGmail())
                        .password(bCryptPasswordEncoder.encode(registerDto.getPassword()))
                        .role(Role.USER)
                        .verificationCode(verificationCodeTemp)
                        .verificationExpiry(expiryTime)
                        .status(Status.REGISTERED)
                        .build();

                user = userRepository.save(user);
            }

            System.out.println("User saved: " + user);

            // Send verification email
            boolean emailSent = sendVerificationEmail(user.getGmail(), verificationCodeTemp);

            if (emailSent) {
                return ResponseEntity.ok("Gmail ga xabar yuborildi");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Kodni yuborishda xatolik yuz berdi");
            }

        } catch (Exception ex) {
            System.err.println("Error during registration: " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ro'yxatdan o'tishda xatolik yuz berdi: " + ex.getMessage());
        }
    }

    private boolean sendVerificationEmail(String to, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Verification Code");
            helper.setText("Kodni hech kimga aytmang bu kod bir martalik \n" + code);

            System.out.println("Attempting to send email to: " + to);
            javaMailSender.send(message);
            System.out.println("Email sent successfully to: " + to);

            return true;
        } catch (MessagingException ex) {
            System.err.println("Failed to send email: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }


    public ResponseEntity<?> registerGmailVerificationCode (AuthVersificationCode authVersificationCode){
            User user = userRepository.findByGmail(authVersificationCode.getGmail()).orElseThrow(ItemNotFoundException::new);

            if (user.getGmail() != null &&
                    user.getVerificationCode() != null &&
                    user.getStatus() == Status.REGISTERED) {

                if (user.getVerificationExpiry().isBefore(LocalDateTime.now())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kod muddati tugagan");
                }

                if (!user.getVerificationCode().equals(authVersificationCode.getVerificationCode())) {
                    return ResponseEntity.badRequest().body("Kod noto‘g‘ri");
                }

                user.setStatus(Status.ACTIVE);
                user.setVerificationCode(null);
                user.setVerificationExpiry(null);
                userRepository.save(user);

                return ResponseEntity.ok(new AuthResp(
                        user.getGmail(),
                        jwtUtil.encode(user.getGmail(), user.getRole())
                ));
            }

            if (user.getStatus() == Status.ACTIVE) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("siz ro`yxatdan o`tgansiz");
            }

            if (user.getStatus() == Status.INACTIVE) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Siz ro`yxatdan o`tmagansiz");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xatolik");
        }


    public ResponseEntity<AuthResp> login(AuthDto authDto) {

        User user =  getByGmail(authDto.getGmail());

        if (user.getGmail().equals(authDto.getGmail()) &&
        bCryptPasswordEncoder.matches(authDto.getPassword(), (user.getPassword()))) {

            return ResponseEntity.ok(
                    new AuthResp(
                            user.getGmail(),
                            jwtUtil.encode(user.getGmail(), user.getRole())
                    )
            );

        }

        throw new InvalidPasswordOrGmailException();

    }

    private User getByGmail(String gmail) {
        return userRepository
                .findByGmail(gmail)
                .orElseThrow(ItemNotFoundException::new);
    }



}
