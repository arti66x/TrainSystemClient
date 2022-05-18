package com.example.rzdwebapp.data.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "medical_exam")
@Getter @Setter
public class MedicalExam {
    @EmbeddedId
    private MedicalExamId id;

    @ManyToOne
    @MapsId("staffId")
    @JoinColumn
    private Staff staff;
}
