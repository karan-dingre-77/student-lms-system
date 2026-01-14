package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Entity.Admin;
import com.Entity.Student;
import com.Service.AdminService;
import com.Service.StdService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	StdService stdService;

	@RequestMapping("admin/login")
	public String admin(Model model) {
		model.addAttribute("admin", new Admin());
		return "adminlogin";
	}

	@RequestMapping("/admin/profile")
	public String adminLogin(@ModelAttribute("admin") Admin adminInfo, Model model) {
		Admin validadmin = adminService.checkLoginInfo(adminInfo.getUsername(), adminInfo.getPassword());
		if (validadmin != null) {
			model.addAttribute("admin", validadmin.getUsername());
			return "adminOperations";
		} else {
			model.addAttribute("err", "invalid username and password..!");
			return "adminlogin";
		}
	}

	@RequestMapping("/findstudent")
	public String findstudent(Model model) {
		model.addAttribute("studentn", new Student());
		model.addAttribute("studentr", new Student());
		model.addAttribute("studetnList", null);
		return "findstudent";
	}

	@RequestMapping("/findbyrollno")
	public String findbyrollno(@ModelAttribute("studentr") Student student, Model model) {
		Student std = stdService.findStdByRollNo(student.getRollNo());
		if (std != null) {
			model.addAttribute("studentrll", std);
			model.addAttribute("studentn", new Student());
			model.addAttribute("studentr", new Student());
			model.addAttribute("studetnList", null);
			return "findstudent";
		} else {
			model.addAttribute("err", "Student does't found!");
			model.addAttribute("studentn", new Student());
			model.addAttribute("studentr", new Student());
			model.addAttribute("studentnList", null);
			return "findstudent";
		}
	}

	@RequestMapping("/findbyname")
	public String findStdByName(@ModelAttribute("studentn") Student student, Model model) {
		List<Student> stdList = stdService.findStdByName(student.getName());
		if (!stdList.isEmpty()) {
			model.addAttribute("studetnList", stdList);
			model.addAttribute("studentr", new Student());
			model.addAttribute("studentn", new Student());
			return "findstudent";
		} else {
			model.addAttribute("err", "Student does't found!");
			model.addAttribute("studentr", new Student());
			model.addAttribute("studentn", new Student());
			return "findstudent";
		}
	}

	@RequestMapping("/viewallstudents")
	public String findAllStudents(Model model) {
		List<Student> studentList = stdService.findAllStudents();
		if (studentList != null && !studentList.isEmpty()) {
			model.addAttribute("studentList", studentList);
			return "allstudents";
		} else {
			model.addAttribute("err", "No students found!");
			return "allstudents";
		}
	}

	@RequestMapping("/addstudent")
	public String addStudent(Model model) {
		model.addAttribute("student", new Student());
		return "addNewStudent";
	}

	@RequestMapping("/admin/addstudent")
	public String addNewStudent(@ModelAttribute("student") Student std, Model model) {
		boolean status = stdService.addNewStudent(std);
		if (status) {
			model.addAttribute("scs", "Student added successfully...");
			model.addAttribute("student", new Student());
			return "addNewStudent";
		} else {
			model.addAttribute("err", "Failed to add student. Please try again.");
			return "addNewStudent";
		}
	}

	@RequestMapping("/deletestudent")
	public String deleteStudent(Model model) {
		model.addAttribute("studentn", new Student());
		model.addAttribute("studentr", new Student());
		return "deletestudent";
	}

	@RequestMapping("/deleteByName")
	public String deleteByName(@ModelAttribute("studentn") Student std, Model model) {
		boolean status = stdService.deleteByName(std.getName());
		if (status) {
			model.addAttribute("scs", "Student deleted successfully...");
			model.addAttribute("studentr", new Student());
			model.addAttribute("studentn", new Student());
			return "deletestudent";
		} else {
			model.addAttribute("err", "Failed to delete student. Please try again.");
			model.addAttribute("studentn", new Student());
			model.addAttribute("studentr", new Student());
			return "deletestudent"; 
		}
	}
	
	@RequestMapping("/deletebyrollno")
	public String deleteByRollno(@ModelAttribute("studentr") Student std, Model model) {
		boolean status = stdService.deleteByRollNo(std.getRollNo());
		if (status) {
			model.addAttribute("scs", "Student deleted successfully...");
			model.addAttribute("studentn", new Student());
			model.addAttribute("studentr", new Student());
			return "deletestudent"; 
		} else {
			model.addAttribute("err", "Failed to delete student. Please try again.");
			model.addAttribute("studentn", new Student());
			model.addAttribute("studentr", new Student());
			return "deletestudent";
		}
	}

	@RequestMapping("/updatestd")
	public String updateStudent(Model model) {
		model.addAttribute("student", new Student());
		return "upadatestd";
	}

	@RequestMapping("/updatestudent/byRollno")
	public String updateStudentbyRN(@ModelAttribute("student") Student student, Model model) {
		Student std = stdService.findByroll(student.getRollNo());
		if (std != null) {
			model.addAttribute("student1", std);
			model.addAttribute("student", new Student());
			return "upadatestd";
		} else {
			model.addAttribute("student", new Student());
			model.addAttribute("err", "Student not found..!");
			return "upadatestd";
		}
	}

	@RequestMapping("/admin/updatestd")
	public String confirmUpdate(@ModelAttribute("student1") Student student, Model model) {
		Student student1 = stdService.confirmUpdate(student);
		if (student1 != null) {
			model.addAttribute("scs", "Student data updated successfully..!");
			model.addAttribute("student1", student1);
			model.addAttribute("student", new Student());
			return "upadatestd";
		} else {
			model.addAttribute("scs", "Student data updated successfully..!");
			model.addAttribute("student", new Student());
			return "upadatestd";
		}
	}
	
	
	

	// --------------------------------------------------------------------------//

	@RequestMapping("/menuFrmFindStd")
	public String menuFrmFindStd() {
		return "adminOperations";
	}

	@RequestMapping("/menuFrmViewAllStd")
	public String menuFrmViewAllStd() {
		return "adminOperations";
	}

	@RequestMapping("/menuFrmAddNewStd")
	public String menuFrmAddNewStd() {
		return "adminOperations";
	}

	@RequestMapping("/menuFrmUpdtStd")
	public String menuFrmUpdtStd() {
		return "adminOperations";
	}

	@RequestMapping("/menuFrmDeletStd")
	public String menuFrmDeletStd() {
		return "adminOperations";
	}
	

}

