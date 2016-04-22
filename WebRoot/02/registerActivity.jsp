<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.caucho.hessian.client.HessianProxyFactory" %>
<%@ page import="com.wwfly.service.result.Log" %>
<%@ page import="com.wwfly.service.result.TravelRoutineData" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wwfly.service.in.ITravel" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>注册活动</title>
</head>
<body>
	<header class="banner">
		<img src= "/pictures/header.jpg" height="100" width="800"></img>
	</header>
	<section>
	<%
		if(session.getAttribute("login") != null) {
			String email = (String)session.getAttribute("username");
			String url = "http://" + request.getLocalName() + ":" + request.getLocalPort();
           	String TravelUrl = "/client/travel";
			HessianProxyFactory shpf = new HessianProxyFactory();
			ITravel basicTravelSer = (ITravel) shpf.create(ITravel.class, url + TravelUrl);
			String routineName = (String)request.getParameter("trName");
			//decode
			routineName=new String(routineName.getBytes("iso-8859-1"),"UTF-8");
			//中午加空格应该有问题
			out.println("***" + routineName + "***");
			Log msg = basicTravelSer.RegisterATravelRoutine(email, routineName);
			if(msg.hasError() == false) {
				out.println("注册成功");
			} else {
				out.println(msg);
				out.println("由于种种原因注册失败");
			}   
			%><li><a href = "/main.jsp">返回主页面：</a></li><%
		} else {
			out.println("请先登录");
			%><li><a href="/01/login.jsp">登陆</a></li><%
		}
	 %>
	 </section>
</body>
</html>