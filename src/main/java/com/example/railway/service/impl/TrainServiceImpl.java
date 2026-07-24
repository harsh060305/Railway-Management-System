package com.example.railway.service.impl;

import com.example.railway.entity.Train;
import com.example.railway.entity.Route;
import com.example.railway.entity.Station;
import com.example.railway.dto.ApiResponse;
import com.example.railway.repository.TrainRepository;
import com.example.railway.repository.RouteRepository;
import com.example.railway.repository.StationRepository;
import com.example.railway.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public ApiResponse getAllTrains() {
        return new ApiResponse(true, "Trains fetched", trainRepository.findAll());
    }

    @Override
    public ApiResponse searchTrains(String sourceCode, String destCode) {
        Optional<Station> srcOpt = stationRepository.findByCode(sourceCode);
        Optional<Station> destOpt = stationRepository.findByCode(destCode);

        if (srcOpt.isEmpty() || destOpt.isEmpty()) {
            return new ApiResponse(false, "Invalid station codes", null);
        }

        List<Route> routes = routeRepository.findBySourceStationAndDestStation(srcOpt.get(), destOpt.get());
        List<Train> trains = routes.stream().map(Route::getTrain).collect(Collectors.toList());

        return new ApiResponse(true, "Trains found", trains);
    }

    @Override
    public ApiResponse addTrain(Train train) {
        return new ApiResponse(true, "Train added", trainRepository.save(train));
    }
}
