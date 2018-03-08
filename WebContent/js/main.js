/**
 *  coding by    : jomin Kim of Team Shiva
 *  last update  : 18/02/13
 *  coding style : Object Oriented Programming Javascript
*/

/** Controller : /    =>    mainpage **/
// json API Ajax처리 (by only Javascript)
var nowUrl = location.href;
var mainPath = nowUrl.substr(nowUrl.length - 14, 14);
if (mainPath === "/ShivaProject/"){
	(function(){
		var rssAjax = function(json) {
			var data = JSON.parse(json.response);
			var li;

			if(data.DailyWeatherStation){
				li = "<li>"+data.DailyWeatherStation.row[0].STN_NM + "</li>" +
				 "<li>"+data.DailyWeatherStation.row[0].SAWS_OBS_TM + "</li>" +
				 "<li>"+data.DailyWeatherStation.row[0].SAWS_TA_MAX + "˚/" + data.DailyWeatherStation.row[0].SAWS_TA_MIN + "˚</li>";
				document.getElementById("ulWeather").innerHTML = li;			
			} else{				
				li = "<li>강남</li>" +
				"<li>"+data.RESULT.CODE+"</li>" +
				"<li style='font-size:14px'>"+data.RESULT.MESSAGE+"</li>";
				document.getElementById("ulWeather").innerHTML = li;			
			}
			

		}
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				rssAjax(this);
			}
		}
		xhttp.open("GET", "http://openapi.seoul.go.kr:8088/77545161716d73683132374955416741/json/DailyWeatherStation/2/11/2018/강남", true);
		xhttp.send();
	})();	
}


// 로그인 여부에 따른 스크립트 실행
var hasUserId = {
	excuteFalse : function() {
		$('.win8_btn_box a').on('click', function(e){
			alert("로그인 후 이용이 가능합니다");
		});
		$('.about_box_line a').on('click', function(e){
			alert("로그인 후 이용이 가능합니다");
		});		
		$('#linkNoti').on('click', function(e){
			alert("로그인 후 이용이 가능합니다");
		});		
	},
	excuteTrue : function() {
		$('.license_box a').attr('href', './main.lo');	
		$('.resume_box a').attr('href', './myResume.ro');	
		$('.used_box a').attr('href', './ListSaleController.uo');	
		$('.car_box a').attr('href', './main.co');
		
		$('#linkDetail1').attr('href', './main.lo');
		$('#linkDetail2').attr('href', './myResume.ro');
		$('#linkDetail3').attr('href', './ListSaleController.uo');		
		
		$('#linkNoti').attr('href', './boardList.bbs');		
	}
}


/** Controller : recentPost.shiva **/
// 최신 게시물 불러오기 (by jQuery)
var recentPostView = {
	excute : function() {
		
		$.ajax({
			type: 'POST',
			url: './recentPost.shiva',
			data: {},
			success: function(data) {
				//alert("돌아온 데이터"+data);
				var textArr = data.split('/');
				$('#usedPostCon1').text(textArr[0]);
				$('#usedPostCon2').text(textArr[1]+'님의 이력서');
				$('#usedPostCon3').text(textArr[2]);		
			
				for (var i = 0; i < textArr.length; i++) {
					if(textArr[i] == 'null' ) {
						$('#usedPostCon'+(i+1)).text('게시글이 없습니다');					
					}
				}

			}
		});	// ajax	
		
	} // excute()
};
recentPostView.excute();


/** Controller : recentPost.shiva **/
// 인기 게시물 불러오기 (by jQuery)
var hotPostView = {
	excute : function() {
		
		$.ajax({
			type: 'POST',
			url: './hotPost.shiva',
			data: {},
			success: function(data) {
				//console.log("돌아온 데이터 : "+data);
				var textArr = data.split('&');
				for (var i = 0; i < textArr.length; i++) {
					if(i==2 || i==3){
						$('.hot_post'+(i+1)).text(textArr[i]+'님의 이력서');												
					}else{
						$('.hot_post'+(i+1)).text(textArr[i]);						
					}
				}

			}
		});	// ajax	
		
	} // excute()
};
hotPostView.excute();


/** Controller : recentNotice.shiva **/
// 최근 공지사항 불러오기 (by jQuery)
var recentNoticeView = {
	excute : function() {
		
		$.ajax({
			type: 'POST',
			url: './recentNotice.shiva',
			data: {},
			success: function(data) {
				$('#linkNoti').text(data);
			}
		});	// ajax	
		
	} // excute()
};
recentNoticeView.excute();


// 로그인 유효성 검사
var modalValidator = {
	id : $('#frmLoginModal #id'),
	pw : $('#frmLoginModal #passwd'),
	excute : function() {
		var that = this;
		$('#btnLogin').on('click', function(){
			if(that.id.val()==''){
				alert('아이디를 입력해주세요');
				that.id.focus();
				return false;
			} else if (that.pw.val()=='') {
				alert('비밀번호를 입력해주세요');
				that.pw.focus();
				return false;
			} else{
				$(that.frm).submit();	
				return true;
			}				
		});
	}
};
modalValidator.excute();


/** Controller : memberjoin.shiva 
 *  literal Class : asyncValidator, pwValidator, nullValidator
 * **/
// Global Variable : 회원가입/수정의 유효성 검사들 통과여부 
var joinValiState = false;

// asyncValidator : 비동기로 아이디 중복 검사 (by jQuery)
var asyncValidator = {
	id : $('#frmMemberJoin #id'),
	excute : function() {
		var that = this;
		that.id.on('keyup', function(){	
			var desiredId = that.id.val();
			var msgCheckId = $('#msgCheckId');
			if(desiredId==='') {
				msgCheckId.text('아이디 입력해주세요');
				msgCheckId.attr("class","check_txt_check");
			} else {
				$.ajax({
					type: 'POST',
					url: './memberCheck.shiva',
					data: {'desiredId':desiredId},
					success: function(data) {
						// alert(data);
						if(data) {
							msgCheckId.text(data+'는 사용 가능한 아이디 입니다.');
							msgCheckId.attr("class","check_txt_basic");
							joinValiState=true;
						} else {
							msgCheckId.text('중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요');
							msgCheckId.attr("class","check_txt_check");
							joinValiState=false;
						}
					}
				});		
			}// if
		});				
	} // excute()
};
asyncValidator.excute();

// pwValidator : 비밀번호와 비번확인 일치여부 체크
var pwValidator = {
	excute : function() {
		var passCheck = function() {
			var msgCheckPw = $('#msgCheckPw');
			var pw1 = $('#passwd').val();
			var pw2 = $('#passwdChk').val();
			if(pw1 =='' && pw2 =='') {
				msgCheckPw.text('비밀번호를 입력해주세요');
				msgCheckPw.attr("class","check_txt_check");
				joinValiState=false;
			} else if(pw1 != pw2) {
				msgCheckPw.text('비밀번호가 일치하지 않습니다');
				msgCheckPw.attr("class","check_txt_check");
				joinValiState=false;
			} else if(pw1 == pw2) {
				msgCheckPw.text('비밀번호가 일치합니다');	
				msgCheckPw.attr("class","check_txt_basic");
				joinValiState=true;
			}
		}// passCheck()
		$('#passwd').on('keyup', passCheck);
		$('#passwdChk').on('keyup', passCheck);
	}
};
pwValidator.excute(); // 매개변수 받아서 하는걸로 변경


// nullValidator : 정규표현식 써서 형식(이메일,전화번호) 맞는지도 체크하기
var nullValidator = {
	//frm : '#frmMemberJoin',
	frm : '',
	excute : function(frm) {
		var that = this;
		this.frm = frm;
		$('#selectMail').on('change', function() {
			$('#mailDomain').val($(this).val());
		});
		$('#btnJoin').on('click', function() {
			if($(that.frm+' #id').val()=='') {
				alert('아이디를 입력해주세요');
				$(that.frm+' #id').focus();
				return false;
			} else if ($(that.frm+' #passwd').val()=='') {
				alert('비밀번호를 입력해주세요');
				$(that.frm+' #passwd').focus();
				return false;
			} else if ($(that.frm+' #passwdChk').val()=='') {
				alert('비밀번호 확인을 입력해주세요');
				$(that.frm+' #passwdChk').focus();
				return false;
			} else if ($(that.frm+' #name').val()=='') {
				alert('이름을 입력해주세요');
				$(that.frm+' #name').focus();
				return false;
			} else if ($(that.frm+' #genM').is(":checked")==false && $(that.frm+' #genW').is(":checked")==false) {
				alert('성별을 체크해주세요');
				$(that.frm+' #genM').focus();
				return false;
			} else if ($(that.frm+' #mail').val()=='') {
				alert('이메일을 입력해주세요');
				$(that.frm+' #mail').focus();
				return false;
			} else if ($(that.frm+' #mailDomain').val()=='') {
				alert('이메일 도메인을 입력 혹은 선택해주세요');
				$(that.frm+' #mailDomain').focus();
				return false;
			} else if ($(that.frm+' #phone').val()=='') {
				alert('전화번호를 입력해주세요');
				$(that.frm+' #phone').focus();
				return false;
			} else if (/^[0-9]*$/.exec($(that.frm+' #phone').val())==null) {	
				alert('전화번호는 숫자만 입력해주세요');
				$(that.frm+' #phone').focus();
				return false;
			} else if(joinValiState===true){
				$(that.frm).submit();	
				return true;
			}				
		});
	}
};
// 정규표현식으로 현재 컨트롤러 구분해서 nullValidator의 메서드 실행
if (/memberModify.shiva/.exec(nowUrl) != null){
	nullValidator.excute('#frmMemberModi');
} else if (/memberjoin.shiva/.exec(nowUrl) != null){
	nullValidator.excute('#frmMemberJoin');
}



/** Controller : mypage.shiva **/
// myPageClass : 회원정보 수정/삭제 클래스
var myPageClass = { 
	frmMypage : $('#frmMypage'),
	modifyMember : function() {
		var that = this;
		$('#btnMod').on('click', function(){
			that.frmMypage.attr('action','./memberModify.shiva');
			that.frmMypage.submit();
		});		
	},
	deleteMember : function() {
		var that = this;
		$('#btnDel').on('click', function(){
			if(confirm('정말 탈퇴하시겠습니까?')){
				that.frmMypage.attr('action','./memberDelete.shiva');
				that.frmMypage.submit();				
			}
		});		
	}
};
myPageClass.modifyMember();
myPageClass.deleteMember();

/** Controller :  boardWriteForm.bbs, boardUpdateForm.bbs **/
var writeValidator = {
	subjectLen : 3,
	contentLen : 7,
	excute : function() {
		var that = this;
 		$('#btnWrtSubmit').on('click', function() {
			if($('#wrtSubject').val().length<that.subjectLen) {
				alert('게시물 제목을 '+that.subjectLen+'자 이상 입력해주세요')
				return false;
			} else if(ckContent.getData().length<(that.contentLen+8)) {			
				alert('게시물 내용을 '+that.contentLen+'자 이상 입력해주세요')
				return false;	
			} 
		});
	}
}
writeValidator.excute();