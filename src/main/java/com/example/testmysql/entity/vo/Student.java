package com.example.testmysql.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 10:49
 **/
@Data
@TableName("student")
public class Student {
	private int id;
	private int empid;
	private String name;
	private String emp;
	private String word;
	private int age;

}
