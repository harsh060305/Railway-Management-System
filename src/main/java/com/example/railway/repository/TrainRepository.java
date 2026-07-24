package com.example.railway.repository;

import com.example.railway.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByTrainNo(String trainNo);
    List<Train> findByNameContainingIgnoreCase(String name);
}
