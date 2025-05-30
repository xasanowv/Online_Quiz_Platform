package com.company.answer.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerCr {
    private UUID questionId;
    private String content;
    private Boolean isCorrect;
    private Integer orderNum;
    private String feedback;

}
