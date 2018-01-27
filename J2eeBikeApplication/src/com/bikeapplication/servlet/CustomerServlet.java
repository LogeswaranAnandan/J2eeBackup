package com.bikeapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;
import com.bikeapplication.delegate.CustomerDelegate;

@WebServlet({ "/CustomerServlet", "/CustomerRequestServlet" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CustomerServlet.class.getName());

	public CustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDelegate delegate = new CustomerDelegate();
		String functionality = request.getParameter("customer-functionality");

		if (functionality == null) {
			response.sendRedirect("index.jsp");
		} else if (functionality.equals("View All Bikes") || functionality.equals("Rent a Bike")) {
			List<BikeBeanClass> bikeBeanList = delegate.viewAllBikes();
			request.setAttribute("bikeBeanList", bikeBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllBikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Rent this Bike")) {
			delegate.rentBike(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("customer.jsp");
			dispatcher.include(request, response);
		} else if (functionality.equals("Return the Bike")) {
			delegate.viewUserRentedBikes(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewrentedbikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Return this Bike")) {
			delegate.returnBike(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewrentamount.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View Rent History")) {
			delegate.viewRentHistory(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewrenthistory.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
