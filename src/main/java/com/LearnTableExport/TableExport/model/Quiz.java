package com.LearnTableExport.TableExport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qID;

    private String title;

    @Column(length = 5000)
    private String description;
    private String totalQuestion;
    private String maxMarks;
    private String minMarks;
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quiz")
    @JsonIgnore
    private Set<Question> questions = new LinkedHashSet<>();
}
