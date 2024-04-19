package com.example.RideIt.controller;

import com.example.RideIt.dto.request.DriverRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/driver")
public class DriverController {

    public ResponseEntity<String> addDriver(@RequestBody DriverRequest driverRequest){
        return null;

    }
}
