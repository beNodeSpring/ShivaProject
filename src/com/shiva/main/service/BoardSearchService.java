package com.shiva.main.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.controller.HttpUtil;
import com.shiva.main.dao.BoardDAO;
import com.shiva.main.vo.BoardVO;

public class BoardSearchService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		String searchOpt = request.getParameter("searchOpt");
		String searchWord = request.getParameter("searchWord");
		
		List<BoardVO> list = dao.boardSearch(searchOpt, searchWord);
		request.setAttribute("boardList", list);
		
		int resultLen=list.size();
		request.setAttribute("resultLen", resultLen);
		
		HttpUtil.forward(request, response, "/main/boardSearchList.jsp");
	}

}
