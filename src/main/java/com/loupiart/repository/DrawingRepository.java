package com.loupiart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loupiart.model.Drawing;
import com.loupiart.model.User;

public interface DrawingRepository extends JpaRepository<Drawing, Long> {
    Drawing findByUser(User user);
    void deleteByUser(User user);
}
