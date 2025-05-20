package com.company.answer;

import com.company.answer.DTO.AnswerCr;
import com.company.answer.DTO.AnswerResp;
import com.company.userresponse.UserResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerResp create(AnswerCr answerCr) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setContent(answerCr.getContent());
        answerEntity.setIsCorrect(answerCr.);
    }
    public AnswerResp getById(UUID id) {

    }

    public AnswerResp updateAns(AnswerCr answerCr, UUID id) {

    }

    public AnswerResp delete(UUID id) {
        return null;
    }
}
