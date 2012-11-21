<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MasterMind</title>
<link rel="stylesheet" href="styles.css" />

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

</head>
<body>

<div id="page">
	<div id="header" class="rounded shadow"><div id="heading">MASTERMIND</div></div>
	<div id="content" class="rounded shadow">
		<div id="board" class="shadow">
			<c:if test="${not empty boardState}">
				${boardState}
			</c:if>
		
			
			<c:if test="${empty boardState}">
				<div class="coderow">
					<div class="rowcontent">
						<div class="codepegarea">
							<div class="questionpeg shadow"><p>?</p></div>
							<div class="questionpeg shadow"><p>?</p></div>
							<div class="questionpeg shadow"><p>?</p></div>
							<div class="questionpeg shadow"><p>?</p></div>
						</div>
					</div>
				</div>
			
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div><div class="largepeg smallshadow"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
				<div id="row8" class="currentRow"><div class="rowcontent"><div class="largepegarea"><div class="largepeg smallshadow droppable"></div><div class="largepeg smallshadow droppable"></div><div class="largepeg smallshadow droppable"></div><div class="largepeg smallshadow droppable"></div></div><div class="smallpegarea"><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div><div class="smallpeg smallshadow"></div></div></div></div>
			
			
				<div class="userarea">
					<div class="userarealeft">
						<div class="userpegarea shadow rounded">
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, red, black);"></div>
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, green, black);"></div>
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, blue, black);"></div>
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, yellow, black);"></div>
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, brown, black);"></div>
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, orange, black);"></div>
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, gray, black);"></div>
							<div class="largepeg smallshadow draggable" style=" background-image: -webkit-radial-gradient(30% 40%, white, black);"></div>
						</div>
					</div>
					<div class="userarearight">
						<form method="post" name="currentRow" action="javascript: submitRow">
							<input type="hidden" name="peg0" />
							<input type="hidden" name="peg1" />
							<input type="hidden" name="peg2" />
							<input type="hidden" name="peg3" />
							<input type="button" name="submit" value="Check" id="submit" />
						</form>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>

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

$("#submit").click(function() {
	var currentRowData = new Array();
	$('.currentRow > div > div > div > div:last-child').each(function(index, value) {
		var bgColor = $(this).css("backgroundImage");
		var parts = bgColor.split(',');
		currentRowData[index] = parts[1];
	});
	
	if(currentRowData.length != 4) {
		alert("ERROR");
		/*$(document).dialog({modal: true});*/
	} else {
		currentRowData.forEach(function(item) {
			alert(item);
		});
	}
});

</script>

</body>
</html>