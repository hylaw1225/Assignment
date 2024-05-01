package com.hkipe.booking.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingRequest {
    private Long deviceId;
    private String startDate;
    private String endDate;
}
