package com.example.seaprobe.dao;

import com.example.seaprobe.model.Probe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbeRepository extends JpaRepository<Probe, Integer> {
}
