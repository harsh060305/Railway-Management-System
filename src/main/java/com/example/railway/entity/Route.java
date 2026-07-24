package com.example.railway.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "routes")
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "dest_station_id")
    private Station destStation;

    @Column(name = "distance_km")
    private Integer distanceKm;
}
