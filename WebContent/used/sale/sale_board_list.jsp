<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "java.util.*" %>
<%@ page import = "com.shiva.used.VO.*" %>
<%@ page import = "com.shiva.used.dao.*" %>
	<%
			List<used_saleVO> boardlist = (ArrayList<used_saleVO>)request.getAttribute("boardlist");
			int listcount = ((Integer)request.getAttribute("listcount")).intValue();
			int nowpage= ((Integer)request.getAttribute("page")).intValue();
			int endpage = ((Integer)request.getAttribute("endpage")).intValue();
			int startpage = ((Integer)request.getAttribute("startpage")).intValue();
			int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
		%>
<!DOCTYPE html>
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">  
<title>회원 생성</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.table-responsive *{text-align: center;}
.table-responsive .tit{text-align: left;}
.form-control{display: inline-block; max-width: calc(100% - 60px);}
.tr_th th{background: #337ab7; color: #fff; font-size: 14px;}
.mb30{margin-bottom: 30px;}
.mb40{margin-bottom: 40px;}
.mb50{margin-bottom: 50px;}
<%-- 김재겸/css구분 --%>
	html, body{height:100%;}
	.used_board{min-height:100%;background : url(/ShivaProject/images/bg_main-body3.jpg) 50% 0 no-repeat;background-size: cover;}
	.sale_board{background : #fff; padding:60px;}
	.textarea_content{width : 100% ;height:500px;text-align:left; resize : none;}
</style>
<script src="../../js/jquery-3.3.1.min.js"></script>
</head>

	<jsp:include page="/inc/header.jsp" flush="false" />

	<body>
	<div class = "used_board">
		<div class = "container">
			<div class = "row">
				<div class = "modal-content sale_board">
						<%-- <tr>
							<th colspan = "3">중고거래 판매하기 게시판 - list</th>
							<th colspan = "2">
								<font size = 2>글 개수 : <%=listcount %></font>
							</th>
						</tr> --%>
						<a href = "./ListSaleController.uo" class="btn btn btn-warning">판매 게시판</a>
						<a href = "./ListBuyController.uo" class="btn btn btn-danger">구매 게시판</a>
						<h2 class = "board_title">중고거래 판매하기 게시판</h2>
						<form action = "SearchListSaleService.uo" method = "post" class = "text-right">
							<input type = "text" name = "searchList">
							<input type = "submit" value = "검색" class="btn btn btn-info">
						</form>
						<table class="table table-responsive mb50 table-bordered">
						<caption class="sr-only">게시판 제목</caption>
						<colgroup>
							<col width="10%">
							<col width="50%">
							<col width="10%">
							<col width="20%">
							<col width="10%">
						</colgroup>
						<%
							if(listcount > 0 ){
						%>
						
						<thead>
						<tr class="tr_th">
							<th><strong class="bd_num">번호</strong></th>
							<th>제목</th>
							<th>작성자</th>
							<th>날짜</th>
							<th>조회수</th>
						</tr>
						</thead>
						<tbody>
						<%
							for(int i = 0; i < boardlist.size();i++){
								used_saleVO bl = (used_saleVO)boardlist.get(i);
						%>
						<tr>
							<td><strong class="bd_num"><%=bl.getNUM_S() %></strong></td>
							<td class="tit">
							<div>
								
								<a href = "./DetailSaleService.uo?num=<%=bl.getNUM_S() %>" ><%=bl.getSUBJECT_S() %> </a>
								  
							</div>
							</td>
							
							<td>
								<div><strong class="bd_num"><%=bl.getNAME_S() %></strong></div>
							</td>
							<td>
								<div><strong class="bd_num"><%=bl.getDATE_S() %></strong></div>
							</td>
							<td>
								<div><strong class="bd_num"><%=bl.getREADCOUNT_S() %></strong></div>
							</td>
						</tr>
						
						<%
						}
						%>
						
						<tr>
							<td colspan = "5">
								<%if(nowpage <= 1){ %>
									[이전]&nbsp;
								<%}else{ %>
									<a href = "./ListSaleController.uo?page=<%=nowpage-1 %>">[이전]</a>
								<%} %>
								<%for(int a = startpage;a <= endpage;a++){
									if(a == nowpage){
									%>[<%=a %>]<% 	
									}else{
										%>
											<a href = "./ListSaleController.uo?page=<%=a %>">[<%=a %>]</a>
										<% 
									}
									%>
								<%} %>
								<%if(nowpage>= maxpage){%>
								
									[다음]
								<%}else{%>
									<a href = "./ListSaleController.uo?page=<%=nowpage+1 %>">[다음]</a>
								<%} %> 
							</td>
						</tr>
						<%
							}else{
						%>
						<tr>
							<td colspan = "4">판매하기 게시판</td>
							<td style="testalign:right"><font size = 2>등록된 게시글이 없습니다.</font></td>
						</tr>
						<%
							}
						%>
						</tbody>
					</table>
					<%-- <table class="table table-responsive mb50 table-bordered">
							<colgroup>
								<col width="80%">
								<col width="15%">
								<col width="5%">
							</colgroup>
							<tr>
								<td><input type = "hidden"></td>
								<td><input type = "text" name = "search-key"></td>
								<td><a href = "./ListSaleController.uo?page=<%=a %>">[<%=a %>]</a></td>
							</tr>
							
						</table> --%>
					<div class="text-right">
						<a href = "SaleBoardWrite.uo" class="btn btn-primary">글쓰기</a>
						<!-- <input type=submit value="버튼파랑" class="btn btn-primary">
						<input type=submit value="버튼초록" class="btn btn-success">
						<input type=submit value="버튼하늘" class="btn btn btn-info">
						<input type=submit value="버튼주황" class="btn btn btn-warning">
						<input type=submit value="버튼빨강" class="btn btn btn-danger"> -->
					</div>
				</div>
			</div>
			
			<jsp:include page="/inc/footer.jsp" flush="false" />
		</div>
		</div>
	</body>
</html>