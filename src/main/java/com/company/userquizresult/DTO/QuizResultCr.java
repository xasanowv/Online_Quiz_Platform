package com.company.userquizresult.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizResultCr {
    private UUID userId;
    private UUID quizId;
    private Double score;
}
