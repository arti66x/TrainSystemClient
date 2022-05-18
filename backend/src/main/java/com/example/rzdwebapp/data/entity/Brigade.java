package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brigade")
@Getter
@Setter
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","administrator"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Sector sector;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private BrigadeType type;
}
