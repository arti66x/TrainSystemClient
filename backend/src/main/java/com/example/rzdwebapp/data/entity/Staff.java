package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Entity
@Table(name = "staff")
@Getter @Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(length = 60)
    private String fullName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Specialization specialization;

    @NotNull
    private Integer salary;

    @NotNull
    private Boolean isMale;

    @NotNull
    private Date hireDate;

    @NotNull
    private Date birthdate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","administrator"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Sector sector;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","type","sector"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Brigade brigade;
}
