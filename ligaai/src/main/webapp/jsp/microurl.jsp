<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
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
	<form id="microurl">
		<input type="text" name="url" id="url" />
		<input type="submit" />
	</form>
	
	<%-- 
	<c:if test="${fn:length(microurl.micro) == fn:length(microurl.url)}">
		Puxa! Não pudemos ajudar ajudar no tamanho de sua url, as duas tem o mesmo tamanho ${fn:length(microurl.micro)} caracteres.
	</c:if>
	<c:if test="${fn:length(microurl.micro) > fn:length(microurl.url)}">
		Xi! Nossa url está maior do que a url original em ${fn:length(microurl.micro) - fn:length(microurl.url) } caracteres.
	</c:if>
	<c:if test="${fn:length(microurl.micro) < fn:length(microurl.url)}">
		Você irá economizar ${fn:length(microurl.url) - fn:length(microurl.micro)} caracteres.
	</c:if>
	
	<c:if test="${msg == null}">
		url encurtada: <a href="${microurl.micro}">${microurl.micro}</a>
		<br />
		url original : <a href="${microurl.url}">${microurl.url}</a>
	</c:if>
	<c:if test="${msg != null}">
		<c:choose>
			<c:when test="${msg == 1}">A url recebida é vazia, por favor, insira uma url! :)</c:when>
			<c:when test="${msg == 2}">A url recebida </c:when>
		</c:choose>
	</c:if>
	--%>
	<div id="microurlmicro"> </div>
	<div id="microurlurl"> </div>
	
	<script type="text/javascript" src="/js/ZeroClipboard.js"></script>
	<div id="d_clip_button" style="border:1px solid black; padding:20px;">Copy To Clipboard</div>
	<script language="JavaScript">
		var clip = new ZeroClipboard.Client();
		clip.setText( 'Copy me!' );
		clip.glue( 'd_clip_button' );
	</script>
	
	<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.jsonp-2.1.2.min.js"></script>
	<script type="text/javascript" src="/js/ligaai.js"></script>
</body>
</html>