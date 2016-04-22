<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.caucho.hessian.client.HessianProxyFactory" %>
<%@ page import="com.wwfly.service.result.Log" %>
<%@ page import="com.wwfly.service.result.TravelRoutineData" %>
<%@ page import="com.wwfly.service.result.UserData" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wwfly.service.in.IInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	 <header class="banner">
		<a href = "http://localhost:8080">
		 	<img src= "/pictures/header.jpg" height="100" width="800"></img>
		 </a>
	</header>
	<section>
	<article>
	<h2>用户基本信息：</h2>
	<%
			String url = "http://" + request.getLocalName() + ":" + request.getLocalPort();
	        String TravelUrl = "/client/info";
			HessianProxyFactory shpf = new HessianProxyFactory();
			IInfo basicTravelSer = (IInfo) shpf.create(IInfo.class, url + TravelUrl);
			//email is unique
			UserData data =  basicTravelSer.getInfoBaseOnEmail((String)session.getAttribute("username")).first.get(0);
			out.println("用户名： " + data.getName()); 
			%><br><% 
			out.println("性别： " + data.getSex());
			%><br><% 
			out.println("年龄： " + data.getAge());
			%><br><% 
			out.println("用户电话： " + data.getUserPhone());
			%><br><% 
			if( data.getSchool() != null) { 
				out.println("学校： " + data.getSchool());
			}
			%><div align = "right"><a href = "/02/editUserInfo.jsp">修改用户信息</a></div><% 
	 %>
	 </article>
	 <article>
	 <h2>用户注册：</h2>
	 <br>
	 <%
	 	List<TravelRoutineData> trDataList = basicTravelSer.getTravelRoutineBasedOnEmailName((String)session.getAttribute("username")).first;
	 	if(trDataList.size() == 0) {
	 		out.println("您还没有注册任何活动");
	 	} else {
	 		int counter = 1;
		 	for(TravelRoutineData trData : trDataList) {
		 		out.println(counter + ". " + trData.getName());
			 	out.print("用户注册活动 :" + trData.getName() + " 出发日期： " + trData.getStartDate() + " 返程日期： " + trData.getEndDate()
			 	+ " 出发地点： " + trData.getStartingPlace() + " 返程地点： " + trData.getDestination());
			 	String string = trData.getName();
			 	%><br><div align = "right"><a href = <%="/02/editTravelRoutine.jsp?trName=" + string%>>取消注册</a></div><%
			 	counter += 1; 
		 	}
		}
	  %>
	 </article>
	</section>
	
</body>
</html>