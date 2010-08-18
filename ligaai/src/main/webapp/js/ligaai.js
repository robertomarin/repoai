
$.ajaxSetup({ scriptCharset: "utf-8" , contentType: "application/json; charset=utf-8"});
var encodeUrl = escape;
if(encodeURIComponent) {
	encodeUrl = encodeURIComponent;
}

$(function() {
	$('#microurl').submit(function() {
		if($.trim($('#url').val()) != ''){
			$('#microurlmicro').removeClass('copiedSuccessfully');
			$('#clickToCopy').html('Clique para copiar');
			var url = '/ajax/microurl?url=' + encodeUrl($('#url').val());
			$('#loader').show();
			$.getJSON(url, function(data) {
				if(data.microurl != null){
					$('#loader, .message').hide();
					var microurl = document.location.protocol + '//' + document.location.hostname + (document.location.port ? ':' + document.location.port : '') + '/' + data.microurl.micro;
					
					$('#doneShortenUrl').fadeIn();
					$('#microurlmicro').val(microurl);
					$('#twitter').attr('href', 'http://twitter.com/timeline/home?status=' + microurl);
					
					var savedChars = $('#url').val().length - microurl.length;
					
					if(savedChars <= 0){
						$('#savedChars').html('nenhum :(');
					} else{
						$('#savedChars').html(savedChars);
					}

					//Configurando copy to clipboard
			        var clipComplete = function(client){
			        	$('#microurlmicro').addClass('copiedSuccessfully');
			        	$('#clickToCopy').html('URL Copiada');
			        };

					ZeroClipboard.setMoviePath('/js/ZeroClipboard.swf');
					var clip = new ZeroClipboard.Client();
					clip.addEventListener('onComplete', clipComplete);
					clip.setText(microurl);
					clip.glue('microurlmicro');
				}else{
					$('.message').html('Ocorreu um erro na hora de encurtar :(').fadeIn();
					$('#loader').hide();
				}
				$('#microurlurl').html('<a href="' + data.microurl.url + '" target="_blank">' + data.microurl.url + '</a>');
			});
		}else{
			$('.message').fadeIn();
		}
		return false;
	});
	
	$('#message').blur(function(){
		var mVal = $(this).val();
		if(mVal == 'Me liga.ai ;-)' || $.trim(mVal) == ''){
			$(this).removeClass('typeMessage');
			 $(this).val('Me liga.ai ;-)');
		};
	});
	
	$('#message').focus(function(){
		var mVal = $(this).val();
		$(this).addClass('typeMessage');
		if(mVal == 'Me liga.ai ;-)'){
			$(this).val('');
		};
	});
	
	$('#ligaai').submit(function() {
		var usuario;
		if(usuario != null){
			var url = '/ajax/ligaai?'
				+ 'message=' + encodeUrl($('#message').val())
				+ '&contact=' + encodeUrl($('#contact').val())
				+ '&email=' + encodeUrl($('#email').val())
				+ '&contactType=' + encodeUrl($('.contactType').val());
			
			$.getJSON(url, function(data) {
				if(data.ok) {
					//var microurl = document.location.protocol + '//' + document.location.hostname + '/' + data.microurl.micro;
					$('#content article:first').prepend('<article><div class="userPic">foto</div><div class="userInfo"><header><hgroup><h1>Nome do rebento</h1><h2>' + $('#contact').val() + '</h2></hgroup></header></div></article>');
				} else {
					alert('bug?');
				}
			});
		} else{
			$('#subscribe').dialog('open');
		}
		
		return false;
	});
	
	$('#registerForm').submit(function() {
		var url = '/ajax/user/create?'
			+ 'email=' + encodeUrl($('#email').val())
			+ '&password=' + encodeUrl($('#password').val());
		
		$.getJSON(url, function(data) {
			for(i = 0; i < data.errors.length; i++) {
				alert(data.errors[i].defaultMessage)
			}
		});
		
		return false;
	});
	
	 /*!
	  * About
	 */
	 
	 $('#aboutText').click(function(){
		$('#aboutContent').slideToggle('slow');
	 });
	 
	 /*!
	  * Input clone
	 */

	 /*$('.contactInfo').focus(function(){
		$('#cloneable').clone(true).removeAttr("id").fadeIn(350).insertAfter($(this).parent());
		$(this).unbind('focus');
	 });*/
	 
	 /*!
	  * Input masking
	 */
	 //$('.phone').mask('(99) 9999-9999');
	 
	 $('.contactType').each(function(){
		 $(this).change(function(){
			 if($(this).val() == 'PHONE') {
				 $(this).parent().find('.contactInfo').mask('(99) 9999-9999'); 
			  } else{
				 $(this).parent().find('.contactInfo').unmask(); 
			  };
		 });
	 });
	 
	 /**Subscribe dialog**/
	 $('#subscribe').dialog({
		bgiframe: true,
		autoOpen: false,
		width: 445,
		height: 'auto',
		modal: true,
		closeText: 'X',
		show: 'slow',
		open: function() {
			$('.ui-widget-overlay').click(function(){$('#subscribe').dialog('close');});
		},
	 	close: function() {
			$('#loginForm, #resetForm').hide();
			$('#registerForm').fadeIn();
		}
	});

	 
	 $('#login').click(function(){
		$('#registerForm').hide();
		$('#loginForm').fadeIn();		
	 });
	 $('#register').click(function(){
		$('#loginForm').hide();
		$('#registerForm').fadeIn();
	 });
	 $('#logo').click(function(){
		 $('#subscribe').dialog('open');
		 $('#loginForm').hide();
		 $('#registerForm').fadeIn();
	 });
	 $('#forgotPass').click(function(){
		$('#registerForm, #loginForm').hide();
		$('#resetForm').fadeIn();		
	 });
});