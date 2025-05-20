package com.company.answer;

import com.company.companent.BaseMapper;
import com.company.question.QuestionEntity;
import com.company.userresponse.UserResponseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerEntity extends BaseMapper {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;


    @OneToMany(fetch = FetchType.LAZY)
    private List<UserResponseEntity> userResponses;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect = false;

    @Column(name = "order_num")
    private Integer orderNum;

    @Column
    private String feedback;

}
