package com.wwfly.service.in;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.db.tables.Login;
import com.wwfly.service.param.LoginParam;
import com.wwfly.service.param.RegisterParam;
import com.wwfly.service.result.Log;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2115184769242250790L;

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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
		doPost(request, response);
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
		String registerUrl = "/client/register";
		HessianProxyFactory shpf = new HessianProxyFactory();
		IRegister basicLogSer = (IRegister) shpf.create(IRegister.class, url + registerUrl);
		Integer age = null;
		String sex = null;
		String userEmail = request.getParameter("uname");
		String password = request.getParameter("password");
		String actualName = request.getParameter("name");
		if(request.getParameter("age") != null && request.getParameter("age").isEmpty() == true) {
			age = Integer.getInteger(request.getParameter("age"));
		}
		sex = request.getParameter("sex");
		if(sex != null && sex.length() > 0) {
			sex = request.getParameter("sex").substring(0, 1);
		}
		String userPhone = request.getParameter("phone");
		if(age == null || sex == null|| userEmail == null || password == null || actualName == null || userPhone == null
			|| age.equals("") || sex.equals("")|| userEmail.equals("") || password.equals("") || actualName.equals("") || userPhone.equals("")) {  
			response.sendRedirect(request.getContextPath() + "/01/error.jsp?errorMsg=blank");
		}
		Log log1 = basicLogSer.addAccount(new LoginParam(userEmail, password));
		Log log2 = basicLogSer.register(new RegisterParam(actualName, age, sex, userEmail, userPhone));
		if(log1.hasError() || log2.hasError()) {
			String forward = "/01/error.jsp?errorMsg=log";
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		} else {
			String forward = "/01/sucess.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
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
