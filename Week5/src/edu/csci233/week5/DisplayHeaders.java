package edu.csci233.week5;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayHeaders
 */
@WebServlet("/DisplayHeaders")
public class DisplayHeaders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayHeaders() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(HTMLWriter.getHTMLHeader("Headers"));
		
		// Request headers
		sb.append("<h1>Request Headers</h1>");
		Enumeration<String> requestHeaders = request.getHeaderNames();
		if (requestHeaders.hasMoreElements()) {
			while (requestHeaders.hasMoreElements()) {
				String key = requestHeaders.nextElement();
				sb.append("<p>" + key + ": " +	request.getHeader(key) +"</p>");
			}
		}
		else
		{
			sb.append("<p>No request headers found</p>");
		}
		
		// Response Headers
		sb.append("<h1>Response Headers</h1>");
		Collection<String> responseHeaders = response.getHeaderNames();
		if (responseHeaders.size() != 0) {
			for (String headerName: responseHeaders) {
				String key = headerName;
				sb.append("<p>" + key + ": " +	response.getHeader(key) +"</p>");
			}
		}
		else
		{
			sb.append("<p>No response headers found</p>");
		}
		
		sb.append(HTMLWriter.getHTMLFooter());
		response.getWriter().write(sb.toString());
	}

}
