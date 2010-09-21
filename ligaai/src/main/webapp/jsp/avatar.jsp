<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Upload a file please</title>
	</head>
	<body>
		<h1>Selecione sua foto</h1>
		<form method="post" action="/uploadAvatar.html" enctype="multipart/form-data">
			<input type="file" name="file"/> 
			<input type="submit" name="Upload"/>
		</form>
		<br/>
		<c:if test="${idUser != null}">
			<div  id="avatar"> 
				<table>
                <tr>
                <td>
                <img src="/ligaai/photos/${idUser}.jpg" id="jcrop_target" />
                </td>
                <td>
                <div style="width:100px;height:100px;overflow:hidden;">
                        <img src="/ligaai/photos/${idUser}.jpg" id="preview" />
                </div>

                </td>
                </tr>
                </table>
				
			</div> 
		</c:if>
		<c:if test="${msg != null}">
			<c:out value="${msg}" escapeXml="false"/>
		</c:if>
	</body>
	<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.jsonp-2.1.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.Jcrop.min.js"></script>
	<script type="text/javascript" src="/js/ligaai.js"></script>
	<script language="Javascript">
// Remember to invoke within jQuery(window).load(...)
// If you don't, Jcrop may not initialize properly
$(function(){

	$('#jcrop_target').Jcrop({
		onChange: showPreview,
		onSelect: showPreview,
		aspectRatio: 1
	});

});

// Our simple event handler, called from onChange and onSelect
// event handlers, as per the Jcrop invocation above
function showPreview(coords)
{
	if (parseInt(coords.w) > 0)
	{
		var rx = 100 / coords.w;
		var ry = 100 / coords.h;

		$('#preview').css({
			width: Math.round(rx * 500) + 'px',
			height: Math.round(ry * 370) + 'px',
			marginLeft: '-' + Math.round(rx * coords.x) + 'px',
			marginTop: '-' + Math.round(ry * coords.y) + 'px'
		});
	}
};

</script>
	
</html>
