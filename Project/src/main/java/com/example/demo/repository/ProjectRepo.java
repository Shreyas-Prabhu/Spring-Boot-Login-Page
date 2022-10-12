package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.User;
public interface ProjectRepo extends JpaRepository<User, String>{

	public User findByEmail(String email);
}
