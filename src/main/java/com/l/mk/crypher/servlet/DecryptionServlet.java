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
		//获取密文
		String cipher = request.getParameter("cipher");
		byte[] cipher_data = ByteUtil.compress(cipher);
		//明文数据
		byte[] data = null;
		try {
			//密钥
			byte[] key = DESedeEncryptionUtil.initKey("hello", "1322", "sdfs"); 
			data = DESedeEncryptionUtil.decrypt(cipher_data, key);
			for (int i=0; i < data.length; i++) {
				System.out.printf("%x", data[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new String(data));
		System.out.println(new String(data,"UTF-8"));
		map.put("xm",new String(data,"UTF-8") );
		request.setAttribute("plaintext", map);
		this.getServletConfig()
			.getServletContext()
			.getRequestDispatcher("/showInfo.jsp")
			.forward(request, response);
	}
}
