package com.company.category;

import com.company.companent.BaseMapper;
import com.company.quiz.QuizEntity;
import com.company.quizcategories.QuizCategoriesEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.*;


@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity extends BaseMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuizEntity> quizzes = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private List<QuizCategoriesEntity>  quizCategories;

    @OneToMany(mappedBy = "category")
    private List<QuizEntity> quizEntities;


}
