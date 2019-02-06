package com.example.service.alerts;


import model.Stop;

import java.util.Optional;

public interface StopsService {
    <T extends Stop> T[] getAllStops() throws Exception;
    <T extends Stop> Optional<T> findByStopId(String stopId) throws Exception;
    <T extends Stop> T[] findByStopName(String stopName) throws Exception;
}
