package com.l.mk.crypher.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.l.mk.crypher.cryption.DESedeEncryptionUtil;
import com.l.mk.crypher.format.ByteUtil;
import com.l.mk.crypher.format.MessageDataTransfer;
import com.l.mk.crypher.obj.Message;
import com.l.mk.crypher.obj.MessageHeader;

/**
 * Servlet implementation class DecryptionServlet
 */
public class DecryptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecryptionServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//数据对象map
		Map<String,String> map = new HashMap<String,String>();
		
		//解析密文头
		String cipherString = request.getParameter("cipher");
		byte[] cipher       = ByteUtil.compress(cipherString);
		byte[] header_cipher = new byte[24];
		System.arraycopy(cipher, 0, header_cipher, 0, 24);
		byte[] key = null, header = null;
		try {
			key = DESedeEncryptionUtil.initKey("12345678".getBytes(),
					"abcdefgh".getBytes(), 
					"!@#$%^&*".getBytes());
			header = DESedeEncryptionUtil.decrypt(header_cipher, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageHeader messageHeader = MessageHeader.getMessageHeader(header);
		Map<String, String> map2 = MessageDataTransfer.messageHeaderToMap(messageHeader);
		
		//解析密文
		byte[] content_cipher = new byte[64], content =null;
		System.arraycopy(cipher, 24, content_cipher, 0, cipher.length - 24);
		
		try {
			key = DESedeEncryptionUtil.initKey(map2.get("ZZJ").getBytes(), 
					map2.get("SPH").getBytes(), 
					map2.get("KPRQ").getBytes());
			content = DESedeEncryptionUtil.decrypt(content_cipher, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message message = Message.getMessage(content);
		Map<String, String> map3 = MessageDataTransfer.messageToMap(message);
		
		
		request.setAttribute("plaintext", map3);
		this.getServletConfig()
			.getServletContext()
			.getRequestDispatcher("/showInfo.jsp")
			.forward(request, response);
	}
}
