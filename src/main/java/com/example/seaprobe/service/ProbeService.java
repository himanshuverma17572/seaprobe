package com.example.seaprobe.service;

import com.example.seaprobe.dao.ProbeRepository;
import com.example.seaprobe.model.Coordinates;
import com.example.seaprobe.model.CurrentAxis;
import com.example.seaprobe.model.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                    CurrentAxis currectAxis = probe.getCurrectAxis();
                    Coordinates currentCoordinates = probe.getCurrentCoordinates();
                    if (CurrentAxis.X_POSITIVE.equals(currectAxis)) {
                        currentCoordinates.setxPosition(currentCoordinates.getxPosition() + steps);
                    } else if (CurrentAxis.X_NEGATIVE.equals(currectAxis)) {
                        currentCoordinates.setxPosition(currentCoordinates.getxPosition() - steps);
                    } else if (CurrentAxis.Y_POSITIVE.equals(currectAxis)) {
                        currentCoordinates.setyPosition(currentCoordinates.getyPosition() + steps);
                    } else if (CurrentAxis.Y_NEGATIVE.equals(currectAxis)) {
                        currentCoordinates.setxPosition(currentCoordinates.getxPosition() - steps);
                    }
                    probe.setCurrentCoordinates(currentCoordinates);
                    return probeRepository.save(probe);
                });
    }

    public Optional<Probe> moveBackward(Integer probeId, Integer steps) {
        return probeRepository.findById(probeId)
                .map(probe -> {
                    CurrentAxis currectAxis = probe.getCurrectAxis();
                    Coordinates currentCoordinates = probe.getCurrentCoordinates();
                    if (CurrentAxis.X_POSITIVE.equals(currectAxis)) {
                        currentCoordinates.setxPosition(currentCoordinates.getxPosition() - steps);
                    } else if (CurrentAxis.X_NEGATIVE.equals(currectAxis)) {
                        currentCoordinates.setxPosition(currentCoordinates.getxPosition() + steps);
                    } else if (CurrentAxis.Y_POSITIVE.equals(currectAxis)) {
                        currentCoordinates.setyPosition(currentCoordinates.getyPosition() - steps);
                    } else if (CurrentAxis.Y_NEGATIVE.equals(currectAxis)) {
                        currentCoordinates.setxPosition(currentCoordinates.getxPosition() + steps);
                    }
                    probe.setCurrentCoordinates(currentCoordinates);
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
}
