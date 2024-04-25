package com.hkipe.booking.controllers;

import com.hkipe.booking.models.Device;
import com.hkipe.booking.repositories.DeviceRepository;
import com.hkipe.booking.requests.DeviceUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/devices")
    public String listDevices(Model model) {
        Iterable<Device> devices = deviceRepository.findAll();
        model.addAttribute("devices", devices);
        return "device/devices";
    }

    @GetMapping("/create-device")
    public String showCreateDeviceForm(Model model) {
        return "device/create-device";
    }

    @PostMapping("create-device")
    public String createDevice(Device device) {
        deviceRepository.save(device);
        return "redirect:/devices";
    }

    @GetMapping("/edit-device/{id}")
    public String showEditDeviceForm(Model model, @PathVariable("id") Long id) {
        var device = deviceRepository.findById(id).get();
        model.addAttribute("device", device);
        return "device/edit-device";
    }

    @PostMapping("/edit-device/{id}")
    public String editDevice(@PathVariable("id") Long id, @ModelAttribute("device") DeviceUpdateRequest deviceUpdateRequest) {
        var device = deviceRepository.findById(id).get();
        device.setModelNumber(deviceUpdateRequest.getModelNumber());
        device.setSerialNumber(deviceUpdateRequest.getSerialNumber());
        deviceRepository.save(device);
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
