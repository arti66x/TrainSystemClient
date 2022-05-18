package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "canseled_run")
@Getter
@Setter
public class CanceledRun {
    @Id
    private Integer id;

    @JsonIncludeProperties({"id","train","route","time"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private TrainSchedule trainSchedule;

    private String description;
}
