package com.l.mk.crypher.cryption;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

public class DESedeEncryptionUtil {

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
	 * 
	 * ת����Կ
	 * @param key ��������Կ
	 * @return Key ��Կ
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		//ʵ��DESede��Կ����
		DESedeKeySpec dks = new DESedeKeySpec(key);
		//ʵ��������Կ����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		//�����Կ
		return keyFactory.generateSecret(dks);
	}
	
	/**
	 * ����
	 * @param data ����ܵ����
	 * @param key  ��Կ
	 * @return byte[] �������
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
	 * @param data ��������
	 * @param key  ��Կ
	 * @return byte[] �������
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
	 * 将至字符串产生key
	 * @return byte[] 
	 * @throws Exception
	 */
	public static byte[] initKey(byte[] key1,byte[] key2,byte[] key3) throws Exception {
		byte[] k1 = new byte[24];
		System.arraycopy(key1, 0, k1, 0, 8);
		System.arraycopy(key2, 0, k1, 8, 8);
		System.arraycopy(key3, 0, k1, 16, 8);
		DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(k1);
	
		return deSedeKeySpec.getKey();
	}
	
	/**
	 * 将不同长度的字符串变成56字节长的字符串
	 * @param s
	 * @return
	 */
	private static String strTo56(String s) {
		StringBuilder sb = new StringBuilder(s);
		if (s.length() < 64) {
			for (int i=0; i<(56-s.length()); i++) {
				sb.append(s.charAt(s.length()%(s.length()/2)));
			}
		}
		if (s.length() > 64) {
			for(int i=0; i< (s.length() - 64); i++) {
				sb.deleteCharAt(s.length()%(s.length()/2));
			}
		}
		return sb.toString();
	}
}
