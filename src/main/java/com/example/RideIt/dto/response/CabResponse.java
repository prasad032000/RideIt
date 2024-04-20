package com.example.RideIt.dto.response;

import com.example.RideIt.Enum.CarType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CabResponse {
    String cabNo;

    CarType carType;

    double farePerKm;
}
