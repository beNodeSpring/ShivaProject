package com.shiva.car.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.car.dao.BoardDAO;
import com.shiva.car.vo.BoardVO;

public class BoardModifyViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		 ActionForward forward = new ActionForward();
		 boolean result = false;
		 System.out.println("d="+ request.getParameter("BOARD_NO"));
		 int num=Integer.parseInt(request.getParameter("BOARD_NO"));
		 
		 BoardDAO boarddao=new BoardDAO();
		 BoardVO boarddata=new BoardVO();
		 
		 boarddata=boarddao.getDetail(num);
			 
		 request.setAttribute("board", boarddata);
			 
		   	 
		   	 forward.setRedirect(false);
		   	 //수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기위해 경로를 설정합니다.
		   	 forward.setPath(
		   	   "car/board_modify.jsp?num="+boarddata.getBOARD_NO());
		   	 return forward;
	}

}
