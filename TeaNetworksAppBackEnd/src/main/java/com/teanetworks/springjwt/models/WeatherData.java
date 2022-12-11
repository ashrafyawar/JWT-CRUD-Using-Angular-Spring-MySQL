package com.teanetworks.springjwt.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "weather")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "condition")
    private String condition;

    @Column(name = "time_range")
    private String timeRange;

    @Column(name = "location")
    private String location;

    @Column(name = "average")
    private Integer average;
}