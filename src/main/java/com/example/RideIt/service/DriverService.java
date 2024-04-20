package com.example.RideIt.service;

import com.example.RideIt.dto.request.CabRequest;
import com.example.RideIt.dto.request.DriverRequest;
import com.example.RideIt.model.Cab;
import com.example.RideIt.model.Driver;
import com.example.RideIt.repository.DriverRepository;
import com.example.RideIt.transformer.CabTransformer;
import com.example.RideIt.transformer.DriverTransformer;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
//    @Autowired
//    DriverRepository driverRepository;

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    public String addDriver(DriverRequest driverRequest) {
        CabRequest cabRequest = driverRequest.getCabRequest();

        //driver request -> driver
        //cabRequest -> Cab
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Cab cab = CabTransformer.cabRequestToCab(cabRequest);

        //set the relational attributes
        driver.setCab(cab);
        cab.setDriver(driver);

        //
        driverRepository.save(driver);//This can save both driver and cab because we are using cascading in driver.

        return "Driver and Cab saved successfully";
    }
}
