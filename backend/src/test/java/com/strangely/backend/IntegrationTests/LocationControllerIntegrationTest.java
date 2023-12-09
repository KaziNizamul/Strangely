package com.strangely.backend.IntegrationTests;
//
//import com.strangely.backend.Controller.Location.LocationController;
//import com.strangely.backend.Model.Location.DTOs.AreaIDDTO;
//import com.strangely.backend.Model.Location.DTOs.LatLongDTO;
//import com.strangely.backend.Model.Location.DTOs.CityDTO;
//import com.strangely.backend.Service.Location.ILocationService;
//import com.strangely.backend.Service.Location.Implementation.LocationService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
public class LocationControllerIntegrationTest {
//
//    @Mock
//    private LocationService locationServiceMock;
//
//    @InjectMocks
//    private LocationController locationController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testIdByLatLong_WithValidAreaID() {
//        // Mock data for a scenario where a valid area ID is returned
//        double latitude = 10.123;
//        double longitude = 20.456;
//        LatLongDTO latLongDTO = new LatLongDTO(latitude, longitude);
//        int validAreaID = 123;
//        AreaIDDTO areaIDDTO = new AreaIDDTO();
//        areaIDDTO.setArea_id(validAreaID);
//
//        when(locationServiceMock.findNearestArea(latitude, longitude)).thenReturn(validAreaID);
//
//        ResponseEntity<AreaIDDTO> response = locationController.IdByLatLong(latLongDTO);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(areaIDDTO.getArea_id(), response.getBody().getArea_id());
//    }
//
//    @Test
//    public void testIdByLatLong_WithInvalidAreaID() {
//        // Mock data for a scenario where an invalid area ID is returned
//        double latitude = 10.123;
//        double longitude = 20.456;
//        LatLongDTO latLongDTO = new LatLongDTO(latitude, longitude);
//        int invalidAreaID = 0; // Assuming 0 indicates an invalid area ID
//        AreaIDDTO areaIDDTO = new AreaIDDTO();
//        areaIDDTO.setArea_id(invalidAreaID);
//
//        when(locationServiceMock.findNearestArea(latitude, longitude)).thenReturn(invalidAreaID);
//
//        ResponseEntity<AreaIDDTO> response = locationController.IdByLatLong(latLongDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(areaIDDTO.getArea_id(), response.getBody().getArea_id());
//    }
//
//    @Test
//    public void testCityById_WithValidCity() {
//        // Mock data for a scenario where a valid city name is returned
//        int areaId = 123;
//        AreaIDDTO areaIDDTO = new AreaIDDTO();
//        areaIDDTO.setArea_id(areaId);
//        String validCityName = "TestCity";
//        CityDTO cityDTO = new CityDTO();
//        cityDTO.setCity(validCityName);
//
//        when(locationServiceMock.getCityNameById(areaId)).thenReturn(validCityName);
//
//        ResponseEntity<CityDTO> response = locationController.CityById(areaIDDTO);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(cityDTO.getCity(), response.getBody().getCity());
//    }
//
//    @Test
//    public void testCityById_WithInvalidCity() {
//        // Mock data for a scenario where an invalid city name is returned
//        int areaId = 123;
//        AreaIDDTO areaIDDTO = new AreaIDDTO();
//        areaIDDTO.setArea_id(areaId);
//        String invalidCityName = null;
//        CityDTO cityDTO = new CityDTO();
//        cityDTO.setCity(invalidCityName);
//
//        when(locationServiceMock.getCityNameById(areaId)).thenReturn(invalidCityName);
//
//        ResponseEntity<CityDTO> response = locationController.CityById(areaIDDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(cityDTO.getCity(), response.getBody().getCity());
//    }
}
