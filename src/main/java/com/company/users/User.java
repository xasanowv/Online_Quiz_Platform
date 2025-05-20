package com.company.users;

import com.company.quiz.QuizEntity;
import com.company.userquizresult.UserQuizResultEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String gmail;
    @Column(nullable = false)
    private String password;

    private String verificationCode;
    private LocalDateTime verificationExpiry;


    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isActive;

    @OneToMany(mappedBy = "creator")
    private List<QuizEntity> quizzes;

    @OneToMany(mappedBy = "user")
    private List<UserQuizResultEntity> results;

}