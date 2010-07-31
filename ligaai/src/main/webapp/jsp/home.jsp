<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	<form id="ligaai">
		<select id="contactType">
			<option value="PHONE">Telefone</option>
			<option value="SKYPE">Skype</option>
			<option value="GTALK">Google Talk</option>
			<option value="MSN">MSN</option>
			<option value="TWITTER">Twitter</option>
			<option value="FACEBOOK">Facebook</option>
			<option value="ORKUT">Orkut<option>
			<option value="EMAIl">E-mail<option>
		</select>
		<input type="text" name="contact" id="contact" />
		<textarea id="message"></textarea>
		
		<input type="submit" />
	</form>
	
	<c:forEach items="${ligaais}" var="ligaai">
	
		<c:out value="${ligaai.contacts[0].type}" escapeXml="false"/>	
		<c:out value="${ligaai.message}" escapeXml="false"/>
		<c:out value="${ligaai.created}" escapeXml="false"/>
		<br/>
	</c:forEach>
	
	<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.jsonp-2.1.2.min.js"></script>
	<script type="text/javascript" src="/js/ligaai.js"></script>
</body>
</html>