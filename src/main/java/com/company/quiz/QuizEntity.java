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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizEntity extends BaseMapper {

    @Column(unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<UserQuizResultEntity> results = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;



    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;



    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();
}
