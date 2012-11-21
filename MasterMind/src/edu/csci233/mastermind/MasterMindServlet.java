package edu.csci233.mastermind;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MasterMindServlet
 */
@WebServlet("/MasterMindServlet")
public class MasterMindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MasterMindServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		if(session.isNew()) {
			createGame();
		}
	}
	
	private Game createGame() {
		Game game = Game.setupGame();
		return game;
	}
	
	private String emptyRow() {
		return "<div><div class=\"rowcontent\"><div class=\"largepegarea\"><div class=\"largepeg smallshadow\"></div><div class=\"largepeg smallshadow\"></div><div class=\"largepeg smallshadow\"></div><div class=\"largepeg smallshadow\"></div></div><div class=\"smallpegarea\"><div class=\"smallpeg smallshadow\"></div><div class=\"smallpeg smallshadow\"></div><div class=\"smallpeg smallshadow\"></div><div class=\"smallpeg smallshadow\"></div></div></div></div>";
	}
	
	private String hiddenCodeRow() {
		return "				<div class=\"coderow\">\r\n" + 
				"					<div class=\"rowcontent\">\r\n" + 
				"						<div class=\"codepegarea\">\r\n" + 
				"							<div class=\"questionpeg shadow\"><p>?</p></div>\r\n" + 
				"							<div class=\"questionpeg shadow\"><p>?</p></div>\r\n" + 
				"							<div class=\"questionpeg shadow\"><p>?</p></div>\r\n" + 
				"							<div class=\"questionpeg shadow\"><p>?</p></div>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"				</div>";
	}

}
