package com.example.railway.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(nullable = false, unique = true)
    private String pnr;

    @Column(name = "journey_date")
    private LocalDate journeyDate;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate = LocalDateTime.now();

    private String status; // CONFIRMED, CANCELLED, WAITLIST

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "dest_station_id")
    private Station destStation;
}
