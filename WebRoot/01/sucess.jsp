<%@ page language="java" contentType="text/html; charset= UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>登陆成功：</h2>
<h2>用户名：<%= request.getParameter("uname") %><br>密码: <%= request.getParameter("password") %></h2>
<% 
	session.setAttribute("login", "true");
	session.setAttribute("username", request.getParameter("uname"));
	response.setHeader("refresh", "URL = main.jsp");
 %>
<a href = "/main.jsp">返回主页面：</a>
</body>
</html>