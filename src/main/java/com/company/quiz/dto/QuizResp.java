package com.company.quiz.dto;

import com.company.category.dto.CategoryResp;
import com.company.question.DTO.QuestionResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResp {
    private UUID id;
    private String title;
    private String description;
    private UUID creatorId;
    private String creatorName;
    private UUID categoryId;
    private String categoryName;
    private List<CategoryResp> categories;
    private List<QuestionResp> questions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

