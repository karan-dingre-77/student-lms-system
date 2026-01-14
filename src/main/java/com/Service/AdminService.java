package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Admin;
import com.Entity.Student;
import com.Repository.AdminRepository;
import com.Repository.StudentRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepo;
	
	public Admin checkLoginInfo(String username, String password) {
		Admin validadmin = adminRepo.findByUsername(username);
		if(validadmin != null && validadmin.getPassword().equals(password)) {
			return validadmin; 
		}
		return null;
	}
}
