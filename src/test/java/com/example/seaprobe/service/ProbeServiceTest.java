package com.example.seaprobe.service;

import com.example.seaprobe.dao.ProbeRepository;
import com.example.seaprobe.model.Coordinates;
import com.example.seaprobe.model.CurrentAxis;
import com.example.seaprobe.model.Probe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProbeServiceTest {

    @Mock
    private ProbeRepository probeRepository;
    @InjectMocks
    private ProbeService probeService;

    @Test
    void moveForward_when_stepsGreaterThanZero_then_incrementCurrentAxis() {
        Integer probeId = 1;
        Probe probe = getProbe(probeId);
        Mockito.when(probeRepository.findById(probeId)).thenReturn(Optional.of(probe));
        Mockito.when(probeRepository.save(probe)).thenReturn(probe);
        Optional<Probe> movedForward = probeService.moveForward(probeId, 5);
        Assertions.assertEquals(10, movedForward.get().getCurrentCoordinates().getxPosition());
    }

    @Test
    void getProbeDetails() {
    }

    @Test
    void testMoveForward() {
    }

    @Test
    void moveBackward() {
    }

    @Test
    void turnLeft() {
    }

    @Test
    void turnRight() {
    }

    private Probe getProbe(Integer probeId) {
        Probe probe = new Probe();
        probe.setId(probeId);
        probe.setCurrectAxis(CurrentAxis.X_POSITIVE);
        Coordinates coordinates = new Coordinates();
        coordinates.setId(1);
        coordinates.setxPosition(5);
        coordinates.setyPosition(5);
        probe.setCurrentCoordinates(coordinates);
        return probe;
    }
}