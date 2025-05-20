package com.company.answer.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResp {
    private UUID id;
    private UUID questionId;
    private String content;
    private Boolean isCorrect;
    private Integer orderNum;
    private String feedback;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
