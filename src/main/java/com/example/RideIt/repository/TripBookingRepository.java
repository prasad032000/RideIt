package com.example.RideIt.repository;

import com.example.RideIt.model.TripBooking;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripBookingRepository extends JpaRepository<TripBooking, Integer> {
}
