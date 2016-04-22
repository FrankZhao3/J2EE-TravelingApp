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
<title>取消活动</title>
</head>
<body>
	 <header class="banner">
		<a href = "http://localhost:8080">
		 	<img src= "/pictures/header.jpg" height="100" width="800"></img>
		 </a>
	</header>
	<section>
	<%
		String email = (String)session.getAttribute("username");
		String url = "http://" + request.getLocalName() + ":" + request.getLocalPort();
        String TravelUrl = "/client/travel";
		HessianProxyFactory shpf = new HessianProxyFactory();
		ITravel basicTravelSer = (ITravel) shpf.create(ITravel.class, url + TravelUrl);
		String routineName = (String)request.getParameter("trName");
		//decode
		routineName=new String(routineName.getBytes("iso-8859-1"),"UTF-8");
		Log msg = basicTravelSer.deleteATravelRoutine(routineName, email);
		if(msg.hasError()) {
			out.println("删除失败");
		} else {
			out.println("成功修改");
		}
		
	 %>
	<a href = "/main.jsp"><button>返回主页面</button></a>
	</section>

</body>
</html>