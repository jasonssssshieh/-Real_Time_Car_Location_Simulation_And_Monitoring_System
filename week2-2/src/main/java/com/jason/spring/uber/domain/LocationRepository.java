package com.jason.spring.uber.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @RestResource(rel = "by-service-type")
    Page<Location> findByVehicleMovementType(@Param("movementType") Location.VehicleMovementType movementType, Pageable pageable);
    @RestResource(path="vin", rel = "by-vin")
    Page<Location> findByUnitInfoUnitVin(@Param("unitVin") String unitVin, Pageable pageable);
    //因为unit vin是在一个embedable的 Unitinfo下的,所以我们第一层应该是UnitInfo, 然后才是UnitVin 不然我们spring data是没法找到匹配的
}
