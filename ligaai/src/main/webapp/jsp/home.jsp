<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<h1>Bem vindo ao liga.ai</h1>
	
	<h2>Aqui é muito legal, veja a hora atual</h2>
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate value="${now}" pattern="HH:mm dd/MM/yy"/>
	
	<h3>Gostou? Nem eu!</h3>
</html>