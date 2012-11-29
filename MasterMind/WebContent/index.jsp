<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MasterMind</title>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="styles.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

</head>
<body>

<div id="page">
	<div id="header" class="rounded shadow"><div id="heading">MASTERMIND</div></div>
	<div id="content" class="rounded shadow">
		<div id="board" class="shadow ${sessionScope.boardWidthClass}">
		
			<div class="coderow rowcontent">
				<div class="codepegarea">
					${sessionScope.codeRowDisplay}
				</div>
			</div>
			
			${sessionScope.gameRowsDisplay}
			
			
			<div class="userarea">
				<div class="userarealeft">
					<div class="userpegarea shadow rounded">
						<div class="largepeg smallshadow draggable redpeg"></div>
						<div class="largepeg smallshadow draggable greenpeg"></div>
						<div class="largepeg smallshadow draggable bluepeg"></div>
						<div class="largepeg smallshadow draggable yellowpeg"></div>
						<div class="largepeg smallshadow draggable purplepeg"></div>
						<div class="largepeg smallshadow draggable orangepeg"></div>
						<div class="largepeg smallshadow draggable graypeg"></div>
						<div class="largepeg smallshadow draggable whitepeg"></div>
					</div>
				</div>
				
				<div class="userarearight">
					<form method="post" name="currentRowForm" id="currentRowForm" action="/MasterMind/MasterMindServlet">
						${sessionScope.hiddenPegFields}
						<input type="hidden" name="buttonPushed" />
						<input type="hidden" name="defaultHasBeenLoaded" id="defaultHasBeenLoaded" value="${sessionScope.defaultHasBeenLoaded}" />
						<input type="submit" value="Check" id="checkResult" name="checkResult" class="fancybutton" /><br />
						<input type="submit" value="New Game" id="newGame" name="newGame" class="fancybutton" /><br />
							
						<label>Row Size</label><br />
						<select id="codeSize" name="codeSize">${sessionScope.codeSizeDisplay}</select>
					</form>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div id="pegmissing-dialog-modal" title="Missing Peg">A peg is missing from the response row, please try again.</div>
<div id="gameover-dialog-modal" title="You Won!">Game Over<br /><input type="button" id="restartGame" name="restartGame" value="New Game" class="fancybutton"></div>
${sessionScope.gameOverDisplay}

<script type="text/javascript">
$(function() {
    $(".draggable").draggable({ helper: 'clone', revert:'invalid' });
    $(".droppable").droppable({
    	tolerance: "intersect",
        drop: function(event, ui) {
            var $this = $(this);
            var $clone = $(ui.helper).clone();
            $this.append($clone);
            $clone.css('top',$this.position().top);
            $clone.css('left',$this.position().left);
        }
    });
});

// Store the current rows selections and post to the servlet, display an error model is not all rows are set.
$("#checkResult").click(function() {
	$('[name=buttonPushed]').val("checkResult");
	var currentRowData = new Array();
	$('.currentRow > div > div > div:last-child').each(function(index, value) {
		var bgColor = $(this).css("backgroundImage");
		var parts = bgColor.split(',');
		currentRowData[index] = parts[1];
	});
	
	if(currentRowData.length != $('[name=codeSize]').val()) {
		$(function() {
	        $("#pegmissing-dialog-modal").dialog({
	            height: 220,
	            width: 440,
	            modal: true
	        });
	    });
		return false;
	}
	
	// If no errors set the value
	for(var i = 0; i < currentRowData.length; i++) {
		$('[name=peg' + i + ']').val(currentRowData[i]);
	}
	
});

// Handle starting a new game
$("#newGame").click(function() {
	$('[name=buttonPushed]').val("newGame");
	$("#currentRowForm").submit();
});

// When the document loads the first time, display the default board.
$(document).ready(function() {
	if($("#defaultHasBeenLoaded").val() != "true") {
		$("<option value='4'>4</option>").appendTo("#codeSize");
		$('[name=allowDuplicates]').val("true");
		$('[name=codeSize]').val("4");
		$("#newGame").click();
	}
});

$("#restartGame").click(function() {
	$("<option value='4'>4</option>").appendTo("#codeSize");
	$('[name=allowDuplicates]').val("true");
	$('[name=codeSize]').val("4");
	$("#newGame").click();
});

</script>

</body>
</html>