$(function() {
	$("#microurl").submit(function() {
		$.getJSON('/ajax/microurl/?url=' + $("#url").val(), function(data) {
			alert(data.microurl.url + "\n" + data.microurl.micro);
		});
		return false;
	});
});	