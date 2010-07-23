<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Bem vindo ao liga.ai</h1>
	
		
	<h2>Aqui é muito legal, veja a hora atual</h2>
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate value="${now}" pattern="HH:mm:ss dd/MM/yy"/>
	<h3>Gostou? Nem eu!</h3>
</body>
</html>