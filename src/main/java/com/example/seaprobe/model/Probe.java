package com.example.seaprobe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
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
    private List<Coordinates> coordinatesVisited = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void setCurrentCoordinates(Coordinates currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    public CurrentAxis getCurrectAxis() {
        return currectAxis;
    }

    public void setCurrectAxis(CurrentAxis currectAxis) {
        this.currectAxis = currectAxis;
    }

    public List<Coordinates> getCoordinatesVisited() {
        return coordinatesVisited;
    }

    public void setCoordinatesVisited(List<Coordinates> coordinatesVisited) {
        this.coordinatesVisited = coordinatesVisited;
    }
}
