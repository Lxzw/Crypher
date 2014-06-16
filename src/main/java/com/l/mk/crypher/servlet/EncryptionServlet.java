package com.l.mk.crypher.servlet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ibm.icu.impl.ICUService.Key;
import com.l.mk.crypher.cryption.DESedeEncryptionUtil;
import com.l.mk.crypher.format.ByteUtil;
import com.l.mk.crypher.format.MessageDataTransfer;
import com.l.mk.crypher.obj.Message;
import com.l.mk.crypher.obj.MessageHeader;

/**
 * Servlet implementation class EncryptionServlet
 */
public class EncryptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EncryptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @功能  对请求的数据进行加密
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = xmlProcess(request);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Message message = MessageDataTransfer.getMessage(map);
		MessageHeader messageHeader = MessageDataTransfer.getMessageHeader(map);
		byte[] content = message.toBytes();
		byte[] header = messageHeader.toBytes();
		byte[] key = null, cipher = null;
		byte[] content_cipher = null;
		byte[] header_cipher = null;
		try {
			key = DESedeEncryptionUtil.initKey(map.get("ZZJ"),
					map.get("SPH"), map.get("KPRQ"));
			content_cipher = DESedeEncryptionUtil.encrypt(content, key);
			key = DESedeEncryptionUtil.initKey("12345678", "abcdefgh", "!@#$%^&*");
			header_cipher  = DESedeEncryptionUtil.encrypt(header, key);
			cipher = ByteUtil.merge(header_cipher, content_cipher);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 输出为字符串
		request.setAttribute("cipher", ByteUtil.extend(cipher));

		this.getServletConfig().getServletContext()
				.getRequestDispatcher("/showCipher.jsp")
				.forward(request, response);
	}

	private Map<String, String> xmlProcess(HttpServletRequest request) throws UnsupportedEncodingException, DocumentException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		String xml = new String(request.getParameter("xml").getBytes("iso-8859-1"),"utf-8");
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element> elements = root.elements();
		for (Element e : elements) {
			map.put(e.getName(), e.getText());
		}		
		return map;
	}

	private void uploadfile(HttpServletRequest request) {
		String contentType = request.getContentType();
		System.out.println(contentType);
		System.out.println(request.getParameter("file"));
		try {
			DataInputStream in = new DataInputStream(request.getInputStream());
			System.out.println(request.getContentLength());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
