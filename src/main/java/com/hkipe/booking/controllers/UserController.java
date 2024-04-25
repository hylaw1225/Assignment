package com.hkipe.booking.controllers;

import com.hkipe.booking.models.User;
import com.hkipe.booking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String listUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/create-user")
    public String showCreateUserForm(Model model) {
        return "user/create-user";
    }

    @PostMapping("create-user")
    public String createUser(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit-user/{id}")
    public String showEditUserForm(Model model, @PathVariable("id") Long id) {
        var user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "user/edit-user";
    }

//    @PostMapping("/edit-user/{id}")
//    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") UserUpdateRequest userUpdateRequest) {
//        var user = userRepository.findById(id).get();
////        user.setModelNumber(userUpdateRequest.getModelNumber());
////        user.setSerialNumber(userUpdateRequest.getSerialNumber());
//        userRepository.save(user);
//        return "redirect:/users";
//    }

//    private class GetUsersResponse<T> {
//        public Iterable<T> data;
//
//        public GetUsersResponse(Iterable<T> data) {
//            this.data = data;
//        }
//    }
}
