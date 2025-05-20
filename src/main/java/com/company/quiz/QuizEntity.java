package com.company.quiz;

import com.company.category.CategoryEntity;
import com.company.companent.BaseMapper;
import com.company.question.QuestionEntity;
import com.company.userquizresult.UserQuizResultEntity;
import com.company.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizEntity extends BaseMapper {
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private Integer timeLimit;
    
    @Column(nullable = false)
    private Boolean isPublished;
    
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();
}
