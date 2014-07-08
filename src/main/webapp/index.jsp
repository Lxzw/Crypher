<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" href="css/style.css" rel="stylesheet" />
</head>
<body>
	<h1>Hello World!</h1>
	
	<form action="/Crypher/EncryptionServlet" method="get" enctype="application/x-www-form-urlencoded">

	<p> xml文件： <textarea rows="9" cols="80" name="xml" ><?xml version="1.0" encoding="utf-8"?>
			<ROOT>
			<XM>周星星</XM>
			<ZJH>330283198910102323</ZJH>
			<QSNY>201309</QSNY>
			<ZZNY>201311</ZZNY>
			<SPH>11111222223333344444</SPH>
			<KPRQ>20131101</KPRQ>
			<SKHJ>1700.00</SKHJ>
			<JFYF>9,10</JFYF>
			<ZZJ>00000001</ZZJ>
			</ROOT>
		 </textarea> </p>
	<p>	<input type="submit" value="Get the Crypher"/> </p>
	</form>
	
</body>
</html>