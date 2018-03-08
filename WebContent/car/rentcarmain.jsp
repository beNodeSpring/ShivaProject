<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<title>시바렌트카</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
.trade_input_box li input[type="text"]{width: 100%; height: 35px; font-size: 14px; border: 1px solid #096099;}
.trade_input_box li input[type="checkbox"]{margin-right: 8px;}
.trade_input_box li p{line-height: 1.5; font-size: 13px; letter-spacing: -0.25px; color: #fff;}
.trade_input_box li .view_preamble{float: right; display: inline-block; width: 76px; height: 21px; line-height: 21px; text-align: center; color: #fff; font-size: 14px; border: 2px solid #fff;}
.trade_input_box li .btn_apply{width: 100%; padding-left: 120px; height: 53px; text-align: left; font-size: 22px; font-weight:bold; color: #fff; box-shadow: 2px 3px 0px rgba(1, 1, 1, 0.35); background: #00ccff url(../../images/main/ico_btn_apply.png) 82px 50% no-repeat; border: 1px solid #0e69a4;}

.boardlist{padding: 60px;}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(function(){
	$('.content_sec>div').hide();
	$('.content_sec>div').eq(0).show();
	$('.top_sec li').on('click', function() {
		var ti = $(this).index();
		$('.content_sec>div').hide();
		$('.content_sec>div').eq(ti).show();
	});
	
	var num = 13;
	$('.btn_apply').on('click', function(){
		var carName = $('#carName').val();
		if(carName=='') {
			alert('차량명을 입력해주세요');		
		}else{
			alert('렌트가 완료되었습니다.');
			num += 1;
			$('.table tbody').prepend('<tr><td><strong class="bd_num">'+ num +'</strong></td><td class="tit"><a href="#">'+carName+' 렌트 원합니다.연락부탁드립니다.</a></td><td>guest</td><td>2018-02-20</td><td>0</td></tr>')		
		}
	});
})
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
							<div class="reserve">
	
								<div class="trade_input_box">
									<ul>
										<li>
											<label for="" class="trade_tit"><em>차량명</em>렌트하시려는 차량명을 입력해주세요</label>
										</li>
										<li>
											<input type="text" id="carName" name="subject" value="" placeholder="예시) 투싼IX, K5, 스파크" class="">
										</li>
										<li>
											<label for="" class="trade_tit"><em>연락처/지역</em>고객님의 소중한 연락처를 남겨주세요</label>
										</li>
										<li>
											<input type="text" id="" name="phone" value="" placeholder="숫자만 입력하세요 예시) 01012345678" class="">
										</li>
										<li>
											<select name="option5" class="customSelect" id="area">
											<option value="">지역을 선택하세요</option>
											<option value="강원">강원</option>
											<option value="경기">경기</option>
											<option value="경남">경남</option>
											<option value="경북">경북</option>
											<option value="광주">광주</option>
											<option value="대구">대구</option>
											<option value="대전">대전</option>
											<option value="부산">부산</option>
											<option value="서울">서울</option>
											<option value="세종">세종</option>
											<option value="울산">울산</option>
											<option value="인천">인천</option>
											<option value="전남">전남</option>
											<option value="전북">전북</option>
											<option value="제주">제주</option>
											<option value="충남">충남</option>
											<option value="충북">충북</option>
											</select>
										</li>
										<li class="cf">
											<input type="checkbox" name="ch_flag" id="ch_flag" class=""><label for="" class="trade_tit">개인정보 수집/이용 동의</label><a href="#" class="view_preamble">전문보기</a>
										</li>
										<li>
											<button type="submit" class="btn_apply">렌트 신청</button>
										</li>
										<li>
											<p>입력하신 정보는 상담을 위한 참고 자료로만 활용됩니다.</p>
										</li>
									</ul>
								</div>
								
							</div>
						
							<div class="boardlist">
								<h3>렌트카 예약 확인</h3>
								<form class="form-inline" action="board" method="get">

									<table class="table table-responsive mb50" summary="게시판 설명">
										<caption class="sr-only">게시판 제목</caption>
										<colgroup>
											<col width="10%">
											<col width="50%">
											<col width="10%">
											<col width="20%">
											<col width="10%">
										</colgroup>
										<thead>
											<tr class="tr_th">
												<th scope="col">번호</th>
												<th scope="col">제목</th>
												<th scope="col">작성자</th>
												<th scope="col">작성일</th>
												<th scope="col">조회수</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><strong class="bd_num">13</strong></td>
												<td class="tit"><a href="#">Qm3 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">12</strong></td>
												<td class="tit"><a href="#">아반떼 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">11</strong></td>
												<td class="tit"><a href="#">코란도c 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">10</strong></td>
												<td class="tit"><a href="#">그랜드스타렉스5밴스틱 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">9</strong></td>
												<td class="tit"><a href="#">미니클럽맨 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">8</strong></td>
												<td class="tit"><a href="#">k3 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">7</strong></td>
												<td class="tit"><a href="#">싼타모플러스 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">6</strong></td>
												<td class="tit"><a href="#">소나타 브릴리언트 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">5</strong></td>
												<td class="tit"><a href="#">마티즈 크레이티브 그루브 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
											<tr>
												<td><strong class="bd_num">4</strong></td>
												<td class="tit"><a href="#">제네시스 렌트 원합니다.연락부탁드립니다.</a></td>
												<td>guest</td>
												<td>2018-01-18</td>
												<td>173</td>
											</tr>
										</tbody>
									</table>
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