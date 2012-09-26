<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RestJSP</title>
</head>
<body>

<h1>Session Storage Retrieval via REST</h1>

<form method="get" action="RestServlet">
  <h2>Get</h2>
  Key: <input type="text" name="key" /><br /> 
  <input type="submit" value="Submit" />
</form>

<form method="post" action="RestServlet">
  <h2>Post</h2>
  Key: <input type="text" name="key" /><br /> 
  Value: <input type="text" name="value" /><br />
  <input type="hidden" name="method" value="post" />
  <input type="submit" value="Submit" />
</form>

<form method="post" action="RestServlet">
  <h2>Put</h2>
  Key: <input type="text" name="key" /><br /> 
  Value: <input type="text" name="value" /><br />
  <input type="hidden" name="method" value="put" />
  <input type="submit" value="Submit" />
</form>

<form method="post" action="RestServlet">
  <h2>Delete</h2>
  Key: <input type="text" name="key" /><br /> 
  <input type="hidden" name="method" value="delete" />
  <input type="submit" value="Submit" />
</form>

</body>
</html>