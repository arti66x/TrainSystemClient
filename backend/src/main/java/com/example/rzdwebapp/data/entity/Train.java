package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "train")
@Getter
@Setter
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private TrainType type;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","sector","type"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Brigade repairBrigade;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","sector","type"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Brigade trainBrigade;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","name"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Station station;

    @Column
    private Short seatsNum;

    @Column
    private Date firstDate;
}
