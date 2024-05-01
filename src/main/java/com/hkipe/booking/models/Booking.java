package com.hkipe.booking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "username", nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    private Timestamp startDate;

    private Timestamp endDate;

    private boolean pickedUp;

    private boolean returned;

}