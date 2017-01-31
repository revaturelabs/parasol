package com.revature.parasol.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.parasol.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
