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
	 *  2.byte数组的格式：
	 *  |姓名|证件号|起始年月|终止年月|税票号|开票日期|税款合计|缴费月份|校验码|自助机编号|
	 */
	public byte[] toBytes() {
		byte[] temp_name = new byte[this.getLength()+1];
		System.arraycopy(name, 0, temp_name, 0, this.length + 1);
		byte[] temp_zjh = null;//证件号仅仅能够处理身份证号码
		if (this.zjh[0] == 0x01 || zjh[0] == 0x02)
			temp_zjh  = new byte[10];
		System.arraycopy(this.getZjh(), 0, temp_zjh, 0, 10);
		//把所有message中的数据合并
		int len_tmp1 = temp_name.length+temp_zjh.length+qsny.length;
		int len_tmp2 = len_tmp1 + zzny.length + sph.length + kprq.length;
		int len = temp_name.length + temp_zjh.length + qsny.length + zzny.length 
				+ sph.length + kprq.length + this.getSkhj().length + jfyf.length + jym.length
				+ zzj.length;
		System.out.println(this.getSkhj().length);
		
		//生成8的倍数
		byte[] b = new byte[(len%8) == 0 ? len : (len/8 + 1) * 8 ];
		
		System.arraycopy(name, 0, b, 0, temp_name.length);
		System.arraycopy(zjh, 0, b, temp_name.length, temp_zjh.length);
		System.arraycopy(qsny, 0, b, temp_name.length+temp_zjh.length, qsny.length);
		System.arraycopy(zzny, 0, b, len_tmp1, zzny.length);
		System.arraycopy(sph, 0, b, len_tmp1+zzny.length , sph.length);
		System.arraycopy(kprq, 0, b, len_tmp1+zzny.length+sph.length, kprq.length);
		System.arraycopy(skhj, 0, b, len_tmp2, this.getSkhj().length);
		System.arraycopy(jfyf, 0, b, len_tmp2+skhj.length, jfyf.length);
		System.arraycopy(jym, 0, b, len_tmp2+skhj.length + jfyf.length, jym.length);
		System.arraycopy(zzj, 0, b, len_tmp2+skhj.length+jfyf.length+jym.length, zzj.length
				);
		
		return b;
	} 
	
	/**
	 * 将字节数组转换为Message的对象
	 * 1. 校验字节数组
	 * 2. 解析字节数组
	 * @param b
	 * 
	 * @return
	 */
	public static Message getMessage(byte[] b) {
		Message message = new Message();
		int recent_len = 0;
		//设置name属性
		message.setLength(b[0]); 
		byte[] temp_name = new byte[30];
		System.arraycopy(b, 0, temp_name, 0, message.getLength()+1);
		recent_len = message.getLength() + 1;
		message.setName(temp_name);
		//设置zjh属性
		byte[] temp_zjh = new byte[16];
		int zjh_len = 0;
		if (b[message.getLength()+1] == 0x01 || 
				b[message.getLength()+1] == 0x02) {
			zjh_len = 10;
			System.arraycopy(b, message.getLength() + 1, temp_zjh, 0, 10);
		} else {
			System.err.println("此类证件号还没有处理！请使用身份证号码。");
		}
		recent_len = recent_len + zjh_len;
		message.setZjh(temp_zjh);
		//起始年月
		byte[] qsny = new byte[3];
		System.arraycopy(b, recent_len, qsny, 0, 3);
		recent_len = recent_len + 3;
		message.setQsny(qsny);
		//终止年月
		byte[] zzny = new byte[3];
		System.arraycopy(b, recent_len, zzny, 0, 3);
		recent_len = recent_len + 3;
		message.setZzny(zzny);
		//税票号
		byte[] sph = new byte[10];
		System.arraycopy(b, recent_len, sph, 0, 10);
		recent_len = recent_len + 10;
		message.setSph(sph);
		//开票日期
		byte[] kprq = new byte[4];
		System.arraycopy(b, recent_len, kprq, 0, 4);
		recent_len = recent_len + 4;
		message.setKprq(kprq);
		//税款合计
		byte[] skhj = new byte[message.getSkhj().length];
		System.arraycopy(b, recent_len, skhj, 0, message.getSkhj().length);
		recent_len = recent_len + message.getSkhj().length;
		message.setSkhj(skhj);
		//缴费月份
		byte[] jfyf = new byte[2];
		System.arraycopy(b, recent_len, jfyf, 0, 2);
		recent_len = recent_len + 2;
		message.setJfyf(jfyf);
		//校验码
		byte[] jym = new byte[8];
		System.arraycopy(b, recent_len, jym, 0, 8);
		recent_len = recent_len + 8;
		message.setJym(jym);
		//自助机号
		byte[] zzj = new byte[4];
		System.arraycopy(b, recent_len, zzj, 0, 4);		
		message.setZzj(zzj);
		return message;
	} 
	
	public byte[] getSph() {
		return sph;
	}

	public void setSph(byte[] sph) {
		if (sph.length <= 10) {
		 System.arraycopy(sph, 0, this.sph, (this.sph.length-sph.length), sph.length);}
		else {
			System.arraycopy(sph, sph.length-10, this.sph, 0, 10);
		} 
			
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