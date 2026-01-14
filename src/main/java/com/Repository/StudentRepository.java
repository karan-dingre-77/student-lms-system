package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	public Student getByEmail(String Email);
    List<Student> findByName(String name);
    long deleteByName(String name);
    Student findByRollNo(int rollno); 
    long deleteByRollNo(int rollno);
}

