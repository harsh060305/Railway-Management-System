package com.example.railway.repository;

import com.example.railway.entity.Route;
import com.example.railway.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findBySourceStationAndDestStation(Station sourceStation, Station destStation);
}
