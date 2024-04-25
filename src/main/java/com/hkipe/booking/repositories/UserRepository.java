package com.hkipe.booking.repositories;

import com.hkipe.booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}