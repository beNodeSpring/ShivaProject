/**
 *  coding by jomin Kim of Team Shiva
 *  last update : 18/02/12
*/

/** mainpage **/
// API Ajax
var nowUrl = location.href;
var mainPath = nowUrl.substr(nowUrl.length - 14, 14);
if (mainPath === "/ShivaProject/"){
	(function(){
		var rssAjax = function(json) {
			var data = JSON.parse(json.response);
			var li;

			li = "<li>"+data.DailyWeatherStation.row[0].STN_NM + "</li>" +
				 "<li>"+data.DailyWeatherStation.row[0].SAWS_OBS_TM + "</li>" +
				 "<li>"+data.DailyWeatherStation.row[0].SAWS_TA_MAX + "˚/" + data.DailyWeatherStation.row[0].SAWS_TA_MIN + "˚</li>";
			document.getElementById("ulWeather").innerHTML = li;
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


/** memberjoin.shiva **/
// 정규표현식 써서 형식(이메일,전화번호) 맞는지도 체크하기
var joinValidator = {
	frm : '#frmMemberJoin',
	excute : function() {
		var that = this;
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
			} else{
				$(that.frm).submit();	
				return true;
			}				
		});
	}
};
joinValidator.excute();

/** mypage.shiva **/
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

/**  **/