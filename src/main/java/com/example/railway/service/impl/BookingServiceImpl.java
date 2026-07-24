package com.example.railway.service.impl;

import com.example.railway.dto.BookingRequest;
import com.example.railway.dto.ApiResponse;
import com.example.railway.entity.*;
import com.example.railway.repository.*;
import com.example.railway.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public ApiResponse bookTicket(BookingRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Train train = trainRepository.findById(request.getTrainId()).orElse(null);
        Station src = stationRepository.findById(request.getSourceStationId()).orElse(null);
        Station dest = stationRepository.findById(request.getDestStationId()).orElse(null);

        if (user == null || train == null || src == null || dest == null) {
            return new ApiResponse(false, "Invalid booking details provided", null);
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTrain(train);
        booking.setSourceStation(src);
        booking.setDestStation(dest);
        booking.setJourneyDate(request.getJourneyDate());
        booking.setPnr("PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setStatus("CONFIRMED");
        booking.setTotalAmount(new BigDecimal("500.00")); // Hardcoded for demo

        Booking savedBooking = bookingRepository.save(booking);

        return new ApiResponse(true, "Ticket booked successfully", savedBooking);
    }

    @Override
    public ApiResponse getBookingHistory(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return new ApiResponse(true, "Booking history fetched", bookings);
    }

    @Override
    public ApiResponse cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
            return new ApiResponse(true, "Booking cancelled", null);
        }
        return new ApiResponse(false, "Booking not found", null);
    }
}
