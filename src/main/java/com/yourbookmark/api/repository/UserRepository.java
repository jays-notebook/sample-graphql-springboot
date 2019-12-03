package com.yourbookmark.api.repository;

import com.yourbookmark.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(long userId);
    User findByName(String name);
}
