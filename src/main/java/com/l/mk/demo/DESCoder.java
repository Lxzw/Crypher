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
	 * 	ת����Կ
	 *  @param key ��������Կ
	 *  @param key ��Կ
	 *  @throws Exception
	 * */
	private static Key toKey(byte[] key) throws Exception {
		//ʵ����DES��Կ����
		DESKeySpec dks = new DESKeySpec(key);
		//ʵ����������Կ����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		//������Կ
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}
	
	/**
	 * ����
	 * @param data ����������
	 * @param key ��Կ
	 * @return byte[] ��������
	 * @throws Exception
	 * */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		//��ԭ��Կ
		Key k = toKey(key);
		//ʵ����
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * ����
	 * @param data ����������
	 * @param key ��Կ
	 * @return byte[] ��������
	 * @throws Exception
	 * */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		//��ԭ��Կ
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//��ʼ�������ý���ģʽ
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * ������Կ<br>
	 * Java 6ֻ֧��56λ��Կ<br>
	 * @return byte[] ��������Կ
	 * @throws Excepiton
	 * */
	public static byte[] initKey() throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(56);
		SecretKey secretKey = kg.generateKey();
		//�����Կ�Ķ����Ʊ���ʽ
		return secretKey.getEncoded();
	}
}
/**
 *���á�java��������������� ������ �������ݼ���-�ԳƼ���
 * */
