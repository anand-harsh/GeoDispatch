package com.geodispatch.app.services.impl;

import com.geodispatch.app.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_BASE_URL =
            "https://router.project-osrm.org/route/v1/driving/";

    private final RestClient restClient = RestClient.builder()
            .baseUrl(OSRM_BASE_URL)
            .build();

    @Override
    public double calculateDistance(Point source, Point destination) {

        try {

            String uri = String.format(
                    "%f,%f;%f,%f",
                    source.getX(),
                    source.getY(),
                    destination.getX(),
                    destination.getY()
            );

            OSRMResponseDto response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            if (response == null
                    || response.getRoutes() == null
                    || response.getRoutes().isEmpty()) {
                throw new RuntimeException("No route returned from OSRM.");
            }

            return response.getRoutes()
                    .getFirst()
                    .getDistance() / 1000.0; // meters → kilometers

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Failed to calculate distance using OSRM.",
                    ex
            );
        }
    }
}