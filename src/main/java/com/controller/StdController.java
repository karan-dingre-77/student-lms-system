package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Entity.Student;
import com.Service.StdService;

@Controller
public class StdController {

	@Autowired
	StdService stdService;

	@RequestMapping("/student/register")
	public String registerStudent(Model model) {
		model.addAttribute("register", new Student());
		return "studentRegister";
	}

	@RequestMapping("/student/newlogin")
	public String register(@ModelAttribute("register") Student stdRegister, Model model) {
		boolean status = stdService.registerStudent(stdRegister);
		if(status) {
			model.addAttribute("student", new Student());
			return "studentlogin2";
		}else {
			model.addAttribute("err", "your registration failed..!");
			return "studentRegister"; 
		}
		
	}

	@RequestMapping("/student/login")
	public String student(Model model) {
		model.addAttribute("student", new Student());
		return "studentlogin";
	}

	@RequestMapping("/student/profile")
	public String studentLogin(@ModelAttribute("student") Student student, Model model) {
		Student studentData = stdService.checkStdInfo(student.getEmail(), student.getPassword());
		if (studentData != null) {
			model.addAttribute("student", studentData);
			return "studentViewPage";
		} else {
			model.addAttribute("err", "invalid user name and password...!");
			return "studentlogin";
		}
	}
}
