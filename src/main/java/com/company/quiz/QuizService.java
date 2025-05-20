package com.company.quiz;

import com.company.quiz.dto.QuizCr;
import com.company.quiz.dto.QuizResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    public List<QuizResp> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public QuizResp getQuizById(UUID id) {
        QuizEntity quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
        return convertToDto(quiz);
    }


    public QuizResp createQuiz(QuizCr quizDto) {
        QuizEntity quiz = convertToEntity(quizDto);
        QuizEntity savedQuiz = quizRepository.save(quiz);
        return convertToDto(savedQuiz);
    }


    public QuizResp updateQuiz(UUID id, QuizCr quizDto) {
        QuizEntity existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));

        // Update quiz properties
        existingQuiz.setTitle(quizDto.getTitle());
        existingQuiz.setDescription(quizDto.getDescription());
        // Update other properties as needed

        QuizEntity updatedQuiz = quizRepository.save(existingQuiz);
        return convertToDto(updatedQuiz);
    }


    public void deleteQuiz(UUID id) {
        if (!quizRepository.existsById(id)) {
            throw new RuntimeException("Quiz not found with id: " + id);
        }
        quizRepository.deleteById(id);
    }

    public List<QuizResp> getQuizzesByCreator(UUID creatorId) {
        return quizRepository.findByCreatorId(creatorId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<QuizResp> getQuizzesByCategory(UUID categoryId) {
        return quizRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Helper methods for DTO conversion
    private QuizResp convertToDto(QuizEntity quiz) {
        return QuizResp.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .creatorId(quiz.getCreator().getId())
                .categoryId(quiz.getCategory() != null ? quiz.getCategory().getId() : null)
                // Map other properties as needed
                .build();
    }

    private QuizEntity convertToEntity(QuizCr quizDto) {
        return QuizEntity.builder()
                .title(quizDto.getTitle())
                .description(quizDto.getDescription())
                // Set other properties based on the DTO
                .build();
    }
}
