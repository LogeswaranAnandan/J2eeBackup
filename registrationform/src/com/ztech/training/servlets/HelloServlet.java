package com.ztech.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.training.beanpackage.BeanClass;
import com.ztech.training.dao.DatabaseDao;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		BeanClass bean = new BeanClass();
		DatabaseDao databaseDao = new DatabaseDao();
		PrintWriter out = response.getWriter();
		int rowsAffected = 0;
		int rollNumber = Integer.parseInt(request.getParameter("roll-number"));
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String branch = request.getParameter("branch");
		String section = request.getParameter("section");
		long phoneNumber = Long.parseLong(request.getParameter("phone-number"));
		bean.setRollNumber(rollNumber);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setBranch(branch);
		bean.setSection(section);
		bean.setPhoneNumber(phoneNumber);
		rowsAffected = databaseDao.insertData(bean);
		if (rowsAffected > 0) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.html");
			requestDispatcher.forward(request, response);
		} else {
			out.println("Please enter valid data and try again");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
