package com.example.testmysql.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.testmysql.contants.CommonContants;
import com.example.testmysql.entity.ResponseEntity;
import com.example.testmysql.entity.ResultEnum;
import com.example.testmysql.entity.vo.User;
import com.example.testmysql.service.UserService;
import com.example.testmysql.untils.AesUtils;
import com.example.testmysql.untils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 15:07
 **/
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("auth")
public class LoginController {

	private final UserService userService;

	/**
	 * 注册
	 * @return
	 */
	@PostMapping("register")
	public ResponseEntity register(User user){
		//密码aes 加密密码
		String password = AesUtils.encodeHex(CommonContants.JIA_MI, user.getPassword());
		user.setPassword(password);
		user.setCreateTime(new DateTime());
		log.info("注册信息：{}",user);
		boolean save = userService.save(user);
		if(save){
			return new ResponseEntity().message("注册成功");
		}
		return new ResponseEntity().resultEnum(ResultEnum.A0100);
	}

	@PostMapping("login")
	public ResponseEntity login(User user){
		log.info("user登录数据：{}",user);
//      异常
//		try {
//			userService.list();
//		}catch (Exception e){
		//可以查看异常报错信息
//			System.err.println(e.getMessage());
//		}
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.lambda().eq(User::getUsername,"焦");
		User one = userService.getOne(userQueryWrapper);
		if(one == null){
			return new ResponseEntity().resultEnum(ResultEnum.A0201);
		}
		//密码aes 加密密码
		String password = AesUtils.encodeHex(CommonContants.JIA_MI, user.getPassword());
		if(password.equals(one.getPassword())){
			//先对比 redis是否有数据，有就重新生成token踢掉前面的token
			String token = JwtUtils.createToken(UUID.randomUUID().toString(), user.toString(), System.currentTimeMillis());
			return new ResponseEntity().message(token);
		}
		return new ResponseEntity().resultEnum(ResultEnum.A0001);
	}

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username","username");
		jsonObject.put("password","password");
		String qwreredsfafafdsaaffa = AesUtils.encodeHex(CommonContants.JIA_MI, jsonObject.toString());
		System.err.println(qwreredsfafafdsaaffa);
		String qwreredsfafafdsaaffa1 = AesUtils.decodeHex(CommonContants.JIA_MI, qwreredsfafafdsaaffa);
		System.err.println(qwreredsfafafdsaaffa1);

	}

}
