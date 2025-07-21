package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.nt.entity.Student;
import com.nt.repository.IStudentRepository;

@Service
public class StudentMgmtServiceImpl implements IStudentMgmtService {
	
	@Autowired
	private IStudentRepository studentRepository;

	@Override
	public Iterable<Student> findAllStudents() {

		
		return studentRepository.findAll();
	}

	@Override
	public String registerStudentInfo(Student std) {
		// TODO Auto-generated method stub
		Student save = studentRepository.save(std);
		return "Student Registered with Student Number As : "+save.getSno();
	}

	@Override
	public Student findStudentById(Integer sno) {
		// TODO Auto-generated method stub
		Optional<Student> findById = studentRepository.findById(sno);
		if(findById.isPresent())
			return findById.get();
		return null;
	}

	@Override
	public String updateStudentInfo(Student std) {
		// TODO Auto-generated method stub
		Optional<Student> opt = studentRepository.findById(std.getSno());
		if(opt.isPresent()) {
			studentRepository.save(std);
			return "Student Edited With Student Number As : "+std.getSno();
		}
		return "No Such Student Found.";
	}

	@Override
	public String deleteStudentInfo(Integer sno) {
		// TODO Auto-generated method stub
		Optional<Student> optional = studentRepository.findById(sno);
		if(optional.isPresent())
		{
			
			studentRepository.deleteById(sno);
			return "Student Deleted With Student Number As : "+sno;
		}
		return "No Such Student Found With Student Number As : "+sno;
	}
	
	
	

}
