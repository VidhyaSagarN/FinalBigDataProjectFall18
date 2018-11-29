package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.dao.Item;
import com.model.CassandraSession;
import com.model.RedisSession;

public class Filter extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	final static Logger log = Logger.getLogger(Filter.class);

	public void init(ServletConfig config) throws ServletException {

		System.out.println("Log4JInitServlet is initializing log4j");
		String log4jLocation = config.getInitParameter("log4j-properties-location");

		ServletContext sc = config.getServletContext();

		if (log4jLocation == null) {
			System.err.println(
					"*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		} else {
			String webAppPath = sc.getRealPath("/");
			String log4jProp = webAppPath + log4jLocation;
			File file = new File(log4jProp);
			if (file.exists()) {
				System.out.println("Initializing log4j with: " + log4jProp);
				PropertyConfigurator.configure(log4jProp);
			} else {
				System.err
						.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			log.info("Entering: " + this.getClass().getName() + "."
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

			String item_id = request.getParameter("itemid");
			System.out.println("Item Id in request scope: " + item_id);
			RedisSession rs = new RedisSession();
			List<String> itemList = new ArrayList<String>();
			itemList.add(item_id);
			List<Item> items = rs.cachedItems(itemList);

			request.setAttribute("product", items);

			RequestDispatcher view = request.getRequestDispatcher("product.jsp");
			view.forward(request, response);
			
			log.info("Exiting: " + this.getClass().getName() + "."
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
}
