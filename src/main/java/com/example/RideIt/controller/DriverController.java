package com.example.RideIt.controller;

import com.example.RideIt.dto.request.DriverRequest;
import com.example.RideIt.dto.response.DriverResponse;
import com.example.RideIt.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/driver")
public class DriverController {

//    @Autowired
//    DriverService driverService;

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<String> addDriver(@RequestBody DriverRequest driverRequest){
        String response = driverService.addDriver(driverRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }




    @PutMapping ResponseEntity<DriverResponse> updateDriver(@RequestBody DriverRequest driverRequest){
        DriverResponse response = driverService.updateDriver(driverRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //delete a driver by mob no,(don't manually get the cab)//completed
    @DeleteMapping ResponseEntity<String> deleteDriver(@RequestParam String mobNo){
        String response = driverService.deleteDriver(mobNo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Get Driver by mobile No//completed
    @GetMapping ResponseEntity<DriverResponse> getDriver(@RequestParam String  mobNo){
        DriverResponse response = driverService.getDriver(mobNo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
