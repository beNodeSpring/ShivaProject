//글 수정 처리 Action 클래스
package com.shiva.car.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.car.dao.BoardDAO;
import com.shiva.car.vo.BoardVO;


 public class BoardModifyAction implements Action {
	 public ActionForward execute(HttpServletRequest request,
			                      HttpServletResponse response) 
	 	throws Exception{
		 request.setCharacterEncoding("utf-8");
		 ActionForward forward = new ActionForward();
		 boolean result = false;
		 
		 int num=Integer.parseInt(request.getParameter("BOARD_NO"));
		 
		 BoardDAO boarddao=new BoardDAO();
		 BoardVO boarddata=new BoardVO();
		 
		
		 //수정 내용을 설정합니다.
			 boarddata.setBOARD_NO(num);
			 boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			 boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
			 
			 //DAO에서 수정 메서드 호출하여 수정합니다.
			 result = boarddao.boardModify(boarddata);
			 //수정에 실패한 경우
			 if(result==false){
		   		System.out.println("게시판 수정 실패");
		   		return null;
		   	 }
			 //수정 성공의 경우
		   	 System.out.println("게시판 수정 완료");
		   	 
		   	 forward.setRedirect(true);
		   	 //수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기위해 경로를 설정합니다.
		   	 forward.setPath(
		   	   "./CarBoardView.co?num="+boarddata.getBOARD_NO());
		   	 return forward;
	 }
}