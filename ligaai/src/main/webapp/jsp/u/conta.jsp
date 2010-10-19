<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %> 

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
		    <my:header/>
			<div class="containerShadow">
				<div id="userList">
					<section id="content">
						<c:if test="${empty param.monkey and msg == null}">
							<div class="unit avatar">
								<img src="/ligaai/avatar/original/${user.id}.jpg" />
							</div>
							<div class="unit userInfo account">
								<div class="unit userNameContainer">
									<h1 id="user" class="userName"><c:out value="${user.name}"></c:out></h1>
									<a href="#" id="editName">Editar</a>
								</div>
								<form id="changeNameForm" class="hide">
									<input type="text" id="newName" /><input type="submit" id="changeNameButton" value="Mudar Nome" />
								</form>
								<ul class="unit">
									<li><c:out value="${user.email}"></c:out></li>
									<li><a href="?monkey=x">Recortar avatar atual</a></li>
									<li><a href="#" id="changeAvatar">Trocar avatar</a></li>
									<li>
										<a href="#" id="changePassword">Mudar senha</a>
										<form id="changePasswordForm" class="hide">
											<input type="text" id="newPassword" /> <input type="submit" value="Mudar Senha" />
										</form>
									</li>
								</ul>
							</div>
						</c:if>
						<c:if test="${msg != null}">
							<p class="unit"><c:out value="${msg}" escapeXml="false"/></p>
						</c:if>
						<div id="avatarUpload" class="hide unit">
							<form method="post" action="/uploadAvatar.html" enctype="multipart/form-data">
								<input type="file" name="file"/> 
								<input type="submit" name="Upload" class="uploadAvatarbutton"/>
							</form>
						</div>
						<c:if test="${result or !empty param.monkey}">
							<div id=align="center"> 
				                <img src="/ligaai/avatar/original/${user.id}.jpg" id="cropbox" />
				                <div id="previewContainer" class="hide">
			                        <img src="/ligaai/avatar/original/${user.id}.jpg" id="preview" />
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
					</section>
					
					<!-- AD -->
					<my:side-ad />
					<!-- AD end-->
				</div>
			</div>
		</div>
		<my:footer/>
		<!-- LIGHTBOX -->
		<my:lightbox/>
		<!--JAVASCRIPT-->
		<script language="javascript" type="text/javascript" src="/js/jquery.Jcrop.min.js"></script>
	</body>
</html>
