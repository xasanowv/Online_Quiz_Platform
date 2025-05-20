package com.company.userresponse;

import com.company.answer.AnswerEntity;
import com.company.answer.AnswerRepository;
import com.company.question.QuestionEntity;
import com.company.question.QuestionRepository;
import com.company.userquizresult.QuizResultRepository;
import com.company.userquizresult.UserQuizResultEntity;
import com.company.userresponse.DTO.UserResponsResp;
import com.company.userresponse.DTO.UserResponseCr;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserResponseService {

    private UserResponseRepository userResponseRepository;
    private final QuizResultRepository quizResultRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public UserResponsResp create(UserResponseCr cr) {
        UserQuizResultEntity result = quizResultRepository.findById(cr.getResultId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz result not found"));

        QuestionEntity question = questionRepository.findById(cr.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        AnswerEntity answer = null;
        if (cr.getAnswerId() != null) {
            answer = answerRepository.findById(cr.getAnswerId())
                    .orElseThrow(() -> new EntityNotFoundException("Answer not found"));
        }

        UserResponseEntity response = UserResponseEntity.builder()
                .result(result)
                .question(question)
                .answer(answer)
                .textResponse(cr.getTextResponse())
                .isCorrect(cr.getIsCorrect())
                .build();

        response = userResponseRepository.save(response);
        return mapToResponse(response);
    }

    public UserResponsResp getById(UUID id) {
        return mapToResponse(findResponseById(id));
    }

    public List<UserResponsResp> getAll() {
        return userResponseRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponsResp update(UUID id, UserResponseCr cr) {
        UserResponseEntity response = findResponseById(id);

        UserQuizResultEntity result = quizResultRepository.findById(cr.getResultId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz result not found"));

        QuestionEntity question = questionRepository.findById(cr.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        AnswerEntity answer = null;
        if (cr.getAnswerId() != null) {
            answer = answerRepository.findById(cr.getAnswerId())
                    .orElseThrow(() -> new EntityNotFoundException("Answer not found"));
        }

        response.setResult(result);
        response.setQuestion(question);
        response.setAnswer(answer);
        response.setTextResponse(cr.getTextResponse());
        response.setIsCorrect(cr.getIsCorrect());

        response = userResponseRepository.save(response);
        return mapToResponse(response);
    }

    public void delete(UUID id) {
        UserResponseEntity response = findResponseById(id);
        userResponseRepository.delete(response);
    }

    private UserResponseEntity findResponseById(UUID id) {
        return userResponseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User response not found"));
    }

    private UserResponsResp mapToResponse(UserResponseEntity entity) {
        return UserResponsResp.builder()
                .id(entity.getId())
                .resultId(entity.getResult().getId())
                .questionId(entity.getQuestion().getId())
                .questionContent(entity.getQuestion().getContent())
                .answerId(entity.getAnswer() != null ? entity.getAnswer().getId() : null)
                .answerContent(entity.getAnswer() != null ? entity.getAnswer().getContent() : null)
                .textResponse(entity.getTextResponse())
                .isCorrect(entity.getIsCorrect())
                .build();
    }
}
