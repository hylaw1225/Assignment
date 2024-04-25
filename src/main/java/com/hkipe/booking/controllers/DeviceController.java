package com.hkipe.booking.controllers;

import com.hkipe.booking.models.Device;
import com.hkipe.booking.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/devices")
    public String listDevices(Model model) {
        Iterable<Device> devices = deviceRepository.findAll();
        model.addAttribute("devices", devices);
        return "devices";
    }

    @GetMapping("/create-device")
    public String showCreateDeviceForm(Model model) {
        return "create-device";
    }

    @PostMapping("create-device")
    public String createDevice(Device device) {
        deviceRepository.save(device);
        return "redirect:/devices";
    }

    @GetMapping("/edit-device")
    public String showEditDeviceForm(Model model) {
        return "edit-device";
    }

    @PostMapping("/edit-device")
    public String editDevice(Long deviceId) {
        System.out.println(deviceId);
        return "redirect:/devices";

    }

//    private class GetDevicesResponse<T> {
//        public Iterable<T> data;
//
//        public GetDevicesResponse(Iterable<T> data) {
//            this.data = data;
//        }
//    }
}
