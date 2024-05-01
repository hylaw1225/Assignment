package com.hkipe.booking.repositories;

import com.hkipe.booking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select * from booking b where b.username = ?1", nativeQuery = true)
    List<Booking> findByUsername(String username);

}