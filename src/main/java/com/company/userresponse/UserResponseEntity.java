package com.company.userresponse;

import com.company.answer.AnswerEntity;
import com.company.companent.BaseMapper;
import com.company.question.QuestionEntity;
import com.company.userquizresult.UserQuizResultEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Table(name = "user_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseEntity extends BaseMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id", nullable = false)
    private UserQuizResultEntity result;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private AnswerEntity answer;

    @Column(name = "text_response", columnDefinition = "TEXT")
    private String textResponse;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
