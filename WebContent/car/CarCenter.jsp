<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<title>시바렌트카</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="/ShivaProject/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
* {margin: 0;	padding: 0}
li {list-style: none;}
a {text-decoration: none;}
body {background: url(./car/images/bg_main-body5.jpg) 0 0 no-repeat; background-size: cover;}
#button1 {background: orange;}
#button2 {background: linear-gradient(to right, #0099ab 0%,#259daa 100%);}
#button3 {background: linear-gradient(to right, #9d00a5 0%,#9d2fa3 100%);}
#button4 {background: linear-gradient(to right, #bd1c47 0%,#bc365a 100%);}
#button5 {background: linear-gradient(to right, #603cba 0%,#694bb7 100%);}
.button {
	color: #fff;
	display: block;
	width: 100%;
	height: 100px;
	line-height: 100px;
	text-align: center;
	font-weight: bold;
	font-size: 14pt;
}

.car_section{margin-top: 60px;}

.top_sec{}
.top_sec ul{list-style:none; overflow:auto;}
.top_sec ul li{float:left; width: 20%;}

.content_sec{}

.trade_input_box{margin: 50px auto; padding: 27px 38px; width: 460px; height: 359px; box-sizing: border-box; background: url(./car/images/bg_trade_input_box.png) 0 0 no-repeat;}
.trade_input_box li{}
.trade_input_box li:nth-child(1){margin-bottom: 10px;}
.trade_input_box li:nth-child(2){margin-bottom: 18px;}
.trade_input_box li:nth-child(3){margin-bottom: 10px;}
.trade_input_box li:nth-child(4){margin-bottom: 5px;}
.trade_input_box li:nth-child(5){margin-bottom: 15px;}
.trade_input_box li:nth-child(6){margin-bottom: 6px;}
.trade_input_box li:nth-child(7){}
.trade_input_box li select{width:100%; padding-left:5px; height: 35px; font-size: 14px; border: 1px solid #096099; color:#888; }
.trade_input_box li .trade_tit{font-size: 14px; color: #fff;}
.trade_input_box li .trade_tit em{margin-right: 16px; font-size: 18px; color: #00fffc;}
.trade_input_box li input[type="text"]{height: 35px; font-size: 14px; border: 1px solid #096099;}
.trade_input_box li input[type="checkbox"]{margin-right: 8px;}
.trade_input_box li p{line-height: 1.5; font-size: 13px; letter-spacing: -0.25px; color: #fff;}
.trade_input_box li .view_preamble{float: right; display: inline-block; width: 76px; height: 21px; line-height: 21px; text-align: center; color: #fff; font-size: 14px; border: 2px solid #fff;}
.trade_input_box li .btn_apply{width: 100%; padding-left: 120px; height: 53px; text-align: left; font-size: 22px; font-weight:bold; color: #fff; box-shadow: 2px 3px 0px rgba(1, 1, 1, 0.35); background: #00ccff url(../../images/main/ico_btn_apply.png) 82px 50% no-repeat; border: 1px solid #0e69a4;}

.boardlist{padding: 60px;}
.pd60{padding: 60px;}

/*슬라이드 게시판 모듈화*/
.slide_board{margin: auto; max-width:800px; border-top: 2px solid #337ab7; border-right: 1px solid #c2c2c4; border-left: 1px solid #c2c2c4;}
.slide_board .table_ul{}
.slide_board .table_ul>li{ border-bottom: 1px solid #c2c2c4; background: url('../../images/info/ico_q.gif') 26px 19px no-repeat; }
.slide_board .table_ul>li>a{display: block; padding: 17px 8px 14px 57px; font-size: 14px; color: #000; background: url(../../images/info/ico_list_view.png) 97% center no-repeat; }
.slide_board .table_ul>li.on>a{border-bottom: 1px solid #828282;}
.slide_board .table_ul>li:first-child a{b order-bottom: 1px solid #000;}
.slide_board .table_ul>li .inner_ul_txt{display: none; }
.slide_board .table_ul>li .inner_ul_txt li{font-size: 14px; line-height: 1.5;  color: #444; background-color: #f9f9f9; word-break: keep-all; background: url(../../images/info/ico_a.gif) 26px 25px no-repeat;}
.slide_board .table_ul>li .inner_ul_txt li li{background: none;}
.slide_board .table_ul>li .inner_ul_txt li > div{padding: 20px 57px 25px 57px;}
.slide_board .table_ul>li:first-child .inner_ul_txt{display: block;}

.mb30{margin-bottom: 30px;}
.mb50{margin-bottom: 50px;}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(function(){
	$('.table_ul>li').eq(0).addClass('on');

	$('.table_ul>li>a').on('click', function(){

		var thisParent = $(this).parent('li');
		
		if (thisParent.hasClass('on')) {
			thisParent.removeClass('on');
			$(this).css({"background":"url(../images/info/ico_list_view.png)", 'background-repeat' : 'no-repeat', 'background-position':'97% 50%'});
			thisParent.find('ul').slideUp(200);
		}
		else {
			thisParent.addClass('on');
			$(this).css({"background":"url(../images/info/ico_list_view_on.png)", 'background-repeat' : 'no-repeat', 'background-position':'97% 50%'});
			thisParent.siblings().children('a').css({"background":"..//images/info/ico_list_view.png)", 'background-repeat' : 'no-repeat', 'background-position':'97% 50%'});
			thisParent.children('ul').slideDown(200);
			thisParent.siblings('li').children('ul').slideUp(200);
			thisParent.siblings('li').removeClass('on');
		}
		return false;
	});
});//end_function
</script>
</head>
<body>
	<jsp:include page="/inc/header.jsp"></jsp:include>
	<div class="car_section">
		<div class="container">
			<div class="row">
				<div class="modal-content">

					<div class="top_section cf">
						<div class="top_sec">
							<ul>
								<li><a href="./main.co" id=button1 class="button">예약하기</a></li>
								<li><a href="#" id=button2 class="button">예약확인</a></li>
								<li><a href="./CarBoardList.co" id=button3 class="button">자유게시판</a></li>
								<li><a href="./CarEvent.co" id=button4 class="button">이벤트</a></li>
								<li><a href="./CarCenter.co" id=button5 class="button">고객센터</a></li>
							</ul>
						</div>

						<div class="content_sec">
						
							<div class="pd60">
			
								<h3 class="mb30">자주 묻는 질문</h3>
								<div class="slide_board mb50">
									<ul class="table_ul">
										<li>
											<a class="ob_zoomInOut" href="#"><strong>01. 차량을 예약하려면 무엇이 필요한가요?</strong></a>
											<ul class="inner_ul_txt">
												<li>
													<div class="fix">
														<div class="ob_zoomInOut">차량을 예약하는 데는 신용카드나 체크카드만 있으면 됩니다. 하지만 현지에서 차량을 인수할 때는 다음 목록을 모두 지참하셔야 합니다.</div>
													</div>
												</li>
											</ul>
										</li>
										<li>
											<a class="ob_zoomInOut" href="#"><strong>02. 차량을 렌트할 수 있는 최소연령은 어떻게 되나요??</strong></a>
											<ul class="inner_ul_txt">
												<li>
													<div class="fix">
														<div class="ob_zoomInOut"> 일반적으로 차량을 렌트할 수 연령대는 21세에서 70세 사이이며, 25세 이하와 70세 이상인 운전자에게는 추가요금이 발생할 수 있습니다.</div>
													</div>
												</li>
											</ul>
										</li>
										<li>
											<a class="ob_zoomInOut" href="#"><strong>03. 렌트 당사자 외에 다른 사람이 대신 차량을 예약할 수 있나요?</strong></a>
											<ul class="inner_ul_txt">
												<li>
													<div class="fix">
														<div class="ob_zoomInOut"> 죽을래? </div>
													</div>
												</li>
											</ul>
										</li>
										<li>
											<a class="ob_zoomInOut" href="#"><strong>04. 가장 저렴한 렌터카를 찾는 방법이 있나요???</strong></a>
											<ul class="inner_ul_txt">
												<li>
													<div class="fix">
														<div class="ob_zoomInOut"> 없어 그런거 </div>
													</div>
												</li>
											</ul>
										</li>
										<li>
											<a class="ob_zoomInOut" href="#"><strong>05. 차량을 선택할 때 어떤 부분을 고려해야 하나요?</strong></a>
											<ul class="inner_ul_txt">
												<li>
													<div class="fix">
														<div class="ob_zoomInOut"> 니가 가진 돈 </div>
													</div>
												</li>
											</ul>
										</li>
									</ul>
								</div>								

								<h4 class="sub06_tit"><em>IT렌트카는 </em>고객님의 안전하고 신속한 거래를 위해 항상 준비되어 있습니다.</h4>							
								<ul class="mb50">
									<li>시바렌터카는 차량이용과 관련된 문의사항 및 요구사항을 신속하고 친절하게 처리해 드리도록 최선을 다하고 있습니다.</li>
									<li>24시간 영업지점안내 : 시바1지점, 시바2지점, 시바견지점</li>
									<li>24시간 문의 가능합니다^^(365일 연중무휴)</li>
									<li>02-1818-1818</li>
								</ul>

							</div>

						</div>
					</div>
				</div>
			</div>
			
			<jsp:include page="/inc/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>