package com.learn.spring.security.practice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.spring.security.practice.models.User;


public interface UserRepository extends JpaRepository<User,String>{

    public User findByUsername(String username);
    
}
