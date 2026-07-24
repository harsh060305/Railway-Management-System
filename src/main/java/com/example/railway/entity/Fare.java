package com.example.railway.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fare")
@Data
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(name = "train_class")
    private String trainClass;

    @Column(name = "base_fare_per_km")
    private BigDecimal baseFarePerKm;
}
