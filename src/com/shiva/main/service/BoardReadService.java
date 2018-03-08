package com.shiva.main.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.controller.HttpUtil;
import com.shiva.main.dao.BoardDAO;
import com.shiva.main.vo.BoardVO;

public class BoardReadService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String inputNum = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardVO writing = dao.boardRead(inputNum);
		// 전달받은 num을 boardRead()의 매개변수로 넘겨서 리턴받은 결과값을 속성으로 세팅
		request.setAttribute("boardRead", writing);
		
		HttpUtil.forward(request, response, "/main/boardRead.jsp");		
	}

}
