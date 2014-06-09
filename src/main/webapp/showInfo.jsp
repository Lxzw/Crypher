<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>显示信息</title>
</head>
<body>
        <h3>这里来显示信息</h3>
        <h2>
        	<% Map<String,String> map = (HashMap)request.getAttribute("plaintext"); %>
        	<%=map.get("xm") %>
        </h2>
</body>
</html>