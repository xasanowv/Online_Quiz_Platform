package com.company.category;

import com.company.category.dto.CategoryCr;
import com.company.category.dto.CategoryResp;

import com.company.companent.ApiResponse;
import com.company.companent.Components;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ApiResponse<CategoryResp> create(CategoryCr categoryCr)  {
        Optional<CategoryEntity> byName = getCategoryEntityByName(categoryCr.getName());

        if (byName.isPresent()) {
            throw new AppBadRequestException("category already exsits");
        }

        CategoryEntity saved = categoryRepository.save(CategoryEntity
                        .builder()
                        .name(categoryCr.getName())
                        .build());

        return new ApiResponse<>(toDTO(saved));
    }

    public ApiResponse<CategoryResp> getById(UUID id) {
        CategoryEntity categoryEntity = getCategoryEntityById(id);

        return new ApiResponse<>(toDTO(categoryEntity));
    }

    public ApiResponse<Page<CategoryResp>> getAll(int page, int size) {


        Pageable pageable = PageRequest.of(page, size, Sort.by(Components.CREATED_AT));


        List<CategoryResp> list = categoryRepository.findAllByVisibilityTrue(pageable)
                .stream()
                .map(c -> toDTO(c))
                .toList();

        return new ApiResponse<>(new PageImpl<>(list, pageable, list.size()));

    }


    public ApiResponse<CategoryResp> update(UUID id, CategoryCr categoryCr) throws AppBadRequestException {
        CategoryEntity categoryEntity = getCategoryEntityById(id);

        Optional<CategoryEntity> byName = getCategoryEntityByName(categoryCr.getName());
        if (byName.isPresent()) {
            throw new AppBadRequestException("category already exsits!!!!");
        }

        categoryEntity.setName(categoryCr.getName());

        CategoryEntity saved = categoryRepository.save(categoryEntity);

        return new ApiResponse<>(toDTO(saved));
    }

    public ApiResponse<String> delete(UUID id) {
        CategoryEntity categoryEntity = getCategoryEntityById(id);

        categoryEntity.setVisibility(false);

        categoryRepository.save(categoryEntity);
        return new ApiResponse<>(Components.DELETED);
    }

    private Optional<CategoryEntity> getCategoryEntityByName(String name) {
        return categoryRepository.findByNameAndVisibilityTrue(name);
    }

    private CategoryResp toDTO(CategoryEntity categoryEntity) {
        return CategoryResp
                .builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }

    private CategoryEntity getCategoryEntityById(UUID id) {
        return categoryRepository.findByIdAndVisibilityTrue(id).orElseThrow(ItemNotFoundException::new);
    }
}
