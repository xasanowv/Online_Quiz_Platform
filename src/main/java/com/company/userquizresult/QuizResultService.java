package com.company.userquizresult;

import com.company.quiz.QuizEntity;
import com.company.quiz.QuizRepository;
import com.company.userquizresult.DTO.QuizResultCr;
import com.company.userquizresult.DTO.QuizResultResp;
import com.company.users.User;
import com.company.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizResultService {
    private final QuizResultRepository quizResultRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    public QuizResultResp create(QuizResultCr cr) {
        User user = (User) userRepository.findById(cr.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        QuizEntity quiz = quizRepository.findById(cr.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        UserQuizResultEntity result = UserQuizResultEntity.builder()
                .user(user)
                .quiz(quiz)
                .score(cr.getScore())
                .build();

        result = quizResultRepository.save(result);
        return mapToResponse(result);
    }

    public QuizResultResp getById(UUID id) {
        return mapToResponse(findResultById(id));
    }

    public List<QuizResultResp> getAll() {
        return quizResultRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public QuizResultResp update(UUID id, QuizResultCr cr) {
        UserQuizResultEntity result = findResultById(id);

        User user = (User) userRepository.findById(cr.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        QuizEntity quiz = quizRepository.findById(cr.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(cr.getScore());

        result = quizResultRepository.save(result);
        return mapToResponse(result);
    }

    public void delete(UUID id) {
        UserQuizResultEntity result = findResultById(id);
        quizResultRepository.delete(result);
    }

    private UserQuizResultEntity findResultById(UUID id) {
        return quizResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz result not found"));
    }

    private QuizResultResp mapToResponse(UserQuizResultEntity entity) {
        return QuizResultResp.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .userName(entity.getUser().getUsername())
                .quizId(entity.getQuiz().getId())
                .quizTitle(entity.getQuiz().getTitle())
                .score(entity.getScore())
                .completedAt(entity.getCompletedAt())
                .responseCount(entity.getResponses().size())
                .build();
    }
}
