package com.example.seaprobe.controller;

import com.example.seaprobe.model.Probe;
import com.example.seaprobe.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/probe")
public class ProbeController {

    @Autowired
    private ProbeService probeService;

    @GetMapping("/{probeId}")
    public ResponseEntity getProbeDetails(@PathVariable("probeId") Integer probeId) {
        Optional<Probe> probeOptional = probeService.getProbeDetails(probeId);
        return ResponseEntity.of(probeOptional);
    }

    @PutMapping("/move/forward/{probeId}/{steps}")
    public ResponseEntity moveForward(@PathVariable("probeId") Integer probeId, @PathVariable("steps") Integer steps) {
        Optional<Probe> probeOptional = probeService.moveBackward(probeId, steps);
        return ResponseEntity.of(probeOptional);
    }

    @PutMapping("/move/backward/{probeId}/{steps}")
    public ResponseEntity moveBackward(@PathVariable("probeId") Integer probeId, @PathVariable("steps") Integer steps) {
        Optional<Probe> probeOptional = probeService.moveBackward(probeId, steps);
        return ResponseEntity.of(probeOptional);
    }

    @PutMapping("/turn/left/{probeId}/")
    public ResponseEntity turnLeft(@PathVariable("probeId") Integer probeId) {
        boolean success = probeService.turnLeft(probeId);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
    @PutMapping("/turn/right/{probeId}/")
    public ResponseEntity turnRight(@PathVariable("probeId") Integer probeId) {
        boolean success = probeService.turnRight(probeId);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
