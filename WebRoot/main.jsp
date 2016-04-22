<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.caucho.hessian.client.HessianProxyFactory"%>
<%@ page import="com.wwfly.service.result.Log"%>
<%@ page import="com.wwfly.service.result.TravelRoutineData"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wwfly.service.in.ITravel"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//cdn.ckeditor.com/4.5.1/basic/ckeditor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<!--[if lt IE 9]>
    <script>
      document.createElement("article");
      document.createElement("aside");
      document.createElement("footer");
      document.createElement("header");
      document.createElement("main");
      document.createElement("nav");
      document.createElement("section");
    </script>
    <![endif]-->
<title>首页</title>

</head>
<body>
	<header class="banner"> <img src="pictures/header.jpg"
		height="100" width="800"></img> </header>

	<nav>
	<ul>
		<%
			if(session.getAttribute("login") == null) {
		%>
		<li><a href="01/login.jsp">登陆</a></li>
		<li><a href="01/register.jsp">注册</a></li>
		<%
			}
		%>


		<%
			if(session.getAttribute("login") != null) {
		%><li><a href="/02/userInfo.jsp">我的资料</a></li>
		<li><a href="/01/logout.jsp">登出</a></li>
		<%
			}
		%>
		<li><a href="about.html">联系我们</a></li>
	</ul>
	</nav>

	<main> <section>
	<h2 style="color: white">最新活动</h2>
	<article> <header>
	<h3>
		<%
			String url = "http://" + request.getLocalName() + ":" + request.getLocalPort();
				            	String TravelUrl = "/client/travel";
				            	String string = null;
				HessianProxyFactory shpf = new HessianProxyFactory();
				ITravel basicTravelSer = (ITravel) shpf.create(ITravel.class, url + TravelUrl);
				            	List<TravelRoutineData> trList = basicTravelSer.getMostRecentTravelRoutine().first;
				            	if(trList.size() > 0) { 
				            		out.println(trList.get(0).getName());
		%>
	</h3>
	<p>
		<%
			out.println("起点： " + trList.get(0).getStartingPlace() + " 终点：" + trList.get(0).getDestination());
		%>
	</p>
	</header> <%
 	out.println(trList.get(0)); 
             		string = trList.get(0).getName();
             		if(string.contains(" ")) {
             			char[] charArray = string.toCharArray();
             			charArray[string.indexOf(' ')] = '+';
             			string = String.valueOf(charArray);
             		}
             	}
 %> <br> <%
 	if(session.getAttribute("login") != null) {
 %>
		<style type="text/css">
		#ToggleTarget1 {
			display: none;
		}
		</style>
		
		<script type="text/javascript">
			function Toggle1() {
				var el = document.getElementById("ToggleTarget1");
				if (el.style.display == "block") {
					el.style.display = "none";
				} else {
					el.style.display = "block";
				}
			}
		</script>
		
		<a href="javascript:Toggle1();"><button>展开评论</button></a>
		
	
		<div id="ToggleTarget1"><textarea name="input text1"></textarea>
			<input type="submit" align="right" value="提交评论" /></div>
	<%
		}
	%>
		<div align= "right"><a href=<%="/02/registerActivity.jsp?trName=" + string%>><button>注册活动</button></a></div>
	</article>
	<article> 
	<header>
	<h3>
		<%
			if(trList.size() > 1) {
				              out.println(trList.get(1).getName());
		%>
	</h3>
	<p>
		<%
			out.println("起点： " + trList.get(1).getStartingPlace() + " 终点：" + trList.get(0).getDestination());
		%>
	</p>
	</header>
	<p>
		<%
			out.println(trList.get(1));
				        		string = trList.get(1).getName();
				       			if(string.contains(" ")) {
				    				char[] charArray = string.toCharArray();
				    				charArray[string.indexOf(' ')] = '+';
				    				string = String.valueOf(charArray);
				       			}
				           	}
%> <br> <%
 	if(session.getAttribute("login") != null) {
 %>
		<style type="text/css">
		#ToggleTarget2 {
			display: none;
		}
		</style>
		
		<script type="text/javascript">
			function Toggle2() {
				var el = document.getElementById("ToggleTarget2");
				if (el.style.display == "block") {
					el.style.display = "none";
				} else {
					el.style.display = "block";
				}
			}
		</script>
		
		<a href="javascript:Toggle2();"><button>展开评论</button></a>
		
	
		<div id="ToggleTarget2"><textarea name="input text2"></textarea>
			<input type="submit" align="right" value="提交评论" /></div>
	<%
		}
	%>
		<div align= "right"><a href=<%="/02/registerActivity.jsp?trName=" + string%>><button>注册活动</button></a></div>
	</article> <article> <header>
	<h3>
		<%
			if(trList.size() > 2) { 
				            out.println(trList.get(2).getName());
		%>
	</h3>
	<p>
		<%
			out.println("起点： " + trList.get(2).getStartingPlace() + " 终点：" + trList.get(0).getDestination());
		%>
	</p>
	</header>
	<p>
		<%
			out.println(trList.get(2)); 
				          		string = trList.get(2).getName();
				       			if(string.contains(" ")) {
				    				char[] charArray = string.toCharArray();
				    				charArray[string.indexOf(' ')] = '+';
				    				string = String.valueOf(charArray);
				       			}
				       		}
%> <br> <%
 	if(session.getAttribute("login") != null) {
 %>
		<style type="text/css">
		#ToggleTarget3 {
			display: none;
		}
		</style>
		
		<script type="text/javascript">
			function Toggle3() {
				var el = document.getElementById("ToggleTarget3");
				if (el.style.display == "block") {
					el.style.display = "none";
				} else {
					el.style.display = "block";
				}
			}
		</script>
		
		<a href="javascript:Toggle3();"><button>展开评论</button></a>
		
	
		<div id="ToggleTarget3"><textarea name="input text3"></textarea>
			<input type="submit" align="right" value="提交评论" /></div>
	<%
		}
	%>
		<div align= "right"><a href=<%="/02/registerActivity.jsp?trName=" + string%>><button>注册活动</button></a></div>
	</article> </section> <section>
	<h2 style="color: white">流行活动</h2>
	<article> <header>
	<h3>Snow storm is making travel difficult</h3>
	<p>(author, date)</p>
	</header>
	<p>This is the story text. This is the story text.</p>
	<p>This is the story text. This is the story text.</p>
	</article> <article> <header>
	<h3>Best ViewPoint</h3>
	</header>

	<li><img src='pictures/thumb_IMG_2533_1024.jpg' height="150"
		width="160"></li>
	<li><img src='pictures/02.jpg' height="150" width="160"></li>
	<li><img src='pictures/01.jpg' height="150" width="160"></li>
	</article> </section> </main>

	<aside>
	<h2>音乐分享</h2>
	<br>
	<audio controls> <source src="/audio/music1.mp3"
		type="audio/mpeg"></audio> </aside>

	<aside> <img src="pictures/naruto.jpg" height="400" width="275"></img>
	</aside>


	<footer>
	<p>帮助</p>
	<p>All Right reserve to Frank Zhao</p>
	</footer>

	<script>
		CKEDITOR.replace('input text1', {
			uiColor : '#14B8C4',
			toolbar : [
					[ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList',
							'-', 'Link', 'Unlink' ],
					[ 'FontSize', 'TextColor', 'BGColor' ] ],
			height : [ '100px' ]

		});
	</script>
		<script>
			CKEDITOR.replace('input text2', {
				uiColor : '#14B8C4',
				toolbar : [
						[ 'Bold', 'Italic', '-', 'NumberedList',
								'BulletedList', '-', 'Link', 'Unlink' ],
						[ 'FontSize', 'TextColor', 'BGColor' ] ],
				height : [ '100px' ]

			});
		</script>
		
	<script>
		CKEDITOR.replace('input text3', {
			uiColor : '#14B8C4',
			toolbar : [
					[ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList',
							'-', 'Link', 'Unlink' ],
					[ 'FontSize', 'TextColor', 'BGColor' ] ],
			height : [ '100px' ]

		});
	</script>
</body>
</html>