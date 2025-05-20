package com.company.userquizresult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuizResultRepository extends JpaRepository<UserQuizResultEntity, UUID> {

}
