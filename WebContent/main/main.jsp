<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>개발의 시발점</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="/ShivaProject/css/style.css" rel="stylesheet">
<link href="/ShivaProject/css/sub.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">    
<!-- JavaScript -->
<script src="/ShivaProject/js/jquery-3.3.1.min.js"></script> 
<!--<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script> -->
<script src="/ShivaProject/js/bootstrap.min.js"></script>
<script src="/ShivaProject/js/main.js"></script>
</head>
<%--
response.addHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");
--%>
<body>
    <div class="main-body">	
        <div class="container">
            <div class="row">               
                <div class="main-page">

                    <aside class="main-navigation">
                        <div class="main-menu">

                            <div class="menu-container">
                                <div class="block-keep-ratio block-keep-ratio-2-1 block-width-full home">
                                	<h1 class="logo_h1"><img src="/ShivaProject/images/logo_h1.png" alt="개발이 시발점 로고" /><span>개발의 시발점</span></h1>                                    
                                    <ul class="home_menu cf">
                                    	<c:set var="userId" value="${sessionScope.id}" />
                                    	<c:choose>
											<c:when test="${userId == null}">
	                                    		<li><a href="#" data-target="#layerpop" data-toggle="modal" class="">Login / Join</a></li>
											</c:when>
	                                    	<c:otherwise>
		                                    	<li><span class="ss_user_id"><c:out value='${sessionScope.id}'/>님</span></li>
		                                    	<li><a href="./main/mypage.jsp" class="">Mypage</a></li>
	                                    	</c:otherwise>
                                    	</c:choose>
                                    </ul>                             
                                </div>                                
                            </div>

							<!-- 로그인 모달창 -->
							<div class="modal fade" id="layerpop" >
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<!-- 닫기(x) 버튼 -->
											<button type="button" class="close" data-dismiss="modal">×</button>
											<!-- header title -->
											<h4 class="modal-title">로그인</h4>
										</div>
										
										<form action="./memberLogin.shiva" method="post" >
											<div class="modal-body">
												<!-- 로그인 폼 -->
													<div class="form-group">
													    <label for="id">ID</label>
													    <input type="text" class="form-control" size="10" name="id" id="id">
													</div>
													<div class="form-group">
													    <label for="pass">비밀번호</label>
													    <input type="password" class="form-control" size="10" name="passwd" id="passwd">
													</div>
											</div>
							
											<div class="modal-footer">
												<a href="./main/memberInsert.jsp" class="btn btn-warning">회원가입</a>
												<button type="submit" class="btn btn-primary">로그인</button>
											</div>
										</form>
									</div>
								</div>
							</div>
							<!--// 로그인 모달창 -->

                            <div class="menu-container margin-b-30">                                
                                <div class="block-keep-ratio  block-keep-ratio-1-1  block-width-half  pull-left license_box">                                    
                                    <a href="about.html" class="main-menu-link block-keep-ratio__content flexbox-center license">
                                        <i class="fa fa-drivers-license fa-4x main-menu-link-icon"></i>
                                        IT 자격증
                                    </a>                                    
                                </div>

                                <div class="block-keep-ratio  block-keep-ratio-1-1  block-width-half  pull-right resume_box">
                                    <a href="contact.html" class="main-menu-link block-keep-ratio__content flexbox-center resume">
                                        <i class="fa fa-file-text-o fa-4x main-menu-link-icon"></i>
                                        IT 이력서
                                    </a>                                
                                </div>  
 
                                <div class="block-keep-ratio  block-keep-ratio-1-1  block-width-half  pull-left used_box">                                    
                                    <a href="about.html" class="main-menu-link block-keep-ratio__content flexbox-center used">
                                        <i class="fa fa-handshake-o fa-4x main-menu-link-icon"></i>
										IT 중고
                                    </a>                                    
                                </div>
                                  
                                <div class="block-keep-ratio  block-keep-ratio-1-1  block-width-half  pull-right car_box">
                                    <a href="contact.html" class="main-menu-link block-keep-ratio__content flexbox-center car">
                                        <i class="fa fa-automobile fa-4x main-menu-link-icon"></i>
                                        IT 렌트카
                                    </a>                                
                                </div>    
                            </div>   

                            <div class="menu-container">
                                <div class="block-keep-ratio block-keep-ratio-1-1 block-keep-ratio-md-2-1 block-width-full weather_bax">                                    
                                    <a href="gallery.html" class="main-menu-link  block-keep-ratio__content">
                                        <span class="main-menu-link-text">
                                            Weather  
                                        </span>
                                        <ul id="ulWeather" class="ul_weather">
                                        </ul>                                        
                                    </a>                                    
                                </div>                                
                            </div>

<script>
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
	xhttp.open("GET", "http://openapi.seoul.go.kr:8088/77545161716d73683132374955416741/json/DailyWeatherStation/2/7/2018/강남", true);
	xhttp.send();
})();
</script>

                            <!-- sidebar carousel -->
                            <div class="menu-container">
                                <div class="mauris">
                                    <div id="carousel-menu" class="carousel slide" data-ride="carousel">
                                        <!-- Wrapper for slides -->
                                        <div class="carousel-inner" role="listbox">
                                            <div class="item active">
                                                <!-- <img src="/ShivaProject/images/slider-img-1.png" alt="slider"> -->
                                                <div class="carousel-caption menu-caption">
                                                    	가상 화폐 몰락
                                                </div>
                                            </div>
                                            <div class="item">
                                                <!-- <img src="/ShivaProject/images/slider-img-1.png" alt="slider"> -->
                                                <div class="carousel-caption menu-caption">
                                                    	국내 모바일 웹에도 SPA가 대세가 될 것인가?
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Controls -->
                                        <a class="left carousel-control" href="#carousel-menu" role="button" data-slide="prev">
                                            <span class="fa fa-caret-left" aria-hidden="true"></span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <a class="right carousel-control" href="#carousel-menu" role="button" data-slide="next">
                                            <span class="fa fa-caret-right" aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </div>
                                </div> <!-- .mauris -->
                            </div>
                        </div> <!-- main-menu -->
                    </aside> <!-- main-navigation -->
                    
                    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>

                    <div class="content-main">
 
                                               <div class="row about-box-main">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                                <div class="cards">
                                    
                                    <div class="box about_box_line card">
                                        <div class="box-icon">
                                            <img src="/ShivaProject/images/about-img-6.jpg" alt="Image" class="img-responsive">
                                            <div class="inner_search">
                                                <div class="search_icon text-center Absolute-Center" data-toggle="modal" data-target="#myModal"><i class="fa fa-search"></i></div>
                                            </div> <!-- .inner_search -->
                                        </div> <!-- .box-icon -->
                                        <div class="info">
                                            <div class="about_box_title">
                                                <h3>IT 자격증</h3>
                                                <h4>정보처리기사 시험일정</h4>
                                            </div>
                                        </div> <!-- .info -->
                                        <div class="line"></div>
                                        <p class="about_box_text">자세히 보기</p>
                                    </div>
                                
                                    <div class="box about_box_line card">
                                        <div class="box-icon">
                                            <img src="/ShivaProject/images/about-img-7.jpg" alt="Image" class="img-responsive">
                                            <div class="inner_search">
                                                <div class="search_icon text-center Absolute-Center" data-toggle="modal" data-target="#myModal2"><i class="fa fa-search"></i></div>
                                            </div>
                                        </div>
                                        <div class="info">
                                            <div class="about_box_title">
                                                <h3>IT 이력서</h3>
                                                <h4>삼성SDS 공채용 이력서</h4>
                                            </div>
                                        </div>
                                        <div class="line"></div>
                                        <p class="about_box_text">자세히보기</p>
                                    </div>

                                    <div class="box about_box_line card">
                                        <div class="box-icon">
                                            <img src="/ShivaProject/images/about-img-8.jpg" alt="Image" class="img-responsive">
                                            <div class="inner_search">
                                                <div class="search_icon text-center Absolute-Center" data-toggle="modal" data-target="#myModal3"><i class="fa fa-search"></i></div>
                                            </div> <!-- .inner_search -->
                                        </div> <!-- .box-icon -->
                                        <div class="info">
                                            <div class="about_box_title">
                                                <h3>IT 중고</h3>
                                                <h4>LG그램 2017 S급 판매</h4>
                                            </div>
                                        </div>
                                        <div class="line"></div>
                                        <p class="about_box_text">자세히보기</p>
                                    </div>
                                      
                                </div>
                                
                            </div>
                            
                        </div>
						<!-- 배너
                        <div class="row margin-b-30">
                            <div class="col-xs-12 col-sm-12 col-md-12 biliend">
                                <div class="banner-2-container">
                                    <div class="aenean">
                                        <h4>Aenean sotin </h4>
                                        <h3>biliend</h3>
                                    </div>    
                                </div>                                
                            </div>
                        </div>
						 -->
                        <!-- <div class="row"> -->
                            <div class="about-last-container">
                                <!-- <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"> -->
                                   <div class="box bottom-main info-nav-container">
                                        <ul class="info info-ul">
                                            <li><a href="#" class="about_proin current">추천 게시물</a></li>
                                            <li><a href="#" class="about_proin">개발 잘하는 법</a></li>
                                            <li><a href="#" class="about_proin">많이 하면 됨</a></li>
                                            <li><a href="#" class="about_proin">디자인 잘하는 법</a></li>
                                            <li><a href="#" class="about_proin">타고 나야 됨</a></li>
                                            <li><a href="#" class="about_proin">기획 잘하는 법</a></li> 
                                            <li><a href="#" class="about_proin">디자인+개발 할줄 알면 됨</a></li>
                                            <li><a href="#" class="about_proin">이런게 추천글이라니..</a></li>
                                        </ul>
                                    </div>
                                <!-- </div> -->    
                                <!-- <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8"> -->
                                    <div class="box bottom-main info-description-container">
                                        <div class="info">
                                            <div class="proin-title">
                                                <h3>공지사항</h3>
                                                <h4></h4>
                                            </div>                                       
                                            <img src="/ShivaProject/images/about-img-5.png" alt="Image" class="img-responsive">
                                            <p class="proin-right-text">만들어야 될꺼 같은데 시간이 부족해서 만들수 있을려나 모르겠습니다. 시바!</p>
                                        </div> <!-- .info -->
                                    </div>
                                <!-- </div> -->
                            </div>
                        <!-- </div> -->
                    </div>
                </div>
            </div>
            <footer class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 footer">
                    <p class="copyright">Copyright © 2016 Company Name
                    
                    | More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
                </div>    
            </footer>            
        </div>
    </div>

</body>
</html>
