// API Ajax
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


//