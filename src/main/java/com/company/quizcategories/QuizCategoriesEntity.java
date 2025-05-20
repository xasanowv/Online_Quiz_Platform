package com.company.quizcategories;


import com.company.category.CategoryEntity;
import com.company.companent.BaseMapper;
import com.company.quiz.QuizEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Table(name = "quiz_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizCategoriesEntity extends BaseMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuizEntity quiz;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
