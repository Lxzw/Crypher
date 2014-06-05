package com.l.mk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.l.mk.obj.Message;
import com.l.mk.obj.ZipMessage;
import com.l.mk.utils.ByteUtil;
import com.l.mk.utils.DESedeEncryptionUtil;
import com.l.mk.utils.Parser;
import com.l.mk.utils.SelfKeyGenerator;

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
		// 解析成固定形式的报文
		Message message = Parser.parse(request.getParameter("plaintext"));
		// 压缩报文
		ZipMessage zipMessage = ZipMessage.generate(message);
		// 根据报文头生成密钥
		byte[] key = SelfKeyGenerator.generate(zipMessage.getZipMessageHeader());
		byte[] header = null,content = null;
		// 用DES3算法+key加密压缩的报文内容
	    try {
			content = DESedeEncryptionUtil.encrypt(zipMessage.getZipMessageContent(), key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 报文头用HKEy进行DES3加密
		byte[] hkey = {};
		try {
			header = DESedeEncryptionUtil.encrypt(zipMessage.getZipMessageHeader(), hkey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 密文合并
		byte[] cipher = ByteUtil.merge(header,content);
		// 密文扩展
		String s = ByteUtil.extend(cipher);
	
		//将密文发送给要显示的jsp
		request.setAttribute("cipher", "xxx");
		this.getServletConfig()
			.getServletContext()
			.getRequestDispatcher("/showCipher.jsp")
			.forward(request, response);
		
	}

}
