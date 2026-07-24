package com.example.railway.controller;

import com.example.railway.dto.ApiResponse;
import com.example.railway.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchTrains(@RequestParam String source, @RequestParam String destination) {
        return ResponseEntity.ok(trainService.searchTrains(source, destination));
    }
}
