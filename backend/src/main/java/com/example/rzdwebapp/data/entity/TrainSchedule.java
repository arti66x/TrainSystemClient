package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "train_schedule")
@Getter
@Setter
public class TrainSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIncludeProperties({"id"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Train train;

    @JsonIncludeProperties({"id","name"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Route route;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean direction;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @NotNull
    @Column(columnDefinition = "timestamp(0)")
    private Date time;
}
