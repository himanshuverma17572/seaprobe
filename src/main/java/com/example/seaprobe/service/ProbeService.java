package com.example.seaprobe.service;

import com.example.seaprobe.dao.ProbeRepository;
import com.example.seaprobe.model.Coordinates;
import com.example.seaprobe.model.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProbeService {
    @Autowired
    private ProbeRepository probeRepository;

    public Optional<Probe> getProbeDetails(Integer probeId){
        return null;
    }
    public Optional<Probe> moveForward(Integer probeId, Integer steps){
        return null;
    }
    public Optional<Probe> moveBackward(Integer probeId, Integer steps){
        return null;
    }
    public boolean turnLeft(Integer probeId){
        return false;
    }
    public boolean turnRight(Integer probeId){
        return false;
    }
}
