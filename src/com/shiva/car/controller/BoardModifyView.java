//글 수정 화면을 출력해주는 Action 클래스
package com.shiva.car.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.car.dao.BoardDAO;
import com.shiva.car.vo.BoardVO;



public class BoardModifyView implements Action {
	 public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
		 	request.setCharacterEncoding("euc-kr");
	   		
			BoardDAO boarddao=new BoardDAO();
		   	BoardVO boarddata=new BoardVO();
		   	
			int num=Integer.parseInt(request.getParameter("num"));
			//글 내용을 불러와서 boarddata객체에 저장합니다.
		   	boarddata=boarddao.getDetail(num);
		   	
		   	//글 내용 불러오기 실패한 경우입니다.
		   	if(boarddata==null){
		   		System.out.println("(수정)상세보기 실패");
		   		return null;
		   	}
		   	System.out.println("(수정)상세보기 성공");
		   	
		   	request.setAttribute("boarddata", boarddata);
		   	forward.setRedirect(false);
	   		forward.setPath("./car/board_modify.jsp");
	   		return forward;
	 }
}