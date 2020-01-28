package com.isimtl.java2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	
	private final String NAME_COOKIE_NB_VISITS = "nbVisits";

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		Cookie[] cookies = request.getCookies();
		
		int nbVisits;
		nbVisits = 0;
		
		if (null != cookies)
		{
			for (int i=0; i<cookies.length && nbVisits==0; i++) 
			{
				String nameCookie = cookies[i].getName();				
				if (nameCookie.equals(NAME_COOKIE_NB_VISITS)) {
					try 
					{
						String cookieValue = cookies[i].getValue();		
						nbVisits = Integer.parseInt(cookieValue);
					}
					catch (NumberFormatException e) {}
			}
		}
	}
	
	
	nbVisits++;
	
	Cookie nbVisitsCookie = new Cookie(NAME_COOKIE_NB_VISITS, ""+nbVisits);
	//nbVisitsCookie.setMaxAge(365*24*60*60);
	nbVisitsCookie.setMaxAge(60*2);
	
	response.addCookie(nbVisitsCookie);
	response.setContentType("text/html");
	
	out.println("<html><head><title>Visits</title></head><body>");
	out.println("This is your visit number: " + nbVisits);
	out.println("</body></html>");
}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
