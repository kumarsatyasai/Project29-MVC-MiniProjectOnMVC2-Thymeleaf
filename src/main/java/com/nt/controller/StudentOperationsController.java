package com.nt.controller;

import java.util.Map;   

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Student;
import com.nt.service.IStudentMgmtService;

@Controller
@RequestMapping("/student")
public class StudentOperationsController {
	
	@Autowired
	private IStudentMgmtService studentService;
	
	@GetMapping("/")
	public String showStudentHomePage() {
		return "show_home";
	}
	
	@GetMapping("/studentreport")
    public String showStudentReport(Map<String,Object> map) {
    	
		Iterable<Student> students = studentService.findAllStudents();
		students.forEach(System.out::println);
		map.put("studentsList", students);
    	return "show_report";
    }
	
	@GetMapping("/launchregisterform")
	public String launchRegisterForm(@ModelAttribute Student student) {
		
		return "register_form";
	}
	
	@PostMapping("/addstudent")
	public String addStudent(@ModelAttribute Student student, RedirectAttributes attrs) {
		
		String registerStudentInfo = studentService.registerStudentInfo(student);
		attrs.addFlashAttribute("resultMsg", registerStudentInfo);
		return "redirect:studentreport";
	}
	
	@GetMapping("/editform")
	public String showEditFormPage(@RequestParam Integer sno, @ModelAttribute Student student) {
		
		
		Student student1 = studentService.findStudentById(sno);
		BeanUtils.copyProperties(student1, student);
		return "edit_form";
	}
	
	@PostMapping("/edit")
	public String updateStudent(@ModelAttribute Student student, RedirectAttributes attrs) {
		
		String updateStudentInfo = studentService.updateStudentInfo(student);
		attrs.addFlashAttribute("editResult", updateStudentInfo);
		return "redirect:studentreport";
	}
	
	@GetMapping("/delete")
	public String deleteRecord(@RequestParam Integer sno, RedirectAttributes attrs) {
		
		
		String deleteStudentInfo = studentService.deleteStudentInfo(sno);
		attrs.addFlashAttribute("deleteResult", deleteStudentInfo);
		return "redirect:studentreport";
	}

}
