package com.l.mk.crypher.obj;

public class Message {
	
	private byte[] sph = new byte[10];//税票号
	private byte   length; //姓名长度
	private byte[] name = new byte[30]; //姓名
	private byte[] skhj = new byte[6]; //缴款合计
	private byte[] qsny = new byte[3]; //起始年月
	private byte[] kprq = new byte[4]; //开票日期
	private byte[] zzny = new byte[3]; //终止年月
	private byte   zjh_type;            //证件号类型和长度
	private byte[] zjh = new byte[16];   //证件号
	private byte[] jfyf = new byte[2]; //缴费月份
	private byte[] zzj  = new byte[4]; //自助机编号
	private byte[] jym  = new byte[8]; //校验码
	
	/**
	 * 获得校验码
	 * 将数据转换为字符串（每四位当成一个ASCII码），然后进行MD5，取最后8个字节
	 * @return
	 */
	public byte[] getJym() {
		return jym;
	}
	
	/**
	 *  获得报文字节数组
	 *  
	 *  1.确认报文是8的倍数(填充方式)
	 */
	public byte[] toBytes() {
		byte[] b = new byte[8];
		return b;
	} 
	
	/**
	 * 将字节数组转换为Message的对象
	 * 1. 校验字节数组
	 * 2. 解析字节数组
	 * @param b
	 * @return
	 */
	public Message getMessage(byte[] b) {
		return null;
	} 
	
	public byte[] getSph() {
		return sph;
	}

	public void setSph(byte[] sph) {
		this.sph = sph;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}

	public byte[] getName() {
		return name;
	}

	public void setName(byte[] name) {
		this.name = name;
	}

	public byte[] getSkhj() {
		return skhj;
	}

	public void setSkhj(byte[] skhj) {
		this.skhj = skhj;
	}

	public byte[] getQsny() {
		return qsny;
	}

	public void setQsny(byte[] qsny) {
		this.qsny = qsny;
	}

	public byte[] getKprq() {
		return kprq;
	}

	public void setKprq(byte[] kprq) {
		this.kprq = kprq;
	}

	public byte[] getZzny() {
		return zzny;
	}

	public void setZzny(byte[] zzny) {
		this.zzny = zzny;
	}

	public byte getZjh_type() {
		return zjh_type;
	}

	public void setZjh_type(byte zjh_type) {
		this.zjh_type = zjh_type;
	}

	public byte[] getZjh() {
		return zjh;
	}

	public void setZjh(byte[] zjh) {
		this.zjh = zjh;
	}

	public byte[] getJfyf() {
		return jfyf;
	}

	public void setJfyf(byte[] jfyf) {
		this.jfyf = jfyf;
	}

	public byte[] getZzj() {
		return zzj;
	}

	public void setZzj(byte[] zzj) {
		this.zzj = zzj;
	}

	public void setJym(byte[] jym) {
		this.jym = jym;
	}

}