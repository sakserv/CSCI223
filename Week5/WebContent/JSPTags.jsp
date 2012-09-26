<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Tags</title>
</head>
<body>
 <jsp:useBean id="testBean" class="edu.csci233.week5.TestBean" scope="page">
 	<jsp:setProperty name="testBean" property="name" value="Shane" />
 </jsp:useBean>
 <h1>Hello, <jsp:getProperty name="testBean" property="name" /></h1>
 
 <jsp:include page="IncludePage.jsp"></jsp:include>
 
</body>
</html>