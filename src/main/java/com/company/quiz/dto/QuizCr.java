package com.company.quiz.dto;

import com.company.question.DTO.QuestionCr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCr {
    private String title;
    private String description;
    private UUID creatorId;
    private UUID categoryId;
    private List<UUID> categoryIds;
    private List<QuestionCr> questions;
}
