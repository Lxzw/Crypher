package com.l.mk.obj;

import java.util.ArrayList;

/**
 * 
 * @author L
 *
 */
public class MessageContent {
	
	private byte[] sph = new byte[10];//税票号
	private ArrayList<Byte> name = new ArrayList<Byte>();//姓名 
	private byte[] jshjs = new byte[6];//缴税合计数
	private byte[] qsny = new byte[3];//起始年月
	private byte[] kprq = new byte[4];//开票日期
	private byte[] zzny = new byte[3]; //终止年月
	private ArrayList<Byte> zjhm = new ArrayList<Byte>();//证件号码
	private byte[] jfyfbj = new byte[2]; //缴费月份标记
	private byte[] zzjbh = new byte[4]; //自助机编号
	private byte[] xym = new byte[8];//校验码

	public byte[] getSph() {
		return sph;
	}
	public void setSph(byte[] sph) {
		this.sph = sph;
	}
	public ArrayList<Byte> getName() {
		return name;
	}
	public void setName(ArrayList<Byte> name) {
		this.name = name;
	}
	public byte[] getJshjs() {
		return jshjs;
	}
	public void setJshjs(byte[] jshjs) {
		this.jshjs = jshjs;
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
	public ArrayList<Byte> getZjhm() {
		return zjhm;
	}
	public void setZjhm(ArrayList<Byte> zjhm) {
		this.zjhm = zjhm;
	}
	public byte[] getJfyfbj() {
		return jfyfbj;
	}
	public void setJfyfbj(byte[] jfyfbj) {
		this.jfyfbj = jfyfbj;
	}
	public byte[] getZzjbh() {
		return zzjbh;
	}
	public void setZzjbh(byte[] zzjbh) {
		this.zzjbh = zzjbh;
	}
	public byte[] getXym() {
		return xym;
	}
	public void setXym(byte[] xym) {
		this.xym = xym;
	}
}
