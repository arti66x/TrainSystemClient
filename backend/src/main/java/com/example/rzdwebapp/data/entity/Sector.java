package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sector")
@Getter @Setter
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","specialization","salary","isMale","hireDate","birthdate","sector","brigade"})
    private Staff administrator;

    @NotNull
    @Column(length = 40,unique = true)
    private String name;
}
