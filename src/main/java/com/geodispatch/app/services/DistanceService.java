package com.geodispatch.app.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
  double calculateDistance(Point paramPoint1, Point paramPoint2);
}
