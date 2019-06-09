package com.jason.spring.uber.service;

import com.jason.spring.uber.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationService {
    //service尽量使用interface 因为他的底下可能会有不同的implementation
    //service层是定义我们具体的逻辑, 但不一定是和我们的Repositry紧耦合的
    //这里就是增删改查
    List<Location> saveLocations(List<Location> carLocations);

    void deleteAll();

    Page<Location> findByVehicleMovementType(String movementType, Pageable pageable);

    Page<Location> findByVin(String vin, Pageable pageable);
}
