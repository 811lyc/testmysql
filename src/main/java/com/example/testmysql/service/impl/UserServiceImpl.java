package com.example.testmysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testmysql.dao.UserDao;
import com.example.testmysql.entity.vo.User;
import com.example.testmysql.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 17:58
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
