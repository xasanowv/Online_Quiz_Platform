package com.company.quizcategories;

import com.company.category.CategoryEntity;
import com.company.category.CategoryRepository;
import com.company.quiz.QuizEntity;
import com.company.quiz.QuizRepository;
import com.company.quiz.dto.QuizCr;
import com.company.quizcategories.DTO.QuizCateCR;
import com.company.quizcategories.DTO.QuizCateResp;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuizCateService {

    private final QuizCateRepository quizCateRepository;
    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;

    public QuizCateResp create(QuizCateCR quizCateCR) {
        QuizEntity quiz = quizRepository.findById(quizCateCR.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        CategoryEntity category = categoryRepository.findById(quizCateCR.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        QuizCategoriesEntity quizCategory = QuizCategoriesEntity
                .builder()
                .quiz(quiz)
                .category(category)
                .build();

        quizCategory = quizCateRepository.save(quizCategory);
        return mapToResponse(quizCategory);
    }

    public QuizCateResp getById(UUID id) {
    return quizCateRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));
    }


    public List<QuizCateResp> getAll() {
        return quizCateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public QuizCateResp update(UUID id, QuizCateCR cr) {
        QuizCategoriesEntity quizCategory = findQuizCategoryById(id);

        QuizEntity quiz = quizRepository.findById(cr.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        CategoryEntity category = categoryRepository.findById(cr.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        quizCategory.setQuiz(quiz);
        quizCategory.setCategory(category);

        quizCategory = quizCateRepository.save(quizCategory);
        return mapToResponse(quizCategory);
    }


    public void delete(UUID id) {
        QuizCategoriesEntity quizCategory = findQuizCategoryById(id);
        quizCateRepository.delete(quizCategory);
    }


    private QuizCategoriesEntity findQuizCategoryById(UUID id) {
        return quizCateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz category not found"));
    }

    private QuizCateResp mapToResponse(QuizCategoriesEntity entity) {
        return QuizCateResp.builder()
                .id(entity.getId())
                .quizId(entity.getQuiz().getId())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getName())
                .build();
    }
}
