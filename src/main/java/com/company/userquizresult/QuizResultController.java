package com.company.userquizresult;

import com.company.userquizresult.DTO.QuizResultCr;
import com.company.userquizresult.DTO.QuizResultResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quizResult")
@RequiredArgsConstructor
public class QuizResultController {

    private final QuizResultService quizResultService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public QuizResultResp create(@RequestBody QuizResultCr cr) {
        return quizResultService.create(cr);
    }

    @GetMapping("/{id}")
    public QuizResultResp getById(@PathVariable UUID id) {
        return quizResultService.getById(id);
    }

    @GetMapping
    public List<QuizResultResp> getAll() {
        return quizResultService.getAll();
    }


    @PutMapping("/{id}")
    public QuizResultResp update(@PathVariable UUID id, @RequestBody QuizResultCr cr) {
        return quizResultService.update(id, cr);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        quizResultService.delete(id);
    }

}
