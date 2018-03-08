$(document).ready(function(){
	var temp = 0;
	
	$("#tf_button").click(function(){
		$("#tf_text").prop("disabled", false);
	});
	
	$("#tf_text").focusout(function(){
		$("#tf_text").prop("disabled", true);
	});
	
	$("#board_button").click(function(){
		window.location.href = "/ShivaProject/ResumeList.ro";
	});
	
	$("#worktable1 tbody tr td").click(function(){
		$("#worktable1").hide();
		$("#work_remove").hide();
		$("#work_add").hide();
		$("#worktable2").show();
		$("#work_return").show();
		var length = $("#worktable1 tbody tr").length;
		var row = $(this).parent().parent().children().index($(this).parent());
		temp = length-row;
		$("#worktable2 tbody")
		.prepend("<tr style ='height : 5vh;'>" +
				"	<td style = 'border-bottom:1px solid skyblue'>" +
				"		<input type = 'text' id = 'temp1' value = '" +
							$("#worktable1 tbody tr:nth-child(" + (row + 1) + ") td:nth-child(1) input").val() +
				"		' style = 'background-color:transparent; border:none;' readonly>" +
				"	</td>" +
				"	<td style = 'border-bottom:1px solid skyblue'>" +
				"		<input type = 'text' id = 'temp2' value = '"+
							$("#worktable1 tbody tr:nth-child(" + (row + 1) + ") td:nth-child(2) input").val() + 		
				"		' style = 'background-color:transparent; border:none;' readonly>" +
				"	</td>" +
				"</tr>");
		$("#worktable2 tbody tr td textarea:nth-child(" + temp + ")").show();
		temp = row + 1;
	});
	
	$("#work_return").click(function(){
		$("#worktable1").show();
		$("#work_remove").show();
		$("#work_add").show();
		$("#worktable2").hide();
		$("#work_return").hide();
		$("#worktable2 tbody tr td textarea").hide();
		$("#worktable1 tbody tr:nth-child(" + temp + ") td:nth-child(1) input").val($("#temp1").val());
		$("#worktable1 tbody tr:nth-child(" + temp + ") td:nth-child(2) input").val($("#temp2").val());
		$("#worktable2 tbody tr:first").remove();
	});
	
	if ($("#JavaVal").val() == "true"){
		$("#Java").css("background-color", "tomato");
	} else {
		$("#Java").css("background-color", "transparent");
	}
	
	if ($("#PythonVal").val() == "true"){
		$("#Python").css("background-color", "tomato");
	} else {
			$("#Python").css("background-color", "transparent");
	}
	
	if ($("#CVal").val() == "true"){
		$("#C").css("background-color", "tomato");
	} else {
		$("#C").css("background-color", "transparent");
	}

	if ($("#RubyVal").val() == "true"){
		$("#Ruby").css("background-color", "tomato");
	} else {
		$("#Ruby").css("background-color", "transparent");
	}

	if ($("#JavaScriptVal").val() == "true"){
		$("#JavaScript").css("background-color", "tomato");
	} else {
		$("#JavaScript").css("background-color", "transparent");
	}

	if ($("#CshapVal").val() == "true"){
		$("#Cshap").css("background-color", "tomato");
	} else {
		$("#Cshap").css("background-color", "transparent");
	}

	if ($("#PHPVal").val() == "true"){
		$("#PHP").css("background-color", "tomato");
	} else {
		$("#PHP").css("background-color", "transparent");
	}

	if ($("#Objective-CVal").val() == "true"){
		$("#Objective-C").css("background-color", "tomato");
	} else {
		$("#Objective-C").css("background-color", "transparent");
	}

	if ($("#SQLVal").val() == "true"){
		$("#SQL").css("background-color", "tomato");
	} else {
		$("#SQL").css("background-color", "transparent");
	}

	if ($("#CplusVal").val() == "true"){
		$("#Cplus").css("background-color", "tomato");
	} else {
		$("#Cplus").css("background-color", "transparent");
	}
});