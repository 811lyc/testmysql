package com.example.testmysql.controller;

import com.example.testmysql.entity.vo.Student;
import com.example.testmysql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 10:47
 **/
@RestController
@RequestMapping
public class StudentController {
	@Autowired
	private StudentService studentService;
	@RequestMapping("test")
	public void Student(){
		List<String> auth = Arrays.asList("auth");
		System.out.println(auth);
		List<Student> list = studentService.list();
		System.out.println(list.size());
		list.forEach(
				System.out::println
		);
	}
}
