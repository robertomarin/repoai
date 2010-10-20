<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="ligaai">
		<div id="cloneable" class="hide">
			<select class="contactType" name="contactType">
				<option value="PHONE" selected>Telefone</option>
				<option value="SKYPE">Skype</option>
				<option value="GTALK">Google Talk</option>
				<option value="MSN">MSN</option>
				<option value="TWITTER">Twitter</option>
				<option value="FACEBOOK">Facebook</option>
				<option value="ORKUT">Orkut</option>
				<option value="EMAIl">E-mail</option>
			</select>
			<input type="text" name="contact" id="contact" class="phone contactInfo" />
		</div>
		<div class="contactContainer">
			<select class="contactType" name="contactType">
				<option value="PHONE" selected>Telefone</option>
				<option value="SKYPE" class="skype">Skype</option>
				<option value="GTALK">Google Talk</option>
				<option value="MSN">MSN</option>
				<option value="TWITTER">Twitter</option>
				<option value="FACEBOOK">Facebook</option>
				<option value="ORKUT">Orkut</option>
				<option value="EMAIl">E-mail</option>
			</select>
			<input type="text" name="contact" id="contact" class="phone contactInfo" />
		</div>
		
		<textarea id="message">Me liga.ai ;-)</textarea>
		<p><input type="checkbox" checked="checked" /> Li e concordo com os <a href="#" title="Veja os termos de uso do liga.ai">termos de uso</a></p>						
		<input type="submit" value="envia.ai" class="sendButton" />
		<input type="hidden" id="email" value="jul.dsantos@gmail.com" />
	</form>
	
	<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.jsonp-2.1.2.min.js"></script>
	<script type="text/javascript" src="/js/ligaai.js"></script>
</body>
</html>