package com.l.mk.obj;

import java.util.ArrayList;

/**
 * 
 * @author L
 *
 */
public class MessageContent {
	
	private byte[] sph = new byte[10];//˰Ʊ��
	private ArrayList<Byte> name = new ArrayList<Byte>();//���� 
	private byte[] jshjs = new byte[6];//��˰�ϼ���
	private byte[] qsny = new byte[3];//��ʼ����
	private byte[] kprq = new byte[4];//��Ʊ����
	private byte[] zzny = new byte[3]; //��ֹ����
	private ArrayList<Byte> zjhm = new ArrayList<Byte>();//֤������
	private byte[] jfyfbj = new byte[2]; //�ɷ��·ݱ��
	private byte[] zzjbh = new byte[4]; //���������
	private byte[] xym = new byte[8];//У����

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
