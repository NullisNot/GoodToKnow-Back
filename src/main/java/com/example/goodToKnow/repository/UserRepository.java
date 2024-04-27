package com.example.goodToKnow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByUserName(String userName);
}
