package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "ticket")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIncludeProperties({"id"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private TrainSchedule trainSchedule;

    @NotBlank
    @Column(length = 60)
    private String fullName;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean luggage;

    @JsonIncludeProperties({"id","station"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RouteStation startStation;

    @JsonIncludeProperties({"id","station"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RouteStation endStation;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private TicketStatus status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @NotNull
    @Column(columnDefinition = "timestamp(0)")
    private Date time;
}
