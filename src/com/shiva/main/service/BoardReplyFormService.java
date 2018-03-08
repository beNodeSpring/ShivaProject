package com.shiva.main.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.controller.HttpUtil;
import com.shiva.main.dao.BoardDAO;
import com.shiva.main.vo.BoardVO;

public class BoardReplyFormService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pNum = request.getParameter("num");
		
		BoardDAO dao = new BoardDAO();
		
		BoardVO writing = dao.boardReplyForm(pNum);
		// 답글 작성에 필요한 원글 데이터를 속성에 세팅
		request.setAttribute("boardReplyForm", writing);
		
		HttpUtil.forward(request, response, "/main/boardReply.jsp");
	}

}
