package com.example.goodToKnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
