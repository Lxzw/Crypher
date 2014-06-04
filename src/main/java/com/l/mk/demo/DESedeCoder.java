package com.l.mk.demo;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class DESedeCoder {
	/**
	 * 密钥算法
	 * java 6 支持密钥长度为112位和168位
	 */
	public static final String KEY_ALGORITHM = "DESede";
	
	/**
	 * 加密/解密算法  / 工作模式  / 填充方式
	 */
	public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
	
	/**
	 * 转换密钥
	 * @param key 二进制密钥
	 * @return Key 密钥
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		//实例化DESede密钥材料
		DESedeKeySpec dks = new DESedeKeySpec(key);
		//实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		//生成密钥
		return keyFactory.generateSecret(dks);
	}
	
	/**
	 * 解密
	 * @param data 待解密的数据
	 * @param key  密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		//还原密钥
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//初始化，设置解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 加密
	 * @param data 待加密数据
	 * @param key  密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		//还原密钥
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 生成密钥<br>
	 * @return byte[] 二进制密钥
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(168);
		//生成秘密密钥
		SecretKey secretKey = kg.generateKey();
		//SecretKeySpec s = new SecretKeySpec(key,KEY_ALGORITHM);
		//s.getEncoded();
		return secretKey.getEncoded();
	}
}

/**
 *引用《java加密与解密艺术》 第七章 初等数据加密-对称加密
 * */

