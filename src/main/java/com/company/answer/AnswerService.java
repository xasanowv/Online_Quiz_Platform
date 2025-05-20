package com.company.answer;

import com.company.answer.DTO.AnswerCr;
import com.company.answer.DTO.AnswerResp;
import com.company.question.QuestionEntity;
import com.company.question.QuestionRepository;
import com.company.userresponse.UserResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerResp create(AnswerCr answerCr) {
        QuestionEntity question = questionRepository.findById(answerCr.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        AnswerEntity answer = new AnswerEntity();
        answer.setQuestion(question);
        answer.setContent(answerCr.getContent());
        answer.setIsCorrect(answerCr.getIsCorrect());
        answer.setOrderNum(answerCr.getOrderNum());
        answer.setFeedback(answerCr.getFeedback());

        AnswerEntity savedAnswer = answerRepository.save(answer);
        return mapToAnswerResp(savedAnswer);
    }

    public AnswerResp getById(UUID id) {
        AnswerEntity answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        return mapToAnswerResp(answer);
    }

    public AnswerResp updateAns(AnswerCr answerCr, UUID id) {
        AnswerEntity answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        if (answerCr.getQuestionId() != null && !answerCr.getQuestionId().equals(answer.getQuestion().getId())) {
            QuestionEntity question = questionRepository.findById(answerCr.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            answer.setQuestion(question);
        }

        answer.setContent(answerCr.getContent());
        answer.setIsCorrect(answerCr.getIsCorrect());
        answer.setOrderNum(answerCr.getOrderNum());
        answer.setFeedback(answerCr.getFeedback());

        AnswerEntity updatedAnswer = answerRepository.save(answer);
        return mapToAnswerResp(updatedAnswer);
    }

    public AnswerResp delete(UUID id) {
        AnswerEntity answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        answerRepository.delete(answer);
        return mapToAnswerResp(answer);
    }

    private AnswerResp mapToAnswerResp(AnswerEntity answer) {
        return AnswerResp.builder()
                .id(answer.getId())
                .questionId(answer.getQuestion().getId())
                .content(answer.getContent())
                .isCorrect(answer.getIsCorrect())
                .orderNum(answer.getOrderNum())
                .feedback(answer.getFeedback())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .build();
    }
}
