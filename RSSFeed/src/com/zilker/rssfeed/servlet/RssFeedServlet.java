package com.zilker.rssfeed.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.zilker.rssfeed.bean.RssFeedBean;
import com.zilker.rssfeed.java.RssFeedData;

/**
 * Servlet implementation class RssFeedServlet
 */
@WebServlet("/RssFeedServlet")
public class RssFeedServlet extends HttpServlet {
	RssFeedData rssFeedData = new RssFeedData();
	Logger logger = Logger.getLogger(RssFeedServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RssFeedServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String URL = "";
		String topic = request.getParameter("topic");
		logger.info("topic is : " + topic);
		if((topic == null) || topic.equals("Top Stories")) {
			URL = "https://news.google.com/news/rss/?ned=in&gl=IN&hl=en-IN";
			topic = "Today's Headlines";
		} else if(topic.equals("India")) {
			URL = "https://news.google.com/news/rss/headlines/section/topic/NATION.en_in/India?ned=in&hl=en-IN&gl=IN";
		} else {
			URL = "https://news.google.com/news/rss/headlines/section/topic/" + topic.toUpperCase() +".en_in/" + topic + "?ned=in&hl=en-IN&gl=IN";
		}
		
//URL = "https://news.google.com/news/rss/headlines/section/topic/" + topic.toUpperCase() +".en_in/" + topic + "?ned=in&hl=en-IN&gl=IN";
		JSONArray jsonArray = rssFeedData.fetchRssFeedData(URL);
		request.setAttribute("jsonArray", jsonArray);
		topic += " news";
		request.setAttribute("topic", topic );
		RequestDispatcher rd = request.getRequestDispatcher("display.jsp");
		rd.forward(request, response);

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
