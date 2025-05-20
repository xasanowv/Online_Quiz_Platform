package com.company.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, UUID> {

    Optional<QuizEntity> findByTitle(String title);
    List<QuizEntity> findByCreatorId(UUID creatorId);
    List<QuizEntity> findByCategoryId(UUID categoryId);
}
