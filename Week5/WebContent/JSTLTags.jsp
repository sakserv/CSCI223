<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSTL Tags</title>
</head>
<body>

<form method="post">
  <input type="text" name="name" />
  <input type="submit" value="Submit" />
</form>

<c:if test="${param.name != ''}">
  <c:if test="${param.name == 'Shane'}">
    <h1>Hello, Great One.</h1>
  </c:if>
  <c:if test="${param.name != 'Shane'}">
    <h1>Hello, ${param.name}</h1>
  </c:if>
</c:if>

<c:choose>
<c:when test="${param.name == 'Shane'}"><h1>Hello, Great One.</h1></c:when>
<c:when test="${param.name != ''}"><h1>Hello, ${param.name}</h1></c:when>
<c:otherwise></c:otherwise>
</c:choose>

<c:forEach var="i" begin="0" end="${fn:length(param.name)}" step="1">
    <c:out value="${fn:substring(param.name, i, i + 1)}" /><br />     
</c:forEach>


</body>
</html>