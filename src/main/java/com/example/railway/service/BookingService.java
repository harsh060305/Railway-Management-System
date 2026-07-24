package com.example.railway.service;

import com.example.railway.dto.BookingRequest;
import com.example.railway.dto.ApiResponse;

public interface BookingService {
    ApiResponse bookTicket(BookingRequest request);
    ApiResponse getBookingHistory(Long userId);
    ApiResponse cancelBooking(Long bookingId);
}
