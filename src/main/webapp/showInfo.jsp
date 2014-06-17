<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>显示信息</title>
</head>
<body>
	<h3>这里来显示信息</h3>

	<%
		Map<String,String> map = (HashMap)request.getAttribute("plaintext");
	%>


	 <p>	  姓名：   <%=map.get("XM")%> </p>
	 <p>     证件号：   <%=map.get("ZJH")%> </p>
	 <p>     起始年月：   <%=map.get("QSNY")%> </p>
	 <p>     终止年月：   <%=map.get("ZZNY")%> </p>
	 <p>     税票号：   <%=map.get("SPH")%> </p>
	 <p>     开票日期：   <%=map.get("KPRQ")%> </p>
     <p>     税款合计：   <%=map.get("SKHJ")%> </p> 
     <p>     缴费月份：   <%=map.get("JFYF")%> </p>
     <p>     自助机编号：   <%=map.get("ZZJ")%> </p>
</body>
</html>