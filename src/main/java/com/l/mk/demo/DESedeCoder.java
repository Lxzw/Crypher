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
	 * ��Կ�㷨
	 * java 6 ֧����Կ����Ϊ112λ��168λ
	 */
	public static final String KEY_ALGORITHM = "DESede";
	
	/**
	 * ����/�����㷨  / ����ģʽ  / ��䷽ʽ
	 */
	public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
	
	/**
	 * ת����Կ
	 * @param key ��������Կ
	 * @return Key ��Կ
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		//ʵ����DESede��Կ����
		DESedeKeySpec dks = new DESedeKeySpec(key);
		//ʵ����������Կ����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		//������Կ
		return keyFactory.generateSecret(dks);
	}
	
	/**
	 * ����
	 * @param data �����ܵ�����
	 * @param key  ��Կ
	 * @return byte[] ��������
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		//��ԭ��Կ
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//��ʼ�������ý���ģʽ
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * ����
	 * @param data ����������
	 * @param key  ��Կ
	 * @return byte[] ��������
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		//��ԭ��Կ
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//��ʼ��������Ϊ����ģʽ
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * ������Կ<br>
	 * @return byte[] ��������Կ
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(168);
		//����������Կ
		SecretKey secretKey = kg.generateKey();
		//SecretKeySpec s = new SecretKeySpec(key,KEY_ALGORITHM);
		//s.getEncoded();
		return secretKey.getEncoded();
	}
}

/**
 *���á�java��������������� ������ �������ݼ���-�ԳƼ���
 * */

