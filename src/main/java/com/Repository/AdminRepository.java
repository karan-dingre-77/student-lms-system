package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.Admin;
import com.Entity.Student;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByUsername(String username);
}