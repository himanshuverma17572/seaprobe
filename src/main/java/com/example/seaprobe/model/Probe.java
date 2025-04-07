package com.example.seaprobe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
public class Probe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Coordinates currentCoordinates;
    private CurrentAxis currectAxis;
    @OneToMany
    private List<Coordinates> coordinatesVisited;

}
