package com.example.railway.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "trains")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "train_no", nullable = false, unique = true)
    private String trainNo;

    @Column(nullable = false)
    private String name;

    private String type;
}
