package com.wwfly.service.in;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.service.param.LoginParam;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8998814864835716910L;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "http://" + request.getLocalName() + ":" + request.getLocalPort();
		String loginUrl = "/client/login";
		HessianProxyFactory shpf = new HessianProxyFactory();
		ILogin basicLogSer = (ILogin) shpf.create(ILogin.class, url + loginUrl);
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		System.out.println("用户名： " + username);
		System.out.println("密码: " + password);
		
		LoginParam userLogin = new LoginParam(username, password);
		Pair<Log, Boolean> pair = basicLogSer.login(userLogin);
		System.out.println(pair.first);
		if(pair.second) {
//			//请求重定向
//			resp.sendRedirect(req.getContextPath() + "/01/sucess.jsp");
			//请求转发
			String forward = "/01/sucess.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		} else {
			//请求重定向
			response.sendRedirect(request.getContextPath() + "/01/error.jsp");
//			//请求转发
//			String forward = "/01/error.jsp";
//			RequestDispatcher rd = request.getRequestDispatcher(forward);
//			rd.forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
