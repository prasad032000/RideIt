package com.example.RideIt.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverRequest {
    String name;

    int age;

    String panNumber;

    String mobNo;

    CabRequest cabRequest;
}
