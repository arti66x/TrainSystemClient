package com.example.rzdwebapp.data.entity;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "repair")
@Getter
@Setter
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIncludeProperties({"id"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Train train;

    @NotNull
    private Date startDate;

    private Date endDate;

    private String description;

    @JsonIncludeProperties({"id"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Brigade brigade;
}
