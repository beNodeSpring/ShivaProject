/**
 *  coding by jomin Kim of Team Shiva
 *  last update : 18/02/12
*/
// mainpage
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



// mypage.shiva
var myPageClass = {
	frmMypage : $('#frmMypage'),
	modifyMember : function() {
		var that = this;
		$('#btnMod').on('click', function(){
			that.frmMypage.attr('action','./modify.shiva');
			that.frmMypage.submit();
		});		
	},
	deleteMember : function() {
		var that = this;
		$('#btnDel').on('click', function(){
			that.frmMypage.attr('action','./memberDelete.shiva');
			that.frmMypage.submit();
		});		
	}
};
myPageClass.modifyMember();
myPageClass.deleteMember();

//