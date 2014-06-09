package com.l.mk.crypher.servlet;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class EncryptionServletTest {

	@Test
	public void test() throws ServletException, IOException {
		//模拟创建request/response对象   
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpServletResponse response = createMock(HttpServletResponse.class);
		//模拟创建ServletConfig和ServletContext
		ServletConfig servletConfig = createMock(ServletConfig.class);
		ServletContext servletContext = createMock(ServletContext.class);
		
		//创建EncryptionServlet实例
		EncryptionServlet es = new EncryptionServlet();
		
		//由servletConfig作为参数，初始化；模拟容器行为
		es.init(servletConfig);
	    
		expect(servletConfig.getServletContext()).andReturn(servletContext).anyTimes();
		expect(request.getParameter("xm")).andReturn("周星星");

        PrintWriter pw=new PrintWriter(System.out,true);
        expect(response.getWriter()).andReturn(pw).anyTimes();
		
		//以上均是录制，下面为重放
        replay(request);
        replay(response);
        replay(servletConfig);
        replay(servletContext);
        
        es.doGet(request, response);
        pw.flush();
        
        //验证结果是否预期
        verify(request);
        verify(response);
        verify(servletConfig);
        verify(servletContext);
	}

}
