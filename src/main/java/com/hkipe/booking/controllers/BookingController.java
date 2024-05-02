package com.hkipe.booking.controllers;

import com.hkipe.booking.models.Booking;
import com.hkipe.booking.models.Device;
import com.hkipe.booking.repositories.BookingRepository;
import com.hkipe.booking.repositories.DeviceRepository;
import com.hkipe.booking.repositories.UserRepository;
import com.hkipe.booking.requests.CreateBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all-bookings")
    public String showAllBookings(Model model) {
        var bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "booking/all-bookings";
    }

    @GetMapping("my-bookings")
    public String showMyBookings(Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        var bookings = bookingRepository.findByUsername(authentication.getName());
        model.addAttribute("bookings", bookings);
        return "booking/my-bookings";
    }

    @GetMapping("create-booking")
    public String showCreateBookingForm(Model model) {
        var devices = deviceRepository.findAll();
        model.addAttribute("devices", devices);
        return "booking/create-booking";
    }

    @PostMapping("create-booking")
    public String createBooking(CreateBookingRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        var booking = new Booking();
        booking.setDevice(deviceRepository.findById(request.getDeviceId()).get());
        booking.setUser(userRepository.findByUsername(authentication.getName()));
        booking.setStartDate(Timestamp.valueOf(LocalDateTime.parse(request.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        booking.setEndDate(Timestamp.valueOf(LocalDateTime.parse(request.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        booking.setPickedUp(false);
        booking.setReturned(false);
        bookingRepository.save(booking);

        return "redirect:/my-bookings";
    }

    @GetMapping("/booking/{id}/pickup")
    public String bookingPickup(@PathVariable Long id) {
        var booking = bookingRepository.findById(id).get();
        var device = booking.getDevice();
        if (!device.isOccupied()) {
            booking.setPickedUp(true);
            device.setOccupied(true);

            bookingRepository.save(booking);
            deviceRepository.save(device);
        }

        return "redirect:/all-bookings";
    }

    @GetMapping("/booking/{id}/return")
    public String bookingReturn(@PathVariable Long id) {
        var booking = bookingRepository.findById(id).get();
        var device = booking.getDevice();

        booking.setReturned(true);
        device.setOccupied(false);

        bookingRepository.save(booking);
        deviceRepository.save(device);

        return "redirect:/all-bookings";
    }
}
