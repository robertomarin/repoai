<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>Liga.ai</title>
		<meta charset="UTF-8"/>
		<meta name="title" content="Liga ai" />
		<meta name="description" content="Liga ai" />
		<meta name="keywords" content="Liga ai" /> 
		<link type="image/x-icon" href="/img/favicon.ico" rel="shortcut icon" />
		<!--css-->
		<link rel="stylesheet" href="/css/ligaai.css" />
		<!--[if lt IE 9]>
			<script src="/js/html5.js"></script>
		<![endif]-->
	</head>
	<body class="morningBackground">
		<div class="wrapper">
		    <jsp:include page="header.jsp" />
			<div class="containerShadow">
				<div id="userList">
					<section id="content">
						<c:if test="${idUser != null}">
							<div id="article"> 
				                <img src="/ligaai/photos/${idUser}.jpg" id="cropbox" />
				                <div id="previewContainer" class="hide">
			                        <img src="/ligaai/photos/${idUser}.jpg" id="preview" />
				                </div>
							</div>
							<form action="/cropAvatar.html" id="formCrop" onsubmit="return checkCoords();">
								<input type="hidden" name="x" id="x" />
								<input type="hidden" name="y" id="y" />
								<input type="hidden" name="w" id="w" />
								<input type="hidden" name="h" id="h" />
								<input type="submit" value="Enviar avatar" />
							</form>
						</c:if>
						<c:if test="${msg != null}">
							<c:out value="${msg}" escapeXml="false"/>
						</c:if>
					</section>
					
					<!-- AD -->
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
