<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.invalidate();
	response.setHeader("refresh", "URL = /main.jsp");
 %>
 <section>
 <font color = white>成功登出</font><br>
 <a href = "/main.jsp" ><font color = white>返回主页面</font></a>
 </section>
</body>
</html>