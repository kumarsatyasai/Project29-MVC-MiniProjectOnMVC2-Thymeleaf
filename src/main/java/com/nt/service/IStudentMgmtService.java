package com.nt.service;

import com.nt.entity.Student;

public interface IStudentMgmtService {
	
	public Iterable<Student> findAllStudents();
	
	public String registerStudentInfo(Student std);
	
	public Student findStudentById(Integer sno);
	
	public String updateStudentInfo(Student std);
	
	public String deleteStudentInfo(Integer sno);

}
