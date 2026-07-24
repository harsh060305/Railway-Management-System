package com.example.railway.repository;

import com.example.railway.entity.Availability;
import com.example.railway.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByTrainAndTravelDate(Train train, LocalDate travelDate);
}
