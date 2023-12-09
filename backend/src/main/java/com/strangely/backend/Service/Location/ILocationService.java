package com.strangely.backend.Service.Location;

import com.strangely.backend.Model.Location.Entities.Area;

import java.util.List;

public interface ILocationService {
    List<Area> getAllAreas();
    int findNearestArea(double latitude, double longitude);
    String getCityNameById(int id);
}
