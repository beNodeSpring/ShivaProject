$(document).ready(function(){
	var temp = 0;
	
	$("#tf_button").click(function(){
		$("#tf_text").prop("disabled", false);
	});
	
	$("#tf_text").focusout(function(){
		$("#tf_text").prop("disabled", true);
	});
	
	$("#work_add").click(function(){
		if($("#worktable1 tbody tr").length < 9){
			$("#worktable1 tbody")
				.prepend("<tr style ='height : 5vh;'>" +
						"	<td style = 'border-bottom:1px solid skyblue'>" +
						"		<input type = 'text' value = 'Project Name'" +
						" name = 'PROJECT" + ($("#worktable1 tbody tr").length+1) + "'" +
						" style = 'background-color:transparent; border:none;'>" +
						"	</td>" +
						"	<td style = 'border-bottom:1px solid skyblue'>" +
						"		<input type = 'text' value = 'Duration' " +
						" name = 'DURATION" + ($("#worktable1 tbody tr").length+1) + "'" +
						" style = 'background-color:transparent; border:none;'>" +
						"	</td>" +
						"</tr>");
			
			$("#worktable1 tbody tr:first td").click(function(){
				$("#worktable1").hide();
				$("#work_remove").hide();
				$("#work_add").hide();
				$("#update_button").hide();
				$("#board_button").hide();
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
						"		' style = 'background-color:transparent; border:none;'>" +
						"	</td>" +
						"	<td style = 'border-bottom:1px solid skyblue'>" +
						"		<input type = 'text' id = 'temp2' value = '"+
									$("#worktable1 tbody tr:nth-child(" + (row + 1) + ") td:nth-child(2) input").val() + 		
						"		' style = 'background-color:transparent; border:none;'>" +
						"	</td>" +
						"</tr>");
				$("#worktable2 tbody tr td textarea:nth-child(" + temp + ")").show();
				temp = row + 1;
			});
		}
		else{
			alert("더이상 늘릴 수 없습니다.");
		}
	});
	
	$("#work_remove").click(function(){
		var length = $("#worktable1 tbody tr").length;
		$("#worktable2 tbody tr td textarea:nth-child(" + length + ")").val("Fill In");
		$("#worktable1 tbody tr:first").remove();
	});
	
	$("#work_return").click(function(){
		$("#worktable1").show();
		$("#work_remove").show();
		$("#work_add").show();
		$("#update_button").show();
		$("#board_button").show();
		$("#worktable2").hide();
		$("#work_return").hide();
		$("#worktable2 tbody tr td textarea").hide();
		$("#worktable1 tbody tr:nth-child(" + temp + ") td:nth-child(1) input").val($("#temp1").val());
		$("#worktable1 tbody tr:nth-child(" + temp + ") td:nth-child(2) input").val($("#temp2").val());
		$("#worktable2 tbody tr:first").remove();
	});
	
	$("#update_button").click(function(){
		$("#tf_text").prop("disabled", false);
		resumeform.submit();
	})
	
	$("#board_button").click(function(){
		///ShivaProject/ResumeList.rolocation.href = "/ShivaProject/ResumeList.ro";
	})
	
	$("#worktable1 tbody tr td").click(function(){
		$("#worktable1").hide();
		$("#work_remove").hide();
		$("#work_add").hide();
		$("#update_button").hide();
		$("#board_button").hide();
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
				"		' style = 'background-color:transparent; border:none;'>" +
				"	</td>" +
				"	<td style = 'border-bottom:1px solid skyblue'>" +
				"		<input type = 'text' id = 'temp2' value = '"+
							$("#worktable1 tbody tr:nth-child(" + (row + 1) + ") td:nth-child(2) input").val() + 		
				"		' style = 'background-color:transparent; border:none;'>" +
				"	</td>" +
				"</tr>");
		$("#worktable2 tbody tr td textarea:nth-child(" + temp + ")").show();
		temp = row + 1;
	});
	
	$("#Java").click(function(){
		if ($("#JavaVal").val() == "true"){
			$("#Java").css("background-color", "transparent");
			$("#JavaVal").val("false");
		} else {
			$("#Java").css("background-color", "tomato");
			$("#JavaVal").val("true");
		}
	});
	
	$("#Python").click(function(){
		if ($("#PythonVal").val() == "true"){
			$("#Python").css("background-color", "transparent");
			$("#PythonVal").val("false");
		} else {
			$("#Python").css("background-color", "tomato");
			$("#PythonVal").val("true");
		}
	});
	
	$("#C").click(function(){
		if ($("#CVal").val() == "true"){
			$("#C").css("background-color", "transparent");
			$("#CVal").val("false");
		} else {
			$("#C").css("background-color", "tomato");
			$("#CVal").val("true");
		}
	});
	
	$("#Ruby").click(function(){
		if ($("#RubyVal").val() == "true"){
			$("#Ruby").css("background-color", "transparent");
			$("#RubyVal").val("false");
		} else {
			$("#Ruby").css("background-color", "tomato");
			$("#RubyVal").val("true");
		}
	});
	
	$("#JavaScript").click(function(){
		if ($("#JavaScriptVal").val() == "true"){
			$("#JavaScript").css("background-color", "transparent");
			$("#JavaScriptVal").val("false");
		} else {
			$("#JavaScript").css("background-color", "tomato");
			$("#JavaScriptVal").val("true");
		}
	});
	
	$("#Cshap").click(function(){
		if ($("#CshapVal").val() == "true"){
			$("#Cshap").css("background-color", "transparent");
			$("#CshapVal").val("false");
		} else {
			$("#Cshap").css("background-color", "tomato");
			$("#CshapVal").val("true");
		}
	});
	
	$("#PHP").click(function(){
		if ($("#PHPVal").val() == "true"){
			$("#PHP").css("background-color", "transparent");
			$("#PHPVal").val("false");
		} else {
			$("#PHP").css("background-color", "tomato");
			$("#PHPVal").val("true");
		}
	});
	
	$("#Objective-C").click(function(){
		if ($("#Objective-CVal").val() == "true"){
			$("#Objective-C").css("background-color", "transparent");
			$("#Objective-CVal").val("false");
		} else {
			$("#Objective-C").css("background-color", "tomato");
			$("#Objective-CVal").val("true");
		}
	});
	
	$("#SQL").click(function(){
		if ($("#SQLVal").val() == "true"){
			$("#SQL").css("background-color", "transparent");
			$("#SQLVal").val("false");
		} else {
			$("#SQL").css("background-color", "tomato");
			$("#SQLVal").val("true");
		}
	});
	
	$("#Cplus").click(function(){
		if ($("#CplusVal").val() == "true"){
			$("#Cplus").css("background-color", "transparent");
			$("#CplusVal").val("false");
		} else {
			$("#Cplus").css("background-color", "tomato");
			$("#CplusVal").val("true");
		}
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