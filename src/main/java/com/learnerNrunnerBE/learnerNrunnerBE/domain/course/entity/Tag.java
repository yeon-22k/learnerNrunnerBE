package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Tag {
    @Id
    private Long id;
    private String name;
}
