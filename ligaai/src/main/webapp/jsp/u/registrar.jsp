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
	<form id="u_registrar">
		<strong class="ligaStyle">registra<span class="baseColor">.</span>ai</strong>
		<p>Já tem uma conta? <span id="login" class="formAction baseColor">Faça login</span></p>
		<ul>
			<li><label>Nome de usuário</label></li>
			<li><input id="name" type="text" /></li>
			<li><label>Senha</label></li>
			<li><input id="password" type="password" /></li>
			<li><label>E-mail</label></li>
			<li><input id="email" type="text" /></li>
			<li id="terms">
				<code>
					Terms of Service
					These Terms of Service (“Terms”) govern your access to and use of the services and Twitter’s websites (the “Services”), and any information, text, graphics, or other materials uploaded, downloaded or appearing on the Services (collectively referred to as “Content”). Your access to and use of the Services is conditioned on your acceptance of and compliance with these Terms. By accessing or using the Services you agree to be bound by these Terms.

					Basic Terms

					You are responsible for your use of the Services, for any content you post to the Services, and for any consequences thereof. The Content you submit, post, or display will be able to be viewed by other users of the Services and through third party services and websites (go to the account settings page to control who sees your Content). You should only provide Content that you are comfortable sharing with others under these Terms.
				</code>
			</li>
			<li>Ao clicar em registrar, estou concordando com os termos de uso</li>
			<li><input type="submit" value="registra.ai" /></li>
		</ul>
	</form>
	
	<form id="u_entrar">
		<strong class="ligaStyle">registra<span class="baseColor">.</span>ai</strong>
		<p>Já tem uma conta? <span id="login" class="formAction baseColor">Faça login</span></p>
		<ul>
			<li><label>E-mail</label></li>
			<li><input id="email" type="text" /></li>
			<li><label>Senha</label></li>
			<li><input id="password" type="password" /></li>
			<li><input type="submit" value="registra.ai" /></li>
		</ul>
	</form>
	
	<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.jsonp-2.1.2.min.js"></script>
	<script type="text/javascript" src="/js/ligaai.js"></script>
</body>
</html>