package com.example.testmysql.entity;



import java.util.HashMap;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 15:11
 **/
public class ResponseEntity extends HashMap<String,Object> {



	public ResponseEntity message(String message){
		this.put("message",message);
		return this;
	}

	public ResponseEntity code(String code){
		this.put("code",code);
		return this;
	}

	public ResponseEntity resultEnum(ResultEnum resultEnum) {
		this.put("code", resultEnum.code);
		this.put("message", resultEnum.message);
		return this;
	}

	@Override
	public Object put(String s, Object o) {
		return super.put(s, o);
	}

}
