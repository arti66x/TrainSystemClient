package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "route_station")
@Getter
@Setter
public class RouteStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIncludeProperties({"id","name"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Route route;

    @JsonIncludeProperties({"id","city"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Station station;

    @NotNull
    private Integer routeTime; // время прибытия относительно первой станции

    private Short timeStop;
}
