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
	public byte[] messageHeaderBytes() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static MessageHeader getMessageHeader(byte[] b) {
		return null;
	}
	
	public byte[] toBytes() {
		byte[] b = new byte[16];
		b[0] = length;
		System.arraycopy(kprq, 0, b, 1, kprq.length);
		System.arraycopy(sph, 0, b, 5, sph.length);
		System.arraycopy(zzj, 0, b, 12, zzj.length);
		return b;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}

	public byte[] getKprq() {
		return kprq;
	}

	public void setKprq(byte[] kprq) {
		this.kprq = kprq;
	}

	public byte[] getSph() {
		return sph;
	}

	public void setSph(byte[] sph) {
		this.sph = sph;
	}

	public byte[] getZzj() {
		return zzj;
	}

	public void setZzj(byte[] zzj) {
		this.zzj = zzj;
	}
	
}
