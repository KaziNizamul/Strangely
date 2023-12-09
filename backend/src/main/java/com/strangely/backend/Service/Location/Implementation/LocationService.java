package com.strangely.backend.Service.Location.Implementation;
import com.strangely.backend.Model.Location.Entities.Area;
import com.strangely.backend.Repository.Location.AreaRepository;
import com.strangely.backend.Service.Location.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private AreaRepository areaRepository;

    private static double EARTH_RADIUS = 6371.0;// Radius of the Earth in kilometers
    private static int two =2;

    //Get the all area id for a customer
    @Override
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    //Find the nearest area_id from location
// Find the nearest area to a given set of coordinates (latitude and longitude) among a list of areas.
    @Override
    public int findNearestArea(double latitude, double longitude) {
        // Retrieve the list of all areas.
        List<Area> areas = getAllAreas();
        double minDistance = Double.MAX_VALUE;
        int response = 0;
        for (Area area : areas) {
            // Get the latitude and longitude of the current area.
            double lat2 = area.getLatitude();
            double lon2 = area.getLongitude();
            // Calculate the distance between the given coordinates and the current area using the getDistance method.
            double distance = getDistance(latitude, longitude, lat2, lon2);
            // Update the minimum distance and the response area ID if the current area is closer.
            if (distance < minDistance) {
                minDistance = distance;
                response = area.getAreaId();
            }
        }
        return response;
    }

    // Find the distance between two different locations using the Haversine formula.
    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude values from degrees to radians.
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        // Constant value representing the Earth's radius in kilometers.

        // Calculate the differences in latitude and longitude.
        double diffLat = lat2 - lat1;
        double diffLon = lon2 - lon1;
        // Haversine formula calculations.
        double a = Math.pow(Math.sin(diffLat / two), two) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(diffLon / two), two);
        double c = two * Math.asin(Math.sqrt(a));
        return EARTH_RADIUS * c;
    }

    // Retrieve the city name associated with a given area ID.
    @Override
    public String getCityNameById(int id) {
        // Attempt to find the Area by its ID in the areaRepository.
        Area area = areaRepository.findByAreaId(id);

        // Check if the Area is found.
        if (area != null) {
            return area.getName();
        } else {
            return "Area not found";
        }
    }

}

