<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" href="css/style.css" rel="stylesheet" />
</head>
<body>
	<h1>Hello World!</h1>
	
	<form action="/Crypher/EncryptionServlet" method="post" enctype="application/x-www-form-urlencoded">
		
	<p>	姓名：<input type="text" name="xm" value="周星星" />      </p>
	<p>	证件号：<input type="text" name="zjh" value="1330283198911222723" />       </p>
	<p>	起始年月：<input type="text" name="qsny" value="201309" />     </p>
	<p>	终止年月：<input type="text" name="zzny" value="201312" />     </p>
	<p>	税票号：<input type="text" name="sph" value="11111222223333344444" />       </p>
	<p>	开票日期：<input type="text" name="kprq" value="20131209" />     </p>
	<p>	税款合计：<input type="text" name="skhj" value="2100.00" />     </p>
	<p>	缴费月份：<input type="text" name="jfyf" value="10,11,12" />     </p>
	<p>	自助机编号：<input type="text" name="zzj" value="12345678" />     </p>
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
    <p> <input type="file" name="file" /> </p>
	<p>	<input type="submit" value="Get the Crypher"/> </p>
	</form>
	
</body>
</html>