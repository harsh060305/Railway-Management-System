package com.example.railway.controller;

import com.example.railway.entity.*;
import com.example.railway.dto.ApiResponse;
import com.example.railway.repository.*;
import com.example.railway.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TrainService trainService;

    @PostMapping("/trains")
    public ResponseEntity<ApiResponse> addTrain(@RequestBody Train train) {
        return ResponseEntity.ok(trainService.addTrain(train));
    }

    @DeleteMapping("/trains/{id}")
    public ResponseEntity<ApiResponse> deleteTrain(@PathVariable Long id) {
        trainRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Train deleted", null));
    }

    @PostMapping("/stations")
    public ResponseEntity<ApiResponse> addStation(@RequestBody Station station) {
        return ResponseEntity.ok(new ApiResponse(true, "Station added", stationRepository.save(station)));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse(true, "Users fetched", userRepository.findAll()));
    }

    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse> getAllBookings() {
        return ResponseEntity.ok(new ApiResponse(true, "All bookings fetched", bookingRepository.findAll()));
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<ApiResponse> cancelBookingAdmin(@PathVariable Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
            return ResponseEntity.ok(new ApiResponse(true, "Booking cancelled by admin", null));
        }
        return ResponseEntity.ok(new ApiResponse(false, "Booking not found", null));
    }
}
