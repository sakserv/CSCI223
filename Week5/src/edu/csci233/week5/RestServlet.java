package edu.csci233.week5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RestServlet
 */
@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestServlet() {
        super();
    }

   
   
    /**
     * Retrieve
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HTML Header
		StringBuffer sb = new StringBuffer();
		sb.append(HTMLWriter.getHTMLHeader("Get"));
		
		String key = request.getParameter("key");
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(key);
		
		// Return 404 if key not found in session
		if(value == null) {
			response.sendError(404, "Key " + key + " not found");
		}
		
		// User reponse.
		sb.append("<h2>Successfully Retrieved:");
		sb.append("<h2>Key: " + key + "</h2><br />");
		sb.append("<h2>Value: " + value + "</h2>");
		response.setStatus(200);
		
		// HTML Footer
		sb.append(backLink());
		sb.append(HTMLWriter.getHTMLFooter());
		response.getWriter().write(sb.toString());
	}

	
	/**
	 * Update/Create
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// HTML Spec does not support Put/Delete
		String method = request.getParameter("method");
		if(method.equals("put")) {
			doPut(request, response);
		} else if(method.equals("delete")) {
			doDelete(request, response);
			
		// Handle post if we are still here.
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(HTMLWriter.getHTMLHeader("Post"));
			
			String key = request.getParameter("key");
			String value = request.getParameter("value");
			HttpSession session = request.getSession();
			
			// Return Precondition Failed 412 if either input field is blank
			if (key.isEmpty() || value.isEmpty()) {
				response.sendError(412, "Must supply both key and value");
			}
			
			// Add to session
			session.setAttribute(key, value);
			
			// User reponse.
			sb.append("<h2>Successfully Updated/Created:");
			sb.append("<h2>Key: " + key + "</h2><br />");
			sb.append("<h2>Value: " + value + "</h2><br />");
			
			sb.append(backLink());
			sb.append(HTMLWriter.getHTMLFooter());
			response.getWriter().write(sb.toString());
		}
	}	
	
	
	/**
	 * Create
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(HTMLWriter.getHTMLHeader("Put"));
		
		// See if anything is stored in session, if not
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		HttpSession session = request.getSession();
		
		// Return 412 if already exists.
		if(session.getAttribute(key) != null) {
			response.sendError(512, "Key " + key + " exists, use post");
		}
		
		// Return Precondition Failed 412 if either input field is blank
		if (key.isEmpty() || value.isEmpty()) {
			response.sendError(412, "Must supply both key and value");
		}
		
		// Add to session
		session.setAttribute(key, value);
		
		// User reponse.
		sb.append("<h2>Successfully Created:");
		sb.append("<h2>Key: " + key + "</h2><br />");
		sb.append("<h2>Value: " + value + "</h2><br />");
		
		sb.append(backLink());
		sb.append(HTMLWriter.getHTMLFooter());
		response.getWriter().write(sb.toString());
	}
	
	
	/**
	 * Delete
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(HTMLWriter.getHTMLHeader("Post"));
		
		String key = request.getParameter("key");
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(key);
		
		// Return Precondition Failed 412 if input field is blank
		if (key.isEmpty()) {
			response.sendError(412, "Must supply both key and value");
		}
		
		// Return 404 if key not found in session
		if(value == null) {
			response.sendError(404, "Key " + key + " not found");
		}
		
		// Remove the attribute from session
		session.removeAttribute(key);
		
		// User reponse.
		sb.append("<h2>Successfully Removed:");
		sb.append("<h2>Key: " + key + "</h2><br />");
		sb.append("<h2>Value: " + value + "</h2><br />");
		
		sb.append(backLink());
		sb.append(HTMLWriter.getHTMLFooter());
		response.getWriter().write(sb.toString());
	}
	
	private String backLink() {
		return "<div><a href='RestJSP.jsp'>Go Back</a></div>";
	}

}
