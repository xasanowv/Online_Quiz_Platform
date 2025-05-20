package com.company.quizcategories;

import com.company.quiz.dto.QuizCr;
import com.company.quizcategories.DTO.QuizCateCR;
import com.company.quizcategories.DTO.QuizCateResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pai/v1/quizCategory")
@RequiredArgsConstructor
public class QuizCateController {

    private final QuizCateService quizCateService;

    @PostMapping("/create")
    public ResponseEntity<QuizCateResp> createQuizCategory(@RequestBody QuizCateCR quizCr) {
        return ResponseEntity.ok(quizCateService.create(quizCr));
    }
    @GetMapping("/{id}")
    public QuizCateResp getById(@PathVariable UUID id) {
        return quizCateService.getById(id);
    }

    @GetMapping
    public List<QuizCateResp> getAll() {
        return quizCateService.getAll();
    }


    @PutMapping("/{id}")
    public QuizCateResp update(@PathVariable UUID id, @RequestBody QuizCateCR cr) {
        return quizCateService.update(id, cr);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        quizCateService.delete(id);
    }

}
