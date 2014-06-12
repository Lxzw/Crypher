package com.l.mk.crypher.servlet;

import java.io.DataInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.l.mk.crypher.cryption.DESedeEncryptionUtil;
import com.l.mk.crypher.format.ByteUtil;


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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		xmlProcess(request);
		uploadfile(request);
		//从表单中获得数据
		byte[] data  = request.getParameter("xm").getBytes("iso-8859-1");
		//加密
		byte[] cipher = null;
		try {
			byte[] key = DESedeEncryptionUtil.initKey("hello", "1322", "sdfs"); 
			cipher = DESedeEncryptionUtil.encrypt(data, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//输出为字符串
		request.setAttribute("cipher", ByteUtil.extend(cipher));
		
		this.getServletConfig()
			.getServletContext()
			.getRequestDispatcher("/showCipher.jsp")
			.forward(request, response);
	}

	private void xmlProcess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String xml =request.getParameter("xml");
		System.out.println(xml);
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
