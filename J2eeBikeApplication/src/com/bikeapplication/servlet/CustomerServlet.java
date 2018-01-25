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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		if (functionality == null) {
			response.sendRedirect("index.jsp");
		} else if (functionality.equals("View All Bikes")) {
			List<BikeBeanClass> bikeBeanList = delegate.viewAllBikes();
			request.setAttribute("bikeBeanList", bikeBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllBikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View & Rent Available Bikes") || functionality.equals("Rent a Bike")) {
			List<BikeBeanClass> bikeBeanList = delegate.viewAvailableBikes();
			request.setAttribute("bikeBeanList", bikeBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewavailablebikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Rent this Bike")) {
			response.setContentType("text/html");
			boolean isSuccessful = false;
			RentBeanClass rentBean = new RentBeanClass();
			int bikeId = Integer.parseInt(request.getParameter("bike-id"));
			int rentDuration = Integer.parseInt(request.getParameter("rent-duration"));
			int advancePaid = Integer.parseInt(request.getParameter("advance-paid"));
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			rentBean.setUserId(userId);
			rentBean.setBikeId(bikeId);
			rentBean.setDuration(rentDuration);
			rentBean.setAdvancePaid(advancePaid);
			isSuccessful = delegate.rentBike(rentBean);
			if (isSuccessful == true) {
				out.println("<script>alert('Bike is rented successfully')</script>");
			} else {
				out.println(
						"<script>alert('Some problem occurred while renting the bike. Please try again..!')</script>");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("customer.jsp");
			dispatcher.include(request, response);
		} else if (functionality.equals("Return the Bike")) {
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			List<BikeBeanClass> bikeBeanList = delegate.viewUserRentedBikes(userId);
			request.setAttribute("bikeBeanList", bikeBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewrentedbikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Return this Bike")) {
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			int bikeId = Integer.parseInt(request.getParameter("bike-id"));
			String registrationNumber = request.getParameter("registration-number");
			RentCalculatorBeanClass rentCalculatorBean = delegate.returnBike(userId, bikeId, registrationNumber);
			request.setAttribute("rentCalculatorBean", rentCalculatorBean);
			request.setAttribute("status", "Return this Bike");
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewrentamount.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View Remaining Duration of Rent")) {
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			List<BikeBeanClass> bikeBeanList = delegate.viewUserRentedBikes(userId);
			request.setAttribute("bikeBeanList", bikeBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewremainingduration.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View Remaining Duration")) {
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			int bikeId = Integer.parseInt(request.getParameter("bike-id"));
			String registrationNumber = request.getParameter("registration-number");
			RentCalculatorBeanClass rentCalculatorBean = delegate.viewRemainingDuration(userId, bikeId, registrationNumber);
			request.setAttribute("rentCalculatorBean", rentCalculatorBean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewrentamount.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
