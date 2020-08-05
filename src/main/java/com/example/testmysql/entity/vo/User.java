package com.example.testmysql.entity.vo;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 16:32
 **/
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@TableId
	private int id;

	@TableField("username")
	private String username;

	@TableField("password")
	private String password;

	@TableField("is_delete")
	private int isDelete;

	@TableField("create_time")
	private Date createTime;

	@TableField("udpate_time")
	private Date updateTime;
 }
