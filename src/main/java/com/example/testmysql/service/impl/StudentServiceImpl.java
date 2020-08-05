package com.example.testmysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testmysql.dao.StudentDao;
import com.example.testmysql.entity.vo.Student;
import com.example.testmysql.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 10:47
 **/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

//	@Override
//	public List<Map> selectStudentList() {
//		System.out.println(studentDao.selectStudentList());
//		return null;
//	}
}
