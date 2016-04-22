<%@ page language="java" contentType="text/html; charset= UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
登陆失败：<br>
<% String errorMsg = (String)request.getParameter("errorMsg"); 
	if(errorMsg.equals("blank")) {
		out.println("不能有空白");
	} else if(errorMsg.equals("log")) {
		out.println("道歉，数据库出了问题，请稍后重试。");
	}%>
<a href = "/main.jsp">返回主页面：</a>
</body>
</html>