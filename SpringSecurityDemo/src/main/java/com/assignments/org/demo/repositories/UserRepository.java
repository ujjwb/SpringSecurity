package com.assignments.org.demo.repositories;

import com.assignments.org.demo.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}
