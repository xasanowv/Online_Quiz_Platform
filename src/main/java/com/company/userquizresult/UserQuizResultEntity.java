package com.company.userquizresult;

import com.company.companent.BaseMapper;
import com.company.quiz.QuizEntity;
import com.company.userresponse.UserResponseEntity;
import com.company.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_quiz_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuizResultEntity extends BaseMapper {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @Column(nullable = false)
    private Double score;

    @Column(name = "completed_at")
    @CreationTimestamp
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserResponseEntity> responses = new ArrayList<>();
}
