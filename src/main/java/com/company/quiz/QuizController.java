package com.company.quiz;

import com.company.quiz.dto.QuizCr;
import com.company.quiz.dto.QuizResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizResp>> getAllQuizzes() {
        List<QuizResp> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResp> getQuizById(@PathVariable UUID id) {
        QuizResp quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping
    public ResponseEntity<QuizResp> createQuiz(@RequestBody QuizCr quizDto) {
        QuizResp createdQuiz = quizService.createQuiz(quizDto);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResp> updateQuiz(@PathVariable UUID id, @RequestBody QuizCr quizDto) {
        QuizResp updatedQuiz = quizService.updateQuiz(id, quizDto);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable UUID id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<QuizResp>> getQuizzesByCreator(@PathVariable UUID creatorId) {
        List<QuizResp> quizzes = quizService.getQuizzesByCreator(creatorId);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<QuizResp>> getQuizzesByCategory(@PathVariable UUID categoryId) {
        List<QuizResp> quizzes = quizService.getQuizzesByCategory(categoryId);
        return ResponseEntity.ok(quizzes);
    }
}
