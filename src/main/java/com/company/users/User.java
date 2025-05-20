package com.company.users;

import com.company.quiz.QuizEntity;
import com.company.userquizresult.UserQuizResultEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User  implements UserDetails  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false,unique = true)
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return gmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
