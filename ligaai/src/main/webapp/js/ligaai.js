
$.ajaxSetup({ scriptCharset: "utf-8" , contentType: "application/json; charset=utf-8"});

$(function() {
	$('#microurl').submit(function() {
		var url = '/ajax/microurl?url=' + $('#url').val();
		
		$.getJSON(url, function(data) {
			var microurl = document.location.protocol + '//' + document.location.hostname + '/' + data.microurl.micro;
			
			$('#microurlmicro').html('<a href="' + microurl + '" target="_blank">' + microurl + '</a>');
			$('#microurlurl').html('<a href="' + data.microurl.url + '" target="_blank">' + data.microurl.url + '</a>');
		});
		
		return false;
	});
	
	$('#ligaai').submit(function() {
		var url = '/ajax/ligaai?'
			+ 'message=' + $('#message').val()
			+ '&contact=' + $('#contact').val()
			+ '&contactType=' + $('#contactType').val();
		
		$.getJSON(url, function(data) {
			if(data.ok) {
				var microurl = document.location.protocol + '//' + document.location.hostname + '/' + data.microurl.micro;
			} else {
				alert('bug?');
			}
		});
		
		return false;
	});
});