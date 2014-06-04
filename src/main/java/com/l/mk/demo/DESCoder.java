package com.l.mk.demo;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESCoder {
	
	public static final String KEY_ALGORITHM = "DES";
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	
	/**
	 * 	转换密钥
	 *  @param key 二进制密钥
	 *  @param key 密钥
	 *  @throws Exception
	 * */
	private static Key toKey(byte[] key) throws Exception {
		//实例化DES密钥材料
		DESKeySpec dks = new DESKeySpec(key);
		//实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		//生成密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}
	
	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 * */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		//还原密钥
		Key k = toKey(key);
		//实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 加密
	 * @param data 待加密数据
	 * @param key 密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 * */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		//还原密钥
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//初始化，设置解密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 生成密钥<br>
	 * Java 6只支持56位密钥<br>
	 * @return byte[] 二进制密钥
	 * @throws Excepiton
	 * */
	public static byte[] initKey() throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(56);
		SecretKey secretKey = kg.generateKey();
		//获得密钥的二进制编形式
		return secretKey.getEncoded();
	}
}
/**
 *引用《java加密与解密艺术》 第七章 初等数据加密-对称加密
 * */
