package com.LearnTableExport.TableExport.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question")
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ques_id")
    private Long quesId;

    @Column(length = 5000)
    private String content;
    private String image;

    @Column(name = "option1")
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    @Transient
    private String givenAnswer;

}
