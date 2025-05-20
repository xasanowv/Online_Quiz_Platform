package com.company.question;

import com.company.question.DTO.QuestionCr;
import com.company.question.DTO.QuestionResp;
import com.company.quiz.QuizEntity;
import com.company.quiz.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuestionResp create(QuestionCr cr) {
        QuizEntity quiz = quizRepository.findById(cr.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        QuestionEntity question = QuestionEntity.builder()
                .quiz(quiz)
                .content(cr.getContent())
                .type(cr.getType())
                .points(cr.getPoints())
                .build();

        question = questionRepository.save(question);
        return mapToResponse(question);
    }

    public QuestionResp getById(UUID id) {
        return mapToResponse(findQuestionById(id));
    }

    public List<QuestionResp> getAll() {
        return questionRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public QuestionResp update(UUID id, QuestionCr cr) {
        QuestionEntity question = findQuestionById(id);
        QuizEntity quiz = quizRepository.findById(cr.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        question.setQuiz(quiz);
        question.setContent(cr.getContent());
        question.setType(cr.getType());
        question.setPoints(cr.getPoints());
        question = questionRepository.save(question);
        return mapToResponse(question);
    }

    public void delete(UUID id) {
        QuestionEntity question = findQuestionById(id);
        questionRepository.delete(question);
    }

    private QuestionEntity findQuestionById(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    private QuestionResp mapToResponse(QuestionEntity entity) {
        return QuestionResp.builder()
                .id(entity.getId())
                .quizId(entity.getQuiz().getId())
                .content(entity.getContent())
                .type(entity.getType())
                .points(entity.getPoints())
                .build();
    }
}
