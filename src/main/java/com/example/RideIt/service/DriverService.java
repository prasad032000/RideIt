package com.example.RideIt.service;

import com.example.RideIt.dto.request.CabRequest;
import com.example.RideIt.dto.request.DriverRequest;
import com.example.RideIt.dto.response.DriverResponse;
import com.example.RideIt.exception.DriverNotFoundException;
import com.example.RideIt.model.Cab;
import com.example.RideIt.model.Driver;
import com.example.RideIt.repository.DriverRepository;
import com.example.RideIt.transformer.CabTransformer;
import com.example.RideIt.transformer.DriverTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public DriverResponse updateDriver(DriverRequest driverRequest) {
        Driver driver = driverRepository.findDriverByMobNo(driverRequest.getMobNo());
        driver.setAge(driverRequest.getAge());
        driver.setName(driverRequest.getName());
        driver.setPanNumber(driverRequest.getPanNumber());

        CabRequest cabRequest = driverRequest.getCabRequest();

        Cab cab = CabTransformer.cabRequestToCab(cabRequest);
        cab.setId(driver.getCab().getId());

        driver.setCab(cab);
        cab.setDriver(driver);

        driverRepository.save(driver);

        return DriverTransformer.driverToDriverResponse(driver);
    }

    public String deleteDriver(String mobNo) {
        //get Driver from database
        Driver driver = driverRepository.findDriverByMobNo(mobNo);
        if(driver !=null){
            driverRepository.delete(driver);
        }else{
            throw new DriverNotFoundException("Driver not Found");
        }
        return "Driver Deleted";
    }

    public DriverResponse getDriver(String mobNo) {
        Driver driver = driverRepository.getDriverByMob(mobNo);
        return DriverTransformer.driverToDriverResponse(driver);
    }
}
