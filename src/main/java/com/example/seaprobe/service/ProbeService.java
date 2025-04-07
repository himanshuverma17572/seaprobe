package com.example.seaprobe.service;

import com.example.seaprobe.dao.ProbeRepository;
import com.example.seaprobe.model.Coordinates;
import com.example.seaprobe.model.CurrentAxis;
import com.example.seaprobe.model.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProbeService {
    @Autowired
    private ProbeRepository probeRepository;

    public Optional<Probe> getProbeDetails(Integer probeId) {
        return probeRepository.findById(probeId);
    }

    public Optional<Probe> moveForward(Integer probeId, Integer steps) {
        return probeRepository.findById(probeId)
                .map(probe -> {
                    CurrentAxis currentAxis = probe.getCurrectAxis();
                    Coordinates currentCoordinates = probe.getCurrentCoordinates();
                    probe.getCoordinatesVisited().add(currentCoordinates);
                    Coordinates nextCoordinates = new Coordinates(currentCoordinates.getxPosition(), currentCoordinates.getyPosition());
                    if (CurrentAxis.X_POSITIVE.equals(currentAxis)) {
                        nextCoordinates.setxPosition(nextCoordinates.getxPosition() + steps);
                    } else if (CurrentAxis.X_NEGATIVE.equals(currentAxis)) {
                        nextCoordinates.setxPosition(nextCoordinates.getxPosition() - steps);
                    } else if (CurrentAxis.Y_POSITIVE.equals(currentAxis)) {
                        nextCoordinates.setyPosition(nextCoordinates.getyPosition() + steps);
                    } else if (CurrentAxis.Y_NEGATIVE.equals(currentAxis)) {
                        nextCoordinates.setyPosition(nextCoordinates.getyPosition() - steps);
                    }
                    probe.setCurrentCoordinates(nextCoordinates);
                    return probeRepository.save(probe);
                });
    }

    public Optional<Probe> moveBackward(Integer probeId, Integer steps) {
        return probeRepository.findById(probeId)
                .map(probe -> {
                    CurrentAxis currentAxis = probe.getCurrectAxis();
                    Coordinates currentCoordinates = probe.getCurrentCoordinates();
                    probe.getCoordinatesVisited().add(currentCoordinates);
                    Coordinates nextCoordinates = new Coordinates(currentCoordinates.getxPosition(), currentCoordinates.getyPosition());
                    if (CurrentAxis.X_POSITIVE.equals(currentAxis)) {
                        nextCoordinates.setxPosition(nextCoordinates.getxPosition() - steps);
                    } else if (CurrentAxis.X_NEGATIVE.equals(currentAxis)) {
                        nextCoordinates.setxPosition(nextCoordinates.getxPosition() + steps);
                    } else if (CurrentAxis.Y_POSITIVE.equals(currentAxis)) {
                        nextCoordinates.setyPosition(nextCoordinates.getyPosition() - steps);
                    } else if (CurrentAxis.Y_NEGATIVE.equals(currentAxis)) {
                        nextCoordinates.setyPosition(nextCoordinates.getyPosition() + steps);
                    }
                    probe.setCurrentCoordinates(nextCoordinates);
                    return probeRepository.save(probe);
                });
    }

    public boolean turnLeft(Integer probeId) {
        return probeRepository.findById(probeId)
                .map(probe -> {
                    CurrentAxis currectAxis = probe.getCurrectAxis();
                    if (CurrentAxis.X_POSITIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.Y_POSITIVE);
                    } else if (CurrentAxis.X_NEGATIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.Y_NEGATIVE);
                    } else if (CurrentAxis.Y_POSITIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.X_NEGATIVE);
                    } else if (CurrentAxis.Y_NEGATIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.X_POSITIVE);
                    }
                    return probeRepository.save(probe);
                }).isPresent();
    }

    public boolean turnRight(Integer probeId) {
        return probeRepository.findById(probeId)
                .map(probe -> {
                    CurrentAxis currectAxis = probe.getCurrectAxis();
                    if (CurrentAxis.X_POSITIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.Y_NEGATIVE);
                    } else if (CurrentAxis.X_NEGATIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.Y_POSITIVE);
                    } else if (CurrentAxis.Y_POSITIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.X_POSITIVE);
                    } else if (CurrentAxis.Y_NEGATIVE.equals(currectAxis)) {
                        probe.setCurrectAxis(CurrentAxis.X_NEGATIVE);
                    }
                    return probeRepository.save(probe);
                }).isPresent();
    }

    public Optional<Probe> setupProbe() {
        Probe probe = new Probe();
        probe.setCurrectAxis(CurrentAxis.X_POSITIVE);
        Coordinates coordinates = new Coordinates();
        coordinates.setxPosition(0);
        coordinates.setyPosition(0);
        probe.setCurrentCoordinates(coordinates);
        return Optional.of(probeRepository.save(probe));
    }
}
