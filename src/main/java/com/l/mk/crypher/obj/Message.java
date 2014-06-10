package com.l.mk.crypher.obj;

public class Message {
	private byte[] sph = new byte[10];//税票号
	private byte   length; //姓名长度
	private byte[] jshj = new byte[6]; //缴款合计
	private byte[] qsny = new byte[3]; //起始年月
	private byte[] kprq = new byte[4]; //开票日期
	private byte[] zzny = new byte[3]; //终止年月
	private byte   zjh_len;            //证件号长度
	private byte[] jfyf = new byte[2]; //缴费月份
	private byte[] zzj  = new byte[4]; //自助机编号
	private byte[] jym  = new byte[8]; //校验码
	
	/**
	 * 获得校验码
	 * 将数据转换为字符串（每四位当成一个ASCII码），然后进行MD5，取最后8个字节
	 * @return
	 */
	private byte[] getJym() {
		return null;
	}
	
	
}