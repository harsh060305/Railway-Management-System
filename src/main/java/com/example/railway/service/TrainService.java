package com.example.railway.service;

import com.example.railway.entity.Train;
import com.example.railway.dto.ApiResponse;

public interface TrainService {
    ApiResponse getAllTrains();
    ApiResponse searchTrains(String sourceCode, String destCode);
    ApiResponse addTrain(Train train);
}
