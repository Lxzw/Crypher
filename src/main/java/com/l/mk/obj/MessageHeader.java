package com.l.mk.obj;

public class MessageHeader {
	
	private byte sjnrcd; //数据内容长度
	private byte[] kprq = new byte[4]; //开票日期
	private byte[] sph = new byte[7]; //税票号
	private byte[] zzjbh = new byte[4]; //自助机编号

	public byte getSjnrcd() {
		return sjnrcd;
	}
	public void setSjnrcd(byte sjnrcd) {
		this.sjnrcd = sjnrcd;
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
	public byte[] getZzjbh() {
		return zzjbh;
	}
	public void setZzjbh(byte[] zzjbh) {
		this.zzjbh = zzjbh;
	}
}
