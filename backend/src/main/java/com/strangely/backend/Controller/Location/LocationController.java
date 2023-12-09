package com.strangely.backend.Controller.Location;

import com.strangely.backend.Config.Exception.exceptionHandler;
import com.strangely.backend.Model.Location.DTOs.AreaIDDTO;
import com.strangely.backend.Model.Location.DTOs.LatLongDTO;
import com.strangely.backend.Model.Location.DTOs.CityDTO;
import com.strangely.backend.Service.Location.ILocationService;
import com.strangely.backend.Service.Exception.Restexception;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class LocationController {

    @Autowired
    private ILocationService locationService;

    private final exceptionHandler exceptionHandler;

    // This method is a GET mapping for "/getIdByLatLong", designed to retrieve area ID based on latitude and longitude.
// The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping(value = "/getIdByLatLong", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AreaIDDTO> IdByLatLong(@RequestBody @Valid LatLongDTO latLongDTO) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Create a new AreaIDDTO instance to hold the area ID information.
            AreaIDDTO ai = new AreaIDDTO();

            // Set the area ID in the AreaIDDTO using the locationService based on the provided latitude and longitude.
            ai.setArea_id(locationService.findNearestArea(latLongDTO.getLatitude(), latLongDTO.getLongitude()));

            // Check if the area ID is not equal to 0.
            if (ai.getArea_id() != 0) {
                // If the area ID is found, create a 201 CREATED response with the AreaIDDTO in the response body.
                response = ResponseEntity.status(HttpStatus.CREATED).body(ai);
            } else {
                // If the area ID is 0, generate a 400 BAD REQUEST response with the AreaIDDTO in the response body.
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ai);
            }
        } catch (Restexception e) {
            response = exceptionHandler.handleException(e);
        }
        return response;
    }

    // This method is a general request mapping for "/getCitybyId", designed to retrieve city information by area ID.
// The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping(value = "/getCitybyId", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityDTO> CityById(@RequestBody @Valid AreaIDDTO id) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Create a new CityDTO instance to hold the city information.
            CityDTO c = new CityDTO();

            // Set the city name in the CityDTO using the locationService based on the provided area ID.
            c.setCity(locationService.getCityNameById(id.getArea_id()));

            // Check if the city name is not null.
            if (c.getCity() != null) {
                // If the city name is found, create a 200 OK response with the CityDTO in the response body.
                response = ResponseEntity.status(HttpStatus.OK).body(c);
            } else {
                // If the city name is not found, generate a 400 BAD REQUEST response with the CityDTO in the response body.
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(c);
            }
        } catch (Restexception e) {
            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = exceptionHandler.handleException(e);
        }
        return response;
    }

}
