package com.example.health.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import com.example.health.config.UserDetailsImpl;
import com.example.health.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);



	Boolean existsByUsername(String username);



    void save(Authentication user);



    // void saveByUser(UserDetailsImpl user);

    

}
