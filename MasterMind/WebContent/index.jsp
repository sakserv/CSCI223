<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MasterMind</title>
<link rel="stylesheet" href="styles.css" />
</head>
<body>

<div id="page">
	<div id="header"><div id="heading">MASTERMIND</div></div>
	<div id="content">
		<div id="board">
			<div id="coderow"><div class="rowcontent"><div class="codepegs"></div></div></div>
			<c:if test="${empty rows}">
				<div id="row9"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row8"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row7"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row6"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row5"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row4"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row3"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row2"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row1"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
				<div id="row0"><div class="rowcontent"><div class="largepegs"></div><div class="smallpegs"></div></div></div>
			</c:if>	
			
			<div id="userrow"></div>
		</div>
	</div>
</div>

</body>
</html>