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
		
		// Get session
		HttpSession session = request.getSession();
		
		// Setup a new game
		String buttonPushed = request.getParameter("buttonPushed");
		if(session.getAttribute("game") == null || buttonPushed.equals("newGame")) {
			setupNewGame(request, response);
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
			
			// Check the users code against the code row and display if correct
			if(!game.checkGuess(currentRow)) {
				setCodeRowHiddenDisplay(request, response);
			} else {
				game.setCodeRow(currentRow);
				setCodeRowDisplay(request, response);
				session.setAttribute("gameOverTextDisplay", "You Won!");
				setGameOverModal(request, response);
			}
			
			// Handle the user reaching the end of the board without a correct repsonse.
			game.setActiveRowId(game.getActiveRowId() + 1);
			if(game.getActiveRowId() == Game.BOARD_HEIGHT) {
				setCodeRowDisplay(request, response);
				session.setAttribute("gameOverTextDisplay", "Better luck next time!");
				setGameOverModal(request,response);
			}
		}
		
		// Display the board
		generateGameRowsDisplay(request, response);
		codeSizeSelectedDisplay(request, response);
		
		session.setAttribute("defaultHasBeenLoaded", "true");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * Creates a new game and sets the defaults.
	 * @param request
	 * @param response
	 */
	private void setupNewGame(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
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
	}
	
	/**
	 * Displays the game over modal.
	 * @param request
	 * @param response
	 */
	private void setGameOverModal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		session.removeAttribute("gameOverDisplay");
		
		sb.append("<script type='text/javascript'>");
		sb.append("$(function() {");
		sb.append("$('#gameover-dialog-modal').dialog({");
		sb.append("height: 220,");
		sb.append("width: 440,");
		sb.append("closeOnEscape: false,");
		sb.append("dialogClass: 'no-close',");
		sb.append("modal: true })});");
		sb.append("</script>");
		session.setAttribute("gameOverDisplay", sb.toString());
	}

	/**
	 * Generates all rows on the game board.
	 * @param request
	 * @param response
	 */
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
	
	/**
	 * Sets the current row using the users response.
	 * @param request
	 * @param response
	 */
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
	
	/**
	 * Generates the HTML for a single row including the response pegs.
	 * @param request
	 * @param response
	 * @param userRow	row to be processed
	 * @param responseRow	reponse pegs
	 * @param currentRow	true if the row being processed is the current row.
	 * @return
	 */
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
	
	/**
	 * Sets the code row display when pegs are hidden
	 * @param request
	 * @param response
	 */
	private void setCodeRowHiddenDisplay(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < game.getCodeSize(); i++) {
			sb.append("<div class='questionpeg shadow'><p>?</p></div>");
		}
		session.setAttribute("codeRowDisplay", sb.toString());
	}
	
	/**
	 * Sets the codeRow display for reveal.
	 * @param request
	 * @param response
	 */
	private void setCodeRowDisplay(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		
		for(Peg peg : game.getCodeRow().getPegs()) {
			sb.append("<div class='largepeg shadow " + getPegCssClass(peg.getColor()) + "'></div>");
		}
		session.setAttribute("codeRowDisplay", sb.toString());
	}
	
	/**
	 * Gets the css class based on peg color
	 * @param color	Peg color
	 * @return	css class
	 */
	private String getPegCssClass(Color color) {
		return color.toString().toLowerCase() + "peg";
	}
	
	/**
	 * Get the peg color enum from the color string
	 * 
	 * @param color	The peg color as a string
	 * @return	enum color of the peg
	 */
	private Color getPegColor(String color) {
		return Color.valueOf(color.toUpperCase().trim());
	}
	
	/**
	 * Set code row display when the pegs are hidden.
	 * @param request	Http request
	 * @param response	Http response
	 */
	private void createHiddenPegFields(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < game.getCodeSize(); i++) {
			sb.append("<input type='hidden' name='peg" + i + "' />");
		}
		
		session.removeAttribute("hiddenPegFields");
		session.setAttribute("hiddenPegFields", sb.toString());
	}
	
	/**
	 * Set the codeSizeDisplay session attribute which is used to populate the field in the form to the current value
	 * @param request	Http request
	 * @param response	Http response
	 */
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
	
	/**
	 * Dynamically adjust the board width
	 * @param request	Http request
	 * @param response	Http response
	 */
	private void setBoardWidthCss(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("boardWidthClass");
		session.setAttribute("boardWidthClass", "board" + game.getCodeSize());
	}
	
	/**
	 * Set the game settings.
	 * @param request	Http request
	 * @param response	Http response
	 */
	private void setGameSettings(HttpServletRequest request, HttpServletResponse response) {
		game.setCodeSize(Integer.parseInt(String.valueOf(request.getParameter("codeSize"))));
	}

}
