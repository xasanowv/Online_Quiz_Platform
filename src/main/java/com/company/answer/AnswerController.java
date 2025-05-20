package com.company.answer;

import com.company.answer.DTO.AnswerCr;
import com.company.answer.DTO.AnswerResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/create")
    public ResponseEntity<AnswerResp> AnswerCreate(@RequestBody AnswerCr answerCr) {
        return ResponseEntity.ok(answerService.create(answerCr));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AnswerResp> getAllAnswer(@PathVariable UUID id) {
        return ResponseEntity.ok(answerService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnswerResp> updateAnswer(@RequestBody AnswerCr  answerCr, @PathVariable UUID id) {
        return ResponseEntity.ok(answerService.updateAns(answerCr,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AnswerResp> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(answerService.delete(id));
    }
}
