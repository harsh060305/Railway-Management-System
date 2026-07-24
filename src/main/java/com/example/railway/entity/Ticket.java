package com.example.railway.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "passenger_name")
    private String passengerName;

    private Integer age;
    private String gender;

    @Column(name = "seat_no")
    private String seatNo;

    @Column(name = "train_class")
    private String trainClass;
}
