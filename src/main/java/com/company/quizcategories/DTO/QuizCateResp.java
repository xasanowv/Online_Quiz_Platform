package com.company.quizcategories.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizCateResp {
    private UUID quizId;
    private UUID categoryId;
    private UUID id; 
    private String categoryName;
}
