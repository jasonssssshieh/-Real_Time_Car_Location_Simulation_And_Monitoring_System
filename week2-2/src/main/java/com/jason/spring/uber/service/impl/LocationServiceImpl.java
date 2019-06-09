package com.jason.spring.uber.service.impl;

import com.jason.spring.uber.domain.Location;
import com.jason.spring.uber.domain.LocationRepository;
import com.jason.spring.uber.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    //在impl的package里面真正的对Location Service Interface 的实现
    //需要去调用repository 去完成这些功能
    LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public List<Location> saveLocations(List<Location> carLocations) {
            return locationRepository.save(carLocations);
            //jpa已经帮我们实现了save功能
    }

    public void deleteAll() {
            locationRepository.deleteAll();
    }

    public Page<Location> findByVehicleMovementType(String movementType, Pageable pageable) {
            return locationRepository.findByVehicleMovementType(Location.VehicleMovementType.valueOf(movementType), pageable);
    }

    public Page<Location> findByVin(String vin, Pageable pageable) {
            return locationRepository.findByUnitInfoUnitVin(vin, pageable);
    }
}
