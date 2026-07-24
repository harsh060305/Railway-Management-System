package com.example.railway.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookingRequest {
    private Long userId;
    private Long trainId;
    private String trainClass;
    private LocalDate journeyDate;
    private String passengerName;
    private Integer age;
    private String gender;
    private Long sourceStationId;
    private Long destStationId;
}
