package com.example.RideIt.dto.request;

import com.example.RideIt.Enum.TripStatus;
import com.example.RideIt.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripBookingRequest {
    String source;

    String destination;

    double tripDistanceInKm;

    String customerEmailId;
}
