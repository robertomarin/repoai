<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="pt-BR">
	<head>
		<title>Liga.ai</title>
		<meta charset="UTF-8"/>
		<meta name="title" content="Liga ai" />
		<meta name="description" content="Liga ai" />
		<meta name="keywords" content="Liga ai" /> 
		<link type="image/x-icon" href="/img/favicon.ico" rel="shortcut icon" />
		<!--css-->
		<link rel="stylesheet" href="/css/ligaai.css" />
		<!--js-->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript"> 
			if(typeof jQuery != 'function'){
				var jHead=document.createElement('script'); 
				jHead.type='text/javascript'; 
				jHead.src='/js/jquery-1.4.2.min.js" />'; 
				document.getElementsByTagName('head')[0].appendChild(jHead);
			}
		</script>
		<script language="javascript" src="/js/maskinput.js"></script>
		<script type="text/javascript" src="/js/ZeroClipboard.js"></script>
		<script language="javascript" src="/js/ligaai.js"></script>
		<script language="javascript" src="/js/jquery-ui-1.8.2.custom.min.js"></script>
		<!--[if lt IE 9]>
			<script src="/js/html5.js"></script>
		<![endif]-->
	</head>
	<body class="morningBackground">
	
	

	<script language="JavaScript">

	</script>
	
		<div class="wrapper">
			<header id="logo">
				<h1 class="ligaStyle">Liga<span class="baseColor">.</span>ai</h1>
			</header>
			
			<!--Shorten url-->
			<article id="shortenUrl">
				<div class="container">
					<header>
						<h1>O liga.ai encurta urls para você...</h1>
					</header>
					<section>
						<form id="microurl">
							<input type="text" name="url" id="url" class="shortenField" />
							<input type="submit" value="encurta.ai" class="shortenSubmit" />
						</form>
						<div id="doneShortenUrl">
							<span class="baseColor">Sua url encurtada</span> <input type="text" id="microurlmicro" readonly="readonly" />
							<ul>
								<li><a href="#" title="Postar essa url no Twitter">Twitte essa url</a></li>
								<li>|</li>
								<li><span id="copyClipboard">Copy to clipboard</span></li>
								<li>|</li>
								<li>Você economizou <strong class="baseColor">5</strong> caracteres</li>
								<li>|</li>
								<li>Cliques: 56</li>
							</ul>
						</div>
					</section>
				</div>
			</article>
			<!--Shorten url end-->
			<!-- Liga.ai -->
			<article id="liga">
				<div class="container">
					<header>
						<h1 class="ligaStyle">Me Liga<span class="baseColor">.</span>ai</h1>
						<span id="subscribeButton">LOGAR</span>
					</header>
					<section>
						<form id="ligaai">
							<!-- 
							<div id="cloneable">
								<select class="contactType">
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
							 -->
							<div class="contactContainer">
								<select class="contactType">
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
							
							<textarea id="message"></textarea>
							<input type="text" id="email" />
							<input type="submit" value="envia.ai" />
							<p><input type="checkbox" checked="checked" /> Li e concordo com os <a href="#" title="Veja os termos de uso do liga.ai">termos de uso</a></p>						
						</form>
					</section>
				</div>
			</article>
			<!-- Liga.ai end-->
			
			<section id="content">
				<!--user list-->
				<c:forEach items="${ligaais}" var="ligaai">
					<article class="users">
						<div class="userPic">
							<img src="user.jpg" class="userPic" alt="Me liga ai!" />
						</div>
						<div class="userInfo">
							<header>
								<hgroup>
								<h1>Jo Soares</h1>
								<h2><c:out value="${ligaai.contacts[0].content}" escapeXml="false"/></h2>
								<h3><c:out value="${ligaai.message}" escapeXml="false"/></h3>
								</hgroup>
							</header>
							<section>
								<ul class="socialNetwork">
									<li class="fullLine"><strong>Meus Perfis</strong></li>
									<li><a href="#" class="twitter"></a></li>
									<li><a href="#" class="facebook"></a></li>
									<li><a href="#" class="orkut"></a></li>
									<li class="msn fullLine">odaliska.semdestino@hotmail.com</li>
									<li class="skype fullLine">jurona</li>
									<li class="gTalk fullLine">gatom@gmail.com</li>
								</ul>
							</section>
						</div>
						<div class="rating">
							<span class="like"></span>
							<span class="dislike"></span>
						</div>
					</article>
				</c:forEach>
				<!--end user list-->
			</section>
			
			<!-- AD -->
			<section id="ad" class="lastUnit">
				<script type="text/javascript"><!--
					google_ad_client = "pub-9040087244579201";
					/* 160x600 */
					google_ad_slot = "8363583236";
					google_ad_width = 160;
					google_ad_height = 600;
					//-->
					</script>
					<script type="text/javascript"
					src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
				</script>
			</section>
			<!-- AD end-->
		</div>
		<footer>
			<div class="wrapper">
				<p>Todos os direitos reservados - liga.ai</p>
				<p>Antes de utilizar nosos servicos leia o Termos de uso</p>
				<p>Sobre o Liga.ai</p>
			</div>
		</footer>
		<div id="about">
			<p id="aboutText">
				<span class="baseColor">.</span>sobre
			</p>
			<div id="aboutContent">
				Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.
			</div>
		</div>
		<!-- LIGHTBOX -->
		<div id="subscribe">
			<form>
				<strong class="ligaStyle">registra<span class="baseColor">.</span>ai</strong>
				<ul>
					<li><label>Nome de usuario</label> <input type="text" /></li>
					<li><label>Senha</label> <input type="text" /></li>
					<li><label>E-mail</label> <input type="text" /></li>
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
		</div>
		
		<!-- LIGHTBOX -->
		<!--GA-->
		<script type="text/javascript">
			 var _gaq = _gaq || [];
			 _gaq.push(['_setAccount', 'UA-17799216-1']);
			 _gaq.push(['_trackPageview']);
			
			 (function() {
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			 })();
		</script>
		<!--FIM GA-->
	</body>
</html>