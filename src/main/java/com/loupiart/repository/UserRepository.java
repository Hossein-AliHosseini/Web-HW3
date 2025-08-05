package com.loupiart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loupiart.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
}
