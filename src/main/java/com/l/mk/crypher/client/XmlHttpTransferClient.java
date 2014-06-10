package com.l.mk.crypher.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.w3c.dom.*;


/**
 * 2006-04-04
 * 
 * @author weiwei
 */
public class XmlHttpTransferClient {

	protected URL url;

	private URLConnection conn;

	/**
	 * POST方法是向服务器传送数据，以便服务器做出相应的处理。例如网页上常用的 提交表格。
	 */
	public void POST(Document content) {
		sendMessage(content);
	}

	/**
	 * 把xml文件写入到输出流中，从而实现想nc服务器传送数据
	 * 
	 * @param content
	 */
	private void sendMessage(Document content) {
		try {
			OutputStream raw = conn.getOutputStream();
			OutputStream buf = new BufferedOutputStream(raw);
			OutputStreamWriter out = new OutputStreamWriter(buf);
			/*
			 * SAXReader saxReader = new SAXReader(); Document documentFROMD =
			 * saxReader.read("d://try.xml");
			 * documentFROMD.setXMLEncoding("gb2312");
			 */
			// out.write(new String(content));
			out.write(content.asXML());
			out.flush();
			out.close();
			raw.close();
		} catch (IOException e) {
			new IOException("传送失败");
		}
	}

	/**
	 * 从输入流中读取回执文件
	 * 
	 * @return
	 */
	public Document getReceiverMessage() {
		try {

			InputStream raw = conn.getInputStream();
			InputStream in = new BufferedInputStream(raw);
			Reader reader = new InputStreamReader(in);
			BufferedReader bufreader = new BufferedReader(reader);

			String xmlString = "";

			// 输出在后台
			int c;
			System.out.println("==================Beging====================");
			while ((c = bufreader.read()) != -1) {
				System.out.print((char) c);
				xmlString += (char) c;
			}
			in.close();
			System.out.println("===================End======================");
			// 把xml字符串转换成xml文件
			Document document = DocumentHelper.parseText(xmlString);
			return document;
		} catch (DocumentException e) {
			new DocumentException("回执文件格式不合法");
		} catch (IOException e) {
			new IOException("无法取得回执文件");
		}
		return null;
	}

	/**
	 * 建立连接，并设置输出准备为true
	 * 
	 * @param urlString
	 */
	public void openServer(String urlString) {
		try {
			checkHTTP(urlString);
			conn = url.openConnection();
			conn.setDoOutput(true);
		} catch (IOException e) {
			new IOException("连接服务器失败");
		}
	}

	/**
	 * 存储文件
	 * 
	 * @param bytes
	 */
//	public void saveFile(Document doc) {
//		Element root = doc.getRootElement();
//
//		// 生成的文件名可以自己命名，这里是取某个标签里的值
//
//		String filename = "d://生成的XML文件//" + "[REC]"
//				+ root.attributeValue("filename");
//		try {
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			format.setEncoding("gb2312");
//			XMLWriter writer = new XMLWriter(
//					new FileWriter(new File(filename)), format);
//
//			writer.write(doc);
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	/**
	 * 检查URL合法性
	 * 
	 * @param urlString
	 * @throws ProtocolException
	 */
	protected void checkHTTP(String urlString) throws ProtocolException {
		try {
			URL url = new URL(urlString);
			if (url == null || !url.getProtocol().toUpperCase().equals("HTTP"))
				throw new ProtocolException("这不是HTTP协议");
			this.url = url;
		} catch (MalformedURLException m) {
			throw new ProtocolException("协议格式错误");
		}
	}
}
