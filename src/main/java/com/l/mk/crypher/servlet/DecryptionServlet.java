package com.l.mk.crypher.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.sun.org.apache.bcel.internal.generic.NEW;

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		byte[] content_cipher = new byte[cipher.length - header_cipher.length], content =null;
		System.arraycopy(cipher, 24, content_cipher, 0, cipher.length - header_cipher.length);
		
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
		
		String param = toParse(map3);
		
		response.setContentType("text/plain;charset=utf-8");
		response.setCharacterEncoding("GBK");
		PrintWriter outPrintWriter = response.getWriter();
		
		outPrintWriter.println(param);
		
//		request.setAttribute("plaintext", map3);
//		this.getServletConfig()
//			.getServletContext()
//			.getRequestDispatcher("/showInfo.jsp")
//			.forward(request, response);
	}

	private String toParse(Map<String, String> map3)  {
		StringBuilder sBuilder = new StringBuilder("<ROOT>");
		if (map3.containsKey("RES")) {
			sBuilder = sBuilder.append("<RES>")
					.append(map3.get("RES")).append("</RES");
		}
		if (map3.containsKey("XM")) {
			sBuilder = sBuilder.append("<XM>")
					.append(map3.get("XM")).append("</XM>");
		}
		if (map3.containsKey("ZJH")) {
			sBuilder = sBuilder.append("<ZJH>")
					.append(map3.get("ZJH")).append("</ZJH>");
		}
		if (map3.containsKey("QSNY")) {
			sBuilder = sBuilder.append("<QSNY>")
					.append(map3.get("QSNY")).append("</QSNY>");
		}
		if (map3.containsKey("ZZNY")) {
			sBuilder = sBuilder.append("<ZZNY>")
					.append(map3.get("ZZNY")).append("</ZZNY>");
		}
		if (map3.containsKey("SPH")) {
			sBuilder = sBuilder.append("<SPH>")
					.append(map3.get("SPH")).append("</SPH>");
		}
		if (map3.containsKey("KPRQ")) {
			sBuilder = sBuilder.append("<KPRQ>")
					.append(map3.get("KPRQ")).append("</KPRQ>");
		}
		if (map3.containsKey("SKHJ")) {
			sBuilder = sBuilder.append("<SKHJ>")
					.append(map3.get("SKHJ")).append("</SKHJ>");
		}
		if (map3.containsKey("JFYF")) {
			sBuilder = sBuilder.append("<JFYF>")
					.append(map3.get("JFYF")).append("</JFYF>");
		}
		sBuilder.append("</ROOT>");
		
		return sBuilder.toString();
	}
}
