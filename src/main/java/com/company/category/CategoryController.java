package com.company.category;

import com.company.category.dto.CategoryCr;
import com.company.category.dto.CategoryResp;
import com.company.companent.ApiResponse;
import com.company.exception.AppBadRequestException;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PermitAll
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResp>> create(@RequestBody CategoryCr categoryCr)  {
        return ResponseEntity.ok(
                categoryService.create(categoryCr)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResp>> getById(@PathVariable UUID id) {

        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<Page<CategoryResp>>> getAll(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoryService.getAll(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResp>> update(@PathVariable UUID id, @RequestBody CategoryCr categoryCr) throws AppBadRequestException {
        return ResponseEntity.ok(categoryService.update(id , categoryCr));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }
}

