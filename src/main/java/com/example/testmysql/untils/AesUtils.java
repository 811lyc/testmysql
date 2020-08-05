package com.example.testmysql.untils;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 *
 * hutool 提供了加密 类
 * @author LYC
 * @desc
 * @create 2020-08-04 16:44
 **/
public class AesUtils {

	/**
	 * aes 加密（16进制）
	 * @param key
	 * @param content
	 * @return
	 */
	public static String encodeHex(String key,String content){
		SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
		return symmetricCrypto.encryptHex(content);
	}


	/**
	 * 解密
	 * @param key
	 * @param content
	 * @return
	 */
	public static String decodeHex(String key,String content){
		SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
		return symmetricCrypto.decryptStr(content);
	}
}
