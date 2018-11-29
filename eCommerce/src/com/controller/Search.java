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

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;
import com.dao.Item;
import com.model.CassandraSession;
import com.model.RedisSession;
import com.model.SolrSearch;

public class Search extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger log = Logger.getLogger(Search.class);
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Log4JInitServlet is initializing log4j");
		String log4jLocation = config.getInitParameter("log4j-properties-location");

		ServletContext sc = config.getServletContext();

		if (log4jLocation == null) {
			System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		} else {
			String webAppPath = sc.getRealPath("/");
			String log4jProp = webAppPath + log4jLocation;
			File file = new File(log4jProp);
			if (file.exists()) {
				System.out.println("Initializing log4j with: " + log4jProp);
				PropertyConfigurator.configure(log4jProp);
			} else {
				System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
		super.init(config);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			log.info("Entering: " + this.getClass().getName() + "."
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

			String item_id = request.getParameter("itemid");
			String keyword = request.getParameter("keyword");

			log.info("keyword from JSP as reuest param:" + keyword);
			log.info("Item ID from JSP as reuest param:" + item_id);
			
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "sagarbigdataproject", "api_key",
					"733572674397238", "api_secret", "U0piOLEWFtrXz9TqJnqZdR8nqQc"));
			
			SingletonManager manager = new SingletonManager();
			manager.setCloudinary(cloudinary);
			manager.init();
			
			if (item_id == "" && keyword == "")
			{
				log.info("********Item ID and Keyword are null. Redirecting to error page****");
				RequestDispatcher view = request.getRequestDispatcher("errorview.jsp");
				view.forward(request, response);
			}
			if (item_id != "") {
				
				RedisSession redisSession = new RedisSession();
				List<String> itemList = new ArrayList<String>();
				itemList.add(item_id);
				List<Item> items = redisSession.cachedItems(itemList);
				request.setAttribute("products2", items);

			} 
			else if (keyword != "") 
			{
				SolrSearch solrSearch = new SolrSearch();
				List<Item> solrItems = solrSearch.solrSearchData(keyword);
				request.setAttribute("products2", solrItems);

			}

			RequestDispatcher view = request.getRequestDispatcher("searchview.jsp");
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
