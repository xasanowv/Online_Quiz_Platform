package com.company.quizcategories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuizCateRepository extends JpaRepository<QuizCategoriesEntity, UUID> {

}
