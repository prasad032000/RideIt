package com.example.RideIt.service;

import com.example.RideIt.dto.request.TripBookingRequest;
import com.example.RideIt.dto.response.TripBookingResponse;
import com.example.RideIt.exception.CabNotFoundException;
import com.example.RideIt.exception.CustomerNotFoundException;
import com.example.RideIt.model.Cab;
import com.example.RideIt.model.Customer;
import com.example.RideIt.model.TripBooking;
import com.example.RideIt.repository.CabRepository;
import com.example.RideIt.repository.CustomerRepository;
import com.example.RideIt.repository.DriverRepository;
import com.example.RideIt.repository.TripBookingRepository;
import com.example.RideIt.transformer.BookingTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripBookingService {

    private final CustomerRepository customerRepository;
    private final CabRepository cabRepository;
    public final DriverRepository driverRepository;
    private final TripBookingRepository tripBookingRepository;
    public TripBookingResponse bookCab(boolean applyCoupon, TripBookingRequest tripBookingRequest) {

        //1st step is to validate the customer
        Customer customer = customerRepository.findByEmailId(tripBookingRequest.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid email Id");
        }

        //check if cab is available or not
        //get a random available cab

        Cab cab = cabRepository.getRandomAvailableCab();
        if(cab ==null){
            throw new CabNotFoundException("Sorry! All drivers are busy now. Please book after some time!!");
        }

        //prepare the booking entity
        TripBooking tripBooking = BookingTransformer.bookingRequestToBooking(tripBookingRequest);
        tripBooking.setTotalFare(cab.getFarePerKm()* tripBookingRequest.getTripDistanceInKm());

        tripBooking.setCustomer(customer);
        tripBooking.setDriver(cab.getDriver());
        TripBooking savedTripBooking = tripBookingRepository.save(tripBooking);

        customer.getBookings().add(savedTripBooking);
        cab.setAvailable(false);
        cab.getDriver().getBookings().add(savedTripBooking);

        //customer and booking
        customerRepository.save(customer);//customer + savedBooking
        driverRepository.save(cab.getDriver()); // driver + cab + savedBooking

        //last step -> prepare booking response
        return BookingTransformer.tripBookingToTripBookingResponse(savedTripBooking);
    }


}
