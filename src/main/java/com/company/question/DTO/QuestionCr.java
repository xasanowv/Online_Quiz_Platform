package com.company.question.DTO;

import com.company.question.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCr {
    private UUID quizId;
    private String content;
    private QuestionType type;
    private Integer points;
}
