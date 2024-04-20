package com.example.RideIt.dto.response;

import com.example.RideIt.Enum.TripStatus;
import com.example.RideIt.dto.request.CustomerRequest;
import com.example.RideIt.dto.request.DriverRequest;
import com.example.RideIt.model.Customer;
import com.example.RideIt.model.Driver;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TripBookingResponse {
    String bookingId;

    String source;

    String destination;

    double tripDistanceInKm;

    double totalFare;

    TripStatus tripStatus;

    Date bookedAt;

    CustomerResponse customerResponse;

    DriverResponse driverResponse;
}
