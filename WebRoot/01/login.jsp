<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<title>登陆</title>
<body>
	 <header class="banner">
		<a href = "http://localhost:8080">
		 	<img src= "/pictures/header.jpg" height="100" width="800"></img>
		 </a>
	</header>
	
	<nav>
		<ul>
			<li id="googleLink"><a href="http://www.google.com">谷歌主页</a></li>
			<li id="baiduLink"><a href="http://www.baidu.com">百度主页</a></li>
		</ul>
	</nav>
		<section>
		<form action = "<%= request.getContextPath() %>/loginServlet" method = "post">
			<p align="center">
				<font size = "5" color="white">Email:</font> 
				<input type="text" name="uname" /> <br> 
				<font size = "5" color="white">密码: </font> 
				<input type="password" name="password" maxlength= 15/><br> 
				<input type="submit" value="Login"> 
				<input type="reset" value="Reset"> <br>
			</p>
		</form>
		<section>
</body>
</html>
