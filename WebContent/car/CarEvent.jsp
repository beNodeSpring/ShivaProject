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
.ul_car{overflow: hidden;}
.ul_car li{float: left; height: 165px; line-height: 165px; width: 20%;}
.ul_car li img{width: 100%;}
.pd60{padding: 60px;}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>

<jsp:include page="/inc/header.jsp"></jsp:include>

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
							
								<h3>평창 올림픽 개최기념 이벤트</h3>
								<p>자세한 사항은 이미지를 클릭해주세요</p>
								<img src="./car/images/event1.gif" alt="" />							
							
								<h3>랜덤 외제차 렌트 서비스</h3>
								<p>저희 IT CAR를 이용해 주시는 분들중 추첨을 통해서 렌트 차종을 외제차로 바꿔드립니다.</p>
								<p>차종은 변경될 수 있으며, 렌트 수령시에 당첨여부를 통지해드립니다</p>
								<ul class="ul_car">
									<li><img src="./car/images/1.jpg" alt="" /></li>
									<li><img src="./car/images/2.jpg" alt="" /></li>
									<li><img src="./car/images/3.jpg" alt="" /></li>
									<li><img src="./car/images/4.jpg" alt="" /></li>
									<li><img src="./car/images/5.jpg" alt="" /></li>
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