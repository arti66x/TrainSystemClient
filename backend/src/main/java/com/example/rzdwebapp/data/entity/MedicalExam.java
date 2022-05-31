package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Entity
@Table(name = "medical_exam")
@Getter @Setter
public class MedicalExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @JsonIncludeProperties({"id","fullName"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Staff staff;

    @NotNull
    private Date date;
}
