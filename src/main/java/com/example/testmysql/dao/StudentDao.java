package com.example.testmysql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testmysql.entity.vo.Student;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 10:47
 **/

public interface StudentDao extends BaseMapper<Student> {
	/**
	 * 查询学生
	 * @return
	 */
//	@Select("select * from student")
//	List<Map> selectStudentList();

}
