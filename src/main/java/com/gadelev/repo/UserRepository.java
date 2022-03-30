package com.gadelev.repo;


import com.gadelev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    Optional<User> getByEmail(String email);

    User findByVerificationCode(String code);

}
