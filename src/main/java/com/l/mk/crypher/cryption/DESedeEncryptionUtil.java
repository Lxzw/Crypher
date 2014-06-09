package com.l.mk.crypher.cryption;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

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
	public static byte[] initKey(String key1,String key2,String key3) throws Exception {
		Key k1 = new SecretKeySpec(strTo56(key1).getBytes(),"DESede");
		Key k2 = new SecretKeySpec(strTo56(key2).getBytes(),"DESede");
		Key k3 = new SecretKeySpec(strTo56(key3).getBytes(),"DESede");
		byte[] key = new byte[192];
		System.arraycopy(k1.getEncoded(), 0, key, 0, k1.getEncoded().length);
		System.arraycopy(k2.getEncoded(), 0, key, 64, k2.getEncoded().length);
		System.arraycopy(k3.getEncoded(), 0, key, 128, k3.getEncoded().length);
		return key;
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
