package com.geodispatch.app.services.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class OSRMRoute {

    /**
     * Distance returned by OSRM in meters.
     */
    private Double distance;
}