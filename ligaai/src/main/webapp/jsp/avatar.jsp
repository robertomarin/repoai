<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Upload a file please</title>
	</head>
	<body>
		<h1>Please upload a file</h1>
		<form method="post" action="/uploadAvatar.html" enctype="multipart/form-data">
			<input type="file" name="file" /> 
			<input type="submit" />
		</form>
		<br/>
		<div  id="avatar"> 
			<img src="/ligaai/photos/teste.jpg">
		</div> 
		<c:if test="${msg != null}">
			<c:out value="${msg}" escapeXml="false"/>
		</c:if>
	</body>
</html>
