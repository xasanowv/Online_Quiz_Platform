package com.company.quiz.dto;

import com.company.question.DTO.QuestionCr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCr {
    private String title;
    private String description;
    private Long creatorId;
    private Long categoryId;
    private List<Long> categoryIds;
    private List<QuestionCr> questions;
}
