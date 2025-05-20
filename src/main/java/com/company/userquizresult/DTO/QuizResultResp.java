package com.company.userquizresult.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResultResp {
    private UUID id;
    private UUID userId;
    private String userName;
    private UUID quizId;
    private String quizTitle;
    private Double score;
    private LocalDateTime completedAt;
    private Integer responseCount;
}
