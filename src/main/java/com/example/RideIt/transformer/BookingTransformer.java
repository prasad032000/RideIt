package com.example.RideIt.transformer;

import com.example.RideIt.Enum.TripStatus;
import com.example.RideIt.dto.request.TripBookingRequest;
import com.example.RideIt.dto.response.TripBookingResponse;
import com.example.RideIt.model.TripBooking;
import jakarta.persistence.GeneratedValue;

import java.util.UUID;

public class BookingTransformer {
    public static TripBooking bookingRequestToBooking(TripBookingRequest tripBookingRequest){
        return TripBooking.builder()
                .bookingId(String.valueOf(UUID.randomUUID()))
                .source(tripBookingRequest.getSource())
                .destination(tripBookingRequest.getDestination())
                .tripDistanceInKm(tripBookingRequest.getTripDistanceInKm())
                .tripStatus(TripStatus.IN_TRANSIT)
                .build();
    }

    public static TripBookingResponse tripBookingToTripBookingResponse(TripBooking tripBooking){
        return TripBookingResponse.builder()
                .bookingId(tripBooking.getBookingId())
                .source(tripBooking.getSource())
                .destination(tripBooking.getDestination())
                .tripDistanceInKm(tripBooking.getTripDistanceInKm())
                .totalFare(tripBooking.getTotalFare())
                .tripStatus(tripBooking.getTripStatus())
                .bookedAt(tripBooking.getBookedAt())
                .customerResponse(CustomerTransformer.customerToCustomerResponse(tripBooking.getCustomer()))
                .driverResponse(DriverTransformer.driverToDriverResponse(tripBooking.getDriver()))
                .build();
    }
}
