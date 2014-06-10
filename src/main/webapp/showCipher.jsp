<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!-- 加入产生二维码的js -->
<script type="text/javascript" src="js/jquery.qrcode.min.js">
jQuery('#qrcode').qrcode({
    text    : "http://jetienne.com"
}); 
</script>
<title>显示密文</title>
</head>
<body>
	<h3>跳过来了</h3>
	<h4><%=request.getAttribute("cipher")%></h4>
	<form action="/Crypher/DecryptionServlet" method="post">
		<input type="hidden" name="cipher"
			value=<%=request.getAttribute("cipher")%> /> <input type="submit"
			value="Get the Info" />
	</form>
	
	<div id="qrcode"></div>
	 
</body>
</html>