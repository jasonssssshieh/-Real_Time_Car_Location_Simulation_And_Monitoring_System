package com.jason.spring.uber.rest;

        import com.jason.spring.uber.domain.Location;
        import com.jason.spring.uber.domain.LocationRepository;
        import com.jason.spring.uber.service.LocationService;
        import com.jason.spring.uber.service.impl.LocationServiceImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Pageable;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.*;

        import javax.websocket.server.PathParam;
        import java.util.*;

@RestController
public class FleetBulkUploadController {
    private LocationService locationService;

    @Autowired
    public FleetBulkUploadController(LocationService locationService){
        this.locationService = locationService;
    }

    //做post的请求的话,需要返回一个create的对象
    @RequestMapping(value = "/fleet", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Location> locations) throws Exception {
        this.locationService.saveLocations(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.POST)
    public void purge() {
        //清除所有记录
        this.locationService.deleteAll();
    }

    @RequestMapping(value = "/fleet/{movementType}", method = RequestMethod.GET)
    public Page<Location> findByMovementType(@PathVariable String movementType,  @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return this.locationService.findByVehicleMovementType(movementType, new PageRequest(page, size));
    }

    @RequestMapping(value = "/fleet/vin/{vin}", method = RequestMethod.GET)
    public Page<Location> findByVinNumber(@PathVariable String vin, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        return this.locationService.findByVin(vin, new PageRequest(page, size));
    }
}
