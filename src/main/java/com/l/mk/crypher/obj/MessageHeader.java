package com.l.mk.crypher.obj;

/**
 * 
 * @author L
 *
 * 定义报文头对象
 */
public class MessageHeader {
	
	private byte	length;//数据长度
	private byte[]	kprq = new byte[4]; //开票日期
	private byte[]	sph	 = new byte[7]; //税票号
	private byte[]	zzj	 = new byte[4]; //自助机编号
	
	/**
	 * 将MessageHeader对象中的数据转换为字节流表示 
	 * @return 字节数组为8（1 + 4 + 7 + 1）
	 */
	public byte[] getMessageHeaderByteStream() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public MessageHeader getMessageHeader(byte[] b) {
		return null;
	}
	
	
}
