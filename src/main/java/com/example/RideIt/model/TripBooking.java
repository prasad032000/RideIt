package com.example.RideIt.model;

import com.example.RideIt.Enum.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;//util contains timeStamp+date

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TripBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String bookingId;

    String source;

    String destination;

    double tripDistanceInKm;

    double totalFare;

    TripStatus tripStatus;

    @CreationTimestamp
    Date bookedAt;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    Driver driver;
}
