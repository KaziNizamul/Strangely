package com.strangely.backend.Repository.Location;

import com.strangely.backend.Model.Location.Entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    Area findByAreaId(int areaId);
}

