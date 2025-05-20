package com.company.question;

import com.company.question.DTO.QuestionCr;
import com.company.question.DTO.QuestionResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResp create(@RequestBody QuestionCr cr) {
        return questionService.create(cr);
    }

    @GetMapping("/{id}")
    public QuestionResp getById(@PathVariable UUID id) {
        return questionService.getById(id);
    }

    @GetMapping
    public List<QuestionResp> getAll() {
        return questionService.getAll();
    }

    @PutMapping("/{id}")
    public QuestionResp update(@PathVariable UUID id, @RequestBody QuestionCr cr) {
        return questionService.update(id, cr);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        questionService.delete(id);
    }
}
