package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Student;
import com.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StdService {

	@Autowired
	StudentRepository studentRepo;

	public boolean registerStudent(Student stdData) {
		boolean status;
		try {
			studentRepo.save(stdData);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public Student checkStdInfo(String Email, String password) {
		Student validStudent = studentRepo.getByEmail(Email);
		if (validStudent != null && validStudent.getPassword().equals(password)) {
			return validStudent;
		}
		return null;
	}

	public List<Student> findStdByName(String name) {
		return studentRepo.findByName(name);
	}

	public Student findStdByRollNo(int rollNo) {

		try {
			Student student = studentRepo.findByRollNo(rollNo);
			return student;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Student> findAllStudents() {
		List<Student> stdList = studentRepo.findAll();
		if (stdList != null && !stdList.isEmpty()) {
			return stdList;
		} else {
			return null;
		}
	}

	public boolean addNewStudent(Student std) {
		boolean status;
		try {
			studentRepo.save(std);
			status = true;
		} catch (Exception e) {
			return false;
		}
		return status;
	}

	@Transactional
	public boolean deleteByName(String name) {
		boolean status;
		long count = studentRepo.deleteByName(name);
		if (count > 0) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	@Transactional
	public boolean deleteByRollNo(int rollno) {
		long count = studentRepo.deleteByRollNo(rollno);
		if (count > 0) {
			return true;
		} else {
			return false; 
		}
	}

	public Student findByroll(int rollno) {
		Student std = studentRepo.findByRollNo(rollno);
		if (std != null) {
			return std;
		} else {
			return null;
		}
	}

	public Student confirmUpdate(Student student) {
		try {
			studentRepo.save(student);
			Student std = studentRepo.findByRollNo(student.getRollNo());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
