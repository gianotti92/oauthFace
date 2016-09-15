<%@page import="org.apache.oltu.oauth2.client.response.OAuthAuthzResponse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
String code = oar.getCode(); 
session.setAttribute("code", code);


%>
</head>
<body>

Ingrese datos de persona <br>
<form action="token">
nombre:<input type="text"  name="nombre"><br>
apellido:<input type="text" name="apellido"><br>
dni:<input type="text" name="dni">
<input type="submit" value="get token">
</form>



</body>
</html>