$(document).ready(function(){
	
	$("#resumetable tr td").click(function(){
		var id = $(this).parent().find("td:first").text();
	});
});