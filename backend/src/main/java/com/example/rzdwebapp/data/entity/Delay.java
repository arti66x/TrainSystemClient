package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "delay")
@Getter
@Setter
@ToString

public class Delay {
    @EmbeddedId
    private DelayId id;

    @JsonIncludeProperties({"id","train","route","time"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @MapsId("trainScheduleId")
    private TrainSchedule trainSchedule;

    @JsonIncludeProperties({"id","station"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @MapsId("routeStationId")
    private RouteStation routeStation; //начиная с этой станции обновляется расписание

    @NotNull
    private Short time;
}
