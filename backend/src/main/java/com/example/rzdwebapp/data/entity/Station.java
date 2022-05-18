package com.example.rzdwebapp.data.entity;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "station",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"city", "name"})}
)
@Getter
@Setter
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(length = 20)
    private String city;

    @Column(length = 20)
    private String name;
}
