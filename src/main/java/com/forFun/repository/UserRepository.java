package com.forFun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forFun.model.User;

/**
 * 
 * @author sandro.almeida
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);

    
}