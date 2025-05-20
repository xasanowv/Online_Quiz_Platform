package com.company.userresponse.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponsResp {
    private UUID id;
    private UUID resultId;
    private UUID questionId;
    private String questionContent;
    private UUID answerId;
    private String answerContent;
    private String textResponse;
    private Boolean isCorrect;
}
