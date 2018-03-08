<!--  글 내용을 보여주는 View 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.shiva.used.service.*" %>
<%@ page import="com.shiva.used.dao.*" %>
<%@ page import="com.shiva.used.VO.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%
//게시물의 내용이 담겨있는 boarddata 속성의 데이터를 BoardBean 타입으로 
//변환하여 얻습니다.
	used_saleVO VO = (used_saleVO)request.getAttribute("VO");
	List<used_sale_replyVO> VO_R = (ArrayList<used_sale_replyVO>)request.getAttribute("VO_R");
	System.out.println("VO : "+VO);
	System.out.println("VO_R : "+VO_R);
	session = request.getSession();
	System.out.println("session : "+session.getAttribute("id"));
%>
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
	.used_board{min-height:100%;background : url(/ShivaProject/images/bg_main-body3.jpg) 50% 0 no-repeat;background-size: cover;}
	.sale_board{background : #fff; padding:60px;}
	.textarea_content{width : 100% ;height:500px;text-align:left; resize : none;}
	.text_content{width : 100% ;text-align:left;}
	.reply-del{    height: 26px;
				    line-height: 26px;
				    padding: 0 8px;
				    font-size: 12px;}
	</style>
	<title>개발의 시발점</title>
	<script src="../../js/jquery-3.3.1.min.js"></script>
</head>

<body>

<jsp:include page="/inc/header.jsp" flush="false" />

<!-- 게시판 수정 -->
<div class = "used_board">
	<div class = "container">
		<div class = "row">
			<div class = "sale_board modal-content">
				<div class="text-right">
					<a href="./ListSaleController.uo" class="btn btn-primary">  목록보기  </a>
				</div>
				<table class="table table-responsive mb50 table-bordered">
					<caption class="sr-only">게시판 제목</caption>
					<colgroup>
						<col width="24%">
						<col width="50%">
						<col width="20%">
						<col width="6%">
					</colgroup>
					<thead>
						<tr valign="middle" class="tr_th">
							<th colspan="4">판매하기 게시판 본문글</th>
						</tr>	
					</thead>
					<tbody>
						<tr>
							<td>
								<div>글쓴이</div>
							</td>		
							<td colspan="3" class = "tit">
							<%=VO.getNAME_S()%>
							</td>
						</tr>	
						<tr>
							<td>
								<div>제 목</div>
							</td>		
							<td colspan="3" class = "tit">
							<%=VO.getSUBJECT_S()%><!-- 게시물의 제목을 출력합니다. -->
							</td>
						</tr>	
						<tr>
							<td>
								<div>내 용</div>
							</td>		
							<td colspan="3">		
							 <textarea name="BOARD_CONTENT" class = "textarea_content" readonly><%=VO.getCONTENT_S() %></textarea>

							</td>			
						</tr>	
						<%if(!(VO.getFILE_S()==null)){ 			
							%>
						<tr>
							<td>
								<div>사진</div>
							</td>
							<td colspan="3">
							<!-- 게시물에 첨부파일이 존재할 경우 첨부 파일의 링크를 표시합니다. -->
							
							 <img src="./sale_picture/<%=VO.getFILE_S()%>" width = "100%" height = "500px" align="left">
						    
							</td>
						</tr>
						<%} %>
						<%for(int i = 0; i < VO_R.size(); i++){
							used_sale_replyVO bl = (used_sale_replyVO)VO_R.get(i);
						%>
						
						<tr>
							<td>
								<%=bl.getNAME_S() %>
							</td>
							<td class = "tit">
								<%=bl.getCONTENT_S() %>
								
							</td>
							<td>
								<%=bl.getDATE_S() %>
							</td>
							<td>
								<%if(bl.getNAME_S().equals(session.getAttribute("id"))){ %>
								<a href="./DeleteReplySaleService.uo?num=<%=VO.getNUM_S() %>&content=<%=bl.getCONTENT_S() %>" class="btn btn btn-danger reply-del">
								  삭제  
								</a>
								<%} %>
							</td>
						</tr>
						<%} %>
						<form action = "./ReplySaleController.uo" method = "post" name = "reply">
							<tr>
								<td>
									댓글
								</td>
								<td>
									<input type = "text" name = "reply_content" id = "reply_content" class = "text_content">
									<input type = "hidden" name = num value = "<%=VO.getNUM_S() %>">
								</td>
								<td colspan = "2">
									<input type = "button" onclick="javascript:reply.submit()" value = "작성" class="btn btn btn-info">
								</td>
							</tr>
						</form>
			<%-- 답변, 수정, 삭제, 목록의 링크를 표시합니다. --%>
					</tbody>
				</table>
				<div class="text-right">
				<%--  <%if(session.getAttribute("id").equals(session.getAttribute("id"))){   --%>
				<%if(VO.getNAME_S().equals(session.getAttribute("id"))){ %>
					<a href="./DeleteSaleService.uo?num=<%=VO.getNUM_S() %>" class="btn btn btn-danger">
					  삭제  
					</a>
					<a href="./ModifySaleController.uo?num=<%=VO.getNUM_S() %>" class="btn btn btn-warning">
					  수정  
					</a>
					<a href="./SuccessSaleController.uo?num=<%=VO.getNUM_S() %>&subject=<%=VO.getSUBJECT_S() %>" class="btn btn-success">
					  판매완료  
					 </a>
					 <%} %>
					<a href="./ListSaleController.uo" class="btn btn-primary">  목록보기  </a>
					<%-- <input type=submit value="버튼파랑" class="btn btn-primary">
					<input type=submit value="버튼초록" class="btn btn-success">
					<input type=submit value="버튼하늘" class="btn btn btn-info">
					<input type=submit value="버튼주황" class="btn btn btn-warning">
					<input type=submit value="버튼빨강" class="btn btn btn-danger"> --%>
				</div>
			</div>
		</div>
		
		<jsp:include page="/inc/footer.jsp" flush="false" />
		
	</div>
</div>
</body>
</html>