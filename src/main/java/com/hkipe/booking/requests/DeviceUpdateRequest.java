package com.hkipe.booking.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceUpdateRequest {
    private String serialNumber;
    private String modelNumber;
}
