package com.strangely.backend.UnitTest.Service.Homepage;

import com.strangely.backend.Model.Location.Entities.Area;
import com.strangely.backend.Repository.Location.AreaRepository;
import com.strangely.backend.Service.Location.Implementation.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LocationServiceTest {


    @Mock
    private AreaRepository areaRepositoryMock;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAreas() {
        List<Area> mockAreas = Arrays.asList(
                new Area(1, "Area1", 40.7128, -74.0060),
                new Area(2, "Area2", 34.0522, -118.2437)
        );
        when(areaRepositoryMock.findAll()).thenReturn(mockAreas);
        List<Area> result = locationService.getAllAreas();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindNearestArea() {
        List<Area> mockAreas = Arrays.asList(
                new Area(1, "Area1", 40.7128, -74.0060),
                new Area(2, "Area2", 34.0522, -118.2437)
        );
        when(locationService.getAllAreas()).thenReturn(mockAreas);
        int nearestAreaId = locationService.findNearestArea(40.0, -75.0);
        assertEquals(1, nearestAreaId);
    }

    @Test
    public void testGetCityNameById_WhenAreaExists() {
        int areaId = 1;
        Area mockArea = new Area(areaId, "Area1", 40.7128, -74.0060);
        when(areaRepositoryMock.findByAreaId(areaId)).thenReturn(mockArea);
        String cityName = locationService.getCityNameById(areaId);
        assertEquals("Area1", cityName);
    }

    @Test
    public void testGetCityNameById_WhenAreaNotFound() {
        int nonExistingAreaId = 999;
        when(areaRepositoryMock.findByAreaId(nonExistingAreaId)).thenReturn(null);
        String cityName = locationService.getCityNameById(nonExistingAreaId);
        assertEquals("Area not found", cityName);
    }

    @Test
    public void testFindNearestArea_NoAreasAvailable() {
        when(locationService.getAllAreas()).thenReturn(Collections.emptyList());
        int nearestAreaId = locationService.findNearestArea(40.0, -75.0);
        assertEquals(0, nearestAreaId);
    }


    @Test
    public void testGetCityNameById_WhenRepositoryReturnsNull() {
        int nonExistingAreaId = 999;
        when(areaRepositoryMock.findByAreaId(nonExistingAreaId)).thenReturn(null);
        String cityName = locationService.getCityNameById(nonExistingAreaId);
        assertEquals("Area not found", cityName);
    }

}
