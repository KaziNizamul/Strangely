package com.strangely.backend.Model.Location.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LatLongDTO {
    private double latitude;
    private double longitude;
}
