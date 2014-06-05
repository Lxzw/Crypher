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
		// �����ɹ̶���ʽ�ı���
		Message message = Parser.parse(request.getParameter("plaintext"));
		// ѹ������
		ZipMessage zipMessage = ZipMessage.generate(message);
		// ���ݱ���ͷ������Կ
		byte[] key = SelfKeyGenerator.generate(zipMessage.getZipMessageHeader());
		byte[] header = null,content = null;
		// ��DES3�㷨+key����ѹ���ı�������
	    try {
			content = DESedeEncryptionUtil.encrypt(zipMessage.getZipMessageContent(), key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����ͷ��HKEy����DES3����
		byte[] hkey = {};
		try {
			header = DESedeEncryptionUtil.encrypt(zipMessage.getZipMessageHeader(), hkey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ���ĺϲ�
		byte[] cipher = ByteUtil.merge(header,content);
		// ������չ
		String s = ByteUtil.extend(cipher);
	
		//�����ķ��͸�Ҫ��ʾ��jsp
		request.setAttribute("cipher", "xxx");
		this.getServletConfig()
			.getServletContext()
			.getRequestDispatcher("/showCipher.jsp")
			.forward(request, response);
		
	}

}
