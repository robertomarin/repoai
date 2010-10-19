<%@page import="ai.liga.ligaai.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fai" uri="http://liga.ai/jstl/functions"%>


<html lang="pt-BR">
	<body class="morningBackground">
		<div class="wrapper">
		    <jsp:include page="header.jsp" />
			<div class="containerShadow">
				<!--Shorten url-->
				<article id="shortenUrl">
					<div class="container">
						<div class="message hide">Insere sua url ai :)</div>
						<header>
							<h1>O liga.ai encurta urls para você...</h1>
						</header>
						<section>
							<form id="microurl">
								<input type="text" name="url" id="url" class="shortenField" />
								<input type="submit" value="encurta.ai" class="shortenSubmit" />
							</form>
							<div id="loader" class="hide"><img src="/img/loader.gif" /></div>
							<div id="doneShortenUrl" class="hide">
								<span class="baseColor">Sua url encurtada</span>
								<input type="text" id="microurlmicro" readonly="readonly" />
								<div id="clickToCopy">Clique para copiar</div>
								<ul>
									<li><a href="#" title="Postar essa url no Twitter" id="twitter">Twitte essa url</a></li>
									<li>|</li>
									<li>Você economizou <strong class="baseColor" id="savedChars"></strong> caracteres</li>
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
						</header>
						<section>
							<form id="ligaai" action="/l/novo">
								<input type="hidden" id="position" value="0" />
								<input type="hidden" id="needsubscribe" />
								<div id="cloneable" class="hide">
									<select class="contactType" name="">
										<option value="PHONE" selected>Telefone</option>
										<option value="SKYPE">Skype</option>
										<option value="GTALK">Google Talk</option>
										<option value="MSN">MSN</option>
										<option value="TWITTER">Twitter</option>
										<option value="FACEBOOK">Facebook</option>
										<option value="ORKUT">Orkut</option>
										<option value="EMAIL">E-mail</option>
									</select>
									<input type="text" name="" class="phone contactInfo" />
								</div>
								<div class="contactContainer">
									<select class="contactType" name="contacts[0].type">
										<option value="PHONE" selected>Telefone</option>
										<option value="SKYPE" class="skype">Skype</option>
										<option value="GTALK">Google Talk</option>
										<option value="MSN">MSN</option>
										<option value="TWITTER">Twitter</option>
										<option value="FACEBOOK">Facebook</option>
										<option value="ORKUT">Orkut</option>
										<option value="EMAIL">E-mail</option>
									</select>
									<input type="text" id="firstContact" name="contacts[0].content" class="phone contactInfo" />
									<a href="#" id="addMoreContact" title="Adicione mais contatos">+</a>
								</div>
								
								<textarea name="message" id="message">Me liga.ai ;-)</textarea>
								<p><input type="checkbox" checked="checked" id="agree" /> <label for="agree">Li e concordo com os <a href="#" title="Veja os termos de uso do liga.ai">termos de uso</a></label></p>						
								<input type="submit" value="envia.ai" class="sendButton" />
							</form>
						</section>
					</div>
				</article>
				<!-- Liga.ai end-->
				<div id="userList">
					<section id="content">
						<!--user list-->
						<c:forEach items="${ligaais}" var="ligaai" varStatus="i">
							<article class="users">
								<div class="userPic">
									<img src="/ligaai/avatar/${ligaai.user.id}.jpg" class="userPic lazyImage" alt="Me liga ai!" onerror="javascript:showUnavailableImage(this, '80x80')"/>
								</div>
								<div class="userInfo">
									<header>
										<hgroup>
										<h1><a name="${ligaai.id}" href="#${ligaai.id}"><c:out value="${ligaai.user.name}" escapeXml="false"/></a></h1>
										<h3><c:out value="${ligaai.message}" escapeXml="false"/></h3>
										<ul>
											<c:forEach items="${ligaai.contacts}" var="contact" varStatus="j">
											<li class="${fn:toLowerCase(contact.type)}">
												<c:if test="${fai:isLinkable(contact.type)}">
													<a href="${fai:getUrlContact(contact)}">${contact.content}</a>
												</c:if>
												<c:if test="${not fai:isLinkable(contact.type) }">
													${contact.content}
												</c:if>
											</li>
											</c:forEach>
										</ul>
										</hgroup>
									</header>
								</div>
<!--								<div class="rating">-->
<!--									<span class="like"></span>-->
<!--									<span class="dislike"></span>-->
<!--								</div>-->
							</article>
							<!-- Middle AD -->
							<c:if test="${i.count eq 2}">
								<section class="middleAd">
									<script type="text/javascript"><!--
										google_ad_client = "pub-9040087244579201";
										/* 468x60, criado 16/08/10 */
										google_ad_slot = "0951573583";
										google_ad_width = 468;
										google_ad_height = 60;
										//-->
										</script>
										<script type="text/javascript"
										src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
									</script>
								</section>
							</c:if>
							<!-- Middle AD end -->
						</c:forEach>
						<!--end user list-->
					</section>
					
					<!-- AD Lateral -->
					<section id="ad">
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
			</div>
		</div>
		<jsp:include page="footer.jsp" />
		<!-- LIGHTBOX -->
		<jsp:include page="lightbox.jsp" />
		<!--JAVASCRIPT-->
		<jsp:include page="javascripts.jsp" />
	</body>
</html>