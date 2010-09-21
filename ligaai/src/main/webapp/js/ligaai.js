/**
 * jQuery BASE64 functions
 * 
 * 	<code>
 * 		Encodes the given data with base64. 
 * 		String $.base64Encode ( String str )
 *		<br />
 * 		Decodes a base64 encoded data.
 * 		String $.base64Decode ( String str )
 * 	</code>
 * 
 * Encodes and Decodes the given data in base64.
 * This encoding is designed to make binary data survive transport through transport layers that are not 8-bit clean, such as mail bodies.
 * Base64-encoded data takes about 33% more space than the original data. 
 * This javascript code is used to encode / decode data using base64 (this encoding is designed to make binary data survive transport through transport layers that are not 8-bit clean). Script is fully compatible with UTF-8 encoding. You can use base64 encoded data as simple encryption mechanism.
 * If you plan using UTF-8 encoding in your project don't forget to set the page encoding to UTF-8 (Content-Type meta tag). 
 * This function orginally get from the WebToolkit and rewrite for using as the jQuery plugin.
 * 
 * Example
 * 	Code
 * 		<code>
 * 			$.base64Encode("I'm Persian."); 
 * 		</code>
 * 	Result
 * 		<code>
 * 			"SSdtIFBlcnNpYW4u"
 * 		</code>
 * 	Code
 * 		<code>
 * 			$.base64Decode("SSdtIFBlcnNpYW4u");
 * 		</code>
 * 	Result
 * 		<code>
 * 			"I'm Persian."
 * 		</code>
 * 
 * @alias Muhammad Hussein Fattahizadeh < muhammad [AT] semnanweb [DOT] com >
 * @link http://www.semnanweb.com/jquery-plugin/base64.html
 * @see http://www.webtoolkit.info/
 * @license http://www.gnu.org/licenses/gpl.html [GNU General Public License]
 * @param {jQuery} {base64Encode:function(input))
 * @param {jQuery} {base64Decode:function(input))
 * @return string
 */

(function(c){var b="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";var d=function(f){f=f.replace(/\x0d\x0a/g,"\x0a");var e="";for(var h=0;h<f.length;h++){var g=f.charCodeAt(h);if(g<128){e+=String.fromCharCode(g)}else{if((g>127)&&(g<2048)){e+=String.fromCharCode((g>>6)|192);e+=String.fromCharCode((g&63)|128)}else{e+=String.fromCharCode((g>>12)|224);e+=String.fromCharCode(((g>>6)&63)|128);e+=String.fromCharCode((g&63)|128)}}}return e};var a=function(e){var f="";var g=0;var h=c1=c2=0;while(g<e.length){h=e.charCodeAt(g);if(h<128){f+=String.fromCharCode(h);g++}else{if((h>191)&&(h<224)){c2=e.charCodeAt(g+1);f+=String.fromCharCode(((h&31)<<6)|(c2&63));g+=2}else{c2=e.charCodeAt(g+1);c3=e.charCodeAt(g+2);f+=String.fromCharCode(((h&15)<<12)|((c2&63)<<6)|(c3&63));g+=3}}}return f};c.extend({base64Encode:function(g){var e="";var o,m,k,n,l,j,h;var f=0;g=d(g);while(f<g.length){o=g.charCodeAt(f++);m=g.charCodeAt(f++);k=g.charCodeAt(f++);n=o>>2;l=((o&3)<<4)|(m>>4);j=((m&15)<<2)|(k>>6);h=k&63;if(isNaN(m)){j=h=64}else{if(isNaN(k)){h=64}}e=e+b.charAt(n)+b.charAt(l)+b.charAt(j)+b.charAt(h)}return e},base64Decode:function(g){var e="";var o,m,k;var n,l,j,h;var f=0;g=g.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(f<g.length){n=b.indexOf(g.charAt(f++));l=b.indexOf(g.charAt(f++));j=b.indexOf(g.charAt(f++));h=b.indexOf(g.charAt(f++));o=(n<<2)|(l>>4);m=((l&15)<<4)|(j>>2);k=((j&3)<<6)|h;e=e+String.fromCharCode(o);if(j!=64){e=e+String.fromCharCode(m)}if(h!=64){e=e+String.fromCharCode(k)}}e=a(e);return e}})})(jQuery);

/**
 * Cookie plugin
 *
 * Copyright (c) 2006 Klaus Hartl (stilbuero.de)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 **/

$.cookie=function(a,b,c){if(typeof b!='undefined'){c=c||{};if(b===null){b='';c.expires=-1}var d='';if(c.expires&&(typeof c.expires=='number'||c.expires.toUTCString)){var e;if(typeof c.expires=='number'){e=new Date();e.setTime(e.getTime()+(c.expires*24*60*60*1000))}else{e=c.expires}d='; expires='+e.toUTCString()}var f=c.path?'; path='+(c.path):'';var g=c.domain?'; domain='+(c.domain):'';var h=c.secure?'; secure':'';document.cookie=[a,'=',b,d,f,g,h].join('')}else{var j=null;if(document.cookie&&document.cookie!=''){var k=document.cookie.split(';');for(var i=0;i<k.length;i++){var l=jQuery.trim(k[i]);if(l.substring(0,a.length+1)==(a+'=')){j=l.substring(a.length+1);break}}}return j}};

$.unserialize=function(s){if(typeof s!='string') return false;params=s.split('&');ret={};for (param in params){p=params[param].split('=');if (p.length==1) p[1]='';ret[unescape(p[0])]=unescape(p[1]);}return ret;}

$.ajaxSetup({ scriptCharset: "utf-8" , contentType: "application/json; charset=utf-8"});
var encodeUrl = escape;
if(encodeURIComponent) {
	encodeUrl = encodeURIComponent;
}

$(function() {
	if($.cookie('u') != null){
		var x = $.cookie('u').replace(/"/g, '');
		var y = $.unserialize(x);
		y = $.base64Decode(y.u);
		y = eval("(" + y + ")");
	}

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
		if(usuarioLiga != null){
			var url = '/ligaai/criar?'
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
	
	$('#u_criar').submit(function() {
		var el = this;
		var url = '/u/criar?'
			+ 'name=' + encodeUrl($(this).find('#name').val())
			+ '&email=' + encodeUrl($(this).find('#email').val())
			+ '&password=' + encodeUrl($(this).find('#password').val());
		
		$.getJSON(url, function(data) {
			if(data.errors){
				for(i = 0; i < data.errors.length; i++) {
					$(el).parent().find('.message').html(data.errors[i].defaultMessage).fadeIn();
				};
			}else {
				$('#subscribe').dialog('close');
			}
		});
		
		return false;
	});
	
	$('#u_entrar').submit(function() {
		var el = this;
		var url = '/u/entrar?'
			+ 'email=' + encodeUrl($(this).find('#email').val())
			+ '&password=' + encodeUrl($(this).find('#password').val());
		
		$.getJSON(url, function(data) {
			if(data.errors){
				for(i = 0; i < data.errors.length; i++) {
					$(el).parent().find('.message').html(data.errors[i].defaultMessage).fadeIn();
				};
			}else {
				$('#subscribe').dialog('close');
			};
		});
		return false;
	});
	
	$('#u_entrar_topo').submit(function() {
		var el = this;
		var url = '/u/entrar?'
			+ 'email=' + encodeUrl($(this).find('#email').val())
			+ '&password=' + encodeUrl($(this).find('#password').val());
		
		$.getJSON(url, function(data) {
			if(data.errors){
				for(i = 0; i < data.errors.length; i++) {
					alert(data.errors[i].defaultMessage);
				};
			}else {
				alert('logou!');
			};
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

	 $('.contactInfo').focus(function(){
		$('#cloneable').clone(true).removeAttr("id").fadeIn(350).insertAfter($(this).parent());
		$(this).unbind('focus');
	 });
	 
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
			$('#u_entrar, #resetForm').hide();
			$('#u_criar').fadeIn();
		}
	});

	 
	 $('#login').click(function(){
		$('#u_criar').hide();
		$('#u_entrar').fadeIn();		
	 });
	 $('#register').click(function(){
		$('#u_entrar').hide();
		$('#u_criar').fadeIn();
	 });
	 $('#forgotPass').click(function(){
		$('#u_criar, #u_entrar').hide();
		$('#resetForm').fadeIn();		
	 });
	 
	 $('#lightboxRegister').live('click', function(){
		 $('#subscribe').dialog('open');
		 $('#u_entrar, #resetForm').hide();
		 $('#u_criar').show();
	 });
});