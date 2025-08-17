package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Tag {
    @Id
    private Long id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
