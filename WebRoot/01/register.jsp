<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
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
	<h1><font size = "10" color="white">我要注册</font></h1>
	<form action = "<%= request.getContextPath() %>/registerServlet" method = "post">
		<p align="center">
			<font size = "5" color="white">用户Email:</font> 
			<input type="text" name="uname" /> <br> 
			<font size = "5" color="white">密码: </font> 
			<input type="password" name="password" id = "p1" maxLength = 15 onchange="p1s();"/> <br> 
			<font size = "5" color="white">密码确认: </font> 
			<input type="password" name="password2" id = "p2" maxLength = 15 disabled="disabled" /> <br> 
			<script type="text/javascript">
			    function sub(){
			        var p1 = document.getElementById("p1");
			        var p2 = document.getElementById("p2");
			        if(p1!=p2){
			            alert("两次密码不一样");
			            return false;
			        }
			        return true;
			    }
			    function p1s(){
			        var p2 = document.getElementById("p2");
			        p2.disabled=false;
			    }
			</script>
			<font size = "5" color="white">姓名:</font> 
			<input type="text" name="name" /> <br> 
			<font size = "5" color="white">性别: </font> 
			<input type="text" name="sex" /> <br> 
			<font size = "5" color="white">年龄:</font> 
			<input type="text" name="age" /> <br> 
			<font size = "5" color="white">联系电话: </font> 
			<input type="text" name="phone" /> <br><br>
			<input type="submit" value="注册"> 
			<input type="reset" value="重新填写"> <br>
		</p>
	</form>
	</section>
	
</body>
</html>