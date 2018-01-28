package com.bikeapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.delegate.AdminDelegate;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegate delegate = new AdminDelegate();
		String functionality = request.getParameter("admin-functionality");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if (functionality == null) {
			response.sendRedirect("index.jsp");
		} else if (functionality.equals("View All Bikes")) {
			List<BikeBeanClass> bikeBeanList = delegate.viewAllBikes();
			request.setAttribute("bikeBeanList", bikeBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminviewallbikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View All Rented Bikes")) {
			List<RentBeanClass> rentBeanList = delegate.viewAllRentedBikes();
			request.setAttribute("rentBeanList", rentBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminviewrentedbikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View Customer Details")) {
			List<UserBeanClass> userBeanList = delegate.viewAllUsers();
			request.setAttribute("userBeanList", userBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminviewallusers.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Add New Bike")) {
			response.sendRedirect("adminaddnewbike.jsp");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
