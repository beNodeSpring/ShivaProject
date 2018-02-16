package com.shiva.main.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.controller.HttpUtil;
import com.shiva.main.dao.BoardDAO;
import com.shiva.main.vo.BoardVO;

public class BoardUpdateFormService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pNum = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardVO wrting = dao.boardUpdateForm(pNum);
		
		request.setAttribute("boardUpdateForm", wrting);
		
		HttpUtil.forward(request, response, "/main/boardUpdate.jsp");
	}

}
