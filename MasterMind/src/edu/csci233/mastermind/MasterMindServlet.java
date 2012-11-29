package edu.csci233.mastermind;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.csci233.mastermind.Peg.Color;
import edu.csci233.mastermind.Peg.PegType;

/**
 * Servlet implementation class MasterMindServlet
 */
@WebServlet("/MasterMindServlet")
public class MasterMindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Game game;
       
    public MasterMindServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Validate params
		HttpSession session = request.getSession();
		if(!validateSessionParamTypes(request, response)) {
			response.sendError(500, "Session is corrupt, restart your browser.");
		}
		
		// Setup a new game
		String buttonPushed = request.getParameter("buttonPushed");
		if(session.getAttribute("game") == null || buttonPushed.equals("newGame")) {
			game = Game.setupGame();
			setGameSettings(request, response);
			
			// Create a new empty board state.
			session.removeAttribute("gameOverDisplay");
			game.initGameState();
			game.initReponseState();
			game.generateCodeRow();
			game.setCodeRowHidden(true);
			game.setActiveRowId(0);
			setBoardWidthCss(request, response);
			createHiddenPegFields(request, response);
			setCodeRowHiddenDisplay(request, response);
			
			// Store the game object in session.
			session.setAttribute("game", game);
		} else {
			// Not a new game, get the game object from session
			game = (Game)session.getAttribute("game");
		}
		
		// If user clicked "check", set the users pegs in the current row.
		if(buttonPushed.equals("checkResult")) {
			
			// Row is now in it's proper place in the game state array
			populateActiveRow(request, response);
			
			// Set the response row using the current row.
			Row currentRow = game.getGameState()[game.getActiveRowId()];
			game.setResponseRow(currentRow);
			
			if(!game.checkGuess(currentRow)) {
				setCodeRowHiddenDisplay(request, response);
			} else {
				game.setCodeRow(currentRow);
				setCodeRowDisplay(request, response);
				setGameOverModal(request, response);
			}
			game.setActiveRowId(game.getActiveRowId() + 1);
		}
		
		generateGameRowsDisplay(request, response);
		codeSizeSelectedDisplay(request, response);
		
		session.setAttribute("defaultHasBeenLoaded", "true");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private void setGameOverModal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		session.removeAttribute("gameOverDisplay");
		
		sb.append("<script type='text/javascript'>");
		sb.append("$(function() {");
		sb.append("$('#gameover-dialog-modal').dialog({");
		sb.append("height: 220,");
		sb.append("width: 440,");
		sb.append("modal: true })});");
		sb.append("</script>");
		session.setAttribute("gameOverDisplay", sb.toString());
	}

	private void generateGameRowsDisplay(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		session.removeAttribute("gameRowsDisplay");
		
		for(int i = Game.BOARD_HEIGHT - 1; i >= 0; i--) {
			if(i == game.getActiveRowId()) {
				sb.append(getRowDisplay(request, response, game.getGameState()[i], game.getResponseState()[i], true));
			} else {
				sb.append(getRowDisplay(request, response, game.getGameState()[i], game.getResponseState()[i], false));
			}
		}
		
		session.setAttribute("gameRowsDisplay", sb.toString());
	}
	
	private void populateActiveRow(HttpServletRequest request, HttpServletResponse response) {
		Row[] gameState = game.getGameState();
		Row activeRow = gameState[game.getActiveRowId()];
		Peg[] activeRowPegs = activeRow.getPegs();
		
		for(int i = 0; i < activeRowPegs.length; i++) {
			activeRowPegs[i].setColor(getPegColor((String)request.getParameter("peg" + i)));
			activeRowPegs[i].setPegType(PegType.LARGE);
		}
		
		activeRow.populate(activeRowPegs);
		gameState[game.getActiveRowId()] = activeRow;
		game.setGameState(gameState);
	
	}
	
	private String getRowDisplay(HttpServletRequest request, HttpServletResponse response, Row userRow, Row responseRow, boolean currentRow) {
		StringBuffer sb = new StringBuffer();
		
		// Start the current row
		if(currentRow) {
			sb.append("<div class='rowcontent currentRow'>");
		} else {
			sb.append("<div class='rowcontent'>");
		}
		
		sb.append("<div class='largepegarea'>");
		for (Peg peg : userRow.getPegs()) {
			if(currentRow) {
				sb.append("<div class='largepeg smallshadow droppable'></div>");
			} else {
				if(peg.getColor() == Color.NONE) {
					sb.append("<div class='largepeg smallshadow'></div>");
				} else {
					sb.append("<div class='largepeg smallshadow " + getPegCssClass(peg.getColor()) + "'></div>");
				}
			}
		}
		sb.append("</div>");
		
		
		sb.append("<div class='smallpegarea'>");
		for (Peg peg : responseRow.getPegs()) {
			if(peg.getColor().equals(Color.NONE)) {
				sb.append("<div class='smallpeg smallshadow'></div>");
			} else {
				sb.append("<div class='smallpeg smallshadow " + getPegCssClass(peg.getColor()) + "'></div>");
			}
		}
		sb.append("</div>");
		
		sb.append("</div>");

		
		return sb.toString();
	}
	
	private void setCodeRowHiddenDisplay(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < game.getCodeSize(); i++) {
			sb.append("<div class='questionpeg shadow'><p>?</p></div>");
		}
		session.setAttribute("codeRowDisplay", sb.toString());
	}
	
	private void setCodeRowDisplay(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		
		for(Peg peg : game.getCodeRow().getPegs()) {
			sb.append("<div class='largepeg shadow " + getPegCssClass(peg.getColor()) + "'></div>");
		}
		session.setAttribute("codeRowDisplay", sb.toString());
	}
	
	private String getPegCssClass(Color color) {
		return color.toString().toLowerCase() + "peg";
	}
	
	private Color getPegColor(String color) {
		return Color.valueOf(color.toUpperCase().trim());
	}
	
	private void createHiddenPegFields(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < game.getCodeSize(); i++) {
			sb.append("<input type='hidden' name='peg" + i + "' />");
		}
		
		session.removeAttribute("hiddenPegFields");
		session.setAttribute("hiddenPegFields", sb.toString());
	}
		private void codeSizeSelectedDisplay(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 4; i <= 8; i++) {
			if(i == game.getCodeSize()) {
				sb.append("<option value='" + i + "' selected='selected'>" + i + "</option>");
			} else {
				sb.append("<option value='" + i + "'>" + i + "</option>");
			}
		}
		
		session.removeAttribute("codeSizeDisplay");
		session.setAttribute("codeSizeDisplay", sb.toString());
	}
	
	private void setBoardWidthCss(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("boardWidthClass");
		session.setAttribute("boardWidthClass", "board" + game.getCodeSize());
	}
	
	private boolean validateSessionParamTypes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Enumeration<String> requestParams = request.getAttributeNames();
		while(requestParams.hasMoreElements()) {
			String key = requestParams.nextElement();
			
			if(key.equals("game") && !(session.getAttribute(key) instanceof Game)) {
				return false;
			}
			
			if(key.equals("codeRow") && !(session.getAttribute(key) instanceof Row[])) {
				return false;
			}
			
			if(key.equals("codeRowHidden") && !(session.getAttribute(key) instanceof Boolean)) {
				return false;
			}
			
			if(key.equals("currentRow") && !(session.getAttribute(key) instanceof Integer)) {
				return false;
			}
		}
		return true;
	}
	
	private void setGameSettings(HttpServletRequest request, HttpServletResponse response) {
		game.setCodeSize(Integer.parseInt(String.valueOf(request.getParameter("codeSize"))));
	}

}
